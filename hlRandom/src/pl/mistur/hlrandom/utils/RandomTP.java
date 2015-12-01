package pl.mistur.hlrandom.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class RandomTP {
	
	private static List<Player> pl = new ArrayList<Player>();
	public static List<Player> teleportes = new ArrayList<Player>();
	
	public static void randomTP(int plus, int minus, Player p) {
		Random generator = new Random();
		int x = generator.nextInt(plus) - generator.nextInt(minus);
		int z = generator.nextInt(plus) - generator.nextInt(minus);
		Location loc = new Location(p.getWorld(), x, p.getWorld().getHighestBlockYAt((int)x, (int)z), z);
		
        p.teleport(loc);
        loc.setY(p.getLocation().getY() + 6.0D);
		p.teleport(loc);
   
	}
	
	public static void randomGroupTP(int plus, int minus, Player p) {
		Random generator = new Random();
		int x = generator.nextInt(plus) - generator.nextInt(minus);
		int z = generator.nextInt(plus) - generator.nextInt(minus);
		Location loc = new Location(p.getWorld(), x, p.getWorld().getHighestBlockYAt((int)x, (int)z), z);
		for (Player player : pl) {
			player.teleport(loc);
	        loc.setY(player.getLocation().getY() + 5.0D);
	        player.teleport(loc);
			String s = new String(Messages.getTeleportMessage());
			s = s.replaceAll("%x%", String.valueOf(player.getLocation().getX()));
			s = s.replaceAll("%z%", String.valueOf(player.getLocation().getZ()));
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', s));
			teleportes.add(player);
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
