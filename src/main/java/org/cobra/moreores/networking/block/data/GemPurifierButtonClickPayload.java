package org.cobra.moreores.networking.block.data;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.cobra.moreores.MoreOresModInitializer;
import org.cobra.moreores.block.entity.gem.AbstractGemPCBlockEntity;
import org.cobra.moreores.client.gui.screen.GemCrystallizerScreenHandler;
import org.cobra.moreores.client.gui.screen.GemPurifierScreenHandler;

public record GemPurifierButtonClickPayload(int buttonID, BlockPos pos) implements CustomPayload {
    public static final Id<GemPurifierButtonClickPayload> ID = new Id<>(MoreOresModInitializer.id("button_click"));

    public static final PacketCodec<RegistryByteBuf, GemPurifierButtonClickPayload> PACKET_CODEC =
            PacketCodec.tuple(
                    PacketCodecs.VAR_INT, GemPurifierButtonClickPayload::buttonID,
                    BlockPos.PACKET_CODEC, GemPurifierButtonClickPayload::pos,
                    GemPurifierButtonClickPayload::new
            );

    public void handle(ServerPlayNetworking.Context context) {
        ServerWorld world = context.server().getOverworld();

        if(world.getBlockEntity(pos) instanceof AbstractGemPCBlockEntity<?> blockEntity) {
            switch (buttonID) {
                case 0 -> blockEntity.start();
                case 1 -> blockEntity.pause();
                case 2 -> blockEntity.resume();
                case 3 -> blockEntity.stop();
                }

                if((context.player().currentScreenHandler instanceof GemPurifierScreenHandler screenHandler && screenHandler.blockEntity.getPos().equals(pos)) ||
                context.player().currentScreenHandler instanceof GemCrystallizerScreenHandler screenHandlerL && screenHandlerL.blockEntity.getPos().equals(pos)) {
                    switch (buttonID) {
                        case 0 -> blockEntity.start();
                        case 1 -> blockEntity.pause();
                        case 2 -> blockEntity.resume();
                        case 3 -> blockEntity.stop();
                    }
                }
            }

        MoreOresModInitializer.LOGGER.info("Received button click with ID: {} at {}", buttonID, "[" + pos.getX() + ", " + pos.getY() + ", " + pos.getZ() + "]");

        }

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
