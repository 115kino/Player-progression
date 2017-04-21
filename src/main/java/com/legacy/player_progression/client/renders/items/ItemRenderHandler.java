package com.legacy.player_progression.client.renders.items;

import com.legacy.player_progression.items.ItemHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;

public class ItemRenderHandler 
{

	public static void initialization()
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(ItemHandler.synth_sphere, 0, new ModelResourceLocation("player_progression:synth_sphere", "inventory"));
	}

}