package net.cobra.moreores.block.entity;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringIdentifiable;

public enum PolishingState implements StringIdentifiable {
    IDLE("idle"),
    RUNNING("running"),
    PAUSED("paused");

    private final String  name;

    PolishingState(String name) {
        this.name = name;
    }

    public static final Codec<PolishingState> CODEC = StringIdentifiable.createCodec(PolishingState::values);

    public boolean isIdle() {
        return this == IDLE;
    }

    public boolean isRunning() {
        return this == RUNNING;
    }

    public boolean isPaused() {
        return this == PAUSED;
    }

    @Override
    public String asString() {
        return this.name;
    }
}
