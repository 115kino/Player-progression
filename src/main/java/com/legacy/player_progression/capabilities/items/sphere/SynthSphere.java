package com.legacy.player_progression.capabilities.items.sphere;

import java.util.List;

import net.minecraft.nbt.NBTTagCompound;

import com.legacy.player_progression.capabilities.util.Inbt;

public class SynthSphere implements Inbt
{

	public String type = "";

	public int XpContained;

	public SynthSphere()
	{
		this.XpContained = 0;
	}

	public void displayText(List<String> display)
	{
		if (this.type != "")
		{
			display.add("");
			display.add("Ability: " + type);
		}

		display.add("");
		display.add("XP: " + this.XpContained);
	}

	@Override
	public void writeNBTData(NBTTagCompound compound)
	{
		if (this.type != "") compound.setString("ability", this.type);
		compound.setInteger("xp", this.XpContained);
	}

	@Override
	public void readNBTData(NBTTagCompound compound)
	{
		if (compound.hasKey("ability")) this.type = compound.getString("ability");
		this.XpContained = compound.getInteger("xp");
	}

}