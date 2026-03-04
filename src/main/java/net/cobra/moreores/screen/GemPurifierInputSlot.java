package net.cobra.moreores.screen;

import net.cobra.moreores.registry.ModItemTags;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;

public class GemPurifierInputSlot extends Slot {
    public GemPurifierInputSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        return stack.isIn(ModItemTags.RAW_GEMSTONE);
    }
}
