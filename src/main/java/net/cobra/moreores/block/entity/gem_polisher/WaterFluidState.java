package net.cobra.moreores.block.entity.gem_polisher;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringIdentifiable;

public enum WaterFluidState implements StringIdentifiable {
    IDLE("idle"),
    FILLING("filling"),
    EMPTYING("emptying");

    private final String name;

    WaterFluidState(String name) {
        this.name = name;
    }

    public static final Codec<WaterFluidState> CODEC = StringIdentifiable.createCodec(WaterFluidState::values);

    @Override
    public String asString() {
        return this.name;
    }
}
