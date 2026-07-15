package org.cobra.moreores.item.util.impl;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import org.cobra.moreores.item.util.GemCategory;

public interface Gemstone {
    String getName();
    GemCategory category();
    Item[] items();
    
    Gemstone NONE = new Gemstone() {
        @Override
        public String getName() {
            return "empty";
        }

        @Override
        public GemCategory category() {
            return GemCategory.NONE;
        }

        @Override
        public Item[] items() {
            return new Item[]{Items.AIR};
        }
    };
}
