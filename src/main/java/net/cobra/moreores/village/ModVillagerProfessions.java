package net.cobra.moreores.village;

import com.google.common.collect.ImmutableSet;
import net.cobra.moreores.MoreOresModInitializer;
import net.cobra.moreores.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;

public class ModVillagerProfessions {
    public static final RegistryKey<PointOfInterestType> JEWEL_POI = poiKey("jewel_poi");
    public static final PointOfInterestType JEWEL = registerPoi("jewel_poi", ModBlocks.GEM_PURIFIER_BLOCK);

    public static final RegistryKey<VillagerProfession> JEWELLER = RegistryKey.of(RegistryKeys.VILLAGER_PROFESSION, MoreOresModInitializer.getId("jeweller"));
    public static final VillagerProfession JEWELLER_KEY = registerProfession("jeweller", JEWEL_POI);

    private static VillagerProfession registerProfession(String id, RegistryKey<PointOfInterestType> type) {
        return Registry.register(Registries.VILLAGER_PROFESSION, MoreOresModInitializer.getId(id),
                new VillagerProfession(Text.literal("Jeweller"), entry -> entry.matchesKey(type), entry -> entry.matchesKey(type),
                        ImmutableSet.of(), ImmutableSet.of(), SoundEvents.ENTITY_VILLAGER_WORK_SHEPHERD));
    }

    private static PointOfInterestType registerPoi(String id, Block block) {
        return PointOfInterestHelper.register(MoreOresModInitializer.getId(id), 1, 1, block);
    }

    private static RegistryKey<PointOfInterestType> poiKey(String id) {
        return RegistryKey.of(RegistryKeys.POINT_OF_INTEREST_TYPE, MoreOresModInitializer.getId(id));
    }

    public static void register() {
        MoreOresModInitializer.LOGGER.info("Loading ModVillagerProfessions for " + MoreOresModInitializer.MOD_ID + " mod.");
    }
}