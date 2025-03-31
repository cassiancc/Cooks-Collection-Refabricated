package com.baisylia.cookscollection.client;

import com.baisylia.cookscollection.RegUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;

import java.util.function.Supplier;

import static com.baisylia.cookscollection.CooksCollection.MOD_ID;
import static com.baisylia.cookscollection.CooksCollection.locate;

public class ModSounds {
    public static final Supplier<SoundEvent> OVEN_CRACKLE = RegUtils.regSound("oven_crackle",
            () -> SoundEvent.createVariableRangeEvent(locate( "block.oven.crackle")));

    public static final Supplier<SoundEvent> OVEN_OPEN = RegUtils.regSound("oven_open",
            () -> SoundEvent.createVariableRangeEvent(locate( "block.oven.open")));

    public static final Supplier<SoundEvent> OVEN_CLOSE = RegUtils.regSound("oven_close",
            () -> SoundEvent.createVariableRangeEvent(locate( "block.oven.close")));

    public static void register() {
    }
}