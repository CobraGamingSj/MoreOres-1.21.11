package net.cobra.moreores.client.recipe;

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

import java.util.LinkedHashMap;
import java.util.Map;

public class GemPolishingRecipeJsonBuilder {
    private final Ingredient ingredient;
    private final ItemStack output;
    private final RecipeCategory category;
    private final Map<String, AdvancementCriterion<?>> criterion = new LinkedHashMap<>();

    public GemPolishingRecipeJsonBuilder(Ingredient ingredient, ItemStack output, RecipeCategory category) {
        this.ingredient = ingredient;
        this.output = output;
        this.category = category;
    }

    public static GemPolishingRecipeJsonBuilder create(Ingredient ingredient, ItemStack result, RecipeCategory category) {
        return new GemPolishingRecipeJsonBuilder(ingredient, result, category);
    }

    public GemPolishingRecipeJsonBuilder criterion(String name, AdvancementCriterion<?> criterion) {
        this.criterion.put(name, criterion);
        return this;
    }

    public void offerTo(RecipeExporter exporter, RegistryKey<Recipe<?>> recipeId) {
        this.validate(recipeId);
        Advancement.Builder builder = exporter.getAdvancementBuilder()
                .criterion("has_the_recipe", RecipeUnlockedCriterion.create(recipeId))
                .rewards(AdvancementRewards.Builder.recipe(recipeId))
                .criteriaMerger(AdvancementRequirements.CriterionMerger.OR);
        this.criterion.forEach(builder::criterion);
        GemPurifierRecipe gemPolishingRecipe = new GemPurifierRecipe(this.ingredient, this.output);
        exporter.accept(recipeId, gemPolishingRecipe, builder.build(recipeId.getValue().withPrefixedPath("recipes/" + this.category.getName() + "/")));
    }

    private void validate(RegistryKey<Recipe<?>> recipeId) {
        if (this.criterion.isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + recipeId);
        }
    }
}