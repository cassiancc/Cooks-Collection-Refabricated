package com.ncpbails.cookscollection.client;

import com.ncpbails.cookscollection.CooksCollection;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static com.ncpbails.cookscollection.CooksCollection.MOD_ID;
import static com.ncpbails.cookscollection.CooksCollection.locate;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(Registries.SOUND_EVENT, MOD_ID);

    public static final Supplier<SoundEvent> OVEN_CRACKLE = SOUND_EVENTS.register("oven_crackle",
            () -> SoundEvent.createVariableRangeEvent(locate( "block.oven.crackle")));

    public static final Supplier<SoundEvent> OVEN_OPEN = SOUND_EVENTS.register("oven_open",
            () -> SoundEvent.createVariableRangeEvent(locate( "block.oven.open")));

    public static final Supplier<SoundEvent> OVEN_CLOSE = SOUND_EVENTS.register("oven_close",
            () -> SoundEvent.createVariableRangeEvent(locate( "block.oven.close")));

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}