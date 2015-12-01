package pl.mistur.hlrandom.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import pl.mistur.hlrandom.utils.RandomTP;

public class PlayerDamage implements Listener{
	
    @EventHandler
    public void onPlayerDamage(EntityDamageEvent e) {
            if (e.getEntity() instanceof Player) {
                    Player p = (Player) e.getEntity();
                    if (e.getCause() == DamageCause.FALL && RandomTP.teleportes.contains(p)) {
                            e.setDamage(0.0);
                            RandomTP.teleportes.remove(p);
                    }
            }
    }
}
