package net.cobra.moreores.block.entity.gem;

import net.cobra.moreores.block.GemInfusionBlock;
import net.cobra.moreores.block.ModBlocks;
import net.cobra.moreores.networking.block.data.GemInfusionDataSynchronizer;
import net.cobra.moreores.block.entity.ModBlockEntityType;
import net.cobra.moreores.client.gui.screen.GemInfusionScreenHandler;
import net.cobra.moreores.item.ModItems;
import net.cobra.moreores.item.util.GemType;
import net.cobra.moreores.recipe.GemInfusionRecipe;
import net.cobra.moreores.recipe.input.GemInfusionRecipeInput;
import net.cobra.moreores.registry.ModItemTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
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

import java.util.Optional;

public class GemInfusionBlockEntity extends AbstractGemPFBlockEntity<GemInfusionDataSynchronizer> {

    public static final int INGREDIENT_BEFORE_SLOT = 0;
    public static final int INGREDIENT_AFTER_SLOT = 1;
    public static final int RESULT_SLOT = 2;
    public static final int ENERGY_SOURCE_SLOT = 3;
    public static final int RADIANT_DUST_SLOT = 4;

    private long lastRemovedEnergyMilestone = 0;

    public int dustParticleCount = 0;
    public int maxDust = 10000;
    private int dustTick;

    protected final PropertyDelegate propertyDelegate;
    private int maxProgressTicks = 300;
    private final ServerRecipeManager.MatchGetter<GemInfusionRecipeInput, GemInfusionRecipe> matchGetter = ServerRecipeManager.createCachedMatchGetter(GemInfusionRecipe.Type.INSTANCE);

