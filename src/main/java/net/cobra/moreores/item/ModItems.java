package net.cobra.moreores.item;

import net.cobra.moreores.MoreOresModInitializer;
import net.cobra.moreores.block.ModBlocks;
import net.cobra.moreores.block.jukebox.ModJukeboxSongs;
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
import team.reborn.energy.api.EnergyStorage;

import static net.cobra.moreores.MoreOresModInitializer.getId;

public class ModItems {


    //Seeds
    public static final Item TOMATO_SEEDS = register("tomato_seeds", new AliasedBlockItem("tomato_seeds", ModBlocks.TOMATO_CROP));
    public static final Item PINEAPPLE_SEEDS = register("pineapple_seeds", new AliasedBlockItem("pineapple_seeds", ModBlocks.PINEAPPLE_CROP));


    //Foods
    public static final Item PINEAPPLE = register("pineapple", new Item(new Item.Settings().food(ModFoods.PINEAPPLE, ModConsumableComponents.PINEAPPLE).registryKey(RegistryKey.of(RegistryKeys.ITEM, getId("pineapple")))));
    public static final Item TOMATO = register("tomato", new Item(new Item.Settings().food(ModFoods.TOMATO).registryKey(RegistryKey.of(RegistryKeys.ITEM, getId("tomato")))));
    public static final Item DIAMOND_APPLE = register("diamond_apple", new Item(new Item.Settings().food(ModFoods.DIAMOND_APPLE, ModConsumableComponents.DIAMOND_APPLE).rarity(Rarity.EPIC).registryKey(RegistryKey.of(RegistryKeys.ITEM, getId("diamond_apple")))));


    //Gemstones & Ingots
    public static final Item RUBY = register("ruby", new Item(new Item.Settings().fireproof().trimMaterial(ModArmorTrimMaterials.RUBY).rarity(Rarity.RARE).registryKey(RegistryKey.of(RegistryKeys.ITEM, getId("ruby")))));
    public static final Item RAW_RUBY = register("raw_ruby", new Item(new Item.Settings().fireproof().rarity(Rarity.RARE).registryKey(RegistryKey.of(RegistryKeys.ITEM, getId("raw_ruby")))));
    public static final Item RADIANT = register("radiant", new Item(new Item.Settings().fireproof().trimMaterial(ModArmorTrimMaterials.RADIANT).rarity(Rarity.RARE).registryKey(RegistryKey.of(RegistryKeys.ITEM, getId("radiant")))));
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


    //Music Discs
    public static final Item MUSIC_DISC_TASWELL = register("music_disc_taswell", new Item(new Item.Settings().jukeboxPlayable(ModJukeboxSongs.TASWELL).rarity(Rarity.RARE).maxCount(1).registryKey(RegistryKey.of(RegistryKeys.ITEM, getId("music_disc_taswell")))));
    public static final Item MUSIC_DISC_DREITON = register("music_disc_dreiton", new Item(new Item.Settings().jukeboxPlayable(ModJukeboxSongs.DREITON).rarity(Rarity.RARE).maxCount(1).registryKey(RegistryKey.of(RegistryKeys.ITEM, getId("music_disc_dreiton")))));
    public static final Item MUSIC_DISC_BIOME_FEST = register("music_disc_biome_fest", new Item(new Item.Settings().jukeboxPlayable(ModJukeboxSongs.BIOME_FEST).rarity(Rarity.RARE).maxCount(1).registryKey(RegistryKey.of(RegistryKeys.ITEM, getId("music_disc_biome_fest")))));
    public static final Item MUSIC_DISC_ARIA_MATH = register("music_disc_aria_math", new Item(new Item.Settings().jukeboxPlayable(ModJukeboxSongs.ARIA_MATH).rarity(Rarity.RARE).maxCount(1).registryKey(RegistryKey.of(RegistryKeys.ITEM, getId("music_disc_aria_math")))));
    public static final Item MUSIC_DISC_INFINITE_AMETHYST = register("music_disc_infinite_amethyst", new Item(new Item.Settings().jukeboxPlayable(ModJukeboxSongs.INFINITE_AMETHYST).rarity(Rarity.RARE).maxCount(1).registryKey(RegistryKey.of(RegistryKeys.ITEM, getId("music_disc_infinite_amethyst")))));
    public static final Item MUSIC_DISC_ENDLESS = register("music_disc_endless", new Item(new Item.Settings().jukeboxPlayable(ModJukeboxSongs.ENDLESS).rarity(Rarity.RARE).maxCount(1).registryKey(RegistryKey.of(RegistryKeys.ITEM, getId("music_disc_endless")))));
    public static final Item MUSIC_DISC_FEATHERFALL = register("music_disc_featherfall", new Item(new Item.Settings().jukeboxPlayable(ModJukeboxSongs.FEATHERFALL).rarity(Rarity.RARE).maxCount(1).registryKey(RegistryKey.of(RegistryKeys.ITEM, getId("music_disc_featherfall")))));
    public static final Item MUSIC_DISC_DEEPER = register("music_disc_deeper", new Item(new Item.Settings().jukeboxPlayable(ModJukeboxSongs.DEEPER).rarity(Rarity.RARE).maxCount(1).registryKey(RegistryKey.of(RegistryKeys.ITEM, getId("music_disc_deeper")))));
    public static final Item MUSIC_DISC_WATCHER = register("music_disc_watcher", new Item(new Item.Settings().jukeboxPlayable(ModJukeboxSongs.WATCHER).rarity(Rarity.RARE).maxCount(1).registryKey(RegistryKey.of(RegistryKeys.ITEM, getId("music_disc_watcher")))));


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


