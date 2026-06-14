package org.cobra.moreores.enchantment.entity.effect;

import com.mojang.serialization.MapCodec;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
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
        if(level == 4) {
            EntityType.LIGHTNING_BOLT.spawn(world, user.getBlockPos(), SpawnReason.TRIGGERED);
            EntityType.LIGHTNING_BOLT.spawn(world, user.getBlockPos(), SpawnReason.TRIGGERED);
            EntityType.LIGHTNING_BOLT.spawn(world, user.getBlockPos(), SpawnReason.TRIGGERED);
            EntityType.LIGHTNING_BOLT.spawn(world, user.getBlockPos(), SpawnReason.TRIGGERED);
            EntityType.TNT.spawn(world, new BlockPos(user.getBlockX(), user.getBlockY(), user.getBlockZ()), SpawnReason.TRIGGERED).setFuse(0);
            EntityType.TNT.spawn(world, new BlockPos(user.getBlockX() + 2, user.getBlockY(), user.getBlockZ()), SpawnReason.TRIGGERED).setFuse(10);
            EntityType.TNT.spawn(world, new BlockPos(user.getBlockX() - 2, user.getBlockY(), user.getBlockZ()), SpawnReason.TRIGGERED).setFuse(10);
            EntityType.TNT.spawn(world, new BlockPos(user.getBlockX(), user.getBlockY() + 2, user.getBlockZ()), SpawnReason.TRIGGERED).setFuse(10);
            EntityType.TNT.spawn(world, new BlockPos(user.getBlockX(), user.getBlockY(), user.getBlockZ() + 2), SpawnReason.TRIGGERED).setFuse(10);
            EntityType.TNT.spawn(world, new BlockPos(user.getBlockX(), user.getBlockY(), user.getBlockZ() - 2), SpawnReason.TRIGGERED).setFuse(10);
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> getCodec() {
        return CODEC;
    }
}
