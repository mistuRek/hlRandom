package pl.mistur.hlrandom.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import pl.mistur.hlrandom.utils.RandomTP;

public class PlayerDisconnect implements Listener{
	
	
	@EventHandler
	public void onPlayerDisconnect(PlayerQuitEvent e) {
		if (RandomTP.teleportes.contains(e.getPlayer())) {
			RandomTP.teleportes.remove(e.getPlayer());
		}
	}

}
