package pl.mistur.hlrandom.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryType;

public class PlayerCraft implements Listener{
	
	@EventHandler
	public void onCraft(CraftItemEvent e) {
	    if ((e.getInventory().getType().equals(InventoryType.WORKBENCH)) && (e.getSlotType().toString().equalsIgnoreCase("RESULT")) && (e.getCurrentItem().getType().name().equalsIgnoreCase("JUKEBOX"))) {
	  	      e.setCancelled(true);
	  	      e.getWhoClicked().closeInventory();
	  	    }
		
	}

}
