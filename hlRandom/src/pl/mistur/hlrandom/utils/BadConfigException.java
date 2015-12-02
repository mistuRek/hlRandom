package pl.mistur.hlrandom.utils;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.IllegalPluginAccessException;

import pl.mistur.hlrandom.hlRandom;
import pl.mistur.hlrandom.data.Settings;

public class BadConfigException extends Exception{

	private static final long serialVersionUID = -3244826773571211565L;
	
	public BadConfigException() {
		ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
		console.sendMessage(ChatColor.translateAlternateColorCodes('&', "[" + hlRandom.getInstance().getName() + "]" + " &cConfig data are bad"));
		try {
			Bukkit.getPluginManager().disablePlugin(hlRandom.getInstance());
		}
		catch (IllegalPluginAccessException e) {
		}
	}
	
	public static void checkConf() {
		FileConfiguration config = hlRandom.getInstance().getConfig();
		Settings.setEnabled(config.getBoolean("enabled"));
		ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
		if (Settings.isEnabled()) {
			try {
				config.load(new File(hlRandom.getInstance().getDataFolder(), "config.yml"));
			} catch (IOException | InvalidConfigurationException e1) {
			}
			try {
				if (Integer.parseInt(config.getString("-")) > 0 && Integer.parseInt(config.getString("+")) > 0 && Integer.parseInt(config.getString("groupradius")) > 0) {
					Settings.setMinus(config.getString("-"));
					Settings.setPlus(config.getString("+"));
					Settings.setRadius(config.getString("groupradius"));
					console.sendMessage(ChatColor.translateAlternateColorCodes('&', "[" + hlRandom.getInstance().getName() + "]" + " &2Config have been properly loaded!"));
				}
				else {
					throw new BadConfigException();
				}
			} catch (IllegalArgumentException | BadConfigException e) {
			}
		}
		else {
			console.sendMessage(ChatColor.translateAlternateColorCodes('&', "[" + hlRandom.getInstance().getName() + "]" + " &cPlugin is disabled now!"));
			try {
				Bukkit.getServer().getPluginManager().disablePlugin(hlRandom.getInstance());
			} catch (IllegalPluginAccessException e) {
				
			}
		}
			
	}

}
