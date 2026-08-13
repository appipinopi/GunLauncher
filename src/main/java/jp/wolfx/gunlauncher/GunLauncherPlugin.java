package jp.wolfx.gunlauncher;

import jp.wolfx.gunlauncher.command.GunCommand;
import jp.wolfx.gunlauncher.listener.GunListener;
import org.bukkit.plugin.java.JavaPlugin;

public class GunLauncherPlugin extends JavaPlugin {
    private static GunLauncherPlugin instance;

    @Override
    public void onEnable() {
        instance = this;

        // 1. Initialize built-in famous guns, ammos, and attachments
        jp.wolfx.gunlauncher.module.BuiltinGunsInitializer.init();

        // 2. Load deep modules from plugins/GunLauncher/modules/
        jp.wolfx.gunlauncher.module.ModuleLoader.loadModules(this);

        GunCommand gunCommand = new GunCommand();
        if (getCommand("gun") != null) {
            getCommand("gun").setExecutor(gunCommand);
            getCommand("gun").setTabCompleter(gunCommand);
        }

        getServer().getPluginManager().registerEvents(new GunListener(this), this);

        getLogger().info("GunLauncher Deep Engine has been enabled successfully!");
        getLogger().info("Total Registered Guns: " + jp.wolfx.gunlauncher.api.GunRegistry.getGuns().size());
    }

    @Override
    public void onDisable() {
        getLogger().info("GunLauncher Core Framework has been disabled.");
    }

    public static GunLauncherPlugin getInstance() {
        return instance;
    }
}
