package com.baisylia.cookscollection.recipe;

import dev.architectury.registry.registries.DeferredRegister;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.function.Supplier;

import static com.baisylia.cookscollection.CooksCollection.MOD_ID;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(MOD_ID, Registries.RECIPE_SERIALIZER);
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(MOD_ID, Registries.RECIPE_TYPE);;

    public static final Supplier<RecipeType<OvenRecipe>> BAKING = RECIPE_TYPES.register("baking", () -> registerRecipeType("baking"));
    public static final Supplier<RecipeType<OvenShapedRecipe>> BAKING_SHAPED = RECIPE_TYPES.register("baking_shaped", () -> registerRecipeType("baking_shaped"));

    public static final Supplier<RecipeSerializer<?>> BAKING_SERIALIZER = SERIALIZERS.register("baking", OvenRecipe.Serializer::new);
    public static final Supplier<RecipeSerializer<?>> BAKING_SHAPED_SERIALIZER = SERIALIZERS.register("baking_shaped", OvenShapedRecipe.Serializer::new);

    public static <T extends Recipe<?>> RecipeType<T> registerRecipeType(final String identifier) {
        return new RecipeType<T>() {
            public String toString() {
                return MOD_ID + ":" + identifier;
            }
        };
    }

    public static void register() {
        RECIPE_TYPES.register();
        SERIALIZERS.register();
    }
}