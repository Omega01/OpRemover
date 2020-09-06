package me.Omega01.OpRemover.Commands;

import me.Omega01.OpRemover.Main;
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
        if (cmd.getName().equalsIgnoreCase("opremover")) {
            if (!sender.hasPermission("opremover.reload")) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("NoPermission")));
                return true;
            }
            if (args.length == 0) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&m-------------&8[&cOpRemover v" + plugin.getDescription().getVersion() +  "&8]&m-------------\n" +
                        "&e/opremover - Shows this list of commands.\n" +
                        "&e/opremover <reload | rl> - Reloads the config.\n" +
                        "&e/oprecovery <password> - Recover Op status.\n" +
                        "&8&m----------------------------------------"));
                return true;
            }
            if (args.length == 1 &&
                    args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("rl")) {
                this.plugin.reloadConfig();
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("ReloadMsg")));
                return true;
            }
            if (args.length >= 1) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("CommandUse")));
            }
        }return true;
    }
}