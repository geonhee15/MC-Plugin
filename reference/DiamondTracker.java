package io.github.geonhee15.mcplugin;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

/**
 * 플레이어별 다이아 채굴 횟수를 PersistentDataContainer에 저장.
 * PDC는 플레이어 데이터 파일에 같이 저장돼서 서버 재시작해도 남는다.
 */
public final class DiamondTracker {

    private final NamespacedKey key;

    public DiamondTracker(MCPlugin plugin) {
        this.key = new NamespacedKey(plugin, "diamonds_mined");
    }

    public int get(Player player) {
        PersistentDataContainer pdc = player.getPersistentDataContainer();
        return pdc.getOrDefault(key, PersistentDataType.INTEGER, 0);
    }

    public int increment(Player player) {
        int next = get(player) + 1;
        player.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, next);
        return next;
    }
}
