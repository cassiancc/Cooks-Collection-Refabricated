package com.ncpbails.cookscollection.item;

import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties LEMON = (new FoodProperties.Builder()).nutrition(1).saturationModifier(0.3F).build();
    public static final FoodProperties LEMONADE = (new FoodProperties.Builder()).nutrition(0).saturationModifier(0.8F).build();
    public static final FoodProperties LEMON_MUFFIN = (new FoodProperties.Builder()).nutrition(5).saturationModifier(0.6F).build();
    public static final FoodProperties CHOCOLATE_MUFFIN = (new FoodProperties.Builder()).nutrition(5).saturationModifier(0.6F).build();
    public static final FoodProperties FRIED_POTATO = (new FoodProperties.Builder()).nutrition(1).saturationModifier(0.3F).build();
    public static final FoodProperties RUSTIC_LOAF_SLICE = (new FoodProperties.Builder()).nutrition(2).saturationModifier(0.3F).build();

}