    public GemInfusionBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityType.GEM_infusion_BLOCK_ENTITY, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> GemInfusionBlockEntity.this.initialProgress;
                    case 1 -> GemInfusionBlockEntity.this.maxProgressTicks;
                    case 2 ->  GemInfusionBlockEntity.this.dustParticleCount;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> GemInfusionBlockEntity.this.initialProgress = value;
                    case 1 -> GemInfusionBlockEntity.this.maxProgressTicks = value;
                    case 2 -> GemInfusionBlockEntity.this.dustParticleCount = value;
                }
            }

            @Override
            public int size() {
                return 3;
            }
        };
    }

    public void setDustCount(int dustCount) {
        this.dustParticleCount = dustCount;
    }

    public long energyAmount() {
        return this.energyStorage.amount;
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
    public ServerRecipeManager.MatchGetter<GemInfusionRecipeInput, GemInfusionRecipe> getMatchGetter() {
        return matchGetter;
    }

    @Override
    protected void writeData(WriteView view) {
        super.writeData(view);
        view.putInt("DustCount", dustParticleCount);
        view.putInt("DustTick", dustTick);
    }

    @Override
    protected void readData(ReadView view) {
        super.readData(view);
        dustParticleCount = view.getInt("DustCount", 0);
        dustTick = view.getInt("DustTick", 0);
    }

    @Override
    public int getInitialProgress() {
        return 0;
    }

    @Override
    public int mainStackSize() {
        return 10;
    }

    @Override
    public long getEnergyCapacity() {
        return 1000000;
    }

    @Override
    public long getMaxEnergyInsert() {
        return 19200;
    }

    @Override
    public long getMaxEnergyExtract() {
        return 64000;
    }

    @Override
    public Text getDisplayName() {
        return ModBlocks.GEM_INFUSION_BLOCK.getName();
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new GemInfusionScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    public ItemStack radiantDustStack() {
        return getStack(RADIANT_DUST_SLOT);
    }

    public ItemStack ingredientAfterStack() {
        return getStack(INGREDIENT_AFTER_SLOT);
    }

    @Override
    public ItemStack resultStack() {
        return getStack(RESULT_SLOT);
    }

    @Override
    public ItemStack energyStack() {
        return getStack(ENERGY_SOURCE_SLOT);
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction side) {
        if (side == Direction.DOWN) {
            return false;
        }

        if (slot == INGREDIENT_BEFORE_SLOT || slot == INGREDIENT_AFTER_SLOT) {
            return ingredientStack().isIn(ModItemTags.GEMSTONE) || ingredientAfterStack().isOf(ModItems.RADIANT);
        }

        if (slot == ENERGY_SOURCE_SLOT) {
            return side == Direction.UP && (this.energyStack().isOf(ModItems.ENERGY_INGOT) || stack.isOf(ModBlocks.ENERGY_BLOCK.asItem()));  //
        } else if (slot == RADIANT_DUST_SLOT) {
            return side == Direction.UP && this.getStack(RADIANT_DUST_SLOT).isOf(ModItems.RADIANT_DUST);
        }

        return false;
    }


    @Override
    public GemInfusionDataSynchronizer getScreenOpeningData(ServerPlayerEntity serverPlayerEntity) {
        return new GemInfusionDataSynchronizer(energyAmount(), this.dustParticleCount, this.pos);
    }

    @Override
    public boolean isValid(int slot, ItemStack stack) {
        return switch (slot) {
            case INGREDIENT_BEFORE_SLOT, INGREDIENT_AFTER_SLOT ->
                    stack.isIn(ModItemTags.GEMSTONE) || stack.isIn(ModItemTags.RAW_GEMSTONE);
            case ENERGY_SOURCE_SLOT ->
                    stack.isOf(ModItems.ENERGY_INGOT) || stack.isOf(ModBlocks.ENERGY_BLOCK.asItem());
            case RESULT_SLOT->
                    stack.isIn(ModItemTags.GEMSTONE);
            case RADIANT_DUST_SLOT ->
                stack.isOf(ModItems.RADIANT_DUST);
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

    @Override
    public GemType detectGem(ItemStack stack) {
        return switch (stack.getItem()) {
            case Item i when i == ModItems.CRIMSON_GARNET || i == ModBlocks.CRIMSON_GARNET_BLOCK.asItem() -> GemType.CRIMSON_GARNET;
            case Item i when i == ModItems.RADIANT_AMETHYST || i == ModBlocks.RADIANT_AMETHYST_BLOCK.asItem() -> GemType.RADIANT_AMETHYST;
            case Item i when i == ModItems.CRYSTALLITE || i == ModBlocks.CRYSTALLITE_BLOCK.asItem() -> GemType.CRYSTALLITE;
            case Item i when i == ModItems.LIMESTONE || i == ModBlocks.LIMESTONE_BLOCK.asItem() -> GemType.LIMESTONE;
            case Item i when i == ModItems.MOONSTONE || i == ModBlocks.MOONSTONE_BLOCK.asItem() -> GemType.MOONSTONE;
            case Item i when i == ModItems.QUARTSIDIAN || i == ModBlocks.QUARTSIDIAN_BLOCK.asItem() -> GemType.QUARTSIDIAN;
            case Item i when i == ModItems.ORANGE_ZIRCON || i == ModBlocks.ORANGE_ZIRCON_BLOCK.asItem() -> GemType.ORANGE_ZIRCON;
            case Item i when i == ModItems.OPAL || i == ModBlocks.OPAL_BLOCK.asItem() -> GemType.OPAL;
            case Item i when i == ModItems.GRANDIDIERITE || i == ModBlocks.GRANDIDIERITE_BLOCK.asItem() -> GemType.GRANDIDIERITE;
            case Item i when i == ModItems.RED_BERYL || i == ModBlocks.RED_BERYL_BLOCK.asItem() -> GemType.RED_BERYL;
            case Item i when i == ModItems.KASHMIR_SAPPHIRE || i == ModBlocks.KASHMIR_SAPPHIRE_BLOCK.asItem() -> GemType.KASHMIR_SAPPHIRE;
            default -> GemType.EMPTY;
        };
    }

    // Tick Method
    // Logic per tick
    @Override
    public void tick(World world, BlockPos pos, BlockState state) {
        if (world.isClient()) {
            return;
        }

        dustTick++;

        GemType newGem = getGem();

        if (newGem != this.gemType) {
            setGem(newGem);

            world.updateListeners(pos, getCachedState(), getCachedState(), Block.NOTIFY_ALL);
            markDirty(world, pos, state);
        }

        ItemStack stack = radiantDustStack();
        if(stack.isOf(ModItems.RADIANT_DUST) && dustParticleCount <= maxDust) {
            dustParticleCount += 2000;
            stack.decrement(1);
            markDirty(world, pos, state);
        }
        markDirty(world, pos, state);

        changeState();
        markDirty(world, pos, state);

        if(polishingInfusionState == PolishingInfusionState.RUNNING) {
            energyState = EnergyState.EXTRACTING;
            markDirty(world, pos, state);
            if (isResultSlotEmptyOrReceivable() && hasRecipe() && hasEnoughEnergy()) {
                this.increaseProgress();
                this.extractEnergy();
                if(dustTick >= 20 && dustParticleCount > 0) {
                    this.dustParticleCount--;
                    this.dustTick = 0;
                    markDirty(world, pos, state);
                }
                markDirty(world, pos, state);
                if (hasInfusionFinished()) {
                    this.getInfusedGem();
                    this.resetProgress();
                    markDirty(world, pos, state);
                }
                markDirty(world, pos, state);
            } else {
                this.resetProgress();
                this.polishingInfusionState = PolishingInfusionState.IDLE;
                markDirty(world, pos, state);
            }
        } else if (polishingInfusionState.isPaused()) {
            energyState = EnergyState.INSERTING;
            insertEnergy();
            markDirty(world, pos, state);
        } else {
            if((energyAmount() < 1_000_000 && hasEnergySourceProviderItem())) {
                energyState = EnergyState.INSERTING;
                insertEnergy();
                markDirty(world, pos, state);
            } else {
                energyState = EnergyState.IDLE;
                markDirty(world, pos, state);
            }
        }

        checkForEnoughEnergyAndRemoveItem();
        markDirty(world, pos, state);
    }

    private void changeState() {
        BlockState state = getCachedState();

        state = state.with(GemInfusionBlock.IS_POLISHING, getGem());


        if(state != getCachedState()) {
            world.setBlockState(pos, state, Block.NOTIFY_ALL);
        }
    }

    @Override
    public GemType getGem() {
        return detectGem(resultStack());
    }

    protected void checkForEnoughEnergyAndRemoveItem() {
        long energy = this.energyStorage.amount;

        long [] milestones = {100000, 200000, 300000, 400000, 500000, 600000, 700000, 800000, 800000, 1000000};

        for(long milestone : milestones) {
            if(energy >= milestone && lastRemovedEnergyMilestone < milestone) {
                this.removeStack(ENERGY_SOURCE_SLOT, 1);
                lastRemovedEnergyMilestone = milestone;
                break;
            }
        }
    }

    private void resetProgress() {
        this.initialProgress = 0;
    }

    private void getInfusedGem() {
        RecipeEntry<GemInfusionRecipe> recipe = currentRecipe().orElseThrow();

        this.removeStack(INGREDIENT_BEFORE_SLOT, 1);
        this.removeStack(INGREDIENT_AFTER_SLOT, 1);

        this.setStack(RESULT_SLOT, new ItemStack(recipe.value().getResult().getItem(),
                this.resultStack().getCount() + recipe.value().getResult().getCount()));
    }
    private boolean hasInfusionFinished() {
        return initialProgress >= maxProgressTicks;
    }

    protected boolean hasRecipe() {
        Optional<RecipeEntry<GemInfusionRecipe>> recipe = currentRecipe();

        return recipe.isPresent() && hasEnoughEnergy() && canInsertCountIntoResultSlot(recipe.get().value().getResult())
                && canInsertItemIntoResultSlot(recipe.get().value().getResult().getItem());
    }

    private Optional<RecipeEntry<GemInfusionRecipe>> currentRecipe() {
        ServerWorld serverWorld = (ServerWorld) world;
        return this.matchGetter.getFirstMatch(new GemInfusionRecipeInput(this.ingredientStack(), this.ingredientAfterStack()), serverWorld);
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
}