package org.cobra.moreores.networking.block.data;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.math.BlockPos;
import org.cobra.moreores.MoreOresModInitializer;
import org.cobra.moreores.block.entity.gem.AbstractGemMachineBlockEntity;
import org.cobra.moreores.client.gui.screen.AbstractGemMachineScreenHandler;

public record GemPFEnergyDataPayload(long energyAmount, BlockPos blockPos) implements CustomPayload {
    public static final Id<GemPFEnergyDataPayload> ID = new Id<>(MoreOresModInitializer.id("pos_energy"));

    public void handlePacket(ClientPlayNetworking.Context context) {
        ClientWorld world = context.client().world;
        if (world == null) return;

        if (world.getBlockEntity(this.blockPos) instanceof AbstractGemMachineBlockEntity<?> blockEntity) {
            blockEntity.setEnergyAmount(this.energyAmount);

            if (context.player().currentScreenHandler instanceof AbstractGemMachineScreenHandler<?> screenHandler && screenHandler.getPos().equals(this.blockPos)) {
                blockEntity.setEnergyAmount(this.energyAmount);
            }
        }
    }

    public static final PacketCodec<RegistryByteBuf, GemPFEnergyDataPayload> PACKET_CODEC =
            PacketCodec.tuple(
                    PacketCodecs.LONG, GemPFEnergyDataPayload::energyAmount,
                    BlockPos.PACKET_CODEC, GemPFEnergyDataPayload::blockPos,
                    GemPFEnergyDataPayload::new
            );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
