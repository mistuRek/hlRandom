package pl.mistur.hlrandom;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import pl.mistur.hlrandom.cmd.hlCMD;
import pl.mistur.hlrandom.cmd.hlrTP;
import pl.mistur.hlrandom.data.Settings;
import pl.mistur.hlrandom.events.PlayerCraft;
import pl.mistur.hlrandom.events.PlayerDamage;
import pl.mistur.hlrandom.events.PlayerInteract;
import pl.mistur.hlrandom.utils.metrics.Metrics;

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
		Bukkit.getPluginManager().registerEvents(new PlayerDamage(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerCraft(), this);
		getCommand("hlrandom").setExecutor(new hlCMD());
		getCommand("rtp").setExecutor(new hlrTP());
		Settings.create();
		Settings.loadConfig();
		Settings.loadLang();
		if (!Settings.isEnabled()) {
			Bukkit.getPluginManager().disablePlugin(this);
		}
		else {
			try {
				Metrics metrics = new Metrics(this);
				metrics.start();
			} catch (IOException e) {
			}
		}
		
	}

	

	
	

}
