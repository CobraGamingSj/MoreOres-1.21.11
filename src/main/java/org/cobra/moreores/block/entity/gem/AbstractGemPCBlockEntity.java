package org.cobra.moreores.block.entity.gem;

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
import org.cobra.moreores.block.ModBlocks;
import org.cobra.moreores.block.entity.ImplementedInventory;
import org.cobra.moreores.block.entity.TickableBlockEntity;
import org.cobra.moreores.item.ModItems;
import org.cobra.moreores.item.util.GemCategory;
import org.cobra.moreores.item.util.impl.CrystallizationGems;
import org.cobra.moreores.item.util.impl.IGem;
import org.cobra.moreores.item.util.impl.PurifyingGems;
import org.cobra.moreores.networking.block.data.GemPFEnergyDataPayload;
import team.reborn.energy.api.base.SimpleEnergyStorage;

public abstract class AbstractGemPCBlockEntity<P extends CustomPayload> extends BlockEntity implements ExtendedScreenHandlerFactory<P>, ImplementedInventory, TickableBlockEntity {
    protected final DefaultedList<ItemStack> main;
    protected PolishingInfusionState polishingInfusionState = PolishingInfusionState.IDLE;
    protected EnergyState energyState = EnergyState.IDLE;
    protected IGem gemType = IGem.EMPTY;

    public int initialProgress = 0;

    public AbstractGemPCBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
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
    public void writeData(WriteView view) {
        super.writeData(view);
        Inventories.writeData(view, main);
        view.putInt("Progress", initialProgress);
        view.putLong("Energy", energyStorage.amount);
        view.putNullable("PolishingState", PolishingInfusionState.CODEC, polishingInfusionState);
        view.putNullable("EnergyState", EnergyState.CODEC, energyState);
    }

    @Override
    public void readData(ReadView view) {
        super.readData(view);
        Inventories.readData(view, main);
        initialProgress = view.getInt("Progress", 0);
        energyStorage.amount = view.getLong("Energy", 0);
        polishingInfusionState = view.read("PolishingState", PolishingInfusionState.CODEC).orElse(PolishingInfusionState.IDLE);
        energyState = view.read("EnergyState", EnergyState.CODEC).orElse(EnergyState.IDLE);
    }

    public IGem detectGem(ItemStack stack) {
        Item item = stack.getItem();
        for (PurifyingGems gems : PurifyingGems.values()) {
            for (Item item1 : gems.items()) {
                if(item1 == item) {
                    return gems;
                }
            }
        }
        for (CrystallizationGems gems : CrystallizationGems.values()) {
            for(Item item1 : gems.items()) {
                if(item1 == item) {
                    return gems;
                }
            }
        }
        return IGem.EMPTY;
    }

    public abstract GemCategory category();
    
    public IGem getGem() {
        return detectGem(resultStack());
    }

    public void setGem(IGem gem) {
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
