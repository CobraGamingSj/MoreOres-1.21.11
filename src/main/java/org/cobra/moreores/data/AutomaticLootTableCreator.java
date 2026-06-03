package org.cobra.moreores.data;

import org.cobra.moreores.MoreOresModInitializer;
import org.cobra.moreores.block.ModBlocks;
import org.cobra.moreores.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class AutomaticLootTableCreator extends FabricBlockLootTableProvider {
    private static final Map<Block, Item> ORE_DROPS = Map.ofEntries(
            Map.entry(ModBlocks.RUBY_ORE, ModItems.RAW_RUBY),
            Map.entry(ModBlocks.DEEPSLATE_RUBY_ORE, ModItems.RAW_RUBY),
            Map.entry(ModBlocks.SAPPHIRE_ORE, ModItems.RAW_SAPPHIRE),
            Map.entry(ModBlocks.DEEPSLATE_SAPPHIRE_ORE, ModItems.RAW_SAPPHIRE),
            Map.entry(ModBlocks.GREEN_SAPPHIRE_ORE, ModItems.RAW_GREEN_SAPPHIRE),
            Map.entry(ModBlocks.DEEPSLATE_GREEN_SAPPHIRE_ORE, ModItems.RAW_GREEN_SAPPHIRE),
            Map.entry(ModBlocks.BLUE_GARNET_ORE, ModItems.RAW_BLUE_GARNET),
            Map.entry(ModBlocks.DEEPSLATE_BLUE_GARNET_ORE, ModItems.RAW_BLUE_GARNET),
            Map.entry(ModBlocks.PINK_GARNET_ORE, ModItems.RAW_PINK_GARNET),
            Map.entry(ModBlocks.DEEPSLATE_PINK_GARNET_ORE, ModItems.RAW_PINK_GARNET),
            Map.entry(ModBlocks.GREEN_GARNET_ORE, ModItems.RAW_GREEN_GARNET),
            Map.entry(ModBlocks.DEEPSLATE_GREEN_GARNET_ORE, ModItems.RAW_GREEN_GARNET),
            Map.entry(ModBlocks.KYAWTHUITE_ORE, ModItems.RAW_KYAWTHUITE),
            Map.entry(ModBlocks.DEEPSLATE_KYAWTHUITE_ORE, ModItems.RAW_KYAWTHUITE),
            Map.entry(ModBlocks.TOPAZ_ORE, ModItems.RAW_TOPAZ),
            Map.entry(ModBlocks.DEEPSLATE_TOPAZ_ORE, ModItems.RAW_TOPAZ),
            Map.entry(ModBlocks.WHITE_TOPAZ_ORE, ModItems.RAW_WHITE_TOPAZ),
            Map.entry(ModBlocks.DEEPSLATE_WHITE_TOPAZ_ORE, ModItems.RAW_WHITE_TOPAZ),
            Map.entry(ModBlocks.PERIDOT_ORE, ModItems.RAW_PERIDOT),
            Map.entry(ModBlocks.DEEPSLATE_PERIDOT_ORE, ModItems.RAW_PERIDOT),
            Map.entry(ModBlocks.JADE_ORE, ModItems.RAW_JADE),
            Map.entry(ModBlocks.DEEPSLATE_JADE_ORE, ModItems.RAW_JADE),
            Map.entry(ModBlocks.PYROPE_ORE, ModItems.RAW_PYROPE),
            Map.entry(ModBlocks.DEEPSLATE_PYROPE_ORE, ModItems.RAW_PYROPE),
            Map.entry(ModBlocks.ECLIPSE_GEM_ORE, ModItems.ECLIPSE_GEM_CRYSTALS)
    );

    public AutomaticLootTableCreator(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {

        for (Block block : Registries.BLOCK) {
            Identifier id = Registries.BLOCK.getId(block);

            if(id.getNamespace().equals(MoreOresModInitializer.MOD_ID)) {
                if(ORE_DROPS.containsKey(block)) {
                    addDrop(block, oreDrops(block, ORE_DROPS.get(block)));
                    continue;
                }
                addDrop(block);
            }
        }
    }
}