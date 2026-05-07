package net.cobra.moreores.client.recipe;

import net.cobra.moreores.MoreOresModInitializer;
import net.cobra.moreores.recipe.GemIninfusionRecipe;
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

public class GeminfusionRecipeJsonBuilder {
    private final Ingredient ingredientBefore;
    private final Ingredient ingredientAfter;
    private final ItemStack output;
    private final RecipeCategory category;
    private final Map<String, AdvancementCriterion<?>> criterion = new LinkedHashMap<>();

    public GeminfusionRecipeJsonBuilder(Ingredient ingredientBefore, Ingredient ingredientAfter, ItemStack output, RecipeCategory category) {
        this.ingredientBefore = ingredientBefore;
        this.ingredientAfter = ingredientAfter;
        this.output = output;
        this.category = category;
    }

    public static GeminfusionRecipeJsonBuilder create(Ingredient ingredientBefore, Ingredient ingredientAfter, ItemStack result, RecipeCategory category) {
        return new GeminfusionRecipeJsonBuilder(ingredientBefore, ingredientAfter, result, category);
    }

    public GeminfusionRecipeJsonBuilder criterion(String name, AdvancementCriterion<?> criterion) {
        this.criterion.put(name, criterion);
        return this;
    }

    public void offerTo(RecipeExporter exporter, String name) {
        RegistryKey<Recipe<?>> recipeId = RegistryKey.of(RegistryKeys.RECIPE, MoreOresModInitializer.getId(name + "_infusion"));
        this.validate(recipeId);
        Advancement.Builder builder = exporter.getAdvancementBuilder()
                .criterion("has_the_recipe", RecipeUnlockedCriterion.create(recipeId))
                .rewards(AdvancementRewards.Builder.recipe(recipeId))
                .criteriaMerger(AdvancementRequirements.CriterionMerger.OR);
        this.criterion.forEach(builder::criterion);
        GemIninfusionRecipe geminfusionRecipe = new GemIninfusionRecipe(this.ingredientBefore, this.ingredientAfter, this.output);
        exporter.accept(recipeId, geminfusionRecipe, builder.build(recipeId.getValue().withPrefixedPath("recipes/" + this.category.getName() + "/")));
    }

    private void validate(RegistryKey<Recipe<?>> recipeId) {
        if (this.criterion.isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + recipeId + ", missing 'criterion'");
        }
    }
}