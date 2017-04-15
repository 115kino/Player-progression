package com.legacy.player_progression.capabilities.util;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class CapabilityStorage<C> implements IStorage<C>
{

	@Override
	public NBTBase writeNBT(Capability<C> capability, C instance, EnumFacing side)
	{
		return new NBTTagCompound();
	}

	@Override
	public void readNBT(Capability<C> capability, C instance, EnumFacing side, NBTBase nbt) 
	{

	}

}