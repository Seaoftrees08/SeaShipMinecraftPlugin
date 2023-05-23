package com.github.seaoftrees08.ssmp;

import com.github.seaoftrees08.ssmp.commands.HomeCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class SeaShipMinecraftPlugin extends JavaPlugin {

    public static SeaShipMinecraftPlugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
        Objects.requireNonNull(getCommand("/home")).setExecutor(new HomeCommand());
        getCommand("/spawn");
        getCommand("/suicide");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
