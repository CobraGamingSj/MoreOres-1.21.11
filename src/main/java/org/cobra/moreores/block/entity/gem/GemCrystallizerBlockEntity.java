package org.cobra.moreores.block.entity.gem;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
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
import org.cobra.moreores.block.GemCrystallizerBlock;
import org.cobra.moreores.block.ModBlocks;
import org.cobra.moreores.block.entity.ModBlockEntityType;
import org.cobra.moreores.client.gui.screen.GemCrystallizerScreenHandler;
import org.cobra.moreores.item.ModItems;
import org.cobra.moreores.item.util.GemCategory;
import org.cobra.moreores.item.util.impl.CrystallizationGemstones;
import org.cobra.moreores.item.util.impl.Gemstone;
import org.cobra.moreores.networking.block.data.GemCrystallizerDataSynchronizer;
import org.cobra.moreores.recipe.GemCrystallizerRecipe;
import org.cobra.moreores.recipe.input.GemInfusionRecipeInput;
import org.cobra.moreores.registry.ModItemTags;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class GemCrystallizerBlockEntity extends AbstractGemMachineBlockEntity<GemCrystallizerDataSynchronizer> {

    public static final int INGREDIENT_BEFORE_SLOT = 0;
    public static final int INGREDIENT_AFTER_SLOT = 1;
    public static final int RESULT_SLOT = 2;
    public static final int ENERGY_SOURCE_SLOT = 3;
    public static final int RADIANT_DUST_SLOT = 4;
    public static final int REDSTONE_SLOT = 5;

    private long lastRemovedRadiantDustMilestone = 0;
    
    public int dustParticleCount = 0;
    public int maxDust = 10000;
    private int dustTick;

    protected final PropertyDelegate propertyDelegate;
    private int maxProgressTicks = 300;
    private final ServerRecipeManager.MatchGetter<GemInfusionRecipeInput, GemCrystallizerRecipe> matchGetter = ServerRecipeManager.createCachedMatchGetter(GemCrystallizerRecipe.Type.INSTANCE);

    public GemCrystallizerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityType.GEM_CRYSTALLIZER, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> GemCrystallizerBlockEntity.this.initialProgress;
                    case 1 -> GemCrystallizerBlockEntity.this.maxProgressTicks;
                    case 2 ->  GemCrystallizerBlockEntity.this.dustParticleCount;
                    case 3 ->  GemCrystallizerBlockEntity.this.redstone;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> GemCrystallizerBlockEntity.this.initialProgress = value;
                    case 1 -> GemCrystallizerBlockEntity.this.maxProgressTicks = value;
                    case 2 -> GemCrystallizerBlockEntity.this.dustParticleCount = value;
                    case 3 -> GemCrystallizerBlockEntity.this.redstone = value;
                }
            }

            @Override
            public int size() {
                return 4;
            }
        };
    }
    
    public void setDustCount(int dustCount) {
        this.dustParticleCount = dustCount;
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
    public void writeData(WriteView view) {
        super.writeData(view);
        view.putInt("DustCount", this.dustParticleCount);
        view.putInt("DustTick", this.dustTick);
        view.putNullable("GemType", CrystallizationGemstones.CODEC, getGem());
    }

    @Override
    public void readData(ReadView view) {
        super.readData(view);
        this.dustParticleCount = view.getInt("DustCount", 0);
        this.dustTick = view.getInt("DustTick", 0);
        this.gemstone = view.read("GemType", CrystallizationGemstones.CODEC).orElse(CrystallizationGemstones.NONE);
    }

    @Override
    public int mainStackSize() {
        return 11;
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
        return ModBlocks.GEM_CRYSTALLIZER_BLOCK.getName();
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new GemCrystallizerScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    public ItemStack radiantDustStack() {
        return getStack(RADIANT_DUST_SLOT);
    }

    public ItemStack redstoneStack() {
        return getStack(REDSTONE_SLOT);
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
    public GemCrystallizerDataSynchronizer getScreenOpeningData(ServerPlayerEntity serverPlayerEntity) {
        return new GemCrystallizerDataSynchronizer(energyAmount(), this.getRedstone(), this.dustParticleCount, this.pos);
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
    public GemCategory category() {
        return GemCategory.CRYSTALLIZATION;
    }


    // Tick Method
    // Logic per tick
    @Override
    public void tick(World world, BlockPos pos, BlockState state) {
        if (world.isClient()) {
            return;
        }

        dustTick++;
        redstoneTick++;

        Gemstone newGem = getGem();

        if (newGem != this.gemstone) {
            setGem(newGem);

            world.updateListeners(pos, getCachedState(), getCachedState(), Block.NOTIFY_ALL);
            markDirty(world, pos, state);
        }

        ItemStack stack = radiantDustStack();
        if(stack.isOf(ModItems.RADIANT_DUST) && dustParticleCount <= maxDust) {
            dustParticleCount += 2000;
            markDirty(world, pos, state);
        }
        ItemStack stack1 = redstoneStack();
        if((stack1.isOf(Items.REDSTONE) || world.isReceivingRedstonePower(pos)) && redstone <= maxRedstone) {
            redstone += 10;
            markDirty(world, pos, state);
        }

        changeState();
        if(machineStatus == MachineStatus.RUNNING) {
            energyState = MachineStatus.EnergyState.EXTRACTING;
            markDirty(world, pos, state);
            if (isResultSlotEmptyOrReceivable() && hasRecipe() && hasEnoughEnergy() && dustParticleCount >= 15) {
                this.increaseProgress();
                if((!world.isReceivingRedstonePower(pos) || redstone > 0) && redstoneTick >= 20) {
                    redstone--;
                    redstoneTick = 0;
                }
                this.eatEnergy();
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
                this.machineStatus = MachineStatus.IDLE;
                markDirty(world, pos, state);
            }
        } else if (machineStatus.isPaused()) {
            energyState = MachineStatus.EnergyState.INSERTING;
            giveEnergy();
            markDirty(world, pos, state);
        } else {
            if((energyAmount() < 1_000_000 && hasEnergySourceProviderItem())) {
                energyState = MachineStatus.EnergyState.INSERTING;
                giveEnergy();
                markDirty(world, pos, state);
            } else {
                energyState = MachineStatus.EnergyState.IDLE;
                markDirty(world, pos, state);
            }
        }

        validateEnergyAmount(ENERGY_SOURCE_SLOT);
        validateRedstoneDust(REDSTONE_SLOT);
        validateRadiantDust();
        markDirty(world, pos, state);
    }

    @Override
    public CrystallizationGemstones getGem() {
        Gemstone gem = super.getGem();
        if(gem instanceof CrystallizationGemstones c) {
            return c;
        }
        return CrystallizationGemstones.NONE;
    }
    
    private void changeState() {
        BlockState state = getCachedState();

        state = state.with(GemCrystallizerBlock.IS_CRYSTALLIZING, getGem());


        if(state != getCachedState()) {
            world.setBlockState(pos, state, Block.NOTIFY_ALL);
        }
    }

    @Override
    protected void validateEnergyAmount(int energySlot) {
        if(energyAmount() > 1000000) {
            energyStorage.amount = 1000000;
        }

        long energy = this.energyStorage.amount;

        long[] milestones = {100000, 200000, 300000, 400000, 500000, 600000, 700000, 800000, 900000, 1000000};

        for(long milestone : milestones) {
            if(energy >= milestone && lastRemovedEnergyMilestone < milestone) {
                this.removeStack(energySlot, 1);
                lastRemovedEnergyMilestone = milestone;
                break;
            }
        }
    }

    private void validateRadiantDust() {
        if(dustParticleCount > 10000) {
            dustParticleCount = 10000;
        }

        long energy = dustParticleCount;

        long [] milestones = {2000, 4000, 6000, 8000, 10000};

        for(long milestone : milestones) {
            if(energy >= milestone && lastRemovedRadiantDustMilestone < milestone) {
                this.removeStack(RADIANT_DUST_SLOT, 1);
                lastRemovedRadiantDustMilestone = milestone;
                break;
            }
        }
    }
    
    private void resetProgress() {
        this.initialProgress = 0;
    }

    private void getInfusedGem() {
        RecipeEntry<GemCrystallizerRecipe> recipe = currentRecipe().orElseThrow();

        this.removeStack(INGREDIENT_BEFORE_SLOT, 1);
        this.removeStack(INGREDIENT_AFTER_SLOT, 1);

        this.setStack(RESULT_SLOT, new ItemStack(recipe.value().getResult().getItem(),
                this.resultStack().getCount() + recipe.value().getResult().getCount()));
    }
    private boolean hasInfusionFinished() {
        return initialProgress >= maxProgressTicks;
    }

    protected boolean hasRecipe() {
        Optional<RecipeEntry<GemCrystallizerRecipe>> recipe = currentRecipe();

        return recipe.isPresent() && hasEnoughEnergy() && canInsertCountIntoResultSlot(recipe.get().value().getResult())
                && canInsertItemIntoResultSlot(recipe.get().value().getResult().getItem());
    }

    private Optional<RecipeEntry<GemCrystallizerRecipe>> currentRecipe() {
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