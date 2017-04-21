package com.legacy.player_progression.client;

import com.legacy.player_progression.CommonProxy;
import com.legacy.player_progression.client.renders.blocks.BlockRenderHandler;
import com.legacy.player_progression.client.renders.items.ItemRenderHandler;

public class ClientProxy extends CommonProxy
{

	@Override
	public void initialization()
	{
		BlockRenderHandler.initialization();
		ItemRenderHandler.initialization();
	}

}