package net.cobra.moreores.block.entity.gem;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringIdentifiable;

public enum PolishinginfusionState implements StringIdentifiable {
    IDLE("idle"),
    RUNNING("running"),
    PAUSED("paused");

    private final String name;

    PolishinginfusionState(String name) {
        this.name = name;
    }

    public static final Codec<PolishinginfusionState> CODEC = StringIdentifiable.createCodec(PolishinginfusionState::values);

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
