package com.legacy.player_progression.capabilities.player;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import net.minecraftforge.event.entity.player.PlayerPickupXpEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import com.legacy.player_progression.capabilities.CapabilityHandler;
import com.legacy.player_progression.capabilities.items.ProgressionItem;
import com.legacy.player_progression.capabilities.items.tool.ItemXPTool;
import com.legacy.player_progression.capabilities.items.tool.ToolAbilities;
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
				event.addCapability(new ResourceLocation("player_progression", "player_progression"), new CapabilityProvider<ProgressionPlayer>(new ProgressionPlayer(player), CapabilityHandler.PLAYER_LEVEL_HANDLER));
			}
		}
	}

	@SubscribeEvent
	public void onLivingUpdate(LivingUpdateEvent event)
	{
		if (event.getEntity() instanceof EntityPlayer)
		{
			ProgressionPlayer player = CapabilityHandler.get((EntityPlayer)event.getEntity());

			if (player != null)
			{
				player.onUpdate();
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
	public void onItemBroken(PlayerDestroyItemEvent event)
	{
		ItemStack original = event.getOriginal();

		if (CapabilityHandler.get(original) instanceof ItemXPTool)
		{
			ItemXPTool tool = (ItemXPTool) CapabilityHandler.get(original);

			if (tool.mainAbility == ToolAbilities.Revive)
			{
				original.setItemDamage(0);
				tool.mainAbility = null;
			}
			else if (tool.subAbility == ToolAbilities.Revive)
			{
				original.setItemDamage(original.getMaxDamage() / 2);
				tool.subAbility = null;
			}
		}
	}

	@SubscribeEvent
	public void onSpeedIncreased(BreakSpeed event)
	{
		EntityPlayer player = event.getEntityPlayer();
		ItemStack stack = player.inventory.getCurrentItem();

		if (CapabilityHandler.get(stack) instanceof ItemXPTool)
		{
			ItemXPTool tool = (ItemXPTool) CapabilityHandler.get(stack);

			if (tool.mainAbility == ToolAbilities.SpeedBoost)
			{
				event.setNewSpeed(event.getOriginalSpeed() + 1.0F);
			}
			else if (tool.subAbility == ToolAbilities.SpeedBoost)
			{
				event.setNewSpeed(event.getOriginalSpeed() + 0.5F);
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

		if (item instanceof ItemXPTool)
		{
			ItemXPTool tool = (ItemXPTool) item;

			if (tool.mainAbility == ToolAbilities.DamageBoost)
			{
				target.attackEntityFrom(DamageSource.generic, 6F);
			}
			else if (tool.subAbility == ToolAbilities.DamageBoost)
			{
				target.attackEntityFrom(DamageSource.generic, 3F);
			}

			if (tool.mainAbility == ToolAbilities.Torched)
			{
				target.setFire(10);
			}
			else if (tool.subAbility == ToolAbilities.Torched)
			{
				target.setFire(4);
			}
		}
		if (item instanceof ItemXPSword)
		{
			ItemXPSword sword = (ItemXPSword) item;

			if (player.dimension > 1 || player.dimension < -1)
			{
				if (sword.mainAbility == SwordAbilities.OuterBoost)
				{
					target.attackEntityFrom(DamageSource.generic, 5F);
				}
				else if (sword.subAbility == SwordAbilities.OuterBoost)
				{
					target.attackEntityFrom(DamageSource.generic, 3F);
				}
			}

			if (player.dimension == -1)
			{
				if (sword.mainAbility == SwordAbilities.NetherBoost)
				{
					target.attackEntityFrom(DamageSource.generic, 2F);
				}
				else if (sword.subAbility == SwordAbilities.NetherBoost)
				{
					target.attackEntityFrom(DamageSource.generic, 1F);
				}
			}

			if (player.dimension == 1)
			{
				if (sword.mainAbility == SwordAbilities.EndBoost)
				{
					target.attackEntityFrom(DamageSource.generic, 4F);
				}
				else if (sword.subAbility == SwordAbilities.EndBoost)
				{
					target.attackEntityFrom(DamageSource.generic, 1F);
				}
			}

			if (target instanceof EntityWitch)
			{
				if (sword.mainAbility == SwordAbilities.WitchHunter)
				{
					target.attackEntityFrom(DamageSource.generic, 10F);
				}
				else if (sword.subAbility == SwordAbilities.WitchHunter)
				{
					target.attackEntityFrom(DamageSource.generic, 5F);
				}
			}
			else if (target instanceof EntityLivingBase)
			{
				if (player.world.rand.nextInt(40) == 0)
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

				if (target instanceof EntityAnimal)
				{
					if (sword.mainAbility == SwordAbilities.AnimalButcher)
					{
						target.attackEntityFrom(DamageSource.generic, 10F);
					}
					else if (sword.subAbility == SwordAbilities.AnimalButcher)
					{
						target.attackEntityFrom(DamageSource.generic, 5F);
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
	public void onHarvestBlock(HarvestDropsEvent event)
	{
		if (event.getHarvester() == null)
		{
			return;
		}

		EntityPlayer entityPlayer = event.getHarvester();
		IBlockState state = event.getState();
		int meta = state.getBlock().getMetaFromState(state);

		if (CapabilityHandler.get(entityPlayer.inventory.getCurrentItem()) instanceof ItemXPTool)
		{
			ItemXPTool tool = (ItemXPTool) CapabilityHandler.get(entityPlayer.inventory.getCurrentItem());

			if (tool.mainAbility == ToolAbilities.Flame || tool.subAbility == ToolAbilities.Flame)
			{
				ItemStack stack = FurnaceRecipes.instance().getSmeltingResult(new ItemStack(state.getBlock(), 1, meta));
				if (stack != ItemStack.field_190927_a)
				{
					event.getDrops().clear();
					event.getDrops().add(stack);
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
		World world = event.getPlayer().world;

		if (player != null)
		{
			ProgressionItem tool = CapabilityHandler.get(event.getPlayer().inventory.getCurrentItem());

			if (tool instanceof ItemXPTool && tool.stack.getStrVsBlock(event.getState()) != 1.0F)
			{
				ItemXPTool xpTool = (ItemXPTool) tool;

				if (!world.isRemote)
				{
					if (xpTool.mainAbility == ToolAbilities.XP)
					{
						world.spawnEntity(new EntityXPOrb(world, event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), 6));
					}
					else if (xpTool.subAbility == ToolAbilities.XP)
					{
						world.spawnEntity(new EntityXPOrb(world, event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), 3));
					}
				}

				Block block = event.getState().getBlock();
				int amount = block == Blocks.DIAMOND_ORE ? 20 : block == Blocks.IRON_ORE ? 10 : block == Blocks.GOLD_ORE ? 10 : event.getExpToDrop() + 1;

				tool.giveXp(amount);
			}
		}
	}
}