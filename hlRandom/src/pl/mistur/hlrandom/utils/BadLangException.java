package pl.mistur.hlrandom.utils;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.IllegalPluginAccessException;

import pl.mistur.hlrandom.hlRandom;
import pl.mistur.hlrandom.data.Settings;

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
	
	public static void checkLang() {
		if (Bukkit.getServer().getPluginManager().getPlugin("hlRandom").isEnabled()) {
			FileConfiguration config = hlRandom.getInstance().getConfig();
			Lang l = Lang.get(config.getString("lang"));
			FileConfiguration langconfig;
			File la = new File(Settings.getLangsF(), l.getName() + ".yml");
			langconfig = new YamlConfiguration();
			try {
				langconfig.load(la);
			} catch (IOException | InvalidConfigurationException e) {}
				
			if (langconfig.getString("teleportmessage") != null 
					&& langconfig.getString("invalidarguments") != null 
					&& langconfig.getString("permissions") != null 
					&& (langconfig.getString("notnumber") != null 
					&& langconfig.getString("mustgreater") != null 
					&& langconfig.getString("notplayer") != null
					&& langconfig.getString("hlusage") != null
					&& langconfig.getString("rtpusage") != null)) {
				Messages.setTeleportMessage(langconfig.getString("teleportmessage"));
				Messages.setInvalidarguments(langconfig.getString("invalidarguments"));
				Messages.setDontpermissions(langconfig.getString("permissions"));
				Messages.setIsnotnumber(langconfig.getString("notnumber"));
				Messages.setGreater(langconfig.getString("mustgreater"));
				Messages.setOnlyPlayer(langconfig.getString("notplayer"));
				Messages.setHlusage(langconfig.getString("hlusage"));
				Messages.setRtpusage(langconfig.getString("rtpusage"));
				ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
				console.sendMessage(ChatColor.translateAlternateColorCodes('&', "[" + hlRandom.getInstance().getName() + "]" + " &2Lang have been properly loaded!"));
			}
			else {
				try {
					throw new BadLangException();
				} catch (BadLangException e) {}
			}
		}
	}

}
