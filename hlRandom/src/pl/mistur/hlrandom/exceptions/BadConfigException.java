package pl.mistur.hlrandom.exceptions;

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
		catch (IllegalPluginAccessException e) {}
	}
	
	public static void checkConf() {
		FileConfiguration config = hlRandom.getInstance().getConfig();
		ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
		try {
			config.load(new File(hlRandom.getInstance().getDataFolder(), "config.yml"));
			Settings.setBadBlocks();
			Settings.setButtons();
			Settings.setGroupBlocks();
			Settings.setTeleportBlocks();
		} catch (IOException | InvalidConfigurationException e) {}
		Settings.setUpdatecheck(config.getBoolean("update-checker"));
		Settings.setFirstjointeleport(config.getBoolean("first-join-teleport"));
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
		} catch (IllegalArgumentException | BadConfigException e) {}
			
	}

}
