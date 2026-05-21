package org.cobra.moreores;

import org.cobra.moreores.data.datagen.*;
import org.cobra.moreores.data.datagen.*;
import org.cobra.moreores.enchantment.ModEnchantments;
import org.cobra.moreores.item.equipment.trim.ModArmorTrimMaterials;
import org.cobra.moreores.item.equipment.trim.ModArmorTrimPatterns;
import org.cobra.moreores.world.gen.feature.ModConfiguredFeatures;
import org.cobra.moreores.world.gen.feature.ModPlacedFeatures;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class MoreOresDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(DynamicRegistry::new);
		pack.addProvider(ItemTagGen::new);
		pack.addProvider(BlockTagGen::new);
		pack.addProvider(AutomaticModelGenerator::new);
		pack.addProvider(AdvancementGen::new);
		pack.addProvider(AutomaticLootTableCreator::new);
		pack.addProvider(PointOfInterestTypeTagGen::new);
		pack.addProvider(AutomaticRecipeCreator::new);
		pack.addProvider(AutomaticTranslationCreator::new);
	}

	@Override
	public void buildRegistry(RegistryBuilder registryBuilder) {
		registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap);
		registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, ModPlacedFeatures::bootstrap);
		registryBuilder.addRegistry(RegistryKeys.TRIM_MATERIAL, ModArmorTrimMaterials::bootstrap);
		registryBuilder.addRegistry(RegistryKeys.TRIM_PATTERN, ModArmorTrimPatterns::bootstrap);
		registryBuilder.addRegistry(RegistryKeys.ENCHANTMENT, ModEnchantments::bootstrap);
	}
}