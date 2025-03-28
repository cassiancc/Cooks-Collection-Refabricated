package com.baisylia.cookscollection.fabric.client;

import com.baisylia.cookscollection.block.ModBlocks;
import com.baisylia.cookscollection.block.entity.screen.ModMenus;
import com.baisylia.cookscollection.block.entity.screen.OvenScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.RenderType;

public final class CooksCollectionFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        MenuScreens.register(ModMenus.OVEN_MENU.get(), OvenScreen::new);
        // This entrypoint is suitable for setting up client-specific logic, such as rendering.
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.LEMON_SAPLING.get(), RenderType.cutout());
    }
}
