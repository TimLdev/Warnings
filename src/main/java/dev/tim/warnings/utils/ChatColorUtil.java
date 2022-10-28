package dev.tim.warnings.utils;

import org.bukkit.ChatColor;

public class ChatColorUtil {

    public static final String WRONG = transCC("&7[&c✕&7] ");
    public static final String SUCCESS = transCC("&7[&a✔&7] ");

    public static final String WITH_DELIMITER = "((?<=%1$s)|(?=%1$s))";

    public static String transCC(String text){ //transCC -> translateColorCodes

        String[] texts = text.split(String.format(WITH_DELIMITER, "&"));

        StringBuilder finalText = new StringBuilder();

        for (int i = 0; i < texts.length; i++){
            if (texts[i].equalsIgnoreCase("&")){
                i++;
                if (texts[i].charAt(0) == '#'){
                    finalText.append(org.bukkit.ChatColor.valueOf(texts[i].substring(0, 7))).append(texts[i].substring(7));
                }else{
                    finalText.append(ChatColor.translateAlternateColorCodes('&', "&" + texts[i]));
                }
            }else{
                finalText.append(texts[i]);
            }
        }

        return finalText.toString();
    }

}
