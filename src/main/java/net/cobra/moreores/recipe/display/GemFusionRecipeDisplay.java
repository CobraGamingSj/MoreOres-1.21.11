package net.cobra.moreores.recipe.display;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.display.RecipeDisplay;
import net.minecraft.recipe.display.SlotDisplay;

public record GemFusionRecipeDisplay(SlotDisplay ingredientBefore, SlotDisplay ingredientAfter, SlotDisplay result, SlotDisplay workStation) implements RecipeDisplay {

    @Override
    public SlotDisplay craftingStation() {
        return this.workStation;
    }

    public static final MapCodec<GemFusionRecipeDisplay> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                            SlotDisplay.CODEC.fieldOf("ingredientBefore").forGetter(GemFusionRecipeDisplay::ingredientBefore),
                            SlotDisplay.CODEC.fieldOf("ingredientAfter").forGetter(GemFusionRecipeDisplay::ingredientAfter),
                            SlotDisplay.CODEC.fieldOf("result").forGetter(GemFusionRecipeDisplay::result),
                            SlotDisplay.CODEC.fieldOf("work_station").forGetter(GemFusionRecipeDisplay::workStation)
                    )
                    .apply(instance, GemFusionRecipeDisplay::new)
    );
    public static final PacketCodec<RegistryByteBuf, GemFusionRecipeDisplay> PACKET_CODEC = PacketCodec.tuple(
            SlotDisplay.PACKET_CODEC,
            GemFusionRecipeDisplay::ingredientBefore,
            SlotDisplay.PACKET_CODEC,
            GemFusionRecipeDisplay::ingredientAfter,
            SlotDisplay.PACKET_CODEC,
            GemFusionRecipeDisplay::result,
            SlotDisplay.PACKET_CODEC,
            GemFusionRecipeDisplay::workStation,
            GemFusionRecipeDisplay::new
    );

    public static final Serializer<GemFusionRecipeDisplay> SERIALIZER = new Serializer<>(CODEC, PACKET_CODEC);

    @Override
    public Serializer<GemFusionRecipeDisplay> serializer() {
        return SERIALIZER;
    }
}
