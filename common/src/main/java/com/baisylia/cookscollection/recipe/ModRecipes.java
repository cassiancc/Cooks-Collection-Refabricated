package com.baisylia.cookscollection.recipe;

import com.baisylia.cookscollection.RegUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.function.Supplier;

import static com.baisylia.cookscollection.CooksCollection.MOD_ID;

public class ModRecipes {
    public static final Supplier<RecipeType<OvenRecipe>> BAKING = RegUtils.regRecipe("baking", () -> registerRecipeType("baking"));
    public static final Supplier<RecipeType<OvenShapedRecipe>> BAKING_SHAPED = RegUtils.regRecipe("baking_shaped", () -> registerRecipeType("baking_shaped"));

    public static final Supplier<RecipeSerializer<?>> BAKING_SERIALIZER = RegUtils.regRecipeSerializer("baking", OvenRecipe.Serializer::new);
    public static final Supplier<RecipeSerializer<?>> BAKING_SHAPED_SERIALIZER = RegUtils.regRecipeSerializer("baking_shaped", OvenShapedRecipe.Serializer::new);

    public static <T extends Recipe<?>> RecipeType<T> registerRecipeType(final String identifier) {
        return new RecipeType<T>() {
            public String toString() {
                return MOD_ID + ":" + identifier;
            }
        };
    }

    public static void register() {
    }
}