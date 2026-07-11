package org.cobra.moreores.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class TestGemBlock extends Block {
    public static final VoxelShape SHAPE = Block.createCuboidShape(0, 0, 0, 16, 16, 16);

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if(world.isClient()) {
            return ActionResult.CONSUME;
        }
//        NamedScreenHandlerFactory screenHandlerFactory = (TestGemBlockEntity) world.getBlockEntity(pos);
//        player.openHandledScreen(screenHandlerFactory);
        return ActionResult.SUCCESS;
    }

    public TestGemBlock(Settings settings) {
        super(settings);
    }
}