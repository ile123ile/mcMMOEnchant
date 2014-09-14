package com.blogspot.tuplestudios.enchantingskill.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import com.blogspot.tuplestudios.enchantingskill.enchanting.Enchanting.Enchantment;
import com.blogspot.tuplestudios.enchantingskill.enchanting.EnchantingUtils;

public class CombatListener implements Listener
{
	@EventHandler( priority = EventPriority.HIGH )
	public void onAttacked( EntityDamageByEntityEvent event )
	{
		if( event.getDamager() instanceof LivingEntity )
		{
			LivingEntity attacker = ( LivingEntity ) event.getDamager();
			Entity defender = event.getEntity();
			ItemStack item = attacker.getEquipment().getItemInHand();
			if( EnchantingUtils.hasEnchantment( item , Enchantment.SUPER ) )
			{
				defender.setVelocity( defender.getVelocity().add( new Vector( 0 , 2 , 0 ) ) );
				defender.setPassenger( attacker );
			}
		}
	}
}
