package io.github.starwishsama.slimefunblacklist.listeners;

import io.github.starwishsama.slimefunblacklist.Config;
import io.github.starwishsama.slimefunblacklist.SlimefunBlackList;
import io.github.starwishsama.slimefunblacklist.BlackList;
import io.github.starwishsama.slimefunblacklist.utils.Utils;

import io.github.thebusybiscuit.slimefun4.api.events.AndroidMineEvent;

import me.mrCookieSlime.Slimefun.SlimefunPlugin;
import me.mrCookieSlime.Slimefun.api.BlockStorage;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class AndroidBreakListener implements Listener {
    public AndroidBreakListener(SlimefunBlackList plugin){
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onAndroidBreak(AndroidMineEvent e){
        Block block = e.getBlock();
        if (BlackList.isBlockAndroid(block.getType())){
            e.setCancelled(true);
            BlockStorage.addBlockInfo(e.getAndroid().getBlock(), "paused", "true");
            String json = BlockStorage.getBlockInfoAsJson(e.getAndroid().getBlock());
            Player p = Bukkit.getPlayer(Utils.getOwnerByJson(json));
            if (p != null) {
                p.sendMessage(Utils.color(Config.getPluginPrefix() + String.format(Config.getAndroidStopped(), block.getType().name())));
                SlimefunPlugin.getLocal().sendMessage(p, "android.stopped", true);
            }
        }
    }
}
