package com.ncpbails.cookscollection;

import com.ncpbails.cookscollection.block.ModBlocks;
import com.ncpbails.cookscollection.block.entity.ModBlockEntities;
import com.ncpbails.cookscollection.block.entity.screen.ModMenus;
import com.ncpbails.cookscollection.block.entity.screen.OvenScreen;
import com.ncpbails.cookscollection.item.ModItems;
import com.ncpbails.cookscollection.recipe.ModRecipes;
import com.ncpbails.cookscollection.tab.ModCreativeModeTabs;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

import static com.ncpbails.cookscollection.block.entity.screen.ModMenus.OVEN_MENU;

@Mod(CooksCollection.MOD_ID)
public class CooksCollection
{
    public static final String MOD_ID = "cookscollection";
    private static final Logger LOGGER = LogUtils.getLogger();

    public CooksCollection(IEventBus modEventBus, ModContainer modContainer)
    {
        modEventBus.addListener(this::commonSetup);

        ModCreativeModeTabs.register(modEventBus);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModMenus.register(modEventBus);
        ModRecipes.register(modEventBus);

        NeoForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
        modEventBus.addListener(this::registerScreens);
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }

    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {

    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    public void registerScreens(RegisterMenuScreensEvent event) {
        event.register(ModMenus.OVEN_MENU.get(), OvenScreen::new);
    }

    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {

        }
    }
}
