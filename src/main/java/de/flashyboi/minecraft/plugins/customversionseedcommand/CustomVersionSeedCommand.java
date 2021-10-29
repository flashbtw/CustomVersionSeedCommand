package de.flashyboi.minecraft.plugins.customversionseedcommand;

import de.flashyboi.minecraft.plugins.customversionseedcommand.commands.SeedCommand;
import de.flashyboi.minecraft.plugins.customversionseedcommand.commands.VersionCommand;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class CustomVersionSeedCommand extends JavaPlugin {
    public static Plugin plugin;
    public final Logger log = this.getLogger();
    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        log.info("Loading Events");
        Bukkit.getServer().getPluginManager().registerEvents(new VersionCommand(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new SeedCommand(), this);
        log.info("Loaded Events successfully");
        this.saveDefaultConfig();
        log.info("Enabled!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}