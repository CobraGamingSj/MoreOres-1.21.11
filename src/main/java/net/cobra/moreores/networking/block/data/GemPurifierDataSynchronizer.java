package net.cobra.moreores.networking.block.data;

import net.cobra.moreores.MoreOresModInitializer;
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

public record GemPurifierDataSynchronizer(long energy, FluidVariant fluidVariant, long fluid, BlockPos blockPos) implements CustomPayload {

    public static final Id<GemPurifierDataSynchronizer> ID = new Id<>(MoreOresModInitializer.id("pos_sync"));

    public void handlePacket(ClientPlayNetworking.Context context) {
        ClientWorld world = context.client().world;
        if (world == null) return;

        if (world.getBlockEntity(this.blockPos) instanceof GemPurifierBlockEntity blockEntity) {
            blockEntity.setEnergyLevel(this.energy);
            blockEntity.setWaterLevel(this.fluidVariant, this.fluid);

            if (context.player().currentScreenHandler instanceof GemPurifierScreenHandler screenHandler && screenHandler.blockEntity.getPos().equals(this.blockPos)) {
                blockEntity.setEnergyLevel(this.energy);
                blockEntity.setWaterLevel(this.fluidVariant, this.fluid);
            }
        }
    }

    public static final PacketCodec<RegistryByteBuf, GemPurifierDataSynchronizer> PACKET_CODEC =
            PacketCodec.tuple(
                    PacketCodecs.LONG, GemPurifierDataSynchronizer::energy,
                    FluidVariant.PACKET_CODEC, GemPurifierDataSynchronizer::fluidVariant,
                    PacketCodecs.LONG, GemPurifierDataSynchronizer::fluid,
                    BlockPos.PACKET_CODEC, GemPurifierDataSynchronizer::blockPos,
                    GemPurifierDataSynchronizer::new
            );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
