package org.cobra.moreores.networking.block.data;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.math.BlockPos;
import org.cobra.moreores.MoreOresModInitializer;
import org.cobra.moreores.block.entity.gem.GemPurifierBlockEntity;
import org.cobra.moreores.client.gui.screen.GemPurifierScreenHandler;

public record GemPurifierFluidDataPayload(FluidVariant fluidVariant, long fluidAmount, BlockPos blockPos) implements CustomPayload {
    public static final Id<GemPurifierFluidDataPayload> ID = new Id<>(MoreOresModInitializer.id("pos_fluid"));

    public void handlePacket(ClientPlayNetworking.Context context) {
        ClientWorld world = context.client().world;
        if (world == null) return;

        if (world.getBlockEntity(this.blockPos) instanceof GemPurifierBlockEntity blockEntity) {
            blockEntity.setFluid(this.fluidVariant, this.fluidAmount);

            if (context.player().currentScreenHandler instanceof GemPurifierScreenHandler screenHandler && screenHandler.getBlockEntity().getPos().equals(this.blockPos)) {
                blockEntity.setFluid(this.fluidVariant, this.fluidAmount);
            }
        }
    }

    public static final PacketCodec<RegistryByteBuf, GemPurifierFluidDataPayload> PACKET_CODEC =
            PacketCodec.tuple(
                    FluidVariant.PACKET_CODEC, GemPurifierFluidDataPayload::fluidVariant,
                    PacketCodecs.LONG, GemPurifierFluidDataPayload::fluidAmount,
                    BlockPos.PACKET_CODEC, GemPurifierFluidDataPayload::blockPos,
                    GemPurifierFluidDataPayload::new
            );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
