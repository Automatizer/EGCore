package main;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import Utils.Lists;
import de.tr7zw.itemnbtapi.NBTItem;

public class Gem extends ItemStack{

	public ItemStack create(Material material, String name) {
		ItemStack is = new ItemStack(material);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(name);
		is.setItemMeta(im);
		return is;
	}
	
	public Enchant getEnchant() {
		NBTItem nbti = new NBTItem(this);
		if(nbti.hasKey("enchantName")) {
			Lists l = Lists.getInstance();
			for(Enchant e : l.enchants) {
				if(e.name == nbti.getString("enchantName")) {
					return e;
				}
			}
		}
		return null;
	}
	
	public int getLevel(Enchant e) {
		NBTItem nbti = new NBTItem(this);
		if(nbti.hasKey(e.name)) {
			return nbti.getInteger(e.name);
		}
		return 0;
	}
	
}
