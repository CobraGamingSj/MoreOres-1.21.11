package org.cobra.moreores.client;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import org.cobra.moreores.block.ModBlocks;
import org.cobra.moreores.block.entity.ModBlockEntityType;
import org.cobra.moreores.client.gui.screen.GemCrystallizerScreen;
import org.cobra.moreores.client.gui.screen.GemPurifierScreen;
import org.cobra.moreores.client.gui.screen.ModScreenHandlerType;
import org.cobra.moreores.client.render.block.entity.GemInfusionBlockEntityRenderer;
import org.cobra.moreores.client.render.block.entity.GemPurifierBlockEntityRenderer;
import org.cobra.moreores.networking.ModS2CNetworks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.BlockRenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import org.lwjgl.glfw.GLFW;

public class MoreOresClientModInitializer implements ClientModInitializer {
    public static final KeyBinding altKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "alt",
            GLFW.GLFW_KEY_LEFT_ALT,
            KeyBinding.Category.INVENTORY
    ));

    @Override
    public void onInitializeClient() {

        ModS2CNetworks.registerClientS2C();

        BlockRenderLayerMap.putBlock(ModBlocks.GEM_PURIFIER_BLOCK, BlockRenderLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(ModBlocks.GE_CRYSTALLIZER_BLOCK, BlockRenderLayer.TRANSLUCENT);

        HandledScreens.register(ModScreenHandlerType.GEM_PURIFYING_SCREEN_HANDLER, GemPurifierScreen::new);
        HandledScreens.register(ModScreenHandlerType.GEM_CRYSTALLIZER_SCREEN_HANDLER, GemCrystallizerScreen::new);

        BlockEntityRendererFactories.register(ModBlockEntityType.GEM_PURIFIER_BLOCK_ENTITY, GemPurifierBlockEntityRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntityType.GEM_CRYSTALLIZE_BLOCK_ENTITY, GemInfusionBlockEntityRenderer::new);
    }
}
