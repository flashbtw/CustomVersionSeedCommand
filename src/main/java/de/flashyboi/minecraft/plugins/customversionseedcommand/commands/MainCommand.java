package de.flashyboi.minecraft.plugins.customversionseedcommand.commands;

import de.flashyboi.minecraft.plugins.customversionseedcommand.CustomVersionSeedCommand;
import de.flashyboi.minecraft.plugins.customversionseedcommand.staticvar.ConfigVariables;
import de.flashyboi.minecraft.plugins.customversionseedcommand.toolbox.HexCodeFormatter;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MainCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        String no_perms_for_reload = CustomVersionSeedCommand.plugin.getConfig().getString(ConfigVariables.NO_PERMISSION_RELOAD);
        if (args.length > 1 || args.length < 1) {
            if (sender.hasPermission("CustomVersionSeedCommand.reload")) {
                sender.sendMessage(ChatColor.RED + "Usage: /customversionseedcommand [reload]");
            } else {
                sender.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
            }
            return false;
        }
        if (args[0].equalsIgnoreCase("reload")) {
            if (sender.hasPermission("CustomVersionSeedCommand.reload")) {
                CustomVersionSeedCommand.plugin.reloadConfig();
                sender.sendMessage(ChatColor.of("#00ffe0")+"["+ChatColor.of("#8bff00")+"CustomVersionSeedCommand"+ChatColor.of("#00ffe0")+"]"+ChatColor.RED+" Reloaded Successfully!");
                return true;
            } else {
                HexCodeFormatter message = new HexCodeFormatter(no_perms_for_reload);
                String msg = message.hexCodeFormatter();
                sender.sendMessage(msg);
                return false;
            }
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (!(sender.hasPermission("CustomVersionSeedCommand.reload"))) { return null; }

        List<String> subcommands = new ArrayList<>();
        if (args.length == 1) {
            subcommands.add("reload");
            return subcommands;
        }
        return null;
    }
}

// TODO: Fix reload command