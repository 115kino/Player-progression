package com.legacy.player_progression.capabilities.player;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerPickupXpEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import com.legacy.player_progression.capabilities.CapabilityHandler;
import com.legacy.player_progression.capabilities.items.ProgressionItem;
import com.legacy.player_progression.capabilities.items.bow.ItemXPBow;
import com.legacy.player_progression.capabilities.items.tool.ItemXPTool;
import com.legacy.player_progression.capabilities.items.weapon.ItemXPSword;
import com.legacy.player_progression.capabilities.items.weapon.SwordAbilities;
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
	public void onAttacked(LivingHurtEvent event)
	{
		if (event.getSource() instanceof EntityDamageSourceIndirect)
		{
			EntityDamageSourceIndirect source = (EntityDamageSourceIndirect) event.getSource();

			if (source.damageType == "arrow" && source.getEntity() instanceof EntityPlayer)
			{
				EntityPlayer player = (EntityPlayer) source.getEntity();
				ProgressionItem bow = CapabilityHandler.get(player.inventory.getCurrentItem());

				if (bow instanceof ItemXPBow)
				{
					bow.giveXp(10);
				}
			}
		}
	}

	@SubscribeEvent
	public void onAttackEntity(LivingAttackEvent event)
	{
		if (!(event.getSource().getEntity() instanceof EntityPlayer)) return;

		Entity target = event.getEntity();
		EntityPlayer player = (EntityPlayer) event.getSource().getEntity();
		ProgressionItem item = CapabilityHandler.get(player.inventory.getCurrentItem());

		if (item instanceof ItemXPSword)
		{
			ItemXPSword sword = (ItemXPSword) item;

			if (target instanceof EntityWitch)
			{
				if (sword.mainAbility == SwordAbilities.WitchHunter)
				{
					target.attackEntityFrom(DamageSource.magic, 10F);
				}
				else if (sword.subAbility == SwordAbilities.WitchHunter)
				{
					target.attackEntityFrom(DamageSource.magic, 5F);
				}
			}
			else if (target instanceof EntityLivingBase)
			{
				if (!player.getCooldownTracker().hasCooldown(player.inventory.getCurrentItem().getItem()))
				{
					if (sword.mainAbility == SwordAbilities.XPRobber)
					{
						player.addExperience(player.world.rand.nextInt(20));
					}
					else if (sword.subAbility == SwordAbilities.XPRobber)
					{
						player.addExperience(player.world.rand.nextInt(10));
					}
				}

				int i = 20;

				if (sword.mainAbility == SwordAbilities.Homerun)
				{
					target.addVelocity((double)(-MathHelper.sin(player.rotationYaw * 0.017453292F) * (float)i * 0.5F), 0.1D, (double)(MathHelper.cos(player.rotationYaw * 0.017453292F) * (float)i * 0.5F));
				}
				else if (sword.subAbility == SwordAbilities.Homerun)
				{
					i = 12;
					target.addVelocity((double)(-MathHelper.sin(player.rotationYaw * 0.017453292F) * (float)i * 0.5F), 0.1D, (double)(MathHelper.cos(player.rotationYaw * 0.017453292F) * (float)i * 0.5F));
				}
			}
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