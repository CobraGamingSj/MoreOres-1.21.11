package net.cobra.moreores.block.entity.gem;

import net.cobra.moreores.block.ModBlocks;
import net.cobra.moreores.block.entity.ImplementedInventory;
import net.cobra.moreores.block.entity.TickableBlockEntity;
import net.cobra.moreores.item.ModItems;
import net.cobra.moreores.item.util.GemType;
import net.cobra.moreores.networking.block.data.GemPFEnergyDataPayload;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.recipe.ServerRecipeManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import team.reborn.energy.api.base.SimpleEnergyStorage;

public abstract class AbstractGemPFBlockEntity<P extends CustomPayload> extends BlockEntity implements ExtendedScreenHandlerFactory<P>, ImplementedInventory, TickableBlockEntity {
    protected final DefaultedList<ItemStack> main;
    protected PolishingInfusionState polishingInfusionState = PolishingInfusionState.IDLE;
    protected EnergyState energyState = EnergyState.IDLE;
    protected GemType gemType = GemType.EMPTY;

    public int initialProgress = 0;

    public AbstractGemPFBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.main = DefaultedList.ofSize(mainStackSize(), ItemStack.EMPTY);
    }

    public final SimpleEnergyStorage energyStorage = new SimpleEnergyStorage(getEnergyCapacity(), getMaxEnergyInsert(), getMaxEnergyExtract()) {
        @Override
        public void onFinalCommit() {
            super.onFinalCommit();

            markDirty();

            for(ServerPlayerEntity user : PlayerLookup.tracking((ServerWorld) world, getPos())) {
                ServerPlayNetworking.send(user, new GemPFEnergyDataPayload(this.amount, getPos()));
            }
        }
    };

    public abstract int mainStackSize();
    public abstract long getEnergyCapacity();
    public abstract long getMaxEnergyInsert();
    public abstract long getMaxEnergyExtract();
    public abstract ServerRecipeManager.MatchGetter<?, ?> getMatchGetter();
    public abstract int getInitialProgress();

    @Override
    protected void writeData(WriteView view) {
        super.writeData(view);
        Inventories.writeData(view, main);
        view.putInt("Progress", initialProgress);
        view.putLong("Energy", energyStorage.amount);
        view.putNullable("PolishingState", PolishingInfusionState.CODEC, polishingInfusionState);
        view.putNullable("EnergyState", EnergyState.CODEC, energyState);
        view.putNullable("GemType", GemType.CODEC, gemType);
    }

    @Override
    protected void readData(ReadView view) {
        super.readData(view);
        Inventories.readData(view, main);
        initialProgress = view.getInt("Progress", 0);
        energyStorage.amount = view.getLong("Energy", 0);
        polishingInfusionState = view.read("PolishingState", PolishingInfusionState.CODEC).orElse(PolishingInfusionState.IDLE);
        energyState = view.read("EnergyState", EnergyState.CODEC).orElse(EnergyState.IDLE);
        gemType = view.read("GemType", GemType.CODEC).orElse(GemType.EMPTY);
    }

    public GemType detectGem(ItemStack stack) {
        return switch (stack.getItem()) {
            case Item i when i == ModItems.RUBY || i == ModBlocks.RUBY_BLOCK.asItem() -> GemType.RUBY;
            case Item i when i == ModItems.SAPPHIRE || i == ModBlocks.SAPPHIRE_BLOCK.asItem() -> GemType.SAPPHIRE;
            case Item i when i == ModItems.GREEN_SAPPHIRE || i == ModBlocks.GREEN_SAPPHIRE_BLOCK.asItem() -> GemType.GREEN_SAPPHIRE;
            case Item i when i == ModItems.BLUE_GARNET || i == ModBlocks.BLUE_GARNET_BLOCK.asItem() -> GemType.BLUE_GARNET;
            case Item i when i == ModItems.PINK_GARNET || i == ModBlocks.PINK_GARNET_BLOCK.asItem() -> GemType.PINK_GARNET;
            case Item i when i == ModItems.GREEN_GARNET || i == ModBlocks.GREEN_GARNET_BLOCK.asItem() -> GemType.GREEN_GARNET;
            case Item i when i == ModItems.KYAWTHUITE || i == ModBlocks.KYAWTHUITE_BLOCK.asItem() -> GemType.KYAWTHUITE;
            case Item i when i == ModItems.TOPAZ || i == ModBlocks.TOPAZ_BLOCK.asItem() -> GemType.TOPAZ;
            case Item i when i == ModItems.WHITE_TOPAZ || i == ModBlocks.WHITE_TOPAZ_BLOCK.asItem() -> GemType.WHITE_TOPAZ;
            case Item i when i == ModItems.PERIDOT || i == ModBlocks.PERIDOT_BLOCK.asItem() -> GemType.PERIDOT;
            case Item i when i == ModItems.JADE || i == ModBlocks.JADE_BLOCK.asItem() -> GemType.JADE;
            case Item i when i == ModItems.PYROPE || i == ModBlocks.PYROPE_BLOCK.asItem() -> GemType.PYROPE;
            default -> GemType.EMPTY;
         };
    }

    public GemType getGem() {
        return detectGem(resultStack());
    }

    public void setGem(GemType gem) {
        gemType = gem;
    }

    public void setEnergyLevel(long energy) {
        this.energyStorage.amount = Math.min(energy, getEnergyCapacity());
    }

    protected boolean hasEnergySourceProviderItem() {
        return this.energyStack().isOf(ModItems.ENERGY_INGOT) || this.energyStack().isOf(ModBlocks.ENERGY_BLOCK.asItem());
    }

    protected void increaseProgress() {
        if(this.world.isReceivingRedstonePower(this.pos)) {
            initialProgress += (int) 2.5;
        } else {
            initialProgress++;
        }
    }

    protected boolean hasEnoughEnergy() {
        return this.energyStorage.amount >= 13;
    }

    protected void insertEnergy() {
        if(!hasEnergySourceProviderItem() || energyStorage.amount >= 1_000_000) {
            energyState = EnergyState.IDLE;
            return;
        }
        long amount = energyStack().isOf(ModItems.ENERGY_INGOT) ? 102 : 154;
        if(world.isReceivingRedstonePower(pos)) amount *= (int) 2.5;
        try(Transaction transaction = Transaction.openOuter()) {
            long inserted = energyStorage.insert(amount, transaction);
            transaction.commit();
            if(inserted > 0) energyState = EnergyState.INSERTING;
            else energyState = EnergyState.IDLE;
        }
    }

    protected void extractEnergy() {
        long amount = world.isReceivingRedstonePower(pos) ? 64 : 13;
        try(Transaction transaction = Transaction.openOuter()) {
            energyStorage.extract(amount, transaction);
            transaction.commit();
        }
        energyState = EnergyState.EXTRACTING;
    }

    protected abstract boolean hasRecipe();

    private void resetProgress() {
        this.initialProgress = 0;
    }

    public void start() {
        if(polishingInfusionState.isIdle() && hasRecipe() && hasEnoughEnergy()) {
            polishingInfusionState = PolishingInfusionState.RUNNING;
        }
    }

    public void pause() {
        if(polishingInfusionState.isRunning()) {
            polishingInfusionState = PolishingInfusionState.PAUSED;
        }
    }

    public void resume() {
        if(polishingInfusionState.isPaused()&& hasRecipe() && hasEnoughEnergy()) {
            polishingInfusionState = PolishingInfusionState.RUNNING;
        }
    }

    public void stop() {
        if(!polishingInfusionState.isIdle()) {
            polishingInfusionState = PolishingInfusionState.IDLE;
            resetProgress();
        }
    }
}
