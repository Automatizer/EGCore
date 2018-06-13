package Utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import main.Main;

public class ConfigManager {

	static Plugin pl = Main.plugin;
	static FileConfiguration config = pl.getConfig();
	
	public static boolean load() {
		try {
			config.addDefault("prefix", "[EGCore] ");
			config.options().copyDefaults(true);
			pl.saveConfig();
		}catch(Exception e) {
			return false;
		}
		return true;
	}
	
	public static FileConfiguration getConfigFile(Plugin p, String fileName) {
		FileConfiguration config = new YamlConfiguration();
		try {
			File f = new File(p.getDataFolder(), fileName + ".yml");
			config.load(f);
		}catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
		return config;
	}
	
	public static void generateExtraConfig(File path, String name, ArrayList<String> defaultOptions, String source) {
		FileConfiguration config = new YamlConfiguration();
		try {
			config.save(new File(path, name + ".yml"));
			Logger.log(source + " Successfully generated new configuration file " + name + ".yml.", Severity.SUCCESS);
		} catch (IOException e) {
			Logger.log(source + " Failed to generate new configuration file " + name + ".yml.", Severity.ERROR);
			e.printStackTrace();
		}
		if((!defaultOptions.isEmpty()) || (defaultOptions != null)) {
			for(String str : defaultOptions) {
				String[] s = str.split(";");
				config.addDefault(s[1], s[2]);
			}
		}
	}
	
}
