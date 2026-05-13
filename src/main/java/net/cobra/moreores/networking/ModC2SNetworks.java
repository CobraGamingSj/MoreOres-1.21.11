package net.cobra.moreores.networking;

import net.cobra.moreores.MoreOresModInitializer;
import net.cobra.moreores.networking.block.data.GemPurifierButtonClickPayload;
import net.cobra.moreores.networking.block.data.PolishingStateDataPayload;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

import static net.cobra.moreores.MoreOresModInitializer.LOGGER;

public class ModC2SNetworks {

    public static void registerServerC2S() {
        ServerPlayNetworking.registerGlobalReceiver(GemPurifierButtonClickPayload.ID, GemPurifierButtonClickPayload::handle);
        ServerPlayNetworking.registerGlobalReceiver(PolishingStateDataPayload.ID, PolishingStateDataPayload::handle);
    }

    public static void register() {
        LOGGER.info("Loading ModServerC2SNetworks for" + MoreOresModInitializer.MOD_ID + " mod.");
    }
}
