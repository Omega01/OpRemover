package me.OmegaByte.OpRemover;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Reload implements CommandExecutor {
    private Main plugin;

    public Reload(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("opremover").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.isOp()) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("NoPermission")));
        return true;
            }
        if (args.length == 0) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&cOpRemover&8] &aMade by OmegaByte \nCurrent Version: " + plugin.getDescription().getVersion() + "\nSpigot: https://www.spigotmc.org/resources/opremover.81837/"));
            return true;
        }
        if (args.length == 1 &&
                args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("rl")) {
            this.plugin.reloadConfig();
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("ReloadMsg")));
            return true;
        }
        if (args.length >= 1) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cUse: /opremover reload"));
        }
        return true;
    }
}
