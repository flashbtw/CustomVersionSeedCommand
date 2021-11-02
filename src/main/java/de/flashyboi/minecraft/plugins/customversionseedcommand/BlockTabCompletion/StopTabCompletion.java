package de.flashyboi.minecraft.plugins.customversionseedcommand.BlockTabCompletion;

import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.server.TabCompleteEvent;

public class StopTabCompletion implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void stopTabCompletion(TabCompleteEvent e) {
        String buffer = e.getBuffer();
        CommandSender sender = e.getSender();
        if(!(sender.hasPermission("CustomVersionSeedCommand.allowtabcomplete"))) {
            if (buffer.startsWith("/version")) {
                e.setCancelled(true);
            } else if (buffer.startsWith("/ver")) {
                e.setCancelled(true);
            } else if (buffer.startsWith("/icanhasbukkit")) {
                e.setCancelled(true);
            } else if (buffer.startsWith("/?")) {
                e.setCancelled(true);
            } else if (buffer.startsWith("/seed")) {
                e.setCancelled(true);
            }
        }
    }
}
