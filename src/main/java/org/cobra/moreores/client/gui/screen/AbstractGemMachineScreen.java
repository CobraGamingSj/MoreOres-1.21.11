package org.cobra.moreores.client.gui.screen;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.input.KeyInput;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
import net.minecraft.util.Identifier;
import org.cobra.moreores.MoreOresModInitializer;
import org.cobra.moreores.block.entity.gem.machine.AbstractGemMachineBlockEntity;
import org.cobra.moreores.client.gui.widget.TextureButtonWidget;
import org.cobra.moreores.networking.block.data.MachineStatusDataPayload;
import org.joml.Matrix3x2fStack;
import org.lwjgl.glfw.GLFW;

public abstract class AbstractGemMachineScreen<T extends AbstractGemMachineBlockEntity<?>, ScreenHandler extends AbstractGemMachineScreenHandler<T>> extends HandledScreen<ScreenHandler> {
    private static final int TEXTURE_WIDTH = 256;
    private static final int TEXTURE_HEIGHT = 256;

    ItemStack previewResultStack = ItemStack.EMPTY;

    protected final Identifier SLOT_HIGHLIGHT_BACK_TEXTURE_ = MoreOresModInitializer.id("container/slot_highlight_back");
    protected final Identifier SLOT_HIGHLIGHT_FRONT_TEXTURE_ = MoreOresModInitializer.id("container/slot_highlight_front");

    private static final Identifier REI_HELP = MoreOresModInitializer.id("container/rei");

    public AbstractGemMachineScreen(ScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.backgroundHeight = 196;
        this.backgroundWidth = 207;
    }

    @Override
    protected void init() {
        super.init();
        titleY = 1000;
        playerInventoryTitleY = 1000;

        ButtonWidget start = this.addButton("gui.button.gp.start", 0, this.x + getStartButtonPosX(), y + getStartButtonPosY(), getStartButtonTexture(), handler instanceof GemPurifierScreenHandler ? Text.literal("Start Purification") : Text.literal("Start Crystallization"));
        ButtonWidget pause = this.addButton("gui.button.gp.pause", 1, x + getPauseButtonPosX(), y + getPauseButtonPosY(), getPauseButtonTexture(), handler instanceof GemPurifierScreenHandler ? Text.literal("Pause Purification") : Text.literal("Pause Crystallization"));
        ButtonWidget resume = this.addButton("gui.button.gp.resume", 2, this.x + getResumeButtonPosX(), this.y + getResumeButtonPosY(), getResumeButtonTexture(), handler instanceof GemPurifierScreenHandler ? Text.literal("Resume Purification") : Text.literal("Resume Crystallization"));
        ButtonWidget stop = this.addButton("gui.button.gp.stop", 3, x + getStopButtonPosX(), y + getStopButtonPosY(), getStopButtonTexture(), handler instanceof GemPurifierScreenHandler ? Text.literal("Stop Purification") : Text.literal("Stop Crystallization"));

        start.visible = true;
        pause.visible = true;
        resume.visible = true;
        stop.visible = true;
    }

    private ButtonWidget addButton(String translation, int buttonIndex, int x, int y, Identifier texture, Text tooltip) {
        ButtonWidget button = new TextureButtonWidget(x, y, Text.translatable(translation), texture, buttonIndex, handler.getBlockPos());
        button.setTooltip(Tooltip.of(tooltip));
        return this.addDrawableChild(button);
    }

    protected abstract Identifier getBackgroundTexture();
    protected abstract Identifier getStartButtonTexture();
    protected abstract Identifier getPauseButtonTexture();
    protected abstract Identifier getResumeButtonTexture();
    protected abstract Identifier getStopButtonTexture();

    protected abstract int getStartButtonPosX();
    protected abstract int getStartButtonPosY();

    protected abstract int getPauseButtonPosX();
    protected abstract int getPauseButtonPosY();

    protected abstract int getResumeButtonPosX();
    protected abstract int getResumeButtonPosY();

    protected abstract int getStopButtonPosX();
    protected abstract int getStopButtonPosY();

