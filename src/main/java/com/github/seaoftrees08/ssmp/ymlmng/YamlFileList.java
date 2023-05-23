package com.github.seaoftrees08.ssmp.ymlmng;

public enum YamlFileList {
    homeList("homeList.yml");

    private final String fileName;
    YamlFileList(final String fileName) {
        this.fileName = fileName;
    }

    public String getString(){
        return this.fileName;
    }
}
