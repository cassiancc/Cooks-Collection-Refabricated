package com.baisylia.cookscollection;

import com.baisylia.cookscollection.block.ModBlocks;
import com.baisylia.cookscollection.block.entity.ModBlockEntities;
import com.baisylia.cookscollection.block.entity.screen.ModMenus;
import com.baisylia.cookscollection.client.ModSounds;
import com.baisylia.cookscollection.item.ModItems;
import com.baisylia.cookscollection.recipe.ModRecipes;
import com.baisylia.cookscollection.tab.ModCreativeModeTabs;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

public class CooksCollection
{
    public static final String MOD_ID = "cookscollection";
    private static final Logger LOGGER = LogUtils.getLogger();

    public static void init() {
        ModCreativeModeTabs.register();
        ModBlocks.register();
        ModItems.register();
        ModBlockEntities.register();
        ModMenus.register();
        ModRecipes.register();
        ModSounds.register();
    }

    public static ResourceLocation locate(String identifier) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, identifier);
    }
}
