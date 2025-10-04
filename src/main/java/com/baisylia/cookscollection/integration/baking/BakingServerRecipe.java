package com.baisylia.cookscollection.integration.baking;

import com.baisylia.cookscollection.CooksCollection;
import de.crafty.eiv.common.api.recipe.EivRecipeType;
import de.crafty.eiv.common.api.recipe.IEivServerRecipe;
import de.crafty.eiv.common.recipe.util.EivTagUtil;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;

public class BakingServerRecipe implements IEivServerRecipe {

    public static final EivRecipeType<BakingServerRecipe> TYPE = EivRecipeType.register(
            CooksCollection.locate("baking"),
            () -> new BakingServerRecipe(null, null, 0)
    );
    private List<Ingredient> ingredients;
    private ItemStack resultItem;
    private int cookTime;

    public BakingServerRecipe(NonNullList<Ingredient> ingredients, ItemStack resultItem, int cookTime) {
        this.ingredients = ingredients;
        this.resultItem = resultItem;
        this.cookTime = cookTime;
    }

    @Override
    public void writeToTag(CompoundTag tag) {
        tag.put("ingredients", EivTagUtil.writeList(this.ingredients, (origin, tag1) -> EivTagUtil.writeIngredient(origin)));
        tag.put("result", EivTagUtil.encodeItemStackOnServer(resultItem));
        tag.putInt("cookingtime", cookTime);
    }

    @Override
    public void loadFromTag(CompoundTag tag) {
        this.ingredients = EivTagUtil.readList(tag, "ingredients", EivTagUtil::readIngredient);
        this.resultItem = EivTagUtil.decodeItemStackOnServer(tag.getCompound("result").orElseGet(CompoundTag::new));
        this.cookTime = tag.getIntOr("cookingtime", 0);
    }

    @Override
    public EivRecipeType<? extends IEivServerRecipe> getRecipeType() {
        return TYPE;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public ItemStack getResult() {
        return resultItem;
    }

    public int getCookTime() {
        return cookTime;
    }
}
