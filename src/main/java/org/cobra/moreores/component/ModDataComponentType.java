package org.cobra.moreores.component;

import com.mojang.serialization.Codec;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import org.cobra.moreores.MoreOresModInitializer;

import java.util.function.UnaryOperator;

public class ModDataComponentType {

    public static final ComponentType<Boolean> EVOLVED = register("evolved", builder -> builder.codec(Codec.BOOL));
    
    private static <T> ComponentType<T> register(String name, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, MoreOresModInitializer.id(name), builderOperator.apply(ComponentType.builder()).build());
    }
    
    public static void register() {
        
    }
}
