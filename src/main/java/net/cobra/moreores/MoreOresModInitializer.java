package net.cobra.moreores;

import net.cobra.moreores.block.ModBlocks;
import net.cobra.moreores.block.data.GemPurifierButtonClick;
import net.cobra.moreores.block.data.PolishingStateData;
import net.cobra.moreores.block.entity.ModBlockEntityType;
import net.cobra.moreores.component.type.ModConsumableComponents;
import net.cobra.moreores.enchantment.entity.effect.EnchantmentEffects;
import net.cobra.moreores.item.ModItems;
import net.cobra.moreores.networking.ModC2SNetworks;
import net.cobra.moreores.networking.ModS2CNetworks;
import net.cobra.moreores.networking.ModS2CPayloadRegistry;
import net.cobra.moreores.recipe.GemPurifierRecipe;
import net.cobra.moreores.recipe.book.ModRecipeBookCategories;
import net.cobra.moreores.screen.ModScreenHandlerType;
import net.cobra.moreores.sound.ModBlockSoundGroup;
import net.cobra.moreores.sound.ModSoundEvents;
import net.cobra.moreores.util.CustomTrades;
import net.cobra.moreores.util.VanillaLootTableModifier;
import net.cobra.moreores.village.ModVillagerProfessions;
import net.cobra.moreores.world.gen.WorldGeneration;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistryEvents;
import net.minecraft.block.Blocks;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MoreOresModInitializer implements ModInitializer {

	public static final String MOD_ID = "moreores";
	public static final String ID = "minecraft";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static Identifier getId(String id) {
		return Identifier.of(MOD_ID, id);
	}

	public static RegistryKey<Item> setRegistryKey(String registryKey) {
		return RegistryKey.of(RegistryKeys.ITEM, getId(registryKey));
	}



	// Gemstones Item Group
	public static final ItemGroup GEMSTONES = FabricItemGroup.builder()
			.icon(() -> new ItemStack(ModItems.RUBY))
			.displayName(Text.translatable("itemGroup.moreores.gemstones"))
			.entries((context, entries) -> {
				entries.add(ModItems.RUBY);
				entries.add(ModItems.RADIANT);
				entries.add(ModItems.SAPPHIRE);
				entries.add(ModItems.GREEN_SAPPHIRE);
				entries.add(ModItems.BLUE_GARNET);
				entries.add(ModItems.PINK_GARNET);
				entries.add(ModItems.GREEN_GARNET);
				entries.add(ModItems.TOPAZ);
				entries.add(ModItems.WHITE_TOPAZ);
				entries.add(ModItems.PERIDOT);
				entries.add(ModItems.PYROPE);
				entries.add(ModItems.JADE);
			}).build();

	@Override
	public void onInitialize() {



		// Gemstones Item Group Registry
		Registry.register(Registries.ITEM_GROUP, getId("gemstones"), GEMSTONES);


		// Fuel Registry
		FuelRegistryEvents.BUILD.register(((builder, context) -> {
			builder.add(ModItems.WOOD_PELLET, 2000);
			builder.add(ModItems.ENERGY_INGOT, 25000);
			builder.add(ModBlocks.ENERGY_BLOCK, 24500);
		}));


		// Gemstones & Ingots Registry
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ingredientsEventEntries -> {
			ingredientsEventEntries.addAfter(Items.RAW_GOLD, ModItems.RAW_RUBY);
			ingredientsEventEntries.addAfter(ModItems.RAW_RUBY, ModItems.RAW_SAPPHIRE);
			ingredientsEventEntries.addAfter(ModItems.RAW_SAPPHIRE, ModItems.RAW_GREEN_SAPPHIRE);
			ingredientsEventEntries.addAfter(ModItems.RAW_GREEN_SAPPHIRE, ModItems.RAW_BLUE_GARNET);
			ingredientsEventEntries.addAfter(ModItems.RAW_BLUE_GARNET, ModItems.RAW_PINK_GARNET);
			ingredientsEventEntries.addAfter(ModItems.RAW_PINK_GARNET, ModItems.RAW_GREEN_GARNET);
			ingredientsEventEntries.addAfter(ModItems.RAW_GREEN_GARNET, ModItems.RAW_TOPAZ);
			ingredientsEventEntries.addAfter(ModItems.RAW_TOPAZ, ModItems.RAW_WHITE_TOPAZ);
			ingredientsEventEntries.addAfter(ModItems.RAW_WHITE_TOPAZ, ModItems.RAW_PERIDOT);
			ingredientsEventEntries.addAfter(ModItems.RAW_PERIDOT, ModItems.RAW_PYROPE);
			ingredientsEventEntries.addAfter(ModItems.RAW_PYROPE, ModItems.RAW_JADE);

			ingredientsEventEntries.addAfter(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, ModItems.RUBY_UPGRADE_SMITHING_TEMPLATE);
			ingredientsEventEntries.addAfter(Items.SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE, ModItems.GUARDIAN_ARMOR_TRIM_SMITHING_TEMPLATE);
			ingredientsEventEntries.addBefore(Items.NETHERITE_INGOT, ModItems.ENERGY_INGOT);
			ingredientsEventEntries.addBefore(Items.COAL, ModItems.WOOD_PELLET);
			ingredientsEventEntries.addAfter(Items.NETHERITE_INGOT, ModItems.RADIANT);
			ingredientsEventEntries.addAfter(ModItems.RADIANT, ModItems.RUBY);
			ingredientsEventEntries.addAfter(ModItems.RUBY, ModItems.SAPPHIRE);
			ingredientsEventEntries.addAfter(ModItems.SAPPHIRE, ModItems.GREEN_SAPPHIRE);
			ingredientsEventEntries.addAfter(ModItems.GREEN_SAPPHIRE, ModItems.BLUE_GARNET);
			ingredientsEventEntries.addAfter(ModItems.BLUE_GARNET, ModItems.PINK_GARNET);
			ingredientsEventEntries.addAfter(ModItems.PINK_GARNET, ModItems.GREEN_GARNET);
			ingredientsEventEntries.addAfter(ModItems.GREEN_GARNET, ModItems.TOPAZ);
			ingredientsEventEntries.addAfter(ModItems.TOPAZ, ModItems.WHITE_TOPAZ);
			ingredientsEventEntries.addAfter(ModItems.WHITE_TOPAZ, ModItems.PERIDOT);
			ingredientsEventEntries.addAfter(ModItems.PERIDOT, ModItems.PYROPE);
			ingredientsEventEntries.addAfter(ModItems.PYROPE, ModItems.JADE);
		});


		// Tools & Music Discs Registry
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(toolEventEntries -> {
			toolEventEntries.addAfter(Items.MUSIC_DISC_OTHERSIDE, ModItems.MUSIC_DISC_ARIA_MATH);
			toolEventEntries.addAfter(ModItems.MUSIC_DISC_ARIA_MATH, ModItems.MUSIC_DISC_BIOME_FEST);
			toolEventEntries.addAfter(ModItems.MUSIC_DISC_BIOME_FEST, ModItems.MUSIC_DISC_DREITON);
			toolEventEntries.addAfter(ModItems.MUSIC_DISC_DREITON, ModItems.MUSIC_DISC_ENDLESS);
			toolEventEntries.addAfter(ModItems.MUSIC_DISC_ENDLESS, ModItems.MUSIC_DISC_FEATHERFALL);
			toolEventEntries.addAfter(ModItems.MUSIC_DISC_FEATHERFALL, ModItems.MUSIC_DISC_INFINITE_AMETHYST);
			toolEventEntries.addAfter(ModItems.MUSIC_DISC_INFINITE_AMETHYST, ModItems.MUSIC_DISC_TASWELL);
			toolEventEntries.addAfter(ModItems.MUSIC_DISC_TASWELL, ModItems.MUSIC_DISC_DEEPER);
			toolEventEntries.addAfter(ModItems.MUSIC_DISC_DEEPER, ModItems.MUSIC_DISC_WATCHER);
			toolEventEntries.addAfter(Items.NETHERITE_HOE, ModItems.RUBY_SHOVEL);
			toolEventEntries.addAfter(ModItems.RUBY_SHOVEL, ModItems.RUBY_PICKAXE);
			toolEventEntries.addAfter(ModItems.RUBY_PICKAXE, ModItems.RUBY_AXE);
			toolEventEntries.addAfter(ModItems.RUBY_AXE, ModItems.RUBY_HOE);
			toolEventEntries.addAfter(ModItems.RUBY_HOE, ModItems.SAPPHIRE_SHOVEL);
			toolEventEntries.addAfter(ModItems.SAPPHIRE_SHOVEL, ModItems.SAPPHIRE_PICKAXE);
			toolEventEntries.addAfter(ModItems.SAPPHIRE_PICKAXE, ModItems.SAPPHIRE_AXE);
			toolEventEntries.addAfter(ModItems.SAPPHIRE_AXE, ModItems.SAPPHIRE_HOE);
		});


		// Weapons & Armors Registry
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(combatEventEntries -> {
			combatEventEntries.addAfter(Items.NETHERITE_SWORD, ModItems.RUBY_SWORD);
			combatEventEntries.addAfter(Items.NETHERITE_AXE, ModItems.RUBY_AXE);
			combatEventEntries.addAfter(ModItems.RUBY_AXE, ModItems.SAPPHIRE_AXE);
			combatEventEntries.addAfter(ModItems.RUBY_SWORD, ModItems.SAPPHIRE_SWORD);
			combatEventEntries.addAfter(ModItems.SAPPHIRE_SWORD, ModItems.RADIANT_SWORD);
			combatEventEntries.addAfter(Items.NETHERITE_BOOTS, ModItems.RUBY_HELMET);
			combatEventEntries.addAfter(ModItems.RUBY_HELMET, ModItems.RUBY_CHESTPLATE);
			combatEventEntries.addAfter(ModItems.RUBY_CHESTPLATE, ModItems.RUBY_LEGGINGS);
			combatEventEntries.addAfter(ModItems.RUBY_LEGGINGS, ModItems.RUBY_BOOTS);
			combatEventEntries.addAfter(ModItems.RUBY_BOOTS, ModItems.SAPPHIRE_HELMET);
			combatEventEntries.addAfter(ModItems.SAPPHIRE_HELMET, ModItems.SAPPHIRE_CHESTPLATE);
			combatEventEntries.addAfter(ModItems.SAPPHIRE_CHESTPLATE, ModItems.SAPPHIRE_LEGGINGS);
			combatEventEntries.addAfter(ModItems.SAPPHIRE_LEGGINGS, ModItems.SAPPHIRE_BOOTS);
            combatEventEntries.addAfter(Items.NETHERITE_SPEAR, ModItems.RUBY_SPEAR);
            combatEventEntries.addAfter(ModItems.RUBY_SPEAR, ModItems.SAPPHIRE_SPEAR);
            combatEventEntries.addAfter(Items.NETHERITE_NAUTILUS_ARMOR, ModItems.RUBY_NAUTILUS_ARMOR);
            combatEventEntries.addAfter(ModItems.RUBY_NAUTILUS_ARMOR, ModItems.SAPPHIRE_NAUTILUS_ARMOR);
		});


		// Natural Stuff Registry
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(naturalEventEntries -> {
			naturalEventEntries.addAfter(Blocks.RAW_GOLD_BLOCK, ModBlocks.RAW_RUBY_BLOCK);
			naturalEventEntries.addAfter(ModBlocks.RAW_RUBY_BLOCK, ModBlocks.RAW_SAPPHIRE_BLOCK);
			naturalEventEntries.addAfter(ModBlocks.RAW_SAPPHIRE_BLOCK, ModBlocks.RAW_GREEN_SAPPHIRE_BLOCK);
			naturalEventEntries.addAfter(ModBlocks.RAW_GREEN_SAPPHIRE_BLOCK, ModBlocks.RAW_BLUE_GARNET_BLOCK);
			naturalEventEntries.addAfter(ModBlocks.RAW_BLUE_GARNET_BLOCK, ModBlocks.RAW_PINK_GARNET_BLOCK);
			naturalEventEntries.addAfter(ModBlocks.RAW_PINK_GARNET_BLOCK, ModBlocks.RAW_GREEN_GARNET_BLOCK);
			naturalEventEntries.addAfter(ModBlocks.RAW_GREEN_GARNET_BLOCK, ModBlocks.RAW_TOPAZ_BLOCK);
			naturalEventEntries.addAfter(ModBlocks.RAW_TOPAZ_BLOCK, ModBlocks.RAW_WHITE_TOPAZ_BLOCK);
			naturalEventEntries.addAfter(ModBlocks.RAW_WHITE_TOPAZ_BLOCK, ModBlocks.RAW_PERIDOT_BLOCK);
			naturalEventEntries.addAfter(ModBlocks.RAW_PERIDOT_BLOCK, ModBlocks.RAW_PYROPE_BLOCK);
			naturalEventEntries.addAfter(ModBlocks.RAW_PYROPE_BLOCK, ModBlocks.RAW_JADE_BLOCK);
			naturalEventEntries.addBefore(Items.TORCHFLOWER_SEEDS, ModItems.TOMATO_SEEDS);
			naturalEventEntries.addBefore(Items.PUMPKIN_SEEDS, ModItems.PINEAPPLE_SEEDS);
			naturalEventEntries.addAfter(Blocks.DEEPSLATE_DIAMOND_ORE, ModBlocks.RUBY_ORE);
			naturalEventEntries.addAfter(ModBlocks.RUBY_ORE, ModBlocks.DEEPSLATE_RUBY_ORE);
			naturalEventEntries.addAfter(ModBlocks.DEEPSLATE_RUBY_ORE, ModBlocks.SAPPHIRE_ORE);
			naturalEventEntries.addAfter(ModBlocks.SAPPHIRE_ORE, ModBlocks.DEEPSLATE_SAPPHIRE_ORE);
			naturalEventEntries.addAfter(ModBlocks.DEEPSLATE_SAPPHIRE_ORE, ModBlocks.GREEN_SAPPHIRE_ORE);
			naturalEventEntries.addAfter(ModBlocks.GREEN_SAPPHIRE_ORE, ModBlocks.DEEPSLATE_GREEN_SAPPHIRE_ORE);
			naturalEventEntries.addAfter(ModBlocks.DEEPSLATE_GREEN_SAPPHIRE_ORE, ModBlocks.BLUE_GARNET_ORE);
			naturalEventEntries.addAfter(ModBlocks.BLUE_GARNET_ORE, ModBlocks.DEEPSLATE_BLUE_GARNET_ORE);
			naturalEventEntries.addAfter(ModBlocks.DEEPSLATE_BLUE_GARNET_ORE, ModBlocks.PINK_GARNET_ORE);
			naturalEventEntries.addAfter(ModBlocks.PINK_GARNET_ORE, ModBlocks.DEEPSLATE_PINK_GARNET_ORE);
			naturalEventEntries.addAfter(ModBlocks.DEEPSLATE_PINK_GARNET_ORE, ModBlocks.GREEN_GARNET_ORE);
			naturalEventEntries.addAfter(ModBlocks.GREEN_GARNET_ORE, ModBlocks.DEEPSLATE_GREEN_GARNET_ORE);
			naturalEventEntries.addAfter(ModBlocks.DEEPSLATE_GREEN_GARNET_ORE, ModBlocks.TOPAZ_ORE);
			naturalEventEntries.addAfter(ModBlocks.TOPAZ_ORE, ModBlocks.DEEPSLATE_TOPAZ_ORE);
			naturalEventEntries.addAfter(ModBlocks.DEEPSLATE_TOPAZ_ORE, ModBlocks.WHITE_TOPAZ_ORE);
			naturalEventEntries.addAfter(ModBlocks.WHITE_TOPAZ_ORE, ModBlocks.DEEPSLATE_WHITE_TOPAZ_ORE);
			naturalEventEntries.addAfter(ModBlocks.DEEPSLATE_WHITE_TOPAZ_ORE, ModBlocks.PERIDOT_ORE);
			naturalEventEntries.addAfter(ModBlocks.PERIDOT_ORE, ModBlocks.DEEPSLATE_PERIDOT_ORE);
			naturalEventEntries.addAfter(ModBlocks.DEEPSLATE_PERIDOT_ORE, ModBlocks.JADE_ORE);
			naturalEventEntries.addAfter(ModBlocks.JADE_ORE, ModBlocks.DEEPSLATE_JADE_ORE);
			naturalEventEntries.addAfter(ModBlocks.DEEPSLATE_JADE_ORE, ModBlocks.PYROPE_ORE);
			naturalEventEntries.addAfter(ModBlocks.PYROPE_ORE, ModBlocks.DEEPSLATE_PYROPE_ORE);
		});


		// Functional Block Registry
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(functionalEventEntries -> {
			functionalEventEntries.addAfter(Blocks.BLAST_FURNACE, ModBlocks.ENERGY_BLOCK);
			functionalEventEntries.addAfter(Blocks.REDSTONE_LAMP, ModBlocks.RUBY_LAMP);
			functionalEventEntries.addAfter(Blocks.SMITHING_TABLE, ModBlocks.GEM_PURIFIER_BLOCK);
		});


		// Redstone Block Registry
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register(redstoneEEventEntries -> {
			redstoneEEventEntries.addAfter(Blocks.REDSTONE_LAMP, ModBlocks.RUBY_LAMP);
		});


		// Food Registry
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(foodEventEntries -> {
			foodEventEntries.addAfter(Items.ENCHANTED_GOLDEN_APPLE, ModItems.DIAMOND_APPLE);
			foodEventEntries.addAfter(Items.POTATO, ModItems.PINEAPPLE);
			foodEventEntries.addAfter(ModItems.PINEAPPLE, ModItems.TOMATO);
		});


		// Gemstone Blocks Registry
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(buildingBlockEventEntries -> {
			buildingBlockEventEntries.addBefore(Blocks.NETHERITE_BLOCK, ModBlocks.ENERGY_BLOCK);
			buildingBlockEventEntries.addAfter(Blocks.NETHERITE_BLOCK, ModBlocks.RUBY_BLOCK);
			buildingBlockEventEntries.addAfter(ModBlocks.RUBY_BLOCK, ModBlocks.RADIANT_BLOCK);
			buildingBlockEventEntries.addAfter(ModBlocks.RADIANT_BLOCK, ModBlocks.SAPPHIRE_BLOCK);
			buildingBlockEventEntries.addAfter(ModBlocks.SAPPHIRE_BLOCK, ModBlocks.GREEN_SAPPHIRE_BLOCK);
			buildingBlockEventEntries.addAfter(ModBlocks.GREEN_SAPPHIRE_BLOCK, ModBlocks.BLUE_GARNET_BLOCK);
			buildingBlockEventEntries.addAfter(ModBlocks.BLUE_GARNET_BLOCK, ModBlocks.PINK_GARNET_BLOCK);
			buildingBlockEventEntries.addAfter(ModBlocks.PINK_GARNET_BLOCK, ModBlocks.GREEN_GARNET_BLOCK);
			buildingBlockEventEntries.addAfter(ModBlocks.GREEN_GARNET_BLOCK, ModBlocks.TOPAZ_BLOCK);
			buildingBlockEventEntries.addAfter(ModBlocks.TOPAZ_BLOCK, ModBlocks.WHITE_TOPAZ_BLOCK);
			buildingBlockEventEntries.addAfter(ModBlocks.WHITE_TOPAZ_BLOCK, ModBlocks.PERIDOT_BLOCK);
			buildingBlockEventEntries.addAfter(ModBlocks.PERIDOT_BLOCK, ModBlocks.PYROPE_BLOCK);
			buildingBlockEventEntries.addAfter(ModBlocks.PYROPE_BLOCK, ModBlocks.JADE_BLOCK);
		});


		// ModItems Registry
		ModItems.register();


		// ModBlocks Registry
		ModBlocks.register();


		// ModSounds & ModBlockSoundGroups Registry
		ModSoundEvents.register();
		ModBlockSoundGroup.register();


		// WorldGeneration Registry
		WorldGeneration.generate();


		//Villagers Registry
		ModVillagerProfessions.register();


		//CustomTrades
		CustomTrades.register();


		//ModifyVanillaLootTables
		VanillaLootTableModifier.modifyVanillaLoot();


		//ModBlockEntityType Registry
		ModBlockEntityType.register();


		//ModScreenHandlers Registry
		ModScreenHandlerType.register();


		//ModRecipes Registry
        Registry.register(Registries.RECIPE_TYPE, Identifier.of(MoreOresModInitializer.MOD_ID, GemPurifierRecipe.Type.ID), GemPurifierRecipe.Type.GEM_POLISHING);
        LOGGER.info("Loading ModRecipeType for " + MOD_ID + " mod.");
        Registry.register(Registries.RECIPE_SERIALIZER, Identifier.of(MoreOresModInitializer.MOD_ID, GemPurifierRecipe.Serializer.ID), GemPurifierRecipe.Serializer.GEM_POLISHING);
        LOGGER.info("Loading ModRecipeSerializer for" + MOD_ID + " mod.");


		//Networking Registry
		ModS2CNetworks.register();
		ModC2SNetworks.register();
		ModS2CPayloadRegistry.registerS2CPackets();
		PayloadTypeRegistry.playC2S().register(GemPurifierButtonClick.ID, GemPurifierButtonClick.PACKET_CODEC);
		PayloadTypeRegistry.playC2S().register(PolishingStateData.ID, PolishingStateData.CODEC);
		ModC2SNetworks.registerServerC2S();

		//ModConsumableComponents Registry
		ModConsumableComponents.register();


		//ModRecipeBookCategories Registry
		ModRecipeBookCategories.register();


		//EnchantmentEffects Registry
		EnchantmentEffects.register();
	}
}