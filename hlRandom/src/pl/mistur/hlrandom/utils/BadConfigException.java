package pl.mistur.hlrandom.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.IllegalPluginAccessException;

import pl.mistur.hlrandom.hlRandom;

public class BadConfigException extends Exception{

	private static final long serialVersionUID = -3244826773571211565L;
	
	public BadConfigException() {
		ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
		console.sendMessage(ChatColor.translateAlternateColorCodes('&', "[" + hlRandom.getInstance().getName() + "]" + " &cDane w configu sa nieprawidlowe!"));
		try {
			Bukkit.getPluginManager().disablePlugin(hlRandom.getInstance());
		}
		catch (IllegalPluginAccessException e) {
			
		}
	}

}
