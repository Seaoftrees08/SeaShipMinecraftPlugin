package com.github.seaoftrees08.ssmp.ymlmng;

import com.github.seaoftrees08.ssmp.SeaShipMinecraftPlugin;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;

public class YamlReaderWriter {

    final private String fileName;
    final private File file;
    final private YamlConfiguration yml;

    public YamlReaderWriter(YamlFileList name){
        this.fileName = name.getString();
        file = new File(SeaShipMinecraftPlugin.plugin.getDataFolder(), fileName);
        yml = YamlConfiguration.loadConfiguration(file);
    }

    public YamlConfiguration getYamlConfig(){
        return yml;
    }

    public void save(){
        try {
            yml.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeAndSave(Consumer<YamlConfiguration> write){
        write.accept(yml);
        save();
    }

}
