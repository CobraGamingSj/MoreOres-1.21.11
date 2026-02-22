package net.cobra.moreores.block.entity.gem_polisher;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringIdentifiable;

public enum EnergyState implements StringIdentifiable {
    IDLE("idle"),
    INSERTING("inserting"),
    EXTRACTING("extracting");

    private final String name;

    EnergyState(String name) {
        this.name = name;
    }

    public static final Codec<EnergyState> CODEC = StringIdentifiable.createCodec(EnergyState::values);

    @Override
    public String asString() {
        return this.name;
    }
}
