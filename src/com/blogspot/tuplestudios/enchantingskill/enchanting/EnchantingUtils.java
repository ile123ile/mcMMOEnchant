package com.blogspot.tuplestudios.enchantingskill.enchanting;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.blogspot.tuplestudios.enchantingskill.enchanting.Enchanting.Enchantment;

public class EnchantingUtils
{
	public static void addEnchantment( ItemStack item , Enchantment enchantment , int level )
	{
		if( level < 1 )
		{
			return;
		}
		ArrayList< String > lore;
		int enchantmentIndex = -1;
		ItemMeta itemMeta = item.getItemMeta();
		if( itemMeta.hasLore() )
		{
			lore = new ArrayList< String >( itemMeta.getLore() );
			enchantmentIndex = getEnchantmentIndex( lore , enchantment.getName() );
		}
		else
		{
			lore = new ArrayList< String >();
		}
		String enchantmentName = createEnchantmentName( enchantment , level );
		if( enchantmentIndex != -1 )
		{
			lore.set( enchantmentIndex , enchantmentName );
		}
		else
		{
			lore.add( 0 , enchantmentName );
		}
		itemMeta.setLore( lore );
		item.setItemMeta( itemMeta );
	}
	
	private static String createEnchantmentName( Enchantment enchantment , int level )
	{
		return ChatColor.RESET.toString() + ChatColor.GRAY.toString() + enchantment.getName() + " "
				+ integerToNumerals( level );
	}
	
	public static boolean hasEnchantment( ItemStack item , Enchantment enchantment )
	{
		return getEnchantmentIndex( item.getItemMeta().getLore() , enchantment.getName() ) != -1;
	}
	
	private static int getEnchantmentIndex( List< String > lore , String enchantment )
	{
		for( int i = 0; i < lore.size(); i++ )
		{
			if( isEnchantment( lore.get( i ) , enchantment ) )
			{
				return i;
			}
		}
		return -1;
	}
	
	private static boolean isEnchantment( String enchantmentString , String enchantment )
	{
		if( enchantmentString.length() < 5 )
		{
			return false;
		}
		if( !enchantmentString.contains( ChatColor.RESET.toString() + ChatColor.GRAY.toString() ) )
		{
			return false;
		}
		String[] enchantmentStrings = enchantmentString.split( " " );
		if( enchantmentStrings.length < 2 )
		{
			return false;
		}
		String numeral = enchantmentStrings[ enchantmentStrings.length - 1 ];
		if( !isNumeral( numeral ) )
		{
			return false;
		}
		return enchantmentString.substring( 0 , enchantmentString.length() - numeral.length() - 1 ).equals(
				ChatColor.RESET.toString() + ChatColor.GRAY.toString() + enchantment );
	}
	
	private enum Numeral
	{
		I( 1 ), IV( 4 ), V( 5 ), IX( 9 ), X( 10 ), XL( 40 ), L( 50 ), XC( 90 ), C( 100 ), CD( 400 ), D( 500 ), CM( 900 ), M(
				1000 );
		
		private int value;
		
		private Numeral( int whenSection )
		{
			this.value = whenSection;
		}
		
		public int getValue()
		{
			return value;
		}
	}
	
	private static boolean isNumeral( String numerals )
	{
		return numeralsToInteger( numerals ) != -1;
	}
	
	private static int numeralsToInteger( String numerals )
	{
		char[] numeralArray = numerals.toUpperCase().toCharArray();
		int toRet = 0;
		try
		{
			for( int i = 0; i < numeralArray.length; i++ )
			{
				char c = numeralArray[ i ];
				Numeral lettersSection = Numeral.valueOf( String.valueOf( c ) );
				int toIncrease = lettersSection.value;
				if( i != numeralArray.length - 1 )
				{
					Numeral nextLettersSection = Numeral.valueOf( String.valueOf( numeralArray[ i + 1 ] ) );
					if( nextLettersSection.getValue() > lettersSection.getValue() )
					{
						toIncrease = nextLettersSection.getValue() - lettersSection.getValue();
						i++ ;
					}
				}
				toRet += toIncrease;
			}
		}
		catch ( Exception e )
		{
			return -1;
		}
		return integerToNumerals( toRet ).equalsIgnoreCase( numerals ) ? toRet : -1;
	}
	
	private static String integerToNumerals( int integer )
	{
		if( integer < 1 )
		{
			throw new IllegalArgumentException( Integer.toString( integer ) );
		}
		int intTemp = integer;
		StringBuffer buffer = new StringBuffer();
		Numeral[] numeralValues = Numeral.values();
		for( int numeralIndex = numeralValues.length - 1; numeralIndex >= 0; numeralIndex-- )
		{
			Numeral numeral = numeralValues[ numeralIndex ];
			while( intTemp >= numeral.getValue() )
			{
				buffer.append( numeral.toString() );
				intTemp -= numeral.getValue();
			}
		}
		return buffer.toString();
	}
}
