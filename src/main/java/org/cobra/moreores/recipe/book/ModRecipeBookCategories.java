package org.cobra.moreores.recipe.book;

import org.cobra.moreores.MoreOresModInitializer;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipeBookCategories {

    public static final RecipeBookCategory GEM_POLISHING = register("gem_polishing");
    public static final RecipeBookCategory GEM_CRYSTALLIZER = register("gem_crystallizer");

    public static RecipeBookCategory register(String id) {
        return Registry.register(Registries.RECIPE_BOOK_CATEGORY, Identifier.of(MoreOresModInitializer.MOD_ID, id), new RecipeBookCategory());
    }

    public static void register() {
        MoreOresModInitializer.LOGGER.info("Loading ModRecipeBookCategory for " + MoreOresModInitializer.MOD_ID + " mod.");
    }

}
