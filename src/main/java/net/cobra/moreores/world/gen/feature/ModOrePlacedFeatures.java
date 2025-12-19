package net.cobra.moreores.world.gen.feature;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.placementmodifier.*;

import java.util.List;

public class ModOrePlacedFeatures {

    public static final RegistryKey<PlacedFeature> ORE_RUBY = ModPlacedFeatures.of("ore_ruby");
    public static final RegistryKey<PlacedFeature> ORE_RUBY_MEDIUM = ModPlacedFeatures.of("ore_ruby_medium");
    public static final RegistryKey<PlacedFeature> ORE_RUBY_LARGE = ModPlacedFeatures.of("ore_ruby_large");
    public static final RegistryKey<PlacedFeature> ORE_SAPPHIRE = ModPlacedFeatures.of("ore_sapphire");
    public static final RegistryKey<PlacedFeature> ORE_SAPPHIRE_MEDIUM = ModPlacedFeatures.of("ore_sapphire_medium");
    public static final RegistryKey<PlacedFeature> ORE_SAPPHIRE_LARGE = ModPlacedFeatures.of("ore_sapphire_large");
    public static final RegistryKey<PlacedFeature> ORE_GREEN_SAPPHIRE = ModPlacedFeatures.of("ore_green_sapphire");
    public static final RegistryKey<PlacedFeature> ORE_GREEN_SAPPHIRE_MEDIUM = ModPlacedFeatures.of("ore_green_sapphire_medium");
    public static final RegistryKey<PlacedFeature> ORE_GREEN_SAPPHIRE_LARGE = ModPlacedFeatures.of("ore_green_sapphire_large");
    public static final RegistryKey<PlacedFeature> ORE_BLUE_GARNET = ModPlacedFeatures.of("ore_blue_garnet");
    public static final RegistryKey<PlacedFeature> ORE_BLUE_GARNET_MEDIUM = ModPlacedFeatures.of("ore_blue_garnet_medium");
    public static final RegistryKey<PlacedFeature> ORE_BLUE_GARNET_LARGE = ModPlacedFeatures.of("ore_blue_garnet_large");
    public static final RegistryKey<PlacedFeature> ORE_PINK_GARNET = ModPlacedFeatures.of("ore_pink_garnet");
    public static final RegistryKey<PlacedFeature> ORE_PINK_GARNET_MEDIUM = ModPlacedFeatures.of("ore_pink_garnet_medium");
    public static final RegistryKey<PlacedFeature> ORE_PINK_GARNET_LARGE = ModPlacedFeatures.of("ore_pink_garnet_large");
    public static final RegistryKey<PlacedFeature> ORE_GREEN_GARNET = ModPlacedFeatures.of("ore_green_garnet");
    public static final RegistryKey<PlacedFeature> ORE_GREEN_GARNET_MEDIUM = ModPlacedFeatures.of("ore_green_garnet_medium");
    public static final RegistryKey<PlacedFeature> ORE_GREEN_GARNET_LARGE = ModPlacedFeatures.of("ore_green_garnet_large");
    public static final RegistryKey<PlacedFeature> ORE_TOPAZ = ModPlacedFeatures.of("ore_topaz");
    public static final RegistryKey<PlacedFeature> ORE_TOPAZ_MEDIUM = ModPlacedFeatures.of("ore_topaz_medium");
    public static final RegistryKey<PlacedFeature> ORE_TOPAZ_LARGE = ModPlacedFeatures.of("ore_topaz_large");
    public static final RegistryKey<PlacedFeature> ORE_WHITE_TOPAZ = ModPlacedFeatures.of("ore_white_topaz");
    public static final RegistryKey<PlacedFeature> ORE_WHITE_TOPAZ_MEDIUM = ModPlacedFeatures.of("ore_white_topaz_medium");
    public static final RegistryKey<PlacedFeature> ORE_WHITE_TOPAZ_LARGE = ModPlacedFeatures.of("ore_white_topaz_large");
    public static final RegistryKey<PlacedFeature> ORE_PERIDOT = ModPlacedFeatures.of("ore_peridot");
    public static final RegistryKey<PlacedFeature> ORE_PERIDOT_MEDIUM = ModPlacedFeatures.of("ore_peridot_medium");
    public static final RegistryKey<PlacedFeature> ORE_PERIDOT_LARGE = ModPlacedFeatures.of("ore_peridot_large");
    public static final RegistryKey<PlacedFeature> ORE_JADE = ModPlacedFeatures.of("ore_jade");
    public static final RegistryKey<PlacedFeature> ORE_JADE_MEDIUM = ModPlacedFeatures.of("ore_jade_medium");
    public static final RegistryKey<PlacedFeature> ORE_JADE_LARGE = ModPlacedFeatures.of("ore_jade_large");
    public static final RegistryKey<PlacedFeature> ORE_PYROPE = ModPlacedFeatures.of("ore_pyrope");
    public static final RegistryKey<PlacedFeature> ORE_PYROPE_MEDIUM = ModPlacedFeatures.of("ore_pyrope_medium");
    public static final RegistryKey<PlacedFeature> ORE_PYROPE_LARGE = ModPlacedFeatures.of("ore_pyrope_large");

