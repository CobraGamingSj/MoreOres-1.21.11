package net.cobra.moreores.client.render.block.entity;

import net.cobra.moreores.block.entity.gem.GemIninfusionBlockEntity;
import net.minecraft.client.render.block.entity.state.BlockEntityRenderState;
import net.minecraft.client.render.item.ItemRenderState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GemIninfusionBlockEntityRenderState extends BlockEntityRenderState {

    public GemIninfusionBlockEntity entity;
    public World entityWorld;
    public BlockPos lightPos;

    final ItemRenderState inputItemRenderState = new ItemRenderState();
    final ItemRenderState inputAfterItemRenderState = new ItemRenderState();
    final ItemRenderState energyItemRenderState = new ItemRenderState();
    final ItemRenderState resultItemRenderState = new ItemRenderState();

    public static final GemIninfusionBlockEntityRenderState INSTANCE = new GemIninfusionBlockEntityRenderState();

    public void setEntity(GemIninfusionBlockEntity entity) {
        this.entity = entity;
    }
}
