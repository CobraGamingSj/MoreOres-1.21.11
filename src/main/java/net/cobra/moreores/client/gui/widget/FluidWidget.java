package net.cobra.moreores.client.gui.widget;

import net.cobra.moreores.client.gui.util.ScreenUtils;
import net.cobra.moreores.util.FluidStack;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandler;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantStorage;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.texture.Sprite;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class FluidWidget implements Drawable, Widget {
    private final SingleVariantStorage<FluidVariant> fluidStorage;
    private final Supplier<BlockPos> pos;
    private final int width, height;

    private int x, y;

    public FluidWidget(SingleVariantStorage<FluidVariant> fluidStorage, int x, int y, int width, int height, Supplier<BlockPos> pos) {
        this.fluidStorage = fluidStorage;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.pos = pos;
    }

    public static Builder builder(SingleVariantStorage<FluidVariant> fluidStorage) {
        return new Builder(fluidStorage);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float deltaTicks) {
        long amount = this.fluidStorage.amount;
        if(amount <= 0) return;
        Fluid fluid = this.fluidStorage.variant.getFluid();
        long capacity = this.fluidStorage.getCapacity();
        int fluidHeight = Math.round(((float)amount / capacity) * this.height);
        FluidRenderHandler handler = FluidRenderHandlerRegistry.INSTANCE.get(fluid);
        if(handler == null) return;
        BlockPos blockPos = pos.get();
        FluidState fluidState = fluid.getDefaultState();
        BlockRenderView world = MinecraftClient.getInstance().world;
        if(world == null) return;
        Sprite sprite = handler.getFluidSprites(world, blockPos, fluidState)[1];
        int tintColor = handler.getFluidColor(world, blockPos, fluidState);
        float red = (tintColor >> 16 & 0xFF) /255F;
        float green = (tintColor >> 8 & 0xFF) /255F;
        float blue = (tintColor & 0xFF) /255F;
        ScreenUtils.renderTiledSprite(context, sprite, this.x, this.y + this.height - fluidHeight, this.width, fluidHeight, 1F, red, green, blue);

        if(isPointWithinBounds(this.x, this.y, this.width, this.height, mouseX, mouseY)) {
            drawTooltip(context, mouseX, mouseY);
        }
    }

    protected void drawTooltip(DrawContext context, int mouseX, int  mouseY) {
        Fluid fluid = this.fluidStorage.variant.getFluid();
        long fluidAmount = this.fluidStorage.getAmount();
        long fluidCapacity = this.fluidStorage.getCapacity();

        TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;
        if(fluid != null && fluidAmount > 0) {
            List<Text> texts = List.of(
                    Text.translatable(fluid.getDefaultState().getBlockState().getBlock().getTranslationKey()),
                    Text.literal("%s / %s mB".formatted(FluidStack.convertDropletsToMb(fluidAmount), FluidStack.convertDropletsToMb(fluidCapacity))).formatted(Formatting.BLUE)
            );
            context.drawTooltip(textRenderer, texts, mouseX, mouseY);
        }
    }

    private static boolean isPointWithinBounds(int x, int y, int width, int height, int pointX, int pointY) {
        return pointX >= x && pointX <= x + width &&
                pointY >= y && pointY <= y + height;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public void forEachChild(Consumer<ClickableWidget> consumer) {}

    public static class Builder {
        private final SingleVariantStorage<FluidVariant> fluidStorage;
        private Supplier<BlockPos> posSupplier = () -> null;
        private int x, y;
        private int width, height;

        public Builder(SingleVariantStorage<FluidVariant> fluidStorage) {
            this.fluidStorage = fluidStorage;
        }

        public Builder x(int x) {
            this.x = x;
            return this;
        }

        public Builder y(int y) {
            this.y = y;
            return this;
        }

        public Builder position(int x, int y) {
            this.x = x;
            this.y = y;
            return this;
        }

        public Builder width(int width) {
            this.width = width;
            return this;
        }

        public Builder height(int height) {
            this.height = height;
            return this;
        }

        public Builder size(int width, int height) {
            this.width = width;
            this.height = height;
            return this;
        }

        public Builder bounds(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            return this;
        }

        public Builder posSupplier(Supplier<BlockPos> posSupplier) {
            this.posSupplier = posSupplier;
            return this;
        }

        public FluidWidget build() {
            return new FluidWidget(this.fluidStorage, this.x, this.y, this.width, this.height, this.posSupplier);
        }
    }
}