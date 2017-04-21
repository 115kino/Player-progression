package com.legacy.player_progression.capabilities.player;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import com.legacy.player_progression.capabilities.CapabilityHandler;
import com.legacy.player_progression.capabilities.items.ProgressionItem;
import com.legacy.player_progression.capabilities.items.armor.ItemXPArmor;
import com.legacy.player_progression.capabilities.items.tool.ItemXPTool;
import com.legacy.player_progression.capabilities.items.tool.ToolAbilities;
import com.legacy.player_progression.capabilities.items.weapon.ItemXPSword;
import com.legacy.player_progression.capabilities.util.Inbt;

public class ProgressionPlayer implements Inbt
{

	protected EntityPlayer player;

	public ProgressionPlayer(EntityPlayer player)
	{
		this.player = player;
	}

	public void onUpdate() 
	{
		if (this.player.getRidingEntity() != null && this.player.ticksExisted % 100 == 0)
		{
			for (ItemStack stack : this.player.inventory.mainInventory)
			{
				if (CapabilityHandler.get(stack) instanceof ItemXPTool)
				{
					ItemXPTool tool = (ItemXPTool) CapabilityHandler.get(stack);

					if (tool.mainAbility == ToolAbilities.MountRepair)
					{
						stack.setItemDamage(stack.getItemDamage() - 10);
					}
					else if (tool.subAbility == ToolAbilities.MountRepair)
					{
						stack.setItemDamage(stack.getItemDamage() - 5);
					}
				}
			}
		}
	}

	public void handleExp(int amount)
	{
		ItemStack stack = this.player.inventory.getCurrentItem();
		ProgressionItem mainWeapon = CapabilityHandler.get(stack);

		if (mainWeapon instanceof ItemXPSword)
		{
			mainWeapon.giveXp(amount);
		}

		for (int slot = 0; slot < 4; ++slot)
		{
			ItemStack armor = this.player.inventory.armorInventory.get(slot);
			ProgressionItem currentArmor = CapabilityHandler.get(armor);

			if (currentArmor instanceof ItemXPArmor)
			{
				currentArmor.giveXp(amount);
			}
		}
	}

	@Override
	public void writeNBTData(NBTTagCompound compound)
	{

	}

	@Override
	public void readNBTData(NBTTagCompound compound)
	{

	}

}