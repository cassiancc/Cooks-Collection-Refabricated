package com.baisylia.cookscollection.tab;

import com.baisylia.cookscollection.CooksCollection;
import com.baisylia.cookscollection.item.ModItems;
import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTabs {
    private static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(CooksCollection.MOD_ID, Registries.CREATIVE_MODE_TAB);


    public static final RegistrySupplier<CreativeModeTab> TAB = CREATIVE_MODE_TAB.register(
            "cookscollections_tab", // Tab ID
            () -> CreativeTabRegistry.create(
                    Component.translatable("creativetab.cookscollection.cookscollections_tab"), // Tab Name
                    () -> new ItemStack(ModItems.LEMON.get()) // Icon
            )
    );

    public static void register() {
        CREATIVE_MODE_TAB.register();
    }
}
