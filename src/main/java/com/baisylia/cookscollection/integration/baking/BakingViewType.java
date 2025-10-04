package com.baisylia.cookscollection.integration.baking;

import com.baisylia.cookscollection.CooksCollection;
import com.baisylia.cookscollection.block.ModBlocks;
import com.baisylia.cookscollection.item.ModItems;
import de.crafty.eiv.common.api.recipe.IEivRecipeViewType;
import de.crafty.eiv.common.recipe.inventory.RecipeViewMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class BakingViewType implements IEivRecipeViewType {

    public static final BakingViewType INSTANCE = new BakingViewType();

    @Override
    public Component getDisplayName() {
        return Component.translatable("recipe.cookscollection.shapeless_baking");
    }

    @Override
    public int getDisplayWidth() {
        return 120;
    }

    @Override
    public int getDisplayHeight() {
        return 60;
    }

    @Override
    public ResourceLocation getGuiTexture() {
        return CooksCollection.locate("textures/gui/oven_gui_eiv.png");
    }

    @Override
    public int getSlotCount() {
        return 10;
    }

    @Override
    public void placeSlots(RecipeViewMenu.SlotDefinition builder) {
        builder.addItemSlot(0, 2, 4);
        builder.addItemSlot(1, 20, 4);
        builder.addItemSlot(2, 38, 4);
        builder.addItemSlot(3, 2, 22);
        builder.addItemSlot(4, 20, 22);
        builder.addItemSlot(5, 38, 22);
        builder.addItemSlot(6, 2, 40);
        builder.addItemSlot(7, 20, 40);
        builder.addItemSlot(8, 38, 40);
        builder.addItemSlot(9, 93, 22);
    }

    @Override
    public ResourceLocation getId() {
        return CooksCollection.locate("baking");
    }

    @Override
    public ItemStack getIcon() {
        return ModBlocks.OVEN.get().asItem().getDefaultInstance();
    }

    @Override
    public List<ItemStack> getCraftReferences() {
        return List.of(ModBlocks.OVEN.get().asItem().getDefaultInstance());
    }

}
