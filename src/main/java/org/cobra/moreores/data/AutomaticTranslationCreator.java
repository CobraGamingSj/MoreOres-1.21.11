package org.cobra.moreores.data;

import org.cobra.moreores.MoreOresModInitializer;
import org.cobra.moreores.block.ModBlocks;
import org.cobra.moreores.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class AutomaticTranslationCreator extends FabricLanguageProvider {
    public AutomaticTranslationCreator(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add("gui.button.gp.start", "Start");
        translationBuilder.add("gui.button.gp.pause", "Pause");
        translationBuilder.add("gui.button.gp.resume", "Resume");
        translationBuilder.add("gui.button.gp.stop", "Stop");
        translationBuilder.add("itemGroup.moreores.gemstones", "Gemstones");
        translationBuilder.add("upgrade.moreores.ruby_upgrade", "Smithing Template");
        translationBuilder.add("upgrade.moreores.radiant_upgrade", "Smithing Template");
        translationBuilder.add("item.moreores.smithing_template.applies_to", "Applies to:");
        translationBuilder.add("item.moreores.smithing_template.ruby_upgrade.applies_to", "Netherite Equipment");
        translationBuilder.add("item.moreores.smithing_template.ruby_upgrade.ingredients", "Ruby");
        translationBuilder.add("item.moreores.smithing_template.radiant_upgrade.applies_to", "Sapphire Equipment");
        translationBuilder.add("item.moreores.smithing_template.radiant_upgrade.ingredients", "Sapphire");
        translationBuilder.add("item.moreores.smithing_template.ingredients", "Ingredients:");
        translationBuilder.add("advancement.moreores.gems",  "Is that a gem?");
        translationBuilder.add("advancement.moreores.gems.desc",  "Collect a gemstone");
        translationBuilder.add("advancement.moreores.ruby_armor",  "Cover me in Ruby");
        translationBuilder.add("advancement.moreores.ruby_armor.desc",  "Equip a Ruby Armor");
        translationBuilder.add("advancement.moreores.radiant_sword",  "Overpowered!");
        translationBuilder.add("advancement.moreores.radiant_sword.desc",  "Get a Radiant Sword");
        translationBuilder.add("advancement.moreores.gems_all",  "The gems?");
        translationBuilder.add("advancement.moreores.gems_all.desc",  "Collect every gemstone");
        translationBuilder.add("enchantment.moreores.thunder_striker",  "Thunder Striker");
        translationBuilder.add("entity.minecraft.villager.jeweller",  "Jeweller");
        translationBuilder.add("trim_pattern.moreores.guardian",  "Guardian Armor Trim");
        translationBuilder.add("trim_material.moreores.blue_garnet",  "Blue Garnet Material");
        translationBuilder.add("trim_material.moreores.green_garnet",  "Green Garnet Material");
        translationBuilder.add("trim_material.moreores.green_sapphire",  "Green Sapphire Material");
        translationBuilder.add("trim_material.moreores.sapphire",  "Sapphire Material");
        translationBuilder.add("trim_material.moreores.ruby",  "Ruby Material");
        translationBuilder.add("trim_material.moreores.radiant",  "Radiant Material");
        translationBuilder.add("trim_material.moreores.pyrope",  "Pyrope Material");
        translationBuilder.add("trim_material.moreores.jade",  "Jade Material");

        for (Item item :  Registries.ITEM) {
            Identifier id = Registries.ITEM.getId(item);

            if (id.getNamespace().equals(MoreOresModInitializer.MOD_ID)) {

                if(item == ModItems.RADIANT || item == ModItems.RADIANT_DUST || item == ModItems.RUBY_UPGRADE_SMITHING_TEMPLATE || item == ModItems.GUARDIAN_ARMOR_TRIM_SMITHING_TEMPLATE ||
                item == ModItems.RADIANT_UPGRADE_SMITHING_TEMPLATE || item == ModBlocks.GEM_CRYSTALLIZER_BLOCK.asItem() || item == ModBlocks.GEM_PURIFIER_BLOCK.asItem()) {
                    continue;
                }

                String path = id.getPath();

                String translatedName = MoreOresModInitializer.formatName(path);

                translationBuilder.add(item, translatedName);
            }
        }

        translationBuilder.add(ModItems.RADIANT, "§1Radiant§r");
        translationBuilder.add(ModItems.RADIANT_DUST, "§2Radiant Dust§r");
        translationBuilder.add(ModBlocks.GEM_PURIFIER_BLOCK, "Gem Purifier");
        translationBuilder.add(ModBlocks.GEM_CRYSTALLIZER_BLOCK, "Gem Infusion");
        translationBuilder.add(ModItems.RUBY_UPGRADE_SMITHING_TEMPLATE, "Ruby Upgrade");
        translationBuilder.add(ModItems.RADIANT_UPGRADE_SMITHING_TEMPLATE, "Radiant Upgrade");
        translationBuilder.add(ModItems.GUARDIAN_ARMOR_TRIM_SMITHING_TEMPLATE, "Guardian Armor Trim");
    }
}
