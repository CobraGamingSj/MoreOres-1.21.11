package net.cobra.moreores.data.datagen;

import net.cobra.moreores.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;


public class AdvancementGen extends FabricAdvancementProvider {
    public AdvancementGen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    public void generateAdvancement(RegistryWrapper.WrapperLookup registryLookup, Consumer<AdvancementEntry> consumer) {
        AdvancementEntry rubyGem = Advancement.Builder.create()
                .display(
                        ModItems.RUBY, // The display icon
                        Text.translatable("advancement.moreores.gems"), // The title
                        Text.translatable("advancement.moreores.gems.desc"), // The description
                        Identifier.of("moreores:textures/gui/advancements/backgrounds/gems.png"), // Background image used
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                // The first string used in criterion is the name referenced by other advancements when they want to have 'requirements'
                .criterion("ruby", InventoryChangedCriterion.Conditions.items(ModItems.RUBY))
                .criterion("radiant", InventoryChangedCriterion.Conditions.items(ModItems.RADIANT))
                .criterion("sapphire", InventoryChangedCriterion.Conditions.items(ModItems.SAPPHIRE))
                .criterion("green_sapphire", InventoryChangedCriterion.Conditions.items(ModItems.GREEN_SAPPHIRE))
                .criterion("blue_garnet", InventoryChangedCriterion.Conditions.items(ModItems.BLUE_GARNET))
                .criterion("pink_garnet", InventoryChangedCriterion.Conditions.items(ModItems.PINK_GARNET))
                .criterion("green_garnet", InventoryChangedCriterion.Conditions.items(ModItems.GREEN_GARNET))
                .criterion("topaz", InventoryChangedCriterion.Conditions.items(ModItems.TOPAZ))
                .criterion("white_topaz", InventoryChangedCriterion.Conditions.items(ModItems.WHITE_TOPAZ))
                .criterion("peridot", InventoryChangedCriterion.Conditions.items(ModItems.PERIDOT))
                .criterion("jade", InventoryChangedCriterion.Conditions.items(ModItems.JADE))
                .criterion("pyrope", InventoryChangedCriterion.Conditions.items(ModItems.PYROPE))
                .build(consumer, "is_that_a_gem");

        AdvancementEntry ruby_armor = Advancement.Builder.create()
                .display(
                        ModItems.RUBY_CHESTPLATE,
                        Text.translatable("advancement.moreores.ruby_armor").formatted(Formatting.DARK_AQUA),
                        Text.translatable("advancement.moreores.ruby_armor.desc"),
                        Identifier.of("moreores"),
                        AdvancementFrame.GOAL,
                        true,
                        true,
                        false
                )
                .parent(rubyGem)
                .criterion("ruby_armor", InventoryChangedCriterion.Conditions.items(ModItems.RUBY_HELMET, ModItems.RUBY_CHESTPLATE, ModItems.RUBY_LEGGINGS, ModItems.RUBY_BOOTS))
                .build(consumer, "ruby_armor");

        AdvancementEntry radiant_sword = Advancement.Builder.create()
                .display(
                        ModItems.RADIANT_SWORD,
                        Text.translatable("advancement.moreores.radiant_sword").formatted(Formatting.DARK_PURPLE),
                        Text.translatable("advancement.moreores.radiant_sword.desc"),
                        Identifier.of("moreores:textures/gui/advancements/backgrounds/gems.png"),
                        AdvancementFrame.CHALLENGE,
                        true,
                        true,
                        false
                )
                .parent(rubyGem)
                .criterion("radiant_sword", InventoryChangedCriterion.Conditions.items(ModItems.RADIANT_SWORD))
                .build(consumer, "overpowered");

//        AdvancementEntry sapphire_armor = Advancement.Builder.create()
//                .display(
//                        MinecraftItem.SAPPHIRE
//                )
    }
}