    private static List<PlacementModifier> modifiers(PlacementModifier countModifier, PlacementModifier heightModifier) {
        return List.of(countModifier, SquarePlacementModifier.of(), heightModifier, BiomePlacementModifier.of());
    }

    private static List<PlacementModifier> modifiersWithCount(int count, PlacementModifier heightModifier) {
        return modifiers(CountPlacementModifier.of(count), heightModifier);
    }

    private static List<PlacementModifier> modifiersWithRarity(int chance, PlacementModifier heightModifier) {
        return modifiers(RarityFilterPlacementModifier.of(chance), heightModifier);
    }
    public static void bootstrap(Registerable<PlacedFeature> placedFeatureRegisterable) {
        RegistryEntryLookup<ConfiguredFeature<?, ?>> placedFeatureRegisterableRegistryLookup = placedFeatureRegisterable.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);
        RegistryEntry<ConfiguredFeature<?, ?>> registryEntry20 = placedFeatureRegisterableRegistryLookup.getOrThrow(ModOreConfiguredFeatures.ORE_RUBY_SMALL);
        RegistryEntry<ConfiguredFeature<?, ?>> registryEntry21 = placedFeatureRegisterableRegistryLookup.getOrThrow(ModOreConfiguredFeatures.ORE_RUBY_MEDIUM);
        RegistryEntry<ConfiguredFeature<?, ?>> registryEntry22 = placedFeatureRegisterableRegistryLookup.getOrThrow(ModOreConfiguredFeatures.ORE_RUBY_LARGE);
        RegistryEntry<ConfiguredFeature<?, ?>> registryEntry23 = placedFeatureRegisterableRegistryLookup.getOrThrow(ModOreConfiguredFeatures.ORE_SAPPHIRE_SMALL);
        RegistryEntry<ConfiguredFeature<?, ?>> registryEntry24 = placedFeatureRegisterableRegistryLookup.getOrThrow(ModOreConfiguredFeatures.ORE_SAPPHIRE_MEDIUM);
        RegistryEntry<ConfiguredFeature<?, ?>> registryEntry25 = placedFeatureRegisterableRegistryLookup.getOrThrow(ModOreConfiguredFeatures.ORE_SAPPHIRE_LARGE);
        RegistryEntry<ConfiguredFeature<?, ?>> registryEntry26 = placedFeatureRegisterableRegistryLookup.getOrThrow(ModOreConfiguredFeatures.ORE_GREEN_SAPPHIRE_SMALL);
        RegistryEntry<ConfiguredFeature<?, ?>> registryEntry27 = placedFeatureRegisterableRegistryLookup.getOrThrow(ModOreConfiguredFeatures.ORE_GREEN_SAPPHIRE_MEDIUM);
        RegistryEntry<ConfiguredFeature<?, ?>> registryEntry28 = placedFeatureRegisterableRegistryLookup.getOrThrow(ModOreConfiguredFeatures.ORE_GREEN_SAPPHIRE_LARGE);
        RegistryEntry<ConfiguredFeature<?, ?>> registryEntry29 = placedFeatureRegisterableRegistryLookup.getOrThrow(ModOreConfiguredFeatures.ORE_BLUE_GARNET_SMALL);
        RegistryEntry<ConfiguredFeature<?, ?>> registryEntry30 = placedFeatureRegisterableRegistryLookup.getOrThrow(ModOreConfiguredFeatures.ORE_BLUE_GARNET_MEDIUM);
        RegistryEntry<ConfiguredFeature<?, ?>> registryEntry31 = placedFeatureRegisterableRegistryLookup.getOrThrow(ModOreConfiguredFeatures.ORE_BLUE_GARNET_LARGE);
        RegistryEntry<ConfiguredFeature<?, ?>> registryEntry32 = placedFeatureRegisterableRegistryLookup.getOrThrow(ModOreConfiguredFeatures.ORE_PINK_GARNET_SMALL);
        RegistryEntry<ConfiguredFeature<?, ?>> registryEntry33 = placedFeatureRegisterableRegistryLookup.getOrThrow(ModOreConfiguredFeatures.ORE_PINK_GARNET_MEDIUM);
        RegistryEntry<ConfiguredFeature<?, ?>> registryEntry34 = placedFeatureRegisterableRegistryLookup.getOrThrow(ModOreConfiguredFeatures.ORE_PINK_GARNET_LARGE);
        RegistryEntry<ConfiguredFeature<?, ?>> registryEntry35 = placedFeatureRegisterableRegistryLookup.getOrThrow(ModOreConfiguredFeatures.ORE_GREEN_GARNET_SMALL);
        RegistryEntry<ConfiguredFeature<?, ?>> registryEntry36 = placedFeatureRegisterableRegistryLookup.getOrThrow(ModOreConfiguredFeatures.ORE_GREEN_GARNET_MEDIUM);
        RegistryEntry<ConfiguredFeature<?, ?>> registryEntry37 = placedFeatureRegisterableRegistryLookup.getOrThrow(ModOreConfiguredFeatures.ORE_GREEN_GARNET_LARGE);
        RegistryEntry<ConfiguredFeature<?, ?>> registryEntry38 = placedFeatureRegisterableRegistryLookup.getOrThrow(ModOreConfiguredFeatures.ORE_TOPAZ_SMALL);
        RegistryEntry<ConfiguredFeature<?, ?>> registryEntry39 = placedFeatureRegisterableRegistryLookup.getOrThrow(ModOreConfiguredFeatures.ORE_TOPAZ_MEDIUM);
        RegistryEntry<ConfiguredFeature<?, ?>> registryEntry40 = placedFeatureRegisterableRegistryLookup.getOrThrow(ModOreConfiguredFeatures.ORE_TOPAZ_LARGE);
        RegistryEntry<ConfiguredFeature<?, ?>> registryEntry41 = placedFeatureRegisterableRegistryLookup.getOrThrow(ModOreConfiguredFeatures.ORE_WHITE_TOPAZ_SMALL);
        RegistryEntry<ConfiguredFeature<?, ?>> registryEntry42 = placedFeatureRegisterableRegistryLookup.getOrThrow(ModOreConfiguredFeatures.ORE_WHITE_TOPAZ_MEDIUM);
        RegistryEntry<ConfiguredFeature<?, ?>> registryEntry43 = placedFeatureRegisterableRegistryLookup.getOrThrow(ModOreConfiguredFeatures.ORE_WHITE_TOPAZ_LARGE);
        RegistryEntry<ConfiguredFeature<?, ?>> registryEntry44 = placedFeatureRegisterableRegistryLookup.getOrThrow(ModOreConfiguredFeatures.ORE_PERIDOT_SMALL);
        RegistryEntry<ConfiguredFeature<?, ?>> registryEntry45 = placedFeatureRegisterableRegistryLookup.getOrThrow(ModOreConfiguredFeatures.ORE_PERIDOT_LARGE);
        RegistryEntry<ConfiguredFeature<?, ?>> registryEntry46 = placedFeatureRegisterableRegistryLookup.getOrThrow(ModOreConfiguredFeatures.ORE_PERIDOT_MEDIUM);
        RegistryEntry<ConfiguredFeature<?, ?>> registryEntry47 = placedFeatureRegisterableRegistryLookup.getOrThrow(ModOreConfiguredFeatures.ORE_JADE_SMALL);
        RegistryEntry<ConfiguredFeature<?, ?>> registryEntry48 = placedFeatureRegisterableRegistryLookup.getOrThrow(ModOreConfiguredFeatures.ORE_JADE_LARGE);
        RegistryEntry<ConfiguredFeature<?, ?>> registryEntry49 = placedFeatureRegisterableRegistryLookup.getOrThrow(ModOreConfiguredFeatures.ORE_JADE_MEDIUM);
        RegistryEntry<ConfiguredFeature<?, ?>> registryEntry50 = placedFeatureRegisterableRegistryLookup.getOrThrow(ModOreConfiguredFeatures.ORE_PYROPE_SMALL);
        RegistryEntry<ConfiguredFeature<?, ?>> registryEntry51 = placedFeatureRegisterableRegistryLookup.getOrThrow(ModOreConfiguredFeatures.ORE_PYROPE_LARGE);
        RegistryEntry<ConfiguredFeature<?, ?>> registryEntry52 = placedFeatureRegisterableRegistryLookup.getOrThrow(ModOreConfiguredFeatures.ORE_PYROPE_MEDIUM);

