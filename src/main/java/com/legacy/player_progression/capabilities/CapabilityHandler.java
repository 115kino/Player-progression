package com.legacy.player_progression.capabilities;

import com.legacy.player_progression.capabilities.items.ItemEventHandler;
import com.legacy.player_progression.capabilities.items.ProgressionItem;
import com.legacy.player_progression.capabilities.player.PlayerEventHandler;
import com.legacy.player_progression.capabilities.player.ProgressionPlayer;
import com.legacy.player_progression.capabilities.util.CapabilityStorage;
import com.legacy.player_progression.util.MethodUtil;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CapabilityHandler
{
	@CapabilityInject(ProgressionItem.class)
	public static Capability<ProgressionItem> ITEM_LEVEL_HANDLER = null;

	@CapabilityInject(ProgressionPlayer.class)
	public static Capability<ProgressionPlayer> PLAYER_LEVEL_HANDLER = null;

	public static void initialization()
	{
		MethodUtil.registerEvent(new ItemEventHandler());
		MethodUtil.registerEvent(new PlayerEventHandler());

		CapabilityManager.INSTANCE.register(ProgressionItem.class, new CapabilityStorage<ProgressionItem>(), ProgressionItem.class);
		CapabilityManager.INSTANCE.register(ProgressionPlayer.class, new CapabilityStorage<ProgressionPlayer>(), ProgressionPlayer.class);
	}

	public static ProgressionItem get(ItemStack stack)
	{
		return stack.getCapability(CapabilityHandler.ITEM_LEVEL_HANDLER, null);
	}

	public static ProgressionPlayer get(EntityPlayer player)
	{
		return player.getCapability(CapabilityHandler.PLAYER_LEVEL_HANDLER, null);
	}

}