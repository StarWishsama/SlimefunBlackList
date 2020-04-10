package io.github.starwishsama.slimefunblacklist;

import io.github.starwishsama.slimefunblacklist.command.BlackListCommand;
import io.github.starwishsama.slimefunblacklist.command.GetItemIDCommand;
import io.github.starwishsama.slimefunblacklist.listeners.AndroidBreakListener;

import io.github.starwishsama.slimefunblacklist.listeners.BlockBreakListener;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import lombok.Getter;

import me.mrCookieSlime.Slimefun.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class SlimefunBlackList extends JavaPlugin implements SlimefunAddon {
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
            Objects.requireNonNull(Bukkit.getPluginCommand("sfblacklist")).setExecutor(new BlackListCommand());
            Objects.requireNonNull(Bukkit.getPluginCommand("getsfid")).setExecutor(new GetItemIDCommand());

            new Metrics(this, 6577);
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

    @Override
    public JavaPlugin getJavaPlugin() {
        return this;
    }

    @Override
    public String getBugTrackerURL() {
        return "https://github.com/StarWishsama/SlimefunBlackList/issues";
    }

    @Override
    public String getPluginVersion() {
        return this.getDescription().getVersion();
    }
}
