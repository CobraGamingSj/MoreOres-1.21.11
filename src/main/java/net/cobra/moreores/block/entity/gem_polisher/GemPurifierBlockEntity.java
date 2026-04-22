package net.cobra.moreores.block.entity.gem_polisher;

import net.cobra.moreores.block.GemPurifierBlock;
import net.cobra.moreores.block.ModBlocks;
import net.cobra.moreores.block.data.GemPurifierEnergyData;
import net.cobra.moreores.block.data.GemPurifierFluidData;
import net.cobra.moreores.block.data.GemPurifierSynchronizer;
import net.cobra.moreores.block.entity.ImplementedInventory;
import net.cobra.moreores.block.entity.ModBlockEntityType;
import net.cobra.moreores.block.entity.TickableBlockEntity;
import net.cobra.moreores.item.util.GemType;
import net.cobra.moreores.item.ModItems;
import net.cobra.moreores.recipe.GemPurifierRecipe;
import net.cobra.moreores.recipe.input.GemPurifyingRecipeInput;
import net.cobra.moreores.registry.ModItemTags;
import net.cobra.moreores.client.gui.screen.GemPurifierScreenHandler;
import net.cobra.moreores.util.FluidStack;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.Inventories;
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
import org.jetbrains.annotations.Nullable;
import team.reborn.energy.api.base.SimpleEnergyStorage;

import java.util.Optional;

public class GemPurifierBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory<GemPurifierSynchronizer>, ImplementedInventory, TickableBlockEntity {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(16, ItemStack.EMPTY);
    private PolishingState polishingState = PolishingState.IDLE;
    private EnergyState energyState = EnergyState.IDLE;
    private WaterFluidState waterState = WaterFluidState.IDLE;
    private GemType gemType = GemType.EMPTY;

