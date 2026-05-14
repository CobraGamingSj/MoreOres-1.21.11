package net.cobra.moreores.networking.block.data;

import net.cobra.moreores.MoreOresModInitializer;
import net.cobra.moreores.block.entity.gem.AbstractGemPFBlockEntity;
import net.cobra.moreores.client.gui.screen.AbstractGemPFScreenHandler;
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

        if (world.getBlockEntity(this.blockPos) instanceof AbstractGemPFBlockEntity<?> blockEntity) {
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
