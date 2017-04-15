package com.legacy.player_progression.capabilities.items.bow;

import net.minecraft.item.ItemStack;

import com.legacy.player_progression.capabilities.items.ProgressionItem;

public class ItemXPBow extends ProgressionItem
{

	public ItemXPBow(ItemStack stack)
	{
		super(stack);

		this.currentMaxLevel = 20;
		this.xpNeeded = 385;
	}

}