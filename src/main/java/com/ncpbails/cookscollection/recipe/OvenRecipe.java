package com.ncpbails.cookscollection.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.network.FriendlyByteBuf;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.util.RecipeMatcher;
import net.neoforged.neoforge.items.wrapper.RecipeWrapper;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.registry.ModRecipeSerializers;
import vectorwing.farmersdelight.common.registry.ModRecipeTypes;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OvenRecipe implements Recipe<RecipeWrapper> {
    private final NonNullList<Ingredient> inputItems;
    private final ItemStack output;
    private final ItemStack container;
    private final ItemStack containerOverride;
    private final int cookTime;

    public OvenRecipe(NonNullList<Ingredient> inputItems, ItemStack output, ItemStack container, int cookTime) {
        this.inputItems = inputItems;
        this.output = output;
        if (!container.isEmpty()) {
            this.container = container;
        } else if (!output.getCraftingRemainingItem().isEmpty()) {
            this.container = output.getCraftingRemainingItem();
        } else {
            this.container = ItemStack.EMPTY;
        }

        this.containerOverride = container;
        this.cookTime = cookTime;
    }

    public NonNullList<Ingredient> getIngredients() {
        return this.inputItems;
    }

    public ItemStack getResultItem(HolderLookup.Provider provider) {
        return this.output;
    }

    public ItemStack getResultItemy() {
        return this.output;
    }

    public ItemStack getOutputContainer() {
        return this.container;
    }

    public ItemStack getContainerOverride() {
        return this.containerOverride;
    }

    public ItemStack assemble(RecipeWrapper inv, HolderLookup.Provider provider) {
        return this.output.copy();
    }

    public int getCookTime() {
        return this.cookTime;
    }

    public static class Type implements RecipeType<OvenRecipe> {
        private Type() { }
        public static final Type INSTANCE = new Type();
        public static final String ID = "baking";
    }

    public boolean matches(RecipeWrapper inv, Level level) {
        List<ItemStack> inputs = new ArrayList();
        int i = 0;

        for(int j = 0; j < 6; ++j) {
            ItemStack itemstack = inv.getItem(j);
            if (!itemstack.isEmpty()) {
                ++i;
                inputs.add(itemstack);
            }
        }

        return i == this.inputItems.size() && RecipeMatcher.findMatches(inputs, this.inputItems) != null;
    }

    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= this.inputItems.size();
    }

    public RecipeSerializer<?> getSerializer() {
        return ModRecipeSerializers.COOKING.get();
    }

    public RecipeType<?> getType() {
        return ModRecipeTypes.COOKING.get();
    }

    public ItemStack getToastSymbol() {
        return new ItemStack(ModItems.COOKING_POT.get());
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            OvenRecipe that = (OvenRecipe) o;
            if (this.getCookTime() != that.getCookTime()) {
                return false;
            } else if (!this.inputItems.equals(that.inputItems)) {
                return false;
            } else {
                return !this.output.equals(that.output) ? false : this.container.equals(that.container);
            }
        } else {
            return false;
        }
    }

    public int hashCode() {
        int result = this.inputItems.hashCode();
        result = 31 * result + this.output.hashCode();
        result = 31 * result + this.container.hashCode();
        result = 31 * result + this.getCookTime();
        return result;
    }


    public static class Serializer implements RecipeSerializer<OvenRecipe> {
        private static final MapCodec<OvenRecipe> CODEC = RecordCodecBuilder.mapCodec((inst) -> inst.group(
                Ingredient.LIST_CODEC_NONEMPTY.fieldOf("ingredients").forGetter(OvenRecipe::getIngredients),
                ItemStack.STRICT_CODEC.fieldOf("result").forGetter((r) -> r.output),
                ItemStack.STRICT_CODEC.optionalFieldOf("container", ItemStack.EMPTY).forGetter(OvenRecipe::getContainerOverride),
                Codec.INT.optionalFieldOf("cookingtime", 200).forGetter(OvenRecipe::getCookTime)
        ).apply(inst, (ingredients, output, containerOverride, cookTime) -> new OvenRecipe((NonNullList<Ingredient>) ingredients, output, containerOverride, cookTime)));


        public Serializer() {}

        public MapCodec<OvenRecipe> codec() {
            return CODEC;
        }

        public StreamCodec<RegistryFriendlyByteBuf, OvenRecipe> streamCodec() {
            return StreamCodec.of(OvenRecipe.Serializer::toNetwork, OvenRecipe.Serializer::fromNetwork);
        }

        private static OvenRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
            int i = buffer.readVarInt();
            NonNullList<Ingredient> inputItemsIn = NonNullList.withSize(i, Ingredient.EMPTY);
            inputItemsIn.replaceAll((ignored) -> Ingredient.CONTENTS_STREAM_CODEC.decode(buffer));
            ItemStack outputIn = ItemStack.STREAM_CODEC.decode(buffer);
            ItemStack container = ItemStack.OPTIONAL_STREAM_CODEC.decode(buffer);
            int cookTimeIn = buffer.readVarInt();
            return new OvenRecipe(inputItemsIn, outputIn, container, cookTimeIn);
        }

        private static void toNetwork(RegistryFriendlyByteBuf buffer, OvenRecipe recipe) {
            buffer.writeVarInt(recipe.inputItems.size());
            for (Ingredient ingredient : recipe.inputItems) {
                Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, ingredient);
            }
            ItemStack.STREAM_CODEC.encode(buffer, recipe.output);
            ItemStack.OPTIONAL_STREAM_CODEC.encode(buffer, recipe.container);
            buffer.writeVarInt(recipe.cookTime);
        }
    }
}
