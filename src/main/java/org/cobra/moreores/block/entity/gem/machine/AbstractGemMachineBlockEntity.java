package org.cobra.moreores.block.entity.gem.machine;

import com.mojang.serialization.Codec;
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
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import org.cobra.moreores.block.ModBlocks;
import org.cobra.moreores.block.entity.ImplementedInventory;
import org.cobra.moreores.block.entity.TickableBlockEntity;
import org.cobra.moreores.item.ModItems;
import org.cobra.moreores.item.util.GemCategory;
import org.cobra.moreores.item.util.impl.CrystallizationGemstones;
import org.cobra.moreores.item.util.impl.Gemstone;
import org.cobra.moreores.item.util.impl.PurificationGemstones;
import org.cobra.moreores.networking.block.data.GemMachineEnergyDataPayload;
import org.cobra.moreores.registry.ModItemTags;
import team.reborn.energy.api.base.SimpleEnergyStorage;

public abstract class AbstractGemMachineBlockEntity<Payload extends CustomPayload> extends BlockEntity implements ExtendedScreenHandlerFactory<Payload>, ImplementedInventory, TickableBlockEntity {
    protected final DefaultedList<ItemStack> main;
    protected MachineStatus machineStatus = MachineStatus.IDLE;
    protected MachineStatus.EnergyState energyState = MachineStatus.EnergyState.IDLE;
    protected Gemstone gemstone = Gemstone.NONE;

    long energyExtracted = 0;
    
    public int initialProgress = 0;

    protected int redstone = 0;
    protected int maxRedstone = 10000;
    protected int redstoneTick;

    protected long lastRemovedEnergyMilestone = 0;
    protected long lastRemovedRedstoneMilestone = 0;
    
