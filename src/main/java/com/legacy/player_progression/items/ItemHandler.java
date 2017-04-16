package com.legacy.player_progression.items;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemHandler 
{

	public static Item diamond_sphere;

	public static void initialization()
	{
		diamond_sphere = register("diamond_sphere", new Item().setMaxStackSize(1));
	}

	public static Item register(String unlocalizedName, Item item)
	{
		item.setUnlocalizedName(unlocalizedName);
		item.setRegistryName("player_progression", unlocalizedName);

		GameRegistry.register(item, new ResourceLocation("player_progression", unlocalizedName));

		return item;
	}

}