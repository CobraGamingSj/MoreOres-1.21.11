package org.cobra.moreores.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.cobra.moreores.item.ModItems;

public class GemArrowEntity extends PersistentProjectileEntity {
    protected GemArrowEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected ItemStack getDefaultItemStack() {
        return new ItemStack(ModItems.GEM_ARROW);
    }
}
