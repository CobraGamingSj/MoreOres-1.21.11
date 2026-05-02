package net.cobra.moreores.recipe.input;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.input.RecipeInput;

public record GemFusionRecipeInput(ItemStack firstInputStack, ItemStack secondInputStack) implements RecipeInput {
    @Override
    public ItemStack getStackInSlot(int slot) {
        return switch (slot) {
            case 0 -> firstInputStack;
            case 3 -> secondInputStack;
            default -> throw new IllegalStateException("Unexpected value: " + slot);
        };
    }

    @Override
    public int size() {
        return 1;
    }
}
