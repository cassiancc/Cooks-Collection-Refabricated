package com.baisylia.cookscollection.integration.baking;

import de.crafty.eiv.common.api.recipe.IEivRecipeViewType;
import de.crafty.eiv.common.api.recipe.IEivViewRecipe;
import de.crafty.eiv.common.builtin.BuiltInEivIntegration;
import de.crafty.eiv.common.recipe.inventory.RecipeViewMenu;
import de.crafty.eiv.common.recipe.inventory.SlotContent;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class BakingViewRecipe implements IEivViewRecipe {
    private final ItemStack result;
    private final List<Ingredient> ingredients;
    private final int cookTime;

    @Override
    public IEivRecipeViewType getViewType() {
        return BakingViewType.INSTANCE;
    }

    public BakingViewRecipe(BakingServerRecipe modRecipe) {
        this.ingredients = modRecipe.getIngredients();
        this.cookTime = modRecipe.getCookTime();
        this.result = modRecipe.getResult();
    }

    @Override
    public void bindSlots(RecipeViewMenu.SlotFillContext slotFillContext) {
        AtomicInteger slotID = new AtomicInteger();
        getIngredients().forEach((ingredient -> {
            slotFillContext.bindOptionalSlot(slotID.getAndIncrement(), ingredient, RecipeViewMenu.OptionalSlotRenderer.DEFAULT);
        }));
        while (slotID.get() < 9) {
            slotFillContext.bindOptionalSlot(slotID.getAndIncrement(), SlotContent.of(ItemStack.EMPTY), RecipeViewMenu.OptionalSlotRenderer.DEFAULT);
        }
        slotFillContext.bindOptionalSlot(9, SlotContent.of(result), OptionalSlotRenderer.NONE);
    }

    public interface OptionalSlotRenderer {
        RecipeViewMenu.OptionalSlotRenderer NONE = (guiGraphics, mouseX, mouseY, partialTicks) -> {};
    }

    @Override
    public List<SlotContent> getIngredients() {
        return ingredients.stream().map(SlotContent::of).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<SlotContent> getResults() {
        return List.of(SlotContent.of(result));
    }
}
