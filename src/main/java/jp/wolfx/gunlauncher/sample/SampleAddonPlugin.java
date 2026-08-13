package jp.wolfx.gunlauncher.sample;

import jp.wolfx.gunlauncher.api.GunRegistry;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * サンプル銃を GunLauncher に登録するアドオン・プラグインの例です。
 * 実際には別のプラグインとして独立して作成し、depend: [GunLauncher] を指定します。
 */
public class SampleAddonPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // サンプル銃を登録
        GunRegistry.registerGun(new SampleM4A1Gun());
        
        getLogger().info("Sample M4A1 Gun has been registered to GunLauncher!");
    }
}
