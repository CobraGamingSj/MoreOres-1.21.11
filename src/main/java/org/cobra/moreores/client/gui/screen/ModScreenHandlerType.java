package org.cobra.moreores.client.gui.screen;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import org.cobra.moreores.MoreOresModInitializer;
import org.cobra.moreores.networking.block.data.GemCrystallizerDataSynchronizer;
import org.cobra.moreores.networking.block.data.GemPurifierDataSynchronizer;

public class ModScreenHandlerType {

    public static final ScreenHandlerType<GemPurifierScreenHandler> GEM_PURIFYING_SCREEN_HANDLER =
            register("gem_purifier_block", GemPurifierScreenHandler::new, GemPurifierDataSynchronizer.PACKET_CODEC
            );

    public static final ScreenHandlerType<GemCrystallizerScreenHandler> GEM_CRYSTALLIZER_SCREEN_HANDLER =
            register("gem_crystallizer_block", GemCrystallizerScreenHandler::new, GemCrystallizerDataSynchronizer.PACKET_CODEC);

    private static <S extends ScreenHandler, D extends CustomPayload> ExtendedScreenHandlerType<S, D> register(String id, ExtendedScreenHandlerType.ExtendedFactory<S, D> factory, PacketCodec<? super RegistryByteBuf, D> packetCodec) {
        return Registry.register(Registries.SCREEN_HANDLER, MoreOresModInitializer.id(id), new ExtendedScreenHandlerType<>(factory, packetCodec));
    }

    public static void register() {
        MoreOresModInitializer.LOGGER.info("Loading ModScreenHandlerType for " + MoreOresModInitializer.MOD_ID + " mod.");
    }
}
