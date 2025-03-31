package com.baisylia.cookscollection.tab;

import com.baisylia.cookscollection.CooksCollection;
import com.baisylia.cookscollection.block.ModBlocks;
import com.baisylia.cookscollection.item.ModItems;
import com.baisylia.cookscollection.refabricated.RegUtils;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTabs {


    public static final ResourceKey<CreativeModeTab> COOKSCOLLECTION_TAB_KEY = ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), CooksCollection.locate("cookscollections_tab"));
    public static final CreativeModeTab COOKSCOLLECTION_TAB = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.LEMON.get()))
            .title(Component.translatable("creativetab.cookscollection.cookscollections_tab"))
            .build();

    public static void register() {
        RegUtils.regTab("cookscollections_tab", ()->COOKSCOLLECTION_TAB);

        ItemGroupEvents.modifyEntriesEvent(COOKSCOLLECTION_TAB_KEY).register(output -> {
            output.accept(ModItems.LEMON.get());
            output.accept(ModItems.SALT.get());
            output.accept(ModItems.COOKING_OIL.get());
            output.accept(ModItems.CHOCOLATE_MUFFIN.get());
            output.accept(ModItems.LEMON_MUFFIN.get());
            output.accept(ModItems.FRIED_POTATO.get());
            output.accept(ModItems.FISH_AND_CHIPS.get());
            output.accept(ModItems.LEMONADE.get());
            output.accept(ModBlocks.RUSTIC_LOAF.get());
            output.accept(ModItems.RUSTIC_LOAF_SLICE.get());
            output.accept(ModBlocks.LEMON_SAPLING.get());
            output.accept(ModBlocks.LEMON_LOG.get());
            output.accept(ModBlocks.LEMON_WOOD.get());
            output.accept(ModBlocks.LEMON_LEAVES.get());
            output.accept(ModBlocks.FRUITING_LEMON_LEAVES.get());
            output.accept(ModBlocks.SALTED_DRIPSTONE_BLOCK.get());
            output.accept(ModBlocks.LEMON_CRATE.get());
            output.accept(ModBlocks.OVEN.get());
        });
    }
}
