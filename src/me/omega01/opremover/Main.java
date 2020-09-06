package me.omega01.opremover;

import me.omega01.opremover.bstats.MetricsLite;
import me.omega01.opremover.commands.OpRecovery;
import me.omega01.opremover.commands.Reload;
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

    @Override
    public void onEnable() {
        this.opRecovery = new OpRecovery(this);
        this.reload = new Reload(this);
        int pluginId = 8395;
        MetricsLite metrics = new MetricsLite(this, pluginId);
        Bukkit.getPluginManager().registerEvents(this, this);
        getLogger().info("Has been enabled (Version v" + getDescription().getVersion() + ")");
        getLogger().info("Made by Omega01");
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        getLogger().info("Has been disabled (Version v" + getDescription().getVersion() + ")");
        getLogger().info("Made by Omega01");
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
                System.out.println(ChatColor.translateAlternateColorCodes('&', getConfig().getString("ConsoleLog").replace("%p", p.getName())));
            }
        }
    }
}