package org.cobra.moreores.block.entity.gem;

import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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
import org.cobra.moreores.block.GemPurifierBlock;
import org.cobra.moreores.block.ModBlocks;
import org.cobra.moreores.block.entity.ModBlockEntityType;
import org.cobra.moreores.client.gui.screen.GemPurifierScreenHandler;
import org.cobra.moreores.item.ModItems;
import org.cobra.moreores.item.util.GemCategory;
import org.cobra.moreores.item.util.impl.Gemstone;
import org.cobra.moreores.item.util.impl.PurificationGemstones;
import org.cobra.moreores.networking.block.data.GemPurifierDataSynchronizer;
import org.cobra.moreores.networking.block.data.GemPurifierFluidDataPayload;
import org.cobra.moreores.recipe.GemPurifierRecipe;
import org.cobra.moreores.recipe.input.GemPurifyingRecipeInput;
import org.cobra.moreores.registry.ModItemTags;
import org.cobra.moreores.util.FluidStack;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class GemPurifierBlockEntity extends AbstractGemMachineBlockEntity<GemPurifierDataSynchronizer> {

    private FluidState fluidState = FluidState.IDLE;

    public final SingleVariantStorage<FluidVariant> fluidStorage = new SingleVariantStorage<>() {
        @Override
        protected FluidVariant getBlankVariant() {
            return FluidVariant.blank();
        }

        @Override
        protected long getCapacity(FluidVariant fluidStorage) {
            return FluidStack.convertDropletsToMb(FluidConstants.BUCKET * 810);
        }

        @Override
        protected void onFinalCommit() {
            markDirty();
            for (ServerPlayerEntity user : PlayerLookup.tracking((ServerWorld) world, getPos())) {
                ServerPlayNetworking.send(user, new GemPurifierFluidDataPayload(fluidStorage.variant, fluidStorage.amount, getPos()));
            }
        }
    };

    public static final int INGREDIENT_SLOT = 0;
    public static final int RESULT_SLOT = 1;
    public static final int ENERGY_SOURCE_SLOT = 2;
    public static final int FLUID_SOURCE_SLOT = 3;
    public static final int REDSTONE_SLOT = 4;

    private long lastRemovedWaterMilestone = 0;

    protected final PropertyDelegate propertyDelegate;
    private int maxProgressTick = 384;
    private final ServerRecipeManager.MatchGetter<GemPurifyingRecipeInput, GemPurifierRecipe> matchGetter = ServerRecipeManager.createCachedMatchGetter(GemPurifierRecipe.Type.INSTANCE);

    public GemPurifierBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityType.GEM_PURIFIER, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> GemPurifierBlockEntity.this.initialProgress;
                    case 1 -> GemPurifierBlockEntity.this.maxProgressTick;
                    case 2 -> GemPurifierBlockEntity.this.redstone;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> GemPurifierBlockEntity.this.initialProgress = value;
                    case 1 -> GemPurifierBlockEntity.this.maxProgressTick = value;
                    case 2 -> GemPurifierBlockEntity.this.redstone = value;
                }
            }

            @Override
            public int size() {
                return 3;
            }
        };
    }

    public void setFluid(FluidVariant variant, long waterLevel) {
        this.fluidStorage.variant = variant;
        this.fluidStorage.amount = waterLevel;
    }

    public ItemStack redstoneStack() {
        return getStack(REDSTONE_SLOT);
    }
    
    public long waterAmount() {
        return this.fluidStorage.amount;
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
    public int mainStackSize() {
        return 17;
    }

    @Override
    public long getEnergyCapacity() {
        return 10000000;
    }

    @Override
    public long getMaxEnergyInsert() {
        return 192000;
    }

    @Override
    public long getMaxEnergyExtract() {
        return 640000;
    }

    @Override
    public ServerRecipeManager.MatchGetter<?, ?> getMatchGetter() {
        return matchGetter;
    }

    @Override
    public int getInitialProgress() {
        return 0;
    }

    @Override
    public void writeData(WriteView view) {
        super.writeData(view);
        view.putLong("gem_purifier.water", fluidStorage.amount);
        view.putNullable("gem_purifier.fluidAmount.variant", FluidVariant.CODEC, fluidStorage.variant);
        view.putNullable("WaterState", FluidState.CODEC, fluidState);
        view.putNullable("GemType", PurificationGemstones.CODEC, getGem());
    }

    @Override
    public void readData(ReadView view) {
        super.readData(view);
        fluidStorage.amount = view.getLong("gem_purifier.water", 0);
        fluidStorage.variant = view.read("gem_purifier.fluidAmount.variant", FluidVariant.CODEC).orElse(FluidVariant.blank());
        fluidState = view.read("WaterState", FluidState.CODEC).orElse(FluidState.IDLE);
        gemstone = view.read("GemType", PurificationGemstones.CODEC).orElse(PurificationGemstones.NONE);
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
        if (side == Direction.DOWN) {
            return false;
        }

        if (slot == INGREDIENT_SLOT) {
            return ingredientStack().isIn(ModItemTags.RAW_GEMSTONE);
        }

        if (slot == ENERGY_SOURCE_SLOT) {
            return side == Direction.UP && (this.energyStack().isOf(ModItems.ENERGY_INGOT) || energyStack().isOf(ModBlocks.ENERGY_BLOCK.asItem()));  //
        }

        if(slot == FLUID_SOURCE_SLOT) {
            return side == Direction.UP && this.fluidStack().isOf(Items.WATER_BUCKET);
        }

        return false;
    }

    @Override
    public GemCategory category() {
        return GemCategory.PURIFYING;
    }

    @Override
    public GemPurifierDataSynchronizer getScreenOpeningData(ServerPlayerEntity serverPlayerEntity) {
        return new GemPurifierDataSynchronizer(energyAmount(), this.getRedstone(), fluidStorage.variant, fluidStorage.amount, this.pos);
    }

    @Override
    public boolean isValid(int slot, ItemStack stack) {
        return switch (slot) {
            case INGREDIENT_SLOT->
                    stack.isIn(ModItemTags.GEMSTONE) || stack.isIn(ModItemTags.RAW_GEMSTONE);
            case ENERGY_SOURCE_SLOT ->
                    stack.isOf(ModItems.ENERGY_INGOT) || stack.isOf(ModBlocks.ENERGY_BLOCK.asItem());
            case FLUID_SOURCE_SLOT ->
                    stack.isOf(Items.WATER_BUCKET);
            case RESULT_SLOT->
                    stack.isIn(ModItemTags.GEMSTONE);
            default -> false;
        };
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction side) {
        return side == Direction.DOWN && (slot == RESULT_SLOT);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return main;
    }

    // Tick Method
    // Logic per tick
    @Override
    public void tick(World world, BlockPos pos, BlockState state) {
        if (world.isClient()) {
            return;
        }

        redstoneTick++;
        
        Gemstone newGem = getGem();

        if (newGem != this.gemstone) {
            setGem(newGem);

            world.updateListeners(pos, getCachedState(), getCachedState(), 3);
        }

        ItemStack stack = redstoneStack();
        if((stack.isOf(Items.REDSTONE) || world.isReceivingRedstonePower(pos)) && redstone <= maxRedstone) {
            redstone += 10;
            markDirty(world, pos, state);
        }
        
        changeState();
        if(machineStatus == MachineStatus.RUNNING) {
            energyState = MachineStatus.EnergyState.EXTRACTING;
            if (isResultSlotEmptyOrReceivable() && hasRecipe() && hasEnoughEnergy() && hasEnoughWater()) {
                this.increaseProgress();
                if((!world.isReceivingRedstonePower(pos) || redstone > 0) && redstoneTick >= 20) {
                    redstone--;
                    redstoneTick = 0;
                }
                this.eatEnergy();
                this.drinkWater();
                if (hasPolishingFinished()) {
                    this.getPolishedGemstone();
                    this.resetProgress();
                }
                markDirty(world, pos, state);
            } else {
                this.resetProgress();
                this.machineStatus = MachineStatus.IDLE;
                markDirty(world, pos, state);
            }
        } else if (machineStatus.isPaused()) {
            energyState = MachineStatus.EnergyState.INSERTING;
            fluidState = FluidState.FILLING;
            giveEnergy();
            fillWater();
        } else {
            if((energyAmount() < 10_000_000 && hasEnergySourceProviderItem()) || (waterAmount() < 810000 && hasWaterBucket())) {
                energyState = MachineStatus.EnergyState.INSERTING;
                giveEnergy();
                fluidState = FluidState.FILLING;
                fillWater();
            } else {
                energyState = MachineStatus.EnergyState.IDLE;
                fluidState = FluidState.IDLE;
            }
        }

        validateEnergyAmount(ENERGY_SOURCE_SLOT);
        validateFluidAmount();
        validateRedstoneDust(REDSTONE_SLOT);
        markDirty(world, pos, state);
    }

    @Override
    public PurificationGemstones getGem() {
        Gemstone gem = super.getGem();
        if(gem instanceof PurificationGemstones p) {
            return p;
        }
        return PurificationGemstones.NONE;
    }

    private void changeState() {
        BlockState state = getCachedState();

        state = state.with(GemPurifierBlock.IS_POLISHING, getGem());


        if(state != getCachedState()) {
            world.setBlockState(pos, state, Block.NOTIFY_ALL);
        }
    }
    
    private void fillWater() {
        if(!hasWaterBucket() || waterAmount() >= 810000) {
            fluidState = FluidState.IDLE;
            return;
        }
        long amount = 1620;
        try(Transaction transaction = Transaction.openOuter()) {
            long inserted = fluidStorage.insert(FluidVariant.of(Fluids.WATER), FluidStack.convertDropletsToMb(amount), transaction);
            transaction.commit();
            if(inserted > 0) fluidState = FluidState.FILLING;
            else fluidState = FluidState.IDLE;
        }
    }

    private void drinkWater() {
        long amount = 810;
        try(Transaction transaction = Transaction.openOuter()) {
            fluidStorage.extract(FluidVariant.of(Fluids.WATER), FluidStack.convertDropletsToMb(amount), transaction);
            transaction.commit();
        }
        fluidState = FluidState.EMPTYING;
    }

    private void validateFluidAmount() {
        if(waterAmount() > 810000) {
            fluidStorage.amount = 810000;
        }

        long water = this.fluidStorage.amount;

        long [] milestones = {81000, 162000, 243000, 324000, 405000, 486000, 567000, 648000, 729000, 810000};

        for(long milestone : milestones) {
            if(water >= milestone && lastRemovedWaterMilestone < milestone) {
                this.removeStack(FLUID_SOURCE_SLOT, 1);
                this.setStack(FLUID_SOURCE_SLOT, new ItemStack(Items.BUCKET, 1));
                lastRemovedWaterMilestone = milestone;
                break;
            }
        }
    }

    @Override
    protected boolean hasEnoughEnergy() {
        return this.energyStorage.amount >= 128;
    }

    private boolean hasEnoughWater() {
        return this.fluidStorage.amount >= 1215;
    }

    private void resetProgress() {
        this.initialProgress = 0;
    }

    private void getPolishedGemstone() {
        RecipeEntry<GemPurifierRecipe> recipe = currentRecipe().orElseThrow();

        this.removeStack(INGREDIENT_SLOT, 1);

        this.setStack(RESULT_SLOT, new ItemStack(recipe.value().getResult().getItem(),
                this.resultStack().getCount() + recipe.value().getResult().getCount()));
    }
    private boolean hasPolishingFinished() {
        return initialProgress >= maxProgressTick;
    }

    @Override
    public void increaseProgress() {
        if(this.world.isReceivingRedstonePower(this.pos) || redstone > 0) {
            initialProgress += 5;
        } else {
            initialProgress++;
        }
    }

    @Override
    protected boolean hasRecipe() {
        Optional<RecipeEntry<GemPurifierRecipe>> recipe = currentRecipe();

        return recipe.isPresent() && hasEnoughEnergy() && canInsertCountIntoResultSlot(recipe.get().value().getResult())
                && canInsertItemIntoResultSlot(recipe.get().value().getResult().getItem());
    }

    private boolean hasWaterBucket() {
        return this.fluidStack().isOf(Items.WATER_BUCKET);
    }

    private Optional<RecipeEntry<GemPurifierRecipe>> currentRecipe() {
        ServerWorld serverWorld = (ServerWorld) world;
        return this.matchGetter.getFirstMatch(new GemPurifyingRecipeInput(this.ingredientStack()), serverWorld);
    }

    private boolean canInsertItemIntoResultSlot(Item item) {
        return this.resultStack().getItem() == item || this.resultStack().isEmpty() || this.resultStack().isIn(ModItemTags.GEMSTONE)
                || this.resultStack().isIn(ModItemTags.RAW_GEMSTONE);
    }

    private boolean canInsertCountIntoResultSlot(ItemStack result) {
        return this.resultStack().getCount() + result.getCount() <= this.resultStack().getMaxCount();
    }

    private boolean isResultSlotEmptyOrReceivable() {
        return this.resultStack().isEmpty() || this.resultStack().getCount() < this.resultStack().getMaxCount();
    }

    @Override
    protected void giveEnergy() {
        if(!hasEnergySourceProviderItem() || energyStorage.amount >= 10_000_000) {
            energyState = MachineStatus.EnergyState.IDLE;
            return;
        }
        long amount = energyStack().isOf(ModItems.ENERGY_INGOT) ? 1024 : 1536;
        if(world.isReceivingRedstonePower(pos)) amount *= 5;
        try(Transaction transaction = Transaction.openOuter()) {
            long inserted = energyStorage.insert(amount, transaction);
            transaction.commit();
            if(inserted > 0) energyState = MachineStatus.EnergyState.INSERTING;
            else energyState = MachineStatus.EnergyState.IDLE;
        }
    }

    @Override
    protected void eatEnergy() {
        long amount = world.isReceivingRedstonePower(pos) ? 640 : 128;
        try(Transaction transaction = Transaction.openOuter()) {
            energyStorage.extract(amount, transaction);
            transaction.commit();
        }
        energyState = MachineStatus.EnergyState.EXTRACTING;
    }
}