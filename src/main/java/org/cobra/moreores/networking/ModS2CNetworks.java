package org.cobra.moreores.networking;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import org.cobra.moreores.MoreOresModInitializer;
import org.cobra.moreores.networking.block.data.*;

import static org.cobra.moreores.MoreOresModInitializer.LOGGER;

public class ModS2CNetworks {

    public static void registerClientS2C(){
        ClientPlayNetworking.registerGlobalReceiver(GemMachineEnergyDataPayload.ID, GemMachineEnergyDataPayload::handlePacket);
        ClientPlayNetworking.registerGlobalReceiver(GemPurifierFluidDataPayload.ID, GemPurifierFluidDataPayload::handlePacket);
        ClientPlayNetworking.registerGlobalReceiver(GemPurifierDataSynchronizer.ID, GemPurifierDataSynchronizer::handlePacket);
        ClientPlayNetworking.registerGlobalReceiver(GemCrystallizerDataSynchronizer.ID, GemCrystallizerDataSynchronizer::handlePacket);
        ClientPlayNetworking.registerGlobalReceiver(ScreenGhostRenderingS2CPacket.ID, ScreenGhostRenderingS2CPacket::handlePacket);
    }

    public static void register() {
        LOGGER.info("Loading ModS2CNetworks for" + MoreOresModInitializer.MOD_ID + " mod.");
    }
}
