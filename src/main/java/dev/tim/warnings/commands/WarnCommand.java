package dev.tim.warnings.commands;

import dev.tim.warnings.utils.ChatColorUtil;
import dev.tim.warnings.utils.WarningStorageUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarnCommand extends ChatColorUtil implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("warnings.warn")) {
                if (args.length >= 2) {
                    if (Bukkit.getPlayer(args[0]) != null) {
                        Player target = Bukkit.getPlayer(args[0]);
                        StringBuilder reason = new StringBuilder();

                        for (int i = 1; i < (args.length - 1); i++) {
                            reason.append(args[i]).append(" ");
                        }
                        reason.append(args[args.length - 1]);

                        WarningStorageUtil.createWarning(target, player, reason.toString());
                        player.sendMessage(SUCCESS + "You have successfully warned " + target.getName() + "!");
                    } else {
                        player.sendMessage(WRONG + "The player you want to warn can not be found!");
                    }
                } else {
                    player.sendMessage(WRONG + "Please use: /warn <player> <reason>");
                }
            } else {
                player.sendMessage(WRONG + "You don't have permission to use: /warn");
            }
        }

        return true;
    }
}
