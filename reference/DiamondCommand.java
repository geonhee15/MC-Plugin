package io.github.geonhee15.mcplugin.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.github.geonhee15.mcplugin.DiamondTracker;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;

/**
 * /diamonds : 내가 캔 다이아 개수 출력
 */
public final class DiamondCommand {

    private DiamondCommand() {}

    public static LiteralCommandNode<CommandSourceStack> build(DiamondTracker tracker) {
        return Commands.literal("diamonds")
                .executes(ctx -> {
                    if (!(ctx.getSource().getSender() instanceof Player player)) {
                        ctx.getSource().getSender().sendMessage(Component.text("플레이어만 사용 가능", NamedTextColor.RED));
                        return Command.SINGLE_SUCCESS;
                    }
                    int count = tracker.get(player);
                    player.sendMessage(Component.text("지금까지 캔 다이아: ", NamedTextColor.GRAY)
                            .append(Component.text(count, NamedTextColor.AQUA)));
                    return Command.SINGLE_SUCCESS;
                })
                .build();
    }
}
