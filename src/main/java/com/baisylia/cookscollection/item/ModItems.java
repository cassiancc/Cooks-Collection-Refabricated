package com.baisylia.cookscollection.item;

import com.baisylia.cookscollection.refabricated.RegUtils;
import com.baisylia.cookscollection.item.custom.OilItem;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import vectorwing.farmersdelight.common.item.ConsumableItem;
import vectorwing.farmersdelight.common.item.DrinkableItem;

import java.util.function.Supplier;
//import vectorwing.farmersdelight.common.item.DrinkableItem;

public class ModItems {

    public static Item.Properties bowlFoodItem(FoodProperties food) {
        return (properties()).food(food).craftRemainder(Items.BOWL).stacksTo(16);
    }
    public static Item.Properties drinkItem() {
        return (properties()).craftRemainder(Items.GLASS_BOTTLE).stacksTo(16);
    }
    
    public static Item.Properties properties() {
        return new Item.Properties();
    }

    public static final Supplier<Item> LEMON = RegUtils.regItem("lemon",
            () -> new Item(properties().food(ModFoods.LEMON)));

    public static final Supplier<Item> SALT = RegUtils.regItem("salt",
            () -> new Item(properties()));

    public static final Supplier<Item> COOKING_OIL = RegUtils.regItem("cooking_oil",
            () -> new OilItem(drinkItem().food(ModFoods.COOKING_OIL)));

    public static final Supplier<Item> CHOCOLATE_MUFFIN = RegUtils.regItem("chocolate_muffin",
            () -> new Item(properties().food(ModFoods.CHOCOLATE_MUFFIN)));

    public static final Supplier<Item> LEMON_MUFFIN = RegUtils.regItem("lemon_muffin",
            () -> new Item(properties().food(ModFoods.LEMON_MUFFIN)));

    public static final Supplier<Item> FRIED_POTATO = RegUtils.regItem("fried_potato",
            () -> new Item(properties().food(ModFoods.FRIED_POTATO)));

    public static final Supplier<Item> LEMONADE = RegUtils.regItem("lemonade",
            () -> new DrinkableItem(drinkItem().food(ModFoods.LEMONADE)));

    public static final Supplier<Item> RUSTIC_LOAF_SLICE = RegUtils.regItem("rustic_loaf_slice",
            () -> new Item(properties().food(ModFoods.RUSTIC_LOAF_SLICE)));

    public static final Supplier<Item> FISH_AND_CHIPS = RegUtils.regItem("fish_and_chips",
            () -> new ConsumableItem(bowlFoodItem(ModFoods.FISH_AND_CHIPS), true));

    public static void register() {
    }
}
