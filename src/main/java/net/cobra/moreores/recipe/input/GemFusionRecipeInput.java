package net.cobra.moreores.recipe.input;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.input.RecipeInput;

public record GemFusionRecipeInput(ItemStack inputBefore, ItemStack inputAfter) implements RecipeInput {
    @Override
    public ItemStack getStackInSlot(int slot) {
        return switch (slot) {
            case 0 -> inputBefore;
            case 1 -> inputAfter;
            default -> throw new IllegalStateException("Unexpected value: " + slot);
        };
    }

    @Override
    public int size() {
        return 1;
    }
}
