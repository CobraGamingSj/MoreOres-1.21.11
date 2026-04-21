//package net.cobra.moreores.component.type;
//
//import net.cobra.moreores.MoreOresModInitializer;
//import net.minecraft.component.ComponentType;
//import net.minecraft.registry.Registries;
//import net.minecraft.registry.Registry;
//
//import java.util.function.UnaryOperator;
//
//public class ModDataComponentType {
//
//    public static final ComponentType<GemTypeComponent> GEM_TYPE = register(
//            "gem_type", builder -> builder.codec(GemTypeComponent.CODEC).packetCodec(GemTypeComponent.PACKET_CODEC).cache()
//    );
//
//    private static <T> ComponentType<T> register(String id, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
//        return Registry.register(Registries.DATA_COMPONENT_TYPE, MoreOresModInitializer.getId(id), ((ComponentType.Builder)builderOperator.apply(ComponentType.builder())).build());
//    }
//
//    public static void register() {
//
//    }
//}
