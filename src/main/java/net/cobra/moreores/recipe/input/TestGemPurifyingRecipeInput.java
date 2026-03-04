package net.cobra.moreores.recipe.input;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.input.RecipeInput;

public record TestGemPurifyingRecipeInput(ItemStack firstInputStack, ItemStack secondInputStack) implements RecipeInput {
    @Override
    public ItemStack getStackInSlot(int slot) {
        return firstInputStack;
    }

    @Override
    public int size() {
        return 1;
    }
}
