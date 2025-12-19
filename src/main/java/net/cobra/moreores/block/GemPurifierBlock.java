package net.cobra.moreores.block;

import com.mojang.serialization.MapCodec;
import net.cobra.moreores.MoreOresModInitializer;
import net.cobra.moreores.block.entity.GemPurifierBlockEntity;
import net.cobra.moreores.block.entity.TickableBlockEntity;
import net.cobra.moreores.item.ModItems;
import net.cobra.moreores.registry.ModItemTags;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
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
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.block.WireOrientation;
import org.jetbrains.annotations.Nullable;

public class GemPurifierBlock extends BlockWithEntity implements BlockEntityProvider {
    private static final VoxelShape SHAPE = Block.createCuboidShape(0, 0, 0, 16, 14, 16);
    public static final EnumProperty<Direction> FACING = Properties.HORIZONTAL_FACING;
    public static final BooleanProperty REDSTONE_POWERED = BooleanProperty.of("redstone_powered");
    public static final BooleanProperty HAS_ENERGY = BooleanProperty.of("has_energy");
    public static final MapCodec<GemPurifierBlock> CODEC = GemPurifierBlock.createCodec(GemPurifierBlock::new);

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    protected GemPurifierBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(FACING, Direction.NORTH).with(REDSTONE_POWERED, false));
    }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().rotateYClockwise()).with(REDSTONE_POWERED, ctx.getWorld().isReceivingRedstonePower(ctx.getBlockPos()))
                .with(HAS_ENERGY, false);
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new GemPurifierBlockEntity(pos, state);
    }

    @Override
    protected void onStateReplaced(BlockState state, ServerWorld world, BlockPos pos, boolean moved) {
        if (state.getBlock() != state.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof GemPurifierBlockEntity) {
                ItemScatterer.spawn(world, pos, (GemPurifierBlockEntity) blockEntity);
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
        if(state.get(REDSTONE_POWERED) && !world.isReceivingRedstonePower(pos)) {
            world.setBlockState(pos, state.cycle(REDSTONE_POWERED), Block.NOTIFY_LISTENERS);
        }
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if(MinecraftClient.getInstance().isAltPressed()) {
            ItemStack heldStack = player.getStackInHand(Hand.MAIN_HAND);
            if(!heldStack.isEmpty() && world.getBlockEntity(pos) instanceof GemPurifierBlockEntity be) {
                ItemStack energyStack = be.getStack(GemPurifierBlockEntity.ENERGY_SOURCE_SLOT);
                ItemStack inputStack = be.getStack(GemPurifierBlockEntity.INGREDIENT_SLOT);
                if(!world.isClient()) {
                    if((heldStack.getItem() == ModItems.ENERGY_INGOT || heldStack.getItem() == ModBlocks.ENERGY_BLOCK.asItem())) {
                        if(energyStack.isEmpty()) {
                            be.setStack(GemPurifierBlockEntity.ENERGY_SOURCE_SLOT, heldStack.copyWithCount(1));
                            heldStack.decrement(1);
                        } else if (ItemStack.areItemsEqual(energyStack, heldStack) && energyStack.getCount() < energyStack.getMaxCount()) {
                            energyStack.increment(1);
                            heldStack.decrement(1);
                            be.markDirty();
                        }
                    }
                    if(heldStack.isIn(ModItemTags.RAW_GEMSTONE)) {
                        if(inputStack.isEmpty()) {
                            be.setStack(GemPurifierBlockEntity.INGREDIENT_SLOT, heldStack.copyWithCount(heldStack.getCount()));
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
            NamedScreenHandlerFactory screenHandlerFactory = ((GemPurifierBlockEntity) world.getBlockEntity(pos));

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
        builder.add(HAS_ENERGY);
    }
}