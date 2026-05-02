package net.cobra.moreores.block;

import com.mojang.serialization.MapCodec;
import net.cobra.moreores.MoreOresModInitializer;
import net.cobra.moreores.block.entity.TickableBlockEntity;
import net.cobra.moreores.block.entity.gem.GemFusionBlockEntity;
import net.cobra.moreores.block.entity.gem.GemFusionBlockEntity;
import net.cobra.moreores.item.ModItems;
import net.cobra.moreores.item.util.GemType;
import net.cobra.moreores.registry.ModItemTags;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.block.WireOrientation;
import org.jetbrains.annotations.Nullable;

public class GemFusionBlock extends BlockWithEntity implements BlockEntityProvider {
    private static final VoxelShape SHAPE = Block.createCuboidShape(0, 0, 0, 16, 14, 16);
    public static final EnumProperty<Direction> FACING = Properties.HORIZONTAL_FACING;
    public static final BooleanProperty REDSTONE_POWERED = BooleanProperty.of("redstone_powered");
    public static final EnumProperty<GemType> IS_POLISHING = EnumProperty.of("is_polishing", GemType.class);
    public static final MapCodec<GemFusionBlock> CODEC = GemFusionBlock.createCodec(GemFusionBlock::new);

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    protected GemFusionBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(FACING, Direction.NORTH).with(REDSTONE_POWERED, false)
                .with(IS_POLISHING, GemType.EMPTY));
    }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().rotateYClockwise()).with(REDSTONE_POWERED, ctx.getWorld().isReceivingRedstonePower(ctx.getBlockPos()))
                .with(IS_POLISHING, GemType.EMPTY);
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new GemFusionBlockEntity(pos, state);
    }

    @Override
    protected void onStateReplaced(BlockState state, ServerWorld world, BlockPos pos, boolean moved) {
        if (state.getBlock() != state.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof GemFusionBlockEntity) {
                ItemScatterer.spawn(world, pos, (GemFusionBlockEntity) blockEntity);
                world.updateComparators(pos,this);
            }
            super.onStateReplaced(state, world, pos, moved);
        }
    }

    @Override
    protected void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, @Nullable WireOrientation wireOrientation, boolean notify) {
        if (!world.isClient()) {
            boolean bl = state.get(REDSTONE_POWERED);
            if (bl != world.isReceivingRedstonePower(pos)) {
                if (bl) {
                    world.scheduleBlockTick(pos, this, 4);
                } else {
                    world.setBlockState(pos, state.cycle(REDSTONE_POWERED), Block.NOTIFY_LISTENERS);
                    MoreOresModInitializer.LOGGER.info("Receiving Redstone Signal at BlockPos: '{}'", pos);
                }
            }
        }
    }

    @Override
    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        BlockState newState = state;

        if(state.get(REDSTONE_POWERED) && !world.isReceivingRedstonePower(pos)) {
            newState = newState.with(REDSTONE_POWERED, false);
        }

        if(world.getBlockEntity(pos) instanceof GemFusionBlockEntity be) {
            newState = newState.with(IS_POLISHING, be.getGem());
        }

        world.setBlockState(pos, newState, Block.NOTIFY_ALL);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {

        if(MinecraftClient.getInstance().isAltPressed()) {

            ItemStack heldStack = player.getStackInHand(Hand.MAIN_HAND);

            if(!heldStack.isEmpty() && world.getBlockEntity(pos) instanceof GemFusionBlockEntity be) {
                ItemStack energyStack = be.getStack(GemFusionBlockEntity.ENERGY_SOURCE_SLOT);
                ItemStack inputStack = be.getStack(GemFusionBlockEntity.INGREDIENT_SLOT);

                if(!world.isClient()) {
                    if(heldStack.getItem() == ModItems.RADIANT) {
                        if(energyStack.isEmpty()) {
                            be.setStack(GemFusionBlockEntity.ENERGY_SOURCE_SLOT, heldStack.copyWithCount(heldStack.getCount()));
                            heldStack.decrement(heldStack.getCount());
                        } else if (ItemStack.areItemsEqual(energyStack, heldStack) && energyStack.getCount() < energyStack.getMaxCount()) {
                            energyStack.increment(heldStack.getCount());
                            heldStack.decrement(heldStack.getCount());
                            be.markDirty();
                        }
                    }

                    if(heldStack.isIn(ModItemTags.RAW_GEMSTONE)) {
                        if(inputStack.isEmpty()) {
                            be.setStack(GemFusionBlockEntity.INGREDIENT_SLOT, heldStack.copyWithCount(heldStack.getCount()));
                            heldStack.decrement(heldStack.getCount());
                        } else if (ItemStack.areItemsEqual(inputStack, heldStack)) {
                            inputStack.increment(heldStack.getCount());
                            heldStack.decrement(heldStack.getCount());
                            be.markDirty();
                        }
                    }
                }
                return ActionResult.SUCCESS;
            }
        }

        if(!world.isClient()){
            NamedScreenHandlerFactory screenHandlerFactory = ((GemFusionBlockEntity) world.getBlockEntity(pos));

            if (screenHandlerFactory != null) {
                player.openHandledScreen(screenHandlerFactory);
            }
        }

        return ActionResult.SUCCESS;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return TickableBlockEntity.createTicker(world, state, type);
    }

    @Override
    protected BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    protected BlockState mirror(BlockState state, BlockMirror mirror) {
        return super.mirror(state, mirror);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        builder.add(REDSTONE_POWERED);
        builder.add(IS_POLISHING);
    }
}