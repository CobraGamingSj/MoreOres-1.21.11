package net.cobra.moreores.recipe.display;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.display.RecipeDisplay;
import net.minecraft.recipe.display.SlotDisplay;

public record GemInfusionRecipeDisplay(SlotDisplay ingredientBefore, SlotDisplay ingredientAfter, SlotDisplay result, SlotDisplay workStation) implements RecipeDisplay {

    @Override
    public SlotDisplay craftingStation() {
        return this.workStation;
    }

    public static final MapCodec<GemInfusionRecipeDisplay> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                            SlotDisplay.CODEC.fieldOf("ingredientBefore").forGetter(GemInfusionRecipeDisplay::ingredientBefore),
                            SlotDisplay.CODEC.fieldOf("ingredientAfter").forGetter(GemInfusionRecipeDisplay::ingredientAfter),
                            SlotDisplay.CODEC.fieldOf("result").forGetter(GemInfusionRecipeDisplay::result),
                            SlotDisplay.CODEC.fieldOf("work_station").forGetter(GemInfusionRecipeDisplay::workStation)
                    )
                    .apply(instance, GemInfusionRecipeDisplay::new)
    );
    public static final PacketCodec<RegistryByteBuf, GemInfusionRecipeDisplay> PACKET_CODEC = PacketCodec.tuple(
            SlotDisplay.PACKET_CODEC,
            GemInfusionRecipeDisplay::ingredientBefore,
            SlotDisplay.PACKET_CODEC,
            GemInfusionRecipeDisplay::ingredientAfter,
            SlotDisplay.PACKET_CODEC,
            GemInfusionRecipeDisplay::result,
            SlotDisplay.PACKET_CODEC,
            GemInfusionRecipeDisplay::workStation,
            GemInfusionRecipeDisplay::new
    );

    public static final Serializer<GemInfusionRecipeDisplay> SERIALIZER = new Serializer<>(CODEC, PACKET_CODEC);

    @Override
    public Serializer<GemInfusionRecipeDisplay> serializer() {
        return SERIALIZER;
    }
}
