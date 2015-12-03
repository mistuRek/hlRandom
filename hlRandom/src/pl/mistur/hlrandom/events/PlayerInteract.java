package pl.mistur.hlrandom.events;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.material.Button;

import pl.mistur.hlrandom.data.Settings;
import pl.mistur.hlrandom.utils.RandomTP;

public class PlayerInteract implements Listener{
	
	@EventHandler
	public void onClick(PlayerInteractEvent event) {
		if (event.getAction() == Action.RIGHT_CLICK_BLOCK && (event.getClickedBlock().getType() == Material.STONE_BUTTON)) {
            Button button = (Button) event.getClickedBlock().getState().getData();
			if (event.getClickedBlock().getRelative(button.getAttachedFace()).getType() == Material.SPONGE) {
				RandomTP.randomTP(Settings.getPlus(), Settings.getMinus(), event.getPlayer());
				
			}	
		}
		else if(event.getAction() == Action.RIGHT_CLICK_BLOCK && (event.getClickedBlock().getType() == Material.JUKEBOX)) {
			RandomTP.getPlayersInRadius(3, event.getClickedBlock().getLocation());
			RandomTP.randomGroupTP(Settings.getPlus(), Settings.getMinus(), event.getPlayer());
			
		}
	}

}
