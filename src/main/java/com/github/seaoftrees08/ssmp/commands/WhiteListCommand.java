package com.github.seaoftrees08.ssmp.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class WhiteListCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(command.getName().equalsIgnoreCase("whitelist") && args.length==2 && args[0].equalsIgnoreCase("add")
                && !sender.hasPermission("minecraft.command.whitelist")){
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "whitelist add " + args[1]);
            return true;
        }else if(command.getName().equalsIgnoreCase("whitelist")){
            sender.sendMessage("普通にwhitelistの権限もってるのでそっち使いなさい");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "say test");
        }
        return false;
    }
}
