package net.cobra.moreores.networking;

import net.cobra.moreores.MoreOresModInitializer;
import net.cobra.moreores.networking.block.data.GemPurifierButtonClick;
import net.cobra.moreores.networking.block.data.PolishingStateData;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

import static net.cobra.moreores.MoreOresModInitializer.LOGGER;

public class ModC2SNetworks {

    public static void registerServerC2S() {
        ServerPlayNetworking.registerGlobalReceiver(GemPurifierButtonClick.ID, GemPurifierButtonClick::handle);
        ServerPlayNetworking.registerGlobalReceiver(PolishingStateData.ID, PolishingStateData::handle);
    }

    public static void register() {
        LOGGER.info("Loading ModServerC2SNetworks for" + MoreOresModInitializer.MOD_ID + " mod.");
    }
}
