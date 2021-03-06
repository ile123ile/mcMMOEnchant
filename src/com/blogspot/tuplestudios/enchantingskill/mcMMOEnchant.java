package com.blogspot.tuplestudios.enchantingskill;

import org.bukkit.plugin.java.JavaPlugin;

import com.blogspot.tuplestudios.enchantingskill.enchanting.EnchantingSkillManager;
import com.blogspot.tuplestudios.enchantingskill.listeners.CombatListener;
import com.blogspot.tuplestudios.enchantingskill.listeners.EnchantmentTableHandler;
import com.gmail.nossr50.api.SkillAPI;

public class mcMMOEnchant extends JavaPlugin
{
	
	public static mcMMOEnchant INSTANCE;
	
	@Override
	public void onEnable()
	{
		INSTANCE = this;
		getServer().getPluginManager().registerEvents( new EnchantmentTableHandler() , this );
		getServer().getPluginManager().registerEvents( new CombatListener() , this );
		LocaleManager.addResourceBundles();
		EnchantingSkillManager.createEnchantingSkill();
		SkillAPI.loadNewSkills();
	}
	
}
