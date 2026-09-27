package io.github.geonhee15.mcplugin.listener;

import io.github.geonhee15.mcplugin.DiamondTracker;
import io.github.geonhee15.mcplugin.MCPlugin;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public final class DiamondListener implements Listener {

    private final MCPlugin plugin;
    private final DiamondTracker tracker;

    public DiamondListener(MCPlugin plugin, DiamondTracker tracker) {
        this.plugin = plugin;
        this.tracker = tracker;
    }

    @EventHandler(ignoreCancelled = true)
    public void onBlockBreak(BlockBreakEvent event) {
        Material type = event.getBlock().getType();
        if (type != Material.DIAMOND_ORE && type != Material.DEEPSLATE_DIAMOND_ORE) return;

        int count = tracker.increment(event.getPlayer());
        plugin.getLogger().info(event.getPlayer().getName() + " mined diamond #" + count);

        if (!plugin.getConfig().getBoolean("announce-diamonds", true)) return;

        String raw = plugin.getConfig().getString("message", "<player> mined a diamond (<count>)");
        var message = MiniMessage.miniMessage().deserialize(raw,
                Placeholder.unparsed("player", event.getPlayer().getName()),
                Placeholder.unparsed("count", String.valueOf(count)));
        plugin.getServer().broadcast(message);
    }
}
