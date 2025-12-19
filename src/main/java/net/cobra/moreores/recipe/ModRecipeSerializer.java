package net.cobra.moreores.recipe;

import net.cobra.moreores.MoreOresModInitializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipeSerializer {

//    public static final RecipeSerializer<GemPurifierRecipe> GEM_POLISHING = register("polish_gem", new GemPurifierRecipe.Serializer());
//
//    private static <S extends RecipeSerializer<T>, T extends Recipe<?>> S register(String id, S serializer) {
//        return Registry.register(Registries.RECIPE_SERIALIZER, id, serializer);
//    }

    public static void register() {
        Registry.register(Registries.RECIPE_SERIALIZER, Identifier.of(MoreOresModInitializer.MOD_ID, GemPurifierRecipe.Serializer.ID), GemPurifierRecipe.Serializer.GEM_POLISHING);
    }

}
