package com.ncpbails.cookscollection.block.entity;

import com.ncpbails.cookscollection.block.custom.OvenBlock;
import com.ncpbails.cookscollection.block.entity.screen.OvenMenu;
import com.ncpbails.cookscollection.recipe.OvenRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.items.wrapper.RecipeWrapper;
import vectorwing.farmersdelight.common.block.entity.container.CookingPotMenu;
import vectorwing.farmersdelight.common.crafting.CookingPotRecipe;
import vectorwing.farmersdelight.common.tag.ModTags;

import javax.annotation.Nullable;
import java.util.Optional;

import static com.ncpbails.cookscollection.block.custom.OvenBlock.LIT;

public class OvenBlockEntity extends BlockEntity implements MenuProvider {

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 72;
    private int litTime = 0;

    private final ItemStackHandler itemHandler = new ItemStackHandler(11) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    public OvenBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.OVEN_BLOCK_ENTITY.get(), pWorldPosition, pBlockState);

        this.data = new ContainerData() {
            public int get(int index) {
                return switch (index) {
                    case 0 -> OvenBlockEntity.this.progress;
                    case 1 -> OvenBlockEntity.this.maxProgress;
                    case 2 -> OvenBlockEntity.this.litTime;
                    default -> 0;
                };
            }

            public void set(int index, int value) {
                switch (index) {
                    case 0 -> OvenBlockEntity.this.progress = value;
                    case 1 -> OvenBlockEntity.this.maxProgress = value;
                    case 2 -> OvenBlockEntity.this.litTime = value;
                }
            }

            public int getCount() {
                return 3;
            }
        };
    }


    @Override
    public Component getDisplayName() {
        return Component.literal("Oven");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer) {
        return new OvenMenu(pContainerId, pInventory, this, this.data);
    }

    public ItemStackHandler getItemHandler() {
        return itemHandler;
    }

    @Override
    public void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        itemHandler.deserializeNBT(registries, tag.getCompound("inventory"));
        progress = tag.getInt("oven.progress");
        litTime = tag.getInt("oven.lit_time");
        maxProgress = tag.getInt("oven.max_progress");
    }

    @Override
    public void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        tag.put("inventory", itemHandler.serializeNBT(registries));
        tag.putInt("oven.progress", progress);
        tag.putInt("oven.lit_time", litTime);
        tag.putInt("oven.max_progress", maxProgress);
        super.saveAdditional(tag, registries);
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, OvenBlockEntity pBlockEntity) {
        if(isFueled(pBlockEntity, pPos, pLevel))
        {
            pBlockEntity.litTime = 1;
            setChanged(pLevel, pPos, pState);
        }
        else
        {
            pBlockEntity.litTime = 0;
            setChanged(pLevel, pPos, pState);
        }

        if(hasRecipe(pBlockEntity)) {
            pBlockEntity.progress++;
            setChanged(pLevel, pPos, pState);
            if(pBlockEntity.progress > pBlockEntity.maxProgress) {
                craftItem(pBlockEntity);
            }
        } else {
            pBlockEntity.resetProgress();
            setChanged(pLevel, pPos, pState);
        }
    }


    private static boolean hasRecipe(OvenBlockEntity entity) {
        Level level = entity.level;
        BlockPos pos = entity.getBlockPos();

        // Check if the oven is fueled (lit)
        if (!isFueled(entity, pos, level)) {
            return false;
        }

        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        // Check for OvenShapedRecipe
        //Optional<OvenShapedRecipe> shapedMatch = level.getRecipeManager()
        //        .getRecipeFor(OvenShapedRecipe.Type.INSTANCE, inventory, level);

        // Check for OvenRecipe

        Optional<RecipeHolder<OvenRecipe>> recipeMatch = level.getRecipeManager()
                .getRecipeFor(OvenRecipe.Type.INSTANCE, new RecipeWrapper(entity.itemHandler), level);

        if (recipeMatch.isPresent()) {
            OvenRecipe recipe = recipeMatch.get().value();

            entity.maxProgress = recipe.getCookTime();
            return true;
        }
        return false;
    }


    static boolean isFueled(OvenBlockEntity entity, BlockPos pos, Level level) {
        BlockState stateBelow = level.getBlockState(pos.below());
        if (stateBelow.hasProperty(BlockStateProperties.LIT) ? stateBelow.getValue(BlockStateProperties.LIT) : true) {
            if (stateBelow.is(ModTags.HEAT_SOURCES) || stateBelow.is(ModTags.HEAT_CONDUCTORS)) {
                level.setBlock(pos, entity.getBlockState().setValue(LIT, Boolean.valueOf(true)), 3);
                return true;
            }
            else {
                level.setBlock(pos, entity.getBlockState().setValue(LIT, Boolean.valueOf(false)), 3);
                return false;
            }
        }
        else {
            level.setBlock(pos, entity.getBlockState().setValue(LIT, Boolean.valueOf(false)), 3);
            return false;
        }
    }

    private static void craftItem(OvenBlockEntity entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        // Check for OvenShapedRecipe
        //Optional<OvenShapedRecipe> shapedMatch = level.getRecipeManager()
        //        .getRecipeFor(OvenShapedRecipe.Type.INSTANCE, inventory, level);

        // Check for OvenRecipe

        Optional<RecipeHolder<OvenRecipe>> recipeMatch = level.getRecipeManager()
                .getRecipeFor(OvenRecipe.Type.INSTANCE, new RecipeWrapper(entity.itemHandler), level);

       /* if (shapedMatch.isPresent()) {
            for(int i = 0; i < 9; ++i) {
                ItemStack slotStack = entity.itemHandler.getStackInSlot(i);
                if (slotStack.hasCraftingRemainingItem()) {
                    Direction direction = ((Direction)entity.getBlockState().getValue(OvenBlock.FACING)).getCounterClockWise();
                    double x = (double)entity.worldPosition.getX() + 0.5 + (double)direction.getStepX() * 0.25;
                    double y = (double)entity.worldPosition.getY() + 0.7;
                    double z = (double)entity.worldPosition.getZ() + 0.5 + (double)direction.getStepZ() * 0.25;
                    spawnItemEntity(entity.level, entity.itemHandler.getStackInSlot(i).getCraftingRemainingItem(), x, y, z, (double)((float)direction.getStepX() * 0.08F), 0.25, (double)((float)direction.getStepZ() * 0.08F));
                }
            }

            for (int i = 0; i < 9; ++i) {
                entity.itemHandler.extractItem(i, 1, false);
            }
            inventory.getItem(9).is(shapedMatch.get().getResultItem().getItem());

            entity.itemHandler.setStackInSlot(9, new ItemStack(shapedMatch.get().getResultItem().getItem(),
                    entity.itemHandler.getStackInSlot(9).getCount() + entity.getTheCount(shapedMatch.get().getResultItem())));

            entity.resetProgress();

        } else */if (recipeMatch.isPresent()) {
            for(int i = 0; i < 9; ++i) {
                ItemStack slotStack = entity.itemHandler.getStackInSlot(i);
                if (slotStack.hasCraftingRemainingItem()) {
                    Direction direction = ((Direction)entity.getBlockState().getValue(OvenBlock.FACING)).getCounterClockWise();
                    double x = (double)entity.worldPosition.getX() + 0.5 + (double)direction.getStepX() * 0.25;
                    double y = (double)entity.worldPosition.getY() + 0.7;
                    double z = (double)entity.worldPosition.getZ() + 0.5 + (double)direction.getStepZ() * 0.25;
                    spawnItemEntity(entity.level, entity.itemHandler.getStackInSlot(i).getCraftingRemainingItem(), x, y, z, (double)((float)direction.getStepX() * 0.08F), 0.25, (double)((float)direction.getStepZ() * 0.08F));
                }
            }

            for (int i = 0; i < 9; ++i) {
                entity.itemHandler.extractItem(i, 1, false);
            }
            OvenRecipe recipe = recipeMatch.get().value();

            inventory.getItem(9).is(recipe.getResultItemy().getItem());

            entity.itemHandler.setStackInSlot(9, new ItemStack(recipe.getResultItemy().getItem(),
                    entity.itemHandler.getStackInSlot(9).getCount() + entity.getTheCount(recipe.getResultItemy())));

            entity.resetProgress();
        }
    }
    public static void spawnItemEntity(Level level, ItemStack stack, double x, double y, double z, double xMotion, double yMotion, double zMotion) {
        ItemEntity entity = new ItemEntity(level, x, y, z, stack);
        entity.setDeltaMovement(xMotion, yMotion, zMotion);
        level.addFreshEntity(entity);
    }
    private int getTheCount (ItemStack itemIn)
    {
        return itemIn.getCount();
    }
    private void resetProgress() {
        this.progress = 0;
        this.maxProgress = 72;
    }
}