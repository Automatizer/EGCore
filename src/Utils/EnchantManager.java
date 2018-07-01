package Utils;

import java.util.List;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.tr7zw.itemnbtapi.NBTItem;
import main.Enchant;

public class EnchantManager {

	public boolean isGem(ItemStack is) {
		NBTItem nbti = new NBTItem(is);
		if(nbti.hasKey("isGem")) {
			return true;
		}else {
			return false;
		}
	}
	
	public void enchantItem(ItemStack is, Enchant e, int level) {
		ItemMeta im = is.getItemMeta();
		List<String> lore = im.getLore();
		int l = 1;
		if(hasEnchant(is, e)) {
			if(getEnchantLevel(is, e) > 0) {
				l = getEnchantLevel(is, e);
			}
			if(l == level) l++;
			if(l < level) l = level;
			if(l > level) return;
		}
		NBTItem nbti = new NBTItem(is);
		nbti.setInteger(e.name, l);
		lore.add(e.name + " " + Integer.toString(l));
		im.setLore(lore);
		is.setItemMeta(im);
	}
	
	public void registerEnchants() {
		EventManager em = new EventManager();
		em.callAll("register");
	}
	
	public boolean hasEnchant(ItemStack is, Enchant e) {
		NBTItem nbti = new NBTItem(is);
		if(nbti.hasKey(e.name)) {
			return true;
		}
		return false;
	}
	
	public int getEnchantLevel(ItemStack is, Enchant e) {
		NBTItem nbti = new NBTItem(is);
		if(nbti.hasKey(e.name)) {
			return nbti.getInteger(e.name);
		}
		return 0;
	}
	
}
