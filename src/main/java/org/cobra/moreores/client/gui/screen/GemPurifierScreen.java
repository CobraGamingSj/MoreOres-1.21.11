package org.cobra.moreores.client.gui.screen;

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
import org.cobra.moreores.MoreOresModInitializer;
import org.cobra.moreores.client.gui.widget.FluidWidget;
import org.cobra.moreores.client.gui.widget.TextureButtonWidget;

@Environment(EnvType.CLIENT)
public class GemPurifierScreen extends AbstractGemMachineScreen<GemPurifierScreenHandler> {
    private static final int TEXTURE_WIDTH = 256;
    private static final int TEXTURE_HEIGHT = 256;
    private static final Identifier TEXTURE = MoreOresModInitializer.id("textures/gui/container/gem_purifier/gem_purifier_gui_test.png");
    private static final Identifier START_BUTTON = MoreOresModInitializer.id("textures/gui/container/button/start.png");
    private static final Identifier PAUSE_BUTTON = MoreOresModInitializer.id("textures/gui/container/button/pause.png");
    private static final Identifier RESUME_BUTTON = MoreOresModInitializer.id("textures/gui/container/button/resume.png");
    private static final Identifier STOP_BUTTON = MoreOresModInitializer.id("textures/gui/container/button/stop.png");

    public GemPurifierScreen(GemPurifierScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.backgroundHeight = 201;
        this.backgroundWidth = 226;
    }

    @Override
    public void init() {
        super.init();

        addDrawable(FluidWidget.builder(handler.blockEntity.fluidStorage).bounds(this.x + 10, this.y + 42, 20, 44).posSupplier(handler.blockEntity::getPos).create());
    }
    
    @Override
    protected Identifier getBackgroundTexture() {
        return TEXTURE;
    }

    @Override
    protected Identifier getStartButtonTexture() {
        return START_BUTTON;
    }

    @Override
    protected Identifier getPauseButtonTexture() {
        return PAUSE_BUTTON;
    }

    @Override
    protected Identifier getResumeButtonTexture() {
        return RESUME_BUTTON;
    }

    @Override
    protected Identifier getStopButtonTexture() {
        return STOP_BUTTON;
    }

    @Override
    protected int getStartButtonPosX() {
        return 32;
    }

    @Override
    protected int getStartButtonPosY() {
        return 92;
    }

    @Override
    protected int getPauseButtonPosX() {
        return 80;
    }

    @Override
    protected int getPauseButtonPosY() {
        return 92;
    }

    @Override
    protected int getResumeButtonPosX() {
        return 32;
    }

    @Override
    protected int getResumeButtonPosY() {
        return 140;
    }

    @Override
    protected int getStopButtonPosX() {
        return 80;
    }

    @Override
    protected int getStopButtonPosY() {
        return 140;
    }
    
    @Override
    protected void renderProgressArrow(DrawContext context, int x, int y) {
        if(this.handler.isPolishing()) {
            context.drawTexture(RenderPipelines.GUI_TEXTURED, TEXTURE, x + 83, y + 31, 207, 0, 10, this.handler.progressGetter(), TEXTURE_WIDTH, TEXTURE_HEIGHT);
        }
    }

    @Override
    protected void renderRedstoneDust(DrawContext context, int x, int y) {
        int k = handler.getRedstoneDust();
        int l = MathHelper.clamp((k * 16 + 10000 - 1) / 10000, 0, 16);

        int startX = x + 109;
        int startY = y + 53;
        int endY = y + 57;

        context.fillGradient(startX, startY, startX + l, endY, Colors.RED, Colors.LIGHT_RED);
    }

    @Override
    protected void renderEnergyHandler(DrawContext context, int x, int y) {
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
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
        int energyBarSize = MathHelper.ceil(this.handler.getEnergyPercent() * 44);
        int l = MathHelper.clamp((handler.getRedstoneDust() * 16 + 10000 - 1) / 10000, 0, 16);
        if (isPointWithinBounds(40, 42 + 44 - energyBarSize, 16, energyBarSize, mouseX, mouseY)) {
            context.drawTooltip(this.textRenderer, Text.literal(this.handler.getEnergy() + " / " + this.handler.getEnergyCap() + " J").formatted(Formatting.DARK_AQUA, Formatting.BOLD), mouseX, mouseY);
        }
        if (isPointWithinBounds(109, 53, l, 4, mouseX, mouseY)) {
            context.drawTooltip(this.textRenderer, Text.literal(this.handler.getRedstoneDust() + " Particles").formatted(Formatting.RED), mouseX, mouseY);
        }
    }
}