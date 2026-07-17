package org.cobra.moreores.client.render.block.entity;

import net.minecraft.client.render.block.entity.state.BlockEntityRenderState;
import net.minecraft.client.render.item.ItemRenderState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.cobra.moreores.block.entity.gem.machine.GemCrystallizerBlockEntity;

public class GemCrystallizerBlockEntityRenderState extends BlockEntityRenderState {

    public GemCrystallizerBlockEntity entity;
    public World entityWorld;
    public BlockPos lightPos;

    final ItemRenderState inputBeforeItemRenderState = new ItemRenderState();
    final ItemRenderState inputAfterItemRenderState = new ItemRenderState();
    final ItemRenderState energyItemRenderState = new ItemRenderState();
    final ItemRenderState resultItemRenderState = new ItemRenderState();

    public void setEntity(GemCrystallizerBlockEntity entity) {
        this.entity = entity;
    }
}
