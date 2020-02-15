package io.github.starwishsama.slimefunblacklist.command;

import io.github.starwishsama.slimefunblacklist.BlackList;
import io.github.starwishsama.slimefunblacklist.Config;
import io.github.starwishsama.slimefunblacklist.objects.BlackListItem;

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
                sender.sendMessage(Utils.color(Config.getPluginPrefix() + " /sfbl help"));
            } else {
                Player p = (Player) sender;
                Material m = p.getInventory().getItemInMainHand().getType();
                switch (args[0].toLowerCase()) {
                    case "reload":
                        Config.reload();
                        sender.sendMessage(Utils.color(Config.getPluginPrefix() + " 重载配置文件成功!"));
                        break;
                    case "set":
                        if (m == Material.AIR) {
                            sender.sendMessage(Utils.color(Config.getPluginPrefix() + " /sfbl add (手持要操作的方块)"));
                        } else {
                            if (BlackList.isBlackListItem(m)) {
                                if (args.length > 1) {
                                    BlackListItem item = BlackList.getItemByMaterial(m);
                                    if (item != null) {
                                        if ("android".equals(args[1].toLowerCase())) {
                                            if (args[2].equalsIgnoreCase("true")) {
                                                item.setAndroid(true);
                                                sender.sendMessage(Utils.color(Config.getPluginPrefix() + " 已设置该方块不可被机器人破坏"));
                                            } else if (args[2].equalsIgnoreCase("false")) {
                                                item.setAndroid(false);
                                                sender.sendMessage(Utils.color(Config.getPluginPrefix() + " 已设置该方块可被机器人破坏"));
                                            } else {
                                                sender.sendMessage(Utils.color(Config.getPluginPrefix() + " /sfbl set android [true/false] (手持要操作的方块)"));
                                            }
                                        }
                                    }
                                }
                            } else {
                                sender.sendMessage(Utils.color(Config.getPluginPrefix() + " 你还没添加过这个物品!"));
                            }
                        }
                        break;
                    case "add":
                        if (m == Material.AIR) {
                            sender.sendMessage(Utils.color(Config.getPluginPrefix() + " /sfbl add (手持要操作的方块)"));
                        } else {
                            if (!BlackList.isBlackListItem(m)) {
                                BlackList.addBlackList(m, true);
                                sender.sendMessage(Utils.color(Config.getPluginPrefix() + " 添加成功!"));
                            } else {
                                sender.sendMessage(Utils.color(Config.getPluginPrefix() + " 你已经添加过这个物品了!"));
                            }
                        }
                        break;
                    case "remove":
                        if (m == Material.AIR) {
                            sender.sendMessage(Utils.color(Config.getPluginPrefix() + " /sfbl add (手持要操作的方块)"));
                        } else {
                            if (BlackList.isBlackListItem(m)) {
                                BlackList.removeBlackList(m);
                                sender.sendMessage(Utils.color(Config.getPluginPrefix() + " 删除成功!"));
                            } else {
                                sender.sendMessage(Utils.color(Config.getPluginPrefix() + " 你还没添加过这个物品!"));
                            }
                        }
                        break;
                    default:
                        sender.sendMessage(Utils.color(Config.getPluginPrefix() + " No help now"));
                        break;
                }
            }
        }
        return true;
    }
}
