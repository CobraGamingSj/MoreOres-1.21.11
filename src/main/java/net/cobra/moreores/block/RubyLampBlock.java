package net.cobra.moreores.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.block.WireOrientation;
import org.jetbrains.annotations.Nullable;

public class RubyLampBlock extends Block {

    public static final BooleanProperty LIT = BooleanProperty.of("lit");

    public RubyLampBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(LIT, false));
    }

    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(LIT, ctx.getWorld().isReceivingRedstonePower(ctx.getBlockPos()));
    }

    @Override
    protected void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, @Nullable WireOrientation wireOrientation, boolean notify) {
        if (!world.isClient()) {
            boolean bl = state.get(LIT);
            if (bl != world.isReceivingRedstonePower(pos)) {
                if (bl) {
                    world.scheduleBlockTick(pos, this, 4);
                } else {
                    world.setBlockState(pos, state.cycle(LIT), Block.NOTIFY_LISTENERS);
                }
            }
        }
    }

    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (state.get(LIT) && !world.isReceivingRedstonePower(pos)) {
            world.setBlockState(pos, state.cycle(LIT), 2);
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {

        if (!world.isClient()) {
            boolean currentState = state.get(LIT);
            world.setBlockState(pos, state.with(LIT, !currentState), Block.NOTIFY_ALL);
        }

        return super.onUse(state, world, pos, player, hit);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LIT);
    }
}
