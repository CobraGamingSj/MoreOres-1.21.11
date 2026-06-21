package org.cobra.moreores.block.entity.gem;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringIdentifiable;

public enum MachineStatus implements StringIdentifiable {
    IDLE("idle"),
    RUNNING("running"),
    PAUSED("paused");

    private final String name;

    MachineStatus(String name) {
        this.name = name;
    }

    public static final Codec<MachineStatus> CODEC = StringIdentifiable.createCodec(MachineStatus::values);

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
