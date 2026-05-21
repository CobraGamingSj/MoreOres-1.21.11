package org.cobra.moreores.recipe.display;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.display.RecipeDisplay;
import net.minecraft.recipe.display.SlotDisplay;

public record GemCrystallizingRecipeDisplay(SlotDisplay ingredientBefore, SlotDisplay ingredientAfter, SlotDisplay result, SlotDisplay workStation) implements RecipeDisplay {

    @Override
    public SlotDisplay craftingStation() {
        return this.workStation;
    }

    public static final MapCodec<GemCrystallizingRecipeDisplay> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                            SlotDisplay.CODEC.fieldOf("ingredientBefore").forGetter(GemCrystallizingRecipeDisplay::ingredientBefore),
                            SlotDisplay.CODEC.fieldOf("ingredientAfter").forGetter(GemCrystallizingRecipeDisplay::ingredientAfter),
                            SlotDisplay.CODEC.fieldOf("result").forGetter(GemCrystallizingRecipeDisplay::result),
                            SlotDisplay.CODEC.fieldOf("work_station").forGetter(GemCrystallizingRecipeDisplay::workStation)
                    )
                    .apply(instance, GemCrystallizingRecipeDisplay::new)
    );
    public static final PacketCodec<RegistryByteBuf, GemCrystallizingRecipeDisplay> PACKET_CODEC = PacketCodec.tuple(
            SlotDisplay.PACKET_CODEC,
            GemCrystallizingRecipeDisplay::ingredientBefore,
            SlotDisplay.PACKET_CODEC,
            GemCrystallizingRecipeDisplay::ingredientAfter,
            SlotDisplay.PACKET_CODEC,
            GemCrystallizingRecipeDisplay::result,
            SlotDisplay.PACKET_CODEC,
            GemCrystallizingRecipeDisplay::workStation,
            GemCrystallizingRecipeDisplay::new
    );

    public static final Serializer<GemCrystallizingRecipeDisplay> SERIALIZER = new Serializer<>(CODEC, PACKET_CODEC);

    @Override
    public Serializer<GemCrystallizingRecipeDisplay> serializer() {
        return SERIALIZER;
    }
}
