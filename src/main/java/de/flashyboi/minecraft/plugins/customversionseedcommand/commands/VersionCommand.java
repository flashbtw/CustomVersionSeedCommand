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
        if (command.equalsIgnoreCase("/version")) {
            customVersionCommand(e.getPlayer(), e, all_lines);
        } else if (command.equalsIgnoreCase("/ver")) {
            customVersionCommand(e.getPlayer(), e, all_lines);
        }
    }

    private void customVersionCommand(Player player, PlayerCommandPreprocessEvent e, List<String> all_lines) {
        if(player.hasPermission("CustomVersionSeedCommand.original.versioncommand")) {
            e.setMessage(BUKKIT_VERSION);
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


