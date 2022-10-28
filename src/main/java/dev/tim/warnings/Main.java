package dev.tim.warnings;

import dev.tim.warnings.commands.CheckWarnsCommand;
import dev.tim.warnings.commands.DeleteWarnCommand;
import dev.tim.warnings.commands.WarnCommand;
import dev.tim.warnings.utils.WarningStorageUtil;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.FileNotFoundException;

public final class Main extends JavaPlugin {

    private static Main plugin;

    @Override
    public void onEnable() {
        plugin = this;

        try {
            WarningStorageUtil.loadWarnings();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        registerCommands();
        registerListeners();
    }

    @Override
    public void onDisable() {

    }

    private void registerCommands(){
        getCommand("warn").setExecutor(new WarnCommand());
        getCommand("deletewarn").setExecutor(new DeleteWarnCommand());
        getCommand("checkwarns").setExecutor(new CheckWarnsCommand());
    }

    private void registerListeners(){

    }

    public static Main getPlugin() {
        return plugin;
    }
}