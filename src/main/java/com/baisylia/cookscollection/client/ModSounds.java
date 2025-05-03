package com.baisylia.cookscollection.client;

import com.baisylia.cookscollection.refabricated.RegUtils;
import net.minecraft.sounds.SoundEvent;

import java.util.function.Supplier;

import static com.baisylia.cookscollection.CooksCollection.locate;

public class ModSounds {
    public static final Supplier<SoundEvent> OVEN_CRACKLE = RegUtils.regSound("oven_crackle",
            () -> SoundEvent.createVariableRangeEvent(locate( "block.oven.crackle")));

    public static final Supplier<SoundEvent> OVEN_OPEN = RegUtils.regSound("oven_open",
            () -> SoundEvent.createVariableRangeEvent(locate( "block.oven.open")));

    public static final Supplier<SoundEvent> OVEN_CLOSE = RegUtils.regSound("oven_close",
            () -> SoundEvent.createVariableRangeEvent(locate( "block.oven.close")));

    public static final Supplier<SoundEvent> LEAVES_PICKED = SOUND_EVENTS.register("fruiting_leaves_pick_fruit",
            () -> SoundEvent.createVariableRangeEvent(locate( "block.fruiting_leaves.pick_fruit")));

    public static void register() {
        
    }
}