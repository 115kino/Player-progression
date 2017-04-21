package com.legacy.player_progression.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.legacy.player_progression.capabilities.CapabilityHandler;
import com.legacy.player_progression.container.slots.SlotEquipment;
import com.legacy.player_progression.container.slots.SlotInfo;
import com.legacy.player_progression.container.slots.SlotResult;
import com.legacy.player_progression.container.slots.SlotSynth;
import com.legacy.player_progression.items.ItemHandler;
import com.legacy.player_progression.tile_entities.TileEntitySynthesizer;

public class ContainerSynthesizer extends Container
{

	public ContainerSynthesizer(InventoryPlayer inventory, TileEntitySynthesizer synthesizer)
	{
		super();

		this.addSlotToContainer(new SlotSynth(synthesizer, 0, 80, 30));
		this.addSlotToContainer(new SlotEquipment(synthesizer, 1, 44, 30));
		this.addSlotToContainer(new SlotInfo(synthesizer, 2, -44, 19));
		this.addSlotToContainer(new SlotResult(synthesizer, 3, 116, 30));

        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int k = 0; k < 9; ++k)
        {
            this.addSlotToContainer(new Slot(inventory, k, 8 + k * 18, 142));
        }
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) 
	{
		return true;
	}

    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
    	ItemStack nothing = ItemStack.field_190927_a;
    	Slot slot = this.inventorySlots.get(index);

    	if (slot != null && slot.getHasStack())
    	{
    		ItemStack stack = slot.getStack();

    		if (index == 0 || index == 1 || index == 2 || index == 3)
    		{
    			if (!this.mergeItemStack(stack, 4, 40, false))
    			{
    				return nothing;
    			}
   
    			slot.onSlotChange(stack, stack.copy());
    		}

    		if (stack.getItem() == ItemHandler.synth_sphere)
    		{
    			if (!this.mergeItemStack(stack, 0, 1, false))
    			{
    				return nothing;
    			}
    		}

    		if (CapabilityHandler.get(stack) != null)
    		{
    			if (!this.mergeItemStack(stack, 1, 2, false))
    			{
    				return nothing;
    			}
    		}
    	}

    	return super.transferStackInSlot(playerIn, index);
    }
}