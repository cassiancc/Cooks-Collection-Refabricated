package com.baisylia.cookscollection.block.entity;

import com.baisylia.cookscollection.refabricated.RegUtils;
import com.baisylia.cookscollection.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Supplier;

public class ModBlockEntities {

    public static final Supplier<BlockEntityType<OvenBlockEntity>> OVEN_BLOCK_ENTITY =
            RegUtils.regBlockEntity("oven_block_entity",
                    () -> BlockEntityType.Builder.of(OvenBlockEntity::new, ModBlocks.OVEN.get()).build(null));


    public static void register() {
    }
}
