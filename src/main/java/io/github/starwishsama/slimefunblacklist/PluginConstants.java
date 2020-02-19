package io.github.starwishsama.slimefunblacklist;


import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

public class PluginConstants {
    @Setter
    @Getter
    private static List<String> blacklist = new LinkedList<>();
    @Setter
    @Getter
    private static String pluginPrefix = "&bSlimefunBlackList &r> ";
    @Setter
    @Getter
    private static String androidStopped = "检测到机器人正在破坏禁止破坏的方块, 已自动停止运行.";
    @Setter
    @Getter
    private static String breakBlackListBlock = "这个方块不能用爆炸工具破坏!";
}
