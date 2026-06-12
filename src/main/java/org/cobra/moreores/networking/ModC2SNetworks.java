package org.cobra.moreores.networking;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import org.cobra.moreores.MoreOresModInitializer;
import org.cobra.moreores.networking.block.data.GemCrystallizerBlockData;
import org.cobra.moreores.networking.block.data.GemPurifierBlockData;
import org.cobra.moreores.networking.block.data.GemPurifierButtonClickPayload;
import org.cobra.moreores.networking.block.data.PolishingStateDataPayload;
import org.cobra.moreores.networking.item.EnergyIngotC2SPayload;

import static org.cobra.moreores.MoreOresModInitializer.LOGGER;

public class ModC2SNetworks {

    public static void registerServerC2S() {
        ServerPlayNetworking.registerGlobalReceiver(GemPurifierButtonClickPayload.ID, GemPurifierButtonClickPayload::handle);
        ServerPlayNetworking.registerGlobalReceiver(PolishingStateDataPayload.ID, PolishingStateDataPayload::handle);
        ServerPlayNetworking.registerGlobalReceiver(GemPurifierBlockData.ID, GemPurifierBlockData::handle);
        ServerPlayNetworking.registerGlobalReceiver(GemCrystallizerBlockData.ID, GemCrystallizerBlockData::handle);
        ServerPlayNetworking.registerGlobalReceiver(EnergyIngotC2SPayload.ID, EnergyIngotC2SPayload::handle);
    }

    public static void register() {
        LOGGER.info("Loading ModServerC2SNetworks for" + MoreOresModInitializer.MOD_ID + " mod.");
    }
}
