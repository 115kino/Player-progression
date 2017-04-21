package com.legacy.player_progression.container.slots;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.legacy.player_progression.capabilities.CapabilityHandler;

public class SlotEquipment extends Slot 
{

	public SlotEquipment(IInventory inventoryIn, int index, int xPosition, int yPosition)
	{
		super(inventoryIn, index, xPosition, yPosition);
	}

	@Override
    public boolean isItemValid(ItemStack stack)
    {
        return CapabilityHandler.get(stack) != null;
    }

}