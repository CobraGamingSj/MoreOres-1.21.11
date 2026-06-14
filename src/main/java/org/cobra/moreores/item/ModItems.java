package org.cobra.moreores.item;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.SmithingTemplateItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import org.cobra.moreores.MoreOresModInitializer;
import org.cobra.moreores.item.equipment.ArmorItem;
import org.cobra.moreores.item.equipment.ModArmorMaterials;

import java.util.function.Function;

import static org.cobra.moreores.MoreOresModInitializer.*;

public class ModItems {


    public static final Item GEM_DETECTOR = register("gem_detector", GemDetector::new);

    //Gemstones & Ingots
    public static final Item RUBY = register("ruby", s -> new GemItem(s, "ruby"));
    public static final Item RAW_RUBY = register("raw_ruby", s -> new Item(s.fireproof()));
    public static final Item RADIANT = register("radiant", s -> new GemItem(s, "radiant"));
    public static final Item RADIANT_DUST = register("radiant_dust", s -> new Item(s.rarity(Rarity.EPIC).fireproof()));
    public static final Item SAPPHIRE = register("sapphire", s -> new GemItem(s, "sapphire"));
    public static final Item RAW_SAPPHIRE = register("raw_sapphire", Item::new);
    public static final Item GREEN_SAPPHIRE = register("green_sapphire", s -> new GemItem(s, "green_sapphire"));
    public static final Item RAW_GREEN_SAPPHIRE = register("raw_green_sapphire", Item::new);
    public static final Item PINK_GARNET = register("pink_garnet", s -> new GemItem(s, "pink_garnet"));
    public static final Item RAW_PINK_GARNET = register("raw_pink_garnet", Item::new);
    public static final Item BLUE_GARNET = register("blue_garnet", s -> new GemItem(s, "blue_garnet"));
    public static final Item RAW_BLUE_GARNET = register("raw_blue_garnet", Item::new);
    public static final Item GREEN_GARNET = register("green_garnet", s -> new GemItem(s,  "green_garnet"));
    public static final Item RAW_GREEN_GARNET = register("raw_green_garnet", Item::new);
    public static final Item KYAWTHUITE = register("kyawthuite", s -> new GemItem(s, "kyawthuite"));
    public static final Item RAW_KYAWTHUITE = register("raw_kyawthuite", Item::new);
    public static final Item TOPAZ = register("topaz", s -> new GemItem(s, "topaz"));
    public static final Item RAW_TOPAZ = register("raw_topaz", Item::new);
    public static final Item PERIDOT = register("peridot", s -> new GemItem(s, "peridot"));
    public static final Item RAW_PERIDOT = register("raw_peridot", Item::new);
    public static final Item WHITE_TOPAZ = register("white_topaz", s -> new GemItem(s, "white_topaz"));
    public static final Item RAW_WHITE_TOPAZ = register("raw_white_topaz", Item::new);
    public static final Item PYROPE = register("pyrope", s -> new GemItem(s, "pyrope"));
    public static final Item RAW_PYROPE = register("raw_pyrope", Item::new);
    public static final Item JADE = register("jade", s -> new GemItem(s, "jade"));
    public static final Item RAW_JADE = register("raw_jade", Item::new);

    
    // New Gem Variants {Gem crystallizer}
    public static final Item CRIMSON_GARNET = register("crimson_garnet", s -> new GemItem(s, "crimson_garnet"));
    public static final Item CRYSTALLITE = register("crystallite", s -> new GemItem(s, "crystallite"));
    public static final Item RADIANT_AMETHYST = register("radiant_amethyst", s -> new GemItem(s, "radiant_amethyst"));
    public static final Item MOONSTONE = register("moonstone", s -> new GemItem(s, "moonstone"));
    public static final Item LIMESTONE = register("limestone", s -> new GemItem(s, "limestone"));
    public static final Item QUARTSIDIAN = register("quartsidian", s -> new GemItem(s, "quartsidian"));
    public static final Item ALEXANDRITE = register("alexandrite", s -> new GemItem(s, "alexandrite"));
    public static final Item ORANGE_ZIRCON = register("orange_zircon", Item::new);
    public static final Item OPAL = register("opal", s -> new GemItem(s, "opal"));
    public static final Item GRANDIDIERITE = register("grandidierite", Item::new);
    public static final Item RED_BERYL = register("red_beryl", s -> new GemItem(s, "red_beryl"));
    public static final Item KASHMIR_SAPPHIRE = register("kashmir_sapphire", Item::new);

