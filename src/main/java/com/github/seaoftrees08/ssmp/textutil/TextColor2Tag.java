package com.github.seaoftrees08.ssmp.textutil;

import net.kyori.adventure.text.format.NamedTextColor;

public class TextColor2Tag {

    public static String TextColor2StartTag(NamedTextColor tc){
        return "<" + tc.toString() + ">";
    }

    public static String TextColor2EndTag(NamedTextColor tc){
        return "</" + tc.toString() + ">";
    }

}
