package io.github.starwishsama.slimefunblacklist;

import io.github.starwishsama.slimefunblacklist.objects.BlackListItem;
import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.LinkedList;
import java.util.List;

public class Config {
    private static FileConfiguration config = SlimefunBlackList.getInstance().getConfig();
    @Getter
    private static String androidStopped = "&bSlimefunBlackList &r>";
    @Getter
    private static String pluginPrefix = "检测到机器人正在破坏禁止破坏的方块 %s, 已自动停止运行.";

    public static void load(){
        BlackList.blacklist = config.get("blacklist") == null ? new LinkedList<>() : (List<BlackListItem>) config.get("blacklist");
        androidStopped = config.getString("android-stopped");
        pluginPrefix = config.getString("plugin-prefix");
    }

    public static void save(){
        config.set("blacklist", BlackList.blacklist);
        config.set("android-stopped", androidStopped);
        config.set("plugin-prefix", pluginPrefix);
        SlimefunBlackList.getInstance().saveConfig();
    }

    public static void reload(){
        SlimefunBlackList.getInstance().reloadConfig();
        config = SlimefunBlackList.getInstance().getConfig();
        load();
    }
}
