package net.cobra.moreores.item.util;

import com.mojang.serialization.Codec;
import net.cobra.moreores.block.ModBlocks;
import net.cobra.moreores.item.ModItems;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.StringIdentifiable;

public enum GemType implements StringIdentifiable {
    EMPTY("empty", Items.AIR),
    RUBY("ruby", ModItems.RUBY, ModBlocks.RUBY_BLOCK.asItem()),
    SAPPHIRE("sapphire", ModItems.SAPPHIRE),
    GREEN_SAPPHIRE("green_sapphire", ModItems.GREEN_SAPPHIRE),
    BLUE_GARNET("blue_garnet", ModItems.BLUE_GARNET),
    PINK_GARNET("pink_garnet", ModItems.PINK_GARNET),
    GREEN_GARNET("green_garnet", ModItems.GREEN_GARNET),
    KYAWTHUITE("kyawthuite", ModItems.KYAWTHUITE),
    TOPAZ("topaz", ModItems.TOPAZ),
    WHITE_TOPAZ("white_topaz", ModItems.WHITE_TOPAZ),
    PERIDOT("peridot", ModItems.PERIDOT),
    JADE("jade", ModItems.JADE),
    PYROPE("pyrope", ModItems.PYROPE);

    public static final Codec<GemType> CODEC = Codec.STRING.xmap(
            s -> GemType.valueOf(s.toUpperCase()),
            GemType::name);

    private final String name;
    private final Item[] items;

    GemType(String name, Item... items) {
        this.name = name;
        this.items = items;
    }

    public Item[] getItem() {
        return items;
    }

    @Override
    public String asString() {
        return name.toLowerCase();
    }
}
