package jp.wolfx.gunlauncher;

import jp.wolfx.gunlauncher.command.GunCommand;
import jp.wolfx.gunlauncher.listener.GunListener;
import org.bukkit.plugin.java.JavaPlugin;

public class GunLauncherPlugin extends JavaPlugin {
    private static GunLauncherPlugin instance;

    @Override
    public void onEnable() {
        instance = this;

        GunCommand gunCommand = new GunCommand();
        if (getCommand("gun") != null) {
            getCommand("gun").setExecutor(gunCommand);
            getCommand("gun").setTabCompleter(gunCommand);
        }

        getServer().getPluginManager().registerEvents(new GunListener(this), this);

        getLogger().info("GunLauncher Core Framework has been enabled successfully.");
        getLogger().info("Waiting for gun plugins to register via GunRegistry API...");
    }

    @Override
    public void onDisable() {
        getLogger().info("GunLauncher Core Framework has been disabled.");
    }

    public static GunLauncherPlugin getInstance() {
        return instance;
    }
}
