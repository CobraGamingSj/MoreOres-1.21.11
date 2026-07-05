package org.cobra.moreores.networking.block.data;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.math.BlockPos;
import org.cobra.moreores.MoreOresModInitializer;
import org.cobra.moreores.block.entity.gem.GemCrystallizerBlockEntity;
import org.cobra.moreores.client.gui.screen.GemPurifierScreenHandler;

public record GemCrystallizerDataSynchronizer(long energyAmount, int redstone, int radiantDust, BlockPos blockPos) implements CustomPayload {

    public static final Id<GemCrystallizerDataSynchronizer> ID = new Id<>(MoreOresModInitializer.id("data_pos_sync"));

    public void handlePacket(ClientPlayNetworking.Context context) {
        ClientWorld world = context.client().world;
        if (world == null) return;

        if (world.getBlockEntity(this.blockPos) instanceof GemCrystallizerBlockEntity blockEntity) {
            blockEntity.setEnergyAmount(this.energyAmount);
            blockEntity.setRedstone(this.redstone);
            blockEntity.setDustCount(this.radiantDust);

            if (context.player().currentScreenHandler instanceof GemPurifierScreenHandler screenHandler && screenHandler.blockEntity.getPos().equals(this.blockPos)) {
                blockEntity.setEnergyAmount(this.energyAmount);
                blockEntity.setRedstone(this.redstone);
                blockEntity.setDustCount(this.radiantDust);
            }
        }
    }

    public static final PacketCodec<RegistryByteBuf, GemCrystallizerDataSynchronizer> PACKET_CODEC =
            PacketCodec.tuple(
                    PacketCodecs.LONG, GemCrystallizerDataSynchronizer::energyAmount,
                    PacketCodecs.INTEGER, GemCrystallizerDataSynchronizer::redstone,
                    PacketCodecs.INTEGER, GemCrystallizerDataSynchronizer::radiantDust,
                    BlockPos.PACKET_CODEC, GemCrystallizerDataSynchronizer::blockPos,
                    GemCrystallizerDataSynchronizer::new
            );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