        PlacedFeatures.register(
                placedFeatureRegisterable,
                ORE_RUBY,
                registryEntry20,
                modifiersWithCount(8,HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-20), YOffset.aboveBottom(76)))
        );
        PlacedFeatures.register(
                placedFeatureRegisterable, ORE_RUBY_MEDIUM, registryEntry21, modifiersWithCount(9,HeightRangePlacementModifier.uniform(YOffset.fixed(-64), YOffset.fixed(-4)))
        );
        PlacedFeatures.register(
                placedFeatureRegisterable,
                ORE_RUBY_LARGE,
                registryEntry22,
                modifiersWithRarity(6, HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-20), YOffset.aboveBottom(72)))
        );
        PlacedFeatures.register(
                placedFeatureRegisterable,
                ORE_SAPPHIRE,
                registryEntry23,
                modifiersWithCount(5,HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-20), YOffset.aboveBottom(76)))
        );
        PlacedFeatures.register(
                placedFeatureRegisterable, ORE_SAPPHIRE_MEDIUM, registryEntry24, modifiersWithCount(5,HeightRangePlacementModifier.uniform(YOffset.fixed(-64), YOffset.fixed(-4)))
        );
        PlacedFeatures.register(
                placedFeatureRegisterable,
                ORE_SAPPHIRE_LARGE,
                registryEntry25,
                modifiersWithRarity(8, HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-20), YOffset.aboveBottom(72)))
        );
        PlacedFeatures.register(
                placedFeatureRegisterable,
                ORE_GREEN_SAPPHIRE,
                registryEntry26,
                modifiersWithCount(5,HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-20), YOffset.aboveBottom(76)))
        );
        PlacedFeatures.register(
                placedFeatureRegisterable, ORE_GREEN_SAPPHIRE_MEDIUM, registryEntry25,modifiersWithCount(5,HeightRangePlacementModifier.uniform(YOffset.fixed(-64), YOffset.fixed(-4)))
        );
        PlacedFeatures.register(
                placedFeatureRegisterable,
                ORE_GREEN_SAPPHIRE_LARGE,
                registryEntry28,
                modifiersWithRarity(6, HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-20), YOffset.aboveBottom(77)))
        );
        PlacedFeatures.register(
                placedFeatureRegisterable,
                ORE_BLUE_GARNET,
                registryEntry29,
                modifiersWithCount(5,HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-20), YOffset.aboveBottom(75)))
        );
        PlacedFeatures.register(
                placedFeatureRegisterable, ORE_BLUE_GARNET_MEDIUM, registryEntry30, modifiersWithCount(5,HeightRangePlacementModifier.uniform(YOffset.fixed(-64), YOffset.fixed(-4)))
        );
        PlacedFeatures.register(
                placedFeatureRegisterable,
                ORE_BLUE_GARNET_LARGE,
                registryEntry31,
                modifiersWithRarity(4, HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-20), YOffset.aboveBottom(70)))
        );
        PlacedFeatures.register(
                placedFeatureRegisterable,
                ORE_PINK_GARNET,
                registryEntry32,
                modifiersWithCount(5,HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-20), YOffset.aboveBottom(70)))
        );
        PlacedFeatures.register(
                placedFeatureRegisterable, ORE_PINK_GARNET_MEDIUM, registryEntry33, modifiersWithCount(7,HeightRangePlacementModifier.uniform(YOffset.fixed(-64), YOffset.fixed(-4)))
        );
        PlacedFeatures.register(
                placedFeatureRegisterable,
                ORE_PINK_GARNET_LARGE,
                registryEntry34,
                modifiersWithRarity(4, HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-20), YOffset.aboveBottom(75)))
        );
        PlacedFeatures.register(
                placedFeatureRegisterable,
                ORE_GREEN_GARNET,
                registryEntry35,
                modifiersWithCount(5,HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-20), YOffset.aboveBottom(71)))
        );
        PlacedFeatures.register(
                placedFeatureRegisterable, ORE_GREEN_GARNET_MEDIUM, registryEntry36, modifiersWithCount(7,HeightRangePlacementModifier.uniform(YOffset.fixed(-64), YOffset.fixed(-4)))
        );
        PlacedFeatures.register(
                placedFeatureRegisterable,
                ORE_GREEN_GARNET_LARGE,
                registryEntry37,
                modifiersWithRarity(6, HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-20), YOffset.aboveBottom(67)))
        );
        PlacedFeatures.register(
                placedFeatureRegisterable,
                ORE_TOPAZ,
                registryEntry38,
                modifiersWithCount(5,HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-20), YOffset.aboveBottom(52)))
        );
        PlacedFeatures.register(
                placedFeatureRegisterable, ORE_TOPAZ_MEDIUM, registryEntry39, modifiersWithCount(7,HeightRangePlacementModifier.uniform(YOffset.fixed(-64), YOffset.fixed(-4)))
        );
        PlacedFeatures.register(
                placedFeatureRegisterable,
                ORE_TOPAZ_LARGE,
                registryEntry40,
                modifiersWithRarity(7,HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-20), YOffset.aboveBottom(56)))
        );
        PlacedFeatures.register(
                placedFeatureRegisterable,
                ORE_WHITE_TOPAZ,
                registryEntry41,
                modifiersWithCount(5,HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-20), YOffset.aboveBottom(67)))
        );
        PlacedFeatures.register(
                placedFeatureRegisterable, ORE_WHITE_TOPAZ_MEDIUM, registryEntry47,modifiersWithCount(7,HeightRangePlacementModifier.uniform(YOffset.fixed(-64), YOffset.fixed(-4)))
        );
        PlacedFeatures.register(
                placedFeatureRegisterable,
                ORE_WHITE_TOPAZ_LARGE,
                registryEntry43,
                modifiersWithRarity(5, HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-20), YOffset.aboveBottom(68)))
        );
        PlacedFeatures.register(
                placedFeatureRegisterable,
                ORE_PERIDOT,
                registryEntry44,
                modifiersWithCount(5,HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-20), YOffset.aboveBottom(62)))
        );
        PlacedFeatures.register(
                placedFeatureRegisterable, ORE_PERIDOT_MEDIUM, registryEntry45, modifiersWithCount(7,HeightRangePlacementModifier.uniform(YOffset.fixed(-64), YOffset.fixed(-4)))
        );
        PlacedFeatures.register(
                placedFeatureRegisterable,
                ORE_PERIDOT_LARGE,
                registryEntry46,
                modifiersWithRarity(3, HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-20), YOffset.aboveBottom(68)))
        );
        PlacedFeatures.register(
                placedFeatureRegisterable,
                ORE_JADE,
                registryEntry47,
                modifiersWithCount(5,HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-20), YOffset.aboveBottom(73)))
        );
        PlacedFeatures.register(
                placedFeatureRegisterable, ORE_JADE_MEDIUM, registryEntry48, modifiersWithCount(7,HeightRangePlacementModifier.uniform(YOffset.fixed(-64), YOffset.fixed(-4)))
        );
        PlacedFeatures.register(
                placedFeatureRegisterable,
                ORE_JADE_LARGE,
                registryEntry49,
                modifiersWithRarity(4,HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-20), YOffset.aboveBottom(74)))
        );
        PlacedFeatures.register(
                placedFeatureRegisterable,
                ORE_PYROPE,
                registryEntry50,
                modifiersWithCount(5,HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-20), YOffset.aboveBottom(76)))
        );
        PlacedFeatures.register(
                placedFeatureRegisterable, ORE_PYROPE_MEDIUM, registryEntry51, modifiersWithCount(6, HeightRangePlacementModifier.uniform(YOffset.fixed(-64), YOffset.fixed(-4)))
        );
        PlacedFeatures.register(
                placedFeatureRegisterable,
                ORE_PYROPE_LARGE,
                registryEntry52,
                modifiersWithRarity(5, HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-20), YOffset.aboveBottom(72)))
        );
    }
}
