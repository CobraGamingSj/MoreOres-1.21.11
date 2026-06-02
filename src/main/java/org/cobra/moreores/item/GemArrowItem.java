package org.cobra.moreores.item;

import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ProjectileItem;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;
import org.cobra.moreores.entity.GemArrowEntity;
import org.cobra.moreores.entity.ModEntityTypes;

public class GemArrowItem extends Item implements ProjectileItem {
    public GemArrowItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public ProjectileEntity createEntity(World world, Position pos, ItemStack stack, Direction direction) {
        GemArrowEntity gemArrowEntity = new GemArrowEntity(ModEntityTypes.GEM_ARROW_ENTITY, world);
        gemArrowEntity.pickupType = PersistentProjectileEntity.PickupPermission.ALLOWED;
        return gemArrowEntity;
    }

    public GemArrowEntity createArrow(World world) {
        return new GemArrowEntity(ModEntityTypes.GEM_ARROW_ENTITY, world);
    }
}
