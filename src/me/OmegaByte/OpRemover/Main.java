package me.OmegaByte.OpRemover;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, (Plugin)this);
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&c" + getDescription().getName() + "&8] &ahas been enabled."));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&aMade by OmegaByte"));
    }

    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&c" + getDescription().getName() + "&8] &chas been disabled."));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&cMade by OmegaByte"));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        if (p.isOp()) {
            Bukkit.dispatchCommand((CommandSender) Bukkit.getConsoleSender(), "deop " + p.getName());
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&nServer&8: &aOperator status has been removed from " + p.getName() + " after leaving the server."));
        }
    }
}
