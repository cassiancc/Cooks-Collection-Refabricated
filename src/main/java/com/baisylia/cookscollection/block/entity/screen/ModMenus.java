package com.baisylia.cookscollection.block.entity.screen;

import com.baisylia.cookscollection.CooksCollection;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModMenus {
    public static final DeferredRegister<MenuType<?>> MOD_MENUS = DeferredRegister.create(Registries.MENU, CooksCollection.MOD_ID);

    public static final Supplier<MenuType<OvenMenu>> OVEN_MENU = MOD_MENUS
            .register("oven_menu", () -> IMenuTypeExtension.create(OvenMenu::new));

    public static void register(IEventBus eventBus) {
        MOD_MENUS.register(eventBus);
    }
}