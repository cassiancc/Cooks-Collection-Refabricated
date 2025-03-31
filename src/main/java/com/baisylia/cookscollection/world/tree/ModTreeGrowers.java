package com.baisylia.cookscollection.world.tree;

import com.baisylia.cookscollection.CooksCollection;
import com.baisylia.cookscollection.world.ModConfiguredFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class ModTreeGrowers {
    public static final TreeGrower LEMON = new TreeGrower(CooksCollection.MOD_ID + ":lemon",
            Optional.empty(), Optional.of(ModConfiguredFeatures.LEMON_KEY), Optional.empty());
}
