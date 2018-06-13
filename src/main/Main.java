package main;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import Utils.ConfigManager;
import Utils.Logger;
import Utils.Severity;

public class Main extends JavaPlugin{
	
	public static Plugin plugin;

	public void onEnable() {
		plugin = this;
		if(ConfigManager.load()) {
			Logger.log("Main config loaded successfully.", Severity.SUCCESS);
		}else {
			Logger.log("Main config load failed, aborting plugin startup sequence...", Severity.ERROR);
			Logger.log("Maybe check the file for corruption?", Severity.ERROR);
			return;
		}
		Logger.log("EGCore enabled.", Severity.SUCCESS);
	}
	
	public void onDisable() {
		Logger.log("EGCore disabled.", Severity.SUCCESS);
	}
	
}
