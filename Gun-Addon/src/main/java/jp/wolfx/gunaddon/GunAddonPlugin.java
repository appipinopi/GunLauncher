package jp.wolfx.gunaddon;

import jp.wolfx.gunmain.api.GunRegistry;
import jp.wolfx.gunaddon.sample.SampleCustomGun;
import org.bukkit.plugin.java.JavaPlugin;

public class GunAddonPlugin extends JavaPlugin {
    private static GunAddonPlugin instance;

    @Override
    public void onEnable() {
        instance = this;
        getLogger().info("=== Gun-Addon (Personal Modification) Enabled ===");

        // Register custom guns created by personal modification
        GunRegistry.registerGun(new SampleCustomGun());
        getLogger().info("Registered custom addon gun: addon:custom_rifle");
    }

    @Override
    public void onDisable() {
        getLogger().info("Gun-Addon Disabled.");
    }

    public static GunAddonPlugin getInstance() {
        return instance;
    }
}
