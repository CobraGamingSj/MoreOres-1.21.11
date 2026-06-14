package org.cobra.moreores.data;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.Block;
import net.minecraft.client.data.*;
import net.minecraft.client.render.model.json.ModelVariant;
import net.minecraft.client.render.model.json.WeightedVariant;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.equipment.EquipmentAsset;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.WeightedPool;
import org.cobra.moreores.MoreOresModInitializer;
import org.cobra.moreores.block.ModBlocks;
import org.cobra.moreores.block.RubyLampBlock;
import org.cobra.moreores.item.RadiantBowItem;
import org.cobra.moreores.item.equipment.ModEquipmentAssetKeys;
import org.cobra.moreores.item.equipment.trim.ModArmorTrimAssets;
import org.cobra.moreores.item.equipment.trim.ModArmorTrimMaterials;

import java.util.Collections;
import java.util.List;

public class AutomaticModelGenerator extends FabricModelProvider {
    public AutomaticModelGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        for (Block block : Registries.BLOCK) {
            Identifier id = Registries.BLOCK.getId(block);

            if(id.getNamespace().equals(MoreOresModInitializer.MOD_ID)) {

                if(block == ModBlocks.RUBY_LAMP) {
                    Identifier lampOffIdentifier = TexturedModel.CUBE_ALL.upload(ModBlocks.RUBY_LAMP, blockStateModelGenerator.modelCollector);
                    Identifier lampOnIdentifier = blockStateModelGenerator.createSubModel(ModBlocks.RUBY_LAMP, "_on", Models.CUBE_ALL, TextureMap::all);
                    blockStateModelGenerator.blockStateCollector.accept(VariantsBlockModelDefinitionCreator.of(ModBlocks.RUBY_LAMP)
                            .with(BlockStateModelGenerator.createBooleanModelMap(RubyLampBlock.LIT,
                                    new WeightedVariant(WeightedPool.<ModelVariant>builder().add(new ModelVariant(lampOnIdentifier)).build()),
                                    new WeightedVariant(WeightedPool.<ModelVariant>builder().add(new ModelVariant(lampOffIdentifier)).build()))));
                    continue;
                } else if (block == ModBlocks.GEM_CRYSTALLIZER_BLOCK || block == ModBlocks.GEM_PURIFIER_BLOCK) {
                    continue;
                }
                blockStateModelGenerator.registerSimpleCubeAll(block);
            }
        }
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        for (Item item : Registries.ITEM) {

            if(item instanceof BlockItem) {
                continue;
            }

            Identifier id = Registries.ITEM.getId(item);
            RegistryKey<EquipmentAsset> assetKey = null;
            String path = id.getPath();

            boolean handheld = false;
            
            if(id.getNamespace().equals(MoreOresModInitializer.MOD_ID)) {

                if(path.endsWith("_sword") || path.endsWith("_shovel") ||
                        path.endsWith("_axe") || path.endsWith("_hoe") ||  path.endsWith("_pickaxe")) {
                    itemModelGenerator.register(item, Models.HANDHELD);
                    handheld = true;
                } else if (path.endsWith("_spear")) {
                    itemModelGenerator.registerSpear(item);
                    handheld = true;
                } else if (path.startsWith("ruby_")) {
                    assetKey = ModEquipmentAssetKeys.RUBY;
                } else if (path.startsWith("sapphire_")) {
                    assetKey = ModEquipmentAssetKeys.SAPPHIRE;
                } else if (path.startsWith("radiant_")) {
                    assetKey = ModEquipmentAssetKeys.RADIANT;
                }

                if (assetKey != null) {
                    if(path.endsWith("_helmet")) {
                        itemModelGenerator.registerArmor(
                                item,
                                assetKey,
                                ItemModelGenerator.HELMET_TRIM_ID_PREFIX,
                                false
                        );
                        continue;
                    } else if (path.endsWith("_chestplate")) {
                        itemModelGenerator.registerArmor(
                                item,
                                assetKey,
                                ItemModelGenerator.CHESTPLATE_TRIM_ID_PREFIX,
                                false
                        );
                        continue;
                    } else if (path.endsWith("_leggings")) {
                        itemModelGenerator.registerArmor(
                                item,
                                assetKey,
                                ItemModelGenerator.LEGGINGS_TRIM_ID_PREFIX,
                                false
                        );
                        continue;
                    } else if (path.endsWith("_boots")) {
                        itemModelGenerator.registerArmor(
                                item,
                                assetKey,
                                ItemModelGenerator.BOOTS_TRIM_ID_PREFIX,
                                false
                        );
                        continue;
                    }
                }
                
                if(item instanceof RadiantBowItem bow) {
                    itemModelGenerator.upload(bow, Models.BOW);
                    itemModelGenerator.registerBow(bow);
                    continue;
                }
                
                if(!handheld) itemModelGenerator.register(item, Models.GENERATED);
            }
        }
    }
}
