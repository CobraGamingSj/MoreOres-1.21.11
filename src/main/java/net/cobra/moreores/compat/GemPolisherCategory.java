package net.cobra.moreores.compat;//package net.cobra.moreores.compat;
//
//import me.shedaniel.math.Point;
//import me.shedaniel.math.Rectangle;
//import me.shedaniel.rei.api.client.gui.Renderer;
//import me.shedaniel.rei.api.client.gui.widgets.Widget;
//import me.shedaniel.rei.api.client.gui.widgets.Widgets;
//import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
//import me.shedaniel.rei.api.common.category.CategoryIdentifier;
//import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
//import me.shedaniel.rei.api.common.util.EntryStacks;
//import net.cobra.moreores.MoreOresModInitializer;
//import net.cobra.moreores.block.ModBlocks;
//import net.minecraft.text.Text;
//import net.minecraft.util.Identifier;
//import org.apache.logging.log4j.core.pattern.TextRenderer;
//
//import java.util.LinkedList;
//import java.util.List;
//
//public class GemPurifierCategory implements DisplayCategory<BasicDisplay> {
//    private static final Identifier TEXTURE = Identifier.of(MoreOresModInitializer.MOD_ID, "textures/gui/container/gem_purifier/gem_purifier_gui.png");
//    public static final CategoryIdentifier<GemPurifierDisplay> GEM_POLISHING = CategoryIdentifier.of(MoreOresModInitializer.MOD_ID, "gem_polishing");
//
//    @Override
//    public CategoryIdentifier<? extends BasicDisplay> getCategoryIdentifier() {
//        return GEM_POLISHING;
//    }
//
//    @Override
//    public Text getTitle() {
//        return Text.translatable("block.moreores.gem_purifier_block");
//    }
//
//    @Override
//    public Renderer getIcon() {
//        return EntryStacks.of(ModBlocks.gem_purifier_BLOCK.asItem().getDefaultStack());
//    }
//
//    @Override
//    public List<Widget> setupDisplay(BasicDisplay display, Rectangle bounds) {
//        final Point startPoint = new Point(bounds.getCenterX() - 87, bounds.getCenterY() - 35);
//        List<Widget> widget = new LinkedList<>();
//        widget.add(Widgets.createTexturedWidget(TEXTURE, new Rectangle(startPoint.x, startPoint.y, 175, 82)));
//
//        widget.add(Widgets.createSlot(new Point(startPoint.x + 83, startPoint.y + 13)).entries(display.getInputEntries().get(0)));
//        widget.add(Widgets.createSlot(new Point(startPoint.x + 83, startPoint.y + 61)).entries(display.getInputEntries().get(0)));
//        widget.add(Widgets.createSlot(new Point(startPoint.x + 39, startPoint.y + 36)).markOutput().entries(display.getOutputEntries().get(0)));
//
//        return widget;
//    }
//
//    @Override
//    public int getDisplayHeight() {
//        return 90;
//    }
//}
