package com.baisylia.cookscollection.block;

import com.baisylia.cookscollection.refabricated.RegUtils;
import com.baisylia.cookscollection.block.custom.*;
import com.baisylia.cookscollection.item.ModItems;
import com.baisylia.cookscollection.world.tree.ModTreeGrowers;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

public class ModBlocks {

    public static final Supplier<Block> LEMON_CRATE = registerBlock("lemon_crate",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(vectorwing.farmersdelight.common.registry.ModBlocks.CARROT_CRATE.get())));

    public static final Supplier<Block> LEMON_SAPLING = registerBlock("lemon_sapling",
            () -> new SaplingBlock(ModTreeGrowers.LEMON, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));

    public static final Supplier<Block> LEMON_LOG = registerBlock("lemon_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)));

    public static final Supplier<Block> LEMON_WOOD = registerBlock("lemon_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)));

    public static final Supplier<Block> LEMON_LEAVES = registerBlock("lemon_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)));

    public static final Supplier<Block> FRUITING_LEMON_LEAVES = registerBlock("fruiting_lemon_leaves",
            () -> new FruitingLeaves(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES), ModItems.LEMON));

    public static final Supplier<Block> RUSTIC_LOAF = registerBlock("rustic_loaf",
            () -> new RusticLoafBlock(BlockBehaviour.Properties.ofFullCopy(vectorwing.farmersdelight.common.registry.ModBlocks.APPLE_PIE.get()).noOcclusion(),
                    ModItems.RUSTIC_LOAF_SLICE));

    public static final Supplier<Block> SALTED_POINTED_DRIPSTONE = RegUtils.regBlock("salted_pointed_dripstone",
            () -> new SaltedPointedDripstone(BlockBehaviour.Properties.ofFullCopy(Blocks.POINTED_DRIPSTONE).noOcclusion()
                    .sound(SoundType.POINTED_DRIPSTONE).randomTicks().strength(1.5F, 3.0F).dynamicShape().offsetType(BlockBehaviour.OffsetType.XZ))
    //,FarmersDelight.CREATIVE_TAB, false, 0
    );

    public static final Supplier<Block> SALTED_DRIPSTONE_BLOCK = registerBlock("salted_dripstone_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DRIPSTONE_BLOCK).noOcclusion()));

    public static final Supplier<Block> OVEN = registerBlock("oven",
            () -> new OvenBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS)));



    private static <T extends Block> Supplier<T> registerBlock(String name, Supplier<T> block) {
        Supplier<T> toReturn = RegUtils.regBlock(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, Supplier<T> block) {
        RegUtils.regItem(name, () -> new BlockItem(block.get(), ModItems.properties()));
    }

    public static void register() {
    }
}
