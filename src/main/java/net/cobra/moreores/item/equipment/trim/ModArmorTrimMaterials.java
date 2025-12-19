package net.cobra.moreores.item.equipment.trim;

import net.cobra.moreores.MoreOresModInitializer;
import net.minecraft.item.equipment.trim.ArmorTrimAssets;
import net.minecraft.item.equipment.trim.ArmorTrimMaterial;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

public class ModArmorTrimMaterials {
    public static final RegistryKey<ArmorTrimMaterial> RUBY = of("ruby");
    public static final RegistryKey<ArmorTrimMaterial> RADIANT = of("radiant");
    public static final RegistryKey<ArmorTrimMaterial> SAPPHIRE = of("sapphire");
    public static final RegistryKey<ArmorTrimMaterial> GREEN_SAPPHIRE = of("green_sapphire");
    public static final RegistryKey<ArmorTrimMaterial> BLUE_GARNET = of("blue_garnet");
    public static final RegistryKey<ArmorTrimMaterial> PINK_GARNET = of("pink_garnet");
    public static final RegistryKey<ArmorTrimMaterial> GREEN_GARNET = of("green_garnet");
    public static final RegistryKey<ArmorTrimMaterial> TOPAZ = of("topaz");
    public static final RegistryKey<ArmorTrimMaterial> WHITE_TOPAZ = of("white_topaz");
    public static final RegistryKey<ArmorTrimMaterial> PERIDOT = of("peridot");
    public static final RegistryKey<ArmorTrimMaterial> JADE = of("jade");
    public static final RegistryKey<ArmorTrimMaterial> PYROPE = of("pyrope");

    public static void bootstrap(Registerable<ArmorTrimMaterial> registerable) {
        register(registerable, RUBY, Style.EMPTY.withColor(16711680), ModArmorTrimAssets.RUBY);
        register(registerable, RADIANT, Style.EMPTY.withColor(11730944), ModArmorTrimAssets.RADIANT);
        register(registerable, SAPPHIRE, Style.EMPTY.withColor(6875), ModArmorTrimAssets.SAPPHIRE);
        register(registerable, GREEN_SAPPHIRE, Style.EMPTY.withColor(2925312), ModArmorTrimAssets.GREEN_SAPPHIRE);
        register(registerable, BLUE_GARNET, Style.EMPTY.withColor(TextColor.fromRgb(1507522)), ModArmorTrimAssets.BLUE_GARNET);
        register(registerable, PINK_GARNET, Style.EMPTY.withColor(16711927), ModArmorTrimAssets.PINK_GARNET);
        register(registerable, GREEN_GARNET, Style.EMPTY.withColor(65331), ModArmorTrimAssets.GREEN_GARNET);
        register(registerable, TOPAZ, Style.EMPTY.withColor(13713152), ModArmorTrimAssets.TOPAZ);
        register(registerable, WHITE_TOPAZ, Style.EMPTY.withColor(15328482), ModArmorTrimAssets.WHITE_TOPAZ);
        register(registerable, PERIDOT, Style.EMPTY.withColor(52238), ModArmorTrimAssets.PERIDOT);
        register(registerable, JADE, Style.EMPTY.withColor(11140783), ModArmorTrimAssets.JADE);
        register(registerable, PYROPE, Style.EMPTY.withColor(12717839), ModArmorTrimAssets.PYROPE);
    }

    private static void register(Registerable<ArmorTrimMaterial> registry, RegistryKey<ArmorTrimMaterial> key, Style style, ArmorTrimAssets assets) {
        Text text = Text.translatable(Util.createTranslationKey("trim_material", key.getValue())).fillStyle(style);
        registry.register(key, new ArmorTrimMaterial(assets, text));
    }

    private static RegistryKey<ArmorTrimMaterial> of(String id) {
        Identifier ID = Identifier.of(MoreOresModInitializer.MOD_ID, id);
        return RegistryKey.of(RegistryKeys.TRIM_MATERIAL, ID);
    }
}
