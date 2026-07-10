package org.cobra.moreores.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.block.MapColor;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import org.cobra.moreores.MoreOresModInitializer;
import org.cobra.moreores.item.ModItems;
import org.cobra.moreores.sound.ModBlockSoundGroup;

import java.util.function.Function;

import static org.cobra.moreores.MoreOresModInitializer.id;

public class ModBlocks {

    public static final Block ENERGY_BLOCK = register("energy_block", new EnergyBlock(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MoreOresModInitializer.MOD_ID, "energy_block"))).mapColor(MapColor.BLUE).requiresTool().strength(256.0f, 512.0f).sounds(ModBlockSoundGroup.ENERGY_BLOCK).luminance((state) -> {
        return 30;
    })));
    public static final Block RUBY_LAMP = register("ruby_lamp", new RubyLampBlock(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MoreOresModInitializer.MOD_ID, "ruby_lamp"))).hardness(0.1f).sounds(BlockSoundGroup.GLASS).luminance(state -> state.get(RubyLampBlock.LIT) ? 15:0)));

    public static final Block RUBY_BLOCK = register("ruby_block", new Block(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MoreOresModInitializer.MOD_ID, "ruby_block"))).mapColor(MapColor.DARK_RED).requiresTool().strength(5.0f, 5.0f).strength(5.0f)));

    public static final Block RADIANT_BLOCK = registerSolidBlock(
            "radiant_block", s -> new Block(
            s.requiresTool()), 5f, 5f);

    public static final Block SAPPHIRE_BLOCK = register("sapphire_block", new Block(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MoreOresModInitializer.MOD_ID, "sapphire_block"))).mapColor(MapColor.BLUE).requiresTool().strength(4.0f, 4.0f).strength(4.0f)));
    public static final Block GREEN_SAPPHIRE_BLOCK = register("green_sapphire_block", new Block(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MoreOresModInitializer.MOD_ID, "green_sapphire_block"))).mapColor(MapColor.GREEN).requiresTool().strength(4.0f, 4.0f).strength(4.0f)));
    public static final Block BLUE_GARNET_BLOCK = register("blue_garnet_block", new Block(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MoreOresModInitializer.MOD_ID, "blue_garnet_block"))).mapColor(MapColor.BLUE).requiresTool().strength(6.0f, 6.5f).strength(7.0f).sounds(BlockSoundGroup.AMETHYST_CLUSTER)));
    public static final Block PINK_GARNET_BLOCK = register("pink_garnet_block", new Block(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MoreOresModInitializer.MOD_ID, "pink_garnet_block"))).mapColor(MapColor.PINK).requiresTool().strength(6.0f, 6.5f).strength(7.0f).sounds(BlockSoundGroup.AMETHYST_CLUSTER)));
    public static final Block GREEN_GARNET_BLOCK = register("green_garnet_block", new Block(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MoreOresModInitializer.MOD_ID, "green_garnet_block"))).mapColor(MapColor.PINK).requiresTool().strength(6.0f, 6.5f).strength(7.0f).sounds(BlockSoundGroup.AMETHYST_CLUSTER)));
    public static final Block KYAWTHUITE_BLOCK = registerSolidBlock("kyawthuite_block", s -> new Block(s.requiresTool().mapColor(MapColor.ORANGE)), 5.5f, 6f);
    public static final Block TOPAZ_BLOCK = register("topaz_block", new Block(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MoreOresModInitializer.MOD_ID, "topaz_block"))).mapColor(MapColor.ORANGE).requiresTool().strength(8.0f, 8.0f).strength(9.0f)));
    public static final Block WHITE_TOPAZ_BLOCK = register("white_topaz_block", new Block(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MoreOresModInitializer.MOD_ID, "white_topaz_block"))).requiresTool().strength(6.0f, 6.5f).strength(7.0f)));
    public static final Block PERIDOT_BLOCK = register("peridot_block", new Block(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MoreOresModInitializer.MOD_ID, "peridot_block"))).mapColor(MapColor.GREEN).requiresTool().strength(8.0f, 8.0f).strength(9.0f).sounds(BlockSoundGroup.METAL)));
    public static final Block JADE_BLOCK = register("jade_block", new Block(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MoreOresModInitializer.MOD_ID, "jade_block"))).requiresTool().strength(6.0f, 6.5f).strength(7.0f)));
    public static final Block PYROPE_BLOCK = register("pyrope_block", new Block(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MoreOresModInitializer.MOD_ID, "pyrope_block"))).requiresTool().strength(6.0f, 6.5f).strength(7.0f)));

    public static final Block CRIMSON_GARNET_BLOCK = register("crimson_garnet_block", Block::new);
    public static final Block CRYSTALLITE_BLOCK = register("crystallite_block", Block::new);
    public static final Block RADIANT_AMETHYST_BLOCK = register("radiant_amethyst_block", Block::new);
    public static final Block MOONSTONE_BLOCK = register("moonstone_block", Block::new);
    public static final Block LIMESTONE_BLOCK = register("limestone_block", Block::new);
    public static final Block QUARTSIDIAN_BLOCK = register("quartsidian_block", Block::new);
    public static final Block ALEXANDRITE_BLOCK = register("alexandrite_block", Block::new);
    public static final Block ORANGE_ZIRCON_BLOCK = register("orange_zircon_block", Block::new);
    public static final Block OPAL_BLOCK = register("opal_block", Block::new);
    public static final Block GRANDIDIERITE_BLOCK = register("grandidierite_block", Block::new);
    public static final Block RED_BERYL_BLOCK = register("red_beryl_block", Block::new);
    public static final Block KASHMIR_SAPPHIRE_BLOCK = register("kashmir_sapphire_block", Block::new);

    public static final Block GEM_PURIFIER_BLOCK = register("gem_purifier_block", new GemPurifierBlock(AbstractBlock.Settings.create()
            .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MoreOresModInitializer.MOD_ID, "gem_purifier_block"))).strength(5f).strength(5f, 30f)
            .luminance(state -> state.get(GemPurifierBlock.REDSTONE_POWERED) ? 5 : 0).requiresTool().nonOpaque().sounds(BlockSoundGroup.HEAVY_CORE)));
    public static final Block GEM_CRYSTALLIZER_BLOCK = register("gem_crystallizer_block", new GemCrystallizerBlock(AbstractBlock.Settings.create()
            .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MoreOresModInitializer.MOD_ID, "gem_crystallizer_block"))).strength(5f).strength(5f, 30f)
            .luminance(state -> state.get(GemCrystallizerBlock.REDSTONE_POWERED) ? 5 : 0).requiresTool().nonOpaque().sounds(BlockSoundGroup.HEAVY_CORE)));

    public static final Block TEST_BLOCK = register("test_gem_block", TestGemBlock::new, AbstractBlock.Settings.create().nonOpaque());
    
    public static final Block RAW_RUBY_BLOCK = register("raw_ruby_block", new Block(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MoreOresModInitializer.MOD_ID, "raw_ruby_block"))).mapColor(MapColor.DARK_RED).requiresTool().strength(6.0f, 6.0f).strength(6.0f)));
    public static final Block RAW_SAPPHIRE_BLOCK = register("raw_sapphire_block", new Block(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MoreOresModInitializer.MOD_ID, "raw_sapphire_block"))).mapColor(MapColor.BLUE).requiresTool().strength(5.0f, 5.0f).strength(5.0f)));
    public static final Block RAW_GREEN_SAPPHIRE_BLOCK = register("raw_green_sapphire_block", new Block(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MoreOresModInitializer.MOD_ID, "raw_green_sapphire_block"))).mapColor(MapColor.GREEN).requiresTool().strength(5.0f, 5.0f).strength(5.0f)));
    public static final Block RAW_BLUE_GARNET_BLOCK = register("raw_blue_garnet_block", new Block(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MoreOresModInitializer.MOD_ID, "raw_blue_garnet_block"))).mapColor(MapColor.BLUE).requiresTool().strength(7.0f, 7.5f).strength(8.0f).sounds(BlockSoundGroup.AMETHYST_CLUSTER)));
    public static final Block RAW_PINK_GARNET_BLOCK = register("raw_pink_garnet_block", new Block(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MoreOresModInitializer.MOD_ID, "raw_pink_garnet_block"))).mapColor(MapColor.PINK).requiresTool().strength(7.0f, 7.5f).strength(8.0f).sounds(BlockSoundGroup.AMETHYST_CLUSTER)));
    public static final Block RAW_GREEN_GARNET_BLOCK = register("raw_green_garnet_block", new Block(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MoreOresModInitializer.MOD_ID, "raw_green_garnet_block"))).mapColor(MapColor.PINK).requiresTool().strength(7.0f, 7.5f).strength(8.0f).sounds(BlockSoundGroup.AMETHYST_CLUSTER)));
    public static final Block RAW_KYAWTHUITE_BLOCK = register("raw_kyawthuite_block", new Block(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MoreOresModInitializer.MOD_ID, "raw_kyawthuite_block"))).mapColor(MapColor.ORANGE).requiresTool().strength(9.5f, 10.0f).strength(10.0f)));
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
    public static final Block KYAWTHUITE_ORE = registerSolidBlock("kyawthuite_ore", s -> new ExperienceDroppingBlock(UniformIntProvider.create(1, 2), s.requiresTool().mapColor(MapColor.ORANGE)), 7.5f, 8f);
    public static final Block DEEPSLATE_KYAWTHUITE_ORE = registerSolidBlock("deepslate_kyawthuite_ore", s -> new ExperienceDroppingBlock(UniformIntProvider.create(1, 2), s.requiresTool().mapColor(MapColor.ORANGE)), 8f, 8.5f);
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

    public static final Block ECLIPSE_GEM_ORE = registerSolidBlock("eclipse_gem_ore", s -> new Block(s.mapColor(MapColor.STONE_GRAY).sounds(BlockSoundGroup.STONE)), 16f, 16f);
    
    public static Block register(String id, Block block) {
        registerBlockItem(id, block);
        return Registry.register(Registries.BLOCK, id(id), block);
    }

    public static Block register(String id, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) {
        return register(id, factory.apply(settings.registryKey(MoreOresModInitializer.blockKey(id))));
    }
    
    public static Block register(String id, Function<AbstractBlock.Settings, Block> blockFactory) {
       return registerSolidBlock(id, blockFactory, 7f, 7f);
    }

    public static Block registerSolidBlock(String id, Function<AbstractBlock.Settings, Block> blockFunction, float strength, float resistance) {
        AbstractBlock.Settings settings = AbstractBlock.Settings.create().requiresTool().registryKey(MoreOresModInitializer.blockKey(id)).strength(strength, resistance);
        return register(id, blockFunction.apply(settings));
    }

    public static void registerBlockItem(String id, Block block) {
        ModItems.register(id, settings -> new BlockItem(block, settings.useBlockPrefixedTranslationKey()));
    }

    public static void register() {
        MoreOresModInitializer.LOGGER.info("Loading ModBlocks for {} mod.", MoreOresModInitializer.MOD_ID);
        int blockCount = 0;
        for(Block block : Registries.BLOCK) {
            Identifier id = Registries.BLOCK.getId(block);
            if(id.getNamespace().equals(MoreOresModInitializer.MOD_ID)) {
                String name = MoreOresModInitializer.formatName((id.getPath()));
                blockCount++;
                MoreOresModInitializer.LOGGER.info("Registering Block: {}, for {} mod", name, MoreOresModInitializer.MOD_ID);
            }
        }
        MoreOresModInitializer.LOGGER.info("Registered {} Blocks for {} mod", blockCount, MoreOresModInitializer.MOD_ID);
    }
}