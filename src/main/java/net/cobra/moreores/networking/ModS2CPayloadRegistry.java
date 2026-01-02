package net.cobra.moreores.networking;

import net.cobra.moreores.MoreOresModInitializer;
import net.cobra.moreores.block.data.GemPurifierData;
import net.cobra.moreores.block.data.PolishingStateData;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;

import static net.cobra.moreores.MoreOresModInitializer.LOGGER;

@SuppressWarnings("Same PaR VAL")
public class ModS2CPayloadRegistry {
    static {
       registerS2C(GemPurifierData.ID, GemPurifierData.PACKET_CODEC);
       registerS2C(PolishingStateData.ID, PolishingStateData.CODEC);
    }

    public static<T extends CustomPayload> void registerS2C(CustomPayload.Id<T> id, PacketCodec<RegistryByteBuf, T> packetCodec) {
        PayloadTypeRegistry.playS2C().register(id, packetCodec);
    }

    public static void registerS2CPackets() {
        LOGGER.info("Loading ModS2CPackets for " + MoreOresModInitializer.MOD_ID + " mod.");
    }
}