    public static final Item ENERGY_INGOT = register("energy_ingot", settings ->  new EnergyIngotItem(settings.fireproof().rarity(Rarity.RARE)));


    //  Ruby Tools & Weapons
    public static final Item RUBY_SWORD = registerSword("ruby_sword", settings -> new Item(settings.fireproof()), 6, -2.1f, ModToolMaterials.RUBY);
    public static final Item RUBY_PICKAXE = registerPickaxe("ruby_pickaxe", settings ->  new Item(settings.fireproof()), 2, -3.0f, ModToolMaterials.RUBY);
    public static final Item RUBY_SHOVEL = registerShovel("ruby_shovel", settings ->  new Item(settings.fireproof()), 2.5F, -3.0F, ModToolMaterials.RUBY);
    public static final Item RUBY_AXE = registerAxe("ruby_axe", settings ->  new Item(settings.fireproof()), 6.0F, -2.1F, ModToolMaterials.RUBY);
    public static final Item RUBY_HOE = registerHoe("ruby_hoe", settings ->  new Item(settings.fireproof()), -5.0F, 0.0F, ModToolMaterials.RUBY);
    public static final Item RUBY_SPEAR = registerSpear("ruby_spear", settings -> new Item(settings.fireproof()), ModToolMaterials.RUBY,  1.2F, 
            1.3F, 0.35F,
                    2.0F, 6.5F, 5.0F, 
            5.1F, 8.0F, 4.6F);


    //Ruby Armor
    public static final Item RUBY_HELMET = register(
            "ruby_helmet",
            s -> new Item(s.armor(ModArmorMaterials.RUBY, EquipmentType.HELMET).fireproof())
    );
    public static final Item RUBY_CHESTPLATE = register(
            "ruby_chestplate",
            s -> new Item(s.armor(ModArmorMaterials.RUBY, EquipmentType.CHESTPLATE).fireproof())
    );
    public static final Item RUBY_LEGGINGS = register(
            "ruby_leggings",
            s -> new Item(s.armor(ModArmorMaterials.RUBY, EquipmentType.LEGGINGS).fireproof())
    );
    public static final Item RUBY_BOOTS = register(
            "ruby_boots",
            s -> new Item(s.armor(ModArmorMaterials.RUBY, EquipmentType.BOOTS).fireproof())
    );
    public static final Item RUBY_NAUTILUS_ARMOR = register(
            "ruby_nautilus_armor",
            s -> new Item(s.nautilusArmor(ModArmorMaterials.RUBY).fireproof())
    );


//    Sapphire Armor
    public static final Item SAPPHIRE_HELMET = register(
            "sapphire_helmet",
            s -> new Item(s.armor(ModArmorMaterials.SAPPHIRE, EquipmentType.HELMET).fireproof())
    );
    public static final Item SAPPHIRE_CHESTPLATE = register(
            "sapphire_chestplate",
            s -> new Item(s.armor(ModArmorMaterials.SAPPHIRE, EquipmentType.CHESTPLATE).fireproof())
    );
    public static final Item SAPPHIRE_LEGGINGS = register(
            "sapphire_leggings",
            s -> new Item(s.armor(ModArmorMaterials.SAPPHIRE, EquipmentType.LEGGINGS).fireproof())
    );
    public static final Item SAPPHIRE_BOOTS = register(
            "sapphire_boots",
            s -> new Item(s.armor(ModArmorMaterials.SAPPHIRE, EquipmentType.BOOTS).fireproof())
    );
    public static final Item SAPPHIRE_NAUTILUS_ARMOR = register(
            "sapphire_nautilus_armor",
            s -> new Item(s.nautilusArmor(ModArmorMaterials.SAPPHIRE).fireproof())
    );


//     Radiant Armor
    public static final Item RADIANT_HELMET = register(
            "radiant_helmet",
            s -> new ArmorItem(s.rarity(Rarity.EPIC).armor(ModArmorMaterials.RADIANT, EquipmentType.HELMET).fireproof())
    );
    public static final Item RADIANT_CHESTPLATE = register(
            "radiant_chestplate",
            s -> new ArmorItem(s.rarity(Rarity.EPIC).armor(ModArmorMaterials.RADIANT, EquipmentType.CHESTPLATE).fireproof())
    );
    public static final Item RADIANT_LEGGINGS = register(
            "radiant_leggings",
            s -> new ArmorItem(s.rarity(Rarity.EPIC).armor(ModArmorMaterials.RADIANT, EquipmentType.LEGGINGS).fireproof())
    );
    public static final Item RADIANT_BOOTS = register(
            "radiant_boots",
            s -> new ArmorItem(s.rarity(Rarity.EPIC).armor(ModArmorMaterials.RADIANT, EquipmentType.BOOTS).fireproof())
    );


