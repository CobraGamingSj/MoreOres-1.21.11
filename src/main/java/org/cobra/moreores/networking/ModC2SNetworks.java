package org.cobra.moreores.networking;

import org.cobra.moreores.MoreOresModInitializer;
import org.cobra.moreores.networking.block.data.GemPurifierBlockData;
import org.cobra.moreores.networking.block.data.GemPurifierButtonClickPayload;
import org.cobra.moreores.networking.block.data.PolishingStateDataPayload;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

import static org.cobra.moreores.MoreOresModInitializer.LOGGER;

public class ModC2SNetworks {

    public static void registerServerC2S() {
        ServerPlayNetworking.registerGlobalReceiver(GemPurifierButtonClickPayload.ID, GemPurifierButtonClickPayload::handle);
        ServerPlayNetworking.registerGlobalReceiver(PolishingStateDataPayload.ID, PolishingStateDataPayload::handle);
        ServerPlayNetworking.registerGlobalReceiver(GemPurifierBlockData.ID, GemPurifierBlockData::handle);
    }



    public static void register() {
        LOGGER.info("Loading ModServerC2SNetworks for" + MoreOresModInitializer.MOD_ID + " mod.");
    }
}
