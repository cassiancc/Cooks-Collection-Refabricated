package com.baisylia.cookscollection.integration.baking;

import com.baisylia.cookscollection.CooksCollection;
import com.baisylia.cookscollection.block.entity.screen.OvenScreen;
import com.baisylia.cookscollection.recipe.OvenRecipe;
import de.crafty.eiv.common.api.recipe.IEivRecipeViewType;
import de.crafty.eiv.common.api.recipe.IEivViewRecipe;
import de.crafty.eiv.common.builtin.BuiltInEivIntegration;
import de.crafty.eiv.common.recipe.inventory.RecipeViewMenu;
import de.crafty.eiv.common.recipe.inventory.RecipeViewScreen;
import de.crafty.eiv.common.recipe.inventory.SlotContent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import vectorwing.farmersdelight.common.utility.ClientRenderUtils;

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
            slotFillContext.bindOptionalSlot(slotID.getAndIncrement(), ingredient, OptionalSlotRenderer.NONE);
        }));
        while (slotID.get() < 9) {
            slotFillContext.bindOptionalSlot(slotID.getAndIncrement(), SlotContent.of(ItemStack.EMPTY), OptionalSlotRenderer.NONE);
        }
        var result = SlotContent.of(this.result);
        result.setType(SlotContent.Type.RESULT);
        slotFillContext.bindOptionalSlot(9, result, OptionalSlotRenderer.NONE);
    }

    @Override
    public void renderRecipe(RecipeViewScreen screen, RecipePosition recipePosition, GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        drawCookTime(cookTime, guiGraphics, 48);
        int i = 9;
        int x = 37;
        int y = 38;
        while (i > 0) {
            guiGraphics.blit(RenderPipelines.GUI_TEXTURED, BuiltInEivIntegration.DEFAULT_SLOT_TEXTURE, x, y, 0, 0, 18, 18, 18, 18);
            i--;
            if (i==3 || i==6) {
                y -=18;
                x = 37;
            } else {
                x -=18;
            }
        }
        if (ClientRenderUtils.isCursorInsideBounds(62, 42, 18, 20, mouseX, mouseY)) {
            guiGraphics.setTooltipForNextFrame(Minecraft.getInstance().font, Component.translatable("container.cookscollection.oven.heated"), recipePosition.left()+mouseX, recipePosition.top()+mouseY);
        }
    }

    protected void drawCookTime(int cookTime, GuiGraphics guiGraphics, int y) {
        if (cookTime > 0) {
            int cookTimeSeconds = cookTime / 20;
            Component timeString = Component.translatable("eiv.cooking.time", cookTimeSeconds);
            Minecraft minecraft = Minecraft.getInstance();
            Font fontRenderer = minecraft.font;
            int stringWidth = fontRenderer.width(timeString);
            guiGraphics.drawString(fontRenderer, timeString, 125 - stringWidth, y, 0xFF808080, false);
        }
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

    @Override
    public boolean supportsItemTransfer() {
        return true;
    }

    @Override
    public void mapRecipeItems(RecipeTransferMap transferMap, AbstractContainerScreen<?> screen) {
        transferMap.linkSlots(0, 36);
        transferMap.linkSlots(1, 37);
        transferMap.linkSlots(2, 38);
        transferMap.linkSlots(3, 39);
        transferMap.linkSlots(4, 40);
        transferMap.linkSlots(5, 41);
        transferMap.linkSlots(6, 42);
        transferMap.linkSlots(7, 43);
        transferMap.linkSlots(8, 44);
    }

    @Override
    public List<Class<? extends AbstractContainerScreen<?>>> getTransferClasses() {
        return List.of(OvenScreen.class);
}

}
