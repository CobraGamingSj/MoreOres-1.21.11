package net.cobra.moreores.data.datagen;

import net.cobra.moreores.MoreOresModInitializer;
import net.cobra.moreores.block.ModBlocks;
import net.cobra.moreores.client.recipe.GemPolishingRecipeJsonBuilder;
import net.cobra.moreores.item.ModItems;
import net.cobra.moreores.item.equipment.trim.ModArmorTrimPatterns;
import net.cobra.moreores.registry.ModItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.data.recipe.SmithingTransformRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class RecipeGen extends FabricRecipeProvider {
    public RecipeGen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup wrapperLookup, RecipeExporter recipeExporter) {
        return new RecipeGenerator(wrapperLookup, recipeExporter) {
            @Override
            public void generate() {
                int defaultSmeltingTime = 1500;
                int defaultBlastingTime = 750;
                List<ItemConvertible> RUBY_SMELTABLES = List.of(ModBlocks.RUBY_ORE, ModBlocks.DEEPSLATE_RUBY_ORE, ModItems.RAW_RUBY);
                List<ItemConvertible> SAPPHIRE_SMELTABLES = List.of(ModBlocks.SAPPHIRE_ORE, ModBlocks.DEEPSLATE_SAPPHIRE_ORE, ModItems.RAW_SAPPHIRE);
                List<ItemConvertible> GREEN_SAPPHIRE_SMELTABLES = List.of(ModBlocks.GREEN_SAPPHIRE_ORE, ModBlocks.DEEPSLATE_GREEN_SAPPHIRE_ORE, ModItems.RAW_GREEN_SAPPHIRE);
                List<ItemConvertible> BLUE_GARNET_SMELTABLES = List.of(ModBlocks.BLUE_GARNET_ORE, ModBlocks.DEEPSLATE_BLUE_GARNET_ORE, ModItems.RAW_BLUE_GARNET);
                List<ItemConvertible> PINK_GARNET_SMELTABLES = List.of(ModBlocks.PINK_GARNET_ORE, ModBlocks.DEEPSLATE_PINK_GARNET_ORE, ModItems.RAW_PINK_GARNET);
                List<ItemConvertible> GREEN_GARNET_SMELTABLES = List.of(ModBlocks.GREEN_GARNET_ORE, ModBlocks.DEEPSLATE_GREEN_GARNET_ORE, ModItems.RAW_GREEN_GARNET);
                List<ItemConvertible> TOPAZ_SMELTABLES = List.of(ModBlocks.TOPAZ_ORE, ModBlocks.DEEPSLATE_TOPAZ_ORE, ModItems.RAW_TOPAZ);
                List<ItemConvertible> WHITE_TOPAZ_SMELTABLES = List.of(ModBlocks.WHITE_TOPAZ_ORE, ModBlocks.DEEPSLATE_WHITE_TOPAZ_ORE, ModItems.RAW_WHITE_TOPAZ);
                List<ItemConvertible> PERIDOT_SMELTABLES = List.of(ModBlocks.PERIDOT_ORE, ModBlocks.DEEPSLATE_PERIDOT_ORE, ModItems.RAW_PERIDOT);
                List<ItemConvertible> JADE_SMELTABLES = List.of(ModBlocks.JADE_ORE, ModBlocks.DEEPSLATE_JADE_ORE, ModItems.RAW_JADE);
                List<ItemConvertible> PYROPE_SMELTABLES = List.of(ModBlocks.PYROPE_ORE, ModBlocks.DEEPSLATE_PYROPE_ORE, ModItems.RAW_PYROPE);

                offerSmelting(RUBY_SMELTABLES, RecipeCategory.MISC, ModItems.RUBY, 0.15f, defaultSmeltingTime, "ruby");
                offerSmelting(SAPPHIRE_SMELTABLES, RecipeCategory.MISC, ModItems.SAPPHIRE, 0.15f, defaultSmeltingTime, "sapphire");
                offerSmelting(GREEN_SAPPHIRE_SMELTABLES, RecipeCategory.MISC, ModItems.GREEN_SAPPHIRE, 0.15f, defaultSmeltingTime, "green_sapphire");
                offerSmelting(BLUE_GARNET_SMELTABLES, RecipeCategory.MISC, ModItems.BLUE_GARNET, 0.15f, defaultSmeltingTime, "blue_garnet");
                offerSmelting(PINK_GARNET_SMELTABLES, RecipeCategory.MISC, ModItems.PINK_GARNET, 0.15f, defaultSmeltingTime, "pink_garnet");
                offerSmelting(GREEN_GARNET_SMELTABLES, RecipeCategory.MISC, ModItems.GREEN_GARNET, 0.15f, defaultSmeltingTime, "green_garnet");
                offerSmelting(TOPAZ_SMELTABLES, RecipeCategory.MISC, ModItems.TOPAZ, 0.15f, defaultSmeltingTime, "topaz");
                offerSmelting(WHITE_TOPAZ_SMELTABLES, RecipeCategory.MISC, ModItems.WHITE_TOPAZ, 0.15f, defaultSmeltingTime, "white_topaz");
                offerSmelting(PERIDOT_SMELTABLES, RecipeCategory.MISC, ModItems.PERIDOT, 0.15f, defaultSmeltingTime, "peridot");
                offerSmelting(JADE_SMELTABLES, RecipeCategory.MISC, ModItems.JADE, 0.15f, defaultSmeltingTime, "jade");
                offerSmelting(PYROPE_SMELTABLES, RecipeCategory.MISC, ModItems.PYROPE, 0.15f, defaultSmeltingTime, "pyrope");

                offerBlasting(RUBY_SMELTABLES, RecipeCategory.MISC, ModItems.RUBY, 0.15f, defaultBlastingTime, "ruby");
                offerBlasting(SAPPHIRE_SMELTABLES, RecipeCategory.MISC, ModItems.SAPPHIRE, 0.15f, defaultBlastingTime, "sapphire");
                offerBlasting(GREEN_SAPPHIRE_SMELTABLES, RecipeCategory.MISC, ModItems.GREEN_SAPPHIRE, 0.15f, defaultBlastingTime, "green_sapphire");
                offerBlasting(BLUE_GARNET_SMELTABLES, RecipeCategory.MISC, ModItems.BLUE_GARNET, 0.15f, defaultBlastingTime, "blue_garnet");
                offerBlasting(PINK_GARNET_SMELTABLES, RecipeCategory.MISC, ModItems.PINK_GARNET, 0.15f, defaultBlastingTime, "pink_garnet");
                offerBlasting(GREEN_GARNET_SMELTABLES, RecipeCategory.MISC, ModItems.GREEN_GARNET, 0.15f, defaultBlastingTime, "green_garnet");
                offerBlasting(TOPAZ_SMELTABLES, RecipeCategory.MISC, ModItems.TOPAZ, 0.15f, defaultBlastingTime, "topaz");
                offerBlasting(WHITE_TOPAZ_SMELTABLES, RecipeCategory.MISC, ModItems.WHITE_TOPAZ, 0.15f, defaultBlastingTime, "white_topaz");
                offerBlasting(PERIDOT_SMELTABLES, RecipeCategory.MISC, ModItems.PERIDOT, 0.15f, defaultBlastingTime, "peridot");
                offerBlasting(JADE_SMELTABLES, RecipeCategory.MISC, ModItems.JADE, 0.15f, defaultBlastingTime, "jade");
                offerBlasting(PYROPE_SMELTABLES, RecipeCategory.MISC, ModItems.PYROPE, 0.15f, defaultBlastingTime, "pyrope");

                offerBlasting(List.of(ModItems.RUBY), RecipeCategory.MISC, Items.NETHERITE_INGOT, 0.15f, 450, "netherite");

                // Armor
                SmithingTransformRecipeJsonBuilder.create(
                                Ingredient.ofItem(ModItems.RUBY_UPGRADE_SMITHING_TEMPLATE),
                                Ingredient.ofItem(Items.NETHERITE_HELMET),
                                this.ingredientFromTag(ModItemTags.RUBY_TOOL_MATERIALS),
                                RecipeCategory.COMBAT, ModItems.RUBY_HELMET
                        )
                        .criterion("has_ruby", conditionsFromTag(ModItemTags.RUBY_TOOL_MATERIALS))
                        .offerTo(exporter, getItemPath(ModItems.RUBY_HELMET) + "_smithing");

                SmithingTransformRecipeJsonBuilder.create(
                                Ingredient.ofItem(ModItems.RUBY_UPGRADE_SMITHING_TEMPLATE),
                                Ingredient.ofItem(Items.NETHERITE_CHESTPLATE),
                                this.ingredientFromTag(ModItemTags.RUBY_TOOL_MATERIALS),
                                RecipeCategory.COMBAT, ModItems.RUBY_CHESTPLATE
                        )
                        .criterion("has_ruby", conditionsFromTag(ModItemTags.RUBY_TOOL_MATERIALS))
                        .offerTo(exporter, getItemPath(ModItems.RUBY_CHESTPLATE) + "_smithing");

                SmithingTransformRecipeJsonBuilder.create(
                                Ingredient.ofItem(ModItems.RUBY_UPGRADE_SMITHING_TEMPLATE),
                                Ingredient.ofItem(Items.NETHERITE_LEGGINGS),
                                this.ingredientFromTag(ModItemTags.RUBY_TOOL_MATERIALS),
                                RecipeCategory.COMBAT, ModItems.RUBY_LEGGINGS
                        )
                        .criterion("has_ruby", conditionsFromTag(ModItemTags.RUBY_TOOL_MATERIALS))
                        .offerTo(exporter, getItemPath(ModItems.RUBY_LEGGINGS) + "_smithing");

                SmithingTransformRecipeJsonBuilder.create(
                                Ingredient.ofItem(ModItems.RUBY_UPGRADE_SMITHING_TEMPLATE),
                                Ingredient.ofItem(Items.NETHERITE_BOOTS),
                                this.ingredientFromTag(ModItemTags.RUBY_TOOL_MATERIALS),
                                RecipeCategory.COMBAT, ModItems.RUBY_BOOTS
                        )
                        .criterion("has_ruby", conditionsFromTag(ModItemTags.RUBY_TOOL_MATERIALS))
                        .offerTo(exporter, getItemPath(ModItems.RUBY_BOOTS) + "_smithing");

// Tools
                SmithingTransformRecipeJsonBuilder.create(
                                Ingredient.ofItem(ModItems.RUBY_UPGRADE_SMITHING_TEMPLATE),
                                Ingredient.ofItem(Items.NETHERITE_SWORD),
                                this.ingredientFromTag(ModItemTags.RUBY_TOOL_MATERIALS),
                                RecipeCategory.COMBAT, ModItems.RUBY_SWORD
                        )
                        .criterion("has_ruby", conditionsFromTag(ModItemTags.RUBY_TOOL_MATERIALS))
                        .offerTo(exporter, getItemPath(ModItems.RUBY_SWORD) + "_smithing");

                SmithingTransformRecipeJsonBuilder.create(
                                Ingredient.ofItem(ModItems.RUBY_UPGRADE_SMITHING_TEMPLATE),
                                Ingredient.ofItem(Items.NETHERITE_PICKAXE),
                                this.ingredientFromTag(ModItemTags.RUBY_TOOL_MATERIALS),
                                RecipeCategory.TOOLS, ModItems.RUBY_PICKAXE
                        )
                        .criterion("has_ruby", conditionsFromTag(ModItemTags.RUBY_TOOL_MATERIALS))
                        .offerTo(exporter, getItemPath(ModItems.RUBY_PICKAXE) + "_smithing");

                SmithingTransformRecipeJsonBuilder.create(
                                Ingredient.ofItem(ModItems.RUBY_UPGRADE_SMITHING_TEMPLATE),
                                Ingredient.ofItem(Items.NETHERITE_AXE),
                                this.ingredientFromTag(ModItemTags.RUBY_TOOL_MATERIALS),
                                RecipeCategory.TOOLS, ModItems.RUBY_AXE
                        )
                        .criterion("has_ruby", conditionsFromTag(ModItemTags.RUBY_TOOL_MATERIALS))
                        .offerTo(exporter, getItemPath(ModItems.RUBY_AXE) + "_smithing");

                SmithingTransformRecipeJsonBuilder.create(
                                Ingredient.ofItem(ModItems.RUBY_UPGRADE_SMITHING_TEMPLATE),
                                Ingredient.ofItem(Items.NETHERITE_SHOVEL),
                                this.ingredientFromTag(ModItemTags.RUBY_TOOL_MATERIALS),
                                RecipeCategory.TOOLS, ModItems.RUBY_SHOVEL
                        )
                        .criterion("has_ruby", conditionsFromTag(ModItemTags.RUBY_TOOL_MATERIALS))
                        .offerTo(exporter, getItemPath(ModItems.RUBY_SHOVEL) + "_smithing");

                SmithingTransformRecipeJsonBuilder.create(
                                Ingredient.ofItem(ModItems.RUBY_UPGRADE_SMITHING_TEMPLATE),
                                Ingredient.ofItem(Items.NETHERITE_HOE),
                                this.ingredientFromTag(ModItemTags.RUBY_TOOL_MATERIALS),
                                RecipeCategory.TOOLS, ModItems.RUBY_HOE
                        )
                        .criterion("has_ruby", conditionsFromTag(ModItemTags.RUBY_TOOL_MATERIALS))
                        .offerTo(exporter, getItemPath(ModItems.RUBY_HOE) + "_smithing");

                SmithingTransformRecipeJsonBuilder.create(
                                Ingredient.ofItem(ModItems.RUBY_UPGRADE_SMITHING_TEMPLATE),
                                Ingredient.ofItem(Items.NETHERITE_SPEAR),
                                this.ingredientFromTag(ModItemTags.RUBY_TOOL_MATERIALS),
                                RecipeCategory.COMBAT, ModItems.RUBY_SPEAR
                        )
                        .criterion("has_ruby", conditionsFromTag(ModItemTags.RUBY_TOOL_MATERIALS))
                        .offerTo(exporter, getItemPath(ModItems.RUBY_SPEAR) + "_smithing");

                SmithingTransformRecipeJsonBuilder.create(
                                Ingredient.ofItem(ModItems.RUBY_UPGRADE_SMITHING_TEMPLATE),
                                Ingredient.ofItem(Items.NETHERITE_NAUTILUS_ARMOR),
                                this.ingredientFromTag(ModItemTags.RUBY_TOOL_MATERIALS),
                                RecipeCategory.COMBAT, ModItems.RUBY_NAUTILUS_ARMOR
                        )
                        .criterion("has_ruby", conditionsFromTag(ModItemTags.RUBY_TOOL_MATERIALS))
                        .offerTo(exporter, getItemPath(ModItems.RUBY_NAUTILUS_ARMOR) + "_smithing");

                offerSmithingTrimRecipe(ModItems.GUARDIAN_ARMOR_TRIM_SMITHING_TEMPLATE,
                        ModArmorTrimPatterns.GUARDIAN, RegistryKey.of(RegistryKeys.RECIPE, Identifier.ofVanilla(getItemPath(ModItems.GUARDIAN_ARMOR_TRIM_SMITHING_TEMPLATE) + "_smithing_trim")));

                offerReversibleCompactingRecipes(RecipeCategory.BUILDING_BLOCKS, ModItems.RUBY, RecipeCategory.DECORATIONS,
                        ModBlocks.RUBY_BLOCK);
                offerReversibleCompactingRecipes(RecipeCategory.BUILDING_BLOCKS, ModItems.RADIANT, RecipeCategory.DECORATIONS,
                        ModBlocks.RADIANT_BLOCK);
                offerReversibleCompactingRecipes(RecipeCategory.BUILDING_BLOCKS, ModItems.SAPPHIRE, RecipeCategory.DECORATIONS,
                        ModBlocks.SAPPHIRE_BLOCK);
                offerReversibleCompactingRecipes(RecipeCategory.BUILDING_BLOCKS, ModItems.GREEN_SAPPHIRE, RecipeCategory.DECORATIONS,
                        ModBlocks.GREEN_SAPPHIRE_BLOCK);
                offerReversibleCompactingRecipes(RecipeCategory.BUILDING_BLOCKS, ModItems.BLUE_GARNET, RecipeCategory.DECORATIONS,
                        ModBlocks.BLUE_GARNET_BLOCK);
                offerReversibleCompactingRecipes(RecipeCategory.BUILDING_BLOCKS, ModItems.PINK_GARNET, RecipeCategory.DECORATIONS,
                        ModBlocks.PINK_GARNET_BLOCK);
                offerReversibleCompactingRecipes(RecipeCategory.BUILDING_BLOCKS, ModItems.GREEN_GARNET, RecipeCategory.DECORATIONS,
                        ModBlocks.GREEN_GARNET_BLOCK);
                offerReversibleCompactingRecipes(RecipeCategory.BUILDING_BLOCKS, ModItems.TOPAZ, RecipeCategory.DECORATIONS,
                        ModBlocks.TOPAZ_BLOCK);
                offerReversibleCompactingRecipes(RecipeCategory.BUILDING_BLOCKS, ModItems.WHITE_TOPAZ, RecipeCategory.DECORATIONS,
                        ModBlocks.WHITE_TOPAZ_BLOCK);
                offerReversibleCompactingRecipes(RecipeCategory.BUILDING_BLOCKS, ModItems.PERIDOT, RecipeCategory.DECORATIONS,
                        ModBlocks.PERIDOT_BLOCK);
                offerReversibleCompactingRecipes(RecipeCategory.BUILDING_BLOCKS, ModItems.JADE, RecipeCategory.DECORATIONS,
                        ModBlocks.JADE_BLOCK);
                offerReversibleCompactingRecipes(RecipeCategory.BUILDING_BLOCKS, ModItems.PYROPE, RecipeCategory.DECORATIONS,
                        ModBlocks.PYROPE_BLOCK);

                offerReversibleCompactingRecipes(RecipeCategory.BUILDING_BLOCKS, ModItems.RAW_RUBY, RecipeCategory.DECORATIONS,
                        ModBlocks.RAW_RUBY_BLOCK);
                offerReversibleCompactingRecipes(RecipeCategory.BUILDING_BLOCKS, ModItems.RAW_SAPPHIRE, RecipeCategory.DECORATIONS,
                        ModBlocks.RAW_SAPPHIRE_BLOCK);
                offerReversibleCompactingRecipes(RecipeCategory.BUILDING_BLOCKS, ModItems.RAW_GREEN_SAPPHIRE, RecipeCategory.DECORATIONS,
                        ModBlocks.RAW_GREEN_SAPPHIRE_BLOCK);
                offerReversibleCompactingRecipes(RecipeCategory.BUILDING_BLOCKS, ModItems.RAW_BLUE_GARNET, RecipeCategory.DECORATIONS,
                        ModBlocks.RAW_BLUE_GARNET_BLOCK);
                offerReversibleCompactingRecipes(RecipeCategory.BUILDING_BLOCKS, ModItems.RAW_PINK_GARNET, RecipeCategory.DECORATIONS,
                        ModBlocks.RAW_PINK_GARNET_BLOCK);
                offerReversibleCompactingRecipes(RecipeCategory.BUILDING_BLOCKS, ModItems.RAW_GREEN_GARNET, RecipeCategory.DECORATIONS,
                        ModBlocks.RAW_GREEN_GARNET_BLOCK);
                offerReversibleCompactingRecipes(RecipeCategory.BUILDING_BLOCKS, ModItems.RAW_TOPAZ, RecipeCategory.DECORATIONS,
                        ModBlocks.RAW_TOPAZ_BLOCK);
                offerReversibleCompactingRecipes(RecipeCategory.BUILDING_BLOCKS, ModItems.RAW_WHITE_TOPAZ, RecipeCategory.DECORATIONS,
                        ModBlocks.RAW_WHITE_TOPAZ_BLOCK);
                offerReversibleCompactingRecipes(RecipeCategory.BUILDING_BLOCKS, ModItems.RAW_PERIDOT, RecipeCategory.DECORATIONS,
                        ModBlocks.RAW_PERIDOT_BLOCK);
                offerReversibleCompactingRecipes(RecipeCategory.BUILDING_BLOCKS, ModItems.RAW_JADE, RecipeCategory.DECORATIONS,
                        ModBlocks.RAW_JADE_BLOCK);
                offerReversibleCompactingRecipes(RecipeCategory.BUILDING_BLOCKS, ModItems.RAW_PYROPE, RecipeCategory.DECORATIONS,
                        ModBlocks.RAW_PYROPE_BLOCK);

                createGemPolishing(
                                Ingredient.ofItem(ModItems.RAW_RUBY), new ItemStack(ModItems.RUBY), RecipeCategory.MISC
                        )
                        .criterion(hasItem(ModItems.RAW_RUBY), conditionsFromItem(ModItems.RAW_RUBY))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(getRecipeName(ModItems.RUBY) + "_polishing")));

                createGemPolishing(
                                Ingredient.ofItems(ModItems.RAW_SAPPHIRE), new ItemStack(ModItems.SAPPHIRE), RecipeCategory.MISC
                        )
                        .criterion(hasItem(ModItems.RAW_SAPPHIRE), conditionsFromItem(ModItems.RAW_SAPPHIRE))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(getRecipeName(ModItems.SAPPHIRE) + "_polishing")));

                createGemPolishing(
                                Ingredient.ofItems(ModItems.RAW_GREEN_SAPPHIRE), new ItemStack(ModItems.GREEN_SAPPHIRE), RecipeCategory.MISC
                        )
                        .criterion(hasItem(ModItems.RAW_GREEN_SAPPHIRE), conditionsFromItem(ModItems.RAW_GREEN_SAPPHIRE))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(getRecipeName(ModItems.GREEN_SAPPHIRE) + "_polishing")));

                createGemPolishing(
                                Ingredient.ofItems(ModItems.RAW_BLUE_GARNET), new ItemStack(ModItems.BLUE_GARNET), RecipeCategory.MISC
                        )
                        .criterion(hasItem(ModItems.RAW_BLUE_GARNET), conditionsFromItem(ModItems.RAW_BLUE_GARNET))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(getRecipeName(ModItems.BLUE_GARNET) + "_polishing")));

                createGemPolishing(
                                Ingredient.ofItems(ModItems.RAW_PINK_GARNET), new ItemStack(ModItems.PINK_GARNET), RecipeCategory.MISC
                        )
                        .criterion(hasItem(ModItems.RAW_PINK_GARNET), conditionsFromItem(ModItems.RAW_PINK_GARNET))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(getRecipeName(ModItems.PINK_GARNET) + "_polishing")));

                createGemPolishing(
                                Ingredient.ofItems(ModItems.RAW_GREEN_GARNET), new ItemStack(ModItems.GREEN_GARNET), RecipeCategory.MISC
                        )
                        .criterion(hasItem(ModItems.RAW_GREEN_GARNET), conditionsFromItem(ModItems.RAW_GREEN_GARNET))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(getRecipeName(ModItems.GREEN_GARNET) + "_polishing")));

                createGemPolishing(
                                Ingredient.ofItems(ModItems.RAW_TOPAZ), new ItemStack(ModItems.TOPAZ), RecipeCategory.MISC
                        )
                        .criterion(hasItem(ModItems.RAW_TOPAZ), conditionsFromItem(ModItems.RAW_TOPAZ))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(getRecipeName(ModItems.TOPAZ) + "_polishing")));

                createGemPolishing(
                                Ingredient.ofItems(ModItems.RAW_WHITE_TOPAZ), new ItemStack(ModItems.WHITE_TOPAZ), RecipeCategory.MISC
                        )
                        .criterion(hasItem(ModItems.RAW_WHITE_TOPAZ), conditionsFromItem(ModItems.RAW_WHITE_TOPAZ))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(getRecipeName(ModItems.WHITE_TOPAZ) + "_polishing")));

                createGemPolishing(
                                Ingredient.ofItems(ModItems.RAW_PERIDOT), new ItemStack(ModItems.PERIDOT), RecipeCategory.MISC
                        )
                        .criterion(hasItem(ModItems.RAW_PERIDOT), conditionsFromItem(ModItems.RAW_PERIDOT))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(getRecipeName(ModItems.PERIDOT) + "_polishing")));

                createGemPolishing(
                                Ingredient.ofItems(ModItems.RAW_JADE), new ItemStack(ModItems.JADE), RecipeCategory.MISC
                        )
                        .criterion(hasItem(ModItems.RAW_JADE), conditionsFromItem(ModItems.RAW_JADE))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(getRecipeName(ModItems.JADE) + "_polishing")));

                createGemPolishing(
                                Ingredient.ofItems(ModItems.RAW_PYROPE), new ItemStack(ModItems.PYROPE), RecipeCategory.MISC
                        )
                        .criterion(hasItem(ModItems.RAW_PYROPE), conditionsFromItem(ModItems.RAW_PYROPE))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(getRecipeName(ModItems.PYROPE) + "_polishing")));

                createShaped(RecipeCategory.MISC, ModItems.RADIANT, 1)
                        .pattern("aaa")
                        .pattern("aba")
                        .pattern("aaa")
                                .input('a', ModBlocks.RUBY_BLOCK)
                                .input('b', Items.DIAMOND)
                                        .criterion(hasItem(ModBlocks.RUBY_BLOCK), conditionsFromItem(ModBlocks.RUBY_BLOCK))
                                        .criterion(hasItem(Items.DIAMOND), conditionsFromItem(Items.DIAMOND))
                                                .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(getRecipeName(ModItems.RADIANT) + "_from_ruby")));

                createShaped(RecipeCategory.MISC, ModBlocks.GEM_PURIFIER_BLOCK, 1)
                        .pattern("III")
                        .pattern("III")
                        .pattern("B B")
                        .input('I', Blocks.IRON_BLOCK)
                        .input('B', Blocks.IRON_BARS)
                        .criterion(hasItem(Blocks.IRON_BLOCK), conditionsFromItem(Blocks.IRON_BLOCK))
                        .criterion(hasItem(Blocks.IRON_BARS), conditionsFromItem(Blocks.IRON_BARS))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(getRecipeName(ModBlocks.GEM_PURIFIER_BLOCK))));

                createShaped(RecipeCategory.COMBAT, ModItems.RADIANT_SWORD, 1)
                        .pattern(" I ")
                        .pattern(" I ")
                        .pattern(" B ")
                        .input('I', ModItems.RADIANT)
                        .input('B', Items.STICK)
                        .criterion(hasItem(ModItems.RADIANT), conditionsFromItem(ModItems.RADIANT))
                        .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(getRecipeName(ModItems.RADIANT_SWORD))));

                createShaped(RecipeCategory.MISC, ModBlocks.ENERGY_BLOCK, 1)
                        .pattern("aaa")
                        .pattern("aba")
                        .pattern("aaa")
                                .input('a', ModItems.RADIANT)
                                .input('b', Blocks.TNT)
                                        .criterion(hasItem(ModItems.RADIANT), conditionsFromItem(ModItems.RADIANT))
                                        .criterion(hasItem(Blocks.TNT), conditionsFromItem(Blocks.TNT))
                                                .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(getRecipeName(ModBlocks.ENERGY_BLOCK) + "_from_radiant")));

                createShaped(RecipeCategory.FOOD, ModItems.DIAMOND_APPLE, 1)
                        .pattern("aaa")
                        .pattern("aba")
                        .pattern("aaa")
                        .input('a', Blocks.DIAMOND_BLOCK)
                        .input('b', Items.APPLE)
                        .criterion(hasItem(Items.DIAMOND_BLOCK), conditionsFromItem(Blocks.DIAMOND_BLOCK))
                        .criterion(hasItem(Items.APPLE), conditionsFromItem(Items.APPLE))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(getRecipeName(ModItems.DIAMOND_APPLE))));

                createShaped(RecipeCategory.REDSTONE, ModBlocks.RUBY_LAMP, 1)
                        .pattern("aba")
                        .pattern("bcb")
                        .pattern("aba")
                        .input('a', Items.REDSTONE)
                        .input('b', ModItems.RUBY)
                        .input('c', Blocks.GLOWSTONE)
                        .criterion(hasItem(Items.REDSTONE), conditionsFromItem(Items.REDSTONE))
                        .criterion(hasItem(ModItems.RUBY), conditionsFromItem(ModItems.RUBY))
                        .criterion(hasItem(Blocks.GLOWSTONE), conditionsFromItem(Blocks.GLOWSTONE))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(getRecipeName(ModBlocks.RUBY_LAMP))));

                // Helmet
                createShaped(RecipeCategory.COMBAT, ModItems.SAPPHIRE_HELMET, 1)
                        .pattern("aaa")
                        .pattern("a a")
                        .pattern("   ")
                        .input('a', ModItems.SAPPHIRE)
                        .criterion(hasItem(ModItems.SAPPHIRE), conditionsFromItem(ModItems.SAPPHIRE))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(getRecipeName(ModItems.SAPPHIRE_HELMET))));

