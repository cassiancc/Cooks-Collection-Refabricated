package com.baisylia.cookscollection.block.entity.screen;

import com.baisylia.cookscollection.CooksCollection;
import dev.architectury.registry.registries.DeferredRegister;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;

import java.util.function.Supplier;

import static com.baisylia.cookscollection.RegUtils.regMenu;


public class ModMenus {
    public static final DeferredRegister<MenuType<?>> MOD_MENUS = DeferredRegister.create(CooksCollection.MOD_ID, Registries.MENU);

    public static final Supplier<MenuType<OvenMenu>> OVEN_MENU = regMenu("oven", () -> new ExtendedScreenHandlerType<>(OvenMenu::new, BlockPos.STREAM_CODEC));

    public static void register() {
        MOD_MENUS.register();
    }
}