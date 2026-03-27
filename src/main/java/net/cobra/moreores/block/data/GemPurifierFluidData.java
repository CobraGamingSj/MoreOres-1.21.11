package net.cobra.moreores.block.data;

import net.cobra.moreores.MoreOresModInitializer;
import net.cobra.moreores.block.entity.gem_polisher.GemPurifierBlockEntity;
import net.cobra.moreores.client.gui.screen.GemPurifierScreenHandler;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.math.BlockPos;

public record GemPurifierFluidData(FluidVariant var, long fluid, BlockPos blockPos) implements CustomPayload {
    public static final Id<GemPurifierFluidData> ID = new Id<>(MoreOresModInitializer.getId("pos_fluid"));

    public void handlePacket(ClientPlayNetworking.Context context) {
        ClientWorld world = context.client().world;
        if (world == null) return;

        if (world.getBlockEntity(this.blockPos) instanceof GemPurifierBlockEntity blockEntity) {
            blockEntity.setWaterLevel(this.var, this.fluid);

            if (context.player().currentScreenHandler instanceof GemPurifierScreenHandler screenHandler && screenHandler.blockEntity.getPos().equals(this.blockPos)) {
                blockEntity.setWaterLevel(this.var, this.fluid);
            }
        }
    }

    public static final PacketCodec<RegistryByteBuf, GemPurifierFluidData> PACKET_CODEC =
            PacketCodec.tuple(
                    FluidVariant.PACKET_CODEC, GemPurifierFluidData::var,
                    PacketCodecs.LONG, GemPurifierFluidData::fluid,
                    BlockPos.PACKET_CODEC, GemPurifierFluidData::blockPos,
                    GemPurifierFluidData::new
            );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
