package pl.mistur.hlrandom.data;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import pl.mistur.hlrandom.hlRandom;
import pl.mistur.hlrandom.utils.Check;
import pl.mistur.hlrandom.utils.Lang;
import pl.mistur.hlrandom.utils.Messages;

public class Settings {
	
	private static File mainfolder = hlRandom.getInstance().getDataFolder();
	private static File configfile = new File(mainfolder, "config.yml");
	private static File lang = new File(mainfolder, "langs");
	private static boolean enabled;
	private static int minus;
	private static int plus;
	private static int radius;
	
	public static void create() {
		if (!mainfolder.exists()) {
			mainfolder.mkdir();
		}
		if (!lang.exists()) {
			lang.mkdir();
		}
		if (!configfile.exists()) {
			hlRandom.getInstance().saveDefaultConfig();
		}
		FileConfiguration config = hlRandom.getInstance().getConfig();
		Lang l = Lang.get(config.getString("lang"));
		if (!new File(getLangsF(), l.getName() + ".yml").exists()) {
			if (l.getName().equalsIgnoreCase("pl")) {
				copy(hlRandom.getInstance().getResource("pl.yml"), new File(getLangsF(), l.getName() + ".yml"));
			}
			else {
				copy(hlRandom.getInstance().getResource("en.yml"), new File(getLangsF(), l.getName() + ".yml"));
			}
		}
	}
	
	public static void loadConfig(){
		FileConfiguration config = hlRandom.getInstance().getConfig();
		setEnabled(config.getBoolean("enabled"));
		ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
		if (isEnabled()) {
			Check.checkConf();
		}
		else {
			console.sendMessage(ChatColor.translateAlternateColorCodes('&', "[" + hlRandom.getInstance().getName() + "]" + " &cPlugin is disabled now!"));
		}
	}
	
	public static void loadLang() {
		FileConfiguration config = hlRandom.getInstance().getConfig();
		Lang l = Lang.get(config.getString("lang"));
		FileConfiguration langconfig;
		File la = new File(getLangsF(), l.getName() + ".yml");
		langconfig = new YamlConfiguration();
		try {
			langconfig.load(la);
		} catch (IOException | InvalidConfigurationException e) {
			
		}
		Messages.setTeleportMessage(langconfig.getString("teleportmessage"));
		Messages.setInvalidarguments(langconfig.getString("invalidarguments"));
		Messages.setDontpermissions(langconfig.getString("permissions"));
		Messages.setIsnotnumber(langconfig.getString("notnumber"));
		Messages.setGreater(langconfig.getString("mustgreater"));
		Messages.setOnlyPlayer(langconfig.getString("notplayer"));
	}
	
	public static String getLang() {
		FileConfiguration config = hlRandom.getInstance().getConfig();
		return config.getString("lang");
	}

	public static int getMinus() {
		return minus;
	}
	
	public static int getPlus() {
		return plus;
	}
	
	public static boolean isEnabled() {
		return enabled;
	}

	public static void setMinus(String min) {
		minus = Integer.parseInt(min);
	}
	
	public static void setPlus(String max) {
		plus = Integer.parseInt(max);
	}
	
	public static void setEnabled(boolean enabled) {
		Settings.enabled = enabled;
	}
	
	public static File getLangsF() {
		return lang;
	}
	
	private static void copy(InputStream in, File file) {
	    try {
	        OutputStream out = new FileOutputStream(file);
	        byte[] buf = new byte[1024];
	        int len;
	        while((len=in.read(buf))>0){
	            out.write(buf,0,len);
	        }
	        out.close();
	        in.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	public static int getRadius() {
		return radius;
	}

	public static void setRadius(String radius) {
		Settings.radius = Integer.parseInt(radius);
	}
	







	
	

}
