package org.cobra.moreores.networking;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import org.cobra.moreores.MoreOresModInitializer;
import org.cobra.moreores.networking.block.data.GemCrystallizerBlockData;
import org.cobra.moreores.networking.block.data.GemPurifierBlockData;
import org.cobra.moreores.networking.block.data.GemPurifierButtonClickPayload;
import org.cobra.moreores.networking.block.data.MachineStatusDataPayload;
import org.cobra.moreores.networking.item.EnergyIngotC2SPayload;

import static org.cobra.moreores.MoreOresModInitializer.LOGGER;

public class ModC2SPayloadRegistry {

    static {
        registerC2S(GemPurifierButtonClickPayload.ID, GemPurifierButtonClickPayload.PACKET_CODEC);
        registerC2S(MachineStatusDataPayload.ID, MachineStatusDataPayload.PACKET_CODEC);
        registerC2S(GemPurifierBlockData.ID, GemPurifierBlockData.PACKET_CODEC);
        registerC2S(GemCrystallizerBlockData.ID, GemCrystallizerBlockData.PACKET_CODEC);
        registerC2S(EnergyIngotC2SPayload.ID, EnergyIngotC2SPayload.PACKET_CODEC);
    }
    
    public static<T extends CustomPayload> void registerC2S(CustomPayload.Id<T> id, PacketCodec<RegistryByteBuf, T> packetCodec) {
        PayloadTypeRegistry.playC2S().register(id, packetCodec);
    }
    
    public static void registerC2SPackets() {
        LOGGER.info("Loading ModC2SPackets for " + MoreOresModInitializer.MOD_ID + " mod.");
    }
    
}
