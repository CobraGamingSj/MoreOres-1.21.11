package org.cobra.moreores.client.gui.screen;

import net.minecraft.entity.player.PlayerInventory;
import org.cobra.moreores.block.entity.gem.machine.AbstractGemMachineBlockEntity;

public interface ScreenUtilHelper<T extends AbstractGemMachineBlockEntity<?>> {

    void addPlayerGenericInventory(PlayerInventory playerInventory);

    void addPlayerHotbarInventory(PlayerInventory playerInventory);

    T getBlockEntity();
}
