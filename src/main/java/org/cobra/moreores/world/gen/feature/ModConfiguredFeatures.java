package org.cobra.moreores.world.gen.feature;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import org.cobra.moreores.MoreOresModInitializer;

public class ModConfiguredFeatures {

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> featureRegisterable) {
        ModOreConfiguredFeatures.bootstrap(featureRegisterable);
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> of(String id) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(MoreOresModInitializer.MOD_ID, id));
    }
}
