package net.cobra.moreores.block.entity.gem;

import net.cobra.moreores.block.GemFusionBlock;
import net.cobra.moreores.block.ModBlocks;
import net.cobra.moreores.networking.block.data.GemPFEnergyData;
import net.cobra.moreores.block.entity.ModBlockEntityType;
import net.cobra.moreores.client.gui.screen.GemFusionScreenHandlerTest;
import net.cobra.moreores.item.ModItems;
import net.cobra.moreores.item.util.GemType;
import net.cobra.moreores.recipe.GemPurifierRecipe;
import net.cobra.moreores.recipe.input.GemPurifyingRecipeInput;
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
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class GemFusionBlockEntity extends AbstractGemPFBlockEntity<GemPFEnergyData> {

    public static final int INGREDIENT_SLOT = 0;
    public static final int SECOND_INGREDIENT_SLOT = 3;
    public static final int RESULT_SLOT = 1;
    public static final int ENERGY_SOURCE_SLOT = 2;

    private long lastRemovedEnergyMilestone = 0;

    protected final PropertyDelegate propertyDelegate;
    private int maxProgressTicks = 200;
    private final ServerRecipeManager.MatchGetter<GemPurifyingRecipeInput, GemPurifierRecipe> matchGetter = ServerRecipeManager.createCachedMatchGetter(GemPurifierRecipe.Type.INSTANCE);

    public GemFusionBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityType.GEM_FUSION_BLOCK_ENTITY, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> GemFusionBlockEntity.this.initialProgress;
                    case 1 -> GemFusionBlockEntity.this.maxProgressTicks;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> GemFusionBlockEntity.this.initialProgress = value;
                    case 1 -> GemFusionBlockEntity.this.maxProgressTicks = value;
                }
            }

            @Override
            public int size() {
                return 2;
            }
        };
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
    public ServerRecipeManager.MatchGetter<GemPurifyingRecipeInput, GemPurifierRecipe> getMatchGetter() {
        return matchGetter;
    }

    @Override
    public int getInitialProgress() {
        return 0;
    }

    @Override
    public int mainStackSize() {
        return 15;
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
        return ModBlocks.GEM_FUSION_BLOCK.getName();
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new GemFusionScreenHandlerTest(syncId, playerInventory, this, this.propertyDelegate);
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction side) {
        if (side == Direction.DOWN) {
            return false;
        }

        if (slot == INGREDIENT_SLOT || slot == SECOND_INGREDIENT_SLOT) {
            return ingredientStack().isIn(ModItemTags.RAW_GEMSTONE);
        }

        if (slot == ENERGY_SOURCE_SLOT) {
            return side == Direction.UP && (this.energyStack().isOf(ModItems.ENERGY_INGOT) || energyStack().isOf(ModBlocks.ENERGY_BLOCK.asItem()));  //
        }

        return false;
    }


    @Override
    public GemPFEnergyData getScreenOpeningData(ServerPlayerEntity serverPlayerEntity) {
        return new GemPFEnergyData(energyAmount(), this.pos);
    }

    @Override
    public boolean isValid(int slot, ItemStack stack) {
        return switch (slot) {
            case INGREDIENT_SLOT, SECOND_INGREDIENT_SLOT ->
                    stack.isIn(ModItemTags.GEMSTONE) || stack.isIn(ModItemTags.RAW_GEMSTONE);
            case ENERGY_SOURCE_SLOT ->
                    stack.isOf(ModItems.ENERGY_INGOT) || stack.isOf(ModBlocks.ENERGY_BLOCK.asItem());
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

        GemType newGem = getGem();

        if (newGem != this.gemType) {
            setGem(newGem);

            world.updateListeners(pos, getCachedState(), getCachedState(), 3);
        }

        changeState();

        if(polishingFusionState == PolishingFusionState.RUNNING) {
            energyState = EnergyState.EXTRACTING;
            if (isResultSlotEmptyOrReceivable() && hasRecipe() && hasEnoughEnergy()) {
                this.increaseProgress();
                this.extractEnergy();
                if (hasFusionFinished()) {
                    this.getFusionedGemstone ();
                    this.resetProgress();
                }
                markDirty(world, pos, state);
            } else {
                this.resetProgress();
                this.polishingFusionState = PolishingFusionState.IDLE;
                markDirty(world, pos, state);
            }
        } else if (polishingFusionState.isPaused()) {
            energyState = EnergyState.INSERTING;
            insertEnergy();
        } else {
            if((energyAmount() < 1_000_000 && hasEnergySourceProviderItem())) {
                energyState = EnergyState.INSERTING;
                insertEnergy();
            } else {
                energyState = EnergyState.IDLE;
            }
        }

        checkForEnoughEnergyAndRemoveItem();
        markDirty(world, pos, state);
    }

    private void changeState() {
        BlockState state = getCachedState();

        state = state.with(GemFusionBlock.IS_POLISHING, getGem());


        if(state != getCachedState()) {
            world.setBlockState(pos, state, Block.NOTIFY_ALL);
        }
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

    private void getFusionedGemstone() {
        RecipeEntry<GemPurifierRecipe> recipe = currentRecipe().orElseThrow();

        this.removeStack(INGREDIENT_SLOT, 1);

        this.setStack(RESULT_SLOT, new ItemStack(recipe.value().getResult().getItem(),
                this.resultStack().getCount() + recipe.value().getResult().getCount()));
    }
    private boolean hasFusionFinished() {
        return initialProgress >= maxProgressTicks;
    }



    protected boolean hasRecipe() {
        Optional<RecipeEntry<GemPurifierRecipe>> recipe = currentRecipe();

        return recipe.isPresent() && hasEnoughEnergy() && canInsertCountIntoResultSlot(recipe.get().value().getResult())
                && canInsertItemIntoResultSlot(recipe.get().value().getResult().getItem());
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
}