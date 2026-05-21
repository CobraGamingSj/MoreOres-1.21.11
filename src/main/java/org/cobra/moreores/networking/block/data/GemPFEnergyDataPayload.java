package org.cobra.moreores.networking.block.data;

import org.cobra.moreores.MoreOresModInitializer;
import org.cobra.moreores.block.entity.gem.AbstractGemPCBlockEntity;
import org.cobra.moreores.client.gui.screen.AbstractGemPFScreenHandler;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.math.BlockPos;

public record GemPFEnergyDataPayload(long energy, BlockPos blockPos) implements CustomPayload {
    public static final Id<GemPFEnergyDataPayload> ID = new Id<>(MoreOresModInitializer.id("pos_energy"));

    public void handlePacket(ClientPlayNetworking.Context context) {
        ClientWorld world = context.client().world;
        if (world == null) return;

        if (world.getBlockEntity(this.blockPos) instanceof AbstractGemPCBlockEntity<?> blockEntity) {
            blockEntity.setEnergyLevel(this.energy);

            if (context.player().currentScreenHandler instanceof AbstractGemPFScreenHandler screenHandler && screenHandler.getPos().equals(this.blockPos)) {
                blockEntity.setEnergyLevel(this.energy);
            }
        }
    }

    public static final PacketCodec<RegistryByteBuf, GemPFEnergyDataPayload> PACKET_CODEC =
            PacketCodec.tuple(
                    PacketCodecs.LONG, GemPFEnergyDataPayload::energy,
                    BlockPos.PACKET_CODEC, GemPFEnergyDataPayload::blockPos,
                    GemPFEnergyDataPayload::new
            );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
