package com.github.seaoftrees08.ssmp;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class PlayerListeners implements Listener {

    public PlayerListeners(SeaShipMinecraftPlugin ssmp){
        ssmp.getServer().getPluginManager().registerEvents(this, ssmp);
    }

    @EventHandler
    public void allowWhitelist(PlayerCommandPreprocessEvent event){
        if(event.getPlayer().hasPermission("minecraft.command.whitelist")){
            System.out.println("You have enough permissions!");
            return;
        }

        String[] args = event.getMessage().split(" ");
        if(args.length == 3 && args[0].equalsIgnoreCase("/whitelist") && args[1].equalsIgnoreCase("add")){
            Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "whitelist add" + args[2]);
            event.getPlayer().sendMessage(args[2] + "をホワイトリストに追加しました");
            event.setCancelled(true);
        }

    }

}
