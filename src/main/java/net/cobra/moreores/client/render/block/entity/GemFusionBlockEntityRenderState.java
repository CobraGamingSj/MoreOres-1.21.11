package net.cobra.moreores.client.render.block.entity;

import net.cobra.moreores.block.entity.gem.GemFusionBlockEntity;
import net.minecraft.client.render.block.entity.state.BlockEntityRenderState;
import net.minecraft.client.render.item.ItemRenderState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GemFusionBlockEntityRenderState extends BlockEntityRenderState {

    public GemFusionBlockEntity entity;
    public World entityWorld;
    public BlockPos lightPos;

    final ItemRenderState inputItemRenderState = new ItemRenderState();
    final ItemRenderState inputAfterItemRenderState = new ItemRenderState();
    final ItemRenderState energyItemRenderState = new ItemRenderState();
    final ItemRenderState resultItemRenderState = new ItemRenderState();

    public static final GemFusionBlockEntityRenderState INSTANCE = new GemFusionBlockEntityRenderState();

    public void setEntity(GemFusionBlockEntity entity) {
        this.entity = entity;
    }
}
