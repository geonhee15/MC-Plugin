package io.github.geonhee15.mcplugin;

import io.github.geonhee15.mcplugin.listener.PlayerJoinListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class MCPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("MCPlugin 켜짐");
        this.getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);

    }

    @Override
    public void onDisable() {
        getLogger().info("MCPlugin 꺼짐");
    }
}
