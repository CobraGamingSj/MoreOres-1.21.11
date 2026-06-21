package org.cobra.moreores.block.entity.gem;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringIdentifiable;

public enum MachineEnergyState implements StringIdentifiable {
    IDLE("idle"),
    INSERTING("inserting"),
    EXTRACTING("extracting");

    private final String name;

    MachineEnergyState(String name) {
        this.name = name;
    }

    public static final Codec<MachineEnergyState> CODEC = StringIdentifiable.createCodec(MachineEnergyState::values);

    @Override
    public String asString() {
        return this.name;
    }
}
