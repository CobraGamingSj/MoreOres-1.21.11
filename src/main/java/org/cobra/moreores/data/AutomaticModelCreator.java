package org.cobra.moreores.data;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.Block;
import net.minecraft.client.data.*;
import net.minecraft.client.render.item.model.ItemModel;
import net.minecraft.client.render.item.model.SelectItemModel;
import net.minecraft.client.render.item.property.select.TrimMaterialProperty;
import net.minecraft.client.render.item.tint.DyeTintSource;
import net.minecraft.client.render.model.json.ModelVariant;
import net.minecraft.client.render.model.json.WeightedVariant;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.equipment.EquipmentAsset;
import net.minecraft.item.equipment.trim.ArmorTrimAssets;
import net.minecraft.item.equipment.trim.ArmorTrimMaterial;
import net.minecraft.item.equipment.trim.ArmorTrimMaterials;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AutomaticModelCreator extends FabricModelProvider {
    private static final List<ItemModelGenerator.TrimMaterial> TRIM_MATERIALS = List.of(
            new ItemModelGenerator.TrimMaterial(ModArmorTrimAssets.RUBY, ModArmorTrimMaterials.RUBY),
            new ItemModelGenerator.TrimMaterial(ModArmorTrimAssets.RADIANT, ModArmorTrimMaterials.RADIANT),
            new ItemModelGenerator.TrimMaterial(ModArmorTrimAssets.SAPPHIRE, ModArmorTrimMaterials.SAPPHIRE),
            new ItemModelGenerator.TrimMaterial(ModArmorTrimAssets.GREEN_SAPPHIRE, ModArmorTrimMaterials.GREEN_SAPPHIRE),
            new ItemModelGenerator.TrimMaterial(ModArmorTrimAssets.BLUE_GARNET, ModArmorTrimMaterials.BLUE_GARNET),
            new ItemModelGenerator.TrimMaterial(ModArmorTrimAssets.PINK_GARNET, ModArmorTrimMaterials.PINK_GARNET),
            new ItemModelGenerator.TrimMaterial(ModArmorTrimAssets.GREEN_GARNET, ModArmorTrimMaterials.GREEN_GARNET),
            new ItemModelGenerator.TrimMaterial(ModArmorTrimAssets.KYAWTHUITE, ModArmorTrimMaterials.KYAWTHUITE),
            new ItemModelGenerator.TrimMaterial(ModArmorTrimAssets.TOPAZ, ModArmorTrimMaterials.TOPAZ),
            new ItemModelGenerator.TrimMaterial(ModArmorTrimAssets.WHITE_TOPAZ, ModArmorTrimMaterials.WHITE_TOPAZ),
            new ItemModelGenerator.TrimMaterial(ModArmorTrimAssets.PERIDOT, ModArmorTrimMaterials.PERIDOT),
            new ItemModelGenerator.TrimMaterial(ModArmorTrimAssets.JADE, ModArmorTrimMaterials.JADE),
            new ItemModelGenerator.TrimMaterial(ModArmorTrimAssets.PYROPE, ModArmorTrimMaterials.PYROPE),
            new ItemModelGenerator.TrimMaterial(ModArmorTrimAssets.CRIMSON_GARNET, ModArmorTrimMaterials.CRIMSON_GARNET),
            new ItemModelGenerator.TrimMaterial(ModArmorTrimAssets.CRYSTALLITE, ModArmorTrimMaterials.CRYSTALLITE),
            new ItemModelGenerator.TrimMaterial(ModArmorTrimAssets.RADIANT_AMETHYST, ModArmorTrimMaterials.RADIANT_AMETHYST),
            new ItemModelGenerator.TrimMaterial(ModArmorTrimAssets.LIMESTONE, ModArmorTrimMaterials.LIMESTONE),
            new ItemModelGenerator.TrimMaterial(ModArmorTrimAssets.MOONSTONE, ModArmorTrimMaterials.MOONSTONE),
            new ItemModelGenerator.TrimMaterial(ModArmorTrimAssets.ALEXANDRITE, ModArmorTrimMaterials.ALEXANDRITE),
            new ItemModelGenerator.TrimMaterial(ModArmorTrimAssets.QUARTSIDIAN, ModArmorTrimMaterials.QUARTSIDIAN),
            new ItemModelGenerator.TrimMaterial(ModArmorTrimAssets.OPAL, ModArmorTrimMaterials.OPAL),
            new ItemModelGenerator.TrimMaterial(ModArmorTrimAssets.RED_BERYL, ModArmorTrimMaterials.RED_BERYL),
            new ItemModelGenerator.TrimMaterial(ArmorTrimAssets.QUARTZ, ArmorTrimMaterials.QUARTZ),
            new ItemModelGenerator.TrimMaterial(ArmorTrimAssets.IRON, ArmorTrimMaterials.IRON),
            new ItemModelGenerator.TrimMaterial(ArmorTrimAssets.NETHERITE, ArmorTrimMaterials.NETHERITE),
            new ItemModelGenerator.TrimMaterial(ArmorTrimAssets.REDSTONE, ArmorTrimMaterials.REDSTONE),
            new ItemModelGenerator.TrimMaterial(ArmorTrimAssets.COPPER, ArmorTrimMaterials.COPPER),
            new ItemModelGenerator.TrimMaterial(ArmorTrimAssets.GOLD, ArmorTrimMaterials.GOLD),
            new ItemModelGenerator.TrimMaterial(ArmorTrimAssets.EMERALD, ArmorTrimMaterials.EMERALD),
            new ItemModelGenerator.TrimMaterial(ArmorTrimAssets.DIAMOND, ArmorTrimMaterials.DIAMOND),
            new ItemModelGenerator.TrimMaterial(ArmorTrimAssets.LAPIS, ArmorTrimMaterials.LAPIS),
            new ItemModelGenerator.TrimMaterial(ArmorTrimAssets.AMETHYST, ArmorTrimMaterials.AMETHYST),
            new ItemModelGenerator.TrimMaterial(ArmorTrimAssets.RESIN, ArmorTrimMaterials.RESIN)
    );

    public AutomaticModelCreator(FabricDataOutput output) {
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
        Map<String, Identifier> trimPrefixes = Map.of(
                "_helmet", ItemModelGenerator.HELMET_TRIM_ID_PREFIX,
                "_chestplate", ItemModelGenerator.CHESTPLATE_TRIM_ID_PREFIX,
                "_leggings", ItemModelGenerator.LEGGINGS_TRIM_ID_PREFIX,
                "_boots", ItemModelGenerator.BOOTS_TRIM_ID_PREFIX
        );  
        
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
                    boolean generated = false;
                    for (Map.Entry<String, Identifier> entry : trimPrefixes.entrySet()) {
                        String suffix = entry.getKey();
                        Identifier prefix = entry.getValue();
                        if(path.endsWith(suffix)) {
                            itemModelGenerator.registerArmor(item, assetKey, prefix, false);
                            generated = true;
                        }
                    }
                    if(generated) {
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

    public final void registerArmor(Item item, RegistryKey<EquipmentAsset> equipmentKey, Identifier trimIdPrefix, boolean dyeable, ItemModelGenerator gen) {
        Identifier identifier = ModelIds.getItemModelId(item);
        Identifier identifier2 = TextureMap.getId(item);
        Identifier identifier3 = TextureMap.getSubId(item, "_overlay");
        List<SelectItemModel.SwitchCase<RegistryKey<ArmorTrimMaterial>>> list = new ArrayList<>(TRIM_MATERIALS.size());

        for (ItemModelGenerator.TrimMaterial trimMaterial : TRIM_MATERIALS) {
            Identifier identifier4 = identifier.withSuffixedPath("_" + trimMaterial.assets().base().suffix() + "_trim");
            Identifier identifier5 = trimIdPrefix.withSuffixedPath("_" + trimMaterial.assets().getAssetId(equipmentKey).suffix());
            ItemModel.Unbaked unbaked;
            if (dyeable) {
                gen.uploadArmor(identifier4, identifier2, identifier3, identifier5);
                unbaked = ItemModels.tinted(identifier4, new DyeTintSource(-6265536));
            } else {
                gen.uploadArmor(identifier4, identifier2, identifier5);
                unbaked = ItemModels.basic(identifier4);
            }

            list.add(ItemModels.switchCase(trimMaterial.materialKey(), unbaked));
        }

        ItemModel.Unbaked unbaked2;
        if (dyeable) {
            Models.GENERATED_TWO_LAYERS.upload(identifier, TextureMap.layered(identifier2, identifier3), gen.modelCollector);
            unbaked2 = ItemModels.tinted(identifier, new DyeTintSource(-6265536));
        } else {
            Models.GENERATED.upload(identifier, TextureMap.layer0(identifier2), gen.modelCollector);
            unbaked2 = ItemModels.basic(identifier);
        }

        gen.output.accept(item, ItemModels.select(new TrimMaterialProperty(), unbaked2, list));
    }
}
