package net.cobra.moreores.block.entity;

import net.cobra.moreores.block.GemPurifierBlock;
import net.cobra.moreores.block.ModBlocks;
import net.cobra.moreores.block.data.GemPurifierData;
import net.cobra.moreores.item.ModItems;
import net.cobra.moreores.recipe.GemPurifierRecipe;
import net.cobra.moreores.recipe.input.GemPurifyingRecipeInput;
import net.cobra.moreores.registry.ModItemTags;
import net.cobra.moreores.screen.GemPurifierScreenHandler;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.ServerRecipeManager;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import team.reborn.energy.api.EnergyStorage;
import team.reborn.energy.api.EnergyStorageUtil;
import team.reborn.energy.api.base.SimpleEnergyStorage;

import java.util.Optional;

public class GemPurifierBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory<GemPurifierData>, ImplementedInventory, TickableBlockEntity {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(15, ItemStack.EMPTY);
    private PolishingState firstPolishingState = PolishingState.IDLE;

    public final SimpleEnergyStorage energyStorage = new SimpleEnergyStorage(1_000_000, 64,128) {
        @Override
        public void onFinalCommit() {
            super.onFinalCommit();

            markDirty();

            for(ServerPlayerEntity user : PlayerLookup.tracking((ServerWorld) world, getPos())) {
                ServerPlayNetworking.send(user, new GemPurifierData(this.amount, getPos()));
            }
        }
    };

    public static final int INGREDIENT_SLOT = 0;
    public static final int RESULT_SLOT = 1;
    public static final int ENERGY_SOURCE_SLOT = 2;

    private long lastRemovedEnergyMilestone = 0;

    protected final PropertyDelegate propertyDelegate;
    private int firstIngredientInitialProgress = 0;
    private int firstSlotMaxProgressTick = 384;
    private final ServerRecipeManager.MatchGetter<GemPurifyingRecipeInput, GemPurifierRecipe> matchGetter = ServerRecipeManager.createCachedMatchGetter(GemPurifierRecipe.Type.GEM_POLISHING);

