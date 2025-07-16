package com.baisylia.cookscollection.item;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import vectorwing.farmersdelight.common.FoodValues;
import vectorwing.farmersdelight.common.registry.ModEffects;

public class ModConsumables {
    public static final Consumable LEMONADE = Consumable.builder().sound(SoundEvents.GENERIC_DRINK).onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.SPEED, 1200, 0), 1.0F)).build();
    public static final Consumable FISH_AND_CHIPS = Consumable.builder().onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(ModEffects.NOURISHMENT, FoodValues.LONG_DURATION, 0), 1.0F)).build();
    public static final Consumable OIL = Consumable.builder().sound(SoundEvents.HONEY_DRINK).build();
}
