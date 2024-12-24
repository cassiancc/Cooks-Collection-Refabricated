package com.ncpbails.cookscollection.block.entity;

import com.ncpbails.cookscollection.CooksCollection;
import com.ncpbails.cookscollection.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, CooksCollection.MOD_ID);

    public static final Supplier<BlockEntityType<OvenBlockEntity>> OVEN_BLOCK_ENTITY =
            BLOCK_ENTITY_TYPES.register("oven_block_entity",
                    () -> BlockEntityType.Builder.of(OvenBlockEntity::new, ModBlocks.OVEN.get()).build(null));


    public static void register(IEventBus eventBus) {
        BLOCK_ENTITY_TYPES.register(eventBus);
    }
}
