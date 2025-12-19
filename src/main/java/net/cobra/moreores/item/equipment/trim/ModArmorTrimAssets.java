package net.cobra.moreores.item.equipment.trim;

import net.minecraft.item.equipment.trim.ArmorTrimAssets;

import java.util.Map;

public class ModArmorTrimAssets {

    public static final ArmorTrimAssets RUBY = of("ruby");
    public static final ArmorTrimAssets RADIANT = of("radiant");
    public static final ArmorTrimAssets SAPPHIRE = of("sapphire");
    public static final ArmorTrimAssets GREEN_SAPPHIRE = of("green_sapphire");
    public static final ArmorTrimAssets BLUE_GARNET = of("blue_garnet");
    public static final ArmorTrimAssets PINK_GARNET = of("pink_garnet");
    public static final ArmorTrimAssets GREEN_GARNET = of("green_garnet");
    public static final ArmorTrimAssets TOPAZ = of("topaz");
    public static final ArmorTrimAssets WHITE_TOPAZ = of("white_topaz");
    public static final ArmorTrimAssets PERIDOT = of("peridot");
    public static final ArmorTrimAssets JADE = of("jade");
    public static final ArmorTrimAssets PYROPE = of("pyrope");

    public static ArmorTrimAssets of(String suffix) {
        return new ArmorTrimAssets(new ArmorTrimAssets.AssetId(suffix), Map.of());
    }

}
