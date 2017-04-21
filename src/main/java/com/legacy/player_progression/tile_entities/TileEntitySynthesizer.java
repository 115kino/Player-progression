package com.legacy.player_progression.tile_entities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;

import com.legacy.player_progression.capabilities.CapabilityHandler;
import com.legacy.player_progression.capabilities.items.ProgressionItem;
import com.legacy.player_progression.capabilities.items.sphere.SynthSphere;
import com.legacy.player_progression.capabilities.items.tool.ItemXPTool;
import com.legacy.player_progression.capabilities.items.tool.ToolAbilities;
import com.legacy.player_progression.capabilities.items.weapon.ItemXPSword;
import com.legacy.player_progression.capabilities.items.weapon.SwordAbilities;
import com.legacy.player_progression.items.ItemHandler;

public class TileEntitySynthesizer extends TileEntity implements IInventory, ITickable
{

	public NonNullList<ItemStack> inventory = NonNullList.func_191197_a(4, ItemStack.field_190927_a);

	public TileEntitySynthesizer()
	{

	}

	@Override
	public String getName()
	{
		return "Synthesizer";
	}

	@Override
	public boolean hasCustomName()
	{
		return false;
	}

	@Override
	public int getSizeInventory() 
	{
		return 4;
	}

	@Override
	public boolean func_191420_l() 
	{
		return false; //Was empty?
	}

	@Override
	public void update()
	{
		if (this.getStackInSlot(3) == ItemStack.field_190927_a && CapabilityHandler.getSphere(this.getStackInSlot(0)) != null && CapabilityHandler.get(this.getStackInSlot(1)) != null)
		{
			ItemStack stack = this.getStackInSlot(1).copy();
			SynthSphere sphere = CapabilityHandler.getSphere(this.getStackInSlot(0));
			ProgressionItem progression = CapabilityHandler.get(stack);

			if (progression instanceof ItemXPSword)
			{
				if (SwordAbilities.getAbility(sphere.type) != null && ((ItemXPSword)progression).subAbility == null)
				{
					((ItemXPSword) progression).subAbility = SwordAbilities.getAbility(sphere.type);
					progression.giveXp(sphere.XpContained);
					this.setInventorySlotContents(3, stack);
				}
			}
			else if (progression instanceof ItemXPTool)
			{
				if (ToolAbilities.getAbility(sphere.type) != null && ((ItemXPTool)progression).subAbility == null)
				{
					((ItemXPTool) progression).subAbility = ToolAbilities.getAbility(sphere.type);
					progression.giveXp(sphere.XpContained);
					this.setInventorySlotContents(3, stack);
				}
			}
		}
		else if (this.getStackInSlot(3) != ItemStack.field_190927_a && (CapabilityHandler.getSphere(this.getStackInSlot(0)) == null || CapabilityHandler.get(this.getStackInSlot(1)) == null))
		{
			this.removeStackFromSlot(3);
		}
	}

	@Override
	public ItemStack getStackInSlot(int index) 
	{
		return this.inventory.get(index);
	}

	@Override
	public ItemStack decrStackSize(int index, int count)
	{
		ItemStack stack = ItemStackHelper.getAndSplit(this.inventory, index, count);

		return stack;
	}

	@Override
	public ItemStack removeStackFromSlot(int index) 
	{
		return this.inventory.set(index, ItemStack.field_190927_a);
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) 
	{
		if (!this.world.isRemote && index == 3 && stack == ItemStack.field_190927_a) // FINALLY!!
		{
			this.inventory.clear();
		}

		this.inventory.set(index, stack);
	}

	@Override
	public int getInventoryStackLimit() 
	{
		return 64;
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player)
	{
		return true;
	}

	@Override
	public void openInventory(EntityPlayer player)
	{

	}

	@Override
	public void closeInventory(EntityPlayer player) 
	{
		this.removeStackFromSlot(3);
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack)
	{
		if (index == 0)
		{
			return stack.getItem() == ItemHandler.synth_sphere;
		}

		if (index == 3)
		{
			return false;
		}

		return CapabilityHandler.get(stack) != null;
	}

	@Override
	public int getField(int id)
	{
		return id;
	}

	@Override
	public void setField(int id, int value) 
	{

	}

	@Override
	public int getFieldCount()
	{
		return 0;
	}

	@Override
	public void clear()
	{
		this.inventory.clear();
	}

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);

		ItemStackHelper.func_191283_b(compound, this.inventory);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);

		ItemStackHelper.func_191282_a(compound, this.inventory);

		return compound;
	}

}