package org.pongchi.randombox;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class RandomBox extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultData();
        getCommand("랜덤박스").setExecutor(new RandomBoxCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void saveDefaultData() {
        if (!new File(getDataFolder(), "boxes.json").exists())
            saveResource("boxes.json", false);
    }
}
