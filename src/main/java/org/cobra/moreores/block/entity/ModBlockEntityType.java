package org.cobra.moreores.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.cobra.moreores.MoreOresModInitializer;
import org.cobra.moreores.block.ModBlocks;
import org.cobra.moreores.block.entity.gem.GemCrystallizerBlockEntity;
import org.cobra.moreores.block.entity.gem.GemPurifierBlockEntity;
import team.reborn.energy.api.EnergyStorage;

public class ModBlockEntityType {

    public static final BlockEntityType<GemPurifierBlockEntity> GEM_PURIFIER =
            register("gem_purifier_block", FabricBlockEntityTypeBuilder.create(GemPurifierBlockEntity::new, ModBlocks.GEM_PURIFIER_BLOCK));

    public static final BlockEntityType<GemCrystallizerBlockEntity> GEM_CRYSTALLIZER =
            register("gem_crystallizer_block", FabricBlockEntityTypeBuilder.create(GemCrystallizerBlockEntity::new, ModBlocks.GEM_CRYSTALLIZER_BLOCK));

    private static <BE extends BlockEntity> BlockEntityType<BE> register(String id, FabricBlockEntityTypeBuilder<BE> factory) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(MoreOresModInitializer.MOD_ID, id), factory.build());
    }

    public static void register() {
        EnergyStorage.SIDED.registerForBlockEntity((blockEntity, direction) -> blockEntity.energyStorage, GEM_PURIFIER);
        EnergyStorage.SIDED.registerForBlockEntity((blockEntity, direction) -> blockEntity.energyStorage, GEM_CRYSTALLIZER);
        FluidStorage.SIDED.registerForBlockEntity(((blockEntity, direction) -> blockEntity.fluidStorage), GEM_PURIFIER);
        MoreOresModInitializer.LOGGER.info("Loading ModBlockEntityTypes for {} mod.", MoreOresModInitializer.MOD_ID);
    }
}
