package net.cobra.moreores.block.entity.gem;

import net.cobra.moreores.block.ModBlocks;
import net.cobra.moreores.networking.block.data.GemPFEnergyData;
import net.cobra.moreores.block.entity.ImplementedInventory;
import net.cobra.moreores.block.entity.TickableBlockEntity;
import net.cobra.moreores.item.ModItems;
import net.cobra.moreores.item.util.GemType;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.inventory.Inventories;
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
    protected PolishingFusionState polishingFusionState = PolishingFusionState.IDLE;
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
                ServerPlayNetworking.send(user, new GemPFEnergyData(this.amount, getPos()));
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
        view.putLong("gem_purifier.energy", energyStorage.amount);
        view.putNullable("PolishingState", PolishingFusionState.CODEC, polishingFusionState);
        view.putNullable("EnergyState", EnergyState.CODEC, energyState);
        view.putNullable("GemType", GemType.CODEC, gemType);
    }

    @Override
    protected void readData(ReadView view) {
        super.readData(view);
        Inventories.readData(view, main);
        initialProgress = view.getInt("Progress", 0);
        energyStorage.amount = view.getLong("gem_purifier.energy", 0);
        polishingFusionState = view.read("PolishingState", PolishingFusionState.CODEC).orElse(PolishingFusionState.IDLE);
        energyState = view.read("EnergyState", EnergyState.CODEC).orElse(EnergyState.IDLE);
        gemType = view.read("GemType", GemType.CODEC).orElse(GemType.EMPTY);
    }

    public GemType detectGem(ItemStack stack) {
        if (stack.isOf(ModItems.RUBY) || stack.isOf(ModBlocks.RUBY_BLOCK.asItem())) return GemType.RUBY;
        if (stack.isOf(ModItems.SAPPHIRE) || stack.isOf(ModBlocks.SAPPHIRE_BLOCK.asItem())) return GemType.SAPPHIRE;
        if (stack.isOf(ModItems.GREEN_SAPPHIRE) || stack.isOf(ModBlocks.GREEN_SAPPHIRE_ORE.asItem())) return GemType.GREEN_SAPPHIRE;
        if (stack.isOf(ModItems.BLUE_GARNET) || stack.isOf(ModBlocks.BLUE_GARNET_BLOCK.asItem())) return GemType.BLUE_GARNET;
        if (stack.isOf(ModItems.PINK_GARNET) || stack.isOf(ModBlocks.PINK_GARNET_BLOCK.asItem())) return GemType.PINK_GARNET;
        if (stack.isOf(ModItems.GREEN_GARNET) || stack.isOf(ModBlocks.GREEN_GARNET_BLOCK.asItem())) return GemType.GREEN_GARNET;
        if (stack.isOf(ModItems.KYAWTHUITE) || stack.isOf(ModBlocks.KYAWTHUITE_BLOCK.asItem())) return GemType.KYAWTHUITE;
        if (stack.isOf(ModItems.TOPAZ) || stack.isOf(ModBlocks.TOPAZ_BLOCK.asItem())) return GemType.TOPAZ;
        if (stack.isOf(ModItems.WHITE_TOPAZ) || stack.isOf(ModBlocks.WHITE_TOPAZ_BLOCK.asItem())) return GemType.WHITE_TOPAZ;
        if (stack.isOf(ModItems.PERIDOT) || stack.isOf(ModBlocks.PERIDOT_BLOCK.asItem())) return GemType.PERIDOT;
        if (stack.isOf(ModItems.JADE) || stack.isOf(ModBlocks.JADE_BLOCK.asItem())) return GemType.JADE;
        if (stack.isOf(ModItems.PYROPE) || stack.isOf(ModBlocks.PYROPE_BLOCK.asItem())) return GemType.PYROPE;
        return GemType.EMPTY;
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
        if(polishingFusionState.isIdle() && hasRecipe() && hasEnoughEnergy()) {
            polishingFusionState = PolishingFusionState.RUNNING;
        }
    }

    public void pause() {
        if(polishingFusionState.isRunning()) {
            polishingFusionState = PolishingFusionState.PAUSED;
        }
    }

    public void resume() {
        if(polishingFusionState.isPaused()&& hasRecipe() && hasEnoughEnergy()) {
            polishingFusionState = PolishingFusionState.RUNNING;
        }
    }

    public void stop() {
        if(!polishingFusionState.isIdle()) {
            polishingFusionState = PolishingFusionState.IDLE;
            resetProgress();
        }
    }
}
