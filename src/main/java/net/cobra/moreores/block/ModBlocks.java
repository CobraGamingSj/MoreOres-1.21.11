package net.cobra.moreores.block;

import net.cobra.moreores.MoreOresModInitializer;
import net.cobra.moreores.sound.ModBlockSoundGroup;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class ModBlocks {

    public static final Block TOMATO_CROP = Registry.register(Registries.BLOCK, Identifier.of(MoreOresModInitializer.MOD_ID, "tomato_crop"),
            new TomatoCropBlock(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MoreOresModInitializer.MOD_ID, "tomato_crop"))).mapColor(MapColor.DARK_GREEN)
                    .noCollision()
                    .ticksRandomly()
                    .breakInstantly()
                    .sounds(BlockSoundGroup.CROP)
                    .pistonBehavior(PistonBehavior.DESTROY)));

    public static final Block PINEAPPLE_CROP = Registry.register(Registries.BLOCK, Identifier.of(MoreOresModInitializer.MOD_ID, "pineapple_crop"),
            new PineappleCropBlock(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MoreOresModInitializer.MOD_ID, "pineapple_crop"))).mapColor(MapColor.YELLOW)
                    .noCollision()
                    .ticksRandomly()
                    .breakInstantly()
                    .sounds(BlockSoundGroup.CROP)
                    .pistonBehavior(PistonBehavior.DESTROY)));

    public static final Block ENERGY_BLOCK = register("energy_block", new EnergyBlock(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MoreOresModInitializer.MOD_ID, "energy_block"))).mapColor(MapColor.BLUE).requiresTool().strength(256.0f, 512.0f).strength(512.0f).sounds(ModBlockSoundGroup.ENERGY_BLOCK).luminance((state) -> {
        return 30;
    })));
    public static final Block RUBY_LAMP = register("ruby_lamp", new RubyLampBlock(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MoreOresModInitializer.MOD_ID, "ruby_lamp"))).hardness(0.1f).sounds(BlockSoundGroup.GLASS).luminance(state -> state.get(RubyLampBlock.LIT) ? 15:0)));
    public static final Block RUBY_BLOCK = register("ruby_block", new Block(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MoreOresModInitializer.MOD_ID, "ruby_block"))).mapColor(MapColor.DARK_RED).requiresTool().strength(5.0f, 5.0f).strength(5.0f)));
    public static final Block RADIANT_BLOCK = register("radiant_block", new Block(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MoreOresModInitializer.MOD_ID, "radiant_block"))).mapColor(MapColor.BRIGHT_RED).requiresTool().strength(5.0f, 5.0f).strength(5.0f)));
    public static final Block SAPPHIRE_BLOCK = register("sapphire_block", new Block(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MoreOresModInitializer.MOD_ID, "sapphire_block"))).mapColor(MapColor.BLUE).requiresTool().strength(4.0f, 4.0f).strength(4.0f)));
    public static final Block GREEN_SAPPHIRE_BLOCK = register("green_sapphire_block", new Block(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MoreOresModInitializer.MOD_ID, "green_sapphire_block"))).mapColor(MapColor.GREEN).requiresTool().strength(4.0f, 4.0f).strength(4.0f)));
    public static final Block BLUE_GARNET_BLOCK = register("blue_garnet_block", new Block(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MoreOresModInitializer.MOD_ID, "blue_garnet_block"))).mapColor(MapColor.BLUE).requiresTool().strength(6.0f, 6.5f).strength(7.0f).sounds(BlockSoundGroup.AMETHYST_CLUSTER)));
    public static final Block PINK_GARNET_BLOCK = register("pink_garnet_block", new Block(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MoreOresModInitializer.MOD_ID, "pink_garnet_block"))).mapColor(MapColor.PINK).requiresTool().strength(6.0f, 6.5f).strength(7.0f).sounds(BlockSoundGroup.AMETHYST_CLUSTER)));
    public static final Block GREEN_GARNET_BLOCK = register("green_garnet_block", new Block(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MoreOresModInitializer.MOD_ID, "green_garnet_block"))).mapColor(MapColor.PINK).requiresTool().strength(6.0f, 6.5f).strength(7.0f).sounds(BlockSoundGroup.AMETHYST_CLUSTER)));
    public static final Block TOPAZ_BLOCK = register("topaz_block", new Block(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MoreOresModInitializer.MOD_ID, "topaz_block"))).mapColor(MapColor.ORANGE).requiresTool().strength(8.0f, 8.0f).strength(9.0f)));
    public static final Block WHITE_TOPAZ_BLOCK = register("white_topaz_block", new Block(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MoreOresModInitializer.MOD_ID, "white_topaz_block"))).requiresTool().strength(6.0f, 6.5f).strength(7.0f)));
    public static final Block PERIDOT_BLOCK = register("peridot_block", new Block(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MoreOresModInitializer.MOD_ID, "peridot_block"))).mapColor(MapColor.GREEN).requiresTool().strength(8.0f, 8.0f).strength(9.0f).sounds(BlockSoundGroup.METAL)));
    public static final Block JADE_BLOCK = register("jade_block", new Block(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MoreOresModInitializer.MOD_ID, "jade_block"))).requiresTool().strength(6.0f, 6.5f).strength(7.0f)));
    public static final Block PYROPE_BLOCK = register("pyrope_block", new Block(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MoreOresModInitializer.MOD_ID, "pyrope_block"))).requiresTool().strength(6.0f, 6.5f).strength(7.0f)));
    public static final Block GEM_PURIFIER_BLOCK = register("gem_purifier_block", new GemPurifierBlock(AbstractBlock.Settings.create()
            .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MoreOresModInitializer.MOD_ID, "gem_purifier_block"))).strength(5f).strength(2.75f, 3f)
            .luminance(state -> state.get(GemPurifierBlock.REDSTONE_POWERED) ? 5 : 0).requiresTool().nonOpaque().sounds(BlockSoundGroup.HEAVY_CORE)));

    public static final Block RAW_RUBY_BLOCK = register("raw_ruby_block", new Block(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MoreOresModInitializer.MOD_ID, "raw_ruby_block"))).mapColor(MapColor.DARK_RED).requiresTool().strength(6.0f, 6.0f).strength(6.0f)));
    public static final Block RAW_SAPPHIRE_BLOCK = register("raw_sapphire_block", new Block(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MoreOresModInitializer.MOD_ID, "raw_sapphire_block"))).mapColor(MapColor.BLUE).requiresTool().strength(5.0f, 5.0f).strength(5.0f)));
    public static final Block RAW_GREEN_SAPPHIRE_BLOCK = register("raw_green_sapphire_block", new Block(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MoreOresModInitializer.MOD_ID, "raw_green_sapphire_block"))).mapColor(MapColor.GREEN).requiresTool().strength(5.0f, 5.0f).strength(5.0f)));
    public static final Block RAW_BLUE_GARNET_BLOCK = register("raw_blue_garnet_block", new Block(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MoreOresModInitializer.MOD_ID, "raw_blue_garnet_block"))).mapColor(MapColor.BLUE).requiresTool().strength(7.0f, 7.5f).strength(8.0f).sounds(BlockSoundGroup.AMETHYST_CLUSTER)));
    public static final Block RAW_PINK_GARNET_BLOCK = register("raw_pink_garnet_block", new Block(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MoreOresModInitializer.MOD_ID, "raw_pink_garnet_block"))).mapColor(MapColor.PINK).requiresTool().strength(7.0f, 7.5f).strength(8.0f).sounds(BlockSoundGroup.AMETHYST_CLUSTER)));
    public static final Block RAW_GREEN_GARNET_BLOCK = register("raw_green_garnet_block", new Block(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MoreOresModInitializer.MOD_ID, "raw_green_garnet_block"))).mapColor(MapColor.PINK).requiresTool().strength(7.0f, 7.5f).strength(8.0f).sounds(BlockSoundGroup.AMETHYST_CLUSTER)));
    public static final Block RAW_TOPAZ_BLOCK = register("raw_topaz_block", new Block(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MoreOresModInitializer.MOD_ID, "raw_topaz_block"))).mapColor(MapColor.ORANGE).requiresTool().strength(9.0f, 9.0f).strength(10.0f)));
    public static final Block RAW_WHITE_TOPAZ_BLOCK = register("raw_white_topaz_block", new Block(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MoreOresModInitializer.MOD_ID, "raw_white_topaz_block"))).requiresTool().strength(7.0f, 7.5f).strength(8.0f)));
    public static final Block RAW_PERIDOT_BLOCK = register("raw_peridot_block", new Block(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MoreOresModInitializer.MOD_ID, "raw_peridot_block"))).mapColor(MapColor.GREEN).requiresTool().strength(9.0f, 9.0f).strength(10.0f).sounds(BlockSoundGroup.METAL)));
    public static final Block RAW_JADE_BLOCK = register("raw_jade_block", new Block(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MoreOresModInitializer.MOD_ID, "raw_jade_block"))).requiresTool().strength(7.0f, 7.5f).strength(8.0f)));
    public static final Block RAW_PYROPE_BLOCK = register("raw_pyrope_block", new Block(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MoreOresModInitializer.MOD_ID, "raw_pyrope_block"))).requiresTool().strength(7.0f, 7.5f).strength(8.0f)));

    public static final Block RUBY_ORE = register("ruby_ore", new ExperienceDroppingBlock(UniformIntProvider.create(2, 3), AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MoreOresModInitializer.MOD_ID, "ruby_ore"))).requiresTool().strength(6.0f, 6.0f).strength(6.0f).sounds(BlockSoundGroup.STONE).mapColor(MapColor.STONE_GRAY)));
    public static final Block DEEPSLATE_RUBY_ORE = register("deepslate_ruby_ore", new ExperienceDroppingBlock(UniformIntProvider.create(2, 3), AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MoreOresModInitializer.MOD_ID, "deepslate_ruby_ore"))).requiresTool().strength(6.5f, 6.5f).strength(6.5f).sounds(BlockSoundGroup.STONE).mapColor(MapColor.STONE_GRAY)));
    public static final Block SAPPHIRE_ORE = register("sapphire_ore", new ExperienceDroppingBlock(UniformIntProvider.create(2, 3), AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MoreOresModInitializer.MOD_ID, "sapphire_ore"))).requiresTool().strength(5.0f, 5.0f).strength(5.0f).sounds(BlockSoundGroup.STONE).mapColor(MapColor.STONE_GRAY)));
    public static final Block DEEPSLATE_SAPPHIRE_ORE = register("deepslate_sapphire_ore", new ExperienceDroppingBlock(UniformIntProvider.create(2, 3), AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MoreOresModInitializer.MOD_ID, "deepslate_sapphire_ore"))).requiresTool().strength(5.5f, 5.5f).strength(5.5f).sounds(BlockSoundGroup.STONE).mapColor(MapColor.STONE_GRAY)));
    public static final Block GREEN_SAPPHIRE_ORE = register("green_sapphire_ore", new ExperienceDroppingBlock(UniformIntProvider.create(2, 3), AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MoreOresModInitializer.MOD_ID, "green_sapphire_ore"))).requiresTool().strength(5.0f, 5.0f).strength(5.0f).sounds(BlockSoundGroup.STONE).mapColor(MapColor.STONE_GRAY)));
    public static final Block DEEPSLATE_GREEN_SAPPHIRE_ORE = register("deepslate_green_sapphire_ore", new ExperienceDroppingBlock(UniformIntProvider.create(2, 3), AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MoreOresModInitializer.MOD_ID, "deepslate_green_sapphire_ore"))).requiresTool().strength(5.5f, 5.5f).strength(5.5f).sounds(BlockSoundGroup.STONE).mapColor(MapColor.STONE_GRAY)));
    public static final Block BLUE_GARNET_ORE = register("blue_garnet_ore", new ExperienceDroppingBlock(UniformIntProvider.create(2, 3), AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MoreOresModInitializer.MOD_ID, "blue_garnet_ore"))).requiresTool().strength(7.0f, 7.5f).strength(8.0f).sounds(BlockSoundGroup.STONE).mapColor(MapColor.STONE_GRAY)));
    public static final Block DEEPSLATE_BLUE_GARNET_ORE = register("deepslate_blue_garnet_ore", new ExperienceDroppingBlock(UniformIntProvider.create(2, 3), AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MoreOresModInitializer.MOD_ID, "deepslate_blue_garnet_ore"))).requiresTool().strength(7.5f, 8.0f).strength(8.5f).sounds(BlockSoundGroup.STONE).mapColor(MapColor.STONE_GRAY)));
    public static final Block PINK_GARNET_ORE = register("pink_garnet_ore", new ExperienceDroppingBlock(UniformIntProvider.create(2, 3), AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MoreOresModInitializer.MOD_ID, "pink_garnet_ore"))).requiresTool().strength(7.0f, 7.5f).strength(8.0f).sounds(BlockSoundGroup.STONE).mapColor(MapColor.STONE_GRAY)));
    public static final Block DEEPSLATE_PINK_GARNET_ORE = register("deepslate_pink_garnet_ore", new ExperienceDroppingBlock(UniformIntProvider.create(2, 3), AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MoreOresModInitializer.MOD_ID, "deepslate_pink_garnet_ore"))).requiresTool().strength(7.5f, 8.0f).strength(8.5f).sounds(BlockSoundGroup.STONE).mapColor(MapColor.STONE_GRAY)));
    public static final Block GREEN_GARNET_ORE = register("green_garnet_ore", new ExperienceDroppingBlock(UniformIntProvider.create(2, 3), AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MoreOresModInitializer.MOD_ID, "green_garnet_ore"))).requiresTool().strength(7.0f, 7.5f).strength(8.0f).sounds(BlockSoundGroup.STONE).mapColor(MapColor.STONE_GRAY)));
    public static final Block DEEPSLATE_GREEN_GARNET_ORE = register("deepslate_green_garnet_ore", new ExperienceDroppingBlock(UniformIntProvider.create(2, 3), AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MoreOresModInitializer.MOD_ID, "deepslate_green_garnet_ore"))).requiresTool().strength(7.5f, 8.0f).strength(8.5f).sounds(BlockSoundGroup.STONE).mapColor(MapColor.STONE_GRAY)));
    public static final Block TOPAZ_ORE = register("topaz_ore", new ExperienceDroppingBlock(UniformIntProvider.create(1, 3), AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MoreOresModInitializer.MOD_ID, "topaz_ore"))).requiresTool().strength(9.0f, 9.0f).strength(10.0f).sounds(BlockSoundGroup.STONE).mapColor(MapColor.STONE_GRAY)));
    public static final Block DEEPSLATE_TOPAZ_ORE = register("deepslate_topaz_ore", new ExperienceDroppingBlock(UniformIntProvider.create(1, 3), AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MoreOresModInitializer.MOD_ID, "deepslate_topaz_ore"))).requiresTool().strength(9.5f, 9.5f).strength(10.5f).sounds(BlockSoundGroup.STONE).mapColor(MapColor.STONE_GRAY)));
    public static final Block WHITE_TOPAZ_ORE = register("white_topaz_ore", new ExperienceDroppingBlock(UniformIntProvider.create(3, 5), AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MoreOresModInitializer.MOD_ID, "white_topaz_ore"))).requiresTool().strength(9.5f, 9.5f).strength(10.5f).sounds(BlockSoundGroup.STONE).mapColor(MapColor.STONE_GRAY)));
    public static final Block DEEPSLATE_WHITE_TOPAZ_ORE = register("deepslate_white_topaz_ore", new ExperienceDroppingBlock(UniformIntProvider.create(3, 5), AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MoreOresModInitializer.MOD_ID, "deepslate_white_topaz_ore"))).requiresTool().strength(10.0f, 10.0f).strength(11.0f).sounds(BlockSoundGroup.STONE).mapColor(MapColor.STONE_GRAY)));
    public static final Block PERIDOT_ORE = register("peridot_ore", new ExperienceDroppingBlock(UniformIntProvider.create(1, 2), AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MoreOresModInitializer.MOD_ID, "peridot_ore"))).requiresTool().strength(9.0f, 9.0f).strength(10.0f).sounds(BlockSoundGroup.STONE).mapColor(MapColor.STONE_GRAY)));
    public static final Block DEEPSLATE_PERIDOT_ORE = register("deepslate_peridot_ore", new ExperienceDroppingBlock(UniformIntProvider.create(1, 2), AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MoreOresModInitializer.MOD_ID, "deepslate_peridot_ore"))).requiresTool().strength(9.5f, 9.5f).strength(10.5f).sounds(BlockSoundGroup.STONE).mapColor(MapColor.STONE_GRAY)));
    public static final Block JADE_ORE = register("jade_ore", new ExperienceDroppingBlock(UniformIntProvider.create(3, 5), AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MoreOresModInitializer.MOD_ID, "jade_ore"))).requiresTool().strength(10.0f, 10.0f).strength(11.0f).sounds(BlockSoundGroup.STONE).mapColor(MapColor.STONE_GRAY)));
    public static final Block DEEPSLATE_JADE_ORE = register("deepslate_jade_ore", new ExperienceDroppingBlock(UniformIntProvider.create(3, 5), AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MoreOresModInitializer.MOD_ID, "deepslate_jade_ore"))).requiresTool().strength(10.5f, 10.5f).strength(11.5f).sounds(BlockSoundGroup.STONE).mapColor(MapColor.STONE_GRAY)));
    public static final Block PYROPE_ORE = register("pyrope_ore", new ExperienceDroppingBlock(UniformIntProvider.create(3, 5), AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MoreOresModInitializer.MOD_ID, "pyrope_ore"))).requiresTool().strength(9.5f, 9.5f).strength(10.5f).sounds(BlockSoundGroup.STONE).mapColor(MapColor.STONE_GRAY)));
    public static final Block DEEPSLATE_PYROPE_ORE = register("deepslate_pyrope_ore", new ExperienceDroppingBlock(UniformIntProvider.create(3, 5), AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MoreOresModInitializer.MOD_ID, "deepslate_pyrope_ore"))).requiresTool().strength(10.0f, 10.0f).strength(11.0f).sounds(BlockSoundGroup.STONE).mapColor(MapColor.STONE_GRAY)));

    public static Block register(String id, Block block) {
        registerBlockItem(id, block);
        Identifier ID = Identifier.of(MoreOresModInitializer.MOD_ID, id);

        return Registry.register(Registries.BLOCK, ID, block);
    }

    public static void registerBlockItem(String id, Block block) {
        Identifier ID = Identifier.of(MoreOresModInitializer.MOD_ID, id);
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, ID);
        Registry.register(Registries.ITEM, ID, new BlockItem(block, new Item.Settings().registryKey(key).useBlockPrefixedTranslationKey()));
    }

    public static void register() {
        MoreOresModInitializer.LOGGER.info("Loading ModBlocks for " + MoreOresModInitializer.MOD_ID + " mod.");
    }

}