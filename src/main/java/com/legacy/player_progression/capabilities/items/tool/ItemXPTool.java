package com.legacy.player_progression.capabilities.items.tool;

import java.util.List;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;

import com.legacy.player_progression.capabilities.items.ProgressionItem;
import com.legacy.player_progression.util.MethodUtil;

public class ItemXPTool extends ProgressionItem
{

	protected ToolMaterial material;

	public ToolAbilities mainAbility, subAbility;

	public ItemXPTool(ItemStack stack)
	{
		super(stack);

		this.material = MethodUtil.determineMaterial(((ItemTool)stack.getItem()).getToolMaterialName());
		this.xpNeeded = ((this.material.getHarvestLevel() + 1) * 10) + (int)(this.material.getEfficiencyOnProperMaterial() * 10);
	}

	@Override
	public void levelUp()
	{
		super.levelUp();

		if (this.level == this.currentMaxLevel)
		{
			this.mainAbility = ToolAbilities.getRandomAbility();
		}
	}

	@Override
	public void displayText(List<String> display)
	{
		super.displayText(display);

		if (this.mainAbility != null)
		{
			display.add("");
			display.add("Main Ability:");
			display.add(this.mainAbility.ability);
		}

		if (this.subAbility != null)
		{
			display.add("");
			display.add("Sub Ability:");
			display.add(this.subAbility.ability);
		}
	}

	@Override
	protected void writeExtraData(NBTTagCompound compound)
	{
		if (this.mainAbility != null) compound.setInteger("main_ability", this.mainAbility.id);
		if (this.subAbility != null) compound.setInteger("sub_ability", this.subAbility.id);
	}

	@Override
	protected void readExtraData(NBTTagCompound compound)
	{
		if (compound.hasKey("main_ability")) this.mainAbility = ToolAbilities.getAbility(compound.getInteger("main_ability"));
		if (compound.hasKey("sub_ability")) this.subAbility = ToolAbilities.getAbility(compound.getInteger("sub_ability"));
	}

}