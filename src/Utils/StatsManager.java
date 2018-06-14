package Utils;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import main.Main;

public class StatsManager {
	
	public static void addPlayer(Player p) {
		String uniqueId = p.getUniqueId().toString();
		int i;
		if(getStat("player-amount") != null ) {
			i = Integer.parseInt(getStat("player-amount").toString());
		}else {
			setStat("player-amount", 0);
			i = 0;
		}
		setStat("players." + uniqueId + ".Player-ID", i);
		setStat("player-amount", new Integer(Integer.parseInt(getStat("player-amount").toString()) + 1));
	}
	
	public static Object getStat(String key) {
		return ConfigManager.getValue(ConfigManager.getFile(Main.plugin, "stats"), key);
	}
	
	public static boolean hasPlayer(String uniqueId) {
		if(hasStat("players." + uniqueId)) { return true; }else { return false; }
	}
	
	public static boolean hasStat(String key) {
		return ConfigManager.hasValue(ConfigManager.getFile(Main.plugin, "stats"), key);
	}

	public static boolean initialize() {
		FileConfiguration config = new YamlConfiguration();
		try {
			File f = new File(Main.plugin.getDataFolder(), "stats.yml");
			if(f.exists()) {
				config.load(f);
				return true;
			}else {
				ConfigManager.generateNewConfig(Main.plugin.getDataFolder(), "stats", Main.plugin.getName());
				return true;
			}
		}catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static void setStat(String key, Object value) {
		ConfigManager.setValue(ConfigManager.getFile(Main.plugin, "stats"), key, value);
	}
	
}
