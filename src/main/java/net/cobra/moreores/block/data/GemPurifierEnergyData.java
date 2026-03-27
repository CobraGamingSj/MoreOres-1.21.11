package net.cobra.moreores.block.data;

import net.cobra.moreores.MoreOresModInitializer;
import net.cobra.moreores.block.entity.gem_polisher.GemPurifierBlockEntity;
import net.cobra.moreores.client.gui.screen.GemPurifierScreenHandler;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.math.BlockPos;

public record GemPurifierEnergyData(long energy, BlockPos blockPos) implements CustomPayload {
    public static final Id<GemPurifierEnergyData> ID = new Id<>(MoreOresModInitializer.getId("pos_energy"));

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

    public static final PacketCodec<RegistryByteBuf, GemPurifierEnergyData> PACKET_CODEC =
            PacketCodec.tuple(
                    PacketCodecs.LONG, GemPurifierEnergyData::energy,
                    BlockPos.PACKET_CODEC, GemPurifierEnergyData::blockPos,
                    GemPurifierEnergyData::new
            );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
