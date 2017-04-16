package com.legacy.player_progression.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockHandler 
{

	private Block synthesizer;

	public static void initialization()
	{
		
	}

	public static Block register(String unlocalizedName, Block block)
	{
		block.setUnlocalizedName(unlocalizedName);
		block.setRegistryName("player_progression", unlocalizedName);

		GameRegistry.register(block, new ResourceLocation("player_progression", unlocalizedName));
		GameRegistry.register(new ItemBlock(block).setRegistryName("player_progression", unlocalizedName));

		return block;
	}

}