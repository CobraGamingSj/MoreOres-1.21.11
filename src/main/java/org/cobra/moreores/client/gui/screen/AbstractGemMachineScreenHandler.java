package org.cobra.moreores.client.gui.screen;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.math.BlockPos;
import org.jspecify.annotations.Nullable;

public abstract class AbstractGemMachineScreenHandler extends ScreenHandler implements ScreenUtilHelper {
    protected final BlockPos pos;

    public AbstractGemMachineScreenHandler(@Nullable ScreenHandlerType<?> type, int syncId, BlockPos pos) {
        super(type, syncId);
        this.pos = pos;
    }

    public BlockPos getPos() {
        return pos;
    };

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
}
