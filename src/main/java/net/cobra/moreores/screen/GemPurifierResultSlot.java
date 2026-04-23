package net.cobra.moreores.screen;

import net.cobra.moreores.registry.ModItemTags;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;

public class GemPurifierResultSlot extends Slot {
    public GemPurifierResultSlot(Inventory inventory, int i, int x, int y) {
        super(inventory, i, x, y);
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        return stack.isIn(ModItemTags.GEMSTONE) || stack.isIn(ModItemTags.GEMSTONE_BLOCKS);
    }
}
