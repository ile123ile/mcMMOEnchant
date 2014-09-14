package com.blogspot.tuplestudios.enchantingskill;

import java.util.Random;

import org.bukkit.Color;
import org.bukkit.inventory.ItemStack;

import com.gmail.nossr50.api.AbilityAPI;
import com.gmail.nossr50.api.SkillAPI;
import com.gmail.nossr50.datatypes.player.McMMOPlayer;
import com.gmail.nossr50.datatypes.skills.SecondaryAbility;
import com.gmail.nossr50.datatypes.skills.SkillType;
import com.gmail.nossr50.datatypes.skills.SkillType.SkillUseType;
import com.gmail.nossr50.skills.SkillManager;

public class EnchantingSkillManager extends SkillManager
{
	
	public static SecondaryAbility elderKnowledge;
	
	public static SkillType enchantingSkill;
	
	public EnchantingSkillManager( McMMOPlayer mcMMOPlayer )
	{
		super( mcMMOPlayer , enchantingSkill );
	}
	
	public static void createEnchantingSkill()
	{
		elderKnowledge = AbilityAPI.createSecondaryAbility( "ELDER_KNOWLEDGE" , "ENCHANTING" , 500 , 80 );
		enchantingSkill = SkillAPI.createSkill( "ENCHANTING" , mcMMOEnchant.INSTANCE , EnchantingSkillManager.class , EnchantingCommand.class ,
				false , Color.AQUA , SkillUseType.MISC , elderKnowledge );
	}
	
	public int getTier()
	{
		for( Enchanting.Tier tier : Enchanting.Tier.values() )
		{
			if( getSkillLevel() >= tier.getLevel() )
			{
				return tier.toNumerical();
			}
		}
		
		return 0;
	}
	
	public void addEnchantments( ItemStack item , int levelsSpent )
	{
		if( Enchanting.canEnchant( Enchanting.Enchantment.SUPER , item , getSkillLevel() ) )
		{
			Random rand = new Random();
			EnchantingUtils.addEnchantment( item , Enchanting.Enchantment.SUPER ,
					( int ) Math.max( Math.min( ( rand.nextGaussian() + levelsSpent / 10 ) , 3 ) , 0d ) );
		}
	}
	
}
