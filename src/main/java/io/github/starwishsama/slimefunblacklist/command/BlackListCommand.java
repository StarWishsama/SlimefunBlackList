package io.github.starwishsama.slimefunblacklist.command;

import io.github.starwishsama.slimefunblacklist.ConfigSetup;
import io.github.starwishsama.slimefunblacklist.PluginConstants;

import io.github.starwishsama.slimefunblacklist.utils.Utils;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BlackListCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("sfbl") || cmd.getName().equalsIgnoreCase("sfblacklist")) {
            if (args.length == 0) {
                sender.sendMessage(Utils.color(PluginConstants.getPluginPrefix()
                        + "\n/sfbl reload 重载配置文件\n"
                        + "/sfbl add 新增禁止破坏物品\n"
                        + "/sfbl remove 删除禁止破坏物品\n"));
            } else {
                if (sender instanceof Player) {
                    if (sender.hasPermission("sfblacklist.command.main")) {
                        Player p = (Player) sender;
                        Material item = p.getInventory().getItemInMainHand().getType();
                        switch (args[0].toLowerCase()) {
                            case "reload":
                                ConfigSetup.load();
                                sender.sendMessage(Utils.color(PluginConstants.getPluginPrefix() + " 重载配置文件成功!"));
                                break;
                            case "add":
                                if (item == Material.AIR) {
                                    sender.sendMessage(Utils.color(PluginConstants.getPluginPrefix() + " /sfbl add (手持要操作的方块)"));
                                } else {
                                    if (!Utils.isBlackListItem(item)) {
                                        Utils.addItem(item);
                                        sender.sendMessage(Utils.color(PluginConstants.getPluginPrefix() + " 添加成功!"));
                                    } else {
                                        sender.sendMessage(Utils.color(PluginConstants.getPluginPrefix() + " 你已经添加过这个物品了!"));
                                    }
                                }
                                break;
                            case "remove":
                                if (item == Material.AIR) {
                                    sender.sendMessage(Utils.color(PluginConstants.getPluginPrefix() + " /sfbl add (手持要操作的方块)"));
                                } else {
                                    if (Utils.isBlackListItem(item)) {
                                        Utils.removeItem(item);
                                        sender.sendMessage(Utils.color(PluginConstants.getPluginPrefix() + " 删除成功!"));
                                    } else {
                                        sender.sendMessage(Utils.color(PluginConstants.getPluginPrefix() + " 你还没添加过这个物品!"));
                                    }
                                }
                                break;
                        }
                    } else {
                        sender.sendMessage(Utils.color(PluginConstants.getPluginPrefix() + "&c你没有权限!"));
                    }
                } else {
                    sender.sendMessage(Utils.color(PluginConstants.getPluginPrefix() + "&c后台不能使用此命令"));
                }
            }
        }
        return true;
    }
}
