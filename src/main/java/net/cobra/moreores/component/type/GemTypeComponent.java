//package net.cobra.moreores.component.type;
//
//import com.mojang.serialization.Codec;
//import net.cobra.moreores.block.entity.gem_polisher.util.GemType;
//import net.minecraft.network.RegistryByteBuf;
//import net.minecraft.network.codec.PacketCodec;
//import net.minecraft.network.codec.PacketCodecs;
//
//public record GemTypeComponent(GemType type) {
//
//    public static final Codec<GemTypeComponent> CODEC =
//            GemType.CODEC.xmap(GemTypeComponent::new, GemTypeComponent::type);
//
//    public static final PacketCodec<RegistryByteBuf, GemTypeComponent> PACKET_CODEC =
//            PacketCodec.tuple(
//                    PacketCodecs.STRING.xmap(GemType::valueOf, GemType::name),
//                    GemTypeComponent::type,
//                    GemTypeComponent::new
//            );
//
//}