// Chestplate
                createShaped(RecipeCategory.COMBAT, ModItems.SAPPHIRE_CHESTPLATE, 1)
                        .pattern("a a")
                        .pattern("aaa")
                        .pattern("aaa")
                        .input('a', ModItems.SAPPHIRE)
                        .criterion(hasItem(ModItems.SAPPHIRE), conditionsFromItem(ModItems.SAPPHIRE))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(getRecipeName(ModItems.SAPPHIRE_CHESTPLATE))));

// Leggings
                createShaped(RecipeCategory.COMBAT, ModItems.SAPPHIRE_LEGGINGS, 1)
                        .pattern("aaa")
                        .pattern("a a")
                        .pattern("a a")
                        .input('a', ModItems.SAPPHIRE)
                        .criterion(hasItem(ModItems.SAPPHIRE), conditionsFromItem(ModItems.SAPPHIRE))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(getRecipeName(ModItems.SAPPHIRE_LEGGINGS))));

// Boots
                createShaped(RecipeCategory.COMBAT, ModItems.SAPPHIRE_BOOTS, 1)
                        .pattern("   ")
                        .pattern("a a")
                        .pattern("a a")
                        .input('a', ModItems.SAPPHIRE)
                        .criterion(hasItem(ModItems.SAPPHIRE), conditionsFromItem(ModItems.SAPPHIRE))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(getRecipeName(ModItems.SAPPHIRE_BOOTS))));

