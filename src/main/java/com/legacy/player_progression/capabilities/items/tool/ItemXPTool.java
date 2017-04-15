package com.legacy.player_progression.capabilities.items.tool;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;

import com.legacy.player_progression.capabilities.items.ProgressionItem;
import com.legacy.player_progression.util.MethodUtil;

public class ItemXPTool extends ProgressionItem
{

	protected ToolMaterial material;

	public ItemXPTool(ItemStack stack)
	{
		super(stack);

		this.material = MethodUtil.determineMaterial(((ItemTool)stack.getItem()).getToolMaterialName());
		this.xpNeeded = ((this.material.getHarvestLevel() + 1) * 10) + (int)(this.material.getEfficiencyOnProperMaterial() * 10);
	}

}