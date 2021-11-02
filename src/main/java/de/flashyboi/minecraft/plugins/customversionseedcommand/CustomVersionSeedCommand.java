package de.flashyboi.minecraft.plugins.customversionseedcommand;

import de.flashyboi.minecraft.plugins.customversionseedcommand.BlockTabCompletion.StopTabCompletion;
import de.flashyboi.minecraft.plugins.customversionseedcommand.commands.MainCommand;
import de.flashyboi.minecraft.plugins.customversionseedcommand.commands.SeedCommand;
import de.flashyboi.minecraft.plugins.customversionseedcommand.commands.VersionCommand;

import de.flashyboi.minecraft.plugins.customversionseedcommand.toolbox.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class CustomVersionSeedCommand extends JavaPlugin {
    public static Plugin plugin;
    public final Logger log = this.getLogger();

    public static final int METRICS_PLUGIN_ID = 13225;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        log.info("Loading Events");
        Bukkit.getServer().getPluginManager().registerEvents(new StopTabCompletion(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new VersionCommand(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new SeedCommand(), this);
        log.info("Loaded Events successfully");
        log.info("Loading Commands");
        this.getCommand("CustomVersionSeedCommand").setExecutor(new MainCommand());
        this.getCommand("CustomVersionSeedCommand").setTabCompleter(new MainCommand());
        log.info("Loaded Commands successfully");
        log.info("Loading Metrics");
        Metrics metrics = new Metrics(this, METRICS_PLUGIN_ID);
        log.info("Loaded Metrics successfully");
        this.saveDefaultConfig();
        log.info("Enabled!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static void onConfigReload() {
        plugin.reloadConfig();
    }
}