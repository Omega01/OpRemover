package me.omega01.opremover.commands;

import me.omega01.opremover.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class OpRecovery implements CommandExecutor {
    private Main plugin;

    public OpRecovery(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("oprecovery").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> OpPlayers = plugin.getConfig().getStringList("OpPlayerList");
        if (cmd.getName().equalsIgnoreCase("oprecovery")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "This command is only for players!");
                return true;
            }
            if (sender.isOp()) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("AlreadyOp")));
                return true;
            }
            if (!OpPlayers.contains(sender.getName())) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("NoPermission")));
            } else {
                if (!plugin.getConfig().getBoolean("UsePassword")) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("OptionDisabled")));
                } else {
                    if (args.length == 0) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("PasswordUse")));
                        return true;
                    }
                    if (args.length == 1) {
                        if (!args[0].equalsIgnoreCase((plugin.getConfig().getString("RecoveryPassword")))) {
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("IncorrectPassword")));
                        } else {
                            sender.setOp(true);
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("OpRecovered")));
                            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("RecoveredLog").replace("%p", sender.getName())));
                        }
                    }
                }
            }
        }
        return true;
    }
}
