package org.cobra.moreores.mixin;

import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.item.equipment.trim.ArmorTrimAssets;
import net.minecraft.item.equipment.trim.ArmorTrimMaterial;
import net.minecraft.registry.RegistryKey;
import org.cobra.moreores.item.equipment.trim.ModArmorTrimAssets;
import org.cobra.moreores.item.equipment.trim.ModArmorTrimMaterials;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Mixin(ItemModelGenerator.class)
public class ItemModelGeneratorMixin {

    @Mutable
    @Shadow
    @Final
    public static List<ItemModelGenerator.TrimMaterial> TRIM_MATERIALS;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void moreores$addTrimMaterials(CallbackInfo ci) {
        List<ItemModelGenerator.TrimMaterial> materials = new ArrayList<>(TRIM_MATERIALS);

        add(materials, ModArmorTrimAssets.RUBY, ModArmorTrimMaterials.RUBY);
        add(materials, ModArmorTrimAssets.RADIANT, ModArmorTrimMaterials.RADIANT);
        add(materials, ModArmorTrimAssets.SAPPHIRE, ModArmorTrimMaterials.SAPPHIRE);
        add(materials, ModArmorTrimAssets.GREEN_SAPPHIRE, ModArmorTrimMaterials.GREEN_SAPPHIRE);
        add(materials, ModArmorTrimAssets.BLUE_GARNET, ModArmorTrimMaterials.BLUE_GARNET);
        add(materials, ModArmorTrimAssets.PINK_GARNET, ModArmorTrimMaterials.PINK_GARNET);
        add(materials, ModArmorTrimAssets.GREEN_GARNET, ModArmorTrimMaterials.GREEN_GARNET);
        add(materials, ModArmorTrimAssets.KYAWTHUITE, ModArmorTrimMaterials.KYAWTHUITE);
        add(materials, ModArmorTrimAssets.TOPAZ, ModArmorTrimMaterials.TOPAZ);
        add(materials, ModArmorTrimAssets.WHITE_TOPAZ, ModArmorTrimMaterials.WHITE_TOPAZ);
        add(materials, ModArmorTrimAssets.PERIDOT, ModArmorTrimMaterials.PERIDOT);
        add(materials, ModArmorTrimAssets.JADE, ModArmorTrimMaterials.JADE);
        add(materials, ModArmorTrimAssets.PYROPE, ModArmorTrimMaterials.PYROPE);
        add(materials, ModArmorTrimAssets.CRIMSON_GARNET, ModArmorTrimMaterials.CRIMSON_GARNET);
        add(materials, ModArmorTrimAssets.CRYSTALLITE, ModArmorTrimMaterials.CRYSTALLITE);
        add(materials, ModArmorTrimAssets.RADIANT_AMETHYST, ModArmorTrimMaterials.RADIANT_AMETHYST);
        add(materials, ModArmorTrimAssets.LIMESTONE, ModArmorTrimMaterials.LIMESTONE);
        add(materials, ModArmorTrimAssets.MOONSTONE, ModArmorTrimMaterials.MOONSTONE);
        add(materials, ModArmorTrimAssets.ALEXANDRITE, ModArmorTrimMaterials.ALEXANDRITE);
        add(materials, ModArmorTrimAssets.QUARTSIDIAN, ModArmorTrimMaterials.QUARTSIDIAN);
        add(materials, ModArmorTrimAssets.OPAL, ModArmorTrimMaterials.OPAL);
        add(materials, ModArmorTrimAssets.RED_BERYL, ModArmorTrimMaterials.RED_BERYL);

        TRIM_MATERIALS = List.copyOf(materials);
    }

    private static void add(
            List<ItemModelGenerator.TrimMaterial> list,
            ArmorTrimAssets assets,
            RegistryKey<ArmorTrimMaterial> material) {

        list.add(new ItemModelGenerator.TrimMaterial(assets, material));
    }
}
