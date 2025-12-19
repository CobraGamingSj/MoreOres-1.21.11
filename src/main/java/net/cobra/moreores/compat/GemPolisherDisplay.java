package net.cobra.moreores.compat;//package net.cobra.moreores.compat;
//
//import com.mojang.serialization.codecs.RecordCodecBuilder;
//import me.shedaniel.rei.api.common.category.CategoryIdentifier;
//import me.shedaniel.rei.api.common.display.Display;
//import me.shedaniel.rei.api.common.display.DisplaySerializer;
//import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
//import me.shedaniel.rei.api.common.entry.EntryIngredient;
//import me.shedaniel.rei.api.common.util.EntryIngredients;
//import me.shedaniel.rei.api.common.util.EntryStacks;
//import net.cobra.moreores.recipe.GemPurifierRecipe;
//import net.minecraft.recipe.RecipeEntry;
//import org.jetbrains.annotations.Nullable;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//public class GemPurifierDisplay extends BasicDisplay {
//
//    public GemPurifierDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs) {
//        super(inputs, outputs);
//    }
//
//    public GemPurifierDisplay(RecipeEntry<GemPurifierRecipe> recipe) {
//        super(List.of(EntryIngredients.ofIngredient(recipe.value().getIngredient())), List.of(EntryIngredient.of(EntryStacks.of(recipe.value().getResult()))));
//    }
//
//    @Override
//    public CategoryIdentifier<?> getCategoryIdentifier() {
//        return GemPurifierCategory.GEM_POLISHING;
//    }
//
//    @Override
//    public @Nullable DisplaySerializer<? extends Display> getSerializer() {
//        return null;
//    }
//}
