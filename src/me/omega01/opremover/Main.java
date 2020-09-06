package me.omega01.opremover;

import me.omega01.opremover.commands.OpRecovery;
import me.omega01.opremover.commands.Reload;
import me.omega01.opremover.bstats.MetricsLite;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class Main extends JavaPlugin implements Listener {

    OpRecovery opRecovery;
    Reload reload;

    public void onEnable() {
        this.opRecovery = new OpRecovery(this);
        this.reload = new Reload(this);
        int pluginId = 8395;
        MetricsLite metrics = new MetricsLite(this, pluginId);
        Bukkit.getPluginManager().registerEvents(this, this);
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&c" + getDescription().getName() + "&8] &aHas been enabled (Version v" + getDescription().getVersion() + ")"));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&aMade by Omega01"));
        saveDefaultConfig();
    }

    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&c" + getDescription().getName() + "&8] &aHas been disabled (Version v" + getDescription().getVersion() + ")"));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&cMade by Omega01"));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        List<String> OpPlayers = getConfig().getStringList("OpPlayerList");
        if (p.isOp() && OpPlayers.contains(p.getName())) {
            p.setOp(false);
            if (getConfig().getBoolean("UseGamemode")) {
                p.setGameMode(GameMode.valueOf(getConfig().getString("Gamemode")));
            }
            if (getConfig().getBoolean("LogToConsole")) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("ConsoleLog").replace("%p", p.getName())));
            }
        }
    }
}
