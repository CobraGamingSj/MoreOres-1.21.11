package net.cobra.moreores.data.datagen;

import net.cobra.moreores.block.ModBlocks;
import net.cobra.moreores.block.TomatoCropBlock;
import net.cobra.moreores.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class LootTableGenerator extends FabricBlockLootTableProvider {

    public LootTableGenerator(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {

        BlockStatePropertyLootCondition.Builder builder = BlockStatePropertyLootCondition.builder(ModBlocks.TOMATO_CROP).properties(StatePredicate.Builder.create()
                .exactMatch(TomatoCropBlock.AGE, 5));
        addDrop(ModBlocks.TOMATO_CROP, cropDrops(ModBlocks.TOMATO_CROP, ModItems.TOMATO, ModItems.TOMATO_SEEDS, builder));
        BlockStatePropertyLootCondition.Builder builder2 = BlockStatePropertyLootCondition.builder(ModBlocks.PINEAPPLE_CROP).properties(StatePredicate.Builder.create()
                .exactMatch(TomatoCropBlock.AGE, 4));
        addDrop(ModBlocks.PINEAPPLE_CROP, cropDrops(ModBlocks.PINEAPPLE_CROP, ModItems.PINEAPPLE, ModItems.PINEAPPLE_SEEDS, builder2));

        addDrop(ModBlocks.RUBY_BLOCK);
        addDrop(ModBlocks.RUBY_LAMP);
        addDrop(ModBlocks.RADIANT_BLOCK);
        addDrop(ModBlocks.SAPPHIRE_BLOCK);
        addDrop(ModBlocks.GREEN_SAPPHIRE_BLOCK);
        addDrop(ModBlocks.BLUE_GARNET_BLOCK);
        addDrop(ModBlocks.PINK_GARNET_BLOCK);
        addDrop(ModBlocks.GREEN_GARNET_BLOCK);
        addDrop(ModBlocks.TOPAZ_BLOCK);
        addDrop(ModBlocks.WHITE_TOPAZ_BLOCK);
        addDrop(ModBlocks.PERIDOT_BLOCK);
        addDrop(ModBlocks.JADE_BLOCK);
        addDrop(ModBlocks.PYROPE_BLOCK);
        addDrop(ModBlocks.ENERGY_BLOCK);
        addDrop(ModBlocks.GEM_PURIFIER_BLOCK);

        addDrop(ModBlocks.RAW_RUBY_BLOCK);
        addDrop(ModBlocks.RAW_SAPPHIRE_BLOCK);
        addDrop(ModBlocks.RAW_GREEN_SAPPHIRE_BLOCK);
        addDrop(ModBlocks.RAW_BLUE_GARNET_BLOCK);
        addDrop(ModBlocks.RAW_PINK_GARNET_BLOCK);
        addDrop(ModBlocks.RAW_GREEN_GARNET_BLOCK);
        addDrop(ModBlocks.RAW_TOPAZ_BLOCK);
        addDrop(ModBlocks.RAW_WHITE_TOPAZ_BLOCK);
        addDrop(ModBlocks.RAW_PERIDOT_BLOCK);
        addDrop(ModBlocks.RAW_PYROPE_BLOCK);
        addDrop(ModBlocks.RAW_JADE_BLOCK);

        addDrop(ModBlocks.RUBY_ORE, oreDrops(ModBlocks.RUBY_ORE, ModItems.RAW_RUBY));
        addDrop(ModBlocks.DEEPSLATE_RUBY_ORE, oreDrops(ModBlocks.DEEPSLATE_RUBY_ORE, ModItems.RAW_RUBY));
        addDrop(ModBlocks.SAPPHIRE_ORE, oreDrops(ModBlocks.SAPPHIRE_ORE, ModItems.RAW_SAPPHIRE));
        addDrop(ModBlocks.DEEPSLATE_SAPPHIRE_ORE, oreDrops(ModBlocks.DEEPSLATE_SAPPHIRE_ORE, ModItems.RAW_SAPPHIRE));
        addDrop(ModBlocks.GREEN_SAPPHIRE_ORE, oreDrops(ModBlocks.GREEN_SAPPHIRE_ORE, ModItems.RAW_GREEN_SAPPHIRE));
        addDrop(ModBlocks.DEEPSLATE_GREEN_SAPPHIRE_ORE,oreDrops(ModBlocks.DEEPSLATE_GREEN_SAPPHIRE_ORE, ModItems.RAW_GREEN_SAPPHIRE));
        addDrop(ModBlocks.BLUE_GARNET_ORE, oreDrops(ModBlocks.BLUE_GARNET_ORE, ModItems.RAW_BLUE_GARNET));
        addDrop(ModBlocks.DEEPSLATE_BLUE_GARNET_ORE, oreDrops(ModBlocks.DEEPSLATE_BLUE_GARNET_ORE, ModItems.RAW_BLUE_GARNET));
        addDrop(ModBlocks.PINK_GARNET_ORE, oreDrops(ModBlocks.PINK_GARNET_ORE, ModItems.RAW_PINK_GARNET));
        addDrop(ModBlocks.DEEPSLATE_PINK_GARNET_ORE, oreDrops(ModBlocks.DEEPSLATE_PINK_GARNET_ORE, ModItems.RAW_PINK_GARNET));
        addDrop(ModBlocks.GREEN_GARNET_ORE, oreDrops(ModBlocks.GREEN_GARNET_ORE, ModItems.RAW_GREEN_GARNET));
        addDrop(ModBlocks.DEEPSLATE_GREEN_GARNET_ORE,oreDrops(ModBlocks.DEEPSLATE_GREEN_GARNET_ORE, ModItems.RAW_GREEN_GARNET));
        addDrop(ModBlocks.TOPAZ_ORE, oreDrops(ModBlocks.TOPAZ_ORE, ModItems.RAW_TOPAZ));
        addDrop(ModBlocks.DEEPSLATE_TOPAZ_ORE, oreDrops(ModBlocks.DEEPSLATE_TOPAZ_ORE, ModItems.RAW_TOPAZ));
        addDrop(ModBlocks.WHITE_TOPAZ_ORE, oreDrops(ModBlocks.WHITE_TOPAZ_ORE, ModItems.RAW_WHITE_TOPAZ));
        addDrop(ModBlocks.DEEPSLATE_WHITE_TOPAZ_ORE, oreDrops(ModBlocks.DEEPSLATE_WHITE_TOPAZ_ORE, ModItems.RAW_WHITE_TOPAZ));
        addDrop(ModBlocks.PYROPE_ORE, oreDrops(ModBlocks.PYROPE_ORE, ModItems.RAW_PYROPE));
        addDrop(ModBlocks.DEEPSLATE_PYROPE_ORE, oreDrops(ModBlocks.DEEPSLATE_PYROPE_ORE, ModItems.RAW_PYROPE));
        addDrop(ModBlocks.JADE_ORE, oreDrops(ModBlocks.JADE_ORE, ModItems.RAW_JADE));
        addDrop(ModBlocks.DEEPSLATE_JADE_ORE, oreDrops(ModBlocks.DEEPSLATE_JADE_ORE, ModItems.RAW_JADE));
        addDrop(ModBlocks.PERIDOT_ORE, oreDrops(ModBlocks.PERIDOT_ORE, ModItems.RAW_PERIDOT));
        addDrop(ModBlocks.DEEPSLATE_PERIDOT_ORE, oreDrops(ModBlocks.DEEPSLATE_PERIDOT_ORE, ModItems.RAW_PERIDOT));
    }
}
