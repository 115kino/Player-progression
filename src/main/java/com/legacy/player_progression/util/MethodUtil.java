package com.legacy.player_progression.util;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class MethodUtil
{

	@SuppressWarnings("deprecation")
	public static void registerEvent(Object obj)
	{
		MinecraftForge.EVENT_BUS.register(obj);
		FMLCommonHandler.instance().bus().register(obj);
	}

	public static ToolMaterial determineMaterial(String name)
	{
		return ToolMaterial.valueOf(name);
	}
}