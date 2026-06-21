package org.cobra.moreores.block.entity.gem;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringIdentifiable;

public enum FluidState implements StringIdentifiable {
    IDLE("idle"),
    FILLING("filling"),
    EMPTYING("emptying");

    private final String name;

    FluidState(String name) {
        this.name = name;
    }

    public static final Codec<FluidState> CODEC = StringIdentifiable.createCodec(FluidState::values);

    @Override
    public String asString() {
        return this.name;
    }
}