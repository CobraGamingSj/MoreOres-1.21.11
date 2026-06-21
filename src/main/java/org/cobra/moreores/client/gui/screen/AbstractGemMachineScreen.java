package org.cobra.moreores.client.gui.screen;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.input.KeyInput;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.cobra.moreores.client.gui.widget.TextureButtonWidget;
import org.cobra.moreores.networking.block.data.PolishingStateDataPayload;
import org.lwjgl.glfw.GLFW;

public abstract class AbstractGemMachineScreen<S extends AbstractGemMachineScreenHandler> extends HandledScreen<S> {
    private static final int TEXTURE_WIDTH = 256;
    private static final int TEXTURE_HEIGHT = 256;

    public AbstractGemMachineScreen(S handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.backgroundHeight = 196;
        this.backgroundWidth = 207;
    }

    @Override
    protected void init() {
        super.init();
        titleY = 1000;
        playerInventoryTitleY = 1000;

        ButtonWidget start = this.addButton("gui.button.gp.start", 0, this.x + 112, y + 8, getStartButtonTexture(), Text.literal("Start Polishing"));

        ButtonWidget pause = this.addButton("gui.button.gp.pause", 1, x + 160, y + 8, getPauseButtonTexture(), Text.literal("Pause Polishing"));

        ButtonWidget resume = this.addButton("gui.button.gp.resume", 2, this.x + 112, this.y + 56, getResumeButtonTexture(), Text.literal("Resume Polishing"));

        ButtonWidget stop = this.addButton("gui.button.gp.stop", 3, x + 160, y + 56, getStopButtonTexture(), Text.literal("Stop Polishing"));

        start.visible = true;
        pause.visible = true;
        resume.visible = true;
        stop.visible = true;
    }

    protected ButtonWidget addButton(String translation, int buttonId, int x, int y, Identifier texture, Text tooltip) {
        ButtonWidget button = new TextureButtonWidget(x, y, Text.translatable(translation), texture, buttonId, handler.getPos());
        button.setTooltip(Tooltip.of(tooltip));
        return this.addDrawableChild(button);
    }

    protected abstract Identifier getBackgroundTexture();
    protected abstract Identifier getStartButtonTexture();
    protected abstract Identifier getPauseButtonTexture();
    protected abstract Identifier getResumeButtonTexture();
    protected abstract Identifier getStopButtonTexture();

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
        ClientPlayNetworking.send(new PolishingStateDataPayload(handler.getPos(), action));
    }

    protected abstract void renderEnergyHandler(DrawContext context, int x, int y);
    protected abstract void renderProgressArrow(DrawContext context, int x, int y);

    @Override
    protected void drawBackground(DrawContext context, float deltaTicks, int mouseX, int mouseY) {
        int i = this.x;
        int j = this.y;

        context.drawTexture(RenderPipelines.GUI_TEXTURED, getBackgroundTexture(), i, j, 0f, 0f, this.backgroundWidth, this.backgroundHeight, TEXTURE_WIDTH, TEXTURE_HEIGHT);
        renderEnergyHandler(context, i, j);
        renderProgressArrow(context, i, j);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }
}
