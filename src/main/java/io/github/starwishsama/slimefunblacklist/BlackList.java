package io.github.starwishsama.slimefunblacklist;

import io.github.starwishsama.slimefunblacklist.objects.BlackListItem;
import org.bukkit.Material;

import java.util.LinkedList;
import java.util.List;

public class BlackList {
    public static List<BlackListItem> blacklist = new LinkedList<>();

    public static boolean isBlackListItem(Material m){
        return getItemByMaterial(m) != null;
    }

    public static boolean isBlockAndroid(Material m){
        BlackListItem item = getItemByMaterial(m);
        if (item != null){
            return item.isAndroid();
        }
        return false;
    }

    public static void addBlackList(Material item, boolean android){
        blacklist.add(new BlackListItem(item, android));
    }

    public static void removeBlackList(Material item){
        if (isBlackListItem(item)){
            blacklist.remove(getItemByMaterial(item));
        }
    }

    public static BlackListItem getItemByMaterial(Material item){
        if (!blacklist.isEmpty()){
            for (BlackListItem blackListItem : blacklist){
                if (blackListItem.getMaterial() == item){
                    return blackListItem;
                }
            }
        }
        return null;
    }
}
