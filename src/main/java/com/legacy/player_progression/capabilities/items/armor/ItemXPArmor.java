package com.legacy.player_progression.capabilities.items.armor;

import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;

import com.legacy.player_progression.capabilities.items.ProgressionItem;

public class ItemXPArmor extends ProgressionItem
{

	protected ArmorMaterial material;

	public ItemXPArmor(ItemStack stack) 
	{
		super(stack);

		ItemArmor armor = (ItemArmor) stack.getItem();

		this.material = armor.getArmorMaterial();

		this.xpNeeded = (int)((this.material.getToughness() + 1) * 18) + (this.material.getDamageReductionAmount(armor.getEquipmentSlot()) + 1) * 18;

		this.currentMaxLevel = 20;
	}

}