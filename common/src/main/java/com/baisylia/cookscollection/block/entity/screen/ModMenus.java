package com.baisylia.cookscollection.block.entity.screen;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.inventory.MenuType;

import java.util.function.Supplier;

import static com.baisylia.cookscollection.RegUtils.regMenu;


public class ModMenus {

    public static final Supplier<MenuType<OvenMenu>> OVEN_MENU = regMenu("oven", () -> new ExtendedScreenHandlerType<>(OvenMenu::new, BlockPos.STREAM_CODEC));

    public static void register() {
    }
}