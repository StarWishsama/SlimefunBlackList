package io.github.starwishsama.slimefunblacklist.objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bukkit.Material;

@Data
@AllArgsConstructor
public class BlackListItem {
    private Material material;
    private boolean android;
}
