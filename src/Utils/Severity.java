package Utils;

import org.bukkit.ChatColor;

public enum Severity {
	
	SUCCESS, DEBUG, WARN, ERROR;
	
	public ChatColor getColor() {
		switch(this.toString().toLowerCase()) {
		case "success": return ChatColor.GREEN;
		case "debug": return ChatColor.WHITE;
		case "warn": return ChatColor.YELLOW;
		case "error": return ChatColor.RED;
		default: return null;
		}
	}

}
