package net.cobra.moreores.item.equipment;

import net.cobra.moreores.MoreOresModInitializer;
import net.minecraft.item.equipment.EquipmentAsset;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public interface ModEquipmentAssetKeys {
    RegistryKey<? extends Registry<EquipmentAsset>> REGISTRY_KEY = RegistryKey.ofRegistry(Identifier.ofVanilla("equipment_asset"));

    RegistryKey<EquipmentAsset> RUBY = register("ruby");
    RegistryKey<EquipmentAsset> SAPPHIRE = register("sapphire");
    RegistryKey<EquipmentAsset> RADIANT = register("radiant");

    static RegistryKey<EquipmentAsset> register(String id) {
        return RegistryKey.of(REGISTRY_KEY, MoreOresModInitializer.getId(id));
    }
}
