package pl.mistur.hlrandom.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import pl.mistur.hlrandom.hlRandom;
import pl.mistur.hlrandom.data.Settings;
import pl.mistur.hlrandom.exceptions.BadLangException;
import pl.mistur.hlrandom.lang.Lang;

public class Messages {
	
	private static List<String> mess = new ArrayList<>();
	private static HashMap<String, String> messages = new HashMap<>();
	
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
			
			Messages.registerMessage("teleportmessage");
			Messages.registerMessage("invalidarguments");
			Messages.registerMessage("permissions");
			Messages.registerMessage("notnumber");
			Messages.registerMessage("mustgreater");
			Messages.registerMessage("notplayer");
			Messages.registerMessage("hlusage");
			Messages.registerMessage("rtpusage");
			Messages.registerMessage("playerexists");
			Messages.registerMessage("consolertpusage");
			for (String s : Messages.getMessages()) {
				if (langconfig.getString(s) != null) {
					add(s, langconfig.getString(s));
				}
				else {
					try {
						throw new BadLangException();
					} catch (BadLangException e) {return;}
				}
			}
			ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
			console.sendMessage(ChatColor.translateAlternateColorCodes('&', "[" + hlRandom.getInstance().getName() + "]" + " &2Messages have been properly loaded!"));
		}
	}
	
	private static void registerMessage(String name) {
		if (!mess.contains(name)) {
			mess.add(name);
		}
		else {
			return;
		}
		
	}
	
	private static ArrayList<String> getMessages() {
		return new ArrayList<String>(mess);
	}
	
	public static String getMessage(String message){
		String s = messages.get(message);
		if(s == null){
			return new String(ChatColor.translateAlternateColorCodes('&', "&cInvalid message"));
		}
		else {
			return ChatColor.translateAlternateColorCodes('&', s);
		}
	}
	
	private static void add(String name, String row) {
		messages.put(name, row);	
	}


}