    public final SimpleEnergyStorage energyStorage = new SimpleEnergyStorage(10_000_000, 192000, 640000) {
        @Override
        public void onFinalCommit() {
            super.onFinalCommit();

            markDirty();

            for(ServerPlayerEntity user : PlayerLookup.tracking((ServerWorld) world, getPos())) {
                ServerPlayNetworking.send(user, new GemPurifierEnergyData(this.amount, getPos()));
            }
        }
    };
    public final SingleVariantStorage<FluidVariant> fluidStorage = new SingleVariantStorage<FluidVariant>() {
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
            for(ServerPlayerEntity user : PlayerLookup.tracking((ServerWorld) world, getPos())) {
                ServerPlayNetworking.send(user, new GemPurifierFluidData(fluidStorage.variant, fluidStorage.amount, getPos()));
            }
        }
    };

    public static final int INGREDIENT_SLOT = 0;
    public static final int RESULT_SLOT = 1;
    public static final int ENERGY_SOURCE_SLOT = 2;
    public static final int WATER_SOURCE_SLOT = 3;

    private long lastRemovedEnergyMilestone = 0;
    private long lastRemovedWaterMilestone = 0;

    protected final PropertyDelegate propertyDelegate;
    private int initialProgress = 0;
    private int maxProgressTick = 384;
    private final ServerRecipeManager.MatchGetter<GemPurifyingRecipeInput, GemPurifierRecipe> matchGetter = ServerRecipeManager.createCachedMatchGetter(GemPurifierRecipe.Type.INSTANCE);

    public GemPurifierBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityType.GEM_PURIFIER_BLOCK_ENTITY, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> GemPurifierBlockEntity.this.initialProgress;
                    case 1 -> GemPurifierBlockEntity.this.maxProgressTick;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> GemPurifierBlockEntity.this.initialProgress = value;
                    case 1 -> GemPurifierBlockEntity.this.maxProgressTick = value;
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

    public void setWaterLevel(FluidVariant variant, long waterLevel) {
        this.fluidStorage.variant = variant;
        this.fluidStorage.amount = waterLevel;
    }

    public long energyAmount() {
        return this.energyStorage.amount;
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
    protected void writeData(WriteView view) {
        super.writeData(view);
        Inventories.writeData(view, inventory);
        view.putInt("gem_purifier.progress", initialProgress);
        view.putLong("gem_purifier.fluid", energyStorage.amount);
        view.putLong("gem_purifier.water", fluidStorage.amount);
        view.putNullable("gem_purifier.fluid.variant", FluidVariant.CODEC, fluidStorage.variant);
        view.putNullable("PolishingState", PolishingState.CODEC, polishingState);
        view.putNullable("EnergyState", EnergyState.CODEC, energyState);
        view.putNullable("WaterFluidState", WaterFluidState.CODEC, waterState);
        view.putNullable("GemType", GemType.CODEC, gemType);
    }

    @Override
    protected void readData(ReadView view) {
        super.readData(view);
        Inventories.readData(view, inventory);
        initialProgress = view.getInt("gem_purifier.progress", 0);
        energyStorage.amount = view.getLong("gem_purifier.fluid", 0);
        fluidStorage.amount = view.getLong("gem_purifier.water", 0);
        fluidStorage.variant = view.read("gem_purifier.fluid.variant", FluidVariant.CODEC).orElse(FluidVariant.blank());
        polishingState = view.read("PolishingState", PolishingState.CODEC).orElse(PolishingState.IDLE);
        energyState = view.read("EnergyState", EnergyState.CODEC).orElse(EnergyState.IDLE);
        waterState = view.read("WaterFluidState", WaterFluidState.CODEC).orElse(WaterFluidState.IDLE);
        gemType = view.read("GemType", GemType.CODEC).orElse(GemType.EMPTY);
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
    public void setStack(int slot, ItemStack stack) {
        ImplementedInventory.super.setStack(slot, stack);
        if(slot == RESULT_SLOT) {
            gemType = detectGem(stack);
        }
    }

    public GemType detectGem(ItemStack stack) {
        if (stack.isOf(ModItems.RUBY)) return GemType.RUBY;
        if (stack.isOf(ModItems.SAPPHIRE)) return GemType.SAPPHIRE;
        if (stack.isOf(ModItems.GREEN_SAPPHIRE)) return GemType.GREEN_SAPPHIRE;
        if (stack.isOf(ModItems.BLUE_GARNET)) return GemType.BLUE_GARNET;
        if (stack.isOf(ModItems.PINK_GARNET)) return GemType.PINK_GARNET;
        if (stack.isOf(ModItems.GREEN_GARNET)) return GemType.GREEN_GARNET;
        if (stack.isOf(ModItems.KYAWTHUITE)) return GemType.KYAWTHUITE;
        if (stack.isOf(ModItems.TOPAZ)) return GemType.TOPAZ;
        if (stack.isOf(ModItems.WHITE_TOPAZ)) return GemType.WHITE_TOPAZ;
        if (stack.isOf(ModItems.PERIDOT)) return GemType.PERIDOT;
        if (stack.isOf(ModItems.JADE)) return GemType.JADE;
        if (stack.isOf(ModItems.PYROPE)) return GemType.PYROPE;
        return GemType.EMPTY;
    }

    public GemType getGem() {
        return detectGem(resultStack());
    }

    public void setGem(GemType gem) {
        gemType = gem;
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

        if(slot == WATER_SOURCE_SLOT) {
            return side == Direction.UP && this.fluidStack().isOf(Items.WATER_BUCKET);
        }

        return false;
    }


    @Override
    public GemPurifierSynchronizer getScreenOpeningData(ServerPlayerEntity serverPlayerEntity) {
        return new GemPurifierSynchronizer(energyAmount(), fluidStorage.variant, fluidStorage.amount, this.pos);
    }

    @Override
    public boolean isValid(int slot, ItemStack stack) {
        return switch (slot) {
            case INGREDIENT_SLOT->
                    stack.isIn(ModItemTags.GEMSTONE) || stack.isIn(ModItemTags.RAW_GEMSTONE);
            case ENERGY_SOURCE_SLOT ->
                    stack.isOf(ModItems.ENERGY_INGOT) || stack.isOf(ModBlocks.ENERGY_BLOCK.asItem());
            case WATER_SOURCE_SLOT ->
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
        return inventory;
    }


    // Tick Method
    // Logic per tick
    @Override
    public void tick(World world, BlockPos pos, BlockState state) {
        if (world.isClient()) {
            return;
        }

        ItemStack result = this.resultStack();
        GemType newGem = detectGem(result);

        if (newGem != this.gemType) {
            setGem(newGem);

            world.updateListeners(pos, getCachedState(), getCachedState(), 3);
        }

        changeState();

        if(polishingState == PolishingState.RUNNING) {
            energyState = EnergyState.EXTRACTING;
            if (isResultSlotEmptyOrReceivable() && hasRecipe() && hasEnoughEnergy() && hasEnoughWater()) {
                this.increaseProgress();
                this.extractEnergy();
                this.consumeWater();
                if (hasPolishingFinished()) {
                    this.getPolishedGemstone();
                    this.resetProgress();
                }
                markDirty(world, pos, state);
            } else {
                this.resetProgress();
                this.polishingState = PolishingState.IDLE;
                markDirty(world, pos, state);
            }
        } else if (polishingState.isPaused()) {
            energyState = EnergyState.INSERTING;
            waterState = WaterFluidState.FILLING;
            insertEnergy();
            fillWater();
        } else {
            if((energyAmount() < 10_000_000 && hasEnergySourceProviderItem()) || (waterAmount() < 810000 && hasWaterBucket())) {
                energyState = EnergyState.INSERTING;
                insertEnergy();
                waterState = WaterFluidState.FILLING;
                fillWater();
            } else {
                energyState = EnergyState.IDLE;
                waterState= WaterFluidState.IDLE;
            }
        }

        checkForEnoughEnergyAndRemoveItem();
        checkForEnoughWaterAndRemoveBucket();
        markDirty(world, pos, state);
    }

    private void changeState() {
        BlockState state = getCachedState();

        state = state.with(GemPurifierBlock.IS_POLISHING, detectGem(resultStack()));


        if(state != getCachedState()) {
            world.setBlockState(pos, state, Block.NOTIFY_ALL);
        }

//        if(initialProgress > 0) {
//            world.setBlockState(pos, getCachedState().with(GemPurifierBlock.IS_POLISHING, true), Block.NOTIFY_ALL);
//            MoreOresModInitializer.LOGGER.debug("Gem Purifier at {} is polishing. Progress: {}/{}", pos, propertyDelegate.get(0), propertyDelegate.get(1));
//        } else {
//            world.setBlockState(pos, getCachedState().with(GemPurifierBlock.IS_POLISHING, false), Block.NOTIFY_ALL);
//        }
//
//        if (this.energyStorage.amount > 0 && !getCachedState().get(GemPurifierBlock.HAS_ENERGY)) {
//            world.setBlockState(pos, getCachedState().with(GemPurifierBlock.HAS_ENERGY, true), Block.NOTIFY_ALL);
//        } else if (this.energyStorage.amount == 0 && getCachedState().get(GemPurifierBlock.HAS_ENERGY)) {
//            world.setBlockState(pos, getCachedState().with(GemPurifierBlock.HAS_ENERGY, false), Block.NOTIFY_ALL);
//        }
    }

    private void insertEnergy() {
        if(!hasEnergySourceProviderItem() || energyAmount() >= 10_000_000) {
            energyState = EnergyState.IDLE;
            return;
        }
        long amount = energyStack().isOf(ModItems.ENERGY_INGOT) ? 1024 : 1536;
        if(world.isReceivingRedstonePower(pos)) amount *= 5;
        try(Transaction transaction = Transaction.openOuter()) {
            long inserted = energyStorage.insert(amount, transaction);
            transaction.commit();
            if(inserted > 0) energyState = EnergyState.INSERTING;
            else energyState = EnergyState.IDLE;
        }
    }

    private void fillWater() {
        if(!hasWaterBucket() || waterAmount() >= 810000) {
            waterState = WaterFluidState.IDLE;
            return;
        }
        long amount = 1620;
        try(Transaction transaction = Transaction.openOuter()) {
            long inserted = fluidStorage.insert(FluidVariant.of(Fluids.WATER), FluidStack.convertDropletsToMb(amount), transaction);
            transaction.commit();
            if(inserted > 0) waterState = WaterFluidState.FILLING;
            else waterState = WaterFluidState.IDLE;
        }
    }

    private void extractEnergy() {
        long amount = world.isReceivingRedstonePower(pos) ? 640 : 128;
        try(Transaction transaction = Transaction.openOuter()) {
            energyStorage.extract(amount, transaction);
            transaction.commit();
        }
        energyState = EnergyState.EXTRACTING;
    }

    private void consumeWater() {
        long amount = 810;
        try(Transaction transaction = Transaction.openOuter()) {
            fluidStorage.extract(FluidVariant.of(Fluids.WATER), FluidStack.convertDropletsToMb(amount), transaction);
            transaction.commit();
        }
        waterState = WaterFluidState.EMPTYING;
    }

    private void checkForEnoughEnergyAndRemoveItem() {
        long energy = this.energyStorage.amount;

        long [] milestones = {1000000, 2000000, 3000000, 4000000, 5000000, 6000000, 7000000, 8000000, 8000000, 10000000};

        for(long milestone : milestones) {
            if(energy >= milestone && lastRemovedEnergyMilestone < milestone) {
                this.removeStack(ENERGY_SOURCE_SLOT, 1);
                lastRemovedEnergyMilestone = milestone;
                break;
            }
        }
    }

    private void checkForEnoughWaterAndRemoveBucket() {
        long water = this.fluidStorage.amount;

        long [] milestones = {81000, 162000, 243000, 324000, 405000, 486000, 567000, 648000, 729000, 810000};

        for(long milestone : milestones) {
            if(water >= milestone && lastRemovedWaterMilestone < milestone) {
                this.removeStack(WATER_SOURCE_SLOT, 1);
                this.setStack(WATER_SOURCE_SLOT, new ItemStack(Items.BUCKET, 1));
                lastRemovedWaterMilestone = milestone;
                break;
            }
        }
    }

    private boolean hasEnoughEnergy() {
        return this.energyStorage.amount >= 128;
    }

    private boolean hasEnoughWater() {
        return this.fluidStorage.amount >= 1024;
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

    public void increaseProgress() {
        if(this.world.isReceivingRedstonePower(this.pos)) {
            initialProgress += 5;
        } else {
            initialProgress++;
        }
    }

    private boolean hasRecipe() {
        Optional<RecipeEntry<GemPurifierRecipe>> recipe = currentRecipe();

        return recipe.isPresent() && hasEnoughEnergy() && canInsertCountIntoResultSlot(recipe.get().value().getResult())
                && canInsertItemIntoResultSlot(recipe.get().value().getResult().getItem());
    }

    private boolean hasEnergySourceProviderItem() {
        return this.energyStack().isOf(ModItems.ENERGY_INGOT) || this.energyStack().isOf(ModBlocks.ENERGY_BLOCK.asItem());
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

    public void startPolish() {
        if(polishingState.isIdle() && hasRecipe() && hasEnoughEnergy()) {
            polishingState = PolishingState.RUNNING;
        }
    }

    public void pausePolish() {
        if(polishingState.isRunning()) {
            polishingState = PolishingState.PAUSED;
        }
    }

    public void resumePolish() {
        if(polishingState.isPaused()&& hasRecipe() && hasEnoughEnergy()) {
            polishingState = PolishingState.RUNNING;
        }
    }

    public void stopPolish() {
        if(!polishingState.isIdle()) {
            polishingState = PolishingState.IDLE;
            resetProgress();
        }
    }
}