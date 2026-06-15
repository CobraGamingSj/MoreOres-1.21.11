//package org.cobra.moreores.mixin;
//
//import net.minecraft.client.data.ItemModelGenerator;
//import org.cobra.moreores.item.equipment.trim.ModArmorTrimAssets;
//import org.cobra.moreores.item.equipment.trim.ModArmorTrimMaterials;
//import org.spongepowered.asm.mixin.Final;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Shadow;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Mixin(ItemModelGenerator.class)
//public class ItemModelGeneratorMixin {
//
//    @Shadow
//    @Final
//    public static List<ItemModelGenerator.TrimMaterial> TRIM_MATERIALS;
//
//    static {
//        List<ItemModelGenerator.TrimMaterial> materials = new ArrayList<>(TRIM_MATERIALS);
//        materials.add(new ItemModelGenerator.TrimMaterial(ModArmorTrimAssets.RUBY, ModArmorTrimMaterials.RUBY));
//        materials.add(new ItemModelGenerator.TrimMaterial(ModArmorTrimAssets.RADIANT, ModArmorTrimMaterials.RADIANT));
//        materials.add(new ItemModelGenerator.TrimMaterial(ModArmorTrimAssets.SAPPHIRE, ModArmorTrimMaterials.SAPPHIRE));
//        materials.add(new ItemModelGenerator.TrimMaterial(ModArmorTrimAssets.GREEN_SAPPHIRE, ModArmorTrimMaterials.GREEN_SAPPHIRE));
//        materials.add(new ItemModelGenerator.TrimMaterial(ModArmorTrimAssets.BLUE_GARNET, ModArmorTrimMaterials.BLUE_GARNET));
//        materials.add(new ItemModelGenerator.TrimMaterial(ModArmorTrimAssets.PINK_GARNET, ModArmorTrimMaterials.PINK_GARNET));
//        materials.add(new ItemModelGenerator.TrimMaterial(ModArmorTrimAssets.GREEN_GARNET, ModArmorTrimMaterials.GREEN_GARNET));
//        materials.add(new ItemModelGenerator.TrimMaterial(ModArmorTrimAssets.KYAWTHUITE, ModArmorTrimMaterials.KYAWTHUITE));
//        materials.add(new ItemModelGenerator.TrimMaterial(ModArmorTrimAssets.TOPAZ, ModArmorTrimMaterials.TOPAZ));
//        materials.add(new ItemModelGenerator.TrimMaterial(ModArmorTrimAssets.WHITE_TOPAZ, ModArmorTrimMaterials.WHITE_TOPAZ));
//        materials.add(new ItemModelGenerator.TrimMaterial(ModArmorTrimAssets.PERIDOT, ModArmorTrimMaterials.PERIDOT));
//        materials.add(new ItemModelGenerator.TrimMaterial(ModArmorTrimAssets.JADE, ModArmorTrimMaterials.JADE));
//        materials.add(new ItemModelGenerator.TrimMaterial(ModArmorTrimAssets.PYROPE, ModArmorTrimMaterials.PYROPE));
//        materials.add(new ItemModelGenerator.TrimMaterial(ModArmorTrimAssets.CRIMSON_GARNET, ModArmorTrimMaterials.CRIMSON_GARNET));
//        materials.add(new ItemModelGenerator.TrimMaterial(ModArmorTrimAssets.CRYSTALLITE, ModArmorTrimMaterials.CRYSTALLITE));
//        materials.add(new ItemModelGenerator.TrimMaterial(ModArmorTrimAssets.RADIANT_AMETHYST, ModArmorTrimMaterials.RADIANT_AMETHYST));
//        materials.add(new ItemModelGenerator.TrimMaterial(ModArmorTrimAssets.LIMESTONE, ModArmorTrimMaterials.LIMESTONE));
//        materials.add(new ItemModelGenerator.TrimMaterial(ModArmorTrimAssets.MOONSTONE, ModArmorTrimMaterials.MOONSTONE));
//        materials.add(new ItemModelGenerator.TrimMaterial(ModArmorTrimAssets.ALEXANDRITE, ModArmorTrimMaterials.ALEXANDRITE));
//        materials.add(new ItemModelGenerator.TrimMaterial(ModArmorTrimAssets.QUARTSIDIAN, ModArmorTrimMaterials.QUARTSIDIAN));
//        materials.add(new ItemModelGenerator.TrimMaterial(ModArmorTrimAssets.OPAL, ModArmorTrimMaterials.OPAL));
//        materials.add(new ItemModelGenerator.TrimMaterial(ModArmorTrimAssets.RED_BERYL, ModArmorTrimMaterials.RED_BERYL));
//
//        TRIM_MATERIALS = List.copyOf(materials);
//    }
//
//}
