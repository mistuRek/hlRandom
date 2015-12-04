package pl.mistur.hlrandom.cmd;

import org.bukkit.Bukkit;
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
							}
							else {
								sender.sendMessage(Messages.getMessage("invalidarguments"));
								
							}
						}
						else if (args.length == 2) {
							if (!args[0].equalsIgnoreCase("-get")) {
								Player p = (Player) sender;
								try {
									if (Integer.valueOf(args[0]) > 0 && Integer.valueOf(args[1]) > 0) {
										RandomTP.randomTP(Integer.valueOf(args[0]), Integer.valueOf(args[1]), p);	
									}
									else {
										sender.sendMessage(Messages.getMessage("mustgreater"));
									}
								} catch (IllegalArgumentException e){
									sender.sendMessage(Messages.getMessage("notnumber"));
								}
							}
							else if (args[0].equalsIgnoreCase("-get")) {
								@SuppressWarnings("deprecation")
								Player player = Bukkit.getPlayer(args[1]);
								if (player != null) {
									RandomTP.randomTP(Settings.getPlus(), Settings.getMinus(), player);
								}
								else {
									String s = Messages.getMessage("playerexists");
									s = s.replaceAll("%player%", args[1]);
									sender.sendMessage(s);
								}
							}	
							else {
								sender.sendMessage(Messages.getMessage("invalidarguments"));
							}
						}
						else if (args.length == 3) {
							@SuppressWarnings("deprecation")
							Player player = Bukkit.getPlayer(args[2]);
							if (player != null) {
								try {
									if (Integer.valueOf(args[0]) > 0 && Integer.valueOf(args[1]) > 0) {
										RandomTP.randomTP(Integer.valueOf(args[0]), Integer.valueOf(args[1]), player);	
									}
									else {
										sender.sendMessage(Messages.getMessage("mustgreater"));
									}
								} catch (IllegalArgumentException e){
									sender.sendMessage(Messages.getMessage("notnumber")); }
							}
							else {
								String s = Messages.getMessage("playerexists");
								s = s.replaceAll("%player%", args[2]);
								sender.sendMessage(s);
							}
						}	
						else {
							sender.sendMessage(Messages.getMessage("invalidarguments"));
						}
					}
					else {
						sender.sendMessage(Messages.getMessage("rtpusage"));
					}
				}
				else {
					if (args.length > 0) { 
						if (args.length == 3) {
							@SuppressWarnings("deprecation")
							Player player = Bukkit.getPlayer(args[2]);
							if (player != null) {
								try {
									if (Integer.valueOf(args[0]) > 0 && Integer.valueOf(args[1]) > 0) {
										RandomTP.randomTP(Integer.valueOf(args[0]), Integer.valueOf(args[1]), player);	
									}
									else {
										sender.sendMessage(Messages.getMessage("mustgreater"));
									}
								} catch (IllegalArgumentException e){
									sender.sendMessage(Messages.getMessage("notnumber")); }
							}
							else {
								String s = Messages.getMessage("playerexists");
								s = s.replaceAll("%player%", args[2]);
								sender.sendMessage(s);
							}
						}
						else if (args.length > 1) { 
							if (args.length < 2) {
								if (args[0].equalsIgnoreCase("-get")) {
									@SuppressWarnings("deprecation")
									Player player = Bukkit.getPlayer(args[1]);
									if (player != null) {
										RandomTP.randomTP(Settings.getPlus(), Settings.getMinus(), player);
									}
									else {
										String s = Messages.getMessage("playerexists");
										s = s.replaceAll("%player%", args[2]);
										sender.sendMessage(s);
									}
								}	
								else {
									sender.sendMessage(Messages.getMessage("invalidarguments"));
								}
							}
						}
						else {
							sender.sendMessage(Messages.getMessage("invalidarguments"));
						}
					}
					else {
						sender.sendMessage(Messages.getMessage("consolertpusage"));
					}
				}
			}
			else {
				sender.sendMessage(Messages.getMessage("permissions"));
			}
		}
		return false;
	}
	
	

}
