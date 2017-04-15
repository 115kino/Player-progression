package com.legacy.player_progression.capabilities.player;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerPickupXpEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import com.legacy.player_progression.capabilities.CapabilityHandler;
import com.legacy.player_progression.capabilities.util.CapabilityProvider;

public class PlayerEventHandler 
{

	@SubscribeEvent
	@SuppressWarnings("deprecation")
	public void initPlayerCapabilities(AttachCapabilitiesEvent.Entity event)
	{
		if (event.getEntity() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.getEntity();

			if (CapabilityHandler.get(player) == null)
			{
				event.addCapability(new ResourceLocation("player_progression", "player_progression"), new CapabilityProvider<ProgressionPlayer>(new ProgressionPlayer(player)));
			}
		}
	}

	@SubscribeEvent
	public void onXPaquired(PlayerPickupXpEvent event)
	{
		ProgressionPlayer player = CapabilityHandler.get(event.getEntityPlayer());

		if (player != null)
		{
			player.handleExp(event.getOrb().getXpValue());
		}
	}

}