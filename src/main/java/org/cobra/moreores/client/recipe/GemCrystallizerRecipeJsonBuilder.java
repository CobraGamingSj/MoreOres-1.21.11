package org.cobra.moreores.client.recipe;

import org.cobra.moreores.MoreOresModInitializer;
import org.cobra.moreores.item.ModItems;
import org.cobra.moreores.recipe.GemCrystallizerRecipe;
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

public class GemCrystallizerRecipeJsonBuilder {
    private final Ingredient ingredientBefore;
    private final Ingredient ingredientAfter;
    private final ItemStack output;
    private final RecipeCategory category;
    private final Map<String, AdvancementCriterion<?>> criterion = new LinkedHashMap<>();

    public GemCrystallizerRecipeJsonBuilder(Ingredient ingredientBefore, Ingredient ingredientAfter, ItemStack output, RecipeCategory category) {
        this.ingredientBefore = ingredientBefore;
        this.ingredientAfter = ingredientAfter;
        this.output = output;
        this.category = category;
    }

    public static GemCrystallizerRecipeJsonBuilder create(Ingredient ingredientBefore, ItemStack result, RecipeCategory category) {
        return new GemCrystallizerRecipeJsonBuilder(ingredientBefore, Ingredient.ofItem(ModItems.RADIANT), result, category);
    }

    public static GemCrystallizerRecipeJsonBuilder createQuartsidian() {
        return new GemCrystallizerRecipeJsonBuilder(Ingredient.ofItem(Items.QUARTZ), Ingredient.ofItem(Blocks.OBSIDIAN.asItem()), new ItemStack(ModItems.QUARTSIDIAN), RecipeCategory.MISC);
    }

    public GemCrystallizerRecipeJsonBuilder criterion(String name, AdvancementCriterion<?> criterion) {
        this.criterion.put(name, criterion);
        return this;
    }

    public void offerTo(RecipeExporter exporter, String name) {
        RegistryKey<Recipe<?>> recipeId = RegistryKey.of(RegistryKeys.RECIPE, MoreOresModInitializer.id(name + "_crystallizer"));
        this.validate(recipeId);
        Advancement.Builder builder = exporter.getAdvancementBuilder()
                .criterion("has_the_recipe", RecipeUnlockedCriterion.create(recipeId))
                .rewards(AdvancementRewards.Builder.recipe(recipeId))
                .criteriaMerger(AdvancementRequirements.CriterionMerger.OR);
        this.criterion.forEach(builder::criterion);
        GemCrystallizerRecipe gemcrystallizerRecipe = new GemCrystallizerRecipe(this.ingredientBefore, this.ingredientAfter, this.output);
        exporter.accept(recipeId, gemcrystallizerRecipe, builder.build(recipeId.getValue().withPrefixedPath("recipes/" + this.category.getName() + "/")));
    }

    private void validate(RegistryKey<Recipe<?>> recipeId) {
        if (this.criterion.isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + recipeId + ", missing 'criterion'");
        }
    }
}