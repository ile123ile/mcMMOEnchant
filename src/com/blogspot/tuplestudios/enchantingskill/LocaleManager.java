package com.blogspot.tuplestudios.enchantingskill;

import java.util.Locale;
import java.util.ResourceBundle;

import com.gmail.nossr50.locale.LocaleLoader;

public class LocaleManager
{
	
	public static final String bundleRoot = "com.blogspot.tuplestudios.enchantingskill.locale.locale";
	
	public static void addResourceBundles()
	{
		ResourceBundle defaultBundle = ResourceBundle.getBundle( bundleRoot , Locale.US );
		ResourceBundle bundle = null;
		try
		{
			bundle = ResourceBundle.getBundle( bundleRoot , Locale.getDefault() );
		}
		catch ( Exception e )
		{
		}
		LocaleLoader.addResourceBundle( bundle , defaultBundle );
	}
}
