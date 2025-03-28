/*
MIT License

Copyright (c) 2020 vectorwing

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package com.baisylia.cookscollection;

import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import vectorwing.farmersdelight.FarmersDelight;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class RegUtils {

    public static <R, T extends R> Supplier<T> register(String name, Supplier<T> supplier, Registry<R> reg) {
        T object = supplier.get();
        Registry.register(reg, ResourceLocation.fromNamespaceAndPath(CooksCollection.MOD_ID, name), object);
        return () -> object;
    }

    public static <B extends EntityType<?>> Supplier<B> regEntity(String name, Supplier<B> supplier) {
        return register(name, supplier, BuiltInRegistries.ENTITY_TYPE);
    }

    public static <B extends MenuType<?>> Supplier<B> regMenu(String name, Supplier<B> supplier) {
        return register(name, supplier, BuiltInRegistries.MENU);
    }

    public static <B extends PlacementModifierType<?>> Supplier<B> regPlacementMod(String name, Supplier<B> supplier) {
        return register(name, supplier, BuiltInRegistries.PLACEMENT_MODIFIER_TYPE);
    }

    public static <B extends RecipeSerializer<?>> Supplier<B> regRecipeSerializer(String name, Supplier<B> supplier) {
        return register(name, supplier, BuiltInRegistries.RECIPE_SERIALIZER);
    }

    public static <B extends RecipeType<?>> Supplier<B> regRecipe(String name, Supplier<B> supplier) {
        return register(name, supplier, BuiltInRegistries.RECIPE_TYPE);
    }

    public static <B extends ParticleType<?>> Supplier<B> regParticle(String name, Supplier<B> supplier) {
        return register(name, supplier, BuiltInRegistries.PARTICLE_TYPE);
    }

    public static <B extends SoundEvent> Supplier<B> regSound(String name, Supplier<B> supplier) {
        return register(name, supplier, BuiltInRegistries.SOUND_EVENT);
    }

    public static <B extends LootItemFunctionType<?>> Supplier<B> regLootFunction(String name, Supplier<B> supplier) {
        return register(name, supplier, BuiltInRegistries.LOOT_FUNCTION_TYPE);
    }

    public static <B extends Feature<?>> Supplier<B> regFeature(String name, Supplier<B> supplier) {
        return register(name, supplier, BuiltInRegistries.FEATURE);
    }

    public static <B extends BlockEntityType<?>> Supplier<B> regBlockEntity(String name, Supplier<B> supplier) {
        return register(name, supplier, BuiltInRegistries.BLOCK_ENTITY_TYPE);
    }

    public static <B extends CreativeModeTab> Supplier<B> regTab(String name, Supplier<B> supplier) {
        return register(name, supplier, BuiltInRegistries.CREATIVE_MODE_TAB);
    }

    public static <B extends DataComponentType<?>> Supplier<B> regComponent(String name, Supplier<B> supplier) {
        return register(name, supplier, BuiltInRegistries.DATA_COMPONENT_TYPE);
    }

    public static <A> Supplier<DataComponentType<A>> regComponent(String name, Consumer<DataComponentType.Builder<A>> stuff) {
        DataComponentType.Builder<A> builder = DataComponentType.builder();
        stuff.accept(builder);
        return register(name, builder::build, BuiltInRegistries.DATA_COMPONENT_TYPE);
    }

    public static <A> Supplier<DataComponentType<A>> regEnchComponent(String name, Consumer<DataComponentType.Builder<A>> stuff) {
        DataComponentType.Builder<A> builder = DataComponentType.builder();
        stuff.accept(builder);
        return register(name, builder::build, BuiltInRegistries.ENCHANTMENT_EFFECT_COMPONENT_TYPE);
    }

    public static <B extends MobEffect> Supplier<B> regEffect(String name, Supplier<B> supplier) {
        return register(name, supplier, BuiltInRegistries.MOB_EFFECT);
    }

    public static <B extends LootItemFunctionType<?>> Supplier<B> regLootFunc(String name, Supplier<B> supplier) {
        return register(name, supplier, BuiltInRegistries.LOOT_FUNCTION_TYPE);
    }

    public static <B extends Item> Supplier<B> regItem(String name, Supplier<B> supplier) {
        return register(name, supplier, BuiltInRegistries.ITEM);
    }

    public static <B extends Block> Supplier<B> regBlock(String name, Supplier<B> supplier) {
        return register(name, supplier, BuiltInRegistries.BLOCK);
    }

    public static <B extends CriterionTrigger<?>> Supplier<B> regTrigger(String name, Supplier<B> supplier) {
        return register(name, supplier, BuiltInRegistries.TRIGGER_TYPES);
    }

    public static <B extends LootItemConditionType> Supplier<B> regLootCond(String name, Supplier<B> supplier) {
        return register(name, supplier, BuiltInRegistries.LOOT_CONDITION_TYPE);
    }
}