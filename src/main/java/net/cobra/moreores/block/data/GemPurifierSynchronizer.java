package net.cobra.moreores.block.data;

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

public record GemPurifierSynchronizer(long energy, FluidVariant fluidVariant, long fluid, BlockPos blockPos) implements CustomPayload {

    public static final Id<GemPurifierSynchronizer> ID = new Id<>(MoreOresModInitializer.getId("pos_sync"));

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

    public static final PacketCodec<RegistryByteBuf, GemPurifierSynchronizer> PACKET_CODEC =
            PacketCodec.tuple(
                    PacketCodecs.LONG, GemPurifierSynchronizer::energy,
                    FluidVariant.PACKET_CODEC, GemPurifierSynchronizer::fluidVariant,
                    PacketCodecs.LONG, GemPurifierSynchronizer::fluid,
                    BlockPos.PACKET_CODEC, GemPurifierSynchronizer::blockPos,
                    GemPurifierSynchronizer::new
            );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
