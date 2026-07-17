package org.cobra.moreores.client.gui.screen;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import org.cobra.moreores.block.entity.gem.machine.AbstractGemMachineBlockEntity;
import org.jetbrains.annotations.NotNull;
import team.reborn.energy.api.base.SimpleEnergyStorage;

public abstract class AbstractGemMachineScreenHandler<T extends AbstractGemMachineBlockEntity<?>> extends ScreenHandler implements ScreenUtilHelper<T> {
    protected final BlockPos blockPos;
    final T blockEntity;

    public AbstractGemMachineScreenHandler(@NotNull ScreenHandlerType<?> type, int syncId, BlockPos blockPos, T blockEntity) {
        super(type, syncId);
        this.blockPos = blockPos;
        this.blockEntity = blockEntity;
    }

    public BlockPos getBlockPos() {
        return blockPos;
    }

    @Override
    public void addPlayerGenericInventory(PlayerInventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 115 + i * 18));
            }
        }
    }

    @Override
    public void addPlayerHotbarInventory(PlayerInventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 173));
        }
    }

    public void addFirstAdditionalInventory(Inventory playerInventory) {
        for (int i = 0; i < 8; ++i) {
            this.addSlot(new Slot(playerInventory, 5 + i, 26 + i * 18, 95));
        }
    }

    public void addSecondAdditionalInventory(Inventory playerInventory) {
        for (int i = 0; i < 4; ++i) {
            this.addSlot(new Slot(playerInventory, 13 + i, 179, 115 + i * 18));
        }
    }

    public long getEnergyAmount() {
        return this.blockEntity.energyAmount();
    }

    public long getEnergyCapacity() {
        return this.blockEntity.energyStorage().getCapacity();
    }

    public float calculateEnergyAmountPercentage() {
        SimpleEnergyStorage energyStorage = this.blockEntity.energyStorage();
        long energy = energyStorage.getAmount();
        long maxEnergy = energyStorage.getCapacity();
        if (maxEnergy == 0 || energy == 0) return 0.0F;

        return MathHelper.clamp((float) energy / (float) maxEnergy, 0.0F, 1.0F);
    }

    @Override
    public T getBlockEntity() {
        return this.blockEntity;
    }
}
