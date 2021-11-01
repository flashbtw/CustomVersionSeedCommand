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

public class SeedCommand implements Listener {

    private static final String ORIGINAL_SEED = "/minecraft:seed";

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerExecutingVersionCommand(PlayerCommandPreprocessEvent e) {
        FileConfiguration cfg = CustomVersionSeedCommand.plugin.getConfig();
        List<String> all_lines = cfg.getConfigurationSection(ConfigVariables.SEED_SECTION).getStringList(ConfigVariables.STRING_LIST_MESSAGE);
        String command = e.getMessage();

        if(command.equalsIgnoreCase("/seed")) {
            Player player = e.getPlayer();
            if(player.hasPermission("CustomVersionSeedCommand.original.seedcommand")) {
                e.setMessage(ORIGINAL_SEED);
            } else {
                for(String one_line : all_lines) {
                    HexCodeFormatter message = new HexCodeFormatter(one_line);
                    String msg = message.hexCodeFormatter();
                    player.sendMessage(msg);
                    e.setCancelled(true);
                }
            }
        }
    }
}



