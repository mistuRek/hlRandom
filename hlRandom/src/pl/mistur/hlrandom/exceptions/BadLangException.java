package pl.mistur.hlrandom.exceptions;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.IllegalPluginAccessException;

import pl.mistur.hlrandom.hlRandom;

public class BadLangException extends Exception{

	private static final long serialVersionUID = 106624942943882232L;
	
	
	public BadLangException() {
		ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
		console.sendMessage(ChatColor.translateAlternateColorCodes('&', "[" + hlRandom.getInstance().getName() + "]" + " &cLangs data are bad"));
		try {
			Bukkit.getPluginManager().disablePlugin(hlRandom.getInstance());
		}
		catch (IllegalPluginAccessException e) {}
		
	}
	
	
	
	


}
