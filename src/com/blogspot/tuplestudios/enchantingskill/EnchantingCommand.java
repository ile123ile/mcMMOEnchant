package com.blogspot.tuplestudios.enchantingskill;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import com.gmail.nossr50.api.SkillAPI;
import com.gmail.nossr50.commands.skills.SkillCommand;
import com.gmail.nossr50.datatypes.skills.SkillType;
import com.gmail.nossr50.locale.LocaleLoader;
import com.gmail.nossr50.skills.alchemy.Alchemy.Tier;
import com.gmail.nossr50.util.Permissions;

public class EnchantingCommand extends SkillCommand
{
	
	public boolean canElderKnowledge;
	private int tier;
	
	public EnchantingCommand( SkillType skill )
	{
		super( skill );
	}
	
	@Override
	protected void dataCalculations( Player player , float skillValue , boolean isLucky )
	{
		// ELDER KNOWLEDGE
		if( canElderKnowledge )
		{
			EnchantingSkillManager enchanting = ( EnchantingSkillManager ) SkillAPI.getSkillManager(
					EnchantingSkillManager.enchantingSkill , player );
			tier = enchanting.getTier();
		}
		
	}
	
	@Override
	protected void permissionsCheck( Player player )
	{
		canElderKnowledge = Permissions.secondaryAbilityEnabled( player , EnchantingSkillManager.elderKnowledge );
		
	}
	
	@Override
	protected List< String > effectsDisplay()
	{
		List< String > messages = new ArrayList< String >();
		
		if( canElderKnowledge )
		{
			messages.add( LocaleLoader.getString( "Effects.Template" , LocaleLoader.getString( "Enchanting.Effect.0" ) ,
					LocaleLoader.getString( "Enchanting.Effect.1" ) ) );
		}
		
		return messages;
	}
	
	@Override
	protected List< String > statsDisplay( Player player , float skillValue , boolean hasEndurance , boolean isLucky )
	{
		List< String > messages = new ArrayList< String >();
		
		if( canElderKnowledge )
		{
			messages.add( LocaleLoader.getString( "Enchanting.ElderKnowledge.Rank" , tier , Tier.values().length ) );
		}
		
		return messages;
	}
	
}
