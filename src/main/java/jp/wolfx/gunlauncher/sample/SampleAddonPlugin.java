package jp.wolfx.gunlauncher.sample;

import jp.wolfx.gunlauncher.api.GunRegistry;
import jp.wolfx.gunlauncher.api.ItemRegistry;
import jp.wolfx.gunlauncher.sample.ammo.CaliberAmbos;
import jp.wolfx.gunlauncher.sample.attachment.SampleAttachments;
import jp.wolfx.gunlauncher.sample.guns.AdvancedGunsPart1;
import jp.wolfx.gunlauncher.sample.guns.AdvancedGunsPart2;
import org.bukkit.plugin.java.JavaPlugin;

public class SampleAddonPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // 1. 口径別弾薬の登録
        ItemRegistry.registerAmmo(new CaliberAmbos.Ammo9mm());
        ItemRegistry.registerAmmo(new CaliberAmbos.Ammo556mm());
        ItemRegistry.registerAmmo(new CaliberAmbos.Ammo762mm());
        ItemRegistry.registerAmmo(new CaliberAmbos.Ammo50AE());
        ItemRegistry.registerAmmo(new CaliberAmbos.Ammo50BMG());

        // 2. カスタムパーツの登録
        ItemRegistry.registerAttachment(new SampleAttachments.ScopeX4());
        ItemRegistry.registerAttachment(new SampleAttachments.Silencer());
        ItemRegistry.registerAttachment(new SampleAttachments.ExtendedMag());
        ItemRegistry.registerAttachment(new SampleAttachments.HeavyStock());

        // 3. 基本銃の登録
        GunRegistry.registerGun(new SampleM4A1Gun());
        GunRegistry.registerGun(new SampleAK47Gun());
        GunRegistry.registerGun(new SampleM16Gun());
        GunRegistry.registerGun(new SampleMX7Gun());
        GunRegistry.registerGun(new SampleInfantryRifleGun());

        // 4. 有名銃（SCAR-H, Desert Eagle, AWP, Barrett M82）の登録
        GunRegistry.registerGun(new AdvancedGunsPart1.ScarH());
        GunRegistry.registerGun(new AdvancedGunsPart1.DesertEagle());
        GunRegistry.registerGun(new AdvancedGunsPart2.AWP());
        GunRegistry.registerGun(new AdvancedGunsPart2.BarrettM82());
        
        getLogger().info("=== GunLauncher Advanced Sample Addon Loaded Successfully ===");
        getLogger().info("Registered Ammos: 5 | Attachments: 4 | Guns: 9");
    }
}
