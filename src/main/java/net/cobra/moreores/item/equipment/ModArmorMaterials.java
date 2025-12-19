package net.cobra.moreores.item.equipment;

import net.cobra.moreores.registry.ModItemTags;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Util;

import java.util.EnumMap;

public interface ModArmorMaterials {

    ArmorMaterial RUBY = new ArmorMaterial(38, Util.make(new EnumMap(EquipmentType.class), map -> {
        map.put(EquipmentType.BOOTS, 5);
        map.put(EquipmentType.LEGGINGS, 8);
        map.put(EquipmentType.CHESTPLATE, 10);
        map.put(EquipmentType.HELMET, 5);
        map.put(EquipmentType.BODY, 13);
    }), 15, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 4.0F, 0.2F, ModItemTags.REPAIRS_RUBY_ARMOR, ModEquipmentAssetKeys.RUBY);
    ArmorMaterial SAPPHIRE = new ArmorMaterial(39, Util.make(new EnumMap(EquipmentType.class), map -> {
        map.put(EquipmentType.BOOTS, 7);
        map.put(EquipmentType.LEGGINGS, 10);
        map.put(EquipmentType.CHESTPLATE, 12);
        map.put(EquipmentType.HELMET, 7);
        map.put(EquipmentType.BODY, 15);
    }), 17, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 4.5F, 0.3F, ModItemTags.REPAIRS_SAPPHIRE_ARMOR, ModEquipmentAssetKeys.SAPPHIRE);
    ArmorMaterial RADIANT = new ArmorMaterial(81, Util.make(new EnumMap(EquipmentType.class), map -> {
        map.put(EquipmentType.BOOTS, 15);
        map.put(EquipmentType.LEGGINGS, 18);
        map.put(EquipmentType.CHESTPLATE, 20);
        map.put(EquipmentType.HELMET, 15);
        map.put(EquipmentType.BODY, 23);
    }), 23, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 7.0F, 0.8F, ModItemTags.REPAIRS_RADIANT_ARMOR, ModEquipmentAssetKeys.RADIANT);
}
