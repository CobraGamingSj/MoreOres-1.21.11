package net.cobra.moreores.enchantment;

import net.cobra.moreores.MoreOresModInitializer;
import net.cobra.moreores.enchantment.entity.effect.ThunderSummonEnchantmentEffect;
import net.minecraft.component.EnchantmentEffectComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.effect.EnchantmentEffectTarget;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;

public class ModEnchantments {

    public static final RegistryKey<Enchantment> THUNDER_STRIKER = of("thunder_striker");

    public static void bootstrap(Registerable<Enchantment> registerable) {
        var enchantments = registerable.getRegistryLookup(RegistryKeys.ENCHANTMENT);
        var items = registerable.getRegistryLookup(RegistryKeys.ITEM);

        register(registerable, THUNDER_STRIKER, Enchantment.builder(
                Enchantment.definition(
                        items.getOrThrow(ItemTags.SWORDS),
                        5,
                        3,
                        Enchantment.leveledCost(7, 10),
                        Enchantment.leveledCost(25, 9),
                        2,
                        AttributeModifierSlot.MAINHAND
                )
        ).addEffect(EnchantmentEffectComponentTypes.POST_ATTACK, EnchantmentEffectTarget.ATTACKER, EnchantmentEffectTarget.VICTIM, new ThunderSummonEnchantmentEffect()));
    }

    private static void register(Registerable<Enchantment> registerable, RegistryKey<Enchantment> key, Enchantment.Builder builder) {
        registerable.register(key, builder.build(key.getValue()));
    }

    private static RegistryKey<Enchantment> of(String id) {
        return RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(MoreOresModInitializer.MOD_ID, id));
    }
}
