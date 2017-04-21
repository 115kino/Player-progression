package com.legacy.player_progression.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemHandler 
{

	public static Item synth_sphere;

	public static void initialization()
	{
		synth_sphere = register("synth_sphere", new ItemSynthSphere().setMaxStackSize(1).setCreativeTab(CreativeTabs.MISC));

		GameRegistry.addRecipe(new SynthSphereRecipe());
		GameRegistry.addRecipe(new ItemStack(synth_sphere), " X ", "X X", " X ", 'X', Items.DIAMOND);
	}

	public static Item register(String unlocalizedName, Item item)
	{
		item.setUnlocalizedName(unlocalizedName);
		item.setRegistryName("player_progression", unlocalizedName);

		GameRegistry.register(item);

		return item;
	}

}