package com.legacy.player_progression.capabilities.util;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;

public class CapabilityProvider<C extends Inbt> implements ICapabilityProvider, INBTSerializable<NBTTagCompound>
{

	private C capability;

	private Capability<?> handler;

	public CapabilityProvider(C capability, Capability<?> handler)
	{
		this.capability = capability;
		this.handler = handler;
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing)
	{
		return this.capability.getClass() == capability.getDefaultInstance().getClass();
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T getCapability(Capability<T> capability, EnumFacing facing)
	{
		if (capability == handler)
		{
			return (T) this.capability;
		}

		return null;
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