    //Sapphire Tools & Weapons
    public static final Item SAPPHIRE_SWORD = registerSword(
            "sapphire_sword",
            s-> new Item(s.fireproof()),
            8, -2.0f, ModToolMaterials.SAPPHIRE);
    public static final Item SAPPHIRE_PICKAXE = registerPickaxe(
            "sapphire_pickaxe",
            s -> new Item(s.fireproof()),
            4, -3.0f, ModToolMaterials.SAPPHIRE);
    public static final Item SAPPHIRE_AXE = registerAxe(
            "sapphire_axe",
            s -> new Item(s.fireproof()),
            8, -2.0f, ModToolMaterials.SAPPHIRE);
    public static final Item SAPPHIRE_HOE = registerHoe(
            "sapphire_hoe",
            s -> new Item(s.fireproof()),
            4, -3.0f, ModToolMaterials.SAPPHIRE);
    public static final Item SAPPHIRE_SHOVEL = registerShovel(
            "sapphire_shovel",
            s -> new Item(s.fireproof()),
            3.5F, -3.0F, ModToolMaterials.SAPPHIRE);
    public static final Item SAPPHIRE_SPEAR = registerSpear("sapphire_spear", settings -> new Item(settings.fireproof()), ModToolMaterials.SAPPHIRE, 1.25F, 1.4F,
            0.3F, 1.5F, 6.0F, 4.5F, 
            5.1F, 7.65F, 4.6F);


    //    Radiant Tools & Weapons
    public static final Item RADIANT_SWORD = registerSword(
            "radiant_sword",
            s -> new Item(s.rarity(Rarity.EPIC).fireproof()),
            32, -1f, ModToolMaterials.RADIANT
    );
    public static final Item RADIANT_PICKAXE = registerPickaxe(
            "radiant_pickaxe",
            s -> new Item(s.rarity(Rarity.EPIC).fireproof()),
            20, -1.5f, ModToolMaterials.RADIANT
    );
    public static final Item RADIANT_AXE = registerAxe(
            "radiant_axe",
            s -> new Item(s.rarity(Rarity.EPIC).fireproof()),
            32, -1f, ModToolMaterials.RADIANT
    );
    public static final Item RADIANT_SHOVEL = registerShovel(
            "radiant_shovel",
            s -> new Item(s.rarity(Rarity.EPIC).fireproof()),
            16, -1.8f, ModToolMaterials.RADIANT
    );
    public static final Item RADIANT_HOE = registerHoe(
            "radiant_hoe",
            s -> new Item(s.rarity(Rarity.EPIC).fireproof()),
            12, -2.2f, ModToolMaterials.RADIANT
    );


    //    Smithing Templates
    public static final Item RUBY_UPGRADE_SMITHING_TEMPLATE = register("ruby_upgrade_smithing_template",
            s -> ModSmithingTemplateItem.createRubyUpgrade(s.rarity(Rarity.UNCOMMON)));
    public static final Item RADIANT_UPGRADE_SMITHING_TEMPLATE = register("radiant_upgrade_smithing_template",
            s -> ModSmithingTemplateItem.createRadiantUpgrade(s.rarity(Rarity.UNCOMMON)));
    public static final Item GUARDIAN_ARMOR_TRIM_SMITHING_TEMPLATE = register("guardian_armor_trim_smithing_template",
            s -> SmithingTemplateItem.of(s.rarity(Rarity.RARE)));

    
    // Bow & Arrow
    public static final Item RADIANT_BOW = register("radiant_bow", s -> new RadiantBowItem(s.maxDamage(1024).enchantable(2).maxCount(1).fireproof().rarity(Rarity.RARE)));
    public static final Item GEM_ARROW = register("gem_arrow", s -> new GemArrowItem(s.fireproof().rarity(Rarity.UNCOMMON)));
    
    
    // Eclipse Gem
    public static final Item ECLIPSE_GEM_CRYSTALS = register("eclipse_gem_crystals", s -> new Item(s.rarity(Rarity.UNCOMMON).fireproof()));
    public static final Item CRYSTAL_OF_ECLIPSE = register("crystal_of_eclipse", settings -> new Item(settings.rarity(Rarity.RARE).fireproof()));
    public static final Item ECLIPSE_GEM = register("eclipse_gem", settings -> new Item(settings.rarity(Rarity.EPIC).fireproof()));
    
