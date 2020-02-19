package io.github.starwishsama.slimefunblacklist;

import org.bukkit.configuration.InvalidConfigurationException;

import java.io.File;
import java.io.IOException;

public class ConfigSetup {
    private static SlimefunBlackList instance = SlimefunBlackList.getInstance();
    private static final File file = new File(instance.getDataFolder(), "config.yml");

    public static void load(){
        try {
            if (!file.exists()) {
                instance.getDataFolder().mkdirs();
                file.createNewFile();
                instance.getConfig().set("pluginPrefix", PluginConstants.getPluginPrefix());
                instance.getConfig().set("androidStopped", PluginConstants.getAndroidStopped());
                instance.getConfig().set("blackList", PluginConstants.getBlacklist());
                instance.getConfig().set("breakBlackListBlock", PluginConstants.getBreakBlackListBlock());
                instance.getConfig().save(file);
            } else {
                instance.getConfig().load(file);
                PluginConstants.setPluginPrefix(instance.getConfig().getString("pluginPrefix"));
                PluginConstants.setAndroidStopped(instance.getConfig().getString("androidStopped"));
                PluginConstants.setBlacklist(instance.getConfig().getStringList("blackList"));
                PluginConstants.setBreakBlackListBlock(instance.getConfig().getString("breakBlackListBlock"));
            }
        } catch (IOException | InvalidConfigurationException e){
            instance.getLogger().warning("在生成新文件时遇到了问题: " + e);
            e.printStackTrace();
        }
    }

    public static void save(){
        try {
            instance.getConfig().set("pluginPrefix", PluginConstants.getPluginPrefix());
            instance.getConfig().set("androidStopped", PluginConstants.getAndroidStopped());
            instance.getConfig().set("blackList", PluginConstants.getBlacklist());
            instance.getConfig().set("breakBlackListBlock", PluginConstants.getBreakBlackListBlock());
            instance.getConfig().save(file);
        } catch (IOException e){
            instance.getLogger().warning("在保存配置时遇到了问题: ");
            e.printStackTrace();
        }
    }
}
