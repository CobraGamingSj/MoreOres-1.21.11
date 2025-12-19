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
import net.cobra.moreores.recipe.ModRecipeSerializer;
import net.cobra.moreores.recipe.ModRecipeType;
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
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(items -> {
			items.addAfter(Items.RAW_GOLD, ModItems.RAW_RUBY);
			items.addAfter(ModItems.RAW_RUBY, ModItems.RAW_SAPPHIRE);
			items.addAfter(ModItems.RAW_SAPPHIRE, ModItems.RAW_GREEN_SAPPHIRE);
			items.addAfter(ModItems.RAW_GREEN_SAPPHIRE, ModItems.RAW_BLUE_GARNET);
			items.addAfter(ModItems.RAW_BLUE_GARNET, ModItems.RAW_PINK_GARNET);
			items.addAfter(ModItems.RAW_PINK_GARNET, ModItems.RAW_GREEN_GARNET);
			items.addAfter(ModItems.RAW_GREEN_GARNET, ModItems.RAW_TOPAZ);
			items.addAfter(ModItems.RAW_TOPAZ, ModItems.RAW_WHITE_TOPAZ);
			items.addAfter(ModItems.RAW_WHITE_TOPAZ, ModItems.RAW_PERIDOT);
			items.addAfter(ModItems.RAW_PERIDOT, ModItems.RAW_PYROPE);
			items.addAfter(ModItems.RAW_PYROPE, ModItems.RAW_JADE);

			items.addAfter(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, ModItems.RUBY_UPGRADE_SMITHING_TEMPLATE);
			items.addAfter(Items.SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE, ModItems.GUARDIAN_ARMOR_TRIM_SMITHING_TEMPLATE);
			items.addBefore(Items.NETHERITE_INGOT, ModItems.ENERGY_INGOT);
			items.addBefore(Items.COAL, ModItems.WOOD_PELLET);
			items.addAfter(Items.NETHERITE_INGOT, ModItems.RADIANT);
			items.addAfter(ModItems.RADIANT, ModItems.RUBY);
			items.addAfter(ModItems.RUBY, ModItems.SAPPHIRE);
			items.addAfter(ModItems.SAPPHIRE, ModItems.GREEN_SAPPHIRE);
			items.addAfter(ModItems.GREEN_SAPPHIRE, ModItems.BLUE_GARNET);
			items.addAfter(ModItems.BLUE_GARNET, ModItems.PINK_GARNET);
			items.addAfter(ModItems.PINK_GARNET, ModItems.GREEN_GARNET);
			items.addAfter(ModItems.GREEN_GARNET, ModItems.TOPAZ);
			items.addAfter(ModItems.TOPAZ, ModItems.WHITE_TOPAZ);
			items.addAfter(ModItems.WHITE_TOPAZ, ModItems.PERIDOT);
			items.addAfter(ModItems.PERIDOT, ModItems.PYROPE);
			items.addAfter(ModItems.PYROPE, ModItems.JADE);
		});


		// Tools & Music Discs Registry
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(tools -> {
			tools.addAfter(Items.MUSIC_DISC_OTHERSIDE, ModItems.MUSIC_DISC_ARIA_MATH);
			tools.addAfter(ModItems.MUSIC_DISC_ARIA_MATH, ModItems.MUSIC_DISC_BIOME_FEST);
			tools.addAfter(ModItems.MUSIC_DISC_BIOME_FEST, ModItems.MUSIC_DISC_DREITON);
			tools.addAfter(ModItems.MUSIC_DISC_DREITON, ModItems.MUSIC_DISC_ENDLESS);
			tools.addAfter(ModItems.MUSIC_DISC_ENDLESS, ModItems.MUSIC_DISC_FEATHERFALL);
			tools.addAfter(ModItems.MUSIC_DISC_FEATHERFALL, ModItems.MUSIC_DISC_INFINITE_AMETHYST);
			tools.addAfter(ModItems.MUSIC_DISC_INFINITE_AMETHYST, ModItems.MUSIC_DISC_TASWELL);
			tools.addAfter(ModItems.MUSIC_DISC_TASWELL, ModItems.MUSIC_DISC_DEEPER);
			tools.addAfter(ModItems.MUSIC_DISC_DEEPER, ModItems.MUSIC_DISC_WATCHER);
			tools.addAfter(Items.NETHERITE_HOE, ModItems.RUBY_SHOVEL);
			tools.addAfter(ModItems.RUBY_SHOVEL, ModItems.RUBY_PICKAXE);
			tools.addAfter(ModItems.RUBY_PICKAXE, ModItems.RUBY_AXE);
			tools.addAfter(ModItems.RUBY_AXE, ModItems.RUBY_HOE);
			tools.addAfter(ModItems.RUBY_HOE, ModItems.SAPPHIRE_SHOVEL);
			tools.addAfter(ModItems.SAPPHIRE_SHOVEL, ModItems.SAPPHIRE_PICKAXE);
			tools.addAfter(ModItems.SAPPHIRE_PICKAXE, ModItems.SAPPHIRE_AXE);
			tools.addAfter(ModItems.SAPPHIRE_AXE, ModItems.SAPPHIRE_HOE);
		});


		// Weapons & Armors Registry
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(combat -> {
			combat.addAfter(Items.NETHERITE_SWORD, ModItems.RUBY_SWORD);
			combat.addAfter(Items.NETHERITE_AXE, ModItems.RUBY_AXE);
			combat.addAfter(ModItems.RUBY_AXE, ModItems.SAPPHIRE_AXE);
			combat.addAfter(ModItems.RUBY_SWORD, ModItems.SAPPHIRE_SWORD);
			combat.addAfter(ModItems.SAPPHIRE_SWORD, ModItems.RADIANT_SWORD);
			combat.addAfter(Items.NETHERITE_BOOTS, ModItems.RUBY_HELMET);
			combat.addAfter(ModItems.RUBY_HELMET, ModItems.RUBY_CHESTPLATE);
			combat.addAfter(ModItems.RUBY_CHESTPLATE, ModItems.RUBY_LEGGINGS);
			combat.addAfter(ModItems.RUBY_LEGGINGS, ModItems.RUBY_BOOTS);
			combat.addAfter(ModItems.RUBY_BOOTS, ModItems.SAPPHIRE_HELMET);
			combat.addAfter(ModItems.SAPPHIRE_HELMET, ModItems.SAPPHIRE_CHESTPLATE);
			combat.addAfter(ModItems.SAPPHIRE_CHESTPLATE, ModItems.SAPPHIRE_LEGGINGS);
			combat.addAfter(ModItems.SAPPHIRE_LEGGINGS, ModItems.SAPPHIRE_BOOTS);
		});


		// Natural Stuff Registry
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(naturals -> {
			naturals.addAfter(Blocks.RAW_GOLD_BLOCK, ModBlocks.RAW_RUBY_BLOCK);
			naturals.addAfter(ModBlocks.RAW_RUBY_BLOCK, ModBlocks.RAW_SAPPHIRE_BLOCK);
			naturals.addAfter(ModBlocks.RAW_SAPPHIRE_BLOCK, ModBlocks.RAW_GREEN_SAPPHIRE_BLOCK);
			naturals.addAfter(ModBlocks.RAW_GREEN_SAPPHIRE_BLOCK, ModBlocks.RAW_BLUE_GARNET_BLOCK);
			naturals.addAfter(ModBlocks.RAW_BLUE_GARNET_BLOCK, ModBlocks.RAW_PINK_GARNET_BLOCK);
			naturals.addAfter(ModBlocks.RAW_PINK_GARNET_BLOCK, ModBlocks.RAW_GREEN_GARNET_BLOCK);
			naturals.addAfter(ModBlocks.RAW_GREEN_GARNET_BLOCK, ModBlocks.RAW_TOPAZ_BLOCK);
			naturals.addAfter(ModBlocks.RAW_TOPAZ_BLOCK, ModBlocks.RAW_WHITE_TOPAZ_BLOCK);
			naturals.addAfter(ModBlocks.RAW_WHITE_TOPAZ_BLOCK, ModBlocks.RAW_PERIDOT_BLOCK);
			naturals.addAfter(ModBlocks.RAW_PERIDOT_BLOCK, ModBlocks.RAW_PYROPE_BLOCK);
			naturals.addAfter(ModBlocks.RAW_PYROPE_BLOCK, ModBlocks.RAW_JADE_BLOCK);
			naturals.addBefore(Items.TORCHFLOWER_SEEDS, ModItems.TOMATO_SEEDS);
			naturals.addBefore(Items.PUMPKIN_SEEDS, ModItems.PINEAPPLE_SEEDS);
			naturals.addAfter(Blocks.DEEPSLATE_DIAMOND_ORE, ModBlocks.RUBY_ORE);
			naturals.addAfter(ModBlocks.RUBY_ORE, ModBlocks.DEEPSLATE_RUBY_ORE);
			naturals.addAfter(ModBlocks.DEEPSLATE_RUBY_ORE, ModBlocks.SAPPHIRE_ORE);
			naturals.addAfter(ModBlocks.SAPPHIRE_ORE, ModBlocks.DEEPSLATE_SAPPHIRE_ORE);
			naturals.addAfter(ModBlocks.DEEPSLATE_SAPPHIRE_ORE, ModBlocks.GREEN_SAPPHIRE_ORE);
			naturals.addAfter(ModBlocks.GREEN_SAPPHIRE_ORE, ModBlocks.DEEPSLATE_GREEN_SAPPHIRE_ORE);
			naturals.addAfter(ModBlocks.DEEPSLATE_GREEN_SAPPHIRE_ORE, ModBlocks.BLUE_GARNET_ORE);
			naturals.addAfter(ModBlocks.BLUE_GARNET_ORE, ModBlocks.DEEPSLATE_BLUE_GARNET_ORE);
			naturals.addAfter(ModBlocks.DEEPSLATE_BLUE_GARNET_ORE, ModBlocks.PINK_GARNET_ORE);
			naturals.addAfter(ModBlocks.PINK_GARNET_ORE, ModBlocks.DEEPSLATE_PINK_GARNET_ORE);
			naturals.addAfter(ModBlocks.DEEPSLATE_PINK_GARNET_ORE, ModBlocks.GREEN_GARNET_ORE);
			naturals.addAfter(ModBlocks.GREEN_GARNET_ORE, ModBlocks.DEEPSLATE_GREEN_GARNET_ORE);
			naturals.addAfter(ModBlocks.DEEPSLATE_GREEN_GARNET_ORE, ModBlocks.TOPAZ_ORE);
			naturals.addAfter(ModBlocks.TOPAZ_ORE, ModBlocks.DEEPSLATE_TOPAZ_ORE);
			naturals.addAfter(ModBlocks.DEEPSLATE_TOPAZ_ORE, ModBlocks.WHITE_TOPAZ_ORE);
			naturals.addAfter(ModBlocks.WHITE_TOPAZ_ORE, ModBlocks.DEEPSLATE_WHITE_TOPAZ_ORE);
			naturals.addAfter(ModBlocks.DEEPSLATE_WHITE_TOPAZ_ORE, ModBlocks.PERIDOT_ORE);
			naturals.addAfter(ModBlocks.PERIDOT_ORE, ModBlocks.DEEPSLATE_PERIDOT_ORE);
			naturals.addAfter(ModBlocks.DEEPSLATE_PERIDOT_ORE, ModBlocks.JADE_ORE);
			naturals.addAfter(ModBlocks.JADE_ORE, ModBlocks.DEEPSLATE_JADE_ORE);
			naturals.addAfter(ModBlocks.DEEPSLATE_JADE_ORE, ModBlocks.PYROPE_ORE);
			naturals.addAfter(ModBlocks.PYROPE_ORE, ModBlocks.DEEPSLATE_PYROPE_ORE);
		});


		// Functional Block Registry
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(FunctionalBlocks -> {
			FunctionalBlocks.addAfter(Blocks.BLAST_FURNACE, ModBlocks.ENERGY_BLOCK);
			FunctionalBlocks.addAfter(Blocks.REDSTONE_LAMP, ModBlocks.RUBY_LAMP);
			FunctionalBlocks.addAfter(Blocks.SMITHING_TABLE, ModBlocks.GEM_PURIFIER_BLOCK);
		});


		// Redstone Block Registry
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register(redstone -> {
			redstone.addAfter(Blocks.REDSTONE_LAMP, ModBlocks.RUBY_LAMP);
		});


		// Food Registry
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(Foods -> {
			Foods.addAfter(Items.ENCHANTED_GOLDEN_APPLE, ModItems.DIAMOND_APPLE);
			Foods.addAfter(Items.POTATO, ModItems.PINEAPPLE);
			Foods.addAfter(ModItems.PINEAPPLE, ModItems.TOMATO);
		});


		// Gemstone Blocks Registry
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(blocks -> {
			blocks.addBefore(Blocks.NETHERITE_BLOCK, ModBlocks.ENERGY_BLOCK);
			blocks.addAfter(Blocks.NETHERITE_BLOCK, ModBlocks.RUBY_BLOCK);
			blocks.addAfter(ModBlocks.RUBY_BLOCK, ModBlocks.RADIANT_BLOCK);
			blocks.addAfter(ModBlocks.RADIANT_BLOCK, ModBlocks.SAPPHIRE_BLOCK);
			blocks.addAfter(ModBlocks.SAPPHIRE_BLOCK, ModBlocks.GREEN_SAPPHIRE_BLOCK);
			blocks.addAfter(ModBlocks.GREEN_SAPPHIRE_BLOCK, ModBlocks.BLUE_GARNET_BLOCK);
			blocks.addAfter(ModBlocks.BLUE_GARNET_BLOCK, ModBlocks.PINK_GARNET_BLOCK);
			blocks.addAfter(ModBlocks.PINK_GARNET_BLOCK, ModBlocks.GREEN_GARNET_BLOCK);
			blocks.addAfter(ModBlocks.GREEN_GARNET_BLOCK, ModBlocks.TOPAZ_BLOCK);
			blocks.addAfter(ModBlocks.TOPAZ_BLOCK, ModBlocks.WHITE_TOPAZ_BLOCK);
			blocks.addAfter(ModBlocks.WHITE_TOPAZ_BLOCK, ModBlocks.PERIDOT_BLOCK);
			blocks.addAfter(ModBlocks.PERIDOT_BLOCK, ModBlocks.PYROPE_BLOCK);
			blocks.addAfter(ModBlocks.PYROPE_BLOCK, ModBlocks.JADE_BLOCK);
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
		LOGGER.info("Registering Buttons for GemPurifier");


		//ModScreenHandlers Registry
		ModScreenHandlerType.register();


		//ModRecipes Registry
		ModRecipeType.register();
		ModRecipeSerializer.register();


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