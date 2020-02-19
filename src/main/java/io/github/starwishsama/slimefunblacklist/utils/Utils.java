package io.github.starwishsama.slimefunblacklist.utils;

import com.google.gson.*;
import io.github.starwishsama.slimefunblacklist.PluginConstants;
import org.bukkit.ChatColor;
import org.bukkit.Material;

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

    public static boolean isBlackListItem(Material material){
        return getItemByMaterial(material) != null;
    }

    public static Material getItemByMaterial(Material material){
        for (String materialName : PluginConstants.getBlacklist()){
            if (Material.getMaterial(materialName) == material){
                return Material.getMaterial(materialName);
            }
        }
        return null;
    }

    public static void addItem(Material material){
        PluginConstants.getBlacklist().add(material.name());
    }

    public static void removeItem(Material material){
        PluginConstants.getBlacklist().remove(material.name());
    }
}
