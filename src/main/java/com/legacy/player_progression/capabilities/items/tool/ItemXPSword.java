package com.legacy.player_progression.capabilities.items.tool;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

import com.legacy.player_progression.capabilities.items.ProgressionItem;
import com.legacy.player_progression.util.MethodUtil;

public class ItemXPSword extends ProgressionItem
{

	protected ToolMaterial material;

	public ItemXPSword(ItemStack stack)
	{
		super(stack);

		this.material = MethodUtil.determineMaterial(((ItemSword)stack.getItem()).getToolMaterialName());
		this.xpNeeded = ((this.material.getHarvestLevel() + 1) * 10) + (int)(this.material.getEfficiencyOnProperMaterial() * 10);
	}

}