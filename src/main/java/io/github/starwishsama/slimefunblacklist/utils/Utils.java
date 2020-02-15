package io.github.starwishsama.slimefunblacklist.utils;

import com.google.gson.*;
import org.bukkit.ChatColor;

import java.util.UUID;

public class Utils {
    public static UUID getOwnerByJson(String json){
        if (json != null){
            JsonElement element = new JsonParser().parse(json);
            if (!element.isJsonNull()){
                JsonObject object = element.getAsJsonObject();
                return UUID.fromString(object.get("owner").getAsString());
            }
        }
        return null;
    }

    public static String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}
