package com.ncpbails.cookscollection.integration;

import com.ncpbails.cookscollection.recipe.ModRecipes;
import com.ncpbails.cookscollection.recipe.OvenRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;

public class CCRecipes {
    private final RecipeManager recipeManager;

    public CCRecipes() {
        Minecraft minecraft = Minecraft.getInstance();
        ClientLevel level = minecraft.level;

        if (level != null) {
            this.recipeManager = level.getRecipeManager();
        } else {
            throw new NullPointerException("minecraft world must not be null.");
        }
    }

    public List<OvenRecipe> getOvenRecipes() {
        return recipeManager.getAllRecipesFor(ModRecipes.BAKING.get()).stream().map(RecipeHolder::value).toList();    }
}
