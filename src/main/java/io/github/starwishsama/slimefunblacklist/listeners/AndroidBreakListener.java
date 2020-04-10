package io.github.starwishsama.slimefunblacklist.listeners;

import com.bekvon.bukkit.residence.api.ResidenceApi;
import com.bekvon.bukkit.residence.containers.Flags;
import com.bekvon.bukkit.residence.protection.ClaimedResidence;
import com.bekvon.bukkit.residence.protection.ResidencePermissions;
import io.github.starwishsama.slimefunblacklist.PluginConstants;
import io.github.starwishsama.slimefunblacklist.SlimefunBlackList;
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
    public AndroidBreakListener(SlimefunBlackList plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onAndroidBreak(AndroidMineEvent e) {
        Block block = e.getBlock();
        String json = BlockStorage.getBlockInfoAsJson(e.getAndroid().getBlock());
        Player p = Bukkit.getPlayer(Utils.getOwnerByJson(json));

        if (PluginConstants.isEnableResidence()){
            ClaimedResidence res = ResidenceApi.getResidenceManager().getByLoc(e.getBlock().getLocation());
            if (res != null && p != null){
                ResidencePermissions perms = res.getPermissions();
                if (res.getOwnerUUID() == p.getUniqueId() || perms.playerHas(p, Flags.admin, true) || p.isOp()){
                    return;
                }

                if (!perms.playerHas(p, Flags.build, true)
                        || !perms.playerHas(p, Flags.place, true)
                        || !perms.playerHas(p, Flags.destroy, true)){
                    e.setCancelled(true);
                    BlockStorage.addBlockInfo(e.getAndroid().getBlock(), "paused", "true");
                    p.sendMessage(Utils.color(PluginConstants.getPluginPrefix() + PluginConstants.getBreakInResidence()));
                    SlimefunPlugin.getLocal().sendMessage(p, "android.stopped", true);
                }
            }
        }

        if (!PluginConstants.isWhiteListMode()) {
            if (Utils.isBlackListItem(block.getType())) {
                e.setCancelled(true);
                BlockStorage.addBlockInfo(e.getAndroid().getBlock(), "paused", "true");
                if (p != null) {
                    p.sendMessage(Utils.color(PluginConstants.getPluginPrefix() + PluginConstants.getAndroidStopped()));
                    SlimefunPlugin.getLocal().sendMessage(p, "android.stopped", true);
                }
            }
        } else {
            if (!Utils.isBlackListItem(block.getType())) {
                e.setCancelled(true);
                BlockStorage.addBlockInfo(e.getAndroid().getBlock(), "paused", "true");
                if (p != null) {
                    p.sendMessage(Utils.color(PluginConstants.getPluginPrefix() + PluginConstants.getAndroidStopped()));
                    SlimefunPlugin.getLocal().sendMessage(p, "android.stopped", true);
                }
            }
        }
    }
}