    public GemPurifierBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityType.GEM_PURIFIER_BLOCK_ENTITY, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> GemPurifierBlockEntity.this.firstIngredientInitialProgress;
                    case 1 -> GemPurifierBlockEntity.this.firstSlotMaxProgressTick;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> GemPurifierBlockEntity.this.firstIngredientInitialProgress = value;
                    case 1 -> GemPurifierBlockEntity.this.firstSlotMaxProgressTick = value;
                }
            }

            @Override
            public int size() {
                return 2;
            }
        };
    }

    public void setEnergyLevel(long energyLevel) {
        this.energyStorage.amount = energyLevel;
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup) {
        return createNbt(registryLookup);
    }

    @Override
    protected void writeData(WriteView view) {
        super.writeData(view);
        Inventories.writeData(view, inventory);
        view.putInt("gem_purifier.progress", firstIngredientInitialProgress);
        view.putLong("gem_purifier.energy", energyStorage.amount);
        view.putNullable("PolishingState", PolishingState.CODEC, firstPolishingState);
    }

    @Override
    protected void readData(ReadView view) {
        super.readData(view);
        Inventories.readData(view, inventory);
        firstIngredientInitialProgress = view.getInt("gem_purifier.progress", 0);
        energyStorage.amount = view.getLong("gem_purifier.energy", 0);
        firstPolishingState = (PolishingState) view.read("PolishingState", PolishingState.CODEC).orElse(PolishingState.IDLE);
    }

    @Override
    public Text getDisplayName() {
        return ModBlocks.GEM_PURIFIER_BLOCK.getName();
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new GemPurifierScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction side) {
        return this.isValid(slot, stack);
    }


    @Override
    public GemPurifierData getScreenOpeningData(ServerPlayerEntity serverPlayerEntity) {
        return new GemPurifierData(this.energyStorage.amount, this.pos);
    }

    @Override
    public boolean isValid(int slot, ItemStack stack) {
        return switch (slot) {
            case INGREDIENT_SLOT ->
                    stack.isIn(ModItemTags.GEMSTONE) || stack.isIn(ModItemTags.RAW_GEMSTONE);
            case ENERGY_SOURCE_SLOT ->
                    stack.isOf(ModItems.ENERGY_INGOT) || stack.isOf(ModBlocks.ENERGY_BLOCK.asItem());
            case RESULT_SLOT ->
                    stack.isIn(ModItemTags.GEMSTONE);
            default -> false;
        };
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction side) {
        return slot == RESULT_SLOT;
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public void tick(World world, BlockPos pos, BlockState state) {
        if (world.isClient()) {
            return;
        }


        if (this.energyStorage.amount > 0 && !getCachedState().get(GemPurifierBlock.HAS_ENERGY)) {
            world.setBlockState(pos, getCachedState().with(GemPurifierBlock.HAS_ENERGY, true), Block.NOTIFY_ALL);
        } else if (this.energyStorage.amount == 0 && getCachedState().get(GemPurifierBlock.HAS_ENERGY)) {
            world.setBlockState(pos, getCachedState().with(GemPurifierBlock.HAS_ENERGY, false), Block.NOTIFY_ALL);
        }

        insertEnergy();

        checkForEnoughEnergyAndRemoveItem();

        if(firstPolishingState == PolishingState.RUNNING) {

            if (isResultSlotEmptyOrReceivable() && hasRecipe() && hasEnoughEnergy()) {
                this.increaseProgress();
                this.extractEnergy();
                if (hasPolishingFinished()) {
                    this.getPolishedGemstone();
                    this.resetProgress();
                }
                markDirty(world, pos, state);
            } else {
                this.resetProgress();
                this.firstPolishingState = PolishingState.IDLE;
                markDirty(world, pos, state);
            }
        }
    }

    private void insertEnergy() {
        if (hasEnergySourceProviderItem()) {
            if(getStack(ENERGY_SOURCE_SLOT).isOf(ModItems.ENERGY_INGOT)) {
                try (Transaction transaction = Transaction.openOuter()) {
                    this.energyStorage.insert(32, transaction);

                    if (this.world.isReceivingRedstonePower(this.pos)) {
                        this.energyStorage.insert(1024, transaction);
                    }
                    transaction.commit();
                }
            } else {
                try (Transaction transaction = Transaction.openOuter()) {
                    this.energyStorage.insert(48, transaction);

                    if (this.world.isReceivingRedstonePower(this.pos)) {
                        this.energyStorage.insert(1192, transaction);
                    }
                    transaction.commit();
                }
            }

            // Under testing
            EnergyStorage energyItem = EnergyStorage.ITEM.find(getStack(ENERGY_SOURCE_SLOT), null);
            if(energyItem != null) {
                if(getStack(ENERGY_SOURCE_SLOT).isOf(ModItems.ENERGY_INGOT)) {
                    try (Transaction transaction = Transaction.openOuter()) {
                        EnergyStorageUtil.move(energyItem, energyStorage, 32, transaction);
                        if(this.world.isReceivingRedstonePower(this.pos)) {
                            EnergyStorageUtil.move(energyItem, energyStorage, 1024, transaction);
                        }
                        transaction.commit();
                    }
                } else if (getStack(ENERGY_SOURCE_SLOT).isOf(ModBlocks.ENERGY_BLOCK.asItem())) {
                    try(Transaction transaction = Transaction.openOuter()) {
                        EnergyStorageUtil.move(energyItem, energyStorage, 48, transaction);
                        if(this.world.isReceivingRedstonePower(this.pos)) {
                            EnergyStorageUtil.move(energyItem, energyStorage, 1192, transaction);
                        }
                        transaction.commit();
                    }
                }
            }
        }
    }

    private void checkForEnoughEnergyAndRemoveItem() {
        long energy = this.energyStorage.amount;

        long [] milestones = {250000, 500000, 750000, 1000000};

        for(long milestone : milestones) {
            if(energy >= milestone && lastRemovedEnergyMilestone < milestone) {
                this.removeStack(ENERGY_SOURCE_SLOT, 1);
                lastRemovedEnergyMilestone = milestone;
                break;
            }
        }
    }

    private void extractEnergy() {
        try(Transaction transaction = Transaction.openOuter()) {
            this.energyStorage.extract(16, transaction);
            transaction.commit();
        }
    }

    private boolean hasEnoughEnergy() {
        return this.energyStorage.amount >= 16;
    }

    private void resetProgress() {
        this.firstIngredientInitialProgress = 0;
    }

    private void getPolishedGemstone() {
        RecipeEntry<GemPurifierRecipe> recipe = currentRecipe().orElseThrow();

        this.removeStack(INGREDIENT_SLOT, 1);

        this.setStack(RESULT_SLOT, new ItemStack(recipe.value().getResult().getItem(),
                getStack(RESULT_SLOT).getCount() + recipe.value().getResult().getCount()));
    }

    private boolean hasPolishingFinished() {
        return firstIngredientInitialProgress >= firstSlotMaxProgressTick;
    }

    public void increaseProgress() {
        if(this.world.isReceivingRedstonePower(this.pos)) {
            firstIngredientInitialProgress += 5;
        } else {

            firstIngredientInitialProgress++;
        }
    }

    private boolean hasRecipe() {
        Optional<RecipeEntry<GemPurifierRecipe>> recipe = currentRecipe();

        return recipe.isPresent() && hasEnoughEnergy() && canInsertCountIntoResultSlot(recipe.get().value().getResult())
                && canInsertItemIntoResultSlot(recipe.get().value().getResult().getItem());
    }

    private boolean hasEnergySourceProviderItem() {
        return this.getStack(ENERGY_SOURCE_SLOT).isOf(ModItems.ENERGY_INGOT) || this.getStack(ENERGY_SOURCE_SLOT).isOf(ModBlocks.ENERGY_BLOCK.asItem());
    }

    private Optional<RecipeEntry<GemPurifierRecipe>> currentRecipe() {
        ServerWorld serverWorld = (ServerWorld) world;
        return this.matchGetter.getFirstMatch(new GemPurifyingRecipeInput(this.getStack(INGREDIENT_SLOT)), serverWorld);
    }

    private boolean canInsertItemIntoResultSlot(Item item) {
        return this.getStack(RESULT_SLOT).getItem() == item || this.getStack(RESULT_SLOT).isEmpty() || this.getStack(RESULT_SLOT).isIn(ModItemTags.GEMSTONE) || this.getStack(RESULT_SLOT).isIn(ModItemTags.RAW_GEMSTONE);
    }

    private boolean canInsertCountIntoResultSlot(ItemStack result) {
        return this.getStack(RESULT_SLOT).getCount() + result.getCount() <= getStack(RESULT_SLOT).getMaxCount();
    }

    private boolean isResultSlotEmptyOrReceivable() {
        return this.getStack(RESULT_SLOT).isEmpty() || this.getStack(RESULT_SLOT).getCount() < this.getStack(RESULT_SLOT).getMaxCount();
    }

    public void startPolish() {
        if(firstPolishingState.isIdle() && hasRecipe() && hasEnoughEnergy()) {
            firstPolishingState = PolishingState.RUNNING;
        }
    }

    public void pausePolish() {
        if(firstPolishingState.isRunning()) {
            firstPolishingState = PolishingState.PAUSED;
        }
    }

    public void resumePolish() {
        if(firstPolishingState.isPaused()) {
            firstPolishingState = PolishingState.RUNNING;
        }
    }

    public void stopPolish() {
        if (!firstPolishingState.isIdle()) {
            firstPolishingState = PolishingState.IDLE;
            resetProgress();
        }
    }
}