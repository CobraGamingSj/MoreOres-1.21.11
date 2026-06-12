package org.cobra.moreores.networking;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import org.cobra.moreores.MoreOresModInitializer;
import org.cobra.moreores.networking.block.data.*;

import static org.cobra.moreores.MoreOresModInitializer.LOGGER;

@SuppressWarnings("Same PaR VAL")
public class ModS2CPayloadRegistry {
    static {
       registerS2C(GemPFEnergyDataPayload.ID, GemPFEnergyDataPayload.PACKET_CODEC);
       registerS2C(GemPurifierFluidDataPayload.ID, GemPurifierFluidDataPayload.PACKET_CODEC);
       registerS2C(GemPurifierDataSynchronizer.ID, GemPurifierDataSynchronizer.PACKET_CODEC);
       registerS2C(GemCrystallizerDataSynchronizer.ID, GemCrystallizerDataSynchronizer.PACKET_CODEC);
       registerS2C(PolishingStateDataPayload.ID, PolishingStateDataPayload.PACKET_CODEC);
    }

    public static<T extends CustomPayload> void registerS2C(CustomPayload.Id<T> id, PacketCodec<RegistryByteBuf, T> packetCodec) {
        PayloadTypeRegistry.playS2C().register(id, packetCodec);
    }

    public static void registerS2CPackets() {
        LOGGER.info("Loading ModS2CPackets for " + MoreOresModInitializer.MOD_ID + " mod.");
    }
}
