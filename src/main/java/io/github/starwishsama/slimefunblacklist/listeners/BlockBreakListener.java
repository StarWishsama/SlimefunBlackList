package io.github.starwishsama.slimefunblacklist.listeners;

import io.github.starwishsama.slimefunblacklist.PluginConstants;
import io.github.starwishsama.slimefunblacklist.SlimefunBlackList;
import io.github.starwishsama.slimefunblacklist.utils.Utils;

import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class BlockBreakListener implements Listener {
    public BlockBreakListener(SlimefunBlackList plugin){
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){
        ItemStack handItem = e.getPlayer().getInventory().getItemInMainHand();
        if (Utils.isBlackListItem(e.getBlock().getType()) && isSlimefunTool(handItem)){
            e.getPlayer().sendMessage(Utils.color(PluginConstants.getPluginPrefix() + PluginConstants.getBreakBlackListBlock()));
            e.setCancelled(true);
        }
    }

    private boolean isSlimefunTool(ItemStack stack){
        SlimefunItem sfItem = SlimefunItem.getByItem(stack);
        if (sfItem != null){
            return sfItem.isItem(SlimefunItems.EXPLOSIVE_PICKAXE)
                    || sfItem.isItem(SlimefunItems.EXPLOSIVE_SHOVEL)
                    || sfItem.isItem(SlimefunItems.EXPLOSIVE_BOW);
        }
        return false;
    }
}