// Sword
                createShaped(RecipeCategory.COMBAT, ModItems.SAPPHIRE_SWORD, 1)
                        .pattern(" a ")
                        .pattern(" a ")
                        .pattern(" b ")
                        .input('a', ModItems.SAPPHIRE)
                        .input('b', Items.STICK)
                        .criterion(hasItem(ModItems.SAPPHIRE), conditionsFromItem(ModItems.SAPPHIRE))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(getRecipeName(ModItems.SAPPHIRE_SWORD))));

// Pickaxe
                createShaped(RecipeCategory.TOOLS, ModItems.SAPPHIRE_PICKAXE, 1)
                        .pattern("aaa")
                        .pattern(" b ")
                        .pattern(" b ")
                        .input('a', ModItems.SAPPHIRE)
                        .input('b', Items.STICK)
                        .criterion(hasItem(ModItems.SAPPHIRE), conditionsFromItem(ModItems.SAPPHIRE))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(getRecipeName(ModItems.SAPPHIRE_PICKAXE))));

// Axe
                createShaped(RecipeCategory.TOOLS, ModItems.SAPPHIRE_AXE, 1)
                        .pattern("aa ")
                        .pattern("ab ")
                        .pattern(" b ")
                        .input('a', ModItems.SAPPHIRE)
                        .input('b', Items.STICK)
                        .criterion(hasItem(ModItems.SAPPHIRE), conditionsFromItem(ModItems.SAPPHIRE))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(getRecipeName(ModItems.SAPPHIRE_AXE))));

