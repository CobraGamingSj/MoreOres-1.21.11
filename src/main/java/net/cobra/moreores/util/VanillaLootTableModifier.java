package net.cobra.moreores.util;

import net.cobra.moreores.MoreOresModInitializer;
import net.cobra.moreores.item.ModItems;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class VanillaLootTableModifier {
    private static final RegistryKey<LootTable> JUNGLE_PYRAMID_KEY = RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.of("minecraft", "chests/jungle_temple"));
    private static final RegistryKey<LootTable> PLAINS_VILLAGE_HOUSE_KEY = RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.of("minecraft", "chests/village/village_plains_house"));
    private static final RegistryKey<LootTable> PLAINS_VILLAGE_TOOLSMITH_KEY = RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.of("minecraft", "chests/village/village_toolsmith"));
    private static final RegistryKey<LootTable> ANCIENT_CITY_KEY = RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.of("minecraft", "chests/village/ancient_city"));
    private static final RegistryKey<LootTable> CREEPER_SKELETON_KEY = RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.of("minecraft", "entities/creeper"));
    private static final RegistryKey<LootTable> ELDER_GUARDIAN_KEY = RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.ofVanilla("entities/elder_guardian"));
    private static final RegistryKey<LootTable> WARDEN_KEY = RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.ofVanilla("entities/warden"));

    public static void modifyVanillaLoot() {

        MoreOresModInitializer.LOGGER.info("Modifying VanillaLootTables for " + MoreOresModInitializer.MOD_ID + " mod.");

        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            if (JUNGLE_PYRAMID_KEY.equals(key)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.5f)) //50% Chances
                        .with(ItemEntry.builder(ModItems.RUBY_UPGRADE_SMITHING_TEMPLATE))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

                tableBuilder.pool(poolBuilder);
            }
            if (WARDEN_KEY.equals(key)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.005f)) //0.5% Chances
                        .with(ItemEntry.builder(ModItems.MUSIC_DISC_DEEPER))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

                tableBuilder.pool(poolBuilder);
            }
            if (ELDER_GUARDIAN_KEY.equals(key)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.00025f)) //0.025% Chances
                        .with(ItemEntry.builder(ModItems.GUARDIAN_ARMOR_TRIM_SMITHING_TEMPLATE))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

                tableBuilder.pool(poolBuilder);
            }
            if (ANCIENT_CITY_KEY.equals(key)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.0005f)) //0.05% Chances
                        .with(ItemEntry.builder(ModItems.MUSIC_DISC_DEEPER))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

                tableBuilder.pool(poolBuilder);
            }
            if (PLAINS_VILLAGE_HOUSE_KEY.equals(key)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.75f)) //75% Chance
                        .with(ItemEntry.builder(ModItems.TOMATO_SEEDS))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f)));

                tableBuilder.pool(poolBuilder);
            }
            if (PLAINS_VILLAGE_HOUSE_KEY.equals(key)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.35f)) //35% Chance
                        .with(ItemEntry.builder(ModItems.PINEAPPLE_SEEDS))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f)));

                tableBuilder.pool(poolBuilder);
            }
            if (PLAINS_VILLAGE_TOOLSMITH_KEY.equals(key)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.05f)) //5% Chances
                        .with(ItemEntry.builder(ModItems.RUBY))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f)));
                LootPool.Builder poolBuilder2 = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.0015f)) //0.15% Chances
                        .with(ItemEntry.builder(ModItems.RADIANT))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f)));
                LootPool.Builder poolBuilder3 = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.015f)) //1.5% Chances
                        .with(ItemEntry.builder(ModItems.GREEN_GARNET))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f)));
                LootPool.Builder poolBuilder4 = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.05f)) //5% Chances
                        .with(ItemEntry.builder(ModItems.SAPPHIRE))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f)));
                LootPool.Builder poolBuilder5 = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.05f)) //5% Chances
                        .with(ItemEntry.builder(ModItems.TOPAZ))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f)));
                LootPool.Builder poolBuilder6 = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.005f)) //0.5% Chances
                        .with(ItemEntry.builder(ModItems.PYROPE))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

                tableBuilder.pool(poolBuilder);
            tableBuilder.pool(poolBuilder2);
            tableBuilder.pool(poolBuilder3);
            tableBuilder.pool(poolBuilder4);
            tableBuilder.pool(poolBuilder5);
            tableBuilder.pool(poolBuilder6);
        }
//        if (CREEPER_SKELETON_KEY.equals(key)) {
//                LootPool.Builder poolBuilder = LootPool.builder()
//                        .rolls(ConstantLootNumberProvider.create(1))
//                        .conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.ATTACKER, EntityPredicate.Builder.create().type(EntityType.SKELETON)))
//                        .with(TagEntry.builder(ItemTags.CREEPER_DROP_MUSIC_DISCS))
//                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f)));
//
//                tableBuilder.pool(poolBuilder);
//            }
        });
    }

}
