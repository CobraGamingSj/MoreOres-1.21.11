package org.cobra.moreores.client.gui.screen;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.cobra.moreores.block.entity.gem.TestGemBlockEntity;
import org.jspecify.annotations.Nullable;

public class TestScreenHandler extends ScreenHandler implements ScreenUtilHelper {
    public TestScreenHandler(@Nullable ScreenHandlerType<?> type, int syncId) {
        super(type, syncId);
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }

    @Override
    public void addPlayerGenericInventory(PlayerInventory playerInventory) {

    }

    @Override
    public void addPlayerHotbarInventory(PlayerInventory playerInventory) {

    }

    @Override
    public BlockEntity getBlockEntity(BlockPos pos, BlockState state, World world) {
        return new TestGemBlockEntity(pos, state);
    }
}
