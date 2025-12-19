package net.cobra.moreores.registry;

import net.cobra.moreores.MoreOresModInitializer;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModItemTags {

    public static final TagKey<Item> GEMSTONE = of("rare/gemstone");
    public static final TagKey<Item> RAW_GEMSTONE = of("rare/raw_gemstone");
    public static final TagKey<Item> METAL = ofVanilla("is_metal");
    public static final TagKey<Item> RARE = ofVanilla("rare/rare");
    public static final TagKey<Item> JUKEBOX_PLAYABLE_DISCS = ofVanilla("jukebox_playable_discs");
    public static final TagKey<Item> REPAIRS_RUBY_ARMOR = ofVanilla("repairs_ruby_armor");
    public static final TagKey<Item> REPAIRS_SAPPHIRE_ARMOR = ofVanilla("repairs_sapphire_armor");
    public static final TagKey<Item> REPAIRS_RADIANT_ARMOR = ofVanilla("repairs_radiant_armor");
    public static final TagKey<Item> RUBY_TOOL_MATERIALS = ofVanilla("ruby_tool_materials");
    public static final TagKey<Item> SAPPHIRE_TOOL_MATERIALS = ofVanilla("sapphire_tool_materials");
    public static final TagKey<Item> RADIANT_TOOL_MATERIALS = ofVanilla("radiant_tool_materials");

    public static final TagKey<Item> ARCSHAPERS = ofVanilla("arcshapers");

    private static TagKey<Item> of(String id) {
        return TagKey.of(RegistryKeys.ITEM, Identifier.of(MoreOresModInitializer.MOD_ID, id));
    }
    private static TagKey<Item> ofVanilla(String id) {
        return TagKey.of(RegistryKeys.ITEM, Identifier.ofVanilla(id));
    }
}
