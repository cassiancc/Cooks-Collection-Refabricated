package com.baisylia.cookscollection.integration;

import com.baisylia.cookscollection.integration.baking.BakingServerRecipe;
import com.baisylia.cookscollection.integration.baking.BakingViewRecipe;
import com.baisylia.cookscollection.recipe.ModRecipes;
import de.crafty.eiv.common.api.IExtendedItemViewIntegration;
import de.crafty.eiv.common.api.recipe.ItemView;
import de.crafty.eiv.common.recipe.ServerRecipeManager;

import java.util.Collections;

public class EIVCooksCollectionPlugin implements IExtendedItemViewIntegration {
    @Override
    public void onIntegrationInitialize() {
        ItemView.addRecipeProvider(recipeList -> {
            ServerRecipeManager.INSTANCE.getRecipesForType(ModRecipes.BAKING.get()).forEach(recipe -> {
                recipeList.add(new BakingServerRecipe(recipe.getIngredients(), recipe.getResultItem(), recipe.getCookTime()));
            });
        });

        ItemView.registerRecipeWrapper(BakingServerRecipe.TYPE, modRecipe -> {
            return Collections.singletonList(new BakingViewRecipe(modRecipe));
        });
    }
}
