package com.blogspot.tuplestudios.enchantingskill;

import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.nossr50.api.SkillAPI;

public class mcMMOEnchant extends JavaPlugin
{
	
	public static mcMMOEnchant INSTANCE;
	
	@Override
	public void onEnable()
	{
		INSTANCE = this;
		getServer().getPluginManager().registerEvents( new EnchantmentTableHandler() , this );
		LocaleManager.addResourceBundles();
		EnchantingSkillManager.createEnchantingSkill();
		SkillAPI.loadNewSkills();
	}
	
}
