package com.legacy.player_progression.capabilities.items;

import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import com.legacy.player_progression.capabilities.CapabilityHandler;
import com.legacy.player_progression.capabilities.items.armor.ItemXPArmor;
import com.legacy.player_progression.capabilities.items.sphere.SynthSphere;
import com.legacy.player_progression.capabilities.items.tool.ItemXPTool;
import com.legacy.player_progression.capabilities.items.weapon.ItemXPSword;
import com.legacy.player_progression.capabilities.util.CapabilityProvider;
import com.legacy.player_progression.items.ItemHandler;

public class ItemEventHandler 
{

	@SubscribeEvent
	@SuppressWarnings("deprecation")
	public void registerItemCapability(AttachCapabilitiesEvent.Item event)
	{
		if (event.getItem() instanceof ItemSword)
		{
			event.addCapability(new ResourceLocation("player_progression", "sword_progression"), new CapabilityProvider<ProgressionItem>(new ItemXPSword(event.getItemStack()), CapabilityHandler.ITEM_LEVEL_HANDLER));
		}
		else if (event.getItem() instanceof ItemTool)
		{
			event.addCapability(new ResourceLocation("player_progression", "tool_progression"), new CapabilityProvider<ProgressionItem>(new ItemXPTool(event.getItemStack()), CapabilityHandler.ITEM_LEVEL_HANDLER));
		}
		else if (event.getItem() instanceof ItemArmor)
		{
			event.addCapability(new ResourceLocation("player_progression", "armor_progression"), new CapabilityProvider<ProgressionItem>(new ItemXPArmor(event.getItemStack()), CapabilityHandler.ITEM_LEVEL_HANDLER));
		}
		else if (event.getItem() == ItemHandler.synth_sphere)
		{
			event.addCapability(new ResourceLocation("player_progression", "sphere"), new CapabilityProvider<SynthSphere>(new SynthSphere(), CapabilityHandler.SYNTH_SPHERE_HANDLER));
		}
	}

	@SubscribeEvent
	public void itemTooltipEvent(ItemTooltipEvent event)
	{
		if (CapabilityHandler.get(event.getItemStack()) != null)
		{
			ProgressionItem stack = CapabilityHandler.get(event.getItemStack());

			stack.displayText(event.getToolTip());
		}
		else if (CapabilityHandler.getSphere(event.getItemStack()) != null)
		{
			CapabilityHandler.getSphere(event.getItemStack()).displayText(event.getToolTip());
		}
	}

}