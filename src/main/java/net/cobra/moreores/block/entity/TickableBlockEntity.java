package net.cobra.moreores.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface TickableBlockEntity {

    void tick(World world, BlockPos pos, BlockState state);

    static <T extends BlockEntity> BlockEntityTicker<T> createTicker(World world, BlockState state, BlockEntityType<T> type) {
        return !world.isClient() ? (world0, blockPos, blockState, blockEntity) -> ((TickableBlockEntity) blockEntity).tick(world0, blockPos, blockState) : null;
    }
}
