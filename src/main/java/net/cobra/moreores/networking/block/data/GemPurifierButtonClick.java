package net.cobra.moreores.networking.block.data;

import net.cobra.moreores.MoreOresModInitializer;
import net.cobra.moreores.block.entity.gem.AbstractGemPFBlockEntity;
import net.cobra.moreores.client.gui.screen.GemInfusionScreenHandler;
import net.cobra.moreores.client.gui.screen.GemPurifierScreenHandler;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

public record GemPurifierButtonClick(int buttonID, BlockPos pos) implements CustomPayload {
    public static final Id<GemPurifierButtonClick> ID = new Id<>(MoreOresModInitializer.getId("button_click"));

    public static final PacketCodec<RegistryByteBuf, GemPurifierButtonClick> PACKET_CODEC =
            PacketCodec.tuple(
                    PacketCodecs.VAR_INT, GemPurifierButtonClick::buttonID,
                    BlockPos.PACKET_CODEC, GemPurifierButtonClick::pos,
                    GemPurifierButtonClick::new
            );

    public void handle(ServerPlayNetworking.Context context) {
        ServerWorld world = context.server().getOverworld();

        if(world.getBlockEntity(pos) instanceof AbstractGemPFBlockEntity<?> blockEntity) {
            switch (buttonID) {
                case 0 -> blockEntity.start();
                case 1 -> blockEntity.pause();
                case 2 -> blockEntity.resume();
                case 3 -> blockEntity.stop();
                }

                if((context.player().currentScreenHandler instanceof GemPurifierScreenHandler screenHandler && screenHandler.blockEntity.getPos().equals(pos)) ||
                context.player().currentScreenHandler instanceof GemInfusionScreenHandler screenHandlerL && screenHandlerL.blockEntity.getPos().equals(pos)) {
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
