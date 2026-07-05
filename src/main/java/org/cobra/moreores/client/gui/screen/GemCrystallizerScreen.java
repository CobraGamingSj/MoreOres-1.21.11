package org.cobra.moreores.client.gui.screen;

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
import org.cobra.moreores.MoreOresModInitializer;

@Environment(EnvType.CLIENT)
public class GemCrystallizerScreen extends AbstractGemMachineScreen<GemCrystallizerScreenHandler> {
    private static final int TEXTURE_WIDTH = 256;
    private static final int TEXTURE_HEIGHT = 256;
    private static final Identifier TEXTURE = MoreOresModInitializer.id("textures/gui/container/gem_crystallizer/gem_crystallizer_gui.png");
    private static final Identifier START_BUTTON = MoreOresModInitializer.id("textures/gui/container/button/start.png");
    private static final Identifier PAUSE_BUTTON = MoreOresModInitializer.id("textures/gui/container/button/pause.png");
    private static final Identifier RESUME_BUTTON = MoreOresModInitializer.id("textures/gui/container/button/resume.png");
    private static final Identifier STOP_BUTTON = MoreOresModInitializer.id("textures/gui/container/button/stop.png");

    public GemCrystallizerScreen(GemCrystallizerScreenHandler handler, PlayerInventory inventory, Text title) {
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
    protected int getStartButtonPosX() {
        return 112;
    }

    @Override
    protected int getStartButtonPosY() {
        return 8;
    }

    @Override
    protected int getPauseButtonPosX() {
        return 160;
    }

    @Override
    protected int getPauseButtonPosY() {
        return 8;
    }

    @Override
    protected int getResumeButtonPosX() {
        return 112;
    }

    @Override
    protected int getResumeButtonPosY() {
        return 56;
    }

    @Override
    protected int getStopButtonPosX() {
        return 160;
    }

    @Override
    protected int getStopButtonPosY() {
        return 56;
    }
    
    @Override
    protected void renderProgressArrow(DrawContext context, int x, int y) {
        if(this.handler.isPolishing()) {
            context.drawTexture(RenderPipelines.GUI_TEXTURED, TEXTURE, x + 70, y + 41, 207, 0, 11, this.handler.progressGetter(), TEXTURE_WIDTH, TEXTURE_HEIGHT);
        }
    }

    @Override
    protected void renderRedstoneDust(DrawContext context, int x, int y) {
        int k = handler.getRedstoneDust();
        int l = MathHelper.clamp((k * 16 + 10000 - 1) / 10000, 0, 16);

        int startX = x + 92;
        int startY = y + 79;
        int endY = y + 83;

        context.fillGradient(startX, startY, startX + l, endY, Colors.RED, -7667712);
    }

    @Override
    protected void drawBackground(DrawContext context, float deltaTicks, int mouseX, int mouseY) {
        super.drawBackground(context, deltaTicks, mouseX, mouseY);
        renderRadiantDust(context, this.x, this.y);
    }

    private void renderRadiantDust(DrawContext context, int x, int y) {
        int k = handler.getDustCount();
        int l = MathHelper.clamp((18 * k + 10000 - 1) / 10000, 0, 18);
        if(l > 0) {
            context.drawTexture(RenderPipelines.GUI_TEXTURED, TEXTURE, x + 38, y + 97, 207, 29, l, 4, TEXTURE_WIDTH, TEXTURE_HEIGHT);
        }
    }

    @Override
    protected void renderEnergyHandler(DrawContext context, int x, int y) {
        int energyBarSize = MathHelper.ceil(this.handler.getEnergyPercent() * 44);

        int startY = y + 43 + 44 - energyBarSize;
        int endY = y + 43 + 44;

        int barX1 = x + 13;
        int barX2 = barX1 + 16;

        context.fillGradient(barX1, startY, barX2, endY, Colors.PURPLE, Colors.RED);
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
        int k = MathHelper.clamp((18 * handler.getDustCount() + 10000 - 1) / 10000, 0, 18);
        int l = MathHelper.clamp((handler.getRedstoneDust() * 16 + 10000 - 1) / 10000, 0, 16);
        if (isPointWithinBounds(13, 43 + 44 - energyBarSize, 16, energyBarSize, mouseX, mouseY)) {
            context.drawTooltip(this.textRenderer, Text.literal(this.handler.getEnergy() + " / " + this.handler.getEnergyCap() + " J").formatted(Formatting.DARK_AQUA, Formatting.BOLD), mouseX, mouseY);
        }
        if (isPointWithinBounds(38, 97, k, 4, mouseX, mouseY)) {
            context.drawTooltip(this.textRenderer, Text.literal(this.handler.getDustCount() + " Particles").formatted(Formatting.RED),  mouseX, mouseY);
        }
        if (isPointWithinBounds(92, 79, l, 4, mouseX, mouseY)) {
            context.drawTooltip(this.textRenderer, Text.literal(this.handler.getRedstoneDust() + " Particles").formatted(Formatting.RED), mouseX, mouseY);
        }
    }
}