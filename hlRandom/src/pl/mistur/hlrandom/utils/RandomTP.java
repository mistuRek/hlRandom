package pl.mistur.hlrandom.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import pl.mistur.hlrandom.data.Settings;

public class RandomTP {
	
	private static List<Player> pl = new ArrayList<Player>();
	
	public static void randomTP(int plus, int minus, Player p) {
		Random generator = new Random();
		int x = generator.nextInt(plus) - generator.nextInt(minus);
		int z = generator.nextInt(plus) - generator.nextInt(minus);
		int y = p.getWorld().getHighestBlockYAt(x, z);
		Block highest = p.getWorld().getBlockAt(x, y - 1, z);
		
		boolean safe = false;
		while (!safe) {
			for (String s: Settings.getBadblocks()) {
				if (highest.getType() == Material.getMaterial(s)) {
					return;
				}
				else {
					safe = true;
				}
			}
			safe = true;

		}
		Location loc = new Location(p.getWorld(), x, y + 0.5D, z);
		p.teleport(loc);
		String s = new String(Messages.getTeleportMessage());
		s = s.replaceAll("%x%", String.valueOf(p.getLocation().getX()));
		s = s.replaceAll("%z%", String.valueOf(p.getLocation().getZ()));
		p.sendMessage(ChatColor.translateAlternateColorCodes('&', s));
   
	}
	
	public static void randomGroupTP(int plus, int minus, Player p) {
		Random generator = new Random();
		int x = generator.nextInt(plus) - generator.nextInt(minus);
		int z = generator.nextInt(plus) - generator.nextInt(minus);
		int y = p.getWorld().getHighestBlockYAt(x, z);
		Block highest = p.getWorld().getBlockAt(x, y - 1, z);
		
		boolean safe = false;
		while (!safe) {
			for (String s: Settings.getBadblocks()) {
				if (highest.getType() == Material.getMaterial(s)) {
					return;
				}
				else {
					safe = true;
				}
			}
			safe = true;

		}
		Location loc = new Location(p.getWorld(), x, y + 0.5D, z);
		for (Player player : pl) {
			player.teleport(loc);
			String s = new String(Messages.getTeleportMessage());
			s = s.replaceAll("%x%", String.valueOf(player.getLocation().getX()));
			s = s.replaceAll("%z%", String.valueOf(player.getLocation().getZ()));
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', s));
		}
		pl.clear();
   
	}
	
	
	  public static List<Player> getPlayersInRadius(int radius, Location l) {
		pl.clear();
	    for (Player p : l.getWorld().getPlayers()) {
	      if (l.distance(p.getLocation()) <= radius) {
	        pl.add(p);
	      }
	    }
	    return pl;
	  }

}
