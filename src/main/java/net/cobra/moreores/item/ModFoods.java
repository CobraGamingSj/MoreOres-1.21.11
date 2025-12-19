package net.cobra.moreores.item;

import net.minecraft.component.type.FoodComponent;

public class ModFoods {

    public static final FoodComponent PINEAPPLE = new FoodComponent.Builder()
            .nutrition(6).saturationModifier(3f).build();

    public static final FoodComponent TOMATO  = new FoodComponent.Builder()
            .nutrition(8).saturationModifier(4f).build();

    public static final FoodComponent DIAMOND_APPLE = new FoodComponent.Builder()
            .nutrition(16).saturationModifier(8f).build();
}