package com.baisylia.cookscollection.item;

import com.baisylia.cookscollection.refabricated.RegUtils;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.Consumable;
import vectorwing.farmersdelight.common.item.ConsumableItem;

import java.util.function.Supplier;

import static com.baisylia.cookscollection.CooksCollection.MOD_ID;
//import vectorwing.farmersdelight.common.item.DrinkableItem;

public class ModItems {

    public static Item.Properties bowlFoodItem(FoodProperties food, Consumable consumable, String id) {
        return (properties(id)).food(food).craftRemainder(Items.BOWL).stacksTo(16);
    }
    public static Item.Properties drinkItem(String id) {
        return (properties(id)).component(DataComponents.CONSUMABLE, ModConsumables.OIL).craftRemainder(Items.GLASS_BOTTLE).stacksTo(16);
    }
    
    public static Item.Properties properties(String id) {
        return new Item.Properties().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MOD_ID, id)));
    }

    public static final Supplier<Item> LEMON = RegUtils.regItem("lemon",
            () -> new Item(properties("lemon").food(ModFoods.LEMON)));

    public static final Supplier<Item> SALT = RegUtils.regItem("salt",
            () -> new Item(properties("salt")));

    public static final Supplier<Item> COOKING_OIL = RegUtils.regItem("cooking_oil",
            () -> new Item(drinkItem("cooking_oil").food(ModFoods.COOKING_OIL)));

    public static final Supplier<Item> CHOCOLATE_MUFFIN = RegUtils.regItem("chocolate_muffin",
            () -> new Item(properties("chocolate_muffin").food(ModFoods.CHOCOLATE_MUFFIN)));

    public static final Supplier<Item> LEMON_MUFFIN = RegUtils.regItem("lemon_muffin",
            () -> new Item(properties("lemon_muffin").food(ModFoods.LEMON_MUFFIN)));

    public static final Supplier<Item> FRIED_POTATO = RegUtils.regItem("fried_potato",
            () -> new Item(properties("fried_potato").food(ModFoods.FRIED_POTATO)));

    public static final Supplier<Item> LEMONADE = RegUtils.regItem("lemonade",
            () -> new ConsumableItem(drinkItem("lemonade").food(ModFoods.LEMONADE, ModConsumables.LEMONADE)));

    public static final Supplier<Item> RUSTIC_LOAF_SLICE = RegUtils.regItem("rustic_loaf_slice",
            () -> new Item(properties("rustic_loaf_slice").food(ModFoods.RUSTIC_LOAF_SLICE)));

    public static final Supplier<Item> FISH_AND_CHIPS = RegUtils.regItem("fish_and_chips",
            () -> new ConsumableItem(bowlFoodItem(ModFoods.FISH_AND_CHIPS, ModConsumables.FISH_AND_CHIPS, "fish_and_chips"), true));

    public static void register() {
    }
}
