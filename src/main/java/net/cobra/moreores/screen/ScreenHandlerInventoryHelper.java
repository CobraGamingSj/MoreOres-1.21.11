package net.cobra.moreores.screen;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface ScreenHandlerInventoryHelper {

    void addPlayerGenericInventory(PlayerInventory playerInventory);

    void addPlayerHotbarInventory(PlayerInventory playerInventory);

    BlockEntity getBlockEntity(BlockPos pos, BlockState state, World world);
}
