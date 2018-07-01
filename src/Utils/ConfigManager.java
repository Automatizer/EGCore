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
	static FileConfiguration mainConfig = pl.getConfig();
	
	public static void generateNewConfig(File path, String name, String source) {
		FileConfiguration config = new YamlConfiguration();
		try {
			config.save(new File(path, name + ".yml"));
			Logger.log(source + " generated new configuration file " + name + ".yml.", Severity.WARN);
		} catch (IOException e) {
			Logger.log(source + " Failed to generate new configuration file " + name + ".yml.", Severity.ERROR);
			e.printStackTrace();
		}
	}
	
	public static FileConfiguration getConfigFile(Plugin plugin, String fileName) {
		FileConfiguration config = new YamlConfiguration();
		try {
			File f = new File(plugin.getDataFolder(), fileName + ".yml");
			if(!f.exists()) {
				generateNewConfig(plugin.getDataFolder(), fileName, plugin.getName());
			}
			config.load(f);
		}catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
		return config;
	}
	
	public static File getFile(Plugin plugin, String name) {
		File f = new File(plugin.getDataFolder(), name + ".yml");
		if(!f.exists()) {
			generateNewConfig(plugin.getDataFolder(), name, plugin.getName());
		}
		return f;
	}
	
	public static Object getValue(File file, String key) {
		FileConfiguration config = new YamlConfiguration();
		try {
			config.load(file);
			if(hasValue(file, key.toString())) {
				return config.get(key);
			}else return null;
		}catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean hasValue(File file, String key) {
		FileConfiguration config = new YamlConfiguration();
		try {
			config.load(file);
			if(config.contains(key)) { return true; }else { return false; }
		}catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean loadMain() {
		try {
			mainConfig.addDefault("prefix", "[EGCore] ");
			mainConfig.options().copyDefaults(true);
			pl.saveConfig();
		}catch(Exception e) {
			return false;
		}
		return true;
	}
	
	public static void setDefaults(File file, ArrayList<String> settings) {
		for(String s : settings) {
			if(s.contains(";")) {
				String[] option = s.split(";");
				setValue(file, option[0], option[1]);
			}
		}
	}
	
	public static void setValue(File file, String key, Object value) {
		FileConfiguration config = new YamlConfiguration();
		try {
			config.load(file);
			config.set(key, value);
			config.save(file);
		}catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}
	
}
