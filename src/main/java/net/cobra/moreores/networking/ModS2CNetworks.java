package net.cobra.moreores.networking;

import net.cobra.moreores.MoreOresModInitializer;
import net.cobra.moreores.networking.block.data.GemInfusionDataSynchronizer;
import net.cobra.moreores.networking.block.data.GemPFEnergyDataPayload;
import net.cobra.moreores.networking.block.data.GemPurifierFluidDataPayload;
import net.cobra.moreores.networking.block.data.GemPurifierDataSynchronizer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

import static net.cobra.moreores.MoreOresModInitializer.LOGGER;

public class ModS2CNetworks {

    public static void registerClientS2C(){
        ClientPlayNetworking.registerGlobalReceiver(GemPFEnergyDataPayload.ID, GemPFEnergyDataPayload::handlePacket);
        ClientPlayNetworking.registerGlobalReceiver(GemPurifierFluidDataPayload.ID, GemPurifierFluidDataPayload::handlePacket);
        ClientPlayNetworking.registerGlobalReceiver(GemPurifierDataSynchronizer.ID, GemPurifierDataSynchronizer::handlePacket);
        ClientPlayNetworking.registerGlobalReceiver(GemInfusionDataSynchronizer.ID, GemInfusionDataSynchronizer::handlePacket);
    }

    public static void register() {
        LOGGER.info("Loading ModS2CNetworks for" + MoreOresModInitializer.MOD_ID + " mod.");
    }
}
