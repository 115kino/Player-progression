package com.legacy.player_progression;

import com.legacy.player_progression.capabilities.CapabilityHandler;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(name = "Player Progression", modid = "player_progression", version = "v1.0c")
public class PlayerProgression
{

	@EventHandler
	public void preInitialization(FMLPreInitializationEvent event)
	{
		
	}

	@EventHandler
	public void initialization(FMLInitializationEvent event)
	{
		CapabilityHandler.initialization();
	}

}