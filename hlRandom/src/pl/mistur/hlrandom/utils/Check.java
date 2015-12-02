package pl.mistur.hlrandom.utils;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;

import pl.mistur.hlrandom.hlRandom;
import pl.mistur.hlrandom.data.Settings;

public class Check {
	
	public static void checkConf(){
		if (Settings.isEnabled()) {
			FileConfiguration config = hlRandom.getInstance().getConfig();
			try {
				config.load(new File(hlRandom.getInstance().getDataFolder(), "config.yml"));
			} catch (IOException | InvalidConfigurationException e1) {
			}
			ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
			try {
				if (Integer.parseInt(config.getString("-")) > 0 && Integer.parseInt(config.getString("+")) > 0 && Integer.parseInt(config.getString("groupradius")) > 0) {
					Settings.setMinus(config.getString("-"));
					Settings.setPlus(config.getString("+"));
					Settings.setRadius(config.getString("groupradius"));
					console.sendMessage(ChatColor.translateAlternateColorCodes('&', "[" + hlRandom.getInstance().getName() + "]" + " &2All data have been properly loaded!"));
				}
				else {
					throw new BadConfigException();
				}
			} catch (IllegalArgumentException | BadConfigException e) {
			}
		}
	}
}
