package net.cobra.moreores.world.gen;

import net.cobra.moreores.MoreOresModInitializer;
import net.cobra.moreores.world.gen.feature.ModOrePlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.gen.GenerationStep;

public class WorldGeneration {
    public static void generate() {

        MoreOresModInitializer.LOGGER.info("Setting Up world generation for Custom Ores for " + MoreOresModInitializer.MOD_ID + " mod.");

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES, ModOrePlacedFeatures.ORE_RUBY);

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES, ModOrePlacedFeatures.ORE_RUBY_MEDIUM);

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES, ModOrePlacedFeatures.ORE_RUBY_LARGE);

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES, ModOrePlacedFeatures.ORE_SAPPHIRE);

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES, ModOrePlacedFeatures.ORE_SAPPHIRE_MEDIUM);

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES, ModOrePlacedFeatures.ORE_SAPPHIRE_LARGE);

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES, ModOrePlacedFeatures.ORE_GREEN_SAPPHIRE);

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES, ModOrePlacedFeatures.ORE_GREEN_SAPPHIRE_MEDIUM);

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES, ModOrePlacedFeatures.ORE_GREEN_SAPPHIRE_LARGE);

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES, ModOrePlacedFeatures.ORE_BLUE_GARNET);

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES, ModOrePlacedFeatures.ORE_BLUE_GARNET_MEDIUM);

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES, ModOrePlacedFeatures.ORE_BLUE_GARNET_LARGE);

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES, ModOrePlacedFeatures.ORE_PINK_GARNET);

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES, ModOrePlacedFeatures.ORE_PINK_GARNET_MEDIUM);

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES, ModOrePlacedFeatures.ORE_PINK_GARNET_LARGE);

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES, ModOrePlacedFeatures.ORE_GREEN_GARNET);

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES, ModOrePlacedFeatures.ORE_GREEN_GARNET_MEDIUM);

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES, ModOrePlacedFeatures.ORE_GREEN_GARNET_LARGE);

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES, ModOrePlacedFeatures.ORE_TOPAZ);

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES, ModOrePlacedFeatures.ORE_TOPAZ_MEDIUM);

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES, ModOrePlacedFeatures.ORE_TOPAZ_LARGE);

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES, ModOrePlacedFeatures.ORE_WHITE_TOPAZ);

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES, ModOrePlacedFeatures.ORE_WHITE_TOPAZ_MEDIUM);

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES, ModOrePlacedFeatures.ORE_WHITE_TOPAZ_LARGE);

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES, ModOrePlacedFeatures.ORE_PERIDOT);

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES, ModOrePlacedFeatures.ORE_PERIDOT_MEDIUM);

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES, ModOrePlacedFeatures.ORE_PERIDOT_LARGE);

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES, ModOrePlacedFeatures.ORE_JADE);

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES, ModOrePlacedFeatures.ORE_JADE_MEDIUM);

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES, ModOrePlacedFeatures.ORE_JADE_LARGE);

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES, ModOrePlacedFeatures.ORE_PYROPE);

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES, ModOrePlacedFeatures.ORE_PYROPE_MEDIUM);

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES, ModOrePlacedFeatures.ORE_PYROPE_LARGE);
    }
}
