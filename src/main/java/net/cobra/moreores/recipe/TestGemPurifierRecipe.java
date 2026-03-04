package net.cobra.moreores.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.cobra.moreores.block.ModBlocks;
import net.cobra.moreores.recipe.book.ModRecipeBookCategories;
import net.cobra.moreores.recipe.display.GemPolishingRecipeDisplay;
import net.cobra.moreores.recipe.input.TestGemPurifyingRecipeInput;
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

public class TestGemPurifierRecipe implements Recipe<TestGemPurifyingRecipeInput> {
    public final Ingredient ingredient;
    public final ItemStack output;

    @Nullable
    private IngredientPlacement ingredientPlacement;

    public TestGemPurifierRecipe(Ingredient ingredient, ItemStack result) {
        this.ingredient = ingredient;
        this.output = result;
    }

    @Override
    public ItemStack craft(TestGemPurifyingRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        return this.output.copy();
    }

    public ItemStack getResult() {
        return this.output;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    @Override
    public boolean matches(TestGemPurifyingRecipeInput input, World world) {
        if (world.isClient()) return false;
        return this.ingredient.test(input.firstInputStack()) && this.ingredient.test(input.secondInputStack());
    }

    @Override
    public RecipeSerializer<? extends Recipe<TestGemPurifyingRecipeInput>> getSerializer() {
        return Serializer.GEM_POLISHING;
    }

    @Override
    public RecipeType<? extends Recipe<TestGemPurifyingRecipeInput>> getType() {
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

    public static class Type implements RecipeType<TestGemPurifierRecipe> {

        //RECIPE PROPERTIES
        private Type() {}
        public static final Type GEM_POLISHING = new Type();
        public static final String ID = "gem_polishing"; //Recipe ID
    }

    public static class Serializer implements RecipeSerializer<TestGemPurifierRecipe> {

        //RECIPE PROPERTIES
        public static final Serializer GEM_POLISHING = new Serializer();
        public static final String ID = "gem_polishing"; //Recipe ID

        //CODEC
        private static final MapCodec<TestGemPurifierRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                Ingredient.CODEC.fieldOf("ingredientGem").forGetter(TestGemPurifierRecipe::getIngredient),
                ItemStack.VALIDATED_CODEC.fieldOf("resultGem").forGetter(TestGemPurifierRecipe::getResult)
        ).apply(instance, TestGemPurifierRecipe::new));

        @Override
        public MapCodec<TestGemPurifierRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, TestGemPurifierRecipe> packetCodec() {
            return PacketCodec.ofStatic(Serializer::write, Serializer::read);
        }

        private static void write(RegistryByteBuf buf, TestGemPurifierRecipe recipe) {
            Ingredient.PACKET_CODEC.encode(buf, recipe.getIngredient());
            ItemStack.PACKET_CODEC.encode(buf, recipe.getResult());
        }

        private static TestGemPurifierRecipe read(RegistryByteBuf buf) {
            Ingredient ingredient = Ingredient.PACKET_CODEC.decode(buf);
            ItemStack result = ItemStack.PACKET_CODEC.decode(buf);
            return new TestGemPurifierRecipe(ingredient, result);
        }
    }
}
