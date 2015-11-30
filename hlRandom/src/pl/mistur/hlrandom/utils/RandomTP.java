package pl.mistur.hlrandom.utils;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class RandomTP {
	
	public static void randomTP(int plus, int minus, Player p) {
		Random generator = new Random();
		int x = generator.nextInt(plus) - generator.nextInt(minus);
		int z = generator.nextInt(plus) - generator.nextInt(minus);
		int y = 256;
		Location loc = new Location(p.getWorld(), x, p.getWorld().getHighestBlockYAt((int)x, (int)z), z);
		
		boolean safe = false;
        while (safe == false) {
            	loc = new Location(p.getWorld(), x, y, z);
            	if (loc.getBlock().getType() != Material.AIR) {
		        	  safe = true;
		          } 
            	else {
            		y-- ;
            	}
        }
        p.teleport(loc);
        loc.setY(p.getLocation().getY() + 6.0D);
		p.teleport(loc);
   
	}

}
