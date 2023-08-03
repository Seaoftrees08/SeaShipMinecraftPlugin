package com.github.seaoftrees08.ssmp;

import com.github.seaoftrees08.ssmp.commands.HomeCommand;
import com.github.seaoftrees08.ssmp.commands.SpawnCommand;
import com.github.seaoftrees08.ssmp.commands.SuicideCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class SeaShipMinecraftPlugin extends JavaPlugin {

    public static SeaShipMinecraftPlugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
        Objects.requireNonNull(getCommand("home")).setExecutor(new HomeCommand());
        Objects.requireNonNull(getCommand("spawn")).setExecutor(new SpawnCommand());
        Objects.requireNonNull(getCommand("suicide")).setExecutor(new SuicideCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
