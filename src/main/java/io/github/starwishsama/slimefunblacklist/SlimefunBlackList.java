package io.github.starwishsama.slimefunblacklist;

import io.github.starwishsama.slimefunblacklist.command.BlackListCommand;
import io.github.starwishsama.slimefunblacklist.listeners.AndroidBreakListener;

import lombok.Getter;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class SlimefunBlackList extends JavaPlugin {
    @Getter
    private static SlimefunBlackList instance;

    @Override
    public void onEnable() {
        if (getServer().getPluginManager().getPlugin("Slimefun") != null) {
            instance = this;
            getLogger().info("已检测到前置 Slimefun, 正在启用...");
            Config.load();
            new AndroidBreakListener(this);
            Bukkit.getPluginCommand("sfblacklist").setExecutor(new BlackListCommand());
        } else {
            getLogger().warning("插件需要前置 Slimefun 4 才能正常使用!");
            this.setEnabled(false);
        }
    }

    @Override
    public void onDisable(){
        getLogger().info("正在保存配置文件...");
        Config.save();
    }
}