    private Slot getOutputSlot() {
        if (this.handler instanceof GemPurifierScreenHandler) {
            return this.handler.getSlot(1);
        }

        if (this.handler instanceof GemCrystallizerScreenHandler) {
            return this.handler.getSlot(2);
        }

        return null;
    }

    @Override
    protected void drawSlotHighlightBack(DrawContext context) {
        if (this.focusedSlot != null && this.focusedSlot.canBeHighlighted() && isOutputSlot(this.focusedSlot)) {
            context.drawGuiTexture(RenderPipelines.GUI_TEXTURED, SLOT_HIGHLIGHT_BACK_TEXTURE_, this.focusedSlot.x - 4, this.focusedSlot.y - 4, 32, 32);
            return;
        }
        super.drawSlotHighlightBack(context);
    }

    @Override
    protected void drawSlotHighlightFront(DrawContext context) {
        if (this.focusedSlot != null && this.focusedSlot.canBeHighlighted() &&  isOutputSlot(this.focusedSlot)) {
            context.drawGuiTexture(RenderPipelines.GUI_TEXTURED, SLOT_HIGHLIGHT_FRONT_TEXTURE_, this.focusedSlot.x - 4, this.focusedSlot.y - 4, 32, 32);
            return;
        }
        super.drawSlotHighlightFront(context);
    }

    @Override
    protected boolean isPointOverSlot(Slot slot, double pointX, double pointY) {
        if(isOutputSlot(slot)) {
            return this.isPointWithinBounds(slot.x, slot.y, 24, 24, pointX, pointY);
        }
        return super.isPointOverSlot(slot, pointX, pointY);
    }

    private boolean isOutputSlot(Slot slot) {
        return (this.handler instanceof GemPurifierScreenHandler && slot.getIndex() == 1) || (this.handler instanceof GemCrystallizerScreenHandler && slot.getIndex() == 2);
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
        ClientPlayNetworking.send(new MachineStatusDataPayload(handler.getBlockPos(), action));
    }

    protected abstract void renderEnergyHandler(DrawContext context, int x, int y);
    protected abstract void renderProgressArrow(DrawContext context, int x, int y);
    protected abstract void renderRedstoneDust(DrawContext context, int x, int y);

    @Override
    protected void drawBackground(DrawContext context, float deltaTicks, int mouseX, int mouseY) {
        int i = this.x;
        int j = this.y;

        context.drawTexture(RenderPipelines.GUI_TEXTURED, getBackgroundTexture(), i, j, 0f, 0f, this.backgroundWidth, this.backgroundHeight, TEXTURE_WIDTH, TEXTURE_HEIGHT);
        renderEnergyHandler(context, i, j);
        renderProgressArrow(context, i, j);
        renderRedstoneDust(context, i, j);

        ItemStack resultStack = this.previewResultStack;
        Slot outputSlot = getOutputSlot();

        if(outputSlot != null && outputSlot.getStack().isEmpty()) {
            int x = this.x + outputSlot.x;
            int y = this.y + outputSlot.y;

            Matrix3x2fStack matrixStack = context.getMatrices();
            matrixStack.pushMatrix();
            matrixStack.translate(x, y);
            matrixStack.scale(1.5f, 1.5f);
            context.drawItemWithoutEntity(resultStack, 0, 0);
            matrixStack.popMatrix();

            if (isPointOverSlot(outputSlot, mouseX, mouseY)) {
                context.drawTooltip(this.textRenderer, Text.literal("Result: " + resultStack.getItemName().getString()), mouseX, mouseY);
            }
        }

        context.drawGuiTexture(RenderPipelines.GUI_TEXTURED, REI_HELP, i - 30, j, 25, 20);
    }

    @Override
    public void drawForeground(DrawContext context, int mouseX, int mouseY) {
        super.drawForeground(context, mouseX, mouseY);
        String name = this.handler.getBlockEntity().getDisplayName().getString();
        int x = 8;
        int y = 8;
        context.drawText(this.textRenderer, name, x, y, Colors.BLACK, false);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }

    public void setPreviewResultStack(ItemStack previewResultStack) {
        this.previewResultStack = previewResultStack;
    }
}
