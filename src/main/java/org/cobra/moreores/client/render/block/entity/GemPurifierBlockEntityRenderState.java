package org.cobra.moreores.client.render.block.entity;

import net.minecraft.client.render.block.entity.state.BlockEntityRenderState;
import net.minecraft.client.render.item.ItemRenderState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.cobra.moreores.block.entity.gem.machine.GemPurifierBlockEntity;

public class GemPurifierBlockEntityRenderState extends BlockEntityRenderState {

    public GemPurifierBlockEntity entity;
    public World entityWorld;
    public BlockPos lightPos;

    final ItemRenderState inputItemRenderState = new ItemRenderState();
    final ItemRenderState energyItemRenderState = new ItemRenderState();
    final ItemRenderState resultItemRenderState = new ItemRenderState();

    public static final GemPurifierBlockEntityRenderState INSTANCE = new GemPurifierBlockEntityRenderState();

    public void setEntity(GemPurifierBlockEntity entity) {
        this.entity = entity;
    }
}
