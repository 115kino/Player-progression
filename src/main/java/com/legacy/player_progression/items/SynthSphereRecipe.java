package com.legacy.player_progression.items;

import com.legacy.player_progression.capabilities.CapabilityHandler;
import com.legacy.player_progression.capabilities.items.ProgressionItem;
import com.legacy.player_progression.capabilities.items.sphere.SynthSphere;
import com.legacy.player_progression.capabilities.items.tool.ItemXPTool;
import com.legacy.player_progression.capabilities.items.weapon.ItemXPSword;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

public class SynthSphereRecipe implements IRecipe {

	@Override
	public boolean matches(InventoryCrafting inv, World worldIn)
	{
		boolean spherePresent = false;
		boolean equipmentPresent = false;

        for (int i = 0; i < inv.getSizeInventory(); ++i)
        {
            ItemStack itemstack = inv.getStackInSlot(i);

            if (CapabilityHandler.get(itemstack) != null)
            {
            	equipmentPresent = true;
            }
            else if (itemstack.getItem() == ItemHandler.synth_sphere)
            {
            	spherePresent = true;
            }
        }

		return equipmentPresent && spherePresent;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv)
	{
		ItemStack sphere = new ItemStack(ItemHandler.synth_sphere);

        for (int i = 0; i < inv.getSizeInventory(); ++i)
        {
            ItemStack itemstack = inv.getStackInSlot(i);
            ProgressionItem item = CapabilityHandler.get(itemstack);
            SynthSphere synth = CapabilityHandler.getSphere(sphere);

            if (synth != null)
            {
                if (item instanceof ItemXPSword)
                {
                	ItemXPSword sword = (ItemXPSword) item;

                	if (sword.mainAbility != null)
                	synth.type = sword.mainAbility.ability;
                }
                else if (item instanceof ItemXPTool)
                {
                	ItemXPTool tool = (ItemXPTool) item;
                	
                	if (tool.mainAbility != null)
                	synth.type = tool.mainAbility.ability;
                }

                if (item != null)
                {
                    synth.XpContained = item.totalXpEarned / 2;
                }
            }
        }

		return sphere;
	}

	@Override
	public int getRecipeSize() 
	{
		return 2;
	}

	@Override
	public ItemStack getRecipeOutput()
	{
		return new ItemStack(ItemHandler.synth_sphere);
	}

	@Override
	public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv)
	{
		return NonNullList.<ItemStack>func_191197_a(inv.getSizeInventory(), ItemStack.field_190927_a);
	}

}