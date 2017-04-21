package com.legacy.player_progression.client.gui;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;

import com.legacy.player_progression.capabilities.CapabilityHandler;
import com.legacy.player_progression.capabilities.items.ProgressionItem;
import com.legacy.player_progression.container.ContainerSynthesizer;
import com.legacy.player_progression.tile_entities.TileEntitySynthesizer;

public class GuiSynthesizer extends GuiContainer
{

	public static final ResourceLocation GUI_TEXTURE = new ResourceLocation("player_progression", "textures/gui/synthesizer.png");

	private int scrollTime = 0;

	public GuiSynthesizer(InventoryPlayer inventory, TileEntitySynthesizer synthesizer) 
	{
		super(new ContainerSynthesizer(inventory, synthesizer));

		this.scrollTime = 0;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(GUI_TEXTURE);
        int i = this.guiLeft;
        int j = this.guiTop;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);

        Slot slot = this.inventorySlots.getSlot(2);

        this.drawTexturedModalRect(i - 75, j + 30, 178, 32, 75, this.scrollTime);

        this.drawTexturedModalRect(i - 95, j + 10, 181, 0, 75, 33);

        if (slot.getHasStack())
        {
        	if (this.scrollTime < 114)
        	{
        		this.scrollTime += 6;
        	}

        	if (this.scrollTime == 114)
        	{
                ProgressionItem stack = CapabilityHandler.get(slot.getStack());

                List<String> details = new ArrayList<String>();

                stack.displayText(details);

            	this.drawString(this.fontRendererObj, "Level: " + stack.level, i - 70, j + 45, 0xffffff);
            	this.drawString(this.fontRendererObj, "XP: " + stack.totalXpEarned, i - 70, j + 55, 0xffffff);

                for (int size = 0; size < details.size(); ++size)
                {
                	if (size > 4)
                	this.drawString(this.fontRendererObj, details.get(size), i - 70, j + (size * 10) + 25, 0xffffff);
                }
        	}
        }
        else
        {
        	if (this.scrollTime > 0)
        	{
        		this.scrollTime -= 6;
        	}
        }

        this.fontRendererObj.drawString("Synthesizer", i + 55, j + 5, 4210752);
	}

}