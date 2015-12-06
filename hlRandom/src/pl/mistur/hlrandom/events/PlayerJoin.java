package pl.mistur.hlrandom.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import pl.mistur.hlrandom.data.Settings;
import pl.mistur.hlrandom.utils.RandomTP;

public class PlayerJoin implements Listener{
	
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onJoin(PlayerJoinEvent e) {
		if (Settings.isFirstjointeleport()) {
			if (!e.getPlayer().hasPlayedBefore()) {
				RandomTP.randomTP(Settings.getPlus(), Settings.getMinus(), e.getPlayer());
				return;
			}
			else {
				return;
			}
		}
		
	}
	

}
