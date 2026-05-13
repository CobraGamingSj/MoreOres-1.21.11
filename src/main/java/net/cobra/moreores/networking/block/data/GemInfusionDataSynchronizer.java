package net.cobra.moreores.networking.block.data;

import net.cobra.moreores.MoreOresModInitializer;
import net.cobra.moreores.block.entity.gem.GemInfusionBlockEntity;
import net.cobra.moreores.block.entity.gem.GemPurifierBlockEntity;
import net.cobra.moreores.client.gui.screen.GemPurifierScreenHandler;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.math.BlockPos;

public record GemInfusionDataSynchronizer(long energy, int dustCount, BlockPos blockPos) implements CustomPayload {

    public static final Id<GemInfusionDataSynchronizer> ID = new Id<>(MoreOresModInitializer.getId("data_pos_sync"));

    public void handlePacket(ClientPlayNetworking.Context context) {
        ClientWorld world = context.client().world;
        if (world == null) return;

        if (world.getBlockEntity(this.blockPos) instanceof GemInfusionBlockEntity blockEntity) {
            blockEntity.setEnergyLevel(this.energy);
            blockEntity.setDustCount(this.dustCount);

            if (context.player().currentScreenHandler instanceof GemPurifierScreenHandler screenHandler && screenHandler.blockEntity.getPos().equals(this.blockPos)) {
                blockEntity.setEnergyLevel(this.energy);
                blockEntity.setDustCount(this.dustCount);
            }
        }
    }

    public static final PacketCodec<RegistryByteBuf, GemInfusionDataSynchronizer> PACKET_CODEC =
            PacketCodec.tuple(
                    PacketCodecs.LONG, GemInfusionDataSynchronizer::energy,
                    PacketCodecs.INTEGER, GemInfusionDataSynchronizer::dustCount,
                    BlockPos.PACKET_CODEC, GemInfusionDataSynchronizer::blockPos,
                    GemInfusionDataSynchronizer::new
            );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
