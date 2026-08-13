# GunLauncher (Core Framework)

GunLauncherは、Minecraft（Spigot / Paper 1.21）向けの**銃追加ランチャーフレームワーク**です。
GunLauncher単体では銃は存在せず、**弾薬・カスタムパーツ・銃本体を追加する外部プラグイン（アドオン）**を導入することで武器が追加されます。

---

## 🔫 銃のサンプル一覧と CustomModelData

リポジトリには、リソースパックの `CustomModelData` に対応した以下の銃のサンプル実装が含まれています。

| 銃ID (`id`) | 表示名 | CustomModelData | 特徴 |
| :--- | :--- | :--- | :--- |
| `sample:m4a1` | M4A1 Assault Rifle | `1001` | 標準的なアサルトライフル（中ダメージ・中連射） |
| `sample:ak47` | AK-47 Assault Rifle | `1002` | 高威力・高反動の重厚なアサルトライフル |
| `sample:m16` | M16 Burst Rifle | `1003` | 3点バースト射撃を行う高精度のライフル |
| `sample:mx7` | MX7 Submachine Gun | `1004` | 大容量マガジン（50発）と高速連射のSMG |
| `sample:infantry_rifle` | 歩兵銃 (Infantry Rifle) | `1005` | 一撃必殺の超高ダメージ・低装填数のボルトアクション |

---

## 💻 外部プラグインでの実装例 (`SampleAddonPlugin.java`)

これらの銃を有効化するには、アドオン側のプラグインで `GunRegistry.registerGun()` を呼び出します。

```java
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
        
        getLogger().info("Sample Guns registered successfully!");
    }
}
```
