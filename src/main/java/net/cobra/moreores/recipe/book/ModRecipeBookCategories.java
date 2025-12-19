package net.cobra.moreores.recipe.book;

import net.cobra.moreores.MoreOresModInitializer;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipeBookCategories {

    public static final RecipeBookCategory GEM_POLISHING = register("gem_polishing");

    public static RecipeBookCategory register(String id) {
        return Registry.register(Registries.RECIPE_BOOK_CATEGORY, Identifier.of(MoreOresModInitializer.MOD_ID, id), new RecipeBookCategory());
    }

    public static void register() {

    }

}
