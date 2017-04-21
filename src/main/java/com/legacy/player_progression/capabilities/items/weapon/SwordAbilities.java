package com.legacy.player_progression.capabilities.items.weapon;

import java.util.Random;

public enum SwordAbilities
{

	Homerun(0, "Full Swing"), WitchHunter(1, "Witch Hunter"), AnimalButcher(2, "Animal Butcher"), XPRobber(3, "XP Robber"), NetherBoost(4, "Nether Boost"), EndBoost(5, "End Boost"), OuterBoost(6, "Outer Boost"); 

	public int id;

	public String ability;

	SwordAbilities(int id, String name)
	{
		this.id = id;
		this.ability = name;
	}

	public static SwordAbilities getAbility(String id)
	{
		return id.contains("XP Robber") ? XPRobber : id.contains("Animal Butcher") ? AnimalButcher : id.contains("Witch Hunter") ? WitchHunter : id.contains("Full Swing") ? Homerun : id.contains("Nether Boost") ? NetherBoost : id.contains("End Boost") ? EndBoost : id.contains("Outer Boost") ? OuterBoost : null;
	}

	public static SwordAbilities getAbility(int id)
	{
		return id == 6 ? OuterBoost : id == 5 ? EndBoost : id == 4 ? NetherBoost : id == 3 ? XPRobber : id == 2 ? AnimalButcher : id == 1 ? WitchHunter : Homerun;
	}

	public static SwordAbilities getRandomAbility()
	{
		Random rand = new Random();

		return getAbility(rand.nextInt(values().length));
	}
}