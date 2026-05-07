package net.cobra.moreores.recipe.input;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.input.RecipeInput;

public record GemInfusionRecipeInput(ItemStack inputBefore, ItemStack inputAfter) implements RecipeInput {
    @Override
    public ItemStack getStackInSlot(int slot) {
        return switch (slot) {
            case 0 -> inputBefore;
            case 1 -> inputAfter;
            default -> ItemStack.EMPTY;
        };
    }

    @Override
    public int size() {
        return 1;
    }
}
