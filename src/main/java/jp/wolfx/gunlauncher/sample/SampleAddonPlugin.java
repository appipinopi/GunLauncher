package jp.wolfx.gunlauncher.sample;

import jp.wolfx.gunlauncher.api.GunRegistry;
import org.bukkit.plugin.java.JavaPlugin;

public class SampleAddonPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // すべてのサンプル銃を登録
        GunRegistry.registerGun(new SampleM4A1Gun());
        GunRegistry.registerGun(new SampleAK47Gun());
        GunRegistry.registerGun(new SampleM16Gun());
        GunRegistry.registerGun(new SampleMX7Gun());
        GunRegistry.registerGun(new SampleInfantryRifleGun());
        
        getLogger().info("Sample Guns (M4A1, AK-47, M16, MX7, Infantry Rifle) have been registered!");
    }
}
