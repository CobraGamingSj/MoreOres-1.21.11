package net.cobra.moreores.block.data;

import net.cobra.moreores.MoreOresModInitializer;
import net.cobra.moreores.block.entity.GemPurifierBlockEntity;
import net.cobra.moreores.screen.GemPurifierScreenHandler;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.math.BlockPos;

public record GemPurifierData(long energy, BlockPos blockPos) implements CustomPayload {
    public static final Id<GemPurifierData> ID = new Id<>(MoreOresModInitializer.getId("pos_energy"));

    public void handlePacket(ClientPlayNetworking.Context context) {
        ClientWorld world = context.client().world;
        if (world == null) return;

        if (world.getBlockEntity(this.blockPos) instanceof GemPurifierBlockEntity blockEntity) {
            blockEntity.setEnergyLevel(this.energy);

            if (context.player().currentScreenHandler instanceof GemPurifierScreenHandler screenHandler && screenHandler.blockEntity.getPos().equals(this.blockPos)) {
                blockEntity.setEnergyLevel(this.energy);
            }
        }
    }

    public static final PacketCodec<RegistryByteBuf, GemPurifierData> PACKET_CODEC =
            PacketCodec.tuple(
                    PacketCodecs.LONG, GemPurifierData::energy,
                    BlockPos.PACKET_CODEC, GemPurifierData::blockPos,
                    GemPurifierData::new
            );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
