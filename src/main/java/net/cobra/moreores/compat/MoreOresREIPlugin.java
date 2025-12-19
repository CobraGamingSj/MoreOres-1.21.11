package net.cobra.moreores.compat;//package net.cobra.moreores.compat;
//
//import me.shedaniel.math.Rectangle;
//import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
//import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
//import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
//import me.shedaniel.rei.api.client.registry.screen.ScreenRegistry;
//import me.shedaniel.rei.api.common.util.EntryStacks;
//import net.cobra.moreores.block.ModBlocks;
//import net.cobra.moreores.recipe.GemPurifierRecipe;
//import net.cobra.moreores.screen.GemPurifierScreen;
//
//public class MoreOresREIPlugin implements REIClientPlugin {
//
//    @Override
//    public void registerCategories(CategoryRegistry registry) {
//        registry.add(new GemPurifierCategory());
//
//        registry.addWorkstations(GemPurifierCategory.GEM_POLISHING, EntryStacks.of(ModBlocks.gem_purifier_BLOCK));
//    }
//
//    @Override
//    public void registerDisplays(DisplayRegistry registry) {
//        registry.beginRecipeFiller(GemPurifierRecipe.class, GemPurifierRecipe.Type.GEM_POLISHING,
//                GemPurifierDisplay::new);
//    }
//
//    @Override
//    public void registerScreens(ScreenRegistry registry) {
//        registry.registerClickArea(screen -> new Rectangle(75, 30, 20, 30), GemPurifierScreen.class, GemPurifierCategory.GEM_POLISHING);
//    }
//}
