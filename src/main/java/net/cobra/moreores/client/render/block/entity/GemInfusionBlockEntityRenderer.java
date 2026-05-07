package net.cobra.moreores.client.render.block.entity;

import net.cobra.moreores.block.GemPurifierBlock;
import net.cobra.moreores.block.entity.gem.GemIninfusionBlockEntity;
import net.minecraft.client.item.ItemModelManager;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.command.ModelCommandRenderer;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.item.ItemRenderState;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemDisplayContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public final class GemIninfusionBlockEntityRenderer implements BlockEntityRenderer<GemIninfusionBlockEntity, GemIninfusionBlockEntityRenderState> {
    private final BlockEntityRendererFactory.Context context;
    private final ItemModelManager itemModelManager;

    public GemIninfusionBlockEntityRenderer(BlockEntityRendererFactory.Context context) {
        this.context = context;
        this.itemModelManager = context.itemModelManager();
    }

    private void renderEnergyTray(ItemRenderState state, MatrixStack matrices,
                                  OrderedRenderCommandQueue queue,
                                  float x, float z, float rotationAngle, int light) {
        matrices.push();

        matrices.translate(0.5, 0, 0.5);
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(rotationAngle));
        matrices.translate(-0.5, 0, -0.5);

        matrices.translate(x, 0.85F, z);
        matrices.scale(0.125f, 0.125f, 0.125f);
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-270));
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(270));

        state.render(matrices, queue, light, OverlayTexture.DEFAULT_UV, 0);
        matrices.pop();
    }

    // X-axis
    private void renderInputTray(ItemRenderState state, MatrixStack matrices,
                            OrderedRenderCommandQueue queue,
                            float x, float z, float rotationAngle, int light) {
        matrices.push();

        matrices.translate(0.5, 0, 0.5);
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(rotationAngle));
        matrices.translate(-0.5, 0, -0.5);

        matrices.translate(x, 0.9F, z);
        matrices.scale(0.15f, 0.15f, 0.15f);
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-270));
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(270));

        state.render(matrices, queue, light, OverlayTexture.DEFAULT_UV, 0);
        matrices.pop();
    }

    private void renderOutputTray(ItemRenderState state, MatrixStack matrices,
                            OrderedRenderCommandQueue queue,
                            float x, float z, float rotationAngle, int light) {
        matrices.push();

        matrices.translate(0.5, 0, 0.5);
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(rotationAngle));
        matrices.translate(-0.5, 0, -0.5);

        matrices.translate(x, 0.9F, z);
        matrices.scale(0.25f, 0.25f, 0.25f);
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-270));
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(270));

        state.render(matrices, queue, light, OverlayTexture.DEFAULT_UV, 0);
        matrices.pop();
    }

    private float getRotationAngle(GemIninfusionBlockEntity entity) {
        if (entity.getWorld() != null) {
            return switch (entity.getCachedState().get(GemPurifierBlock.FACING)) {
                case NORTH -> 180f;
                case EAST -> 90f;
                case WEST -> -90f;
                default -> 0f;
            };
        }
        return 0f;
    }

    @Override
    public void updateRenderState(GemIninfusionBlockEntity blockEntity, GemIninfusionBlockEntityRenderState state, float tickProgress, Vec3d cameraPos, @Nullable ModelCommandRenderer.CrumblingOverlayCommand crumblingOverlay) {
        BlockEntityRenderer.super.updateRenderState(blockEntity, state, tickProgress, cameraPos, crumblingOverlay);
        state.setEntity(blockEntity);
        state.entityWorld = blockEntity.getWorld();
        state.lightPos = blockEntity.getPos();

        itemModelManager.clearAndUpdate(state.inputItemRenderState, blockEntity.getStack(GemIninfusionBlockEntity.INGREDIENT_BEFORE_SLOT),
                ItemDisplayContext.FIXED, blockEntity.getWorld(), null, 0);
        itemModelManager.clearAndUpdate(state.inputAfterItemRenderState, blockEntity.getStack(GemIninfusionBlockEntity.INGREDIENT_AFTER_SLOT),
                ItemDisplayContext.FIXED, blockEntity.getWorld(), null, 0);
        itemModelManager.clearAndUpdate(state.energyItemRenderState, blockEntity.getStack(GemIninfusionBlockEntity.ENERGY_SOURCE_SLOT),
                ItemDisplayContext.FIXED, blockEntity.getWorld(), null, 0);
        itemModelManager.clearAndUpdate(state.resultItemRenderState, blockEntity.getStack(GemIninfusionBlockEntity.RESULT_SLOT),
                ItemDisplayContext.FIXED, blockEntity.getWorld(), null, 0);
    }

    private int getLightLevel(World world, BlockPos pos) {
        int bLight = world.getLightLevel(LightType.BLOCK, pos);
        int sLight = world.getLightLevel(LightType.SKY, pos);
        return LightmapTextureManager.pack(bLight, sLight);
    }

    @Override
    public GemIninfusionBlockEntityRenderState createRenderState() {
        return new GemIninfusionBlockEntityRenderState();
    }

    @Override
    public void render(GemIninfusionBlockEntityRenderState state, MatrixStack matrices, OrderedRenderCommandQueue queue, CameraRenderState cameraState) {
        GemIninfusionBlockEntity entity = state.entity;
        if (entity == null || entity.getWorld() == null) return;

        int light = getLightLevel(state.entityWorld, state.lightPos);
        float rotationAngles = getRotationAngle(entity);

        renderEnergyTray(state.energyItemRenderState, matrices, queue, 0.5f, 0.25f, rotationAngles, light);
        renderInputTray(state.inputItemRenderState, matrices, queue, 0.775f, 0.21f, rotationAngles, light);
        renderInputTray(state.inputAfterItemRenderState, matrices, queue, 0.225f, 0.21f, rotationAngles, light);
        renderOutputTray(state.resultItemRenderState, matrices, queue, 0.51f, 0.675f, rotationAngles, light);
    }

    public BlockEntityRendererFactory.Context context() {
        return context;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (GemIninfusionBlockEntityRenderer) obj;
        return Objects.equals(this.context, that.context);
    }

    @Override
    public int hashCode() {
        return Objects.hash(context);
    }

    @Override
    public String toString() {
        return "GeminfusionBlockEntityRenderer[" +
                "context=" + context + ']';
    }

}
