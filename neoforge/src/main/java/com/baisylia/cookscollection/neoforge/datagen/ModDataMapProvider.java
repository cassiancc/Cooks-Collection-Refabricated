package com.baisylia.cookscollection.neoforge.datagen;

import com.baisylia.cookscollection.block.ModBlocks;
import com.baisylia.cookscollection.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable;
import net.neoforged.neoforge.registries.datamaps.builtin.FurnaceFuel;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;

import java.util.concurrent.CompletableFuture;

public class ModDataMapProvider extends DataMapProvider {
    protected ModDataMapProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather() {
        var LEMON_SAPLING = BuiltInRegistries.BLOCK.getKey(ModBlocks.LEMON_SAPLING.get());
        var LEMON_LOG = BuiltInRegistries.BLOCK.getKey(ModBlocks.LEMON_LOG.get());
        var LEMON_WOOD = BuiltInRegistries.BLOCK.getKey(ModBlocks.LEMON_WOOD.get());
        var LEMON = BuiltInRegistries.ITEM.getKey(ModItems.LEMON.get());
        var FRIED_POTATO = BuiltInRegistries.ITEM.getKey(ModItems.FRIED_POTATO.get());


        this.builder(NeoForgeDataMaps.FURNACE_FUELS)
                .add(LEMON_SAPLING, new FurnaceFuel(50), false)
                .add(LEMON_LOG, new FurnaceFuel(50), false)
                .add(LEMON_WOOD, new FurnaceFuel(50), false);

        this.builder(NeoForgeDataMaps.COMPOSTABLES)
                .add(LEMON, new Compostable(0.25f), false)
                .add(FRIED_POTATO, new Compostable(0.45f), false);
    }
}