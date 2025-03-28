package com.baisylia.cookscollection.client;

import dev.architectury.registry.registries.DeferredRegister;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;

import java.util.function.Supplier;

import static com.baisylia.cookscollection.CooksCollection.MOD_ID;
import static com.baisylia.cookscollection.CooksCollection.locate;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(MOD_ID, Registries.SOUND_EVENT);

    public static final Supplier<SoundEvent> OVEN_CRACKLE = SOUND_EVENTS.register("oven_crackle",
            () -> SoundEvent.createVariableRangeEvent(locate( "block.oven.crackle")));

    public static final Supplier<SoundEvent> OVEN_OPEN = SOUND_EVENTS.register("oven_open",
            () -> SoundEvent.createVariableRangeEvent(locate( "block.oven.open")));

    public static final Supplier<SoundEvent> OVEN_CLOSE = SOUND_EVENTS.register("oven_close",
            () -> SoundEvent.createVariableRangeEvent(locate( "block.oven.close")));

    public static void register() {
        SOUND_EVENTS.register();
    }
}