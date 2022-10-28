package dev.tim.warnings.commands;

import dev.tim.warnings.models.Warning;
import dev.tim.warnings.utils.ChatColorUtil;
import dev.tim.warnings.utils.WarningStorageUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DeleteWarnCommand extends ChatColorUtil implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(player.hasPermission("warnings.delete")){
                if(args.length == 1){
                    for (Warning warn : WarningStorageUtil.findAllWarnings()){
                        if(warn.getId().equals(args[0])){
                            WarningStorageUtil.deleteWarning(args[0]);
                            player.sendMessage(SUCCESS + "Warning successfully deleted!");
                        } else {
                            player.sendMessage(WRONG + "Warning not found!");
                        }
                        return true;
                    }
                } else {
                    player.sendMessage(WRONG + "Please use: /deletewarn <id>");
                }
            } else {
                player.sendMessage(WRONG + "You don't have permission to use: /deletewarn");
            }
        }

        return true;
    }
}
