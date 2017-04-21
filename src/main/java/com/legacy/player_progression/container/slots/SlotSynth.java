package com.legacy.player_progression.container.slots;

import com.legacy.player_progression.items.ItemHandler;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotSynth extends Slot 
{

	public SlotSynth(IInventory inventoryIn, int index, int xPosition, int yPosition)
	{
		super(inventoryIn, index, xPosition, yPosition);
	}

	@Override
    public boolean isItemValid(ItemStack stack)
    {
        return stack.getItem() == ItemHandler.synth_sphere;
    }

}