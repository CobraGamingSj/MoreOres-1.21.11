package net.cobra.moreores.block.data;

import net.cobra.moreores.MoreOresModInitializer;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public record PolishingStateData(BlockPos pos, String action) implements CustomPayload {
    public static final Id<PolishingStateData> ID = new Id<>(Identifier.of(MoreOresModInitializer.MOD_ID, "polishing_state"));

    public static final PacketCodec<RegistryByteBuf, PolishingStateData> CODEC = PacketCodec.of((payload, buf) -> {
        buf.writeBlockPos(payload.pos);
        buf.writeString(payload.action);
    }, buf -> new PolishingStateData(buf.readBlockPos(), buf.readString()));

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
