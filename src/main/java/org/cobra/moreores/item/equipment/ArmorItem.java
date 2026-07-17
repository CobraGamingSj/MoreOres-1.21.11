package org.cobra.moreores.item.equipment;

import com.google.common.collect.ImmutableMap;
import net.minecraft.client.MinecraftClient;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.EquippableComponent;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class ArmorItem extends Item {
    private boolean isFalling = false;
    
    private static final Map<ArmorMaterial, List<StatusEffectInstance>> ARMOR_EFFECTS = new ImmutableMap.Builder<ArmorMaterial, List<StatusEffectInstance>>()
            .put(ModArmorMaterials.RADIANT, List.of(
                    new StatusEffectInstance(StatusEffects.REGENERATION, -1, 3, false, false, false),
                    new StatusEffectInstance(StatusEffects.HEALTH_BOOST, -1, 9, false, false, false)
            )).build();

    public ArmorItem(Settings settings) {
        super(settings);
    }
    
    @Override
    public void inventoryTick(ItemStack stack, ServerWorld world, Entity entity, @Nullable EquipmentSlot slot) {
        if(world.isClient()) {
            return;
        }
        
        if(entity instanceof PlayerEntity player) {
            if(hasEquippedArmorSet(player)) {
                if(player.fallDistance >= 5) {
                    if(!isFalling) {
                        int duration = (int) player.fallDistance;
                        player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, duration, 2, false, false, false));
                        if (slot == EquipmentSlot.FEET) {
                            ItemStack boots = player.getEquippedStack(EquipmentSlot.FEET);
                            boots.damage((int) player.fallDistance / 3, player);
                        }
                    }
                    isFalling = true;
                } else {
                    if(isFalling) {
                        player.removeStatusEffect(StatusEffects.SLOW_FALLING);
                        isFalling = false;
                    }
                }
                evaluateArmorEffects(player);
            } else {
                player.removeStatusEffect(StatusEffects.REGENERATION);
                player.removeStatusEffect(StatusEffects.HEALTH_BOOST);
                player.removeStatusEffect(StatusEffects.SLOW_FALLING);
                isFalling = false;
            }
        }
        super.inventoryTick(stack, world, entity, slot);
    }
    
    private void evaluateArmorEffects(PlayerEntity player) {
        for (Map.Entry<ArmorMaterial, List<StatusEffectInstance>> entry : ARMOR_EFFECTS.entrySet()) {
            ArmorMaterial mapArmorMaterial = entry.getKey();
            List<StatusEffectInstance> mapStatusEffects = entry.getValue();

            if(hasEquippedCorrectArmor(mapArmorMaterial, player)) {
                addStatusEffectForMaterial(player, mapArmorMaterial, mapStatusEffects);
            }
        }
    }

    private void addStatusEffectForMaterial(PlayerEntity player, ArmorMaterial mapArmorMaterial, List<StatusEffectInstance> mapStatusEffect) {
        boolean hasPlayerEffect = mapStatusEffect.stream().allMatch(statusEffectInstance -> player.hasStatusEffect(statusEffectInstance.getEffectType()));

        if(!hasPlayerEffect) {
            for (StatusEffectInstance instance : mapStatusEffect) {
                player.addStatusEffect(new StatusEffectInstance(instance.getEffectType(),
                        instance.getDuration(), instance.getAmplifier(), instance.isAmbient(), instance.shouldShowParticles()));
            }
        }
    }

    private boolean hasEquippedArmorSet(PlayerEntity player) {
        ItemStack boots = player.getEquippedStack(EquipmentSlot.FEET);
        ItemStack leggings = player.getEquippedStack(EquipmentSlot.LEGS);
        ItemStack chestplate = player.getEquippedStack(EquipmentSlot.CHEST);
        ItemStack helmet = player.getEquippedStack(EquipmentSlot.HEAD);

        return !helmet.isEmpty() && !chestplate.isEmpty()
                && !leggings.isEmpty() && !boots.isEmpty();
    }

    private boolean hasEquippedCorrectArmor(ArmorMaterial material, PlayerEntity player) {
        ItemStack boots = player.getEquippedStack(EquipmentSlot.FEET);
        ItemStack leggings = player.getEquippedStack(EquipmentSlot.LEGS);
        ItemStack chestplate = player.getEquippedStack(EquipmentSlot.CHEST);
        ItemStack helmet = player.getEquippedStack(EquipmentSlot.HEAD);

        EquippableComponent equippableComponentBoots = boots.getComponents().get(DataComponentTypes.EQUIPPABLE);
        EquippableComponent equippableComponentLeggings = leggings.getComponents().get(DataComponentTypes.EQUIPPABLE);
        EquippableComponent equippableComponentBreastplate = chestplate.getComponents().get(DataComponentTypes.EQUIPPABLE);
        EquippableComponent equippableComponentHelmet = helmet.getComponents().get(DataComponentTypes.EQUIPPABLE);

        return equippableComponentBoots.assetId().get().equals(material.assetId()) &&
                equippableComponentLeggings.assetId().get().equals(material.assetId()) &&
                equippableComponentBreastplate.assetId().get().equals(material.assetId()) &&
                equippableComponentHelmet.assetId().get().equals(material.assetId());
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        super.appendTooltip(stack, context, displayComponent, textConsumer, type);
        MinecraftClient client = MinecraftClient.getInstance();
        PlayerEntity player = client.player;
        if (player != null && hasEquippedArmorSet(player)) {
            textConsumer.accept(Text.literal("Applied Effects: ")
                    .formatted(Formatting.YELLOW));
            List<StatusEffectInstance> effects = ARMOR_EFFECTS.get(ModArmorMaterials.RADIANT);
            if(effects != null) {
                for (StatusEffectInstance effect : effects) {
                    textConsumer.accept(Text.translatable(effect.getTranslationKey()).append(" " + (effect.getAmplifier() + 1)).formatted(Formatting.RED));
                }
            }
            EquippableComponent self = stack.getComponents().get(DataComponentTypes.EQUIPPABLE);
            if (self != null && self.assetId().isPresent()
                    && self.assetId().get().equals(ModArmorMaterials.RADIANT.assetId())
                    && self.slot() == EquipmentSlot.FEET) {
                textConsumer.accept(Text.literal("Fall Protection Activated").formatted(Formatting.BLUE));
            }
        }
    }
}
