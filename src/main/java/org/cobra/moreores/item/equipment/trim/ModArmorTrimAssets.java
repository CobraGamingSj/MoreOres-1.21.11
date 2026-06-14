package org.cobra.moreores.item.equipment.trim;

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
    public static final ArmorTrimAssets KYAWTHUITE = of("kyawthuite");
    public static final ArmorTrimAssets TOPAZ = of("topaz");
    public static final ArmorTrimAssets WHITE_TOPAZ = of("white_topaz");
    public static final ArmorTrimAssets PERIDOT = of("peridot");
    public static final ArmorTrimAssets JADE = of("jade");
    public static final ArmorTrimAssets PYROPE = of("pyrope");
    public static final ArmorTrimAssets CRIMSON_GARNET = of("crimson_garnet");
    public static final ArmorTrimAssets CRYSTALLITE = of("crystallite");
    public static final ArmorTrimAssets RADIANT_AMETHYST = of("radiant_amethyst");
    public static final ArmorTrimAssets ALEXANDRITE = of("alexandrite");
    public static final ArmorTrimAssets LIMESTONE = of("limestone");
    public static final ArmorTrimAssets MOONSTONE = of("moonstone");
    public static final ArmorTrimAssets QUARTSIDIAN = of("quartsidian");
    public static final ArmorTrimAssets OPAL = of("opal");
    public static final ArmorTrimAssets RED_BERYL = of("red_beryl");

    public static ArmorTrimAssets of(String suffix) {
        return new ArmorTrimAssets(new ArmorTrimAssets.AssetId(suffix), Map.of());
    }

}
