package net.cobra.moreores.client;

import net.cobra.moreores.block.ModBlocks;
import net.cobra.moreores.block.entity.ModBlockEntityType;
import net.cobra.moreores.client.gui.screen.GemPurifierScreen;
import net.cobra.moreores.client.gui.screen.ModScreenHandlerType;
import net.cobra.moreores.client.render.block.entity.GemPurifierBlockEntityRenderer;
import net.cobra.moreores.networking.ModS2CNetworks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.BlockRenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

public class MoreOresClientModInitializer implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModS2CNetworks.registerClientS2C();

        BlockRenderLayerMap.putBlock(ModBlocks.TOMATO_CROP, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.PINEAPPLE_CROP, BlockRenderLayer.CUTOUT);

        BlockRenderLayerMap.putBlock(ModBlocks.GEM_PURIFIER_BLOCK, BlockRenderLayer.TRANSLUCENT);

        HandledScreens.register(ModScreenHandlerType.GEM_PURIFYING_SCREEN_HANDLER, GemPurifierScreen::new);

        BlockEntityRendererFactories.register(ModBlockEntityType.GEM_PURIFIER_BLOCK_ENTITY, GemPurifierBlockEntityRenderer::new);
    }
}
