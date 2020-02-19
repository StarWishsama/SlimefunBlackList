package io.github.starwishsama.slimefunblacklist.command;

import io.github.starwishsama.slimefunblacklist.PluginConstants;
import io.github.starwishsama.slimefunblacklist.utils.Utils;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GetItemIDCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("getsfid") || command.getName().equalsIgnoreCase("getid")){
            if (sender instanceof Player) {
                if (sender.hasPermission("sfblacklist.command.getsfid")) {
                    Player p = (Player) sender;
                    ItemStack item = p.getInventory().getItemInMainHand();
                    if (item.getType() != Material.AIR) {
                        SlimefunItem sfItem = SlimefunItem.getByItem(item);
                        if (sfItem != null) {
                            sender.sendMessage(Utils.color(PluginConstants.getPluginPrefix() + "该物品 ID 为: " + sfItem.getID()));
                        } else {
                            sender.sendMessage(Utils.color(PluginConstants.getPluginPrefix() + "你手上拿着的不是粘液科技物品!"));
                        }
                    } else {
                        sender.sendMessage(Utils.color(PluginConstants.getPluginPrefix() + "你手上什么都没拿!"));
                    }
                } else {
                    sender.sendMessage(Utils.color(PluginConstants.getPluginPrefix() + "&c你没有权限!"));
                }
            } else {
                sender.sendMessage(Utils.color(PluginConstants.getPluginPrefix() + "&c后台不能使用此命令"));
            }
        }
        return true;
    }
}
