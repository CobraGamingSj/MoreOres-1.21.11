package org.cobra.moreores.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.data.recipe.SmithingTransformRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import org.cobra.moreores.MoreOresModInitializer;
import org.cobra.moreores.block.ModBlocks;
import org.cobra.moreores.client.recipe.GemCrystallizerRecipeJsonBuilder;
import org.cobra.moreores.client.recipe.GemPolishingRecipeJsonBuilder;
import org.cobra.moreores.item.ModItems;
import org.cobra.moreores.item.equipment.trim.ModArmorTrimPatterns;
import org.cobra.moreores.registry.ModItemTags;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class AutomaticRecipeCreator extends FabricRecipeProvider {
    private static final Map<Item, SmithingData> SMITHING_DATA = Map.ofEntries(
            Map.entry(Items.NETHERITE_SWORD, new SmithingData(ModItems.RUBY_UPGRADE_SMITHING_TEMPLATE, ModItems.RUBY_SWORD, ModItemTags.RUBY_TOOL_MATERIALS)),
            Map.entry(Items.NETHERITE_PICKAXE, new SmithingData(ModItems.RUBY_UPGRADE_SMITHING_TEMPLATE, ModItems.RUBY_PICKAXE, ModItemTags.RUBY_TOOL_MATERIALS)),
            Map.entry(Items.NETHERITE_AXE, new SmithingData(ModItems.RUBY_UPGRADE_SMITHING_TEMPLATE, ModItems.RUBY_AXE, ModItemTags.RUBY_TOOL_MATERIALS)),
            Map.entry(Items.NETHERITE_HOE, new SmithingData(ModItems.RUBY_UPGRADE_SMITHING_TEMPLATE, ModItems.RUBY_HOE, ModItemTags.RUBY_TOOL_MATERIALS)),
            Map.entry(Items.NETHERITE_SHOVEL, new SmithingData(ModItems.RUBY_UPGRADE_SMITHING_TEMPLATE, ModItems.RUBY_SHOVEL, ModItemTags.RUBY_TOOL_MATERIALS)),
            Map.entry(Items.NETHERITE_HELMET, new SmithingData(ModItems.RUBY_UPGRADE_SMITHING_TEMPLATE, ModItems.RUBY_HELMET, ModItemTags.RUBY_TOOL_MATERIALS)),
            Map.entry(Items.NETHERITE_CHESTPLATE, new SmithingData(ModItems.RUBY_UPGRADE_SMITHING_TEMPLATE, ModItems.RUBY_CHESTPLATE, ModItemTags.RUBY_TOOL_MATERIALS)),
            Map.entry(Items.NETHERITE_LEGGINGS, new SmithingData(ModItems.RUBY_UPGRADE_SMITHING_TEMPLATE, ModItems.RUBY_LEGGINGS, ModItemTags.RUBY_TOOL_MATERIALS)),
            Map.entry(Items.NETHERITE_BOOTS, new SmithingData(ModItems.RUBY_UPGRADE_SMITHING_TEMPLATE, ModItems.RUBY_BOOTS, ModItemTags.RUBY_TOOL_MATERIALS)),
            Map.entry(Items.NETHERITE_NAUTILUS_ARMOR, new SmithingData(ModItems.RUBY_UPGRADE_SMITHING_TEMPLATE, ModItems.RUBY_NAUTILUS_ARMOR, ModItemTags.RUBY_TOOL_MATERIALS)),
            Map.entry(Items.NETHERITE_SPEAR, new SmithingData(ModItems.RUBY_UPGRADE_SMITHING_TEMPLATE, ModItems.RUBY_SPEAR, ModItemTags.RUBY_TOOL_MATERIALS)),
            Map.entry(ModItems.SAPPHIRE_SWORD, new SmithingData(ModItems.RADIANT_UPGRADE_SMITHING_TEMPLATE, ModItems.RADIANT_SWORD, ModItemTags.RADIANT_TOOL_MATERIALS)),
            Map.entry(ModItems.SAPPHIRE_PICKAXE, new SmithingData(ModItems.RADIANT_UPGRADE_SMITHING_TEMPLATE, ModItems.RADIANT_PICKAXE, ModItemTags.RADIANT_TOOL_MATERIALS)),
            Map.entry(ModItems.SAPPHIRE_AXE, new SmithingData(ModItems.RADIANT_UPGRADE_SMITHING_TEMPLATE, ModItems.RADIANT_AXE, ModItemTags.RADIANT_TOOL_MATERIALS)),
            Map.entry(ModItems.SAPPHIRE_HOE, new SmithingData(ModItems.RADIANT_UPGRADE_SMITHING_TEMPLATE, ModItems.RADIANT_HOE, ModItemTags.RADIANT_TOOL_MATERIALS)),
            Map.entry(ModItems.SAPPHIRE_SHOVEL, new SmithingData(ModItems.RADIANT_UPGRADE_SMITHING_TEMPLATE, ModItems.RADIANT_SHOVEL, ModItemTags.RADIANT_TOOL_MATERIALS)),
            Map.entry(ModItems.SAPPHIRE_HELMET, new SmithingData(ModItems.RADIANT_UPGRADE_SMITHING_TEMPLATE, ModItems.RADIANT_HELMET, ModItemTags.RADIANT_TOOL_MATERIALS)),
            Map.entry(ModItems.SAPPHIRE_CHESTPLATE, new SmithingData(ModItems.RADIANT_UPGRADE_SMITHING_TEMPLATE, ModItems.RADIANT_CHESTPLATE, ModItemTags.RADIANT_TOOL_MATERIALS)),
            Map.entry(ModItems.SAPPHIRE_LEGGINGS, new SmithingData(ModItems.RADIANT_UPGRADE_SMITHING_TEMPLATE, ModItems.RADIANT_LEGGINGS, ModItemTags.RADIANT_TOOL_MATERIALS)),
            Map.entry(ModItems.SAPPHIRE_BOOTS, new SmithingData(ModItems.RADIANT_UPGRADE_SMITHING_TEMPLATE, ModItems.RADIANT_BOOTS, ModItemTags.RADIANT_TOOL_MATERIALS))
    );

    private static final Map<Item, Item> SMELTABLES = Map.ofEntries(
            Map.entry(ModBlocks.RUBY_ORE.asItem(), ModItems.RUBY),
            Map.entry(ModBlocks.DEEPSLATE_RUBY_ORE.asItem(), ModItems.RUBY),
            Map.entry(ModBlocks.SAPPHIRE_ORE.asItem(), ModItems.SAPPHIRE),
            Map.entry(ModBlocks.DEEPSLATE_SAPPHIRE_ORE.asItem(), ModItems.SAPPHIRE),
            Map.entry(ModBlocks.GREEN_SAPPHIRE_ORE.asItem(), ModItems.GREEN_SAPPHIRE),
            Map.entry(ModBlocks.DEEPSLATE_GREEN_SAPPHIRE_ORE.asItem(), ModItems.GREEN_SAPPHIRE),
            Map.entry(ModBlocks.BLUE_GARNET_ORE.asItem(), ModItems.BLUE_GARNET),
            Map.entry(ModBlocks.DEEPSLATE_BLUE_GARNET_ORE.asItem(), ModItems.BLUE_GARNET),
            Map.entry(ModBlocks.PINK_GARNET_ORE.asItem(), ModItems.PINK_GARNET),
            Map.entry(ModBlocks.DEEPSLATE_PINK_GARNET_ORE.asItem(), ModItems.PINK_GARNET),
            Map.entry(ModBlocks.GREEN_GARNET_ORE.asItem(), ModItems.GREEN_GARNET),
            Map.entry(ModBlocks.DEEPSLATE_GREEN_GARNET_ORE.asItem(), ModItems.GREEN_GARNET),
            Map.entry(ModBlocks.KYAWTHUITE_ORE.asItem(), ModItems.KYAWTHUITE),
            Map.entry(ModBlocks.DEEPSLATE_KYAWTHUITE_ORE.asItem(), ModItems.KYAWTHUITE),
            Map.entry(ModBlocks.TOPAZ_ORE.asItem(), ModItems.TOPAZ),
            Map.entry(ModBlocks.DEEPSLATE_TOPAZ_ORE.asItem(), ModItems.TOPAZ),
            Map.entry(ModBlocks.WHITE_TOPAZ_ORE.asItem(), ModItems.WHITE_TOPAZ),
            Map.entry(ModBlocks.DEEPSLATE_WHITE_TOPAZ_ORE.asItem(), ModItems.WHITE_TOPAZ),
            Map.entry(ModBlocks.PERIDOT_ORE.asItem(), ModItems.PERIDOT),
            Map.entry(ModBlocks.DEEPSLATE_PERIDOT_ORE.asItem(), ModItems.PERIDOT),
            Map.entry(ModBlocks.JADE_ORE.asItem(), ModItems.JADE),
            Map.entry(ModBlocks.DEEPSLATE_JADE_ORE.asItem(), ModItems.JADE),
            Map.entry(ModBlocks.PYROPE_ORE.asItem(), ModItems.PYROPE),
            Map.entry(ModBlocks.DEEPSLATE_PYROPE_ORE.asItem(), ModItems.PYROPE),
            Map.entry(ModItems.RAW_RUBY, ModItems.RUBY),
            Map.entry(ModItems.RAW_SAPPHIRE, ModItems.SAPPHIRE),
            Map.entry(ModItems.RAW_GREEN_SAPPHIRE, ModItems.GREEN_SAPPHIRE),
            Map.entry(ModItems.RAW_BLUE_GARNET, ModItems.BLUE_GARNET),
            Map.entry(ModItems.RAW_PINK_GARNET, ModItems.PINK_GARNET),
            Map.entry(ModItems.RAW_GREEN_GARNET, ModItems.GREEN_GARNET),
            Map.entry(ModItems.RAW_KYAWTHUITE, ModItems.KYAWTHUITE),
            Map.entry(ModItems.RAW_TOPAZ, ModItems.TOPAZ),
            Map.entry(ModItems.RAW_WHITE_TOPAZ, ModItems.WHITE_TOPAZ),
            Map.entry(ModItems.RAW_PERIDOT, ModItems.PERIDOT),
            Map.entry(ModItems.RAW_JADE, ModItems.JADE),
            Map.entry(ModItems.RAW_PYROPE, ModItems.PYROPE)
            );


    private static final Map<Item, Item> GEM_POLISHABLES = Map.ofEntries(
            Map.entry(ModItems.RAW_RUBY, ModItems.RUBY),
            Map.entry(ModBlocks.RAW_RUBY_BLOCK.asItem(),  ModBlocks.RUBY_BLOCK.asItem()),
            Map.entry(ModItems.RAW_SAPPHIRE, ModItems.SAPPHIRE),
            Map.entry(ModBlocks.RAW_SAPPHIRE_BLOCK.asItem(), ModBlocks.SAPPHIRE_BLOCK.asItem()),
            Map.entry(ModItems.RAW_GREEN_SAPPHIRE, ModItems.GREEN_SAPPHIRE),
            Map.entry(ModBlocks.RAW_GREEN_SAPPHIRE_BLOCK.asItem(), ModBlocks.GREEN_SAPPHIRE_BLOCK.asItem()),
            Map.entry(ModItems.RAW_BLUE_GARNET, ModItems.BLUE_GARNET),
            Map.entry(ModBlocks.RAW_BLUE_GARNET_BLOCK.asItem(), ModBlocks.BLUE_GARNET_BLOCK.asItem()),
            Map.entry(ModItems.RAW_PINK_GARNET, ModItems.PINK_GARNET),
            Map.entry(ModBlocks.RAW_PINK_GARNET_BLOCK.asItem(), ModBlocks.PINK_GARNET_BLOCK.asItem()),
            Map.entry(ModItems.RAW_GREEN_GARNET, ModItems.GREEN_GARNET),
            Map.entry(ModBlocks.RAW_GREEN_GARNET_BLOCK.asItem(), ModBlocks.GREEN_GARNET_BLOCK.asItem()),
            Map.entry(ModItems.RAW_KYAWTHUITE, ModItems.KYAWTHUITE),
            Map.entry(ModBlocks.RAW_KYAWTHUITE_BLOCK.asItem(), ModBlocks.KYAWTHUITE_BLOCK.asItem()),
            Map.entry(ModItems.RAW_TOPAZ, ModItems.TOPAZ),
            Map.entry(ModBlocks.RAW_TOPAZ_BLOCK.asItem(), ModBlocks.TOPAZ_BLOCK.asItem()),
            Map.entry(ModItems.RAW_WHITE_TOPAZ, ModItems.WHITE_TOPAZ),
            Map.entry(ModBlocks.RAW_WHITE_TOPAZ_BLOCK.asItem(), ModBlocks.WHITE_TOPAZ_BLOCK.asItem()),
            Map.entry(ModItems.RAW_PERIDOT, ModItems.PERIDOT),
            Map.entry(ModBlocks.RAW_PERIDOT_BLOCK.asItem(), ModBlocks.PERIDOT_BLOCK.asItem()),
            Map.entry(ModItems.RAW_JADE, ModItems.JADE),
            Map.entry(ModBlocks.RAW_JADE_BLOCK.asItem(), ModBlocks.JADE_BLOCK.asItem()),
            Map.entry(ModItems.RAW_PYROPE, ModItems.PYROPE),
            Map.entry(ModBlocks.RAW_PYROPE_BLOCK.asItem(), ModBlocks.PYROPE_BLOCK.asItem())
    );

    private static final Map<Item, Item> GEM_INFUSES = Map.ofEntries(
            Map.entry(ModItems.RUBY, ModItems.ALEXANDRITE),
            Map.entry(ModBlocks.RUBY_BLOCK.asItem(),  ModBlocks.ALEXANDRITE_BLOCK.asItem()),
            Map.entry(ModItems.SAPPHIRE, ModItems.KASHMIR_SAPPHIRE),
            Map.entry(ModBlocks.SAPPHIRE_BLOCK.asItem(), ModBlocks.KASHMIR_SAPPHIRE_BLOCK.asItem()),
            Map.entry(ModItems.GREEN_SAPPHIRE, ModItems.CRYSTALLITE),
            Map.entry(ModBlocks.GREEN_SAPPHIRE_BLOCK.asItem(), ModBlocks.CRYSTALLITE_BLOCK.asItem()),
            Map.entry(ModItems.BLUE_GARNET, ModItems.CRIMSON_GARNET),
            Map.entry(ModBlocks.BLUE_GARNET_BLOCK.asItem(), ModBlocks.CRIMSON_GARNET_BLOCK.asItem()),
            Map.entry(ModItems.PINK_GARNET, ModItems.RADIANT_AMETHYST),
            Map.entry(ModBlocks.PINK_GARNET_BLOCK.asItem(), ModBlocks.RADIANT_AMETHYST_BLOCK.asItem()),
            Map.entry(ModItems.GREEN_GARNET, ModItems.LIMESTONE),
            Map.entry(ModBlocks.GREEN_GARNET_BLOCK.asItem(), ModBlocks.LIMESTONE_BLOCK.asItem()),
            Map.entry(ModItems.KYAWTHUITE, ModItems.ORANGE_ZIRCON),
            Map.entry(ModBlocks.KYAWTHUITE_BLOCK.asItem(), ModBlocks.ORANGE_ZIRCON_BLOCK.asItem()),
            Map.entry(ModItems.WHITE_TOPAZ, ModItems.MOONSTONE),
            Map.entry(ModBlocks.WHITE_TOPAZ_BLOCK.asItem(), ModBlocks.MOONSTONE_BLOCK.asItem()),
            Map.entry(ModItems.PERIDOT, ModItems.OPAL),
            Map.entry(ModBlocks.PERIDOT_BLOCK.asItem(), ModBlocks.OPAL_BLOCK.asItem()),
            Map.entry(ModItems.JADE, ModItems.GRANDIDIERITE),
            Map.entry(ModBlocks.JADE_BLOCK.asItem(), ModBlocks.GRANDIDIERITE_BLOCK.asItem()),
            Map.entry(ModItems.PYROPE, ModItems.RED_BERYL),
            Map.entry(ModBlocks.PYROPE_BLOCK.asItem(), ModBlocks.RED_BERYL_BLOCK.asItem())
    );

    private static final  Map<Item, Item> SAPPHIRE_MAP = Map.ofEntries(
            Map.entry(ModItems.RUBY_SWORD, ModItems.SAPPHIRE_SWORD),
            Map.entry(ModItems.RUBY_PICKAXE, ModItems.SAPPHIRE_PICKAXE),
            Map.entry(ModItems.RUBY_AXE, ModItems.SAPPHIRE_AXE),
            Map.entry(ModItems.RUBY_SHOVEL, ModItems.SAPPHIRE_SHOVEL),
            Map.entry(ModItems.RUBY_HOE, ModItems.SAPPHIRE_HOE),
            Map.entry(ModItems.RUBY_SPEAR, ModItems.SAPPHIRE_SPEAR),
            Map.entry(ModItems.RUBY_HELMET, ModItems.SAPPHIRE_HELMET),
            Map.entry(ModItems.RUBY_CHESTPLATE, ModItems.SAPPHIRE_CHESTPLATE),
            Map.entry(ModItems.RUBY_LEGGINGS, ModItems.SAPPHIRE_LEGGINGS),
            Map.entry(ModItems.RUBY_BOOTS, ModItems.SAPPHIRE_BOOTS),
            Map.entry(ModItems.RUBY_NAUTILUS_ARMOR, ModItems.SAPPHIRE_NAUTILUS_ARMOR)
    );

    public AutomaticRecipeCreator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup wrapperLookup, RecipeExporter recipeExporter) {
        return new RecipeGenerator(wrapperLookup, recipeExporter) {
            @Override
            public void generate() {
                int defaultSmeltingTime = 1500;
                int defaultBlastingTime = 750;

                for (Block block : Registries.BLOCK) {
                    Identifier id = Registries.BLOCK.getId(block);

                    if(id.getNamespace().equals(MoreOresModInitializer.MOD_ID)) {
                        if(block.getDefaultState().isOf(ModBlocks.GEM_CRYSTALLIZER_BLOCK) || block.getDefaultState().isOf(ModBlocks.GEM_PURIFIER_BLOCK)) {
                            continue;
                        }
                        String path = id.getPath();
                        if(path.endsWith("_block")) {
                            String itemName = path.replace("_block", "");
                            Item item = Registries.ITEM.get(MoreOresModInitializer.id(itemName));
                            if(block.getDefaultState().isOf(ModBlocks.RADIANT_BLOCK)) {
                                createShaped(RecipeCategory.MISC, ModItems.RADIANT, 1)
                                        .pattern("aaa")
                                        .pattern("aba")
                                        .pattern("aaa")
                                        .input('a', ModBlocks.RUBY_BLOCK)
                                        .input('b', Items.DIAMOND)
                                        .criterion(hasItem(ModBlocks.RUBY_BLOCK), conditionsFromItem(ModBlocks.RUBY_BLOCK))
                                        .criterion(hasItem(Items.DIAMOND), conditionsFromItem(Items.DIAMOND))
                                        .offerTo(exporter, MoreOresModInitializer.recipeKey(getRecipeName(ModItems.RADIANT) + "_from_ruby"));
                                offerReversibleCompactingRecipes(RecipeCategory.MISC, ModItems.RADIANT, RecipeCategory.MISC, ModBlocks.RADIANT_BLOCK);
                                continue;
                            }
                            if(block.getDefaultState().isOf(ModBlocks.ENERGY_BLOCK)) {
                                createShaped(RecipeCategory.MISC, ModBlocks.ENERGY_BLOCK, 1)
                                        .pattern("aaa")
                                        .pattern("aba")
                                        .pattern("aaa")
                                        .input('a', ModItems.RADIANT)
                                        .input('b', Blocks.TNT)
                                        .criterion(hasItem(ModItems.RADIANT), conditionsFromItem(ModItems.RADIANT))
                                        .criterion(hasItem(Blocks.TNT), conditionsFromItem(Blocks.TNT))
                                        .offerTo(exporter, MoreOresModInitializer.recipeKey(getRecipeName(ModBlocks.ENERGY_BLOCK) + "_from_radiant"));

                                createShapeless(RecipeCategory.MISC, ModItems.ENERGY_INGOT, 9)
                                        .criterion(hasItem(ModBlocks.ENERGY_BLOCK), conditionsFromItem(ModItems.ENERGY_INGOT))
                                        .input(ModItems.ENERGY_INGOT)
                                        .offerTo(exporter, MoreOresModInitializer.recipeKey(getRecipeName(ModItems.ENERGY_INGOT)));
                                continue;
                            }

                            offerReversibleCompactingRecipes(RecipeCategory.BUILDING_BLOCKS, item, RecipeCategory.DECORATIONS, block);
                        }
                    }
                }

                for(Map.Entry<Item, Item> entry : SAPPHIRE_MAP.entrySet()) {
                    Item inputItem = entry.getKey();
                    Item outputItem = entry.getValue();
                    String path = Registries.ITEM.getId(outputItem).getPath();
                    RecipeCategory category = (path.contains("_pickaxe") || path.contains("hoe") || path.contains("_shovel"))
                            ? RecipeCategory.TOOLS : RecipeCategory.COMBAT;
                    createShaped(category, outputItem)
                            .pattern("aaa")
                            .pattern("aba")
                            .pattern("aaa")
                            .input('a', ModItems.SAPPHIRE)
                            .input('b', inputItem)
                            .criterion(hasItem(ModItems.SAPPHIRE),  conditionsFromItem(ModItems.SAPPHIRE))
                            .criterion(hasItem(inputItem), conditionsFromItem(inputItem))
                            .offerTo(exporter, MoreOresModInitializer.recipeKey(getRecipeName(outputItem)));
                }

                for (Map.Entry<Item, SmithingData> entry : SMITHING_DATA.entrySet()) {
                    Item baseItem = entry.getKey();
                    SmithingData data = entry.getValue();
                    Item result = data.result();
                    Item template = data.template();
                    TagKey<Item> tag = data.toolTag();
                    String path = Registries.ITEM.getId(result).getPath();
                    RecipeCategory category = (path.contains("_pickaxe") || path.contains("hoe") || path.contains("_shovel"))
                            ? RecipeCategory.TOOLS : RecipeCategory.COMBAT;
                    SmithingTransformRecipeJsonBuilder.create(Ingredient.ofItem(template), Ingredient.ofItem(baseItem), ingredientFromTag(tag), category, result)
                            .criterion(hasItem(ModItems.RUBY), conditionsFromTag(tag))
                            .offerTo(exporter, MoreOresModInitializer.recipeKey(getRecipeName(result) + "_smithing"));
                }

                for (var entry: SMELTABLES.entrySet()) {
                    var input = entry.getKey();
                    var output = entry.getValue();

                    offerSmelting(List.of(input), RecipeCategory.MISC, output, .15f, defaultSmeltingTime, output.toString());
                    offerBlasting(List.of(input), RecipeCategory.MISC, output, .15f, defaultBlastingTime, output.toString());
                }

                for (var entry : GEM_POLISHABLES.entrySet()) {
                    var input = entry.getKey();
                    var result = entry.getValue();

                    createGemPurifying(Ingredient.ofItem(input), new ItemStack(result))
                            .criterion(hasItem(input), conditionsFromItem(result))
                            .offerTo(exporter, getRecipeName(result));
                }

                for (var entry : GEM_INFUSES.entrySet()) {
                    Item inputBefore = entry.getKey();
                    Item result = entry.getValue();

                    createGemInfusion(Ingredient.ofItem(inputBefore), new ItemStack(result))
                            .criterion(hasItem(inputBefore), conditionsFromItem(result))
                            .offerTo(exporter, getRecipeName(result));
                }

                offerBlasting(List.of(ModItems.RUBY), RecipeCategory.MISC, Items.NETHERITE_INGOT, 0.15f, 450, "netherite");

                createShaped(
                        RecipeCategory.REDSTONE, ModBlocks.GEM_CRYSTALLIZER_BLOCK
                )
                        .pattern("aba")
                        .pattern("cdc")
                        .pattern("ccc")
                        .input('a', Items.REDSTONE)
                        .input('b', Ingredient.ofItems(ModItems.ENERGY_INGOT, ModBlocks.ENERGY_BLOCK.asItem()))
                        .input('c', Ingredient.ofItems(Blocks.IRON_BLOCK.asItem()))
                        .input('d', Ingredient.ofItems(ModBlocks.GEM_PURIFIER_BLOCK.asItem()))
                        .criterion(hasItem(ModBlocks.GEM_PURIFIER_BLOCK.asItem()), conditionsFromItem(ModBlocks.GEM_PURIFIER_BLOCK.asItem()))
                        .offerTo(exporter, getRecipeName(ModBlocks.GEM_CRYSTALLIZER_BLOCK.asItem()));

                offerSmithingTrimRecipe(ModItems.GUARDIAN_ARMOR_TRIM_SMITHING_TEMPLATE,
                        ModArmorTrimPatterns.GUARDIAN, RegistryKey.of(RegistryKeys.RECIPE, Identifier.ofVanilla(getItemPath(ModItems.GUARDIAN_ARMOR_TRIM_SMITHING_TEMPLATE) + "_smithing_trim")));

                GemCrystallizerRecipeJsonBuilder.createQuartsidian()
                        .criterion(hasItem(Items.QUARTZ), conditionsFromItem(Items.QUARTZ))
                        .criterion(hasItem(Blocks.OBSIDIAN), conditionsFromItem(Blocks.OBSIDIAN))
                        .offerTo(exporter, getRecipeName(ModItems.QUARTSIDIAN));

                createShaped(RecipeCategory.MISC, ModBlocks.GEM_PURIFIER_BLOCK, 1)
                        .pattern("III")
                        .pattern("III")
                        .pattern("B B")
                        .input('I', Blocks.IRON_BLOCK)
                        .input('B', Blocks.IRON_BARS)
                        .criterion(hasItem(Blocks.IRON_BLOCK), conditionsFromItem(Blocks.IRON_BLOCK))
                        .criterion(hasItem(Blocks.IRON_BARS), conditionsFromItem(Blocks.IRON_BARS))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(getRecipeName(ModBlocks.GEM_PURIFIER_BLOCK))));

                createShaped(RecipeCategory.REDSTONE, ModBlocks.RUBY_LAMP, 1)
                        .pattern("aba")
                        .pattern("bcb")
                        .pattern("aba")
                        .input('a', Items.REDSTONE)
                        .input('b', ModItems.RUBY)
                        .input('c', Blocks.GLOWSTONE)
                        .criterion(hasItem(Items.REDSTONE), conditionsFromItem(Items.REDSTONE))
                        .criterion(hasItem(ModItems.RUBY), conditionsFromItem(ModItems.RUBY))
                        .criterion(hasItem(Blocks.GLOWSTONE), conditionsFromItem(Blocks.GLOWSTONE))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(getRecipeName(ModBlocks.RUBY_LAMP))));

                createShaped(RecipeCategory.MISC, ModItems.RUBY_UPGRADE_SMITHING_TEMPLATE, 2)
                        .pattern("aba")
                        .pattern("aca")
                        .pattern("aaa")
                        .input('a', ModItems.RUBY)
                        .input('b', ModItems.RUBY_UPGRADE_SMITHING_TEMPLATE)
                        .input('c', Blocks.STONE)
                        .criterion(hasItem(ModItems.RUBY), conditionsFromItem(ModItems.RUBY))
                        .offerTo(exporter, MoreOresModInitializer.recipeKey(getRecipeName(ModItems.RUBY_UPGRADE_SMITHING_TEMPLATE) + "_duplication"));

                createShaped(RecipeCategory.MISC, ModItems.RADIANT_UPGRADE_SMITHING_TEMPLATE, 2)
                        .pattern("aba")
                        .pattern("aca")
                        .pattern("aaa")
                        .input('a', ModItems.SAPPHIRE)
                        .input('b', ModItems.RADIANT_UPGRADE_SMITHING_TEMPLATE)
                        .input('c', ModBlocks.RUBY_BLOCK)
                        .criterion(hasItem(ModItems.RUBY), conditionsFromItem(ModItems.RUBY))
                        .offerTo(exporter, MoreOresModInitializer.recipeKey(getRecipeName(ModItems.RADIANT_UPGRADE_SMITHING_TEMPLATE) + "_duplication"));

                createShaped(RecipeCategory.MISC, ModItems.RUBY_UPGRADE_SMITHING_TEMPLATE)
                        .pattern("aba")
                        .pattern("aca")
                        .pattern("aaa")
                        .input('a', Blocks.STONE)
                        .input('b', Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE)
                        .input('c', ModItems.RUBY)
                        .criterion(hasItem(ModItems.RUBY), conditionsFromItem(ModItems.RUBY))
                        .criterion(hasItem(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE), conditionsFromItem(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE))
                        .offerTo(exporter, MoreOresModInitializer.recipeKey(getRecipeName(ModItems.RUBY_UPGRADE_SMITHING_TEMPLATE)));

                createShaped(RecipeCategory.MISC, ModItems.RADIANT_UPGRADE_SMITHING_TEMPLATE)
                        .pattern("aba")
                        .pattern("aca")
                        .pattern("aaa")
                        .input('a', ModBlocks.RUBY_BLOCK)
                        .input('b', ModItems.RUBY_UPGRADE_SMITHING_TEMPLATE)
                        .input('c', ModItems.SAPPHIRE)
                        .criterion(hasItem(ModItems.SAPPHIRE), conditionsFromItem(ModItems.SAPPHIRE))
                        .criterion(hasItem(ModItems.RUBY_UPGRADE_SMITHING_TEMPLATE), conditionsFromItem(ModItems.RUBY_UPGRADE_SMITHING_TEMPLATE))
                        .offerTo(exporter, MoreOresModInitializer.recipeKey(getRecipeName(ModItems.RADIANT_UPGRADE_SMITHING_TEMPLATE)));
                
                createShaped(RecipeCategory.MISC, ModItems.ECLIPSE_GEM)
                        .pattern("abc")
                        .pattern("def")
                        .pattern("ghi")
                        .input('a', ModItems.RADIANT_AMETHYST)
                        .input('b', ModItems.MOONSTONE)
                        .input('c', ModItems.LIMESTONE)
                        .input('d', ModItems.QUARTSIDIAN)
                        .input('e', ModItems.CRYSTAL_OF_ECLIPSE)
                        .input('f', ModItems.ALEXANDRITE)
                        .input('g', ModItems.ORANGE_ZIRCON)
                        .input('h', ModItems.OPAL)
                        .input('i', ModItems.GRANDIDIERITE)
                        .criterion(hasItem(ModItems.CRYSTAL_OF_ECLIPSE), conditionsFromItem(ModItems.CRYSTAL_OF_ECLIPSE))
                        .offerTo(exporter, MoreOresModInitializer.recipeKey(getRecipeName(ModItems.ECLIPSE_GEM)));
                
                createShaped(RecipeCategory.COMBAT, ModItems.RADIANT_BOW)
                        .pattern(" ab")
                        .pattern("a b")
                        .pattern(" ab")
                        .input('a', Items.STRING)
                        .input('b', ModItems.ECLIPSE_GEM)
                        .criterion(hasItem(ModItems.ECLIPSE_GEM), conditionsFromItem(ModItems.ECLIPSE_GEM))
                        .offerTo(exporter, MoreOresModInitializer.recipeKey(getRecipeName(ModItems.RADIANT_BOW)));

                createShaped(RecipeCategory.MISC, ModItems.GEM_ARROW, 32)
                        .pattern("abc")
                        .pattern("def")
                        .pattern("ghi")
                        .input('a', ModItems.RADIANT_AMETHYST)
                        .input('b', ModItems.MOONSTONE)
                        .input('c', ModItems.LIMESTONE)
                        .input('d', ModItems.QUARTSIDIAN)
                        .input('e', Items.ARROW)
                        .input('f', ModItems.ALEXANDRITE)
                        .input('g', ModItems.ORANGE_ZIRCON)
                        .input('h', ModItems.OPAL)
                        .input('i', ModItems.GRANDIDIERITE)
                        .criterion(hasItem(Items.ARROW), conditionsFromItem(Items.ARROW))
                        .offerTo(exporter, MoreOresModInitializer.recipeKey(getRecipeName(ModItems.GEM_ARROW)));
            }
        };
    }

    public GemPolishingRecipeJsonBuilder createGemPurifying(Ingredient input, ItemStack result) {
        return GemPolishingRecipeJsonBuilder.create(input, result, RecipeCategory.MISC);
    }

    public GemCrystallizerRecipeJsonBuilder createGemInfusion(Ingredient inputBefore, ItemStack result) {
        return GemCrystallizerRecipeJsonBuilder.create(inputBefore, result, RecipeCategory.MISC);
    }

    @Override
    public String getName() {
        return "Mod Recipes Gen for " + MoreOresModInitializer.MOD_ID;
    }

    private record SmithingData(Item template, Item result, TagKey<Item> toolTag) {

    }
}