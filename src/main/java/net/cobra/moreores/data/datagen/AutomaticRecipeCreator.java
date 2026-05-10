package net.cobra.moreores.data.datagen;

import net.cobra.moreores.MoreOresModInitializer;
import net.cobra.moreores.block.ModBlocks;
import net.cobra.moreores.client.recipe.GemInfusionRecipeJsonBuilder;
import net.cobra.moreores.client.recipe.GemPolishingRecipeJsonBuilder;
import net.cobra.moreores.item.ModItems;
import net.cobra.moreores.item.equipment.trim.ModArmorTrimPatterns;
import net.cobra.moreores.registry.ModItemTags;
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
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class AutomaticRecipeCreator extends FabricRecipeProvider {
    private static final Map<Item, Item> RUBY_UPGRADES = Map.ofEntries(
            Map.entry(),
            Map.entry(),
            Map.entry(),
            Map.entry(),
            Map.entry(),
            Map.entry(),
            Map.entry(),
            Map.entry(),
            Map.entry(),
            Map.entry(),
            Map.entry()
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
                        if(block.getDefaultState().isOf(ModBlocks.GEM_INFUSION_BLOCK) || block.getDefaultState().isOf(ModBlocks.GEM_PURIFIER_BLOCK)) {
                            continue;
                        }
                        String path = id.getPath();
                        if(path.endsWith("_block")) {
                            String itemName = path.replace("_block", "");
                            Item item = Registries.ITEM.get(MoreOresModInitializer.getId(itemName));
                            if(block.getDefaultState().isOf(ModBlocks.ENERGY_BLOCK)) {
                                createShaped(RecipeCategory.MISC, ModBlocks.ENERGY_BLOCK, 1)
                                        .pattern("aaa")
                                        .pattern("aba")
                                        .pattern("aaa")
                                        .input('a', ModItems.RADIANT)
                                        .input('b', Blocks.TNT)
                                        .criterion(hasItem(ModItems.RADIANT), conditionsFromItem(ModItems.RADIANT))
                                        .criterion(hasItem(Blocks.TNT), conditionsFromItem(Blocks.TNT))
                                        .offerTo(exporter, MoreOresModInitializer.setRecipeKey(getRecipeName(ModBlocks.ENERGY_BLOCK) + "_from_radiant"));

                                createShapeless(RecipeCategory.MISC, ModItems.ENERGY_INGOT, 9)
                                        .criterion(hasItem(ModBlocks.ENERGY_BLOCK), conditionsFromItem(ModItems.ENERGY_INGOT))
                                        .input(ModItems.ENERGY_INGOT)
                                        .offerTo(exporter, MoreOresModInitializer.setRecipeKey(getRecipeName(ModItems.ENERGY_INGOT)));
                                continue;
                            }

                            offerReversibleCompactingRecipes(RecipeCategory.BUILDING_BLOCKS, item, RecipeCategory.DECORATIONS, block);
                        }
                    }
                }

                for (Map.Entry<Item, Item> entry : RUBY_UPGRADES.entrySet()) {
                    Item baseItem = entry.getKey();
                    Item result = entry.getValue();
                    String path = Registries.ITEM.getId(result).getPath();
                    RecipeCategory category = (path.contains("_pickaxe") || path.contains("hoe") || path.contains("_shovel")) ? RecipeCategory.TOOLS : RecipeCategory.COMBAT;
                    createRubySet(registries, baseItem, category, result)
                            .criterion(hasItem(baseItem), conditionsFromItem(baseItem))
                            .offerTo(exporter, MoreOresModInitializer.setRecipeKey(getRecipeName(result) + "_smithing"));

                }

                for (var entry: SMELTABLES.entrySet()) {
                    var input = entry.getKey();
                    var output = entry.getValue();

                    offerSmelting(List.of(input), RecipeCategory.MISC, output, .15f, defaultSmeltingTime, output.toString());
                    offerBlasting(List.of(input), RecipeCategory.MISC, output, .15f, defaultBlastingTime, output.toString());
                }

                for(var entry : GEM_POLISHABLES.entrySet()) {
                    var input = entry.getKey();
                    var output = entry.getValue();

                    createGemPurifying(Ingredient.ofItem(input), new ItemStack(output));
                }

                offerBlasting(List.of(ModItems.RUBY), RecipeCategory.MISC, Items.NETHERITE_INGOT, 0.15f, 450, "netherite");

                createShaped(
                        RecipeCategory.REDSTONE, ModBlocks.GEM_INFUSION_BLOCK
                )
                        .pattern("aba")
                                .pattern("cdc")
                                        .pattern("ccc")
                                                .input('a', Items.REDSTONE)
                                                        .input('b', Ingredient.ofItems(ModItems.ENERGY_INGOT, ModBlocks.ENERGY_BLOCK.asItem()))
                        .input('c', Ingredient.ofItems(Blocks.IRON_BLOCK.asItem()))
                        .input('d', Ingredient.ofItems(ModBlocks.GEM_PURIFIER_BLOCK.asItem()))
                        .criterion(hasItem(ModBlocks.GEM_PURIFIER_BLOCK.asItem()), conditionsFromItem(ModBlocks.GEM_PURIFIER_BLOCK.asItem()))
                        .offerTo(exporter, getRecipeName(ModBlocks.GEM_INFUSION_BLOCK.asItem()));

                offerSmithingTrimRecipe(ModItems.GUARDIAN_ARMOR_TRIM_SMITHING_TEMPLATE,
                        ModArmorTrimPatterns.GUARDIAN, RegistryKey.of(RegistryKeys.RECIPE, Identifier.ofVanilla(getItemPath(ModItems.GUARDIAN_ARMOR_TRIM_SMITHING_TEMPLATE) + "_smithing_trim")));

                // Gem Infusion
                createGemInfusion(
                        Ingredient.ofItem(ModItems.RUBY), new ItemStack(ModItems.ALEXANDRITE)
                )
                        .criterion(hasItem(ModItems.RUBY.asItem()), conditionsFromItem(ModItems.RUBY))
                        .offerTo(exporter, getRecipeName(ModItems.ALEXANDRITE));

                createGemInfusion(
                        Ingredient.ofItem(ModItems.SAPPHIRE), new ItemStack(ModItems.KASHMIR_SAPPHIRE)
                )
                        .criterion(hasItem(ModItems.SAPPHIRE.asItem()), conditionsFromItem(ModItems.SAPPHIRE))
                        .offerTo(exporter, getRecipeName(ModItems.KASHMIR_SAPPHIRE));

                createGemInfusion(
                        Ingredient.ofItem(ModItems.GREEN_SAPPHIRE), new ItemStack(ModItems.CRYSTALLITE)
                )
                        .criterion(hasItem(ModItems.GREEN_SAPPHIRE), conditionsFromItem(ModItems.GREEN_SAPPHIRE))
                                .offerTo(exporter, getRecipeName(ModItems.CRYSTALLITE));

                createGemInfusion(
                        Ingredient.ofItem(ModItems.BLUE_GARNET), new ItemStack(ModItems.CRIMSON_GARNET)
                )
                        .criterion(hasItem(ModItems.BLUE_GARNET), conditionsFromItem(ModItems.BLUE_GARNET))
                        .offerTo(exporter, getRecipeName(ModItems.CRIMSON_GARNET));

                createGemInfusion(
                        Ingredient.ofItem(ModItems.PINK_GARNET), new ItemStack(ModItems.RADIANT_AMETHYST)
                )
                        .criterion(hasItem(ModItems.PINK_GARNET), conditionsFromItem(ModItems.PINK_GARNET))
                        .offerTo(exporter, getRecipeName(ModItems.RADIANT_AMETHYST));

                createGemInfusion(
                        Ingredient.ofItem(ModItems.GREEN_GARNET), new ItemStack(ModItems.LIMESTONE)
                )
                        .criterion(hasItem(ModItems.GREEN_GARNET), conditionsFromItem(ModItems.GREEN_GARNET))
                        .offerTo(exporter, getRecipeName(ModItems.LIMESTONE));

                createGemInfusion(
                        Ingredient.ofItem(ModItems.KYAWTHUITE), new ItemStack(ModItems.PAINITE)
                )
                        .criterion(hasItem(ModItems.KYAWTHUITE), conditionsFromItem(ModItems.KYAWTHUITE))
                        .offerTo(exporter, getRecipeName(ModItems.PAINITE));

                createGemInfusion(
                        Ingredient.ofItem(ModItems.WHITE_TOPAZ), new ItemStack(ModItems.MOONSTONE)
                )
                        .criterion(hasItem(ModItems.WHITE_TOPAZ), conditionsFromItem(ModItems.WHITE_TOPAZ))
                        .offerTo(exporter, getRecipeName(ModItems.MOONSTONE));

                createGemInfusion(
                        Ingredient.ofItem(ModItems.PERIDOT), new ItemStack(ModItems.OPAL)
                )
                        .criterion(hasItem(ModItems.PERIDOT), conditionsFromItem(ModItems.PERIDOT))
                        .offerTo(exporter, getRecipeName(ModItems.OPAL));

                createGemInfusion(
                        Ingredient.ofItem(ModItems.JADE), new ItemStack(ModItems.GRANDIDIERITE)
                )
                        .criterion(hasItem(ModItems.JADE), conditionsFromItem(ModItems.JADE))
                        .offerTo(exporter, getRecipeName(ModItems.GRANDIDIERITE));

                createGemInfusion(
                        Ingredient.ofItem(ModItems.PYROPE), new ItemStack(ModItems.RED_BERYL)
                )
                        .criterion(hasItem(ModItems.PYROPE), conditionsFromItem(ModItems.PYROPE))
                        .offerTo(exporter, getRecipeName(ModItems.RED_BERYL));

                GemInfusionRecipeJsonBuilder.createQuartsidian();

                createShaped(RecipeCategory.MISC, ModItems.RADIANT, 1)
                        .pattern("aaa")
                        .pattern("aba")
                        .pattern("aaa")
                                .input('a', ModBlocks.RUBY_BLOCK)
                                .input('b', Items.DIAMOND)
                                        .criterion(hasItem(ModBlocks.RUBY_BLOCK), conditionsFromItem(ModBlocks.RUBY_BLOCK))
                                        .criterion(hasItem(Items.DIAMOND), conditionsFromItem(Items.DIAMOND))
                                                .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(getRecipeName(ModItems.RADIANT) + "_from_ruby")));

                createShaped(RecipeCategory.MISC, ModBlocks.GEM_PURIFIER_BLOCK, 1)
                        .pattern("III")
                        .pattern("III")
                        .pattern("B B")
                        .input('I', Blocks.IRON_BLOCK)
                        .input('B', Blocks.IRON_BARS)
                        .criterion(hasItem(Blocks.IRON_BLOCK), conditionsFromItem(Blocks.IRON_BLOCK))
                        .criterion(hasItem(Blocks.IRON_BARS), conditionsFromItem(Blocks.IRON_BARS))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(getRecipeName(ModBlocks.GEM_PURIFIER_BLOCK))));

                createShaped(RecipeCategory.COMBAT, ModItems.RADIANT_SWORD, 1)
                        .pattern(" I ")
                        .pattern(" I ")
                        .pattern(" B ")
                        .input('I', ModItems.RADIANT)
                        .input('B', Items.STICK)
                        .criterion(hasItem(ModItems.RADIANT), conditionsFromItem(ModItems.RADIANT))
                        .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(getRecipeName(ModItems.RADIANT_SWORD))));

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

                // Helmet
                createShaped(RecipeCategory.COMBAT, ModItems.SAPPHIRE_HELMET, 1)
                        .pattern("aaa")
                        .pattern("a a")
                        .pattern("   ")
                        .input('a', ModItems.SAPPHIRE)
                        .criterion(hasItem(ModItems.SAPPHIRE), conditionsFromItem(ModItems.SAPPHIRE))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(getRecipeName(ModItems.SAPPHIRE_HELMET))));

