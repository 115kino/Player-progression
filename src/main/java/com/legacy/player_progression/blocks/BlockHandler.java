package com.legacy.player_progression.blocks;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.legacy.player_progression.blocks.container.BlockSynthesizer;

public class BlockHandler 
{

	public static Block synthesizer;

	public static void initialization()
	{
		synthesizer = register("synthesizer", new BlockSynthesizer());

		GameRegistry.addRecipe(new ItemStack(synthesizer), "XYX", "YYY", "XYX", 'X', Items.DIAMOND, 'Y', Blocks.COBBLESTONE);
	}

	public static Block register(String unlocalizedName, Block block)
	{
		block.setUnlocalizedName(unlocalizedName);
		block.setRegistryName("player_progression", unlocalizedName);

		GameRegistry.register(block);
		GameRegistry.register(new ItemBlock(block).setRegistryName("player_progression", unlocalizedName));

		return block;
	}

}