package org.cobra.moreores.client.render.block.entity;

import org.cobra.moreores.block.entity.gem.GemCrystallizeBlockEntity;
import net.minecraft.client.render.block.entity.state.BlockEntityRenderState;
import net.minecraft.client.render.item.ItemRenderState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GemInfusionBlockEntityRenderState extends BlockEntityRenderState {

    public GemCrystallizeBlockEntity entity;
    public World entityWorld;
    public BlockPos lightPos;

    final ItemRenderState inputBeforeItemRenderState = new ItemRenderState();
    final ItemRenderState inputAfterItemRenderState = new ItemRenderState();
    final ItemRenderState energyItemRenderState = new ItemRenderState();
    final ItemRenderState resultItemRenderState = new ItemRenderState();

    public void setEntity(GemCrystallizeBlockEntity entity) {
        this.entity = entity;
    }
}