// Chestplate
                createShaped(RecipeCategory.COMBAT, ModItems.SAPPHIRE_CHESTPLATE, 1)
                        .pattern("a a")
                        .pattern("aaa")
                        .pattern("aaa")
                        .input('a', ModItems.SAPPHIRE)
                        .criterion(hasItem(ModItems.SAPPHIRE), conditionsFromItem(ModItems.SAPPHIRE))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(getRecipeName(ModItems.SAPPHIRE_CHESTPLATE))));

// Leggings
                createShaped(RecipeCategory.COMBAT, ModItems.SAPPHIRE_LEGGINGS, 1)
                        .pattern("aaa")
                        .pattern("a a")
                        .pattern("a a")
                        .input('a', ModItems.SAPPHIRE)
                        .criterion(hasItem(ModItems.SAPPHIRE), conditionsFromItem(ModItems.SAPPHIRE))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(getRecipeName(ModItems.SAPPHIRE_LEGGINGS))));

// Boots
                createShaped(RecipeCategory.COMBAT, ModItems.SAPPHIRE_BOOTS, 1)
                        .pattern("   ")
                        .pattern("a a")
                        .pattern("a a")
                        .input('a', ModItems.SAPPHIRE)
                        .criterion(hasItem(ModItems.SAPPHIRE), conditionsFromItem(ModItems.SAPPHIRE))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(getRecipeName(ModItems.SAPPHIRE_BOOTS))));

