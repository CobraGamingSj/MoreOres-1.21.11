package net.cobra.moreores.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.cobra.moreores.block.ModBlocks;
import net.cobra.moreores.recipe.book.ModRecipeBookCategories;
import net.cobra.moreores.recipe.display.GemInfusionRecipeDisplay;
import net.cobra.moreores.recipe.input.GemInfusionRecipeInput;
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

public class GemInfusionRecipe implements Recipe<GemInfusionRecipeInput> {
    public final Ingredient ingredientBefore;
    public final Ingredient ingredientAfter;
    public final ItemStack output;

    @Nullable
    private IngredientPlacement ingredientPlacement;

    public GemInfusionRecipe(Ingredient ingredientBefore, Ingredient ingredientAfter, ItemStack result) {
        this.ingredientBefore = ingredientBefore;
        this.ingredientAfter = ingredientAfter;
        this.output = result;
    }

    @Override
    public ItemStack craft(GemInfusionRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
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
    public boolean matches(GemInfusionRecipeInput input, World world) {
        if (world.isClient()) return false;
        return this.ingredientBefore.test(input.inputBefore()) && this.ingredientAfter.test(input.inputAfter());
    }

    @Override
    public RecipeSerializer<? extends Recipe<GemInfusionRecipeInput>> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<? extends Recipe<GemInfusionRecipeInput>> getType() {
        return Type.INSTANCE;
    }

    @Override
    public List<RecipeDisplay> getDisplays() {
        return List.of(
                new GemInfusionRecipeDisplay(
                        Ingredient.toDisplay(Optional.of(this.ingredientBefore)),
                        Ingredient.toDisplay(Optional.of(this.ingredientAfter)),
                        new SlotDisplay.StackSlotDisplay(this.output),
                        new SlotDisplay.ItemSlotDisplay(ModBlocks.GEM_INFUSION_BLOCK.asItem())
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
        return ModRecipeBookCategories.GEM_INFUSION;
    }

    public List<Ingredient> getIngredients() {
        return List.of(ingredientBefore, ingredientAfter);
    }

    public static class Type implements RecipeType<GemInfusionRecipe> {

        //RECIPE PROPERTIES
        public static final Type INSTANCE = new Type();
        public static final String ID = "gem_infusing"; //Recipe ID
    }

    public static class Serializer implements RecipeSerializer<GemInfusionRecipe> {

        //RECIPE PROPERTIES
        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "gem_infusing"; //Recipe ID

        //CODEC
        private static final MapCodec<GemInfusionRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                Ingredient.CODEC.fieldOf("gemBefore").forGetter(GemInfusionRecipe::getIngredientBefore),
                Ingredient.CODEC.fieldOf("gemAfter").forGetter(GemInfusionRecipe::getIngredientAfter),
                ItemStack.VALIDATED_CODEC.fieldOf("infusedGem").forGetter(GemInfusionRecipe::getResult)
        ).apply(instance, GemInfusionRecipe::new));

        @Override
        public MapCodec<GemInfusionRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, GemInfusionRecipe> packetCodec() {
            return PacketCodec.ofStatic(Serializer::write, Serializer::read);
        }

        private static void write(RegistryByteBuf buf, GemInfusionRecipe recipe) {
            Ingredient.PACKET_CODEC.encode(buf, recipe.getIngredientBefore());
            Ingredient.PACKET_CODEC.encode(buf, recipe.getIngredientAfter());
            ItemStack.PACKET_CODEC.encode(buf, recipe.getResult());
        }

        private static GemInfusionRecipe read(RegistryByteBuf buf) {
            Ingredient ingredientBefore = Ingredient.PACKET_CODEC.decode(buf);
            Ingredient ingredientAfter = Ingredient.PACKET_CODEC.decode(buf);
            ItemStack result = ItemStack.PACKET_CODEC.decode(buf);
            return new GemInfusionRecipe(ingredientBefore, ingredientAfter, result);
        }
    }
}
