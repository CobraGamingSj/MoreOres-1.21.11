package net.cobra.moreores.item;

import net.cobra.moreores.MoreOresModInitializer;
import net.cobra.moreores.block.ModBlocks;
import net.cobra.moreores.component.type.ModConsumableComponents;
import net.cobra.moreores.item.equipment.ModArmorMaterials;
import net.cobra.moreores.item.equipment.trim.ModArmorTrimMaterials;
import net.minecraft.item.*;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Rarity;

import java.util.function.Function;

import static net.cobra.moreores.MoreOresModInitializer.getId;
import static net.cobra.moreores.MoreOresModInitializer.setRegistryKey;

public class ModItems {


    public static final Item GEM_DETECTOR = register("gem_detector", GemDetector::new);

    //Seeds
    public static final Item TOMATO_SEEDS = register("tomato_seeds", new AliasedBlockItem("tomato_seeds", ModBlocks.TOMATO_CROP));
    public static final Item PINEAPPLE_SEEDS = register("pineapple_seeds", new AliasedBlockItem("pineapple_seeds", ModBlocks.PINEAPPLE_CROP));


    //Foods
    public static final Item PINEAPPLE = register("pineapple", s -> new Item(s.food(ModFoods.PINEAPPLE, ModConsumableComponents.PINEAPPLE)));
    public static final Item TOMATO = register("tomato", s -> new Item(s.food(ModFoods.TOMATO)));
    public static final Item DIAMOND_APPLE = register("diamond_apple", s -> new Item(s.food(ModFoods.DIAMOND_APPLE, ModConsumableComponents.DIAMOND_APPLE).rarity(Rarity.EPIC)));