// Sword
                createShaped(RecipeCategory.COMBAT, ModItems.SAPPHIRE_SWORD, 1)
                        .pattern(" a ")
                        .pattern(" a ")
                        .pattern(" b ")
                        .input('a', ModItems.SAPPHIRE)
                        .input('b', Items.STICK)
                        .criterion(hasItem(ModItems.SAPPHIRE), conditionsFromItem(ModItems.SAPPHIRE))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(getRecipeName(ModItems.SAPPHIRE_SWORD))));

// Pickaxe
                createShaped(RecipeCategory.TOOLS, ModItems.SAPPHIRE_PICKAXE, 1)
                        .pattern("aaa")
                        .pattern(" b ")
                        .pattern(" b ")
                        .input('a', ModItems.SAPPHIRE)
                        .input('b', Items.STICK)
                        .criterion(hasItem(ModItems.SAPPHIRE), conditionsFromItem(ModItems.SAPPHIRE))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(getRecipeName(ModItems.SAPPHIRE_PICKAXE))));

// Axe
                createShaped(RecipeCategory.TOOLS, ModItems.SAPPHIRE_AXE, 1)
                        .pattern("aa ")
                        .pattern("ab ")
                        .pattern(" b ")
                        .input('a', ModItems.SAPPHIRE)
                        .input('b', Items.STICK)
                        .criterion(hasItem(ModItems.SAPPHIRE), conditionsFromItem(ModItems.SAPPHIRE))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(getRecipeName(ModItems.SAPPHIRE_AXE))));

