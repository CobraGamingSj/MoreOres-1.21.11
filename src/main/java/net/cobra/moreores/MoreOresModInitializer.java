package net.cobra.moreores;

import net.cobra.moreores.block.ModBlocks;
import net.cobra.moreores.block.entity.ModBlockEntityType;
import net.cobra.moreores.client.gui.screen.ModScreenHandlerType;
import net.cobra.moreores.enchantment.entity.effect.EnchantmentEffects;
import net.cobra.moreores.item.ModItems;
import net.cobra.moreores.networking.ModC2SNetworks;
import net.cobra.moreores.networking.ModS2CNetworks;
import net.cobra.moreores.networking.ModS2CPayloadRegistry;
import net.cobra.moreores.networking.block.data.GemPurifierButtonClickPayload;
import net.cobra.moreores.networking.block.data.PolishingStateDataPayload;
import net.cobra.moreores.recipe.GemInfusionRecipe;
import net.cobra.moreores.recipe.GemPurifierRecipe;
import net.cobra.moreores.recipe.book.ModRecipeBookCategories;
import net.cobra.moreores.recipe.display.GemInfusionRecipeDisplay;
import net.cobra.moreores.recipe.display.GemPolishingRecipeDisplay;
import net.cobra.moreores.registry.BirthdayRewardState;
import net.cobra.moreores.sound.ModBlockSoundGroup;
import net.cobra.moreores.util.VillagerTrades;
import net.cobra.moreores.util.VanillaLootTableModifier;
import net.cobra.moreores.village.ModVillagerProfessions;
import net.cobra.moreores.world.gen.WorldGeneration;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.message.v1.ServerMessageEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.registry.FuelRegistryEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.*;
import net.minecraft.network.packet.s2c.play.SubtitleS2CPacket;
import net.minecraft.network.packet.s2c.play.TitleFadeS2CPacket;
import net.minecraft.network.packet.s2c.play.TitleS2CPacket;
import net.minecraft.recipe.Recipe;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MoreOresModInitializer implements ModInitializer {

	public static final String MOD_ID = "moreores";
	public static final String ID = "minecraft";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static Identifier id(String id) {
		return Identifier.of(MOD_ID, id);
	}

	public static RegistryKey<Item> itemKey(String registryKey) {
		return RegistryKey.of(RegistryKeys.ITEM, id(registryKey));
	}

	public static String formatName(String path) {
		String[] words = path.split("_");
		StringBuilder builder = new StringBuilder();

		for(int i = 0; i < words.length; i++) {
			String word = words[i];

			builder.append(Character.toUpperCase(word.charAt(0)))
					.append(word.substring(1));

			if(i < words.length - 1) {
				builder.append(" ");
			}
		}
		return builder.toString();
	}

	public static RegistryKey<Recipe<?>> recipeKey(String recipeName) {
		return RegistryKey.of(RegistryKeys.RECIPE, id(recipeName));
	}


	// Gemstones Item Group
	public static final ItemGroup GEMSTONES = FabricItemGroup.builder()
			.icon(() -> new ItemStack(ModItems.RADIANT))
			.displayName(Text.translatable("itemGroup.moreores.gemstones"))
			.entries((context, entries) -> {
				entries.add(ModItems.RUBY);
				entries.add(ModItems.RADIANT);
				entries.add(ModItems.SAPPHIRE);
				entries.add(ModItems.GREEN_SAPPHIRE);
				entries.add(ModItems.BLUE_GARNET);
				entries.add(ModItems.PINK_GARNET);
				entries.add(ModItems.GREEN_GARNET);
				entries.add(ModItems.KYAWTHUITE);
				entries.add(ModItems.TOPAZ);
				entries.add(ModItems.WHITE_TOPAZ);
				entries.add(ModItems.PERIDOT);
				entries.add(ModItems.JADE);
				entries.add(ModItems.PYROPE);
				entries.add(ModItems.CRIMSON_GARNET);
				entries.add(ModItems.CRYSTALLITE);
				entries.add(ModItems.RADIANT_AMETHYST);
				entries.add(ModItems.MOONSTONE);
				entries.add(ModItems.LIMESTONE);
				entries.add(ModItems.QUARTSIDIAN);
				entries.add(ModItems.ALEXANDRITE);
				entries.add(ModItems.ORANGE_ZIRCON);
				entries.add(ModItems.OPAL);
				entries.add(ModItems.GRANDIDIERITE);
				entries.add(ModItems.RED_BERYL);
				entries.add(ModItems.KASHMIR_SAPPHIRE);
				entries.add(ModBlocks.RUBY_BLOCK);
				entries.add(ModBlocks.RADIANT_BLOCK);
				entries.add(ModBlocks.SAPPHIRE_BLOCK);
				entries.add(ModBlocks.GREEN_SAPPHIRE_BLOCK);
				entries.add(ModBlocks.BLUE_GARNET_BLOCK);
				entries.add(ModBlocks.PINK_GARNET_BLOCK);
				entries.add(ModBlocks.GREEN_GARNET_BLOCK);
				entries.add(ModBlocks.KYAWTHUITE_BLOCK);
				entries.add(ModBlocks.TOPAZ_BLOCK);
				entries.add(ModBlocks.WHITE_TOPAZ_BLOCK);
				entries.add(ModBlocks.PERIDOT_BLOCK);
				entries.add(ModBlocks.JADE_BLOCK);
				entries.add(ModBlocks.PYROPE_BLOCK);
				entries.add(ModBlocks.CRIMSON_GARNET_BLOCK);
				entries.add(ModBlocks.CRYSTALLITE_BLOCK);
				entries.add(ModBlocks.RADIANT_AMETHYST_BLOCK);
				entries.add(ModBlocks.MOONSTONE_BLOCK);
				entries.add(ModBlocks.LIMESTONE_BLOCK);
				entries.add(ModBlocks.QUARTSIDIAN_BLOCK);
				entries.add(ModBlocks.ALEXANDRITE_BLOCK);
				entries.add(ModBlocks.ORANGE_ZIRCON_BLOCK);
				entries.add(ModBlocks.OPAL_BLOCK);
				entries.add(ModBlocks.GRANDIDIERITE_BLOCK);
				entries.add(ModBlocks.RED_BERYL_BLOCK);
				entries.add(ModBlocks.KASHMIR_SAPPHIRE_BLOCK);
			}).build();

    public static RegistryKey<Block> setBlockKey(String id) {
		return RegistryKey.of(RegistryKeys.BLOCK, id(id));
    }

    @Override
	public void onInitialize() {


		ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
			ServerPlayerEntity player = handler.getPlayer();
			String modVersion = FabricLoader.getInstance().getModContainer(MOD_ID).orElseThrow().getMetadata().getVersion().getFriendlyString();

			player.addCommandTag("moreores_first_join");
			player.networkHandler.sendPacket(new TitleS2CPacket(Text.literal("MoreOres+").formatted(Formatting.DARK_PURPLE, Formatting.BOLD)));
			player.networkHandler.sendPacket(new SubtitleS2CPacket(Text.literal(modVersion).formatted(Formatting.YELLOW)));
			player.networkHandler.sendPacket(new TitleFadeS2CPacket(20, 100, 20));
		});


		ServerMessageEvents.CHAT_MESSAGE.register((msg, sender, params) -> {
			String playerSignature = msg.getSignedContent().toLowerCase();
			if(playerSignature.contains("happy birthday cobra") || playerSignature.contains("happy birthday") || playerSignature.contains("happy bday") || playerSignature.contains("happy bday cobra")) {
				giveBirthdayRewards(sender);
			}
		});


		// Gemstones Item Group Registry
		Registry.register(Registries.ITEM_GROUP, id("gemstones"), GEMSTONES);


		// Fuel Registry
		FuelRegistryEvents.BUILD.register(((builder, context) -> {
			builder.add(ModItems.ENERGY_INGOT, 24500);
			builder.add(ModBlocks.ENERGY_BLOCK, 27500);
		}));


		// Gemstones & Ingots Registry
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ingredientsEventEntries -> {
			ingredientsEventEntries.addAfter(Items.RAW_GOLD, ModItems.RAW_RUBY);
			ingredientsEventEntries.addAfter(ModItems.RAW_RUBY, ModItems.RAW_SAPPHIRE);
			ingredientsEventEntries.addAfter(ModItems.RAW_SAPPHIRE, ModItems.RAW_GREEN_SAPPHIRE);
			ingredientsEventEntries.addAfter(ModItems.RAW_GREEN_SAPPHIRE, ModItems.RAW_BLUE_GARNET);
			ingredientsEventEntries.addAfter(ModItems.RAW_BLUE_GARNET, ModItems.RAW_PINK_GARNET);
			ingredientsEventEntries.addAfter(ModItems.RAW_PINK_GARNET, ModItems.RAW_GREEN_GARNET);
			ingredientsEventEntries.addAfter(ModItems.RAW_GREEN_GARNET, ModItems.RAW_KYAWTHUITE);
			ingredientsEventEntries.addAfter(ModItems.RAW_KYAWTHUITE, ModItems.RAW_TOPAZ);
			ingredientsEventEntries.addAfter(ModItems.RAW_TOPAZ, ModItems.RAW_WHITE_TOPAZ);
			ingredientsEventEntries.addAfter(ModItems.RAW_WHITE_TOPAZ, ModItems.RAW_PERIDOT);
			ingredientsEventEntries.addAfter(ModItems.RAW_PERIDOT, ModItems.RAW_PYROPE);
			ingredientsEventEntries.addAfter(ModItems.RAW_PYROPE, ModItems.RAW_JADE);

			ingredientsEventEntries.addAfter(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, ModItems.RUBY_UPGRADE_SMITHING_TEMPLATE);
			ingredientsEventEntries.addAfter(ModItems.RUBY_UPGRADE_SMITHING_TEMPLATE, ModItems.RADIANT_UPGRADE_SMITHING_TEMPLATE);
			ingredientsEventEntries.addAfter(Items.SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE, ModItems.GUARDIAN_ARMOR_TRIM_SMITHING_TEMPLATE);
			ingredientsEventEntries.addBefore(Items.NETHERITE_INGOT, ModItems.ENERGY_INGOT);
			ingredientsEventEntries.addAfter(Items.BLAZE_POWDER, ModItems.RADIANT_DUST);
			ingredientsEventEntries.addAfter(Items.NETHERITE_INGOT, ModItems.RADIANT);
			ingredientsEventEntries.addAfter(ModItems.RADIANT, ModItems.RUBY);
			ingredientsEventEntries.addAfter(ModItems.RUBY, ModItems.SAPPHIRE);
			ingredientsEventEntries.addAfter(ModItems.SAPPHIRE, ModItems.GREEN_SAPPHIRE);
			ingredientsEventEntries.addAfter(ModItems.GREEN_SAPPHIRE, ModItems.BLUE_GARNET);
			ingredientsEventEntries.addAfter(ModItems.BLUE_GARNET, ModItems.PINK_GARNET);
			ingredientsEventEntries.addAfter(ModItems.PINK_GARNET, ModItems.GREEN_GARNET);
			ingredientsEventEntries.addAfter(ModItems.GREEN_GARNET, ModItems.KYAWTHUITE);
			ingredientsEventEntries.addAfter(ModItems.KYAWTHUITE, ModItems.TOPAZ);
			ingredientsEventEntries.addAfter(ModItems.TOPAZ, ModItems.WHITE_TOPAZ);
			ingredientsEventEntries.addAfter(ModItems.WHITE_TOPAZ, ModItems.PERIDOT);
			ingredientsEventEntries.addAfter(ModItems.PERIDOT, ModItems.PYROPE);
			ingredientsEventEntries.addAfter(ModItems.PYROPE, ModItems.JADE);
		});


		// Tools & Music Discs Registry
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(toolEventEntries -> {
			toolEventEntries.addAfter(Items.NETHERITE_HOE, ModItems.RUBY_SHOVEL);
			toolEventEntries.addAfter(ModItems.RUBY_SHOVEL, ModItems.RUBY_PICKAXE);
			toolEventEntries.addAfter(ModItems.RUBY_PICKAXE, ModItems.RUBY_AXE);
			toolEventEntries.addAfter(ModItems.RUBY_AXE, ModItems.RUBY_HOE);
			toolEventEntries.addAfter(ModItems.RUBY_HOE, ModItems.SAPPHIRE_SHOVEL);
			toolEventEntries.addAfter(ModItems.SAPPHIRE_SHOVEL, ModItems.SAPPHIRE_PICKAXE);
			toolEventEntries.addAfter(ModItems.SAPPHIRE_PICKAXE, ModItems.SAPPHIRE_AXE);
			toolEventEntries.addAfter(ModItems.SAPPHIRE_AXE, ModItems.SAPPHIRE_HOE);
			toolEventEntries.addAfter(ModItems.SAPPHIRE_HOE, ModItems.RADIANT_SHOVEL);
			toolEventEntries.addAfter(ModItems.RADIANT_SHOVEL, ModItems.RADIANT_PICKAXE);
			toolEventEntries.addAfter(ModItems.RADIANT_PICKAXE, ModItems.RADIANT_AXE);
			toolEventEntries.addAfter(ModItems.RADIANT_AXE, ModItems.RADIANT_HOE);
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
			combatEventEntries.addAfter(ModItems.SAPPHIRE_BOOTS, ModItems.RADIANT_HELMET);
			combatEventEntries.addAfter(ModItems.RADIANT_HELMET, ModItems.RADIANT_CHESTPLATE);
			combatEventEntries.addAfter(ModItems.RADIANT_CHESTPLATE, ModItems.RADIANT_LEGGINGS);
			combatEventEntries.addAfter(ModItems.RADIANT_LEGGINGS, ModItems.RADIANT_BOOTS);
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
			naturalEventEntries.addAfter(ModBlocks.RAW_GREEN_GARNET_BLOCK, ModBlocks.RAW_KYAWTHUITE_BLOCK);
			naturalEventEntries.addAfter(ModBlocks.RAW_KYAWTHUITE_BLOCK, ModBlocks.RAW_TOPAZ_BLOCK);
			naturalEventEntries.addAfter(ModBlocks.RAW_TOPAZ_BLOCK, ModBlocks.RAW_WHITE_TOPAZ_BLOCK);
			naturalEventEntries.addAfter(ModBlocks.RAW_WHITE_TOPAZ_BLOCK, ModBlocks.RAW_PERIDOT_BLOCK);
			naturalEventEntries.addAfter(ModBlocks.RAW_PERIDOT_BLOCK, ModBlocks.RAW_PYROPE_BLOCK);
			naturalEventEntries.addAfter(ModBlocks.RAW_PYROPE_BLOCK, ModBlocks.RAW_JADE_BLOCK);
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
			naturalEventEntries.addAfter(ModBlocks.DEEPSLATE_GREEN_GARNET_ORE, ModBlocks.KYAWTHUITE_ORE);
			naturalEventEntries.addAfter(ModBlocks.KYAWTHUITE_ORE, ModBlocks.DEEPSLATE_KYAWTHUITE_ORE);
			naturalEventEntries.addAfter(ModBlocks.DEEPSLATE_KYAWTHUITE_ORE, ModBlocks.TOPAZ_ORE);
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
			functionalEventEntries.addAfter(ModBlocks.GEM_PURIFIER_BLOCK, ModBlocks.GEM_INFUSION_BLOCK);
		});


		// Redstone Block Registry
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register(redstoneEEventEntries -> {
			redstoneEEventEntries.addAfter(Blocks.REDSTONE_LAMP, ModBlocks.RUBY_LAMP);
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
			buildingBlockEventEntries.addAfter(ModBlocks.GREEN_GARNET_BLOCK, ModBlocks.KYAWTHUITE_BLOCK);
			buildingBlockEventEntries.addAfter(ModBlocks.KYAWTHUITE_BLOCK, ModBlocks.TOPAZ_BLOCK);
			buildingBlockEventEntries.addAfter(ModBlocks.TOPAZ_BLOCK, ModBlocks.WHITE_TOPAZ_BLOCK);
			buildingBlockEventEntries.addAfter(ModBlocks.WHITE_TOPAZ_BLOCK, ModBlocks.PERIDOT_BLOCK);
			buildingBlockEventEntries.addAfter(ModBlocks.PERIDOT_BLOCK, ModBlocks.JADE_BLOCK);
			buildingBlockEventEntries.addAfter(ModBlocks.JADE_BLOCK, ModBlocks.PYROPE_BLOCK);
			buildingBlockEventEntries.addAfter(ModBlocks.PYROPE_BLOCK, ModBlocks.CRIMSON_GARNET_BLOCK);
			buildingBlockEventEntries.addAfter(ModBlocks.CRIMSON_GARNET_BLOCK, ModBlocks.CRYSTALLITE_BLOCK);
			buildingBlockEventEntries.addAfter(ModBlocks.CRYSTALLITE_BLOCK, ModBlocks.RADIANT_AMETHYST_BLOCK);
			buildingBlockEventEntries.addAfter(ModBlocks.RADIANT_AMETHYST_BLOCK, ModBlocks.MOONSTONE_BLOCK);
			buildingBlockEventEntries.addAfter(ModBlocks.MOONSTONE_BLOCK, ModBlocks.LIMESTONE_BLOCK);
			buildingBlockEventEntries.addAfter(ModBlocks.LIMESTONE_BLOCK, ModBlocks.QUARTSIDIAN_BLOCK);
			buildingBlockEventEntries.addAfter(ModBlocks.QUARTSIDIAN_BLOCK, ModBlocks.ALEXANDRITE_BLOCK);
			buildingBlockEventEntries.addAfter(ModBlocks.ALEXANDRITE_BLOCK, ModBlocks.ORANGE_ZIRCON_BLOCK);
			buildingBlockEventEntries.addAfter(ModBlocks.ORANGE_ZIRCON_BLOCK, ModBlocks.OPAL_BLOCK);
			buildingBlockEventEntries.addAfter(ModBlocks.OPAL_BLOCK, ModBlocks.GRANDIDIERITE_BLOCK);
			buildingBlockEventEntries.addAfter(ModBlocks.GRANDIDIERITE_BLOCK, ModBlocks.RED_BERYL_BLOCK);
			buildingBlockEventEntries.addAfter(ModBlocks.RED_BERYL_BLOCK, ModBlocks.KASHMIR_SAPPHIRE_BLOCK);
		});


		// ModItems Registry
		ModItems.register();


		// ModBlocks Registry
		ModBlocks.register();


		// ModSounds & ModBlockSoundGroups Registry
		ModBlockSoundGroup.register();


		// WorldGeneration Registry
		WorldGeneration.generate();


		//Villagers Registry
		ModVillagerProfessions.register();


		//CustomTrades
		VillagerTrades.register();


		//ModifyVanillaLootTables
		VanillaLootTableModifier.modifyVanillaLoot();


		//ModBlockEntityType Registry
		ModBlockEntityType.register();


		//ModScreenHandlers Registry
		ModScreenHandlerType.register();


		//ModRecipes Registry
        Registry.register(Registries.RECIPE_TYPE, Identifier.of(MoreOresModInitializer.MOD_ID, GemPurifierRecipe.Type.ID), GemPurifierRecipe.Type.INSTANCE);
        Registry.register(Registries.RECIPE_TYPE, Identifier.of(MoreOresModInitializer.MOD_ID, GemInfusionRecipe.Type.ID), GemInfusionRecipe.Type.INSTANCE);
        LOGGER.info("Loading ModRecipeType for " + MOD_ID + " mod.");
        Registry.register(Registries.RECIPE_SERIALIZER, Identifier.of(MoreOresModInitializer.MOD_ID, GemPurifierRecipe.Serializer.ID), GemPurifierRecipe.Serializer.INSTANCE);
        Registry.register(Registries.RECIPE_SERIALIZER, Identifier.of(MoreOresModInitializer.MOD_ID, GemInfusionRecipe.Serializer.ID), GemInfusionRecipe.Serializer.INSTANCE);
        LOGGER.info("Loading ModRecipeSerializer for" + MOD_ID + " mod.");


		//Networking Registry
		ModS2CNetworks.register();
		ModC2SNetworks.register();
		ModS2CPayloadRegistry.registerS2CPackets();
		PayloadTypeRegistry.playC2S().register(GemPurifierButtonClickPayload.ID, GemPurifierButtonClickPayload.PACKET_CODEC);
		PayloadTypeRegistry.playC2S().register(PolishingStateDataPayload.ID, PolishingStateDataPayload.CODEC);
		ModC2SNetworks.registerServerC2S();


		//ModRecipeBookCategories Registry
		ModRecipeBookCategories.register();
        Registry.register(Registries.RECIPE_DISPLAY, MoreOresModInitializer.id("gem_polishing"), GemPolishingRecipeDisplay.SERIALIZER);
        Registry.register(Registries.RECIPE_DISPLAY, MoreOresModInitializer.id("gem_infusion"), GemInfusionRecipeDisplay.SERIALIZER);


		//EnchantmentEffects Registry
		EnchantmentEffects.register();
	}


	public static void giveBirthdayRewards(ServerPlayerEntity serverPlayer) {
		ServerWorld world = serverPlayer.getEntityWorld();
		BirthdayRewardState state = BirthdayRewardState.get(world);

		if(state.hasClaimed(serverPlayer.getUuid())) {
			serverPlayer.sendMessage(Text.literal("⚠️ You can claim the reward only once!").formatted(Formatting.RED));
			return;
		}

		serverPlayer.giveItemStack(new ItemStack(ModItems.RUBY, 32));
		serverPlayer.giveItemStack(new ItemStack(ModItems.RUBY_UPGRADE_SMITHING_TEMPLATE, 9));
		serverPlayer.giveItemStack(new ItemStack(Items.ENCHANTED_GOLDEN_APPLE, 5));
		serverPlayer.sendMessage(
				Text.literal("🎉 [MoreOres+] ")
						.formatted(Formatting.GOLD)
						.append(Text.literal("Secret unlocked! ")
								.formatted(Formatting.YELLOW))
						.append(Text.literal("Happy Birthday CobraGamingSJ ❤️")
								.formatted(Formatting.LIGHT_PURPLE)),
				false
		);

		state.setClaimed(serverPlayer.getUuid());
	}
}