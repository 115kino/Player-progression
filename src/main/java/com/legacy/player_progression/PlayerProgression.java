package com.legacy.player_progression;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

import com.legacy.player_progression.blocks.BlockHandler;
import com.legacy.player_progression.capabilities.CapabilityHandler;
import com.legacy.player_progression.items.ItemHandler;
import com.legacy.player_progression.tile_entities.TileEntityHandler;

@Mod(name = "Player Progression", modid = "player_progression", version = "v1.0c")
public class PlayerProgression
{

	@Instance("player_progression")
	public static PlayerProgression instance;

	@SidedProxy(modId = "player_progression", clientSide = "com.legacy.player_progression.client.ClientProxy", serverSide = "com.legacy.player_progression.CommonProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void initialization(FMLInitializationEvent event)
	{
		ItemHandler.initialization();
		BlockHandler.initialization();
		TileEntityHandler.initialization();
		CapabilityHandler.initialization();

		proxy.initialization();
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
	}

}