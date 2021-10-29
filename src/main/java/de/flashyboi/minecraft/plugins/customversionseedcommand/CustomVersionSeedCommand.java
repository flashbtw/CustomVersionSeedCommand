package de.flashyboi.minecraft.plugins.customversionseedcommand;

import de.flashyboi.minecraft.plugins.customversionseedcommand.commands.SeedCommand;
import de.flashyboi.minecraft.plugins.customversionseedcommand.commands.VersionCommand;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;


public final class CustomVersionSeedCommand extends JavaPlugin {
    public static Plugin plugin;
    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        Bukkit.getServer().getPluginManager().registerEvents(new VersionCommand(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new SeedCommand(), this);
        this.saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
