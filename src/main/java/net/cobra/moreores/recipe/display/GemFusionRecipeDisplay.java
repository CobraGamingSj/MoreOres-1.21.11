package net.cobra.moreores.recipe.display;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.display.RecipeDisplay;
import net.minecraft.recipe.display.SlotDisplay;

public record GeminfusionRecipeDisplay(SlotDisplay ingredientBefore, SlotDisplay ingredientAfter, SlotDisplay result, SlotDisplay workStation) implements RecipeDisplay {

    @Override
    public SlotDisplay craftingStation() {
        return this.workStation;
    }

    public static final MapCodec<GeminfusionRecipeDisplay> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                            SlotDisplay.CODEC.fieldOf("ingredientBefore").forGetter(GeminfusionRecipeDisplay::ingredientBefore),
                            SlotDisplay.CODEC.fieldOf("ingredientAfter").forGetter(GeminfusionRecipeDisplay::ingredientAfter),
                            SlotDisplay.CODEC.fieldOf("result").forGetter(GeminfusionRecipeDisplay::result),
                            SlotDisplay.CODEC.fieldOf("work_station").forGetter(GeminfusionRecipeDisplay::workStation)
                    )
                    .apply(instance, GeminfusionRecipeDisplay::new)
    );
    public static final PacketCodec<RegistryByteBuf, GeminfusionRecipeDisplay> PACKET_CODEC = PacketCodec.tuple(
            SlotDisplay.PACKET_CODEC,
            GeminfusionRecipeDisplay::ingredientBefore,
            SlotDisplay.PACKET_CODEC,
            GeminfusionRecipeDisplay::ingredientAfter,
            SlotDisplay.PACKET_CODEC,
            GeminfusionRecipeDisplay::result,
            SlotDisplay.PACKET_CODEC,
            GeminfusionRecipeDisplay::workStation,
            GeminfusionRecipeDisplay::new
    );

    public static final Serializer<GeminfusionRecipeDisplay> SERIALIZER = new Serializer<>(CODEC, PACKET_CODEC);

    @Override
    public Serializer<GeminfusionRecipeDisplay> serializer() {
        return SERIALIZER;
    }
}
