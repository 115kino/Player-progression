package com.legacy.player_progression.tile_entities;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler
{

	public static void initialization()
	{
		GameRegistry.registerTileEntity(TileEntitySynthesizer.class, "player_progression_synthesizer");
	}

}