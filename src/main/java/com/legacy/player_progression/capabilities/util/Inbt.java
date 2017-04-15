package com.legacy.player_progression.capabilities.util;

import net.minecraft.nbt.NBTTagCompound;

public interface Inbt
{

	public void writeNBTData(NBTTagCompound compound);

	public void readNBTData(NBTTagCompound compound);

}