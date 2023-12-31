package com.github.seaoftrees08.ssmp.home;

import com.github.seaoftrees08.ssmp.SeaShipMinecraftPlugin;
import com.github.seaoftrees08.ssmp.ymlmng.YamlFileList;
import com.github.seaoftrees08.ssmp.ymlmng.YamlReaderWriter;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Home extends Location {
    public static final String NAMELESS = "NAME_LESS";
    private final YamlReaderWriter hyml = new YamlReaderWriter(YamlFileList.homeList);
    private final Player player;
    private final String homeName;

    private boolean untilSet;

    public UUID uuid;

    /**
     * when set home
     * @param player setしたplayer
     * @param homeName homeの名前
     */
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
        this.untilSet = false;
    }

    // when tp home

    /**
     * when tp home
     * @param player tpしたplayer
     * @param homeName homeの名前
     * @param whenTp homeSetと区別するために引数を入れているだけ。未使用
     */
    public Home(Player player, String homeName, boolean whenTp){
        this(player, homeName);
        String ymlStr = hyml.getYamlConfig().getString(path());
        if(ymlStr == null){
            player.sendRichMessage("<light_purple>[SSMP-Home]</light_purple> <red>homeが設定されていません</red>");
            untilSet = true;
            return;
        }
        String[] locStrs = ymlStr.split(",");
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

    public boolean isUntilSet(){
        return untilSet;
    }

    /**
     * ymlにsaveする
     */
    public void save(){
        hyml.writeAndSave(yml -> yml.set(path(), getHomeString()));
    }

    /**
     * homeを設定した際、ymlに書き込むStirngとして取得する
     * @return
     */
    private String getHomeString(){
        return player.getWorld().getName() + "," +
                player.getLocation().getX() + "," +
                player.getLocation().getY() + "," +
                player.getLocation().getZ() + "," +
                player.getLocation().getYaw() + "," +
                player.getLocation().getPitch();
    }

    /**
     * ymlから読み込んだStringをLocationに変換する
     * @param world world
     * @param x x座標
     * @param y y座標
     * @param z z座標
     * @param yaw yaw
     * @param pitch pitch
     */
    private void setLocation(World world, double x, double y, double z, float yaw, float pitch){
        setWorld(world);
        setX(x);
        setY(y);
        setZ(z);
        setYaw(yaw);
        setPitch(pitch);
    }

    /**
     * ymlのpathを取得する
     * @return
     */
    private String path(){
        return player.getName() + "@" + homeName;
    }



}
