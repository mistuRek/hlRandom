package pl.mistur.hlrandom.cmd;



import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.mistur.hlrandom.hlRandom;
import pl.mistur.hlrandom.data.Settings;
import pl.mistur.hlrandom.utils.Messages;

public class hlCMD implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("hlrandom") || cmd.getName().equalsIgnoreCase("hlr")) {
			if (args.length > 0) {
				if (sender.hasPermission("hlrandom.reload")) { 
					if (args[0].equalsIgnoreCase("reload")) {
						Settings.loadConfig();
						Settings.loadLang();
						
						if (sender instanceof Player) {
							sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&2All data have been properly loaded!"));
						}
					}
				}
				else {
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.getDontpermissions()));
				}
				if(args[0].equalsIgnoreCase("version")){
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c[hlRandom] &2Version: " + hlRandom.getInstance().getDescription().getVersion()));
					
				}
				if(args[0].equalsIgnoreCase("author")) {
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c[hlRandom] &2Author: " + hlRandom.getInstance().getDescription().getAuthors()));
				}
			}
			else {
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.getHlusage()));
			}
		}
		return false;
	}

}
