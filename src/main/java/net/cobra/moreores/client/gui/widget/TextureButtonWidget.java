package net.cobra.moreores.client.gui.widget;

import net.cobra.moreores.networking.block.data.GemPurifierButtonClickPayload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.input.AbstractInput;
import net.minecraft.util.Colors;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class TextureButtonWidget extends ButtonWidget {
    private final Identifier texture;
    private final int buttonId;
    private final BlockPos pos;

    public TextureButtonWidget(int x, int y, net.minecraft.text.Text message, Identifier texture, int buttonId, BlockPos pos) {
        super(x, y, 32, 32, message, btn -> {

        }, DEFAULT_NARRATION_SUPPLIER);
        this.texture = texture;
        this.buttonId = buttonId;
        this.pos = pos;
    }

    @Override
    public void onPress(AbstractInput input) {
        ClientPlayNetworking.send(new GemPurifierButtonClickPayload(buttonId, pos));
    }

    @Override
    protected void drawIcon(DrawContext context, int mouseX, int mouseY, float deltaTicks) {
        context.drawTexture(RenderPipelines.GUI_TEXTURED, texture, getX(), getY(), 0, 0, this.getWidth(), this.getHeight(), 32, 32);
        context.drawStrokedRectangle(getX(), getY(), 32, 32, Colors.DARK_GRAY);
        if(isHovered()) {
            context.drawStrokedRectangle(getX(), getY(), 32, 32, Colors.BLACK);
        }
    }
}
