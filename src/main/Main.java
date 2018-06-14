package main;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import Utils.ConfigManager;
import Utils.Logger;
import Utils.Severity;
import Utils.StatsManager;
import listeners.JoinEvent;

public class Main extends JavaPlugin{
	
	public static Plugin plugin;

	public void onEnable() {
		plugin = this;
		new JoinEvent(this);
		if(ConfigManager.loadMain()) {
			Logger.log("Main config loaded successfully.", Severity.SUCCESS);
		}else {
			Logger.log("Main config load failed, aborting plugin startup sequence...", Severity.ERROR);
			Logger.log("Maybe check the file for corruption?", Severity.ERROR);
			return;
		}
		if(StatsManager.initialize()) {
			Logger.log("Successfully loaded stats file.", Severity.SUCCESS);
		}else {
			Logger.log("Stats file could not be loaded, ignoring...", Severity.ERROR);
			Logger.log("Maybe check the file for corruption?", Severity.ERROR);
		}
		Logger.log("EGCore enabled.", Severity.SUCCESS);
	}
	
	public void onDisable() {
		Logger.log("EGCore disabled.", Severity.SUCCESS);
	}
	
}
