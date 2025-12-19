package net.cobra.moreores.screen;

import net.cobra.moreores.MoreOresModInitializer;
import net.cobra.moreores.block.data.GemPurifierButtonClick;
import net.cobra.moreores.block.data.PolishingStateData;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.input.KeyInput;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class GemPurifierScreen extends HandledScreen<GemPurifierScreenHandler> {
    private static final int TEXTURE_WIDTH = 256;
    private static final int TEXTURE_HEIGHT = 256;
    private static final Identifier TEXTURE = MoreOresModInitializer.getId("textures/gui/container/gem_purifier/gem_purifier_gui.png");
    private ButtonWidget start;
    private ButtonWidget pause;
    private ButtonWidget resume;
    private ButtonWidget stop;

    public GemPurifierScreen(GemPurifierScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.backgroundHeight = 196;
    }

    @Override
    public void init() {
        super.init();
        titleY = 1000;
        playerInventoryTitleY = 1000;

        start = this.addButton("gui.button.gp.start", button -> buttonClicked(0), this.x + 9, y + 92, "Start Polishing");

        pause = this.addButton("gui.button.gp.pause", button -> buttonClicked(1), x + 50, y + 92, "Pause Polishing");

        resume = this.addButton("gui.button.gp.resume", button -> buttonClicked(2), this.x + 93, this.y + 92, "Resume Polishing");

        stop = this.addButton("gui.button.gp.stop", button -> buttonClicked(3), x + 135, y + 92, "Stop Polishing");

        this.start.visible = true;
        this.pause.visible = true;
        this.resume.visible = true;
        this.stop.visible = true;
    }

    private void buttonClicked(int buttonId) {
        ClientPlayNetworking.send(new GemPurifierButtonClick(buttonId, handler.blockEntity.getPos()));
    }

    private ButtonWidget addButton(String translation, ButtonWidget.PressAction pressAction, int x, int y, String tooltip) {
        return this.addDrawableChild(ButtonWidget.builder(Text.translatable(translation), pressAction).dimensions(x, y, 32, 20).tooltip(Tooltip.of(Text.literal(tooltip))).build());
    }

    @Override
    public boolean keyPressed(KeyInput input) {
        if(input.getKeycode() == GLFW.GLFW_KEY_S) {
            sendPolishControlPacket("start");
            return true;
        }
        if(input.getKeycode() == GLFW.GLFW_KEY_P) {
            sendPolishControlPacket("pause");
            return true;
        }
        if(input.getKeycode() == GLFW.GLFW_KEY_R) {
            sendPolishControlPacket("resume");
            return true;
        }
        if(input.getKeycode() == GLFW.GLFW_KEY_SLASH) {
            sendPolishControlPacket("stop");
            return true;
        }
        return super.keyPressed(input);
    }

    private void sendPolishControlPacket(String action) {
        ClientPlayNetworking.send(new PolishingStateData(handler.blockEntity.getPos(), action));
    }

    private void renderProgressArrow(DrawContext context, int x, int y) {
        if(this.handler.isPolishing()) {
            context.drawTexture(RenderPipelines.GUI_TEXTURED, TEXTURE, x + 87, y + 31, 176, 0, 8, this.handler.progressGetter(), TEXTURE_WIDTH, TEXTURE_HEIGHT);
        }
    }

    private void renderEnergyStorageHandler(DrawContext context, int x, int y) {
        int energyBarSize = MathHelper.ceil(this.handler.getEnergyPercent() * 66);
        int gradientStart = 0XFF0000FF;
        int gradientEnd = 0XFF800080;
        context.fillGradient(x + 10, y + 20 + 66 - energyBarSize, x + 10 + 20, y + 20 + 66, gradientStart, gradientEnd);
    }

    @Override
    public void drawForeground(DrawContext context, int mouseX, int mouseY) {
        String name = this.handler.blockEntity.getDisplayName().getString();
        int x = 8;
        int y = 8;
        context.drawText(this.textRenderer, name, x, y, 5390966, false);
        super.drawForeground(context, mouseX, mouseY);
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
        int energyBarSize = MathHelper.ceil(this.handler.getEnergyPercent() * 66);
        if (isPointWithinBounds(10, 20 + 66 - energyBarSize, 25, energyBarSize, mouseX, mouseY)) {
            context.drawTooltip(this.textRenderer, Text.literal(this.handler.getEnergy() + " / " + this.handler.getMaxEnergy() + " J").formatted(Formatting.DARK_AQUA), mouseX, mouseY);
        }
    }
}