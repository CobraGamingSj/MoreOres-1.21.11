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
import org.cobra.moreores.block.entity.gem.machine.GemPurifierBlockEntity;
import org.cobra.moreores.client.gui.screen.GemPurifierScreenHandler;

public record GemPurifierDataSynchronizer(long energyAmount, int redstone, FluidVariant fluidVariant, long fluid, BlockPos blockPos) implements CustomPayload {

    public static final Id<GemPurifierDataSynchronizer> ID = new Id<>(MoreOresModInitializer.id("pos_sync"));

    public void handlePacket(ClientPlayNetworking.Context context) {
        ClientWorld world = context.client().world;
        if (world == null) return;

        if (world.getBlockEntity(this.blockPos) instanceof GemPurifierBlockEntity blockEntity) {
            blockEntity.setEnergyAmount(this.energyAmount);
            blockEntity.setRedstone(this.redstone);
            blockEntity.setFluid(this.fluidVariant, this.fluid);

            if (context.player().currentScreenHandler instanceof GemPurifierScreenHandler screenHandler && screenHandler.getBlockEntity().getPos().equals(this.blockPos)) {
                blockEntity.setEnergyAmount(this.energyAmount);
                blockEntity.setRedstone(this.redstone);
                blockEntity.setFluid(this.fluidVariant, this.fluid);
            }
        }
    }

    public static final PacketCodec<RegistryByteBuf, GemPurifierDataSynchronizer> PACKET_CODEC =
            PacketCodec.tuple(
                    PacketCodecs.LONG, GemPurifierDataSynchronizer::energyAmount,
                    PacketCodecs.INTEGER, GemPurifierDataSynchronizer::redstone,
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
