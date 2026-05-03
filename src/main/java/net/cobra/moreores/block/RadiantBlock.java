package net.cobra.moreores.block;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Degradable;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.function.ValueLists;

import java.util.Optional;
import java.util.function.IntFunction;
import java.util.function.Supplier;

public class RadiantBlock extends Block implements Degradable<RadiantBlock.RadiantCorrosiveState> {

    private final RadiantCorrosiveState corrosiveState;

    public static final Supplier<BiMap<Block, Block>> CORROSION_LEVEL_INCREASES = Suppliers.memoize(
            () -> ImmutableBiMap.<Block, Block>builder()
                    .put(ModBlocks.RADIANT_BLOCK, ModBlocks.EXPOSED_RADIANT_BLOCK)
                    .put(ModBlocks.EXPOSED_RADIANT_BLOCK, ModBlocks.WEATHERED_RADIANT_BLOCK)
                    .put(ModBlocks.WEATHERED_RADIANT_BLOCK, ModBlocks.CORRODED_RADIANT_BLOCK)
                    .build()
    );
    static Supplier<BiMap<Block, Block>> CORROSION_LEVEL_DECREASES = Suppliers.memoize(() ->((BiMap) CORROSION_LEVEL_INCREASES.get()).inverse());

    public RadiantBlock(RadiantCorrosiveState corrosiveState, Settings settings) {
        super(settings);
        this.corrosiveState = corrosiveState;
    }

    public static Optional<Block> getDecreasedOxidationBlock(Block block) {
        return Optional.ofNullable((Block) ((BiMap) CORROSION_LEVEL_DECREASES.get()).get(block));
    }

    public static Optional<Block> getIncreasedOxidationBlock(Block block) {
        return Optional.ofNullable((Block)((BiMap) CORROSION_LEVEL_INCREASES.get()).get(block));
    }

    @Override
    public Optional<BlockState> getDegradationResult(BlockState state) {
        return getIncreasedOxidationBlock(state.getBlock()).map(block -> block.getStateWithProperties(state));
    }

    @Override
    public float getDegradationChanceMultiplier() {
        return this.getDegradationLevel() == RadiantCorrosiveState.UNAFFECTED ? 0.5f : 0.75f;
    }

    @Override
    public RadiantCorrosiveState getDegradationLevel() {
        return this.corrosiveState;
    }

    public enum RadiantCorrosiveState implements StringIdentifiable {
        UNAFFECTED("unaffected"),
        EXPOSED("exposed"),
        WEATHERED("weathered"),
        CORRODED("corroded");

        public static final IntFunction<RadiantCorrosiveState> indexMapper = ValueLists.createIndexToValueFunction(
                Enum::ordinal, values()
        );

        public static final Codec<RadiantCorrosiveState> CODEC = StringIdentifiable.createCodec(RadiantCorrosiveState::values);

        private final String name;

        RadiantCorrosiveState(String name) {
            this.name = name;
        }

        @Override
        public String asString() {
            return name;
        }

        public RadiantCorrosiveState getIncreased() {
            return indexMapper.apply(this.ordinal() + 1);
        }

        public RadiantCorrosiveState getDecreased() {
            return indexMapper.apply(this.ordinal() - 1);
        }
    }

}
