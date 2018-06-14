package listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

import Utils.StatsManager;

public class JoinEvent implements Listener{
	
	Plugin pl;

	public JoinEvent(Plugin p) {
		pl = p;
		Bukkit.getPluginManager().registerEvents(this, p);
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if(StatsManager.hasPlayer(p.getUniqueId().toString())) {
			setStats(p);
		}else {
			StatsManager.addPlayer(p);
			setStats(p);
		}
	}
	
	private void setStats(Player p) {
		String s = "players." + p.getUniqueId().toString() + ".login-amount";
		if(StatsManager.hasStat(s)) {
			StatsManager.setStat(s, new Integer(Integer.parseInt(StatsManager.getStat(s).toString()) + 1));
		}else {
			StatsManager.setStat(s, 1);
		}
		StatsManager.setStat("players." + p.getUniqueId().toString() + ".latest-name", p.getName());
	}
	
}
