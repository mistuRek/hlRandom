package pl.mistur.hlrandom.events;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import pl.mistur.hlrandom.data.Settings;
import pl.mistur.hlrandom.utils.Messages;
import pl.mistur.hlrandom.utils.RandomTP;

public class PlayerInteract implements Listener{
	
	@EventHandler
	public void onClick(PlayerInteractEvent event) {
		if (event.getAction() == Action.RIGHT_CLICK_BLOCK && (event.getClickedBlock().getType() == Material.STONE_BUTTON)) {
			Location sponge1 = event.getClickedBlock().getLocation().add(1.0D, 0.0D, 0.0D);
			Location sponge2 = event.getClickedBlock().getLocation().add(-1.0D, 0.0D, 0.0D);
			Location sponge3 = event.getClickedBlock().getLocation().add(0.0D, 0.0D, 1.0D);
			Location sponge4 = event.getClickedBlock().getLocation().add(0.0D, 0.0D, -1.0D);
			if ((sponge1.getBlock().getType() == Material.SPONGE) || (sponge2.getBlock().getType() == Material.SPONGE) || (sponge3.getBlock().getType() == Material.SPONGE) || (sponge4.getBlock().getType() == Material.SPONGE)) {
				RandomTP.randomTP(Settings.getPlus(), Settings.getMinus(), event.getPlayer());
				String s = new String(Messages.getTeleportMessage());
				s = s.replaceAll("%x%", String.valueOf(event.getPlayer().getLocation().getX()));
				s = s.replaceAll("%z%", String.valueOf(event.getPlayer().getLocation().getZ()));
				event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', s));
				
			}	
		}
		else if(event.getAction() == Action.RIGHT_CLICK_BLOCK && (event.getClickedBlock().getType() == Material.JUKEBOX)) {
			RandomTP.getPlayersInRadius(3, event.getClickedBlock().getLocation());
			RandomTP.randomGroupTP(Settings.getPlus(), Settings.getMinus(), event.getPlayer());
			
		}
	}

}