    //Gemstones & Ingots
    public static final Item RUBY = register("ruby", new Item(new Item.Settings().fireproof().trimMaterial(ModArmorTrimMaterials.RUBY).rarity(Rarity.RARE).registryKey(RegistryKey.of(RegistryKeys.ITEM, getId("ruby")))));
    public static final Item RAW_RUBY = register("raw_ruby", new Item(new Item.Settings().fireproof().rarity(Rarity.RARE).registryKey(RegistryKey.of(RegistryKeys.ITEM, getId("raw_ruby")))));
    public static final Item RADIANT = register("radiant", new Item(new Item.Settings().fireproof().trimMaterial(ModArmorTrimMaterials.RADIANT).rarity(Rarity.RARE).registryKey(RegistryKey.of(RegistryKeys.ITEM, getId("radiant")))));
    public static final Item RADIANT_DUST = register("radiant_dust", new Item(new Item.Settings().fireproof().trimMaterial(ModArmorTrimMaterials.RADIANT).rarity(Rarity.RARE).registryKey(RegistryKey.of(RegistryKeys.ITEM, getId("radiant_dust")))));
    public static final Item SAPPHIRE = register("sapphire", new Item(new Item.Settings().fireproof().trimMaterial(ModArmorTrimMaterials.SAPPHIRE).rarity(Rarity.RARE).registryKey(RegistryKey.of(RegistryKeys.ITEM, getId("sapphire")))));
    public static final Item RAW_SAPPHIRE = register("raw_sapphire", new Item(new Item.Settings().fireproof().rarity(Rarity.RARE).registryKey(RegistryKey.of(RegistryKeys.ITEM, getId("raw_sapphire")))));
    public static final Item GREEN_SAPPHIRE = register("green_sapphire", new Item(new Item.Settings().trimMaterial(ModArmorTrimMaterials.GREEN_SAPPHIRE).fireproof().rarity(Rarity.RARE).registryKey(RegistryKey.of(RegistryKeys.ITEM, getId("green_sapphire")))));
    public static final Item RAW_GREEN_SAPPHIRE = register("raw_green_sapphire", new Item(new Item.Settings().fireproof().rarity(Rarity.RARE).registryKey(RegistryKey.of(RegistryKeys.ITEM, getId("raw_green_sapphire")))));
    public static final Item PINK_GARNET = register("pink_garnet", new Item(new Item.Settings().fireproof().trimMaterial(ModArmorTrimMaterials.PINK_GARNET).rarity(Rarity.RARE).registryKey(RegistryKey.of(RegistryKeys.ITEM, getId("pink_garnet")))));
    public static final Item RAW_PINK_GARNET = register("raw_pink_garnet", new Item(new Item.Settings().fireproof().rarity(Rarity.RARE).registryKey(RegistryKey.of(RegistryKeys.ITEM, getId("raw_pink_garnet")))));
    public static final Item BLUE_GARNET = register("blue_garnet", new Item(new Item.Settings().fireproof().trimMaterial(ModArmorTrimMaterials.BLUE_GARNET).rarity(Rarity.RARE).registryKey(RegistryKey.of(RegistryKeys.ITEM, getId("blue_garnet")))));
    public static final Item RAW_BLUE_GARNET = register("raw_blue_garnet", new Item(new Item.Settings().fireproof().rarity(Rarity.RARE).registryKey(RegistryKey.of(RegistryKeys.ITEM, getId("raw_blue_garnet")))));
    public static final Item GREEN_GARNET = register("green_garnet", new Item(new Item.Settings().fireproof().trimMaterial(ModArmorTrimMaterials.GREEN_GARNET).rarity(Rarity.RARE).registryKey(RegistryKey.of(RegistryKeys.ITEM, getId("green_garnet")))));
    public static final Item RAW_GREEN_GARNET = register("raw_green_garnet", new Item(new Item.Settings().fireproof().rarity(Rarity.RARE).registryKey(RegistryKey.of(RegistryKeys.ITEM, getId("raw_green_garnet")))));
    public static final Item KYAWTHUITE = register("kyawthuite", new Item(new Item.Settings().trimMaterial(ModArmorTrimMaterials.KYAWTHUITE).fireproof().rarity(Rarity.RARE).registryKey(setRegistryKey("kyawthuite"))));
    public static final Item RAW_KYAWTHUITE = register("raw_kyawthuite", new Item(new Item.Settings().fireproof().rarity(Rarity.RARE).registryKey(setRegistryKey("raw_kyawthuite"))));
    public static final Item TOPAZ = register("topaz", new Item(new Item.Settings().fireproof().trimMaterial(ModArmorTrimMaterials.TOPAZ).rarity(Rarity.RARE).registryKey(RegistryKey.of(RegistryKeys.ITEM, getId("topaz")))));
    public static final Item RAW_TOPAZ = register("raw_topaz", new Item(new Item.Settings().fireproof().rarity(Rarity.RARE).registryKey(RegistryKey.of(RegistryKeys.ITEM, getId("raw_topaz")))));
    public static final Item PERIDOT = register("peridot", new Item(new Item.Settings().fireproof().trimMaterial(ModArmorTrimMaterials.PERIDOT).rarity(Rarity.RARE).registryKey(RegistryKey.of(RegistryKeys.ITEM, getId("peridot")))));
    public static final Item RAW_PERIDOT = register("raw_peridot", new Item(new Item.Settings().fireproof().rarity(Rarity.RARE).registryKey(RegistryKey.of(RegistryKeys.ITEM, getId("raw_peridot")))));
    public static final Item WHITE_TOPAZ = register("white_topaz", new Item(new Item.Settings().fireproof().trimMaterial(ModArmorTrimMaterials.WHITE_TOPAZ).rarity(Rarity.RARE).registryKey(RegistryKey.of(RegistryKeys.ITEM, getId("white_topaz")))));
    public static final Item RAW_WHITE_TOPAZ = register("raw_white_topaz", new Item(new Item.Settings().fireproof().rarity(Rarity.RARE).registryKey(RegistryKey.of(RegistryKeys.ITEM, getId("raw_white_topaz")))));
    public static final Item PYROPE = register("pyrope", new Item(new Item.Settings().fireproof().trimMaterial(ModArmorTrimMaterials.PYROPE).rarity(Rarity.RARE).registryKey(RegistryKey.of(RegistryKeys.ITEM, getId("pyrope")))));
    public static final Item RAW_PYROPE = register("raw_pyrope",new Item(new Item.Settings().fireproof().rarity(Rarity.RARE).registryKey(RegistryKey.of(RegistryKeys.ITEM, getId("raw_pyrope")))));
    public static final Item JADE = register("jade", new Item(new Item.Settings().fireproof().trimMaterial(ModArmorTrimMaterials.JADE).rarity(Rarity.RARE).registryKey(RegistryKey.of(RegistryKeys.ITEM, getId("jade")))));
    public static final Item RAW_JADE = register("raw_jade", new Item(new Item.Settings().fireproof().rarity(Rarity.RARE).registryKey(RegistryKey.of(RegistryKeys.ITEM, getId("raw_jade")))));
    public static final Item ENERGY_INGOT = register("energy_ingot", new EnergyIngotItem(new Item.Settings().fireproof().rarity(Rarity.RARE).registryKey(RegistryKey.of(RegistryKeys.ITEM, getId("energy_ingot")))));


