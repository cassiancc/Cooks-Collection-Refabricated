package com.ncpbails.cookscollection.world.tree;

import com.ncpbails.cookscollection.CooksCollection;
import com.ncpbails.cookscollection.world.ModConfiguredFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class ModTreeGrowers {
    public static final TreeGrower LEMON = new TreeGrower(CooksCollection.MOD_ID + ":lemon",
            Optional.empty(), Optional.of(ModConfiguredFeatures.LEMON_KEY), Optional.empty());
}
