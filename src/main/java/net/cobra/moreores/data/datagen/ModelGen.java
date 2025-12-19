package net.cobra.moreores.data.datagen;

import net.cobra.moreores.block.ModBlocks;
import net.cobra.moreores.block.PineappleCropBlock;
import net.cobra.moreores.block.RubyLampBlock;
import net.cobra.moreores.block.TomatoCropBlock;
import net.cobra.moreores.item.ModItems;
import net.cobra.moreores.item.equipment.ModEquipmentAssetKeys;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.*;
import net.minecraft.client.render.model.json.ModelVariant;
import net.minecraft.client.render.model.json.WeightedVariant;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.Pool;

public class ModelGen extends FabricModelProvider {
    public ModelGen(FabricDataOutput output) {
        super(output);
    }
    
    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RUBY_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RADIANT_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SAPPHIRE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.GREEN_SAPPHIRE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.BLUE_GARNET_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PINK_GARNET_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.GREEN_GARNET_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.TOPAZ_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.WHITE_TOPAZ_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PYROPE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.JADE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PERIDOT_BLOCK);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RUBY_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SAPPHIRE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.GREEN_SAPPHIRE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.BLUE_GARNET_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PINK_GARNET_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.GREEN_GARNET_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.TOPAZ_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.WHITE_TOPAZ_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PYROPE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.JADE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PERIDOT_ORE);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_RUBY_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_SAPPHIRE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_GREEN_SAPPHIRE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_BLUE_GARNET_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_PINK_GARNET_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_GREEN_GARNET_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_TOPAZ_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_WHITE_TOPAZ_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_PYROPE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_JADE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_PERIDOT_ORE);

        blockStateModelGenerator.registerCrop(ModBlocks.TOMATO_CROP, TomatoCropBlock.AGE, 0, 1, 2, 3, 4, 5);
        blockStateModelGenerator.registerCrop(ModBlocks.PINEAPPLE_CROP, PineappleCropBlock.AGE, 0, 1, 2, 3, 4);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ENERGY_BLOCK);

        Identifier lampOffIdentifier = TexturedModel.CUBE_ALL.upload(ModBlocks.RUBY_LAMP, blockStateModelGenerator.modelCollector);
        Identifier lampOnIdentifier = blockStateModelGenerator.createSubModel(ModBlocks.RUBY_LAMP, "_on", Models.CUBE_ALL, TextureMap::all);
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockModelDefinitionCreator.of(ModBlocks.RUBY_LAMP)
                .with(BlockStateModelGenerator.createBooleanModelMap(RubyLampBlock.LIT,
                        new WeightedVariant(Pool.<ModelVariant>builder().add(new ModelVariant(lampOnIdentifier)).build()),
                                new WeightedVariant(Pool.<ModelVariant>builder().add(new ModelVariant(lampOffIdentifier)).build()))));

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RAW_RUBY_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RAW_SAPPHIRE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RAW_GREEN_SAPPHIRE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RAW_BLUE_GARNET_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RAW_PINK_GARNET_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RAW_GREEN_GARNET_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RAW_TOPAZ_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RAW_WHITE_TOPAZ_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RAW_PERIDOT_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RAW_PYROPE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RAW_JADE_BLOCK);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.MUSIC_DISC_DEEPER, Models.TEMPLATE_MUSIC_DISC);
        itemModelGenerator.register(ModItems.MUSIC_DISC_WATCHER, Models.TEMPLATE_MUSIC_DISC);
        itemModelGenerator.register(ModItems.MUSIC_DISC_ARIA_MATH, Models.TEMPLATE_MUSIC_DISC);
        itemModelGenerator.register(ModItems.MUSIC_DISC_BIOME_FEST, Models.TEMPLATE_MUSIC_DISC);
        itemModelGenerator.register(ModItems.MUSIC_DISC_ENDLESS, Models.TEMPLATE_MUSIC_DISC);
        itemModelGenerator.register(ModItems.MUSIC_DISC_FEATHERFALL, Models.TEMPLATE_MUSIC_DISC);
        itemModelGenerator.register(ModItems.MUSIC_DISC_DREITON, Models.TEMPLATE_MUSIC_DISC);
        itemModelGenerator.register(ModItems.MUSIC_DISC_TASWELL, Models.TEMPLATE_MUSIC_DISC);
        itemModelGenerator.register(ModItems.MUSIC_DISC_INFINITE_AMETHYST, Models.TEMPLATE_MUSIC_DISC);

        itemModelGenerator.register(ModItems.RUBY, Models.GENERATED);
        itemModelGenerator.register(ModItems.RADIANT, Models.GENERATED);
        itemModelGenerator.register(ModItems.SAPPHIRE, Models.GENERATED);
        itemModelGenerator.register(ModItems.GREEN_SAPPHIRE, Models.GENERATED);
        itemModelGenerator.register(ModItems.BLUE_GARNET, Models.GENERATED);
        itemModelGenerator.register(ModItems.PINK_GARNET, Models.GENERATED);
        itemModelGenerator.register(ModItems.GREEN_GARNET, Models.GENERATED);
        itemModelGenerator.register(ModItems.TOPAZ, Models.GENERATED);
        itemModelGenerator.register(ModItems.WHITE_TOPAZ, Models.GENERATED);
        itemModelGenerator.register(ModItems.PERIDOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.JADE, Models.GENERATED);
        itemModelGenerator.register(ModItems.PYROPE, Models.GENERATED);

        itemModelGenerator.register(ModItems.RAW_RUBY, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_SAPPHIRE, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_GREEN_SAPPHIRE, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_BLUE_GARNET, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_PINK_GARNET, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_GREEN_GARNET, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_TOPAZ, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_WHITE_TOPAZ, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_PERIDOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_JADE, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_PYROPE, Models.GENERATED);

        itemModelGenerator.register(ModItems.RUBY_UPGRADE_SMITHING_TEMPLATE, Models.GENERATED);
        itemModelGenerator.register(ModItems.GUARDIAN_ARMOR_TRIM_SMITHING_TEMPLATE, Models.GENERATED);
        itemModelGenerator.register(ModItems.RADIANT_UPGRADE_SMITHING_TEMPLATE, Models.GENERATED);

        itemModelGenerator.register(ModItems.ENERGY_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.WOOD_PELLET, Models.GENERATED);

        itemModelGenerator.register(ModItems.PINEAPPLE, Models.GENERATED);
        itemModelGenerator.register(ModItems.TOMATO, Models.GENERATED);
        itemModelGenerator.register(ModItems.DIAMOND_APPLE, Models.GENERATED);

        itemModelGenerator.register(ModItems.RADIANT_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.RUBY_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.RUBY_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.RUBY_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.RUBY_HOE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.RUBY_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.SAPPHIRE_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.SAPPHIRE_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.SAPPHIRE_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.SAPPHIRE_HOE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.SAPPHIRE_SHOVEL, Models.HANDHELD);

        itemModelGenerator.registerArmor(ModItems.RUBY_HELMET, ModEquipmentAssetKeys.RUBY,  ItemModelGenerator.HELMET_TRIM_ID_PREFIX, false);
        itemModelGenerator.registerArmor(ModItems.RUBY_CHESTPLATE, ModEquipmentAssetKeys.RUBY, ItemModelGenerator.CHESTPLATE_TRIM_ID_PREFIX, false);
        itemModelGenerator.registerArmor(ModItems.RUBY_LEGGINGS, ModEquipmentAssetKeys.RUBY,  ItemModelGenerator.LEGGINGS_TRIM_ID_PREFIX, false);
        itemModelGenerator.registerArmor(ModItems.RUBY_BOOTS, ModEquipmentAssetKeys.RUBY, ItemModelGenerator.BOOTS_TRIM_ID_PREFIX, false);
        itemModelGenerator.registerArmor(ModItems.SAPPHIRE_HELMET, ModEquipmentAssetKeys.SAPPHIRE, ItemModelGenerator.HELMET_TRIM_ID_PREFIX, false);
        itemModelGenerator.registerArmor(ModItems.SAPPHIRE_CHESTPLATE,  ModEquipmentAssetKeys.SAPPHIRE, ItemModelGenerator.CHESTPLATE_TRIM_ID_PREFIX, false);
        itemModelGenerator.registerArmor(ModItems.SAPPHIRE_LEGGINGS,  ModEquipmentAssetKeys.SAPPHIRE, ItemModelGenerator.LEGGINGS_TRIM_ID_PREFIX, false);
        itemModelGenerator.registerArmor(ModItems.SAPPHIRE_BOOTS, ModEquipmentAssetKeys.SAPPHIRE,  ItemModelGenerator.BOOTS_TRIM_ID_PREFIX, false);
    }
}