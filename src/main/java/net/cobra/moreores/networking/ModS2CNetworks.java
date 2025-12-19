package net.cobra.moreores.networking;

import net.cobra.moreores.MoreOresModInitializer;
import net.cobra.moreores.block.data.GemPurifierData;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

import static net.cobra.moreores.MoreOresModInitializer.LOGGER;

public class ModS2CNetworks {

    public static void registerClientS2C(){
        ClientPlayNetworking.registerGlobalReceiver(GemPurifierData.ID, GemPurifierData::handlePacket);
    }

    public static void register() {
        LOGGER.info("Loading ModS2CNetworks for" + MoreOresModInitializer.MOD_ID + " mod.");
    }
}
