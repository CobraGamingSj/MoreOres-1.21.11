package net.cobra.moreores.enchantment.entity.effect;

import com.mojang.serialization.MapCodec;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;

public record ThunderSummonEnchantmentEffect() implements EnchantmentEntityEffect {

    public static final MapCodec<ThunderSummonEnchantmentEffect> CODEC = MapCodec.unit(ThunderSummonEnchantmentEffect::new);

    @Override
    public void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity user, Vec3d pos) {
        if (level == 1) {
            EntityType.LIGHTNING_BOLT.spawn(world, user.getBlockPos(), SpawnReason.TRIGGERED);
        }
        if (level == 2) {
            EntityType.LIGHTNING_BOLT.spawn(world, user.getBlockPos(), SpawnReason.TRIGGERED);
            EntityType.LIGHTNING_BOLT.spawn(world, user.getBlockPos(), SpawnReason.TRIGGERED);
        }
        if (level == 3) {
            EntityType.LIGHTNING_BOLT.spawn(world, user.getBlockPos(), SpawnReason.TRIGGERED);
            EntityType.LIGHTNING_BOLT.spawn(world, user.getBlockPos(), SpawnReason.TRIGGERED);
            EntityType.TNT.spawn(world, user.getBlockPos(), SpawnReason.TRIGGERED).setFuse(0);
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> getCodec() {
        return CODEC;
    }
}
