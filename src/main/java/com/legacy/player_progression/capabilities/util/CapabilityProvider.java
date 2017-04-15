package com.legacy.player_progression.capabilities.util;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;

public class CapabilityProvider<C extends Inbt> implements ICapabilityProvider, INBTSerializable<NBTTagCompound>
{

	private C capability;

	public CapabilityProvider(C capability)
	{
		this.capability = capability;
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing)
	{
		return this.capability != null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T getCapability(Capability<T> capability, EnumFacing facing)
	{
		return (T) this.capability;
	}

	@Override
	public NBTTagCompound serializeNBT()
	{
		NBTTagCompound compound = new NBTTagCompound();

		this.capability.writeNBTData(compound);

		return compound;
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt) 
	{
		this.capability.readNBTData(nbt);
	}

}