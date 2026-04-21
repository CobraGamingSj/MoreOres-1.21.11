package net.cobra.moreores.block.entity.gem_polisher.util;

import com.mojang.serialization.Codec;
import net.cobra.moreores.item.ModItems;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

public enum GemType {
    EMPTY(0xFFFFFF, Items.AIR),
    RUBY(0xD30100, ModItems.RUBY),
    SAPPHIRE(0x381EFF, ModItems.SAPPHIRE),
    GREEN_SAPPHIRE(0x16BC00, ModItems.GREEN_SAPPHIRE),
    BLUE_GARNET(0x5A20A3, ModItems.BLUE_GARNET),
    PINK_GARNET(0x5A20A3, ModItems.PINK_GARNET),
    GREEN_GARNET(0x27A338, ModItems.GREEN_GARNET),
    KYAWTHUITE(0xFF0F00, ModItems.KYAWTHUITE),
    TOPAZ(0xF3672B, ModItems.TOPAZ),
    WHITE_TOPAZ(0x989B86, ModItems.WHITE_TOPAZ),
    PERIDOT(0x20A328, ModItems.PERIDOT),
    JADE(0xBEFFFF, ModItems.JADE),
    PYROPE(0x750000, ModItems.PYROPE);

    public static final Codec<GemType> CODEC = Codec.STRING.xmap(
            s -> GemType.valueOf(s.toUpperCase()),
            GemType::name);

    private final int color;
    private final Item stack;

    GemType(int colorTintIndex, Item item) {
        this.color = colorTintIndex;
        this.stack = item;
    }

    public int getColor() {
        return 0xFF000000 | color;
    }

    public Item getItem() {
        return stack;
    }
}
