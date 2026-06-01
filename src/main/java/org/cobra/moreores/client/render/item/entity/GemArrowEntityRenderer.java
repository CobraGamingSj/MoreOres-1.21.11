package org.cobra.moreores.client.render.item.entity;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.client.render.entity.state.ProjectileEntityRenderState;
import net.minecraft.util.Identifier;
import org.cobra.moreores.MoreOresModInitializer;
import org.cobra.moreores.entity.GemArrowEntity;

public class GemArrowEntityRenderer extends ProjectileEntityRenderer<GemArrowEntity, ProjectileEntityRenderState> {
    public static final Identifier TEXTURE = MoreOresModInitializer.id("textures/entity/item/gem_arrow.png");
    public GemArrowEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    protected Identifier getTexture(ProjectileEntityRenderState state) {
        return TEXTURE;
    }

    @Override
    public ProjectileEntityRenderState createRenderState() {
        return new ProjectileEntityRenderState();
    }

    @Override
    public void updateRenderState(GemArrowEntity persistentProjectileEntity, ProjectileEntityRenderState projectileEntityRenderState, float f) {
        super.updateRenderState(persistentProjectileEntity, projectileEntityRenderState, f);
    }
}