    //Fuel
    public static final Item WOOD_PELLET = register("wood_pellet", new Item(new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, getId("wood_pellet")))));


    //  Ruby Tools & Weapons
    public static final Item RUBY_SWORD = register("ruby_sword", new Item(new Item.Settings().sword(ModToolMaterials.RUBY, 6, -2.1f).fireproof().registryKey(RegistryKey.of(RegistryKeys.ITEM, getId("ruby_sword")))));
    public static final Item RUBY_PICKAXE = register("ruby_pickaxe", new Item(new Item.Settings().pickaxe(ModToolMaterials.RUBY,  2, -3.0f).fireproof().registryKey(RegistryKey.of(RegistryKeys.ITEM, getId("ruby_pickaxe")))));
    public static final Item RUBY_SHOVEL = register("ruby_shovel", new ShovelItem(ModToolMaterials.RUBY,  2.5F, -3.0F, new Item.Settings().fireproof().registryKey(RegistryKey.of(RegistryKeys.ITEM, getId("ruby_shovel")))));
    public static final Item RUBY_AXE = register("ruby_axe", new AxeItem(ModToolMaterials.RUBY,  6.0F, -2.1F, new Item.Settings().fireproof().registryKey(RegistryKey.of(RegistryKeys.ITEM, getId("ruby_axe")))));
    public static final Item RUBY_HOE = register("ruby_hoe", new HoeItem(ModToolMaterials.RUBY,  -5.0F, 0.0F, new Item.Settings().fireproof().registryKey(RegistryKey.of(RegistryKeys.ITEM, getId("ruby_hoe")))));
    public static final Item RUBY_SPEAR = register("ruby_spear", new Item(new Item.Settings().spear(ModToolMaterials.RUBY,  1.2F, 1.3F, 0.35F,
                    2.0F, 6.5F, 5.0F, 5.1F, 8.0F, 4.6F)
            .fireproof().registryKey(RegistryKey.of(RegistryKeys.ITEM, getId("ruby_spear")))));


    //Ruby Armor
    public static final Item RUBY_HELMET = register(
            "ruby_helmet",
            new Item(new Item.Settings().armor(ModArmorMaterials.RUBY, EquipmentType.HELMET).fireproof().registryKey(RegistryKey.of(RegistryKeys.ITEM, getId("ruby_helmet"))))
    );
    public static final Item RUBY_CHESTPLATE = register(
            "ruby_chestplate",
            new Item(new Item.Settings().armor(ModArmorMaterials.RUBY, EquipmentType.CHESTPLATE).fireproof().registryKey(RegistryKey.of(RegistryKeys.ITEM, getId("ruby_chestplate"))))
    );
    public static final Item RUBY_LEGGINGS = register(
            "ruby_leggings",
            new Item(new Item.Settings().armor(ModArmorMaterials.RUBY, EquipmentType.LEGGINGS).fireproof().registryKey(RegistryKey.of(RegistryKeys.ITEM, getId("ruby_leggings"))))
    );
    public static final Item RUBY_BOOTS = register(
            "ruby_boots",
            new Item(new Item.Settings().armor(ModArmorMaterials.RUBY, EquipmentType.BOOTS).fireproof().registryKey(RegistryKey.of(RegistryKeys.ITEM, getId("ruby_boots"))))
    );
    public static final Item RUBY_NAUTILUS_ARMOR = register(
            "ruby_nautilus_armor",
            new Item(new Item.Settings().nautilusArmor(ModArmorMaterials.RUBY).fireproof().registryKey(RegistryKey.of(RegistryKeys.ITEM, getId("ruby_nautilus_armor"))))
    );


