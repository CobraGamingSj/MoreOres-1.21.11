package org.cobra.moreores.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.*;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.recipe.display.RecipeDisplay;
import net.minecraft.recipe.display.SlotDisplay;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.World;
import org.cobra.moreores.block.ModBlocks;
import org.cobra.moreores.recipe.book.ModRecipeBookCategories;
import org.cobra.moreores.recipe.display.GemCrystallizingRecipeDisplay;
import org.cobra.moreores.recipe.input.GemCrystallizerRecipeInput;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class GemCrystallizerRecipe implements Recipe<GemCrystallizerRecipeInput> {
    public final Ingredient ingredientBefore;
    public final Ingredient ingredientAfter;
    public final ItemStack output;

    @Nullable
    private IngredientPlacement ingredientPlacement;

    public GemCrystallizerRecipe(Ingredient ingredientBefore, Ingredient ingredientAfter, ItemStack result) {
        this.ingredientBefore = ingredientBefore;
        this.ingredientAfter = ingredientAfter;
        this.output = result;
    }

    @Override
    public ItemStack craft(GemCrystallizerRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        return this.output.copy();
    }

    public ItemStack getResult() {
        return this.output;
    }

    public Ingredient getIngredientBefore() {
        return ingredientBefore;
    }

    public Ingredient getIngredientAfter() {
        return ingredientAfter;
    }

    @Override
    public boolean matches(GemCrystallizerRecipeInput input, World world) {
        if (world.isClient()) return false;
        return this.ingredientBefore.test(input.inputBefore()) && this.ingredientAfter.test(input.inputAfter()) ||
                this.ingredientAfter.test(input.inputBefore()) && this.ingredientBefore.test(input.inputAfter());
    }

    @Override
    public RecipeSerializer<? extends Recipe<GemCrystallizerRecipeInput>> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<? extends Recipe<GemCrystallizerRecipeInput>> getType() {
        return Type.INSTANCE;
    }

    @Override
    public List<RecipeDisplay> getDisplays() {
        return List.of(
                new GemCrystallizingRecipeDisplay(
                        Ingredient.toDisplay(Optional.of(this.ingredientBefore)),
                        Ingredient.toDisplay(Optional.of(this.ingredientAfter)),
                        new SlotDisplay.StackSlotDisplay(this.output),
                        new SlotDisplay.ItemSlotDisplay(ModBlocks.GEM_CRYSTALLIZER_BLOCK.asItem())
                )
        );
    }

    @Override
    public IngredientPlacement getIngredientPlacement() {
        if (this.ingredientPlacement == null) {
            this.ingredientPlacement = IngredientPlacement.forMultipleSlots(List.of(Optional.of(this.ingredientBefore), Optional.of(this.ingredientAfter)));
        }
        return this.ingredientPlacement;
    }

    @Override
    public RecipeBookCategory getRecipeBookCategory() {
        return ModRecipeBookCategories.GEM_CRYSTALLIZER;
    }

    public List<Ingredient> getIngredients() {
        return List.of(ingredientBefore, ingredientAfter);
    }

    public static class Type implements RecipeType<GemCrystallizerRecipe> {

        //RECIPE PROPERTIES
        public static final Type INSTANCE = new Type();
        public static final String ID = "gem_crystallizing"; //Recipe ID
    }

    public static class Serializer implements RecipeSerializer<GemCrystallizerRecipe> {

        //RECIPE PROPERTIES
        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "gem_crystallizing"; //Recipe ID

        //CODEC
        private static final MapCodec<GemCrystallizerRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                Ingredient.CODEC.fieldOf("gemBefore").forGetter(GemCrystallizerRecipe::getIngredientBefore),
                Ingredient.CODEC.fieldOf("gemAfter").forGetter(GemCrystallizerRecipe::getIngredientAfter),
                ItemStack.VALIDATED_CODEC.fieldOf("infusedGem").forGetter(GemCrystallizerRecipe::getResult)
        ).apply(instance, GemCrystallizerRecipe::new));

        @Override
        public MapCodec<GemCrystallizerRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, GemCrystallizerRecipe> packetCodec() {
            return PacketCodec.ofStatic(Serializer::write, Serializer::read);
        }

        private static void write(RegistryByteBuf buf, GemCrystallizerRecipe recipe) {
            Ingredient.PACKET_CODEC.encode(buf, recipe.getIngredientBefore());
            Ingredient.PACKET_CODEC.encode(buf, recipe.getIngredientAfter());
            ItemStack.PACKET_CODEC.encode(buf, recipe.getResult());
        }

        private static GemCrystallizerRecipe read(RegistryByteBuf buf) {
            Ingredient ingredientBefore = Ingredient.PACKET_CODEC.decode(buf);
            Ingredient ingredientAfter = Ingredient.PACKET_CODEC.decode(buf);
            ItemStack result = ItemStack.PACKET_CODEC.decode(buf);
            return new GemCrystallizerRecipe(ingredientBefore, ingredientAfter, result);
        }
    }
}
