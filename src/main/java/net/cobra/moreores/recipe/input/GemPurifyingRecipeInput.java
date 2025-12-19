package net.cobra.moreores.recipe.input;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.input.RecipeInput;

public record GemPurifyingRecipeInput(ItemStack inputStack) implements RecipeInput {
    @Override
    public ItemStack getStackInSlot(int slot) {
        return inputStack;
    }

    @Override
    public int size() {
        return 1;
    }
}
