package de.flashyboi.minecraft.plugins.customversionseedcommand.commands;

import de.flashyboi.minecraft.plugins.customversionseedcommand.CustomVersionSeedCommand;
import de.flashyboi.minecraft.plugins.customversionseedcommand.staticvar.ConfigVariables;
import de.flashyboi.minecraft.plugins.customversionseedcommand.toolbox.HexCodeFormatter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MainCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args[0].equalsIgnoreCase("reload")) {
            if (!(sender.hasPermission("CustomVersionSeedCommand.reload"))) {
                String no_perms_for_reload = CustomVersionSeedCommand.plugin.getConfig().getString(ConfigVariables.NO_PERMISSION_RELOAD);
                HexCodeFormatter message = new HexCodeFormatter(no_perms_for_reload);
                String msg = message.hexCodeFormatter();
                sender.sendMessage(msg);
                return false;
            }
            CustomVersionSeedCommand.plugin.reloadConfig();
            sender.sendMessage("CustomVersionSeedCommand was reloaded!");
            return true;
        }
        if (args.length > 1){
            return false;
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        List<String> subcommands = new ArrayList<>();
        if(args.length == 1) {
            subcommands.add("reload");
            return subcommands;
        }
        return null;
    }
}