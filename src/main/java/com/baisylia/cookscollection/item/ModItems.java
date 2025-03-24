package com.baisylia.cookscollection.item;

import com.baisylia.cookscollection.CooksCollection;
import com.baisylia.cookscollection.item.custom.OilItem;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.item.ConsumableItem;
import vectorwing.farmersdelight.common.item.DrinkableItem;
//import vectorwing.farmersdelight.common.item.DrinkableItem;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(CooksCollection.MOD_ID);

    public static Item.Properties bowlFoodItem(FoodProperties food) {
        return (new Item.Properties()).food(food).craftRemainder(Items.BOWL).stacksTo(16);
    }
    public static Item.Properties drinkItem() {
        return (new Item.Properties()).craftRemainder(Items.GLASS_BOTTLE).stacksTo(16);
    }


    public static final DeferredItem<Item> LEMON = ITEMS.register("lemon",
            () -> new Item(new Item.Properties().food(ModFoods.LEMON)));

    public static final DeferredItem<Item> SALT = ITEMS.register("salt",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> COOKING_OIL = ITEMS.register("cooking_oil",
            () -> new OilItem(drinkItem().food(ModFoods.COOKING_OIL)));

    public static final DeferredItem<Item> CHOCOLATE_MUFFIN = ITEMS.register("chocolate_muffin",
            () -> new Item(new Item.Properties().food(ModFoods.CHOCOLATE_MUFFIN)));

    public static final DeferredItem<Item> LEMON_MUFFIN = ITEMS.register("lemon_muffin",
            () -> new Item(new Item.Properties().food(ModFoods.LEMON_MUFFIN)));

    public static final DeferredItem<Item> FRIED_POTATO = ITEMS.register("fried_potato",
            () -> new Item(new Item.Properties().food(ModFoods.FRIED_POTATO)));

    public static final DeferredItem<Item> LEMONADE = ITEMS.register("lemonade",
            () -> new DrinkableItem(drinkItem().food(ModFoods.LEMONADE)));

    public static final DeferredItem<Item> RUSTIC_LOAF_SLICE = ITEMS.register("rustic_loaf_slice",
            () -> new Item(new Item.Properties().food(ModFoods.RUSTIC_LOAF_SLICE)));

    public static final DeferredItem<Item> FISH_AND_CHIPS = ITEMS.register("fish_and_chips",
            () -> new ConsumableItem(bowlFoodItem(ModFoods.FISH_AND_CHIPS), true));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
