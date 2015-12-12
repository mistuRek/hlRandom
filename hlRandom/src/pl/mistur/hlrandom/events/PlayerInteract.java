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
		if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (event.getPlayer().hasPermission("hlrandom.useteleport")) {
				for (String s: Settings.getButtons()) {
					if (Material.getMaterial(s) != null) {
						if (event.getClickedBlock().getType() == Material.valueOf(s)) {
							Button button = (Button) event.getClickedBlock().getState().getData();
							for (String b : Settings.getTeleportBlock()) {
								if (Material.getMaterial(b) != null) {
									if (event.getClickedBlock().getRelative(button.getAttachedFace()).getType() == Material.valueOf(b)) {
										RandomTP.randomTP(Settings.getPlus(), Settings.getMinus(), event.getPlayer());	
									}
								}
									
							}
						}
					}
				}
			}
			if (event.getPlayer().hasPermission("hlrandom.usegroupteleport")) {
				for (String s: Settings.getGroupBlocks()) {
					if (Material.getMaterial(s) != null) {
						if (event.getClickedBlock().getType() == Material.valueOf(s)) {
							RandomTP.getPlayersInRadius(3, event.getClickedBlock().getLocation());
							RandomTP.randomGroupTP(Settings.getPlus(), Settings.getMinus(), event.getPlayer());
							
						}
					}
				}
			}

		}
	}

}
