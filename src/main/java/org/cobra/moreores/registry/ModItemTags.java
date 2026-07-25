package org.cobra.moreores.registry;

import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import org.cobra.moreores.MoreOresModInitializer;

public class ModItemTags {

    public static final TagKey<Item> GEMSTONE = of("rare/gemstone");
    public static final TagKey<Item> CRYSTALLIZED_GEMSTONES = of("rare/crystallized_gemstone");
    public static final TagKey<Item> CRYSTALLIZED = of("rare/crystallized");
    public static final TagKey<Item> GEMSTONE_BLOCKS = of("rare/gemstone_blocks");
    public static final TagKey<Item> CRYSTALLIZED_GEMSTONE_BLOCKS = of("rare/crystallized_gemstone_blocks");
    public static final TagKey<Item> RAW_GEMSTONE = of("rare/raw_gemstone");
    public static final TagKey<Item> RAW_GEMSTONE_BLOCKS = of("rare/raw_gemstone_blocks");
    public static final TagKey<Item> METAL = of("is_metal");
    public static final TagKey<Item> RARE = of("rare/rare");
    public static final TagKey<Item> REPAIRS_RUBY_ARMOR = of("repairs_ruby_armor");
    public static final TagKey<Item> REPAIRS_SAPPHIRE_ARMOR = of("repairs_sapphire_armor");
    public static final TagKey<Item> REPAIRS_RADIANT_ARMOR = of("repairs_radiant_armor");
    public static final TagKey<Item> RUBY_TOOL_MATERIALS = of("ruby_tool_materials");
    public static final TagKey<Item> SAPPHIRE_TOOL_MATERIALS = of("sapphire_tool_materials");
    public static final TagKey<Item> RADIANT_TOOL_MATERIALS = of("radiant_tool_materials");
//    public static final TagKey<Item> ARCSHAPERS = of("enchantable/arcshapers");
    public static final TagKey<Item> HAS_ENERGY = of("machine/has_energy");

    private static TagKey<Item> of(String id) {
        return TagKey.of(RegistryKeys.ITEM, Identifier.of(MoreOresModInitializer.MOD_ID, id));
    }
}
