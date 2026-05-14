package net.cobra.moreores.client.recipe;

import net.cobra.moreores.MoreOresModInitializer;
import net.cobra.moreores.item.ModItems;
import net.cobra.moreores.recipe.GemInfusionRecipe;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.AdvancementRequirements;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.advancement.criterion.RecipeUnlockedCriterion;
import net.minecraft.block.Blocks;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

import java.util.LinkedHashMap;
import java.util.Map;

public class GemInfusionRecipeJsonBuilder {
    private final Ingredient ingredientBefore;
    private final Ingredient ingredientAfter;
    private final ItemStack output;
    private final RecipeCategory category;
    private final Map<String, AdvancementCriterion<?>> criterion = new LinkedHashMap<>();

    public GemInfusionRecipeJsonBuilder(Ingredient ingredientBefore, Ingredient ingredientAfter, ItemStack output, RecipeCategory category) {
        this.ingredientBefore = ingredientBefore;
        this.ingredientAfter = ingredientAfter;
        this.output = output;
        this.category = category;
    }

    public static GemInfusionRecipeJsonBuilder create(Ingredient ingredientBefore, ItemStack result, RecipeCategory category) {
        return new GemInfusionRecipeJsonBuilder(ingredientBefore, Ingredient.ofItem(ModItems.RADIANT), result, category);
    }

    public static GemInfusionRecipeJsonBuilder createQuartsidian() {
        return new GemInfusionRecipeJsonBuilder(Ingredient.ofItem(Items.QUARTZ), Ingredient.ofItem(Blocks.OBSIDIAN.asItem()), new ItemStack(ModItems.QUARTSIDIAN), RecipeCategory.MISC);
    }

    public GemInfusionRecipeJsonBuilder criterion(String name, AdvancementCriterion<?> criterion) {
        this.criterion.put(name, criterion);
        return this;
    }

    public void offerTo(RecipeExporter exporter, String name) {
        RegistryKey<Recipe<?>> recipeId = RegistryKey.of(RegistryKeys.RECIPE, MoreOresModInitializer.id(name + "_infusion"));
        this.validate(recipeId);
        Advancement.Builder builder = exporter.getAdvancementBuilder()
                .criterion("has_the_recipe", RecipeUnlockedCriterion.create(recipeId))
                .rewards(AdvancementRewards.Builder.recipe(recipeId))
                .criteriaMerger(AdvancementRequirements.CriterionMerger.OR);
        this.criterion.forEach(builder::criterion);
        GemInfusionRecipe geminfusionRecipe = new GemInfusionRecipe(this.ingredientBefore, this.ingredientAfter, this.output);
        exporter.accept(recipeId, geminfusionRecipe, builder.build(recipeId.getValue().withPrefixedPath("recipes/" + this.category.getName() + "/")));
    }

    private void validate(RegistryKey<Recipe<?>> recipeId) {
        if (this.criterion.isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + recipeId + ", missing 'criterion'");
        }
    }
}