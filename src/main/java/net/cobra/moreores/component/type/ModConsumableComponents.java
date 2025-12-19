package net.cobra.moreores.component.type;

import net.cobra.moreores.MoreOresModInitializer;
import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.ConsumableComponents;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.consume.ApplyEffectsConsumeEffect;

import java.util.List;

public class ModConsumableComponents {

    public static final ConsumableComponent PINEAPPLE = ConsumableComponents.food().consumeEffect(new ApplyEffectsConsumeEffect(List.of(new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 200, 4),
            new StatusEffectInstance(StatusEffects.REGENERATION ,200, 4)))).build();

    public static final ConsumableComponent DIAMOND_APPLE = ConsumableComponents.food().consumeEffect(new ApplyEffectsConsumeEffect(List.of(new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 200, 4),
            new StatusEffectInstance(StatusEffects.ABSORPTION, 200, 10),
            new StatusEffectInstance(StatusEffects.REGENERATION, 200, 4),
            new StatusEffectInstance(StatusEffects.STRENGTH, 200, 4),
            new StatusEffectInstance(StatusEffects.LUCK, 200, 4),
            new StatusEffectInstance(StatusEffects.RESISTANCE, 200, 4),
            new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 200, 4)))).build();

    public static void register() {
        MoreOresModInitializer.LOGGER.info("Loading ConsumableComponents for " + MoreOresModInitializer.MOD_ID + " mod.");
    }

}
