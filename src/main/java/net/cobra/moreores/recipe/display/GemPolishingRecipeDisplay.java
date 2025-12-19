package net.cobra.moreores.recipe.display;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.display.RecipeDisplay;
import net.minecraft.recipe.display.SlotDisplay;

public record GemPolishingRecipeDisplay(SlotDisplay ingredient, SlotDisplay result, SlotDisplay workStation) implements RecipeDisplay {

    @Override
    public SlotDisplay craftingStation() {
        return this.workStation;
    }

    public static final MapCodec<GemPolishingRecipeDisplay> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                            SlotDisplay.CODEC.fieldOf("ingredient").forGetter(GemPolishingRecipeDisplay::ingredient),
                            SlotDisplay.CODEC.fieldOf("result").forGetter(GemPolishingRecipeDisplay::result),
                            SlotDisplay.CODEC.fieldOf("work_station").forGetter(GemPolishingRecipeDisplay::workStation)
                    )
                    .apply(instance, GemPolishingRecipeDisplay::new)
    );
    public static final PacketCodec<RegistryByteBuf, GemPolishingRecipeDisplay> PACKET_CODEC = PacketCodec.tuple(
            SlotDisplay.PACKET_CODEC,
            GemPolishingRecipeDisplay::ingredient,
            SlotDisplay.PACKET_CODEC,
            GemPolishingRecipeDisplay::result,
            SlotDisplay.PACKET_CODEC,
            GemPolishingRecipeDisplay::workStation,
            GemPolishingRecipeDisplay::new
    );

    public static final Serializer<GemPolishingRecipeDisplay> SERIALIZER = new Serializer<>(CODEC, PACKET_CODEC);

    @Override
    public Serializer<GemPolishingRecipeDisplay> serializer() {
        return SERIALIZER;
    }
}
