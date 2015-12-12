package pl.mistur.hlrandom.events;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryType;

import pl.mistur.hlrandom.data.Settings;

public class PlayerCraft implements Listener{
	
	@EventHandler
	public void onCraft(CraftItemEvent e) {
	    if ((e.getInventory().getType().equals(InventoryType.WORKBENCH)) && (e.getSlotType().toString().equalsIgnoreCase("RESULT"))) {
	    	for (String s : Settings.getGroupBlocks()) {
	    		if ((e.getCurrentItem().getType() == Material.valueOf(s))) {
	    			e.setCancelled(true);
	    		}
	    	}
	    }
		
	}

}
