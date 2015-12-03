package pl.mistur.hlrandom.data;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import pl.mistur.hlrandom.hlRandom;
import pl.mistur.hlrandom.utils.BadConfigException;
import pl.mistur.hlrandom.utils.BadLangException;
import pl.mistur.hlrandom.utils.Lang;

public class Settings {
	
	private static File mainfolder = hlRandom.getInstance().getDataFolder();
	private static File configfile = new File(mainfolder, "config.yml");
	private static File lang = new File(mainfolder, "langs");
	public static List<String> badblocks = new ArrayList<>();
	private static int minus;
	private static int plus;
	private static int radius;
	
	public static void create() {
		ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
		if (!mainfolder.exists()) {
			console.sendMessage(ChatColor.translateAlternateColorCodes('&', "[" + hlRandom.getInstance().getName() + "]" + " &cCreating data folder..."));
			mainfolder.mkdir();
		}
		if (!lang.exists()) {
			console.sendMessage(ChatColor.translateAlternateColorCodes('&', "[" + hlRandom.getInstance().getName() + "]" + " &cCreating langs folder..."));
			lang.mkdir();
		}
		if (!configfile.exists()) {
			console.sendMessage(ChatColor.translateAlternateColorCodes('&', "[" + hlRandom.getInstance().getName() + "]" + " &cCreating config..."));
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
		BadConfigException.checkConf();
	}
	
	public static void loadLang() {
		BadLangException.checkLang();
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

	public static void setMinus(String min) {
		minus = Integer.parseInt(min);
	}
	
	public static void setPlus(String max) {
		plus = Integer.parseInt(max);
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

	public static List<String> getBadblocks() {
		return new ArrayList<String>(badblocks);
	}

	public static void addBadBlock(String badblock) {
		Settings.badblocks.add(badblock);
	}
	
	public static void setBadBlocks() {
		FileConfiguration config = hlRandom.getInstance().getConfig();
		Settings.badblocks = config.getStringList("badblocks");
	}
	







	
	

}
