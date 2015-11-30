package pl.mistur.hlrandom;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.plugin.java.JavaPlugin;

import pl.mistur.hlrandom.cmd.hlCMD;
import pl.mistur.hlrandom.cmd.hlrTP;
import pl.mistur.hlrandom.data.Settings;
import pl.mistur.hlrandom.events.PlayerInteract;
import pl.mistur.hlrandom.utils.Messages;

public class hlRandom extends JavaPlugin {
	
	private static hlRandom instance;
	
	public static hlRandom getInstance() {
		if (instance == null) {
			return new hlRandom();
		}
		return instance;
	}
	
	public void onEnable() {
		instance = this;
		Bukkit.getPluginManager().registerEvents(new PlayerInteract(), this);
		getCommand("hlrandom").setExecutor(new hlCMD());
		getCommand("rtp").setExecutor(new hlrTP());
		Settings.create();
			try {
				Settings.loadConfig();
			} catch (IOException | InvalidConfigurationException e) {
			}
		if (!Settings.isEnabled()) {
			Bukkit.getPluginManager().disablePlugin(this);
		}
		else {
			Messages.setInvalidarguments("&cError: Invalid arguments");
			Messages.setDontpermissions("&cError: You don't have permission to do that");
			Messages.setIsnotnumber("&cError: Arguments must be a number");
			Messages.setGreater("&cError: Integer must be a greater than 0");
		}
		
	}
	

	
	

}
