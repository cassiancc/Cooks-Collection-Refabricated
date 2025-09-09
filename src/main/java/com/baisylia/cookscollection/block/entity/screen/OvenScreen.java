package com.baisylia.cookscollection.block.entity.screen;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class OvenScreen extends AbstractContainerScreen<OvenMenu> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath("cookscollection", "textures/gui/oven_gui.png");

    public OvenScreen(OvenMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float v, int i, int i1) {
        // Get the position where the GUI is to be drawn
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        // Render the background texture
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, x, y, 0, 0, 0,0, imageWidth, imageHeight);

        // Render progress bar if crafting
        if (menu.isCrafting()) {
            guiGraphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, x + 90, y + 35, 176, 14, menu.getScaledProgress(), 17, imageWidth, imageHeight);
        }

        // Render fuel bar if the oven is fueled
        if (menu.isFueled()) {
            guiGraphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, x + 93, y + 55, 176, 32, 17, 15, imageWidth, imageHeight);
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        this.renderBackground(guiGraphics, mouseX, mouseY);
        super.render(guiGraphics, mouseX, mouseY, delta);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
        if (this.isHovering(93, 55, 17, 15, mouseX, mouseY)) {
            String key = "container.cookscollection.oven." + (this.menu.isFueled() ? "heated" : "not_heated");
            guiGraphics.setTooltipForNextFrame(this.font, Component.translatable(key), mouseX, mouseY);
        }
    }

    protected void renderBackground(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        // Draw the background texture
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, (width - imageWidth) / 2, (height - imageHeight) / 2, 0, 0, 0,0, imageWidth, imageHeight);

        // Check and render the crafting progress
        if (menu.isCrafting()) {
            guiGraphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, (width - imageWidth) / 2 + 90, (height - imageHeight) / 2 + 35, 176, 14, menu.getScaledProgress(), 17, 0, imageWidth, imageHeight);
        }

        // Check and render the oven fuel status
        if (menu.isFueled()) {
            guiGraphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, (width - imageWidth) / 2 + 93, (height - imageHeight) / 2 + 55, 176, 32, 17, 15, 0, imageWidth, imageHeight);
        }
    }

}
