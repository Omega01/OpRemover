package me.OmegaByte.OpRemover;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class Main extends JavaPlugin implements Listener {


    public void onEnable() {
        new Reload(this);
            getLogger().info(ChatColor.translateAlternateColorCodes('&', "&8[&cOpRemover&8] &aThe version you are running is outdated, you can download the latest update at https://www.spigotmc.org/resources/opremover.81837/"));
        Bukkit.getPluginManager().registerEvents(this, (Plugin)this);
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&c" + getDescription().getName() + "&8] &ahas been enabled."));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&aMade by OmegaByte"));
        saveDefaultConfig();
    }

    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&c" + getDescription().getName() + "&8] &chas been disabled."));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&cMade by OmegaByte"));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        List<String> OpPlayers = getConfig().getStringList("OpPlayerList");
        if (p.isOp() && OpPlayers.contains(p.getName())) {
                p.setOp(false);
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&nServer&8: &aOperator status has been removed from " + p.getName() + " after leaving the server."));
            }
    }

    @EventHandler
    public void onShutdown(PlayerKickEvent e) {
        Player p = e.getPlayer();
        List<String> OpPlayers = getConfig().getStringList("OpPlayerList");
        if (p.isOp() && OpPlayers.contains(p.getName())) {
            p.setOp(false);
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&nServer&8: &aOperator status has been removed from " + p.getName() + " after being kicked from the server."));
        }
    }
}
