package net.cobra.moreores.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import org.apache.commons.lang3.function.TriFunction;

import java.util.function.BiFunction;
import java.util.function.Function;

public record RadiantBlockSet(Block unaffected, Block exposed, Block weathered, Block corroded) {
    
    public static <WeatheringBlock extends Block> RadiantBlockSet create(
            String baseId,
            TriFunction<String, Function<AbstractBlock.Settings, Block>, AbstractBlock.Settings, Block> registerFunction,
            BiFunction<RadiantBlock.RadiantCorrosiveState, AbstractBlock.Settings, WeatheringBlock> blockFactory,
            Function<RadiantBlock.RadiantCorrosiveState, AbstractBlock.Settings> settingsFromOxidationLevel
    ) {
        return new RadiantBlockSet(
                registerFunction.apply(
                        baseId,
                        settings -> blockFactory.apply(RadiantBlock.RadiantCorrosiveState.UNAFFECTED, settings),
                        settingsFromOxidationLevel.apply(RadiantBlock.RadiantCorrosiveState.UNAFFECTED)
                ),
                registerFunction.apply(
                        "exposed_" + baseId,
                        settings -> blockFactory.apply(RadiantBlock.RadiantCorrosiveState.EXPOSED, settings),
                        settingsFromOxidationLevel.apply(RadiantBlock.RadiantCorrosiveState.EXPOSED)
                ),
                registerFunction.apply(
                        "weathered_" + baseId,
                        settings -> blockFactory.apply(RadiantBlock.RadiantCorrosiveState.WEATHERED, settings),
                        settingsFromOxidationLevel.apply(RadiantBlock.RadiantCorrosiveState.WEATHERED)
                ),
                registerFunction.apply(
                        "corroded_" + baseId,
                        settings -> blockFactory.apply(RadiantBlock.RadiantCorrosiveState.CORRODED, settings),
                        settingsFromOxidationLevel.apply(RadiantBlock.RadiantCorrosiveState.CORRODED)
                )
        );
    }
    
}
