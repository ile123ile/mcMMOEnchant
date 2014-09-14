package com.blogspot.tuplestudios.enchantingskill;

import org.bukkit.configuration.file.FileConfiguration;

public class ConfigManager
{
	public static int getElderKnowledgeTierLevel( Enchanting.Tier tier )
	{
		return getConfig().getInt( "Elder_Knowledge.Rank_Levels.Rank_" + tier.toNumerical(), tier.toNumerical() * 100 );
	}
	
	private static FileConfiguration getConfig()
	{
		return mcMMOEnchant.INSTANCE.getConfig();
	}
}
