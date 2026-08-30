package org.cobra.moreores.networking.block.data;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import org.cobra.moreores.MoreOresModInitializer;
import org.cobra.moreores.client.gui.screen.AbstractGemMachineScreen;

public record ScreenGhostRenderingS2CPacket(ItemStack result) implements CustomPayload {
    public static final Id<ScreenGhostRenderingS2CPacket> ID = new Id<>(MoreOresModInitializer.id("screen_sync"));

    public static final PacketCodec<RegistryByteBuf, ScreenGhostRenderingS2CPacket> PACKET_CODEC = PacketCodec.tuple(
            ItemStack.OPTIONAL_PACKET_CODEC, ScreenGhostRenderingS2CPacket::result,
            ScreenGhostRenderingS2CPacket::new
    );

    public void handlePacket(ClientPlayNetworking.Context context) {
        if(context.client().currentScreen instanceof AbstractGemMachineScreen<?, ?> screen) {
            screen.setPreviewResultStack(result);
        }
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}