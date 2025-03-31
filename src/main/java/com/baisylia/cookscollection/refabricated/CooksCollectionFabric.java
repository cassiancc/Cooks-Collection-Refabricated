package com.baisylia.cookscollection.refabricated;

import com.baisylia.cookscollection.CooksCollection;
import com.baisylia.cookscollection.block.ModBlocks;
import com.baisylia.cookscollection.item.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;

public final class CooksCollectionFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        // Run our common setup.
        CooksCollection.init();
        CompostingChanceRegistry.INSTANCE.add(ModItems.FRIED_POTATO.get(), 0.45f);
        CompostingChanceRegistry.INSTANCE.add(ModItems.LEMON.get(), 0.25f);
        FuelRegistry.INSTANCE.add(ModBlocks.LEMON_LOG.get(), 50);
        FuelRegistry.INSTANCE.add(ModBlocks.LEMON_SAPLING.get(), 50);
        FuelRegistry.INSTANCE.add(ModBlocks.LEMON_WOOD.get(), 50);
    }
}
