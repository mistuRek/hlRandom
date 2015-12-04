package pl.mistur.hlrandom.utils;
 
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;


import pl.mistur.hlrandom.hlRandom;

 
public class UpdateChecker {
	
	private static UpdateResult result;
    private static String local = hlRandom.getInstance().getDescription().getVersion();
    private String www = "http://misturek.pl/projects/hlrandom/version/version.txt";
    private String latest;
	
    public UpdateChecker() {
    	
    }
    
    public void checkUpdate() {
		try {
    		URL url = new URL(www);
			Scanner s = new Scanner(url.openStream());
			latest = s.nextLine();
			if (local.equals(latest)) {
				setResult(UpdateResult.UPDATE_NO);
				updatemsg(this);
			}
			else {
				setResult(UpdateResult.UPDATE_AVAILABLE);
				updatemsg(this);
			}
			s.close();
		} catch (IOException e) {
			setResult(UpdateResult.UPDATE_DISABLED);
		}
	}

	public UpdateResult getResult() {
		return result;
	}

	public static void setResult(UpdateResult result) {
		UpdateChecker.result = result;
	}
	
	public String getLatest() {
		return this.latest;
	}
	
	public void updatemsg(UpdateChecker check) {
		ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
		if (check.getResult() == UpdateResult.UPDATE_AVAILABLE) {
			console.sendMessage(ChatColor.translateAlternateColorCodes('&', "[" + hlRandom.getInstance().getName() + "]" + "&6 Update is available!"));
			console.sendMessage(ChatColor.translateAlternateColorCodes('&', "[" + hlRandom.getInstance().getName() + "]" + "&6 New version: &c" + check.getLatest()));
		}
		else if (check.getResult() == UpdateResult.UPDATE_NO){
			console.sendMessage(ChatColor.translateAlternateColorCodes('&', "[" + hlRandom.getInstance().getName() + "]" + " &6Update is not available!"));
		}
	}	
}