package com.ncpbails.cookscollection.recipe;

import com.ncpbails.cookscollection.CooksCollection;
import com.ncpbails.cookscollection.block.entity.screen.OvenMenu;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, CooksCollection.MOD_ID);


    public static final Supplier<RecipeSerializer<?>> BAKING_SERIALIZER = SERIALIZERS.register("baking", OvenRecipe.Serializer::new);

    //public static final Supplier<RecipeSerializer<?>> BAKING_SHAPED_SERIALIZER = SERIALIZERS.register("baking_shaped", OvenShapedRecipe.Serializer::new);


    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}