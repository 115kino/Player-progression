package com.legacy.player_progression.client.renders.blocks;

import com.legacy.player_progression.blocks.BlockHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;

public class BlockRenderHandler
{

	public static void initialization()
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(BlockHandler.synthesizer), 0, new ModelResourceLocation("player_progression:synthesizer", "inventory"));
	}

}