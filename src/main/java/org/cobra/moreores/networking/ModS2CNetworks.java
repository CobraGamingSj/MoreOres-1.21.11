package org.cobra.moreores.networking;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import org.cobra.moreores.MoreOresModInitializer;
import org.cobra.moreores.networking.block.data.GemCrystallizerDataSynchronizer;
import org.cobra.moreores.networking.block.data.GemPFEnergyDataPayload;
import org.cobra.moreores.networking.block.data.GemPurifierDataSynchronizer;
import org.cobra.moreores.networking.block.data.GemPurifierFluidDataPayload;

import static org.cobra.moreores.MoreOresModInitializer.LOGGER;

public class ModS2CNetworks {

    public static void registerClientS2C(){
        ClientPlayNetworking.registerGlobalReceiver(GemPFEnergyDataPayload.ID, GemPFEnergyDataPayload::handlePacket);
        ClientPlayNetworking.registerGlobalReceiver(GemPurifierFluidDataPayload.ID, GemPurifierFluidDataPayload::handlePacket);
        ClientPlayNetworking.registerGlobalReceiver(GemPurifierDataSynchronizer.ID, GemPurifierDataSynchronizer::handlePacket);
        ClientPlayNetworking.registerGlobalReceiver(GemCrystallizerDataSynchronizer.ID, GemCrystallizerDataSynchronizer::handlePacket);
    }

    public static void register() {
        LOGGER.info("Loading ModS2CNetworks for" + MoreOresModInitializer.MOD_ID + " mod.");
    }
}
