package org.cobra.moreores.networking.block.data;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.cobra.moreores.MoreOresModInitializer;
import org.cobra.moreores.block.entity.gem.machine.AbstractGemMachineBlockEntity;
import org.cobra.moreores.client.gui.screen.AbstractGemMachineScreenHandler;

public record GemPurifierButtonClickPayload(int buttonIndex, BlockPos pos) implements CustomPayload {
    public static final Id<GemPurifierButtonClickPayload> ID = new Id<>(MoreOresModInitializer.id("button_click"));

    public static final PacketCodec<RegistryByteBuf, GemPurifierButtonClickPayload> PACKET_CODEC =
            PacketCodec.tuple(
                    PacketCodecs.VAR_INT, GemPurifierButtonClickPayload::buttonIndex,
                    BlockPos.PACKET_CODEC, GemPurifierButtonClickPayload::pos,
                    GemPurifierButtonClickPayload::new
            );

    public void handle(ServerPlayNetworking.Context context) {
        ServerWorld world = context.server().getOverworld();

        if(world.getBlockEntity(pos) instanceof AbstractGemMachineBlockEntity<?> blockEntity) {
            switch (buttonIndex) {
                case 0 -> blockEntity.start();
                case 1 -> blockEntity.pause();
                case 2 -> blockEntity.resume();
                case 3 -> blockEntity.stop();
                }

                if((context.player().currentScreenHandler instanceof AbstractGemMachineScreenHandler<?> handler && handler.getBlockPos().equals(pos))) {
                    switch (buttonIndex) {
                        case 0 -> blockEntity.start();
                        case 1 -> blockEntity.pause();
                        case 2 -> blockEntity.resume();
                        case 3 -> blockEntity.stop();
                    }
                }
            }

        MoreOresModInitializer.LOGGER.info("Received button click with ID: {} at {}", buttonIndex, "[" + pos.getX() + ", " + pos.getY() + ", " + pos.getZ() + "]");

        }

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