// Shovel
                createShaped(RecipeCategory.TOOLS, ModItems.SAPPHIRE_SHOVEL, 1)
                        .pattern(" a ")
                        .pattern(" b ")
                        .pattern(" b ")
                        .input('a', ModItems.SAPPHIRE)
                        .input('b', Items.STICK)
                        .criterion(hasItem(ModItems.SAPPHIRE), conditionsFromItem(ModItems.SAPPHIRE))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(getRecipeName(ModItems.SAPPHIRE_SHOVEL))));

// Hoe
                createShaped(RecipeCategory.TOOLS, ModItems.SAPPHIRE_HOE, 1)
                        .pattern("aa ")
                        .pattern(" b ")
                        .pattern(" b ")
                        .input('a', ModItems.SAPPHIRE)
                        .input('b', Items.STICK)
                        .criterion(hasItem(ModItems.SAPPHIRE), conditionsFromItem(ModItems.SAPPHIRE))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(getRecipeName(ModItems.SAPPHIRE_HOE))));

                createShaped(RecipeCategory.MISC, ModItems.RUBY_UPGRADE_SMITHING_TEMPLATE, 2)
                        .pattern("aba")
                        .pattern("aca")
                        .pattern("aaa")
                        .input('a', ModItems.RUBY)
                        .input('b', ModItems.RUBY_UPGRADE_SMITHING_TEMPLATE)
                        .input('c', Blocks.STONE)
                        .criterion(hasItem(ModItems.RUBY), conditionsFromItem(ModItems.RUBY))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(getRecipeName(ModItems.RUBY_UPGRADE_SMITHING_TEMPLATE) + "_duplication")));

                createShaped(RecipeCategory.MISC, ModItems.RUBY_UPGRADE_SMITHING_TEMPLATE, 1)
                        .pattern("aba")
                        .pattern("aca")
                        .pattern("aaa")
                        .input('a', Blocks.STONE)
                        .input('b', ModItems.RUBY_UPGRADE_SMITHING_TEMPLATE)
                        .input('c', ModItems.RUBY)
                        .criterion(hasItem(ModItems.RUBY), conditionsFromItem(ModItems.RUBY))
                        .criterion(hasItem(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE), conditionsFromItem(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(getRecipeName(ModItems.RUBY_UPGRADE_SMITHING_TEMPLATE))));

                createShaped(RecipeCategory.COMBAT, ModItems.SAPPHIRE_SPEAR, 1)
                        .pattern("bbb")
                        .pattern("bab")
                        .pattern("bbb")
                        .input('a', ModItems.RUBY_SPEAR)
                        .input('b', ModItems.SAPPHIRE)
                        .criterion(hasItem(ModItems.RUBY_SPEAR), conditionsFromItem(ModItems.RUBY_SPEAR))
                        .criterion(hasItem(ModItems.SAPPHIRE), conditionsFromItem(ModItems.SAPPHIRE))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(getRecipeName(ModItems.SAPPHIRE_SPEAR))));

                createShaped(RecipeCategory.COMBAT, ModItems.SAPPHIRE_NAUTILUS_ARMOR, 1)
                        .pattern("bbb")
                        .pattern("bab")
                        .pattern("bbb")
                        .input('a', ModItems.RUBY_NAUTILUS_ARMOR)
                        .input('b', ModItems.SAPPHIRE)
                        .criterion(hasItem(ModItems.RUBY_NAUTILUS_ARMOR), conditionsFromItem(ModItems.RUBY_NAUTILUS_ARMOR))
                        .criterion(hasItem(ModItems.SAPPHIRE), conditionsFromItem(ModItems.SAPPHIRE))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(getRecipeName(ModItems.SAPPHIRE_NAUTILUS_ARMOR))));
            }
        };
    }

    public GemPolishingRecipeJsonBuilder createGemPurifying(Ingredient input, ItemStack result) {
        return GemPolishingRecipeJsonBuilder.create(input, result, RecipeCategory.MISC);
    }

    public GemInfusionRecipeJsonBuilder createGemInfusion(Ingredient inputBefore, ItemStack result) {
        return GemInfusionRecipeJsonBuilder.create(inputBefore, result, RecipeCategory.MISC);
    }

    public SmithingTransformRecipeJsonBuilder createRubySet(RegistryWrapper.WrapperLookup registry, Item baseItem, RecipeCategory category, Item result) {
        return SmithingTransformRecipeJsonBuilder.create(
                Ingredient.ofItem(ModItems.RUBY_UPGRADE_SMITHING_TEMPLATE),
                Ingredient.ofItem(baseItem),
                Ingredient.ofTag(registry.getOrThrow(RegistryKeys.ITEM).getOrThrow(ModItemTags.RUBY_TOOL_MATERIALS)),
                category,
                result
        );
    }

    @Override
    public String getName() {
        return "Mod Recipes Gen for " + MoreOresModInitializer.MOD_ID;
    }
}
