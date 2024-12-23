package com.ncpbails.cookscollection.item;

import com.ncpbails.cookscollection.CooksCollection;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import vectorwing.farmersdelight.common.item.DrinkableItem;
//import vectorwing.farmersdelight.common.item.DrinkableItem;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(CooksCollection.MOD_ID);


    public static final DeferredItem<Item> LEMON = ITEMS.register("lemon",
            () -> new Item(new Item.Properties().food(ModFoods.LEMON)));

    public static final DeferredItem<Item> SALT = ITEMS.register("salt",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> SUNFLOWER_SEEDS = ITEMS.register("sunflower_seeds",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> COOKING_OIL = ITEMS.register("cooking_oil",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> CHOCOLATE_MUFFIN = ITEMS.register("chocolate_muffin",
            () -> new Item(new Item.Properties().food(ModFoods.CHOCOLATE_MUFFIN)));

    public static final DeferredItem<Item> LEMON_MUFFIN = ITEMS.register("lemon_muffin",
            () -> new Item(new Item.Properties().food(ModFoods.LEMON_MUFFIN)));

    public static final DeferredItem<Item> FRIED_POTATO = ITEMS.register("fried_potato",
            () -> new Item(new Item.Properties().food(ModFoods.FRIED_POTATO)));

    public static final DeferredItem<Item> LEMONADE = ITEMS.register("lemonade",
            () -> new DrinkableItem(new Item.Properties().food(ModFoods.LEMONADE)));

    public static final DeferredItem<Item> RUSTIC_LOAF_SLICE = ITEMS.register("rustic_loaf_slice",
            () -> new Item(new Item.Properties().food(ModFoods.RUSTIC_LOAF_SLICE)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
