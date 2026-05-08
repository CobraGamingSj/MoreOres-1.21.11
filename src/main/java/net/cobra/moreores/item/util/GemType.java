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
    SAPPHIRE("sapphire", ModItems.SAPPHIRE, ModBlocks.SAPPHIRE_BLOCK.asItem()),
    GREEN_SAPPHIRE("green_sapphire", ModItems.GREEN_SAPPHIRE, ModBlocks.GREEN_SAPPHIRE_BLOCK.asItem()),
    BLUE_GARNET("blue_garnet", ModItems.BLUE_GARNET, ModBlocks.BLUE_GARNET_BLOCK.asItem()),
    PINK_GARNET("pink_garnet", ModItems.PINK_GARNET, ModBlocks.PINK_GARNET_BLOCK.asItem()),
    GREEN_GARNET("green_garnet", ModItems.GREEN_GARNET, ModBlocks.GREEN_GARNET_BLOCK.asItem()),
    KYAWTHUITE("kyawthuite", ModItems.KYAWTHUITE, ModBlocks.KYAWTHUITE_BLOCK.asItem()),
    TOPAZ("topaz", ModItems.TOPAZ, ModBlocks.TOPAZ_BLOCK.asItem()),
    WHITE_TOPAZ("white_topaz", ModItems.WHITE_TOPAZ, ModBlocks.WHITE_TOPAZ_BLOCK.asItem()),
    PERIDOT("peridot", ModItems.PERIDOT, ModBlocks.PERIDOT_BLOCK.asItem()),
    JADE("jade", ModItems.JADE, ModBlocks.JADE_BLOCK.asItem()),
    PYROPE("pyrope", ModItems.PYROPE, ModBlocks.PYROPE_BLOCK.asItem());

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
