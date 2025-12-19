package net.cobra.moreores.item.equipment.trim;

import net.cobra.moreores.MoreOresModInitializer;
import net.minecraft.item.equipment.trim.ArmorTrimPattern;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

public class ModArmorTrimPatterns {
    public static final RegistryKey<ArmorTrimPattern> GUARDIAN = of("guardian");

    public static void bootstrap(Registerable<ArmorTrimPattern> registerable) {
        register(registerable, GUARDIAN);
    }

    public static void register(Registerable<ArmorTrimPattern> registry, RegistryKey<ArmorTrimPattern> key) {
        ArmorTrimPattern armorTrimPattern = new ArmorTrimPattern(getId(key), Text.translatable(Util.createTranslationKey("trim_pattern", key.getValue())), false);
        registry.register(key, armorTrimPattern);
    }

    public static Identifier getId(RegistryKey<ArmorTrimPattern> key) {
        return key.getValue();
    }

    private static RegistryKey<ArmorTrimPattern> of(String id) {
        Identifier ID = Identifier.of(MoreOresModInitializer.MOD_ID, id);
        return RegistryKey.of(RegistryKeys.TRIM_PATTERN, ID);
    }
}
