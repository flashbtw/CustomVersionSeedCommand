package de.flashyboi.minecraft.plugins.customversionseedcommand.toolbox;

import net.md_5.bungee.api.ChatColor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HexCodeFormatter {
    String msg;
    public HexCodeFormatter(String msg) {
        this.msg = msg;
    }

    private static final String HEX_CODE_REGEX = "&#[a-fA-F0-9]{6}";
    private final Pattern pattern = Pattern.compile(HEX_CODE_REGEX);

    public String hexCodeFormatter() {
        Matcher match = pattern.matcher(msg);
        while(match.find()) {
            String color = msg.substring(match.start(), match.end());
            color = color.replace("&","");
            msg = msg.replace("&"+color, ChatColor.of(color)+"");
            match = pattern.matcher(msg);
        }
    return msg;
    }
}
