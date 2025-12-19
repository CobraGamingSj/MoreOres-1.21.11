package net.cobra.moreores.block;

import net.cobra.moreores.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.item.ItemConvertible;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class PineappleCropBlock extends CropBlock {
    public static final int MAX_AGE = 4;
    public static final IntProperty AGE = Properties.AGE_4;

    public PineappleCropBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ItemConvertible getSeedsItem() {
        return ModItems.PINEAPPLE_SEEDS;
    }

    @Override
    public IntProperty getAgeProperty() {
        return AGE;
    }

    @Override
    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.getBaseLightLevel(pos, 0) >= 8) {
            int i = this.getAge(state);
            if (i < this.getMaxAge()) {
                float f = getAvailableMoisture(this, world, pos);
                if (random.nextInt((int)(25.0F / f) + 1) == 0) {
                    world.setBlockState(pos, this.withAge(i + 1), Block.NOTIFY_LISTENERS);
                }
            }
        }
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
        return !this.isMature(state);
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        this.applyGrowth(world, pos, state);
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
}
