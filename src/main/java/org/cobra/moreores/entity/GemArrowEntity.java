package org.cobra.moreores.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.cobra.moreores.item.ModItems;

public class GemArrowEntity extends PersistentProjectileEntity {
    public GemArrowEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected ItemStack getDefaultItemStack() {
        return new ItemStack(ModItems.GEM_ARROW);
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        Entity entity = entityHitResult.getEntity();
        entity.damage((ServerWorld) entity.getEntityWorld(), entity.getDamageSources().arrow(this, null), 200);
        LightningEntity lightningEntity = new LightningEntity(EntityType.LIGHTNING_BOLT, entity.getEntityWorld());
        lightningEntity.setPos(entity.getX(), entity.getY(), entity.getZ());
        entity.getEntityWorld().spawnEntity(lightningEntity);
        super.onEntityHit(entityHitResult);
    }
}