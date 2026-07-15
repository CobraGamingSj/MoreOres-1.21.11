package org.cobra.moreores.client.gui.screen;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerInventory;

public interface ScreenUtilHelper<T extends BlockEntity> {

    void addPlayerGenericInventory(PlayerInventory playerInventory);

    void addPlayerHotbarInventory(PlayerInventory playerInventory);

    T getBlockEntity();
}
