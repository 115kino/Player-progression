package com.legacy.player_progression.items;

import com.legacy.player_progression.capabilities.CapabilityHandler;
import com.legacy.player_progression.capabilities.items.sphere.SynthSphere;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemSynthSphere extends Item
{

	public ItemSynthSphere()
	{
		super(); //sorry, habit! :3
	}

    public boolean hasEffect(ItemStack stack)
    {
    	SynthSphere sphere = CapabilityHandler.getSphere(stack);

    	return sphere != null && sphere.type != "";
    }
}