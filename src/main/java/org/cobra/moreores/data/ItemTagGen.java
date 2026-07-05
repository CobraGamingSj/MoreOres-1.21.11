package org.cobra.moreores.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import org.cobra.moreores.block.ModBlocks;
import org.cobra.moreores.item.ModItems;
import org.cobra.moreores.registry.ModItemTags;

import java.util.concurrent.CompletableFuture;

public class ItemTagGen extends FabricTagProvider.ItemTagProvider {
    public ItemTagGen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {

        valueLookupBuilder(ModItemTags.CRYSTALLIZED)
                .add(ModItems.CRIMSON_GARNET)
                .add(ModItems.CRYSTALLITE)  
                .add(ModItems.ALEXANDRITE)
                .add(ModItems.OPAL)
                .add(ModItems.QUARTSIDIAN)
                .add(ModItems.RADIANT_AMETHYST)
                .add(ModItems.LIMESTONE)
                .add(ModItems.MOONSTONE)
                .add(ModItems.RED_BERYL)
                .add(ModItems.GRANDIDIERITE)
                .add(ModItems.KASHMIR_SAPPHIRE)
                .add(ModItems.ORANGE_ZIRCON)
                .add(ModBlocks.CRIMSON_GARNET_BLOCK.asItem())
                .add(ModBlocks.ALEXANDRITE_BLOCK.asItem())
                .add(ModBlocks.CRYSTALLITE_BLOCK.asItem())
                .add(ModBlocks.MOONSTONE_BLOCK.asItem())
                .add(ModBlocks.LIMESTONE_BLOCK.asItem())
                .add(ModBlocks.QUARTSIDIAN_BLOCK.asItem())
                .add(ModBlocks.RED_BERYL_BLOCK.asItem())
                .add(ModBlocks.ORANGE_ZIRCON_BLOCK.asItem())
                .add(ModBlocks.OPAL_BLOCK.asItem())
                .add(ModBlocks.KASHMIR_SAPPHIRE_BLOCK.asItem())
                .add(ModBlocks.RADIANT_AMETHYST_BLOCK.asItem())
                .add(ModBlocks.GRANDIDIERITE_BLOCK.asItem());
        
        valueLookupBuilder(ModItemTags.CRYSTALLIZED_GEMSTONES)
                .add(ModItems.CRIMSON_GARNET)
                .add(ModItems.CRYSTALLITE)
                .add(ModItems.ALEXANDRITE)
                .add(ModItems.OPAL)
                .add(ModItems.QUARTSIDIAN)
                .add(ModItems.RADIANT_AMETHYST)
                .add(ModItems.LIMESTONE)
                .add(ModItems.MOONSTONE)
                .add(ModItems.RED_BERYL)
                .add(ModItems.GRANDIDIERITE)
                .add(ModItems.KASHMIR_SAPPHIRE)
                .add(ModItems.ORANGE_ZIRCON);
        
        valueLookupBuilder(ModItemTags.CRYSTALLIZED_GEMSTONE_BLOCKS)
                .add(ModBlocks.CRIMSON_GARNET_BLOCK.asItem())
                .add(ModBlocks.ALEXANDRITE_BLOCK.asItem())
                .add(ModBlocks.CRYSTALLITE_BLOCK.asItem())
                .add(ModBlocks.MOONSTONE_BLOCK.asItem())
                .add(ModBlocks.LIMESTONE_BLOCK.asItem())
                .add(ModBlocks.QUARTSIDIAN_BLOCK.asItem())
                .add(ModBlocks.RED_BERYL_BLOCK.asItem())
                .add(ModBlocks.ORANGE_ZIRCON_BLOCK.asItem())
                .add(ModBlocks.OPAL_BLOCK.asItem())
                .add(ModBlocks.KASHMIR_SAPPHIRE_BLOCK.asItem())
                .add(ModBlocks.RADIANT_AMETHYST_BLOCK.asItem())
                .add(ModBlocks.GRANDIDIERITE_BLOCK.asItem());
        
        valueLookupBuilder(ItemTags.SPEARS)
                .add(ModItems.RUBY_SPEAR)
                .add(ModItems.SAPPHIRE_SPEAR);

        valueLookupBuilder(ItemTags.TRIM_MATERIALS)
                .add(ModItems.RUBY)
                .add(ModItems.RADIANT)
                .add(ModItems.SAPPHIRE)
                .add(ModItems.GREEN_SAPPHIRE)
                .add(ModItems.BLUE_GARNET)
                .add(ModItems.PINK_GARNET)
                .add(ModItems.GREEN_GARNET)
                .add(ModItems.KYAWTHUITE)
                .add(ModItems.TOPAZ)
                .add(ModItems.WHITE_TOPAZ)
                .add(ModItems.PERIDOT)
                .add(ModItems.JADE)
                .add(ModItems.PYROPE)
                .add(ModItems.CRIMSON_GARNET)
                .add(ModItems.CRYSTALLITE)
                .add(ModItems.ALEXANDRITE)
                .add(ModItems.OPAL)
                .add(ModItems.QUARTSIDIAN)
                .add(ModItems.RADIANT_AMETHYST)
                .add(ModItems.LIMESTONE)
                .add(ModItems.MOONSTONE)
                .add(ModItems.RED_BERYL);

        valueLookupBuilder(ItemTags.SWORDS)
                .add(ModItems.RUBY_SWORD)
                .add(ModItems.SAPPHIRE_SWORD)
                .add(ModItems.RADIANT_SWORD);

        valueLookupBuilder(ItemTags.PICKAXES)
                .add(ModItems.RUBY_PICKAXE)
                .add(ModItems.SAPPHIRE_PICKAXE)
                .add(ModItems.RADIANT_PICKAXE);

        valueLookupBuilder(ItemTags.AXES)
                .add(ModItems.RUBY_AXE)
                .add(ModItems.SAPPHIRE_AXE)
                .add(ModItems.RADIANT_AXE);

        valueLookupBuilder(ItemTags.SHOVELS)
                .add(ModItems.RUBY_SHOVEL)
                .add(ModItems.SAPPHIRE_SHOVEL)
                .add(ModItems.RADIANT_SHOVEL);

        valueLookupBuilder(ItemTags.HOES)
                .add(ModItems.RUBY_HOE)
                .add(ModItems.SAPPHIRE_HOE)
                .add(ModItems.RADIANT_HOE);

        valueLookupBuilder(ItemTags.FOOT_ARMOR)
                .add(ModItems.RUBY_BOOTS)
                .add(ModItems.SAPPHIRE_BOOTS)
                .add(ModItems.RADIANT_BOOTS);

        valueLookupBuilder(ItemTags.LEG_ARMOR)
                .add(ModItems.RUBY_LEGGINGS)
                .add(ModItems.SAPPHIRE_LEGGINGS)
                .add(ModItems.RADIANT_LEGGINGS);

        valueLookupBuilder(ItemTags.HEAD_ARMOR)
                .add(ModItems.RUBY_HELMET)
                .add(ModItems.SAPPHIRE_HELMET)
                .add(ModItems.RADIANT_HELMET);

        valueLookupBuilder(ItemTags.CHEST_ARMOR)
                .add(ModItems.RUBY_CHESTPLATE)
                .add(ModItems.SAPPHIRE_CHESTPLATE)
                .add(ModItems.RADIANT_CHESTPLATE);

        valueLookupBuilder(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.RUBY_HELMET)
                .add(ModItems.RUBY_CHESTPLATE)
                .add(ModItems.RUBY_LEGGINGS)
                .add(ModItems.RUBY_BOOTS)
                .add(ModItems.SAPPHIRE_HELMET)
                .add(ModItems.SAPPHIRE_CHESTPLATE)
                .add(ModItems.SAPPHIRE_LEGGINGS)
                .add(ModItems.RADIANT_HELMET)
                .add(ModItems.RADIANT_CHESTPLATE)
                .add(ModItems.RADIANT_LEGGINGS)
                .add(ModItems.RADIANT_BOOTS);

        valueLookupBuilder(ModItemTags.GEMSTONE)
                .add(ModItems.RUBY)
                .add(ModItems.RADIANT)
                .add(ModItems.SAPPHIRE)
                .add(ModItems.GREEN_SAPPHIRE)
                .add(ModItems.BLUE_GARNET)
                .add(ModItems.PINK_GARNET)
                .add(ModItems.GREEN_GARNET)
                .add(ModItems.KYAWTHUITE)
                .add(ModItems.TOPAZ)
                .add(ModItems.WHITE_TOPAZ)
                .add(ModItems.PERIDOT)
                .add(ModItems.JADE)
                .add(ModItems.PYROPE)
                .add(Items.LAPIS_LAZULI)
                .add(Items.QUARTZ)
                .add(Items.DIAMOND)
                .add(ModItems.CRIMSON_GARNET, ModItems.CRYSTALLITE, ModItems.ALEXANDRITE, ModItems.ORANGE_ZIRCON,
                        ModItems.OPAL, ModItems.QUARTSIDIAN, ModItems.KASHMIR_SAPPHIRE, ModItems.RADIANT_AMETHYST,
                        ModItems.LIMESTONE, ModItems.MOONSTONE, ModItems.RED_BERYL, ModItems.GRANDIDIERITE, ModItems.CRYSTAL_OF_ECLIPSE);

        valueLookupBuilder(ModItemTags.GEMSTONE_BLOCKS)
                .add(ModBlocks.RUBY_BLOCK.asItem(),
                        ModBlocks.SAPPHIRE_BLOCK.asItem(),
                        ModBlocks.GREEN_SAPPHIRE_BLOCK.asItem(),
                        ModBlocks.BLUE_GARNET_BLOCK.asItem(),
                        ModBlocks.PINK_GARNET_BLOCK.asItem(),
                        ModBlocks.GREEN_GARNET_BLOCK.asItem(),
                        ModBlocks.KYAWTHUITE_BLOCK.asItem(),
                        ModBlocks.TOPAZ_BLOCK.asItem(),
                        ModBlocks.WHITE_TOPAZ_BLOCK.asItem(),
                        ModBlocks.PERIDOT_BLOCK.asItem(),
                        ModBlocks.JADE_BLOCK.asItem(),
                        ModBlocks.PYROPE_BLOCK.asItem());

        valueLookupBuilder(ModItemTags.RAW_GEMSTONE)
                .add(ModItems.RAW_RUBY)
                .add(ModItems.RAW_SAPPHIRE)
                .add(ModItems.RAW_GREEN_SAPPHIRE)
                .add(ModItems.RAW_BLUE_GARNET)
                .add(ModItems.RAW_PINK_GARNET)
                .add(ModItems.RAW_GREEN_GARNET)
                .add(ModItems.RAW_KYAWTHUITE)
                .add(ModItems.RAW_TOPAZ)
                .add(ModItems.RAW_WHITE_TOPAZ)
                .add(ModItems.RAW_PERIDOT)
                .add(ModItems.RAW_JADE)
                .add(ModItems.RAW_PYROPE);

        valueLookupBuilder(ModItemTags.RAW_GEMSTONE_BLOCKS)
                .add(ModBlocks.RAW_RUBY_BLOCK.asItem(),
                        ModBlocks.RAW_SAPPHIRE_BLOCK.asItem(),
                        ModBlocks.RAW_GREEN_SAPPHIRE_BLOCK.asItem(),
                        ModBlocks.RAW_BLUE_GARNET_BLOCK.asItem(),
                        ModBlocks.RAW_PINK_GARNET_BLOCK.asItem(),
                        ModBlocks.RAW_GREEN_GARNET_BLOCK.asItem(),
                        ModBlocks.RAW_KYAWTHUITE_BLOCK.asItem(),
                        ModBlocks.RAW_TOPAZ_BLOCK.asItem(),
                        ModBlocks.RAW_WHITE_TOPAZ_BLOCK.asItem(),
                        ModBlocks.RAW_PERIDOT_BLOCK.asItem(),
                        ModBlocks.RAW_JADE_BLOCK.asItem(),
                        ModBlocks.RAW_PYROPE_BLOCK.asItem());

        valueLookupBuilder(ItemTags.ARROWS)
                .add(ModItems.GEM_ARROW);
        
        valueLookupBuilder(ItemTags.BOW_ENCHANTABLE)
                .add(ModItems.RADIANT_BOW);
        
        valueLookupBuilder(ModItemTags.METAL)
                .add(Items.IRON_INGOT)
                .add(Items.COPPER_INGOT)
                .add(Items.GOLD_INGOT);

        valueLookupBuilder(ModItemTags.RARE)
                .addTag(ModItemTags.GEMSTONE)
                .addTag(ModItemTags.METAL);

        valueLookupBuilder(ModItemTags.REPAIRS_RUBY_ARMOR)
                .add(ModItems.RUBY);

        valueLookupBuilder(ModItemTags.REPAIRS_SAPPHIRE_ARMOR)
                .add(ModItems.SAPPHIRE);

        valueLookupBuilder(ModItemTags.REPAIRS_RADIANT_ARMOR)
                .add(ModItems.RADIANT);

        valueLookupBuilder(ModItemTags.RUBY_TOOL_MATERIALS)
                .add(ModItems.RUBY);

        valueLookupBuilder(ModItemTags.SAPPHIRE_TOOL_MATERIALS)
                .add(ModItems.SAPPHIRE);

        valueLookupBuilder(ModItemTags.RADIANT_TOOL_MATERIALS)
                .add(ModItems.RADIANT);
    }
}