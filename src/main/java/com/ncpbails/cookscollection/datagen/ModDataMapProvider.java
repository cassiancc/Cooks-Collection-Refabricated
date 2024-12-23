package com.ncpbails.cookscollection.datagen;

import com.ncpbails.cookscollection.block.ModBlocks;
import com.ncpbails.cookscollection.item.ModItems;
import net.minecraft.core.HolderLookup;
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
        this.builder(NeoForgeDataMaps.FURNACE_FUELS)
                .add(ModBlocks.LEMON_SAPLING.getId(), new FurnaceFuel(50), false)
                .add(ModBlocks.LEMON_LOG.getId(), new FurnaceFuel(50), false)
                .add(ModBlocks.LEMON_WOOD.getId(), new FurnaceFuel(50), false);

        this.builder(NeoForgeDataMaps.COMPOSTABLES)
                .add(ModItems.LEMON.getId(), new Compostable(0.25f), false)
                .add(ModItems.FRIED_POTATO.getId(), new Compostable(0.45f), false);
    }
}