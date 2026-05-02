package net.cobra.moreores.client.recipe;

import net.cobra.moreores.MoreOresModInitializer;
import net.cobra.moreores.recipe.GemFusionRecipe;
import net.cobra.moreores.recipe.GemPurifierRecipe;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.AdvancementRequirements;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.advancement.criterion.RecipeUnlockedCriterion;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

import java.util.LinkedHashMap;
import java.util.Map;

public class GemFusionRecipeJsonBuilder {
    private final Ingredient ingredientBefore;
    private final Ingredient ingredientAfter;
    private final ItemStack output;
    private final RecipeCategory category;
    private final Map<String, AdvancementCriterion<?>> criterion = new LinkedHashMap<>();

    public GemFusionRecipeJsonBuilder(Ingredient ingredientBefore, Ingredient ingredientAfter, ItemStack output, RecipeCategory category) {
        this.ingredientBefore = ingredientBefore;
        this.ingredientAfter = ingredientAfter;
        this.output = output;
        this.category = category;
    }

    public static GemFusionRecipeJsonBuilder create(Ingredient ingredientBefore, Ingredient ingredientAfter, ItemStack result, RecipeCategory category) {
        return new GemFusionRecipeJsonBuilder(ingredientBefore, ingredientAfter, result, category);
    }

    public GemFusionRecipeJsonBuilder criterion(String name, AdvancementCriterion<?> criterion) {
        this.criterion.put(name, criterion);
        return this;
    }

    public void offerTo(RecipeExporter exporter, String name) {
        RegistryKey<Recipe<?>> recipeId = RegistryKey.of(RegistryKeys.RECIPE, MoreOresModInitializer.getId(name + "_fusion"));
        this.validate(recipeId);
        Advancement.Builder builder = exporter.getAdvancementBuilder()
                .criterion("has_the_recipe", RecipeUnlockedCriterion.create(recipeId))
                .rewards(AdvancementRewards.Builder.recipe(recipeId))
                .criteriaMerger(AdvancementRequirements.CriterionMerger.OR);
        this.criterion.forEach(builder::criterion);
        GemFusionRecipe gemFusionRecipe = new GemFusionRecipe(this.ingredientBefore, this.ingredientAfter, this.output);
        exporter.accept(recipeId, gemFusionRecipe, builder.build(recipeId.getValue().withPrefixedPath("recipes/" + this.category.getName() + "/")));
    }

    private void validate(RegistryKey<Recipe<?>> recipeId) {
        if (this.criterion.isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + recipeId + ", missing 'criterion'");
        }
    }
}