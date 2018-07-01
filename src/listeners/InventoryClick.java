package listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.Plugin;

import Utils.EnchantManager;
import main.Gem;

public class InventoryClick implements Listener{
	
	Plugin pl;

	public InventoryClick(Plugin p) {
		Bukkit.getPluginManager().registerEvents(this, p);
		pl = p;
	}
	
	public void onInventoryClick(InventoryClickEvent e) {
		EnchantManager em = new EnchantManager();
		if(e.getWhoClicked() instanceof Player) {
			if(e.isRightClick()) {
				if((e.getCursor() != null) && (em.isGem(e.getCursor()))) {
					Gem g = (Gem) e.getCursor();
					if(g.getEnchant().allowedMats.contains(e.getCurrentItem().getType())) {
						em.enchantItem(e.getCurrentItem(), g.getEnchant(), g.getLevel(g.getEnchant()));
					}
				}
			}
		}
	}
	
}
