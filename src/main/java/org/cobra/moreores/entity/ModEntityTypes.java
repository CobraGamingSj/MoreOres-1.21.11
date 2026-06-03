package org.cobra.moreores.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import org.cobra.moreores.MoreOresModInitializer;

public class ModEntityTypes {

    private static final RegistryKey<EntityType<?>> GEM_ARROW = RegistryKey.of(RegistryKeys.ENTITY_TYPE, MoreOresModInitializer.id("gem_arrow"));
    
    public static final EntityType<GemArrowEntity> GEM_ARROW_ENTITY = Registry.register(Registries.ENTITY_TYPE,
            MoreOresModInitializer.id("gem_arrow"),
            EntityType.Builder.<GemArrowEntity>create(GemArrowEntity::new, SpawnGroup.MISC)
                    .dimensions(0.5f, 0.5f).maxTrackingRange(4).dropsNothing().trackingTickInterval(20).eyeHeight(0.13f).build(GEM_ARROW));
    
}
