package com.legacy.player_progression.capabilities.player;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerPickupXpEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import com.legacy.player_progression.capabilities.CapabilityHandler;
import com.legacy.player_progression.capabilities.items.ProgressionItem;
import com.legacy.player_progression.capabilities.items.tool.ItemXPTool;
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

	@SubscribeEvent
	public void onBlockBroken(BlockEvent.BreakEvent event)
	{
		if (event.getPlayer() == null)
		{
			return;
		}

		ProgressionPlayer player = CapabilityHandler.get(event.getPlayer());

		if (player != null)
		{
			ProgressionItem tool = CapabilityHandler.get(event.getPlayer().inventory.getCurrentItem());

			if (tool instanceof ItemXPTool)
			{
				Block block = event.getState().getBlock();
				int amount = block == Blocks.DIAMOND_ORE ? 20 : block == Blocks.IRON_ORE ? 10 : block == Blocks.GOLD_ORE ? 10 : event.getExpToDrop() + 1;

				tool.giveXp(amount);
			}
		}
	}
}