package net.cobra.moreores.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.cobra.moreores.block.ModBlocks;
import net.cobra.moreores.recipe.book.ModRecipeBookCategories;
import net.cobra.moreores.recipe.display.GemPolishingRecipeDisplay;
import net.cobra.moreores.recipe.input.GemPurifyingRecipeInput;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.*;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.recipe.display.RecipeDisplay;
import net.minecraft.recipe.display.SlotDisplay;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class GemPurifierRecipe implements Recipe<GemPurifyingRecipeInput> {
    public final Ingredient ingredient;
    public final ItemStack output;

    @Nullable
    private IngredientPlacement ingredientPlacement;

    public GemPurifierRecipe(Ingredient ingredient, ItemStack result) {
        this.ingredient = ingredient;
        this.output = result;
    }

    @Override
    public ItemStack craft(GemPurifyingRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        return this.output.copy();
    }

    public ItemStack getResult() {
        return this.output;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    @Override
    public boolean matches(GemPurifyingRecipeInput input, World world) {
        if (world.isClient()) return false;
        return this.ingredient.test(input.inputStack());
    }

    @Override
    public RecipeSerializer<? extends Recipe<GemPurifyingRecipeInput>> getSerializer() {
        return Serializer.GEM_POLISHING;
    }

    @Override
    public RecipeType<? extends Recipe<GemPurifyingRecipeInput>> getType() {
        return Type.GEM_POLISHING;
    }

    @Override
    public List<RecipeDisplay> getDisplays() {
        return List.of(
                new GemPolishingRecipeDisplay(
                        Ingredient.toDisplay(Optional.of(this.ingredient)),
                        new SlotDisplay.StackSlotDisplay(this.output),
                        new SlotDisplay.ItemSlotDisplay(ModBlocks.GEM_PURIFIER_BLOCK.asItem())
                )
        );
    }

    @Override
    public IngredientPlacement getIngredientPlacement() {
        if (this.ingredientPlacement == null) {
            this.ingredientPlacement = IngredientPlacement.forSingleSlot(this.ingredient);
        }
        return this.ingredientPlacement;
    }

    @Override
    public RecipeBookCategory getRecipeBookCategory() {
        return ModRecipeBookCategories.GEM_POLISHING;
    }

    public Ingredient getIngredients() {
        return this.ingredient;
    }

    public static class Type implements RecipeType<GemPurifierRecipe> {

        //RECIPE PROPERTIES
        private Type() {}
        public static final Type GEM_POLISHING = new Type();
        public static final String ID = "gem_polishing"; //Recipe ID
    }

    public static class Serializer implements RecipeSerializer<GemPurifierRecipe> {

        //RECIPE PROPERTIES
        public static final Serializer GEM_POLISHING = new Serializer();
        public static final String ID = "gem_polishing"; //Recipe ID

        //CODEC
        private static final MapCodec<GemPurifierRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                Ingredient.CODEC.fieldOf("ingredientGem").forGetter(GemPurifierRecipe::getIngredient),
                ItemStack.VALIDATED_CODEC.fieldOf("resultGem").forGetter(GemPurifierRecipe::getResult)
        ).apply(instance, GemPurifierRecipe::new));

        @Override
        public MapCodec<GemPurifierRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, GemPurifierRecipe> packetCodec() {
            return PacketCodec.ofStatic(Serializer::write, Serializer::read);
        }

        private static void write(RegistryByteBuf buf, GemPurifierRecipe recipe) {
            Ingredient.PACKET_CODEC.encode(buf, recipe.getIngredient());
            ItemStack.PACKET_CODEC.encode(buf, recipe.getResult());
        }

        private static GemPurifierRecipe read(RegistryByteBuf buf) {
            Ingredient ingredient = Ingredient.PACKET_CODEC.decode(buf);
            ItemStack result = ItemStack.PACKET_CODEC.decode(buf);
            return new GemPurifierRecipe(ingredient, result);
        }
    }
}
