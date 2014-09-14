package com.blogspot.tuplestudios.enchantingskill.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;

import com.blogspot.tuplestudios.enchantingskill.enchanting.EnchantingSkillManager;
import com.gmail.nossr50.api.SkillAPI;

public class EnchantmentTableHandler implements Listener
{
	
	@EventHandler( priority = EventPriority.NORMAL )
	public void onEnchant( EnchantItemEvent event )
	{
		EnchantingSkillManager enchantingManager = ( EnchantingSkillManager ) SkillAPI.getSkillManager(
				EnchantingSkillManager.enchantingSkill , event.getEnchanter() );
		enchantingManager.addEnchantments( event.getItem() , event.getExpLevelCost() );
	}
	
}
