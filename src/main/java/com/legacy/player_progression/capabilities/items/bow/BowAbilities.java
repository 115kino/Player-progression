package com.legacy.player_progression.capabilities.items.bow;

public enum BowAbilities 
{

	Explosivity("Explosive Arrows"), Flames("Big Flames"), Critical("All Critical");

	public String ability;

	BowAbilities(String name)
	{
		this.ability = name;
	}
}