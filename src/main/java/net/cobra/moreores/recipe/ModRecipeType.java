package net.cobra.moreores.recipe;

import net.cobra.moreores.MoreOresModInitializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipeType {

//    public static final RecipeType<GemPolishingRecipe> GEM_POLISHING = register("polish_gem");
//
//    private static <T extends Recipe<?>> RecipeType<T> register(String id) {
//        return Registry.register(Registries.RECIPE_TYPE, Identifier.of(MoreOres.MOD_ID, id), new RecipeType<T>() {
//            public String toString() {
//                return id;
//            }
//        });
//    }

    public static void register() {
        Registry.register(Registries.RECIPE_TYPE, Identifier.of(MoreOresModInitializer.MOD_ID, GemPurifierRecipe.Type.ID), GemPurifierRecipe.Type.GEM_POLISHING);
    }

}
