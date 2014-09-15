package com.blogspot.tuplestudios.enchantingskill.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;

import com.blogspot.tuplestudios.enchantingskill.enchanting.Enchanting;
import com.blogspot.tuplestudios.enchantingskill.enchanting.EnchantingSkillManager;
import com.blogspot.tuplestudios.enchantingskill.enchanting.Enchanting.Enchantment;
import com.gmail.nossr50.api.SkillAPI;

public class EnchantmentTableHandler implements Listener
{
	
	@EventHandler( priority = EventPriority.NORMAL )
	public void onEnchant( EnchantItemEvent event )
	{
		EnchantingSkillManager enchantingManager = ( EnchantingSkillManager ) SkillAPI.getSkillManager(
				EnchantingSkillManager.enchantingSkill , event.getEnchanter() );
		if( event.isCancelled() )
		{
			for( Enchantment enchantment : Enchantment.values() )
			{
				for( Material material : enchantment.getEnchantables() )
				{
					if( event.getItem().getType() == material
							&& Enchanting
									.canEnchant( enchantment , event.getItem() , enchantingManager.getSkillLevel() ) )
					{
						event.setCancelled( false );
					}
				}
			}
		}
		enchantingManager.addEnchantments( event.getItem() , event.getExpLevelCost() );
	}
	
	@EventHandler( priority = EventPriority.NORMAL )
	public void onPrepareEnchantment( PrepareItemEnchantEvent event )
	{
		EnchantingSkillManager enchantingManager = ( EnchantingSkillManager ) SkillAPI.getSkillManager(
				EnchantingSkillManager.enchantingSkill , event.getEnchanter() );
		for( Enchantment enchantment : Enchantment.values() )
		{
			for( Material material : enchantment.getEnchantables() )
			{
				if( event.getItem().getType() == material
						&& Enchanting.canEnchant( enchantment , event.getItem() , enchantingManager.getSkillLevel() ) )
				{
					int base = ( int ) ( Math.random() * 8 + 1 ) + ( int ) ( event.getEnchantmentBonus() / 2f )
							+ ( int ) ( Math.random() * ( event.getEnchantmentBonus() + 1 ) );
					event.setCancelled( false );
					event.getExpLevelCostsOffered()[ 0 ] = ( int ) Math.min( Math.max( base / 3f , 1 ) , 30 );
					event.getExpLevelCostsOffered()[ 1 ] = ( int ) Math.min( ( ( base * 2 ) / 3f + 1 ) , 30 );
					event.getExpLevelCostsOffered()[ 2 ] = ( int ) Math.min(
							Math.max( base , event.getEnchantmentBonus() * 2d ) , 30d );
				}
			}
		}
	}
}
