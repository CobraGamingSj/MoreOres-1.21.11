package net.cobra.moreores.networking;

import net.cobra.moreores.MoreOresModInitializer;
import net.cobra.moreores.networking.block.data.GemInfusionDataSynchronizer;
import net.cobra.moreores.networking.block.data.GemPFEnergyData;
import net.cobra.moreores.networking.block.data.GemPurifierFluidData;
import net.cobra.moreores.networking.block.data.GemPurifierDataSynchronizer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

import static net.cobra.moreores.MoreOresModInitializer.LOGGER;

public class ModS2CNetworks {

    public static void registerClientS2C(){
        ClientPlayNetworking.registerGlobalReceiver(GemPFEnergyData.ID, GemPFEnergyData::handlePacket);
        ClientPlayNetworking.registerGlobalReceiver(GemPurifierFluidData.ID, GemPurifierFluidData::handlePacket);
        ClientPlayNetworking.registerGlobalReceiver(GemPurifierDataSynchronizer.ID, GemPurifierDataSynchronizer::handlePacket);
        ClientPlayNetworking.registerGlobalReceiver(GemInfusionDataSynchronizer.ID, GemInfusionDataSynchronizer::handlePacket);
    }

    public static void register() {
        LOGGER.info("Loading ModS2CNetworks for" + MoreOresModInitializer.MOD_ID + " mod.");
    }
}
