package net.cobra.moreores.data.datagen;

import net.cobra.moreores.block.*;
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
    public void generateBlockStateModels(BlockStateModelGenerator generator) {

        generator.registerSimpleCubeAll(ModBlocks.RUBY_BLOCK);

        generator.registerSimpleCubeAll(ModBlocks.RADIANT_BLOCK);
        generator.registerSimpleCubeAll(ModBlocks.EXPOSED_RADIANT_BLOCK);
        generator.registerSimpleCubeAll(ModBlocks.WEATHERED_RADIANT_BLOCK);
        generator.registerSimpleCubeAll(ModBlocks.CORRODED_RADIANT_BLOCK);

        generator.registerSimpleCubeAll(ModBlocks.SAPPHIRE_BLOCK);
        generator.registerSimpleCubeAll(ModBlocks.GREEN_SAPPHIRE_BLOCK);
        generator.registerSimpleCubeAll(ModBlocks.BLUE_GARNET_BLOCK);
        generator.registerSimpleCubeAll(ModBlocks.PINK_GARNET_BLOCK);
        generator.registerSimpleCubeAll(ModBlocks.GREEN_GARNET_BLOCK);
        generator.registerSimpleCubeAll(ModBlocks.KYAWTHUITE_BLOCK);
        generator.registerSimpleCubeAll(ModBlocks.TOPAZ_BLOCK);
        generator.registerSimpleCubeAll(ModBlocks.WHITE_TOPAZ_BLOCK);
        generator.registerSimpleCubeAll(ModBlocks.PYROPE_BLOCK);
        generator.registerSimpleCubeAll(ModBlocks.JADE_BLOCK);
        generator.registerSimpleCubeAll(ModBlocks.PERIDOT_BLOCK);

        generator.registerSimpleCubeAll(ModBlocks.RUBY_ORE);
        generator.registerSimpleCubeAll(ModBlocks.SAPPHIRE_ORE);
        generator.registerSimpleCubeAll(ModBlocks.GREEN_SAPPHIRE_ORE);
        generator.registerSimpleCubeAll(ModBlocks.BLUE_GARNET_ORE);
        generator.registerSimpleCubeAll(ModBlocks.PINK_GARNET_ORE);
        generator.registerSimpleCubeAll(ModBlocks.GREEN_GARNET_ORE);
        generator.registerSimpleCubeAll(ModBlocks.KYAWTHUITE_ORE);
        generator.registerSimpleCubeAll(ModBlocks.TOPAZ_ORE);
        generator.registerSimpleCubeAll(ModBlocks.WHITE_TOPAZ_ORE);
        generator.registerSimpleCubeAll(ModBlocks.PYROPE_ORE);
        generator.registerSimpleCubeAll(ModBlocks.JADE_ORE);
        generator.registerSimpleCubeAll(ModBlocks.PERIDOT_ORE);

        generator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_RUBY_ORE);
        generator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_SAPPHIRE_ORE);
        generator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_GREEN_SAPPHIRE_ORE);
        generator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_BLUE_GARNET_ORE);
        generator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_PINK_GARNET_ORE);
        generator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_GREEN_GARNET_ORE);
        generator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_KYAWTHUITE_ORE);
        generator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_TOPAZ_ORE);
        generator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_WHITE_TOPAZ_ORE);
        generator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_PYROPE_ORE);
        generator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_JADE_ORE);
        generator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_PERIDOT_ORE);

        generator.registerSimpleCubeAll(ModBlocks.ENERGY_BLOCK);

        Identifier lampOffIdentifier = TexturedModel.CUBE_ALL.upload(ModBlocks.RUBY_LAMP, generator.modelCollector);
        Identifier lampOnIdentifier = generator.createSubModel(ModBlocks.RUBY_LAMP, "_on", Models.CUBE_ALL, TextureMap::all);
        generator.blockStateCollector.accept(VariantsBlockModelDefinitionCreator.of(ModBlocks.RUBY_LAMP)
                .with(BlockStateModelGenerator.createBooleanModelMap(RubyLampBlock.LIT,
                        new WeightedVariant(Pool.<ModelVariant>builder().add(new ModelVariant(lampOnIdentifier)).build()),
                                new WeightedVariant(Pool.<ModelVariant>builder().add(new ModelVariant(lampOffIdentifier)).build()))));

        generator.registerSimpleCubeAll(ModBlocks.RAW_RUBY_BLOCK);
        generator.registerSimpleCubeAll(ModBlocks.RAW_SAPPHIRE_BLOCK);
        generator.registerSimpleCubeAll(ModBlocks.RAW_GREEN_SAPPHIRE_BLOCK);
        generator.registerSimpleCubeAll(ModBlocks.RAW_BLUE_GARNET_BLOCK);
        generator.registerSimpleCubeAll(ModBlocks.RAW_PINK_GARNET_BLOCK);
        generator.registerSimpleCubeAll(ModBlocks.RAW_GREEN_GARNET_BLOCK);
        generator.registerSimpleCubeAll(ModBlocks.RAW_TOPAZ_BLOCK);
        generator.registerSimpleCubeAll(ModBlocks.RAW_KYAWTHUITE_BLOCK);
        generator.registerSimpleCubeAll(ModBlocks.RAW_WHITE_TOPAZ_BLOCK);
        generator.registerSimpleCubeAll(ModBlocks.RAW_PERIDOT_BLOCK);
        generator.registerSimpleCubeAll(ModBlocks.RAW_PYROPE_BLOCK);
        generator.registerSimpleCubeAll(ModBlocks.RAW_JADE_BLOCK);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

        itemModelGenerator.register(ModItems.RUBY, Models.GENERATED);
        itemModelGenerator.register(ModItems.RADIANT, Models.GENERATED);
        itemModelGenerator.register(ModItems.RADIANT_DUST, Models.GENERATED);
        itemModelGenerator.register(ModItems.SAPPHIRE, Models.GENERATED);
        itemModelGenerator.register(ModItems.GREEN_SAPPHIRE, Models.GENERATED);
        itemModelGenerator.register(ModItems.BLUE_GARNET, Models.GENERATED);
        itemModelGenerator.register(ModItems.PINK_GARNET, Models.GENERATED);
        itemModelGenerator.register(ModItems.GREEN_GARNET, Models.GENERATED);
        itemModelGenerator.register(ModItems.KYAWTHUITE, Models.GENERATED);
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
        itemModelGenerator.register(ModItems.RAW_KYAWTHUITE, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_TOPAZ, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_WHITE_TOPAZ, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_PERIDOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_JADE, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_PYROPE, Models.GENERATED);

        itemModelGenerator.register(ModItems.RUBY_UPGRADE_SMITHING_TEMPLATE, Models.GENERATED);
        itemModelGenerator.register(ModItems.GUARDIAN_ARMOR_TRIM_SMITHING_TEMPLATE, Models.GENERATED);
        itemModelGenerator.register(ModItems.RADIANT_UPGRADE_SMITHING_TEMPLATE, Models.GENERATED);

        itemModelGenerator.register(ModItems.ENERGY_INGOT, Models.GENERATED);

        itemModelGenerator.register(ModItems.GEM_DETECTOR, Models.GENERATED);

        itemModelGenerator.register(ModItems.RADIANT_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.RUBY_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.RUBY_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.RUBY_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.RUBY_HOE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.RUBY_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.RUBY_SPEAR, Models.GENERATED);
        itemModelGenerator.register(ModItems.SAPPHIRE_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.SAPPHIRE_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.SAPPHIRE_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.SAPPHIRE_HOE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.SAPPHIRE_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.SAPPHIRE_SPEAR, Models.GENERATED);

        itemModelGenerator.registerArmor(ModItems.RUBY_HELMET, ModEquipmentAssetKeys.RUBY,  ItemModelGenerator.HELMET_TRIM_ID_PREFIX, false);
        itemModelGenerator.registerArmor(ModItems.RUBY_CHESTPLATE, ModEquipmentAssetKeys.RUBY, ItemModelGenerator.CHESTPLATE_TRIM_ID_PREFIX, false);
        itemModelGenerator.registerArmor(ModItems.RUBY_LEGGINGS, ModEquipmentAssetKeys.RUBY,  ItemModelGenerator.LEGGINGS_TRIM_ID_PREFIX, false);
        itemModelGenerator.registerArmor(ModItems.RUBY_BOOTS, ModEquipmentAssetKeys.RUBY, ItemModelGenerator.BOOTS_TRIM_ID_PREFIX, false);
        itemModelGenerator.register(ModItems.RUBY_NAUTILUS_ARMOR, Models.GENERATED);
        itemModelGenerator.registerArmor(ModItems.SAPPHIRE_HELMET, ModEquipmentAssetKeys.SAPPHIRE, ItemModelGenerator.HELMET_TRIM_ID_PREFIX, false);
        itemModelGenerator.registerArmor(ModItems.SAPPHIRE_CHESTPLATE,  ModEquipmentAssetKeys.SAPPHIRE, ItemModelGenerator.CHESTPLATE_TRIM_ID_PREFIX, false);
        itemModelGenerator.registerArmor(ModItems.SAPPHIRE_LEGGINGS,  ModEquipmentAssetKeys.SAPPHIRE, ItemModelGenerator.LEGGINGS_TRIM_ID_PREFIX, false);
        itemModelGenerator.registerArmor(ModItems.SAPPHIRE_BOOTS, ModEquipmentAssetKeys.SAPPHIRE,  ItemModelGenerator.BOOTS_TRIM_ID_PREFIX, false);
        itemModelGenerator.register(ModItems.SAPPHIRE_NAUTILUS_ARMOR, Models.GENERATED);
        itemModelGenerator.registerArmor(ModItems.RADIANT_HELMET, ModEquipmentAssetKeys.RADIANT, ItemModelGenerator.HELMET_TRIM_ID_PREFIX, false);
        itemModelGenerator.registerArmor(ModItems.RADIANT_CHESTPLATE, ModEquipmentAssetKeys.RADIANT, ItemModelGenerator.CHESTPLATE_TRIM_ID_PREFIX, false);
        itemModelGenerator.registerArmor(ModItems.RADIANT_LEGGINGS, ModEquipmentAssetKeys.RADIANT, ItemModelGenerator.LEGGINGS_TRIM_ID_PREFIX, false);
        itemModelGenerator.registerArmor(ModItems.RADIANT_BOOTS, ModEquipmentAssetKeys.RADIANT, ItemModelGenerator.BOOTS_TRIM_ID_PREFIX, false);
    }
}