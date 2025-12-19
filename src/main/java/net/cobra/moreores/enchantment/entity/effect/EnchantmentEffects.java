package net.cobra.moreores.enchantment.entity.effect;

import com.mojang.serialization.MapCodec;
import net.cobra.moreores.MoreOresModInitializer;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static net.cobra.moreores.MoreOresModInitializer.LOGGER;

public class EnchantmentEffects {

    public static final MapCodec<? extends EnchantmentEntityEffect> THUNDER_STRIKER = register("thunder_striker", ThunderSummonEnchantmentEffect.CODEC);

    private static MapCodec<? extends EnchantmentEntityEffect> register(String id, MapCodec<? extends EnchantmentEntityEffect> codec) {
        return Registry.register(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, Identifier.of(MoreOresModInitializer.MOD_ID, id), codec);
    }

    public static void register() {
        LOGGER.info("Loading EnchantmentEffects for " + MoreOresModInitializer.MOD_ID + " mod.");
    }
}
