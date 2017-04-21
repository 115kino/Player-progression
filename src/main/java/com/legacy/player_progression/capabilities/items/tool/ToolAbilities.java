package com.legacy.player_progression.capabilities.items.tool;

import java.util.Random;

public enum ToolAbilities 
{

	SpeedBoost(0, "Break Master"), DamageBoost(1, "Weaponized"), Torched(2, "Flame Hit"),  Flame(3, "Flame Touch"), XP(4, "XP Finder"), MountRepair(5, "Mount Repair"), Revive(6, "Second Chance");

	public String ability;

	public int id;

	ToolAbilities(int i, String name)
	{
		this.id = i;
		this.ability = name;
	}

	public static ToolAbilities getAbility(String id)
	{
		return id.contains("Second Chance") ? Revive : id.contains("Mount Repair") ? MountRepair : id.contains("XP Finder") ? XP : id.contains("Flame Touch") ? Flame : id.contains("Flame Hit") ? Torched : id.contains("Weaponized") ? DamageBoost : id.contains("Break Master") ? SpeedBoost : null;
	}

	public static ToolAbilities getAbility(int id)
	{
		return id == 6 ? Revive : id == 5 ? MountRepair : id == 4 ? XP : id == 3 ? Flame : id == 2 ? Torched : id == 1 ? DamageBoost : SpeedBoost;
	}

	public static ToolAbilities getRandomAbility()
	{
		Random rand = new Random();

		return getAbility(rand.nextInt(values().length));
	}
}