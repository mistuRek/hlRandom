package pl.mistur.hlrandom.cmd;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.mistur.hlrandom.data.Settings;
import pl.mistur.hlrandom.utils.Messages;
import pl.mistur.hlrandom.utils.RandomTP;

public class hlrTP implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("rtp")) {
			if (sender.hasPermission("hlrandom.rtp")) {
				if (sender instanceof Player) {
					if (args.length > 0) {
						if (args.length < 2) {
							if (args[0].equalsIgnoreCase("-get")) {
								Player p = (Player) sender;
								RandomTP.randomTP(Settings.getPlus(), Settings.getMinus(), p);
								String s = new String(Messages.getTeleportMessage());
								s = s.replaceAll("%x%", String.valueOf(p.getLocation().getX()));
								s = s.replaceAll("%z%", String.valueOf(p.getLocation().getZ()));
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', s));
							}
							else {
								sender.sendMessage(ChatColor.translateAlternateColorCodes('&',Messages.getInvalidarguments()));
								
							}
						}
						else if (args.length == 2) {
							Player p = (Player) sender;
							try {
								if (Integer.valueOf(args[0]) > 0 && Integer.valueOf(args[1]) > 0) {
									RandomTP.randomTP(Integer.valueOf(args[0]), Integer.valueOf(args[1]), p);
									String s = new String(Messages.getTeleportMessage());
									s = s.replaceAll("%x%", String.valueOf(p.getLocation().getX()));
									s = s.replaceAll("%z%", String.valueOf(p.getLocation().getZ()));
									p.sendMessage(ChatColor.translateAlternateColorCodes('&', s));	
								}
								else {
									sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.getGreater()));
								}
							} catch (IllegalArgumentException e){
								sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.getIsnotnumber()));
							}
						}
						else {
							sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.getInvalidarguments()));
						}
					}
					else {
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.getInvalidarguments()));
					}
				}
			}
			else {
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.getInvalidarguments()));
			}
		}
		return false;
	}
	
	

}
