package net.cobra.moreores.client.gui.screen;

import net.cobra.moreores.MoreOresModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

@Environment(EnvType.CLIENT)
public class GemInfusionScreen extends AbstractGemPFScreen<GemInfusionScreenHandler> {
    private static final int TEXTURE_WIDTH = 256;
    private static final int TEXTURE_HEIGHT = 256;
    private static final Identifier TEXTURE = MoreOresModInitializer.getId("textures/gui/container/gem_infusion/gem_infusion_gui.png");
    private static final Identifier START_BUTTON = MoreOresModInitializer.getId("textures/gui/container/button/start.png");
    private static final Identifier PAUSE_BUTTON = MoreOresModInitializer.getId("textures/gui/container/button/pause.png");
    private static final Identifier RESUME_BUTTON = MoreOresModInitializer.getId("textures/gui/container/button/resume.png");
    private static final Identifier STOP_BUTTON = MoreOresModInitializer.getId("textures/gui/container/button/stop.png");

    public GemInfusionScreen(GemInfusionScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.backgroundHeight = 196;
        this.backgroundWidth = 207;
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
    protected void renderProgressArrow(DrawContext context, int x, int y) {
        if(this.handler.isPolishing()) {
            context.drawTexture(RenderPipelines.GUI_TEXTURED, TEXTURE, x + 70, y + 41, 207, 0, 11, this.handler.progressGetter(), TEXTURE_WIDTH, TEXTURE_HEIGHT);
        }
    }

    @Override
    protected void drawBackground(DrawContext context, float deltaTicks, int mouseX, int mouseY) {
        super.drawBackground(context, deltaTicks, mouseX, mouseY);
        renderRadiantDust(context, this.x, this.y);
    }

    private void renderRadiantDust(DrawContext context, int x, int y) {
        int k = handler.getDustCount();
        int l = MathHelper.clamp((18 * k + 2000 - 1) / 2000, 0, 18);
        if(l > 0) {
            context.drawTexture(RenderPipelines.GUI_TEXTURED, TEXTURE, x + 38, y + 97, 207, 29, l, 4, TEXTURE_WIDTH, TEXTURE_HEIGHT);
        }
    }

    @Override
    protected void renderEnergyHandler(DrawContext context, int x, int y) {
        int energyBarSize = MathHelper.ceil(this.handler.getEnergyPercent() * 44);
        int gradientStart = Colors.BLUE;
        int gradientEnd = Colors.GREEN;
        context.fillGradient(x + 13, y + 43 + 44 - energyBarSize, x + 13 + 16, y + 43 + 44, gradientStart, gradientEnd);
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
        super.render(context, mouseX, mouseY, delta);
        int energyBarSize = MathHelper.ceil(this.handler.getEnergyPercent() * 44);
        int k = MathHelper.clamp((18 * handler.getDustCount() + 2000 - 1) / 2000, 0, 18);
        if (isPointWithinBounds(13, 43 + 44 - energyBarSize, 16, energyBarSize, mouseX, mouseY)) {
            context.drawTooltip(this.textRenderer, Text.literal(this.handler.getEnergy() + " / " + this.handler.getEnergyCap() + " J").formatted(Formatting.DARK_AQUA, Formatting.BOLD), mouseX, mouseY);
        }
        if (isPointWithinBounds(38 + 18 - k, 97, k, 4, mouseX, mouseY)) {
            context.drawTooltip(this.textRenderer, Text.literal(this.handler.getDustCount() + " Particles").formatted(Formatting.RED),  mouseX, mouseY);
        }
    }
}