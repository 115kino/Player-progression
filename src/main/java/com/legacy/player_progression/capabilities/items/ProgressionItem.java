package com.legacy.player_progression.capabilities.items;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import com.legacy.player_progression.capabilities.util.Inbt;

public class ProgressionItem implements Inbt
{

	public int level;

	public int xp;

	public int xpNeeded;

	public int currentMaxLevel;

	public int totalXpEarned;

	public ItemStack stack;

	public ProgressionItem(ItemStack stack)
	{
		this.xp = 0;
		this.level = 1;
		this.xpNeeded = 75;
		this.currentMaxLevel = 10;

		this.stack = stack;
	}

	public void displayText(List<String> display)
	{
		int math = this.level != this.currentMaxLevel ? this.xpNeeded - this.xp : 0;

		display.add("");
		display.add(this.level != this.currentMaxLevel ? "Level " + this.level : "MAX Level");
		display.add(math + " until next level");
		display.add(this.totalXpEarned + " Xp earned");
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

		this.writeExtraData(tag);

		compound.setTag("item_progression", tag);
	}

	protected void writeExtraData(NBTTagCompound compound)
	{
		
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

		this.readExtraData(tag);
	}

	protected void readExtraData(NBTTagCompound compound)
	{
		
	}

}