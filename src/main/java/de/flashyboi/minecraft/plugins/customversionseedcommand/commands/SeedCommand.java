package de.flashyboi.minecraft.plugins.customversionseedcommand.commands;

import de.flashyboi.minecraft.plugins.customversionseedcommand.CustomVersionSeedCommand;
import de.flashyboi.minecraft.plugins.customversionseedcommand.staticvar.ConfigVariables;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SeedCommand implements Listener {

    private static final String ORIGINAL_SEED = "/minecraft:seed";
    private static final String HEX_CODE_REGEX = "&#[a-fA-F0-9]{6}";

    private final Pattern pattern = Pattern.compile(HEX_CODE_REGEX);

    private final FileConfiguration cfg = CustomVersionSeedCommand.plugin.getConfig();
    private final List<String> all_lines = cfg.getConfigurationSection(ConfigVariables.SEED_SECTION).getStringList(ConfigVariables.STRING_LIST_MESSAGE);

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerExecutingVersionCommand(PlayerCommandPreprocessEvent e) {

        String command = e.getMessage();

        if(command.equalsIgnoreCase("/seed")) {
            Player player = e.getPlayer();
            if(player.hasPermission("CustomVersionSeedCommand.original.seedcommand")) {
                e.setMessage(ORIGINAL_SEED);
            } else {
                for(String one_line : all_lines) {
                    one_line = hexCodeFormatter(one_line);
                    player.sendMessage(one_line);
                    e.setCancelled(true);
                }
            }
        } else {
            return;
        }
    }
    private String hexCodeFormatter(String msg) {
        Matcher match = pattern.matcher(msg);
        while(match.find()) {
            String color = msg.substring(match.start(), match.end());
            color = color.replace("&","");
            msg = msg.replace("&"+color, ChatColor.of(color) + "");
            match = pattern.matcher(msg);
        }
        return msg;
    }
}



