package net.cobra.moreores.client.render.block.entity;

import net.cobra.moreores.block.GemPurifierBlock;
import net.cobra.moreores.block.entity.GemPurifierBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
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
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public final class GemPurifierBlockEntityRenderer implements BlockEntityRenderer<GemPurifierBlockEntity, GemPurifierBlockEntityRenderState> {
    private final BlockEntityRendererFactory.Context context;
    private final ItemModelManager itemModelManager;

    public GemPurifierBlockEntityRenderer(

            BlockEntityRendererFactory.Context context) {
        this.context = context;
        this.itemModelManager = context.itemModelManager();
    }


    private void renderItem(ItemRenderState state, MatrixStack matrices,
                            OrderedRenderCommandQueue queue, float x, float z, float rotationAngle, int light) {
            matrices.push();

            matrices.translate(0.5, 0, 0.5);
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(rotationAngle));
            matrices.translate(-0.5, 0, -0.5);

            matrices.translate(x, 0.9F, z);
            matrices.scale(0.25f, 0.25f, 0.25f);
            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(270));
            matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(270));

            state.render(matrices, queue, light, OverlayTexture.DEFAULT_UV, 0);
            matrices.pop();
    }

    private float getRotationAngle(GemPurifierBlockEntity entity) {
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
    public void updateRenderState(GemPurifierBlockEntity blockEntity, GemPurifierBlockEntityRenderState state, float tickProgress, Vec3d cameraPos, @Nullable ModelCommandRenderer.CrumblingOverlayCommand crumblingOverlay) {
        BlockEntityRenderer.super.updateRenderState(blockEntity, state, tickProgress, cameraPos, crumblingOverlay);
        state.setEntity(blockEntity);
        state.entityWorld = blockEntity.getWorld();
        state.lightPos = blockEntity.getPos();

        itemModelManager.clearAndUpdate(state.inputItemRenderState, blockEntity.getStack(GemPurifierBlockEntity.INGREDIENT_SLOT),
                ItemDisplayContext.FIXED, blockEntity.getWorld(), null, 0);
        itemModelManager.clearAndUpdate(state.energyItemRenderState, blockEntity.getStack(GemPurifierBlockEntity.ENERGY_SOURCE_SLOT),
                ItemDisplayContext.FIXED, blockEntity.getWorld(), null, 0);
        itemModelManager.clearAndUpdate(state.resultItemRenderState, blockEntity.getStack(GemPurifierBlockEntity.RESULT_SLOT),
                ItemDisplayContext.FIXED, blockEntity.getWorld(), null, 0);
    }

    private int getLightLevel(World world, BlockPos pos) {
        int bLight = world.getLightLevel(LightType.BLOCK, pos);
        int sLight = world.getLightLevel(LightType.SKY, pos);
        return LightmapTextureManager.pack(bLight, sLight);
    }

    private void renderEnergyAmountText(GemPurifierBlockEntityRenderState state, GemPurifierBlockEntity be, MatrixStack matrices, OrderedRenderCommandQueue queue, int light) {
        if (be.getWorld() == null || !be.getWorld().isClient()) return;

        long energy = be.energyStorage.amount;
        Vec3d vec = new Vec3d(0.25, 1.5, 0.25);
        Text text = Text.literal(energy + " ⚡");

        matrices.push();
        matrices.translate(vec.x, vec.y, vec.z);
        matrices.multiply(MinecraftClient.getInstance().gameRenderer.getCamera().getRotation());
        matrices.scale(0.025f, -0.025f, 0.025f);

        int backgroundOpacity = (int) (MinecraftClient.getInstance().options.getTextBackgroundOpacity(0.25F) * 255.0F) << 24;

        queue.submitText(matrices, 2, 0, text.asOrderedText(), false, TextRenderer.TextLayerType.SEE_THROUGH, light, Colors.CYAN, backgroundOpacity, 0);

        matrices.pop();
    }

    @Override
    public GemPurifierBlockEntityRenderState createRenderState() {
        return new GemPurifierBlockEntityRenderState();
    }

    @Override
    public void render(GemPurifierBlockEntityRenderState state, MatrixStack matrices, OrderedRenderCommandQueue queue, CameraRenderState cameraState) {
        GemPurifierBlockEntity entity = state.entity;
        if (entity == null || entity.getWorld() == null) return;

        int light = getLightLevel(state.entityWorld, state.lightPos);
        float rotationAngles = getRotationAngle(entity);

        renderItem(state.inputItemRenderState, matrices, queue, 0.75f, 0.25f, rotationAngles, light);
        renderItem(state.energyItemRenderState, matrices, queue, 0.25f, 0.25f, rotationAngles, light);
        renderItem(state.resultItemRenderState, matrices, queue, 0.5f, 0.685f, rotationAngles, light);

        renderEnergyAmountText(state, entity, matrices, queue, LightmapTextureManager.applyEmission(light,2));
    }

    public BlockEntityRendererFactory.Context context() {
        return context;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (GemPurifierBlockEntityRenderer) obj;
        return Objects.equals(this.context, that.context);
    }

    @Override
    public int hashCode() {
        return Objects.hash(context);
    }

    @Override
    public String toString() {
        return "GemPurifierBlockEntityRenderer[" +
                "context=" + context + ']';
    }

}
