package net.cobra.moreores.util;

import net.cobra.moreores.MoreOresModInitializer;
import net.cobra.moreores.item.ModItems;
import net.cobra.moreores.village.ModVillagerProfessions;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradedItem;

public class CustomTrades {

    public static void register() {

        MoreOresModInitializer.LOGGER.info("Loading CustomTrades for " + MoreOresModInitializer.MOD_ID + " mod.");

        TradeOfferHelper.registerWanderingTraderOffers(factories -> {
            factories.addAll(MoreOresModInitializer.getId("emerald_for_tomato_seeds"), (world, entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 3),
                    new ItemStack(ModItems.TOMATO_SEEDS),
                    6, 5, 0.05f
            ));
            factories.addAll(MoreOresModInitializer.getId("emerald_for_pineapple_seeds"), (world, entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 5),
                    new ItemStack(ModItems.PINEAPPLE_SEEDS),
                    6, 5, 0.05f
            ));
            factories.addAll(MoreOresModInitializer.getId("emerald_for_tomato"), (world, entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 7),
                    new ItemStack(ModItems.TOMATO),
                    6, 5, 0.05f
            ));
            factories.addAll(MoreOresModInitializer.getId("emerald_for_pineapple"), (world, entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 11),
                    new ItemStack(ModItems.PINEAPPLE),
                    6, 5, 0.05f
            ));
        });
        TradeOfferHelper.registerVillagerOffers(ModVillagerProfessions.JEWELLER, 1, factories -> {
            factories.add((world, entity, random) -> new TradeOffer(
                    new TradedItem(Items.COAL, 24),
                    new ItemStack(ModItems.RUBY, 1),
                    6, 5, 0.5f
            ));
            factories.add((world, entity, random) -> new TradeOffer(
                    new TradedItem(Items.COPPER_INGOT, 12),
                    new ItemStack(ModItems.SAPPHIRE, 1),
                    6, 5, 0.5f
            ));
            factories.add((world, entity, random) -> new TradeOffer(
                    new TradedItem(Items.IRON_INGOT, 9),
                    new ItemStack(ModItems.GREEN_SAPPHIRE, 1),
                    6, 5, 0.15f
            ));
        });
        TradeOfferHelper.registerVillagerOffers(ModVillagerProfessions.JEWELLER, 2, factories -> {
            factories.add((world, entity, random) -> new TradeOffer(
                    new TradedItem(ModItems.RUBY, 2),
                    new ItemStack(ModItems.BLUE_GARNET, 1),
                    6, 5, 0.5f
            ));
            factories.add((world, entity, random) -> new TradeOffer(
                    new TradedItem(ModItems.SAPPHIRE, 3),
                    new ItemStack(ModItems.PINK_GARNET, 1),
                    6, 5, 0.5f
            ));
            factories.add((world, entity, random) -> new TradeOffer(
                    new TradedItem(ModItems.GREEN_SAPPHIRE, 3),
                    new ItemStack(ModItems.GREEN_GARNET, 1),
                    6, 5, 0.15f
            ));
        });
        TradeOfferHelper.registerVillagerOffers(ModVillagerProfessions.JEWELLER, 3, factories -> {
            factories.add((world, entity, random) -> new TradeOffer(
                    new TradedItem(ModItems.RUBY, 8),
                    new ItemStack(ModItems.TOPAZ, 1),
                    6, 5, 0.05f
            ));
            factories.add((world, entity, random) -> new TradeOffer(
                    new TradedItem(ModItems.PINK_GARNET, 5),
                    new ItemStack(ModItems.WHITE_TOPAZ, 1),
                    6, 5, 0.15f
            ));
            factories.add((world, entity, random) -> new TradeOffer(
                    new TradedItem(ModItems.GREEN_GARNET, 5),
                    new ItemStack(ModItems.TOPAZ, 1),
                    6, 5, 0.15f
            ));
            factories.add((world, entity, random) -> new TradeOffer(
                    new TradedItem(ModItems.RADIANT, 13),
                    new ItemStack(ModItems.WHITE_TOPAZ, 1),
                    6, 5, 0.5f
            ));
            factories.add((world, entity, random) -> new TradeOffer(
                    new TradedItem(ModItems.SAPPHIRE, 10),
                    new ItemStack(ModItems.TOPAZ, 1),
                    6, 5, 0.5f
            ));
            factories.add((world, entity, random) -> new TradeOffer(
                    new TradedItem(ModItems.GREEN_SAPPHIRE, 3),
                    new ItemStack(ModItems.TOPAZ, 1),
                    6, 5, 0.15f
            ));
        });
        TradeOfferHelper.registerVillagerOffers(ModVillagerProfessions.JEWELLER, 3, factories -> {
            factories.add((world, entity, random) -> new TradeOffer(
                    new TradedItem(ModItems.SAPPHIRE, 5),
                    new ItemStack(ModItems.PERIDOT, 1),
                    6, 5, 0.5f
            ));
            factories.add((world, entity, random) -> new TradeOffer(
                    new TradedItem(ModItems.SAPPHIRE, 3),
                    new ItemStack(ModItems.JADE, 1),
                    6, 5, 0.5f
            ));
            factories.add((world, entity, random) -> new TradeOffer(
                    new TradedItem(ModItems.GREEN_SAPPHIRE, 3),
                    new ItemStack(ModItems.PYROPE, 1),
                    6, 5, 0.15f
            ));
            factories.add((world, entity, random) -> new TradeOffer(
                    new TradedItem(ModItems.RADIANT, 15),
                    new ItemStack(ModItems.PERIDOT, 1),
                    6, 5, 0.5f
            ));
            factories.add((world, entity, random) -> new TradeOffer(
                    new TradedItem(ModItems.RUBY, 6),
                    new ItemStack(ModItems.JADE, 1),
                    6, 5, 0.5f
            ));
            factories.add((world, entity, random) -> new TradeOffer(
                    new TradedItem(ModItems.TOPAZ, 3),
                    new ItemStack(ModItems.PYROPE, 1),
                    6, 5, 0.15f
            ));
            factories.add((world, entity, random) -> new TradeOffer(
                    new TradedItem(ModItems.WHITE_TOPAZ, 2),
                    new ItemStack(ModItems.PERIDOT, 1),
                    6, 5, 0.5f
            ));
            factories.add((world, entity, random) -> new TradeOffer(
                    new TradedItem(ModItems.WHITE_TOPAZ, 3),
                    new ItemStack(ModItems.JADE, 1),
                    6, 5, 0.5f
            ));
            factories.add((world, entity, random) -> new TradeOffer(
                    new TradedItem(ModItems.WHITE_TOPAZ, 3),
                    new ItemStack(ModItems.PYROPE, 1),
                    6, 5, 0.15f
            ));
        });
        TradeOfferHelper.registerVillagerOffers(ModVillagerProfessions.JEWELLER, 4, factories -> {
            factories.add((world, entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 14),
                    new ItemStack(ModItems.RADIANT, 1),
                    6, 5, 0.5f
            ));
            factories.add((world, entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 13),
                    new ItemStack(ModItems.RUBY, 1),
                    6, 5, 0.5f
            ));
            factories.add((world, entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 13),
                    new ItemStack(ModItems.SAPPHIRE, 1),
                    6, 5, 0.15f
            ));
            factories.add((world, entity, random) -> new TradeOffer(
                    new TradedItem(Items.NETHERITE_INGOT, 5),
                    new ItemStack(ModItems.BLUE_GARNET, 1),
                    6, 5, 0.5f
            ));
            factories.add((world, entity, random) -> new TradeOffer(
                    new TradedItem(Items.IRON_INGOT, 15),
                    new ItemStack(Items.DIAMOND, 1),
                    6, 5, 0.5f
            ));
        });
        TradeOfferHelper.registerVillagerOffers(ModVillagerProfessions.JEWELLER, 5, factories -> {
            factories.add((world, entity, random) -> new TradeOffer(
                    new TradedItem(Items.NETHERITE_INGOT, 8),
                    new ItemStack(ModItems.GREEN_GARNET, 1),
                    6, 5, 0.5f
            ));
            factories.add((world, entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 18),
                    new ItemStack(ModItems.TOPAZ, 1),
                    6, 5, 0.5f
            ));
            factories.add((world, entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 21),
                    new ItemStack(ModItems.PYROPE, 1),
                    6, 5, 0.15f
            ));
            factories.add((world, entity, random) -> new TradeOffer(
                    new TradedItem(Items.NETHERITE_INGOT, 11),
                    new ItemStack(ModItems.JADE, 1),
                    6, 5, 0.5f
            ));
            factories.add((world, entity, random) -> new TradeOffer(
                    new TradedItem(Items.IRON_INGOT, 65),
                    new ItemStack(ModItems.PERIDOT, 1),
                    6, 5, 0.5f
            ));
        });
    }

}
