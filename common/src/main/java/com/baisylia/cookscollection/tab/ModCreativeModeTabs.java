package com.baisylia.cookscollection.tab;

import com.baisylia.cookscollection.CooksCollection;
import com.baisylia.cookscollection.item.ModItems;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTabs {


    public static final ResourceKey<CreativeModeTab> COOKSCOLLECTION_TAB_KEY = ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), CooksCollection.locate("cookscollections_tab"));
    public static final CreativeModeTab COOKSCOLLECTION_TAB  = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.LEMON.get()))
            .title(Component.translatable("creativetab.cookscollection.cookscollections_tab"))
            .build();

    public static void register() {
    }
}
