package org.cobra.moreores.networking.block.data;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.math.BlockPos;
import org.cobra.moreores.MoreOresModInitializer;
import org.cobra.moreores.block.entity.gem.GemCrystallizeBlockEntity;
import org.cobra.moreores.client.gui.screen.GemPurifierScreenHandler;

public record GemCrystallizerDataSynchronizer(long energy, int dustCount, BlockPos blockPos) implements CustomPayload {

    public static final Id<GemCrystallizerDataSynchronizer> ID = new Id<>(MoreOresModInitializer.id("data_pos_sync"));

    public void handlePacket(ClientPlayNetworking.Context context) {
        ClientWorld world = context.client().world;
        if (world == null) return;

        if (world.getBlockEntity(this.blockPos) instanceof GemCrystallizeBlockEntity blockEntity) {
            blockEntity.setEnergyLevel(this.energy);
            blockEntity.setDustCount(this.dustCount);

            if (context.player().currentScreenHandler instanceof GemPurifierScreenHandler screenHandler && screenHandler.blockEntity.getPos().equals(this.blockPos)) {
                blockEntity.setEnergyLevel(this.energy);
                blockEntity.setDustCount(this.dustCount);
            }
        }
    }

    public static final PacketCodec<RegistryByteBuf, GemCrystallizerDataSynchronizer> PACKET_CODEC =
            PacketCodec.tuple(
                    PacketCodecs.LONG, GemCrystallizerDataSynchronizer::energy,
                    PacketCodecs.INTEGER, GemCrystallizerDataSynchronizer::dustCount,
                    BlockPos.PACKET_CODEC, GemCrystallizerDataSynchronizer::blockPos,
                    GemCrystallizerDataSynchronizer::new
            );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
