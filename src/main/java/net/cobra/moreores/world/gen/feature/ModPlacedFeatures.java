package net.cobra.moreores.world.gen.feature;

import net.cobra.moreores.MoreOresModInitializer;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.PlacedFeature;

public class ModPlacedFeatures {

    public static void bootstrap(Registerable<PlacedFeature> featureRegisterable) {
        ModOrePlacedFeatures.bootstrap(featureRegisterable);
    }

    public static RegistryKey<PlacedFeature> of(String id) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(MoreOresModInitializer.MOD_ID, id));
    }
}