//    Sapphire Armor
    public static final Item SAPPHIRE_HELMET = register(
            "sapphire_helmet",
            new Item(new Item.Settings().armor(ModArmorMaterials.SAPPHIRE, EquipmentType.HELMET).fireproof().registryKey(RegistryKey.of(RegistryKeys.ITEM, getId("sapphire_helmet"))))
    );
    public static final Item SAPPHIRE_CHESTPLATE = register(
            "sapphire_chestplate",
            new Item(new Item.Settings().armor(ModArmorMaterials.SAPPHIRE, EquipmentType.CHESTPLATE).fireproof().registryKey(RegistryKey.of(RegistryKeys.ITEM, getId("sapphire_chestplate"))))
    );
    public static final Item SAPPHIRE_LEGGINGS = register(
            "sapphire_leggings",
            new Item(new Item.Settings().armor(ModArmorMaterials.SAPPHIRE, EquipmentType.LEGGINGS).fireproof().registryKey(RegistryKey.of(RegistryKeys.ITEM, getId("sapphire_leggings"))))
    );
    public static final Item SAPPHIRE_BOOTS = register(
            "sapphire_boots",
            new Item(new Item.Settings().armor(ModArmorMaterials.SAPPHIRE, EquipmentType.BOOTS).fireproof().registryKey(RegistryKey.of(RegistryKeys.ITEM, getId("sapphire_boots"))))
    );
    public static final Item SAPPHIRE_NAUTILUS_ARMOR = register(
            "sapphire_nautilus_armor",
            new Item(new Item.Settings().nautilusArmor(ModArmorMaterials.SAPPHIRE).fireproof().registryKey(RegistryKey.of(RegistryKeys.ITEM, getId("sapphire_nautilus_armor"))))
    );


