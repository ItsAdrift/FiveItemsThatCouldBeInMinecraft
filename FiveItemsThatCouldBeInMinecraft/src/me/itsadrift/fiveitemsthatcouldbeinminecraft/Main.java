package me.itsadrift.fiveitemsthatcouldbeinminecraft;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main instance;

    @Override
    public void onEnable() {
        instance = this;

        getCommand("witem").setExecutor(new WItemCommand());
        Bukkit.getPluginManager().registerEvents(new ItemListener(), this);
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public static Main getInstance() {
        return instance;
    }

}
