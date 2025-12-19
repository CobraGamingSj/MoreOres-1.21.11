package net.cobra.moreores.data.datagen;

import net.cobra.moreores.item.ModItems;
import net.cobra.moreores.registry.ModItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ItemTagGen extends FabricTagProvider.ItemTagProvider {

    public ItemTagGen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        valueLookupBuilder(ItemTags.CREEPER_DROP_MUSIC_DISCS)
                .add(ModItems.MUSIC_DISC_WATCHER)
                .add(ModItems.MUSIC_DISC_ENDLESS)
                .add(ModItems.MUSIC_DISC_DEEPER)
                .add(ModItems.MUSIC_DISC_DREITON)
                .add(ModItems.MUSIC_DISC_TASWELL)
                .add(ModItems.MUSIC_DISC_ARIA_MATH)
                .add(ModItems.MUSIC_DISC_BIOME_FEST)
                .add(ModItems.MUSIC_DISC_FEATHERFALL)
                .add(ModItems.MUSIC_DISC_INFINITE_AMETHYST);

        valueLookupBuilder(ItemTags.TRIM_MATERIALS)
                .add(ModItems.RUBY)
                .add(ModItems.RADIANT)
                .add(ModItems.SAPPHIRE)
                .add(ModItems.GREEN_SAPPHIRE)
                .add(ModItems.BLUE_GARNET)
                .add(ModItems.PINK_GARNET)
                .add(ModItems.GREEN_GARNET)
                .add(ModItems.TOPAZ)
                .add(ModItems.WHITE_TOPAZ)
                .add(ModItems.PERIDOT)
                .add(ModItems.JADE)
                .add(ModItems.PYROPE);

        valueLookupBuilder(ItemTags.VILLAGER_PLANTABLE_SEEDS)
                .add(ModItems.TOMATO_SEEDS)
                .add(ModItems.PINEAPPLE_SEEDS);

        valueLookupBuilder(ModItemTags.ARCSHAPERS);

        valueLookupBuilder(ItemTags.SWORDS)
                .add(ModItems.RUBY_SWORD)
                .add(ModItems.SAPPHIRE_SWORD)
                .add(ModItems.RADIANT_SWORD);

        valueLookupBuilder(ItemTags.PICKAXES)
                .add(ModItems.RUBY_PICKAXE)
                .add(ModItems.SAPPHIRE_PICKAXE);

        valueLookupBuilder(ItemTags.AXES)
                .add(ModItems.RUBY_AXE)
                .add(ModItems.SAPPHIRE_AXE);

        valueLookupBuilder(ItemTags.SHOVELS)
                .add(ModItems.RUBY_SHOVEL)
                .add(ModItems.SAPPHIRE_SHOVEL);

        valueLookupBuilder(ItemTags.HOES)
                .add(ModItems.RUBY_HOE)
                .add(ModItems.SAPPHIRE_HOE);

        valueLookupBuilder(ItemTags.FOOT_ARMOR)
                .add(ModItems.RUBY_BOOTS)
                .add(ModItems.SAPPHIRE_BOOTS);

        valueLookupBuilder(ItemTags.LEG_ARMOR)
                .add(ModItems.RUBY_LEGGINGS)
                .add(ModItems.SAPPHIRE_LEGGINGS);

        valueLookupBuilder(ItemTags.HEAD_ARMOR)
                .add(ModItems.RUBY_HELMET)
                .add(ModItems.SAPPHIRE_HELMET);

        valueLookupBuilder(ItemTags.CHEST_ARMOR)
                .add(ModItems.RUBY_CHESTPLATE)
                .add(ModItems.SAPPHIRE_CHESTPLATE);

        valueLookupBuilder(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.RUBY_HELMET)
                .add(ModItems.RUBY_CHESTPLATE)
                .add(ModItems.RUBY_LEGGINGS)
                .add(ModItems.RUBY_BOOTS)
                .add(ModItems.SAPPHIRE_HELMET)
                .add(ModItems.SAPPHIRE_CHESTPLATE)
                .add(ModItems.SAPPHIRE_LEGGINGS)
                .add(ModItems.SAPPHIRE_BOOTS);

        valueLookupBuilder(ModItemTags.GEMSTONE)
                .add(ModItems.RUBY)
                .add(ModItems.RADIANT)
                .add(ModItems.SAPPHIRE)
                .add(ModItems.GREEN_SAPPHIRE)
                .add(ModItems.BLUE_GARNET)
                .add(ModItems.PINK_GARNET)
                .add(ModItems.GREEN_GARNET)
                .add(ModItems.TOPAZ)
                .add(ModItems.WHITE_TOPAZ)
                .add(ModItems.PERIDOT)
                .add(ModItems.JADE)
                .add(ModItems.PYROPE)
                .add(Items.LAPIS_LAZULI)
                .add(Items.QUARTZ)
                .add(Items.DIAMOND);

        valueLookupBuilder(ModItemTags.RAW_GEMSTONE)
                .add(ModItems.RAW_RUBY)
                .add(ModItems.RAW_SAPPHIRE)
                .add(ModItems.RAW_GREEN_SAPPHIRE)
                .add(ModItems.RAW_BLUE_GARNET)
                .add(ModItems.RAW_PINK_GARNET)
                .add(ModItems.RAW_GREEN_GARNET)
                .add(ModItems.RAW_TOPAZ)
                .add(ModItems.RAW_WHITE_TOPAZ)
                .add(ModItems.RAW_PERIDOT)
                .add(ModItems.RAW_JADE)
                .add(ModItems.RAW_PYROPE);

        valueLookupBuilder(ModItemTags.METAL)
                .add(Items.IRON_INGOT)
                .add(Items.COPPER_INGOT)
                .add(Items.GOLD_INGOT);

        valueLookupBuilder(ModItemTags.RARE)
                .addTag(ModItemTags.GEMSTONE)
                .addTag(ModItemTags.METAL);

        valueLookupBuilder(ModItemTags.JUKEBOX_PLAYABLE_DISCS)
                .add(ModItems.MUSIC_DISC_ARIA_MATH)
                .add(ModItems.MUSIC_DISC_DREITON)
                .add(ModItems.MUSIC_DISC_TASWELL)
                .add(ModItems.MUSIC_DISC_BIOME_FEST)
                .add(ModItems.MUSIC_DISC_FEATHERFALL)
                .add(ModItems.MUSIC_DISC_ENDLESS)
                .add(ModItems.MUSIC_DISC_INFINITE_AMETHYST)
                .add(ModItems.MUSIC_DISC_DEEPER)
                .add(ModItems.MUSIC_DISC_WATCHER);

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