    // Sapphire Armor
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


    //    Sapphire Tools & Weapons
    public static final Item SAPPHIRE_SWORD = register("sapphire_sword", new Item(new Item.Settings().sword(ModToolMaterials.SAPPHIRE,  8, -2.0f).fireproof().registryKey(RegistryKey.of(RegistryKeys.ITEM, getId("sapphire_sword")))));
    public static final Item SAPPHIRE_PICKAXE = register("sapphire_pickaxe", new Item(new Item.Settings().pickaxe(ModToolMaterials.SAPPHIRE,  4, -3.0f).fireproof().registryKey(RegistryKey.of(RegistryKeys.ITEM, getId("sapphire_pickaxe")))));
    public static final Item SAPPHIRE_AXE = register("sapphire_axe", new AxeItem(ModToolMaterials.SAPPHIRE,  8, -2.0f, new Item.Settings().fireproof().registryKey(RegistryKey.of(RegistryKeys.ITEM, getId("sapphire_axe")))));
    public static final Item SAPPHIRE_HOE = register("sapphire_hoe", new HoeItem(ModToolMaterials.SAPPHIRE,  4, -3.0f, new Item.Settings().fireproof().registryKey(RegistryKey.of(RegistryKeys.ITEM, getId("sapphire_hoe")))));
    public static final Item SAPPHIRE_SHOVEL = register("sapphire_shovel", new ShovelItem(ModToolMaterials.SAPPHIRE,  3.5F, -3.0F, new Item.Settings().fireproof().registryKey(RegistryKey.of(RegistryKeys.ITEM, getId("sapphire_shovel")))));
    public static final Item SAPPHIRE_SPEAR = register("sapphire_spear", new Item(new Item.Settings().spear(ModToolMaterials.SAPPHIRE, 1.25F, 1.4F,
            0.3F, 1.5F, 6.0F, 4.5F, 5.1F, 7.65F, 4.6F).fireproof().registryKey(RegistryKey.of(RegistryKeys.ITEM, getId("sapphire_spear")))));


    //    Radiant Tools & Weapons
    public static final Item RADIANT_SWORD = register("radiant_sword", new Item(new Item.Settings().sword(ModToolMaterials.RADIANT,  32, -1.0f).rarity(Rarity.EPIC).fireproof().registryKey(RegistryKey.of(RegistryKeys.ITEM, getId("radiant_sword")))));


    //    Smithing Templates
    public static final Item RUBY_UPGRADE_SMITHING_TEMPLATE = register("ruby_upgrade_smithing_template", ModSmithingTemplateItem.createRubyUpgrade(new Item.Settings().rarity(Rarity.UNCOMMON).registryKey(RegistryKey.of(RegistryKeys.ITEM, getId("ruby_upgrade_smithing_template")))));
    public static final Item RADIANT_UPGRADE_SMITHING_TEMPLATE = register("radiant_upgrade_smithing_template", ModSmithingTemplateItem.createRadiantUpgrade(new Item.Settings().rarity(Rarity.UNCOMMON).registryKey(RegistryKey.of(RegistryKeys.ITEM, getId("radiant_upgrade_smithing_template")))));
    public static final Item GUARDIAN_ARMOR_TRIM_SMITHING_TEMPLATE = register("guardian_armor_trim_smithing_template",
            SmithingTemplateItem.of(new Item.Settings().rarity(Rarity.RARE).registryKey(MoreOresModInitializer.setRegistryKey("guardian_armor_trim_smithing_template"))));

    public static Item register(String id, Item item) {
        return Registry.register(Registries.ITEM, getId(id), item);
    }

    public static void register() {
        MoreOresModInitializer.LOGGER.info("Loading ModItems for " + MoreOresModInitializer.MOD_ID + " mod.");
    }
}