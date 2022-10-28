package dev.tim.warnings.commands;

import dev.tim.warnings.models.Warning;
import dev.tim.warnings.utils.ChatColorUtil;
import dev.tim.warnings.utils.WarningStorageUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CheckWarnsCommand extends ChatColorUtil implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(player.hasPermission("warnings.check")){
                if(args.length == 1){
                    for (Warning warn : WarningStorageUtil.findAllWarnings()){
                        if(warn.getPlayerName().equalsIgnoreCase(args[0])){
                            player.sendMessage(transCC("&a&lID: " + warn.getId()));
                            player.sendMessage(transCC("&cWarned: &f" + warn.getPlayerName()));
                            player.sendMessage(transCC("&eWarned by: &f" + warn.getPlayerWhoWarnedName()));
                            player.sendMessage(transCC("&2Reason: &f" + warn.getReason()));
                            player.sendMessage(transCC("&5Date: &f" + warn.getDate()));
                            player.sendMessage(transCC("&7----------------------------"));
                        } else {
                            player.sendMessage(WRONG + "This player has no warnings!");
                            return true;
                        }
                    }
                } else {
                    player.sendMessage(WRONG + "Please use: /checkwarns <player>");
                }
            } else {
                player.sendMessage(WRONG + "You don't have permission to use: /deletewarn");
            }
        }

        return true;
    }
}
