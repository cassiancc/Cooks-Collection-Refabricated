package com.baisylia.cookscollection.neoforge;

import com.baisylia.cookscollection.CooksCollection;
import com.baisylia.cookscollection.block.entity.screen.ModMenus;
import com.baisylia.cookscollection.block.entity.screen.OvenScreen;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

@Mod(CooksCollection.MOD_ID)
public final class CooksCollectionNeoForge {
    public CooksCollectionNeoForge() {
        // Run our common setup.
        CooksCollection.init();
    }

    public void registerScreens(RegisterMenuScreensEvent event) {
        event.register(ModMenus.OVEN_MENU.get(), OvenScreen::new);
    }
}
