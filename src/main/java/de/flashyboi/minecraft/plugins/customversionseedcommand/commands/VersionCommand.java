package de.flashyboi.minecraft.plugins.customversionseedcommand.commands;

import de.flashyboi.minecraft.plugins.customversionseedcommand.CustomVersionSeedCommand;
import de.flashyboi.minecraft.plugins.customversionseedcommand.staticvar.ConfigVariables;
import de.flashyboi.minecraft.plugins.customversionseedcommand.toolbox.HexCodeFormatter;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.List;

public class VersionCommand implements Listener {

    private static final String BUKKIT_VERSION = "/bukkit:version";

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerExecutingVersionCommand(PlayerCommandPreprocessEvent e) {
        String command = e.getMessage();
        FileConfiguration cfg = CustomVersionSeedCommand.plugin.getConfig();
            List<String> all_lines = cfg.getConfigurationSection(ConfigVariables.VERSION_SECTION).getStringList(ConfigVariables.STRING_LIST_MESSAGE);
        if (command.toLowerCase().startsWith("/version")) {
            int offset = 9;
            customVersionCommand(e.getPlayer(), e, all_lines, offset);
        } else if (command.toLowerCase().startsWith("/ver")) {
            int offset = 5;
            customVersionCommand(e.getPlayer(), e, all_lines, offset);
        } else if (command.toLowerCase().startsWith("/icanhasbukkit")) {
            int offset = 15;
            customVersionCommand(e.getPlayer(), e, all_lines, offset);
        } else if (command.toLowerCase().startsWith("/?")) {
            int offset = 3;
            customVersionCommand(e.getPlayer(), e, all_lines, offset);
        } else if (command.toLowerCase().startsWith("/about")) {
            int offset = 7;
            customVersionCommand(e.getPlayer(), e, all_lines, offset);
        }
    }

    private void customVersionCommand(Player player, PlayerCommandPreprocessEvent e, List<String> all_lines, int offset) {
        if(player.hasPermission("CustomVersionSeedCommand.original.versioncommand")) {
            String command = e.getMessage();
            if (command.length() > offset) {
                String argument = command.substring(offset);
                e.setMessage(BUKKIT_VERSION + " " + argument);
            }
        } else {
            for (String one_line : all_lines) {
                HexCodeFormatter message = new HexCodeFormatter(one_line);
                String msg = message.hexCodeFormatter();
                player.sendMessage(msg);
            }
            e.setCancelled(true);
        }
    }
}


