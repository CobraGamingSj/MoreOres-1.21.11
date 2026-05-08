package net.cobra.moreores.networking.block.data;

import net.cobra.moreores.MoreOresModInitializer;
import net.cobra.moreores.block.entity.gem.AbstractGemPFBlockEntity;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public record PolishingStateData(BlockPos blockPos, String action) implements CustomPayload {
    public static final Id<PolishingStateData> ID = new Id<>(Identifier.of(MoreOresModInitializer.MOD_ID, "polishing_state"));

    public static final PacketCodec<RegistryByteBuf, PolishingStateData> CODEC = PacketCodec.of((payload, buf) -> {
        buf.writeBlockPos(payload.blockPos);
        buf.writeString(payload.action);
    }, buf -> new PolishingStateData(buf.readBlockPos(), buf.readString()));

    public void handle(ServerPlayNetworking.Context context) {

        context.server().execute(() -> {
            if(context.player().getEntityWorld().getBlockEntity(blockPos) instanceof AbstractGemPFBlockEntity<?> be) {
                switch(action) {
                    case "start" -> be.start();
                    case "pause" -> be.pause();
                    case "resume" -> be.resume();
                    case "stop" -> be.stop();
                }
                be.markDirty();
            }
        });
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
