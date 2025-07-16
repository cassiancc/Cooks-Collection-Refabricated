package com.baisylia.cookscollection.block;

import com.baisylia.cookscollection.refabricated.RegUtils;
import com.baisylia.cookscollection.block.custom.*;
import com.baisylia.cookscollection.item.ModItems;
import com.baisylia.cookscollection.world.tree.ModTreeGrowers;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Function;
import java.util.function.Supplier;

import static com.baisylia.cookscollection.CooksCollection.MOD_ID;

public class ModBlocks {

    public static final Supplier<Block> LEMON_CRATE = registerBlock("lemon_crate",
            Block::new, BlockBehaviour.Properties.ofFullCopy(vectorwing.farmersdelight.common.registry.ModBlocks.CARROT_CRATE.get()));

    public static final Supplier<Block> LEMON_SAPLING = registerBlock("lemon_sapling",
            (properties)-> new SaplingBlock(ModTreeGrowers.LEMON, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING));

    public static final Supplier<Block> LEMON_LOG = registerBlock("lemon_log",
            ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG));

    public static final Supplier<Block> LEMON_WOOD = registerBlock("lemon_wood",
            ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD));

    public static final Supplier<Block> LEMON_LEAVES = registerBlock("lemon_leaves",
            (properties) -> new UntintedParticleLeavesBlock(0.01f, ColorParticleOption.create(ParticleTypes.TINTED_LEAVES, -9399763), properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES));

    public static final Supplier<Block> FRUITING_LEMON_LEAVES = registerBlock("fruiting_lemon_leaves",
            (properties) -> new FruitingLeaves(properties, ModItems.LEMON), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES));

    public static final Supplier<Block> RUSTIC_LOAF = registerBlock("rustic_loaf",
            (properties) -> new RusticLoafBlock(properties, ModItems.RUSTIC_LOAF_SLICE),
            BlockBehaviour.Properties.ofFullCopy(vectorwing.farmersdelight.common.registry.ModBlocks.APPLE_PIE.get()).noOcclusion());

    public static final Supplier<Block> SALTED_POINTED_DRIPSTONE = registerBlock("salted_pointed_dripstone",
            SaltedPointedDripstone::new, BlockBehaviour.Properties.ofFullCopy(Blocks.POINTED_DRIPSTONE).noOcclusion()
                    .sound(SoundType.POINTED_DRIPSTONE).randomTicks().strength(1.5F, 3.0F).dynamicShape().offsetType(BlockBehaviour.OffsetType.XZ)
    //,FarmersDelight.CREATIVE_TAB, false, 0
    );

    public static final Supplier<Block> SALTED_DRIPSTONE_BLOCK = registerBlock("salted_dripstone_block",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DRIPSTONE_BLOCK).noOcclusion());

    public static final Supplier<Block> OVEN = registerBlock("oven",
            OvenBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS));

    public static Supplier<Block> registerBlock(ResourceKey<Block> resourceKey, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties properties) {
        Block block = factory.apply(properties.setId(resourceKey));
        Supplier<Block> toReturn = RegUtils.regBlock(resourceKey.location().getPath(), ()-> block);
        registerBlockItem(resourceKey.location().getPath(), toReturn);
        return toReturn;
    }

    private static ResourceKey<Block> registryKey(String name) {
        return ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(MOD_ID, name));
    }

    private static Supplier<Block> registerBlock(String name, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties properties) {
        return registerBlock(registryKey(name), factory, properties);
    }

    private static <T extends Block> void registerBlockItem(String name, Supplier<T> block) {
        RegUtils.regItem(name, () -> new BlockItem(block.get(), ModItems.properties(name).useBlockDescriptionPrefix()));
    }

    public static void register() {
    }
}
