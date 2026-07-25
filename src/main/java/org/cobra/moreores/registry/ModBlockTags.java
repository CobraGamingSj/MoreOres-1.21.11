package org.cobra.moreores.registry;

import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import org.cobra.moreores.MoreOresModInitializer;

public class ModBlockTags {

    public static final TagKey<Block> NEEDS_RUBY_TOOL = of("needs_ruby_tool");
    public static final TagKey<Block> INCORRECT_FOR_RUBY_TOOL = of("incorrect_for_ruby_tool");
    public static final TagKey<Block> NEEDS_SAPPHIRE_TOOL = of("needs_sapphire_tool");
    public static final TagKey<Block> INCORRECT_FOR_SAPPHIRE_TOOL = of("incorrect_for_sapphire_tool");
    public static final TagKey<Block> NEEDS_RADIANT_TOOL = of("needs_radiant_tool");
    public static final TagKey<Block> INCORRECT_FOR_RADIANT_TOOL = of("incorrect_for_radiant_tool");
    public static final TagKey<Block> MOD_ORES = of("mod_ores");
    public static final TagKey<Block> RUBY_ORES = of("ruby_ores");
    public static final TagKey<Block> SAPPHIRE_ORES = of("sapphire_ores");
    public static final TagKey<Block> GREEN_SAPPHIRE_ORES = of("green_sapphire_ores");
    public static final TagKey<Block> BLUE_GARNET_ORES = of("blue_garnet_ores");
    public static final TagKey<Block> PINK_GARNET_ORES = of("pink_garnet_ores");
    public static final TagKey<Block> GREEN_GARNET_ORES = of("green_garnet_ores");
    public static final TagKey<Block> KYAWTHUITE_ORES = of("kyawthuite_ores");
    public static final TagKey<Block> TOPAZ_ORES = of("topaz_ores");
    public static final TagKey<Block> WHITE_TOPAZ_ORES = of("white_topaz_ores");
    public static final TagKey<Block> PERIDOT_ORES = of("peridot_ores");
    public static final TagKey<Block> JADE_ORES = of("jade_ores");
    public static final TagKey<Block> PYROPE_ORES = of("pyrope_ores");
    public static final TagKey<Block> CRYSTALLIZED_GEMSTONE_BLOCKS = of("crystallized_gemstone_ores");
    public static final TagKey<Block> ARCSHAPER_MINEABLE = of("mineable/arcshaper");

    private static TagKey<Block> of(String id) {
        return TagKey.of(RegistryKeys.BLOCK, Identifier.of(MoreOresModInitializer.MOD_ID, id));
    }
}
