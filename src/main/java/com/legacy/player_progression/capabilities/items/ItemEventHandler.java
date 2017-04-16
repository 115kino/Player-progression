package com.legacy.player_progression.capabilities.items;

import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import com.legacy.player_progression.capabilities.CapabilityHandler;
import com.legacy.player_progression.capabilities.items.armor.ItemXPArmor;
import com.legacy.player_progression.capabilities.items.bow.ItemXPBow;
import com.legacy.player_progression.capabilities.items.tool.ItemXPTool;
import com.legacy.player_progression.capabilities.items.weapon.ItemXPSword;
import com.legacy.player_progression.capabilities.util.CapabilityProvider;

public class ItemEventHandler 
{

	@SubscribeEvent
	@SuppressWarnings("deprecation")
	public void registerItemCapability(AttachCapabilitiesEvent.Item event)
	{
		if (event.getItem() instanceof ItemSword)
		{
			event.addCapability(new ResourceLocation("player_progression", "item_progression"), new CapabilityProvider<ProgressionItem>(new ItemXPSword(event.getItemStack())));
		}
		else if (event.getItem() instanceof ItemBow)
		{
			event.addCapability(new ResourceLocation("player_progression", "item_progression"), new CapabilityProvider<ProgressionItem>(new ItemXPBow(event.getItemStack())));
		}
		else if (event.getItem() instanceof ItemTool)
		{
			event.addCapability(new ResourceLocation("player_progression", "item_progression"), new CapabilityProvider<ProgressionItem>(new ItemXPTool(event.getItemStack())));
		}
		else if (event.getItem() instanceof ItemArmor)
		{
			event.addCapability(new ResourceLocation("player_progression", "item_progression"), new CapabilityProvider<ProgressionItem>(new ItemXPArmor(event.getItemStack())));
		}
	}

	@SubscribeEvent
	public void itemTooltipEvent(ItemTooltipEvent event)
	{
		if (CapabilityHandler.get(event.getItemStack()) != null)
		{
			ProgressionItem stack = CapabilityHandler.get(event.getItemStack());
			int math = stack.xpNeeded - stack.xp;

			event.getToolTip().add("");
			event.getToolTip().add(stack.level != stack.currentMaxLevel ? "Level " + stack.level : "MAX Level");
			event.getToolTip().add(math + " until level up");
			event.getToolTip().add(stack.totalXpEarned + " Xp earned");
		}
	}

}