    public AbstractGemMachineBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.main = DefaultedList.ofSize(mainStackSize(), ItemStack.EMPTY);
    }

    protected final SimpleEnergyStorage energyStorage = new SimpleEnergyStorage(getEnergyCapacity(), getMaxEnergyInsert(), getMaxEnergyExtract()) {
        @Override
        public void onFinalCommit() {
            super.onFinalCommit();

            markDirty();

            for (ServerPlayerEntity user : PlayerLookup.tracking((ServerWorld) world, getPos())) {
                ServerPlayNetworking.send(user, new GemMachineEnergyDataPayload(this.amount, getPos()));
            }
        }
    };

    public SimpleEnergyStorage energyStorage() {
        return this.energyStorage;
    }
    
    public abstract int mainStackSize();

    public abstract long getEnergyCapacity();

    public abstract long getMaxEnergyInsert();

    public abstract long getMaxEnergyExtract();

    @Override
    public void writeData(WriteView view) {
        super.writeData(view);
        Inventories.writeData(view, this.main);
        view.putInt("Progress", this.initialProgress);
        view.putInt("Redstone", this.redstone);
        view.putInt("RedstoneTick", this.redstoneTick);
        view.putLong("Energy", this.energyStorage.amount);
        view.putNullable("PolishingState", MachineStatus.CODEC, this.machineStatus);
        view.putNullable("EnergyState", MachineStatus.EnergyState.CODEC, this.energyState);
        view.putLong("EnergyExtracted", this.energyExtracted);
    }

    @Override
    public void readData(ReadView view) {
        super.readData(view);
        Inventories.readData(view, this.main);
        this.initialProgress = view.getInt("Progress", 0);
        this.redstone = view.getInt("Redstone", 0);
        this.redstoneTick = view.getInt("RedstoneTick", 0);
        this.energyStorage.amount = view.getLong("Energy", 0);
        this.energyExtracted = view.getLong("EnergyExtracted", 0);
        this.machineStatus = view.read("PolishingState", MachineStatus.CODEC).orElse(MachineStatus.IDLE);
        this.energyState = view.read("EnergyState", MachineStatus.EnergyState.CODEC).orElse(MachineStatus.EnergyState.IDLE);
    }

    public int getRedstone() {
        return this.redstone;
    }

    public void setRedstone(int redstone) {
        this.redstone = redstone;
    }
    
    public Gemstone detectGem(ItemStack stack) {
        Item item = stack.getItem();
        if (category() == GemCategory.PURIFYING) {
            for (PurificationGemstones gems : PurificationGemstones.values()) {
                for (Item item1 : gems.items()) {
                    if (item1 == item) {
                        return gems;
                    }
                }
            }
            return PurificationGemstones.NONE;
        }
        if (category() == GemCategory.CRYSTALLIZATION) {
            for (CrystallizationGemstones gems : CrystallizationGemstones.values()) {
                for (Item item1 : gems.items()) {
                    if (item1 == item) {
                        return gems;
                    }
                }
            }
            return CrystallizationGemstones.NONE;
        }
        return Gemstone.NONE;
    }

    public long energyAmount() {
        return this.energyStorage.amount;
    }
    
    public abstract GemCategory category();

    public Gemstone getGem() {
        return detectGem(resultStack());
    }

    public void setGem(Gemstone gem) {
        gemstone = gem;
    }

    public void setEnergyAmount(long energy) {
        this.energyStorage.amount = Math.min(energy, getEnergyCapacity());
    }

    protected boolean hasEnergySourceProviderItem() {
        return this.energyStack().isIn(ModItemTags.HAS_ENERGY);
    }

    protected void increaseProgress() {
        if(this.world.isReceivingRedstonePower(this.pos) || redstone > 0) {
            initialProgress += 3;
        } else {
            initialProgress++;
        }
    }

    protected void validateEnergyAmount(int energySlot) {
        if(energyAmount() > 10000000) {
            energyStorage.amount = 10000000;
        }

        long energy = this.energyStorage.amount;

        long[] milestones = {1000000, 2000000, 3000000, 4000000, 5000000, 6000000, 7000000, 8000000, 9000000, 10000000};

        for(long milestone : milestones) {
            if(energy >= milestone && lastRemovedEnergyMilestone < milestone) {
                this.removeStack(energySlot, 1);
                lastRemovedEnergyMilestone = milestone;
                break;
            }
        }
    }

    protected void validateRedstoneDust(int slot) {
        if(redstone > 10000) {
            redstone = 10000;
        }

        int amount = redstone;

        int [] milestones = {1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000};

        for(long milestone : milestones) {
            if(amount >= milestone && lastRemovedRedstoneMilestone < milestone) {
                this.removeStack(slot, 1);
                lastRemovedRedstoneMilestone = milestone;
                break;
            }
        }
    }
    
    protected boolean hasRequiredEnergyAmount() {
        return this.energyStorage.amount >= 13;
    }

    protected void giveEnergy() {
        if(world == null) {
            return;
        }
        if (!hasEnergySourceProviderItem() || energyStorage.amount >= 1_000_000) {
            energyState = MachineStatus.EnergyState.IDLE;
            return;
        }
        long amount = energyStack().isOf(ModItems.ENERGY_INGOT) ? 102 : 154;
        if (world.isReceivingRedstonePower(pos)) amount *= (int) 2.5;
        try (Transaction transaction = Transaction.openOuter()) {
            long inserted = energyStorage.insert(amount, transaction);
            transaction.commit();
            if (inserted > 0) energyState = MachineStatus.EnergyState.INSERTING;
            else energyState = MachineStatus.EnergyState.IDLE;
        }
    }

    protected void eatEnergy() {
        if(world == null) {
            return;
        }
        long amount = world.isReceivingRedstonePower(pos) ? 64 : 13;
        try (Transaction transaction = Transaction.openOuter()) {
            long extracted = energyStorage.extract(amount, transaction);
            energyExtracted += extracted;
            transaction.commit();
        }
        energyState = MachineStatus.EnergyState.EXTRACTING;
    }

    protected abstract boolean hasRecipe();

    private void resetProgress() {
        this.initialProgress = 0;
    }

    public void start() {
        if (machineStatus.isIdle() && hasRecipe() && hasRequiredEnergyAmount()) {
            machineStatus = MachineStatus.RUNNING;
        }
    }

    public void pause() {
        if (machineStatus.isRunning()) {
            machineStatus = MachineStatus.PAUSED;
        }
    }

    public void resume() {
        if (machineStatus.isPaused() && hasRecipe() && hasRequiredEnergyAmount()) {
            machineStatus = MachineStatus.RUNNING;
        }
    }

    public void stop() {
        if (!machineStatus.isIdle()) {
            machineStatus = MachineStatus.IDLE;
            resetProgress();
            try(Transaction transaction = Transaction.openOuter()) {
                this.energyStorage().insert(energyExtracted, transaction);
                transaction.commit();
            }
            this.energyExtracted = 0;
        }
    }

    public enum MachineStatus implements StringIdentifiable {
        IDLE("idle"),
        RUNNING("running"),
        PAUSED("paused");
    
        private final String name;
    
        MachineStatus(String name) {
            this.name = name;
        }
    
        public static final Codec<MachineStatus> CODEC = StringIdentifiable.createCodec(MachineStatus::values);
    
        public boolean isIdle() {
            return this == IDLE;
        }
    
        public boolean isRunning() {
            return this == RUNNING;
        }
    
        public boolean isPaused() {
            return this == PAUSED;
        }
    
        @Override
        public String asString() {
            return this.name;
        }
    
        public enum EnergyState implements StringIdentifiable {
            IDLE("idle"),
            INSERTING("inserting"),
            EXTRACTING("extracting");
        
            private final String name;
        
            EnergyState(String name) {
                this.name = name;
            }
        
            public static final Codec<EnergyState> CODEC = StringIdentifiable.createCodec(EnergyState::values);
        
            @Override
            public String asString() {
                return this.name;
            }
        }
    }
}
