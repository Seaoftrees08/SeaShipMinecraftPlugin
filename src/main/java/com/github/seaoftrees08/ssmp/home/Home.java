package com.github.seaoftrees08.ssmp.home;

import com.github.seaoftrees08.ssmp.SeaShipMinecraftPlugin;
import com.github.seaoftrees08.ssmp.ymlmng.YamlFileList;
import com.github.seaoftrees08.ssmp.ymlmng.YamlReaderWriter;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.Objects;
import java.util.UUID;

public class Home extends Location {
    public static final String NAMELESS = "NAME_LESS";
    private final YamlReaderWriter hyml = new YamlReaderWriter(YamlFileList.homeList);
    private final Player player;
    private final String homeName;

    public UUID uuid;

    //when set home
    public Home(Player player, String homeName){
        super(player.getWorld(),
                player.getLocation().getX(),
                player.getLocation().getY(),
                player.getLocation().getZ(),
                player.getLocation().getYaw(),
                player.getLocation().getPitch()
        );
        this.player = player;
        this.homeName = homeName;
    }

    // when tp home
    public Home(Player player, String homeName, boolean whenTp){
        this(player, homeName);
        String[] locStrs = Objects.requireNonNull(hyml.getYamlConfig().getString(path())).split(",");
        if(locStrs.length != 6) return;
        setLocation(
                SeaShipMinecraftPlugin.plugin.getServer().getWorld(locStrs[0]),
                Double.parseDouble(locStrs[1]),
                Double.parseDouble(locStrs[2]),
                Double.parseDouble(locStrs[3]),
                Float.parseFloat(locStrs[4]),
                Float.parseFloat(locStrs[5])
        );

    }

    public void save(){
        hyml.writeAndSave(yml -> yml.set(path(), getHomeString()));
    }

    private String getHomeString(){
        return player.getWorld() + "," +
                player.getLocation().getX() + "," +
                player.getLocation().getY() + "," +
                player.getLocation().getZ() + "," +
                player.getLocation().getYaw() + "," +
                player.getLocation().getPitch();
    }

    private void setLocation(World world, double x, double y, double z, float yaw, float pitch){
        setWorld(world);
        setX(x);
        setY(y);
        setZ(z);
        setYaw(yaw);
        setPitch(pitch);
    }

    private String path(){
        return player.getName() + "@" + homeName;
    }



}
