package Utils;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import main.Main;

public class Logger {
	
	static Plugin pl = Main.plugin;
	
	public static void log(String s, Severity sev) {
		FileConfiguration config = ConfigManager.getConfigFile(pl, "config");
		Bukkit.getConsoleSender().sendMessage(config.getString("prefix") + sev.getColor() + s);
	}
	
	public static void logPlayer(Player p, String s, Severity sev) {
		p.sendMessage(sev.getColor() + s);
	}

}
