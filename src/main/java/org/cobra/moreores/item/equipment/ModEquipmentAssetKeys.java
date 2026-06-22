package org.cobra.moreores.item.equipment;

import net.minecraft.item.equipment.EquipmentAsset;
import net.minecraft.item.equipment.EquipmentAssetKeys;
import net.minecraft.registry.RegistryKey;
import org.cobra.moreores.MoreOresModInitializer;

public interface ModEquipmentAssetKeys {
    RegistryKey<EquipmentAsset> RUBY = register("ruby");
    RegistryKey<EquipmentAsset> SAPPHIRE = register("sapphire");
    RegistryKey<EquipmentAsset> RADIANT = register("radiant");

    static RegistryKey<EquipmentAsset> register(String id) {
        return RegistryKey.of(EquipmentAssetKeys.REGISTRY_KEY, MoreOresModInitializer.id(id));
    }
}
