package io.github.starwishsama.slimefunblacklist;

import io.github.starwishsama.slimefunblacklist.command.BlackListCommand;
import io.github.starwishsama.slimefunblacklist.command.GetItemIDCommand;
import io.github.starwishsama.slimefunblacklist.listeners.AndroidBreakListener;

import io.github.starwishsama.slimefunblacklist.listeners.BlockBreakListener;
import lombok.Getter;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class SlimefunBlackList extends JavaPlugin {
    @Getter
    private static SlimefunBlackList instance;

    @Override
    public void onEnable() {
        if (getServer().getPluginManager().getPlugin("Slimefun") != null) {
            if (getServer().getPluginManager().getPlugin("Residence") != null){
                PluginConstants.setEnableResidence(true);
            } else {
                getLogger().warning("检测到领地插件未安装, 将自动禁用相关功能");
            }
            instance = this;
            getLogger().info("已检测到前置 Slimefun, 正在启用...");
            ConfigSetup.load();
            new AndroidBreakListener(this);
            new BlockBreakListener(this);
            Bukkit.getPluginCommand("sfblacklist").setExecutor(new BlackListCommand());
            Bukkit.getPluginCommand("getsfid").setExecutor(new GetItemIDCommand());
        } else {
            getLogger().warning("插件需要前置 Slimefun 4 才能正常使用!");
            this.setEnabled(false);
        }
    }

    @Override
    public void onDisable(){
        getLogger().info("正在保存配置文件...");
        ConfigSetup.save();
    }
}
