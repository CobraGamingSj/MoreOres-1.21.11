package org.cobra.moreores.client.gui.screen;

import org.cobra.moreores.MoreOresModInitializer;
import org.cobra.moreores.client.gui.widget.FluidWidget;
import org.cobra.moreores.client.gui.widget.TextureButtonWidget;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

@Environment(EnvType.CLIENT)
public class GemPurifierScreen extends HandledScreen<GemPurifierScreenHandler> {
    private static final int TEXTURE_WIDTH = 256;
    private static final int TEXTURE_HEIGHT = 256;
    private static final Identifier TEXTURE = MoreOresModInitializer.id("textures/gui/container/gem_purifier/gem_purifier_gui.png");
    private static final Identifier START_BUTTON = MoreOresModInitializer.id("textures/gui/container/button/start.png");
    private static final Identifier PAUSE_BUTTON = MoreOresModInitializer.id("textures/gui/container/button/pause.png");
    private static final Identifier RESUME_BUTTON = MoreOresModInitializer.id("textures/gui/container/button/resume.png");
    private static final Identifier STOP_BUTTON = MoreOresModInitializer.id("textures/gui/container/button/stop.png");

    public GemPurifierScreen(GemPurifierScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.backgroundHeight = 196;
        this.backgroundWidth = 207;
    }

    @Override
    public void init() {
        super.init();
        titleY = 1000;
        playerInventoryTitleY = 1000;

        addDrawable(FluidWidget.builder(handler.blockEntity.fluidStorage).bounds(this.x + 10, this.y + 42, 20, 44).posSupplier(handler.blockEntity::getPos).build());

        ButtonWidget start = this.addButton("gui.button.gp.start", 0, this.x + 112, y + 8, START_BUTTON, Text.literal("Start Polishing"));

        ButtonWidget pause = this.addButton("gui.button.gp.pause", 1, x + 160, y + 8, PAUSE_BUTTON, Text.literal("Pause Polishing"));

        ButtonWidget resume = this.addButton("gui.button.gp.resume", 2, this.x + 112, this.y + 56, RESUME_BUTTON, Text.literal("Resume Polishing"));

        ButtonWidget stop = this.addButton("gui.button.gp.stop", 3, x + 160, y + 56, STOP_BUTTON, Text.literal("Stop Polishing"));

        start.visible = true;
        pause.visible = true;
        resume.visible = true;
        stop.visible = true;
    }

    private ButtonWidget addButton(String translation, int buttonId, int x, int y, Identifier texture, Text tooltip) {
        ButtonWidget button = new TextureButtonWidget(x, y, Text.translatable(translation), texture, buttonId, handler.blockEntity.getPos());
        button.setTooltip(Tooltip.of(tooltip));
        return this.addDrawableChild(button);
    }

    private void renderProgressArrow(DrawContext context, int x, int y) {
        if(this.handler.isPolishing()) {
            context.drawTexture(RenderPipelines.GUI_TEXTURED, TEXTURE, x + 83, y + 31, 207, 0, 10, this.handler.progressGetter(), TEXTURE_WIDTH, TEXTURE_HEIGHT);
        }
    }

    private void renderEnergyStorageHandler(DrawContext context, int x, int y) {
        int energyBarSize = MathHelper.ceil(this.handler.getEnergyPercent() * 44);
        int gradientStart = Colors.BLUE;
        int gradientEnd = Colors.GREEN;
        context.fillGradient(x + 40, y + 42 + 44 - energyBarSize, x + 40 + 16, y + 42 + 44, gradientStart, gradientEnd);
    }

    @Override
    public void drawForeground(DrawContext context, int mouseX, int mouseY) {
        super.drawForeground(context, mouseX, mouseY);
        String name = this.handler.blockEntity.getDisplayName().getString();
        int x = 8;
        int y = 8;
        context.drawText(this.textRenderer, name, x, y, Colors.BLACK, false);
    }

    @Override
    public void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        int i = this.x;
        int j = this.y;
        context.drawTexture(RenderPipelines.GUI_TEXTURED, TEXTURE, i, j, 0.0F, 0.0F, this.backgroundWidth, this.backgroundHeight, TEXTURE_WIDTH, TEXTURE_HEIGHT);

        renderProgressArrow(context, i, j);
        renderEnergyStorageHandler(context, i, j);

    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
        int energyBarSize = MathHelper.ceil(this.handler.getEnergyPercent() * 44);
        if (isPointWithinBounds(40, 42 + 44 - energyBarSize, 16, energyBarSize, mouseX, mouseY)) {
            context.drawTooltip(this.textRenderer, Text.literal(this.handler.getEnergy() + " / " + this.handler.getEnergyCap() + " J").formatted(Formatting.DARK_AQUA, Formatting.BOLD), mouseX, mouseY);
        }
    }
}