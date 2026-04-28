package net.cobra.moreores.block.entity.gem;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringIdentifiable;

public enum PolishingFusionState implements StringIdentifiable {
    IDLE("idle"),
    RUNNING("running"),
    PAUSED("paused");

    private final String name;

    PolishingFusionState(String name) {
        this.name = name;
    }

    public static final Codec<PolishingFusionState> CODEC = StringIdentifiable.createCodec(PolishingFusionState::values);

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