// Shovel
                createShaped(RecipeCategory.TOOLS, ModItems.SAPPHIRE_SHOVEL, 1)
                        .pattern(" a ")
                        .pattern(" b ")
                        .pattern(" b ")
                        .input('a', ModItems.SAPPHIRE)
                        .input('b', Items.STICK)
                        .criterion(hasItem(ModItems.SAPPHIRE), conditionsFromItem(ModItems.SAPPHIRE))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(getRecipeName(ModItems.SAPPHIRE_SHOVEL))));

// Hoe
                createShaped(RecipeCategory.TOOLS, ModItems.SAPPHIRE_HOE, 1)
                        .pattern("aa ")
                        .pattern(" b ")
                        .pattern(" b ")
                        .input('a', ModItems.SAPPHIRE)
                        .input('b', Items.STICK)
                        .criterion(hasItem(ModItems.SAPPHIRE), conditionsFromItem(ModItems.SAPPHIRE))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(getRecipeName(ModItems.SAPPHIRE_HOE))));

                createShaped(RecipeCategory.MISC, ModItems.RUBY_UPGRADE_SMITHING_TEMPLATE, 2)
                        .pattern("aba")
                        .pattern("aca")
                        .pattern("aaa")
                        .input('a', ModItems.RUBY)
                        .input('b', ModItems.RUBY_UPGRADE_SMITHING_TEMPLATE)
                        .input('c', Blocks.STONE)
                        .criterion(hasItem(ModItems.RUBY), conditionsFromItem(ModItems.RUBY))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(getRecipeName(ModItems.RUBY_UPGRADE_SMITHING_TEMPLATE) + "_duplication")));

                createShaped(RecipeCategory.MISC, ModItems.RUBY_UPGRADE_SMITHING_TEMPLATE, 1)
                        .pattern("aba")
                        .pattern("aca")
                        .pattern("aaa")
                        .input('a', Blocks.STONE)
                        .input('b', ModItems.RUBY_UPGRADE_SMITHING_TEMPLATE)
                        .input('c', ModItems.RUBY)
                        .criterion(hasItem(ModItems.RUBY), conditionsFromItem(ModItems.RUBY))
                        .criterion(hasItem(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE), conditionsFromItem(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(getRecipeName(ModItems.RUBY_UPGRADE_SMITHING_TEMPLATE))));

                createShaped(RecipeCategory.COMBAT, ModItems.SAPPHIRE_SPEAR, 1)
                        .pattern("bbb")
                        .pattern("bab")
                        .pattern("bbb")
                        .input('a', ModItems.RUBY_SPEAR)
                        .input('b', ModItems.SAPPHIRE)
                        .criterion(hasItem(ModItems.RUBY_SPEAR), conditionsFromItem(ModItems.RUBY_SPEAR))
                        .criterion(hasItem(ModItems.SAPPHIRE), conditionsFromItem(ModItems.SAPPHIRE))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(getRecipeName(ModItems.SAPPHIRE_SPEAR))));

                createShaped(RecipeCategory.COMBAT, ModItems.SAPPHIRE_NAUTILUS_ARMOR, 1)
                        .pattern("bbb")
                        .pattern("bab")
                        .pattern("bbb")
                        .input('a', ModItems.RUBY_NAUTILUS_ARMOR)
                        .input('b', ModItems.SAPPHIRE)
                        .criterion(hasItem(ModItems.RUBY_NAUTILUS_ARMOR), conditionsFromItem(ModItems.RUBY_NAUTILUS_ARMOR))
                        .criterion(hasItem(ModItems.SAPPHIRE), conditionsFromItem(ModItems.SAPPHIRE))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(getRecipeName(ModItems.SAPPHIRE_NAUTILUS_ARMOR))));
            }
        };
    }

    public GemPolishingRecipeJsonBuilder createGemPolishing(Ingredient input, ItemStack result, RecipeCategory category) {
        return GemPolishingRecipeJsonBuilder.create(input, result, category);
    }
    
    @Override
    public String getName() {
        return "Mod Recipes Gen for " + MoreOresModInitializer.MOD_ID;
    }
}
