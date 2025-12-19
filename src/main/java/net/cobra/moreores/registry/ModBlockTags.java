package net.cobra.moreores.registry;

import net.cobra.moreores.MoreOresModInitializer;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModBlockTags {

    public static final TagKey<Block> NEEDS_RUBY_TOOL = of("needs_ruby_tool");
    public static final TagKey<Block> INCORRECT_FOR_RUBY_TOOL = of("incorrect_for_ruby_tool");
    public static final TagKey<Block> NEEDS_SAPPHIRE_TOOL = of("needs_sapphire_tool");
    public static final TagKey<Block> INCORRECT_FOR_SAPPHIRE_TOOL = of("incorrect_for_sapphire_tool");
    public static final TagKey<Block> NEEDS_RADIANT_TOOL = of("needs_radiant_tool");
    public static final TagKey<Block> INCORRECT_FOR_RADIANT_TOOL = of("incorrect_for_radiant_tool");
    public static final TagKey<Block> MOD_ORES = ofVanilla("mod_ores");
    public static final TagKey<Block> RUBY_ORES = ofVanilla("ruby_ores");
    public static final TagKey<Block> SAPPHIRE_ORES = ofVanilla("sapphire_ores");
    public static final TagKey<Block> GREEN_SAPPHIRE_ORES = ofVanilla("green_sapphire_ores");
//    public static final TagKey<Block> BLUE_GARNET_ORES = ofVanilla("blue_garnet_ores");
//    public static final TagKey<Block> PINK_GARNET_ORES = ofVanilla("pink_garnet_ores");
//    public static final TagKey<Block> GREEN_GARNET_ORES = ofVanilla("green_garnet_ores");
//    public static final TagKey<Block> TOPAZ_ORES = ofVanilla("topaz_ores");
//    public static final TagKey<Block> WHITE_TOPAZ_ORES = ofVanilla("white_topaz_ores");
//    public static final TagKey<Block> PERIDOT_ORES = ofVanilla("peridot_ores");
//    public static final TagKey<Block> JADE_ORES = ofVanilla("jade_ores");
//    public static final TagKey<Block> PYROPE_ORES = ofVanilla("pyrope_ores");

    private static TagKey<Block> of(String id) {
        return TagKey.of(RegistryKeys.BLOCK, Identifier.of(MoreOresModInitializer.MOD_ID, id));
    }
    private static TagKey<Block> ofVanilla(String id) {
        return TagKey.of(RegistryKeys.BLOCK, Identifier.ofVanilla(id));
    }

}
