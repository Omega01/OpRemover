package me.OmegaByte.OpRemover;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class Main extends JavaPlugin implements Listener {

    public void onEnable() {
        int pluginId = 8395;
        MetricsLite metrics = new MetricsLite(this, pluginId);
        new Reload(this);
        Bukkit.getPluginManager().registerEvents(this, (Plugin)this);
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&c" + getDescription().getName() + "&8] &ahas been enabled."));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&aMade by OmegaByte"));
        saveDefaultConfig();
    }

    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&c" + getDescription().getName() + "&8] &chas been disabled."));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&cMade by OmegaByte"));
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        List<String> OpPlayers = getConfig().getStringList("OpPlayerList");
        if (p.isOp() && OpPlayers.contains(p.getName())) {
            p.setOp(false);
            if (getConfig().getBoolean("LogToConsole")) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("ConsoleLog").replace("%p", p.getName())));
            }
        }
    }
}