    public static Item register(String id, Item item) {
        return Registry.register(Registries.ITEM, id(id), item);
    }

    public static Item register(String name, Function<Item.Settings, Item> item) {
        return register(name, item.apply(new Item.Settings().registryKey(itemKey(name))));
    }

    public static Item registerSword(String name, Function<Item.Settings, Item> item, float attackDamage, float attackSpeed, ToolMaterial material) {
        return register(name, item.apply(new Item.Settings().registryKey(itemKey(name)).sword(material, attackDamage, attackSpeed)));
    }

    public static Item registerPickaxe(String name, Function<Item.Settings, Item> item, float attackDamage, float attackSpeed, ToolMaterial material) {
        return register(name, item.apply(new Item.Settings().registryKey(itemKey(name)).pickaxe(material, attackDamage, attackSpeed)));
    }

    public static Item registerAxe(String name, Function<Item.Settings, Item> item, float attackDamage, float attackSpeed, ToolMaterial material) {
        return register(name, item.apply(new Item.Settings().registryKey(itemKey(name)).axe(material, attackDamage, attackSpeed)));
    }

    public static Item registerHoe(String name, Function<Item.Settings, Item> item, float attackDamage, float attackSpeed, ToolMaterial material) {
        return register(name, item.apply(new Item.Settings().registryKey(itemKey(name)).hoe(material, attackDamage, attackSpeed)));
    }

    public static Item registerShovel(String name, Function<Item.Settings, Item> item, float attackDamage, float attackSpeed, ToolMaterial material) {
        return register(name, item.apply(new Item.Settings().registryKey(itemKey(name)).shovel(material, attackDamage, attackSpeed)));
    }

    public static Item registerSpear(String name, Function<Item.Settings, Item> item, ToolMaterial material, float swingAnimationSeconds, float chargeDamageMultiplier, float chargeDelaySeconds, 
                                     float maxDurationForDismountSeconds, float minSpeedForDismount, float maxDurationForChargeKnockbackInSeconds, 
                                     float minSpeedForChargeKnockback, float maxDurationForChargeDamageInSeconds, 
                                     float minRelativeSpeedForChargeDamage) {
        return register(name, item.apply(new Item.Settings().spear(material, swingAnimationSeconds, chargeDamageMultiplier, chargeDelaySeconds, maxDurationForDismountSeconds,
                        minSpeedForDismount, maxDurationForChargeKnockbackInSeconds, minSpeedForChargeKnockback, maxDurationForChargeDamageInSeconds, minRelativeSpeedForChargeDamage)
                .registryKey(MoreOresModInitializer.itemKey(name))));
    }

    public static void register() {
        MoreOresModInitializer.LOGGER.info("Loading ModItems for " + MoreOresModInitializer.MOD_ID + " mod");
        int itemCount = 0;
        for(Item item : Registries.ITEM) {
            Identifier id = Registries.ITEM.getId(item);
            if(item instanceof BlockItem) {
                continue;
            }
            if(id.getNamespace().equals(MoreOresModInitializer.MOD_ID)) {
                String name = MoreOresModInitializer.formatName(id.getPath());
                if(item == GEM_DETECTOR) {
                    continue;
                }
                itemCount++;
                LOGGER.info("Registering Item: {}, for {} mod", name, MoreOresModInitializer.MOD_ID);
            }
        }
        LOGGER.info("Registered {} Items for {} mod", itemCount, MoreOresModInitializer.MOD_ID);
    }
}