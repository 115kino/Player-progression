package com.legacy.player_progression.capabilities.items;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import com.legacy.player_progression.capabilities.util.Inbt;

public class ProgressionItem implements Inbt
{

	protected int level;

	protected int xp;

	protected int xpNeeded;

	protected int currentMaxLevel;

	protected int totalXpEarned;

	protected ItemStack stack;

	public ProgressionItem(ItemStack stack)
	{
		this.xp = 0;
		this.level = 1;
		this.xpNeeded = 75;
		this.currentMaxLevel = 10;

		this.stack = stack;
	}

	public void giveXp(int amount)
	{
		if (this.level >= this.currentMaxLevel)
		{
			return;
		}

		this.xp += amount;
		this.totalXpEarned += amount;

		if (this.xp >= this.xpNeeded)
		{
			int extra = this.xpNeeded - this.xp;

			this.levelUp();

			this.xp += extra;
		}
	}

	public void removeXp(int amount)
	{
		this.xp -= amount;
	}

	public void levelUp()
	{
		this.xp = 0;
		this.level += 1;
		this.xpNeeded = this.xpNeeded + (20 + (this.xpNeeded / 5));
		this.stack.setItemDamage(0);
	}

	@Override
	public void writeNBTData(NBTTagCompound compound) 
	{
		NBTTagCompound tag = new NBTTagCompound();

		tag.setInteger("xp", this.xp);
		tag.setInteger("totalXp", this.totalXpEarned);
		tag.setInteger("level", this.level);
		tag.setInteger("currentMaxLevel", this.currentMaxLevel);
		tag.setInteger("xpNeeded", this.xpNeeded);

		compound.setTag("item_progression", tag);
	}

	@Override
	public void readNBTData(NBTTagCompound compound) 
	{
		NBTTagCompound tag = (NBTTagCompound) compound.getTag("item_progression");

		this.xp = tag.getInteger("xp");
		this.totalXpEarned = tag.getInteger("totalXp");
		this.level = tag.getInteger("level");
		this.currentMaxLevel = tag.getInteger("currentMaxLevel");
		this.xpNeeded = tag.getInteger("xpNeeded");
	}

}