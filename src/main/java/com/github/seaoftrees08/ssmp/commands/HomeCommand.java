package com.github.seaoftrees08.ssmp.commands;

import com.github.seaoftrees08.ssmp.SeaShipMinecraftPlugin;
import com.github.seaoftrees08.ssmp.home.Home;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.UUID;

public class HomeCommand implements CommandExecutor {
    private static final HashMap<String, Home> invites = new HashMap<>();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!command.getName().equalsIgnoreCase("home")) return false;

        // /home
        if(args.length == 0 && sender instanceof Player player){
            Home home = new Home(player, Home.NAMELESS, true);
            player.teleport(home);
            return true;
        }

        // /home set
        if(args.length == 1 && args[0].equalsIgnoreCase("set") && sender instanceof Player player){
            Home home = new Home(player, Home.NAMELESS);
            home.save();
            return true;
        }

        // /home invite A
        if(args.length == 2 && args[0].equalsIgnoreCase("invite") && sender instanceof Player player){
            Home home = new Home(player, Home.NAMELESS, true);
            UUID uuid = UUID.randomUUID();
            home.uuid = uuid;
            Player invitee = SeaShipMinecraftPlugin.plugin.getServer().getPlayer(args[1]);
            if (invitee==null){
                reply(sender, NamedTextColor.RED, "招待相手が見つかりません");
                reply(sender, NamedTextColor.RED, "招待相手が見つかりません");
                return true;
            }
            invites.put(invitee.getName(), home);
            new BukkitRunnable(){
                @Override
                public void run(){
                    Home iHome = invites.getOrDefault(invitee.getName(), null);
                    if(iHome != null && iHome.uuid == uuid){
                        invites.remove(invitee.getName());
                    }
                }
            }.runTaskLater(SeaShipMinecraftPlugin.plugin, 20*30);
            reply(sender, NamedTextColor.GREEN, invitee.getName() + "を招待しました! 30秒以内に相手に/home acceptを実行してもらってください");
            reply(invitee, NamedTextColor.AQUA, player.getName() + "から招待を受けました! 30秒以内に/home acceptを実行してください");
            return true;
        }

        // /home accept
        if(args.length == 1 && args[0].equalsIgnoreCase("accept") && sender instanceof Player player){
            Home home = invites.getOrDefault(player.getName(), null);
            if(home != null){
                player.teleport(home);
                invites.remove(player.getName());
                reply(player, NamedTextColor.GREEN, "招待を受けました!");
            }else{
                reply(player, NamedTextColor.RED, "正体が存在しないか、無効です");
            }
        }

        sendHelp(sender);
        return true;
    }

    private void reply(CommandSender cs, NamedTextColor ntc, String msg){
        cs.sendRichMessage(NamedTextColor.LIGHT_PURPLE + "[SSMP-Home]" + ntc + msg);
    }
    private void sendHelp(CommandSender cs){
        reply(cs, NamedTextColor.GRAY, "/home set     #homeを設定する");
        reply(cs, NamedTextColor.GRAY, "/home         #設定したhomeへ飛ぶ");
    }
}