//     Radiant Armor
    public static final Item RADIANT_HELMET = register(
            "radiant_helmet",
            s -> new Item(s.armor(ModArmorMaterials.RADIANT, EquipmentType.HELMET).fireproof())
    );
    public static final Item RADIANT_CHESTPLATE = register(
            "radiant_chestplate",
            s -> new Item(s.armor(ModArmorMaterials.RADIANT, EquipmentType.CHESTPLATE).fireproof())
    );
    public static final Item RADIANT_LEGGINGS = register(
            "radiant_leggings",
            s -> new Item(s.armor(ModArmorMaterials.RADIANT, EquipmentType.LEGGINGS).fireproof())
    );
    public static final Item RADIANT_BOOTS = register(
            "radiant_boots",
            s -> new Item(s.armor(ModArmorMaterials.RADIANT, EquipmentType.BOOTS).fireproof())
    );


    //Sapphire Tools & Weapons
    public static final Item SAPPHIRE_SWORD = register("sapphire_sword", new Item(new Item.Settings().sword(ModToolMaterials.SAPPHIRE,  8, -2.0f).fireproof().registryKey(RegistryKey.of(RegistryKeys.ITEM, getId("sapphire_sword")))));
    public static final Item SAPPHIRE_PICKAXE = register("sapphire_pickaxe", new Item(new Item.Settings().pickaxe(ModToolMaterials.SAPPHIRE,  4, -3.0f).fireproof().registryKey(RegistryKey.of(RegistryKeys.ITEM, getId("sapphire_pickaxe")))));
    public static final Item SAPPHIRE_AXE = register("sapphire_axe", new AxeItem(ModToolMaterials.SAPPHIRE,  8, -2.0f, new Item.Settings().fireproof().registryKey(RegistryKey.of(RegistryKeys.ITEM, getId("sapphire_axe")))));
    public static final Item SAPPHIRE_HOE = register("sapphire_hoe", new HoeItem(ModToolMaterials.SAPPHIRE,  4, -3.0f, new Item.Settings().fireproof().registryKey(RegistryKey.of(RegistryKeys.ITEM, getId("sapphire_hoe")))));
    public static final Item SAPPHIRE_SHOVEL = register("sapphire_shovel", new ShovelItem(ModToolMaterials.SAPPHIRE,  3.5F, -3.0F, new Item.Settings().fireproof().registryKey(RegistryKey.of(RegistryKeys.ITEM, getId("sapphire_shovel")))));
    public static final Item SAPPHIRE_SPEAR = register("sapphire_spear", new Item(new Item.Settings().spear(ModToolMaterials.SAPPHIRE, 1.25F, 1.4F,
            0.3F, 1.5F, 6.0F, 4.5F, 5.1F, 7.65F, 4.6F).fireproof().registryKey(RegistryKey.of(RegistryKeys.ITEM, getId("sapphire_spear")))));


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
    public static final Item RUBY_UPGRADE_SMITHING_TEMPLATE = register("ruby_upgrade_smithing_template", ModSmithingTemplateItem.createRubyUpgrade(new Item.Settings().rarity(Rarity.UNCOMMON).registryKey(RegistryKey.of(RegistryKeys.ITEM, getId("ruby_upgrade_smithing_template")))));
    public static final Item RADIANT_UPGRADE_SMITHING_TEMPLATE = register("radiant_upgrade_smithing_template", ModSmithingTemplateItem.createRadiantUpgrade(new Item.Settings().rarity(Rarity.UNCOMMON).registryKey(RegistryKey.of(RegistryKeys.ITEM, getId("radiant_upgrade_smithing_template")))));
    public static final Item GUARDIAN_ARMOR_TRIM_SMITHING_TEMPLATE = register("guardian_armor_trim_smithing_template",
            SmithingTemplateItem.of(new Item.Settings().rarity(Rarity.RARE).registryKey(setRegistryKey("guardian_armor_trim_smithing_template"))));

    public static Item register(String id, Item item) {
        return Registry.register(Registries.ITEM, getId(id), item);
    }

    public static Item register(String name, Function<Item.Settings, Item> item) {
        return Registry.register(Registries.ITEM, MoreOresModInitializer.getId(name), item.apply(new Item.Settings().registryKey(MoreOresModInitializer.setRegistryKey(name))));
    }

    public static Item registerSword(String name, Function<Item.Settings, Item> item, float attackDamage, float attackSpeed, ToolMaterial material) {
        return register(name, item.apply(new Item.Settings().registryKey(setRegistryKey(name)).sword(material, attackDamage, attackSpeed)));
    }

    public static Item registerPickaxe(String name, Function<Item.Settings, Item> item, float attackDamage, float attackSpeed, ToolMaterial material) {
        return register(name, item.apply(new Item.Settings().registryKey(setRegistryKey(name)).pickaxe(material, attackDamage, attackSpeed)));
    }

    public static Item registerAxe(String name, Function<Item.Settings, Item> item, float attackDamage, float attackSpeed, ToolMaterial material) {
        return register(name, item.apply(new Item.Settings().registryKey(setRegistryKey(name)).axe(material, attackDamage, attackSpeed)));
    }

    public static Item registerHoe(String name, Function<Item.Settings, Item> item, float attackDamage, float attackSpeed, ToolMaterial material) {
        return register(name, item.apply(new Item.Settings().registryKey(setRegistryKey(name)).hoe(material, attackDamage, attackSpeed)));
    }

    public static Item registerShovel(String name, Function<Item.Settings, Item> item, float attackDamage, float attackSpeed, ToolMaterial material) {
        return register(name, item.apply(new Item.Settings().registryKey(setRegistryKey(name)).shovel(material, attackDamage, attackSpeed)));
    }

//    public static Item registerSpear(String name, Function<Item.Settings, Item> item, float attackDamage, float attackSpeed, ToolMaterial material) {
//        return Registry.register(Registries.ITEM, MoreOresModInitializer.getId(name), item.apply(new Item.Settings().spear(material)
//                .registryKey(MoreOresModInitializer.setRegistryKey(name))));
//    }

    public static void register() {
        MoreOresModInitializer.LOGGER.info("Loading ModItems for " + MoreOresModInitializer.MOD_ID + " mod.");
    }
}