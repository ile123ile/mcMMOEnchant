package com.blogspot.tuplestudios.enchantingskill.enchanting;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.blogspot.tuplestudios.enchantingskill.ConfigManager;

public class Enchanting
{
	public enum Enchantment
	{
		SUPER( "Magical" , Tier.FOUR , Material.STICK );
		
		private String name;
		private Tier tier;
		private Material[] enchantables;
		
		private Enchantment( String name , Tier tier , Material... enchantables )
		{
			this.name = name;
			this.tier = tier;
			this.enchantables = enchantables;
		}
		
		public String getName()
		{
			return name;
		}
		
		public Tier getTier()
		{
			return tier;
		}
		
		public Material[] getEnchantables()
		{
			return enchantables;
		}
	}
	
	public enum Tier
	{
		EIGHT( 8 ), SEVEN( 7 ), SIX( 6 ), FIVE( 5 ), FOUR( 4 ), THREE( 3 ), TWO( 2 ), ONE( 1 );
		
		int numerical;
		
		private Tier( int numerical )
		{
			this.numerical = numerical;
		}
		
		public int toNumerical()
		{
			return numerical;
		}
		
		public static Tier fromNumerical( int numerical )
		{
			for( Tier tier : Tier.values() )
			{
				if( tier.toNumerical() == numerical )
				{
					return tier;
				}
			}
			return null;
		}
		
		protected int getLevel()
		{
			return ConfigManager.getElderKnowledgeTierLevel( this );
		}
	}
	
	private Enchanting()
	{
	}
	
	public static boolean canEnchant( Enchantment enchantment , ItemStack item , int enchantingLevel )
	{
		if( EnchantingUtils.hasEnchantment( item ) )
		{
			return false;
		}
		if( enchantingLevel > enchantment.getTier().getLevel() )
		{
			Material itemMaterial = item.getType();
			for( Material mat : enchantment.getEnchantables() )
			{
				if( mat == itemMaterial )
				{
					return true;
				}
			}
		}
		return false;
	}
}
