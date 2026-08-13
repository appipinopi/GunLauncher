# GunLauncher (Core Framework)

GunLauncherは、Minecraft（Spigot / Paper 1.21）向けの**銃追加ランチャーフレームワーク**です。
GunLauncher単体では銃は存在せず、別の「銃追加プラグイン（アドオン）」を導入することで初めて武器が追加されます。

---

## アーキテクチャと仕組み

1. **GunLauncher（コア）**: コマンド（`/gun give`, `/gun list`）やイベント管理、APIレジストリを提供します。
2. **拡張プラグイン（アドオン）**: 開発者はGunLauncherを依存関係（`depend` または `softdepend`）に指定し、Javaコードで独自の銃を定義・登録します。

---

## 外部プラグイン開発者向けガイド（銃の追加方法）

他の開発者が独自の銃を追加するプラグイン（例: `SampleGunAddon`）を作成する手順は以下の通りです。

### 1. `plugin.yml` の設定
```yaml
name: SampleGunAddon
version: 1.0.0
main: com.example.samplegun.SampleGunPlugin
api-version: '1.21'
depend: [GunLauncher]
```

### 2. `CustomGun` インターフェースの実装
```java
package com.example.samplegun;

import jp.wolfx.gunlauncher.api.CustomGun;
import jp.wolfx.gunlauncher.GunLauncherPlugin;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class M4A1Gun implements CustomGun {
    private final NamespacedKey gunKey = new NamespacedKey(GunLauncherPlugin.getInstance(), "gun_id");

    @Override
    public String getId() {
        return "sample:m4a1";
    }

    @Override
    public String getName() {
        return "§bM4A1 Assault Rifle";
    }

    @Override
    public ItemStack craftItemStack() {
        ItemStack item = new ItemStack(Material.IRON_HORSE_ARMOR);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', getName()));
            meta.setCustomModelData(1001);
            meta.getPersistentDataContainer().set(gunKey, PersistentDataType.STRING, getId());
            item.setItemMeta(meta);
        }
        return item;
    }

    @Override
    public void onShoot(Player player, ItemStack gunItem) {
        player.sendMessage("§cBang! Shot fired from M4A1.");
        player.getWorld().playSound(player.getLocation(), org.bukkit.Sound.ENTITY_FIREWORK_ROCKET_BLAST, 1.5f, 1.0f);
    }

    @Override
    public void onReload(Player player, ItemStack gunItem) {
        player.sendMessage("§eReloading M4A1...");
        player.getWorld().playSound(player.getLocation(), org.bukkit.Sound.BLOCK_IRON_DOOR_CLOSE, 1.0f, 1.0f);
    }
}
```

### 3. プラグイン有効化時に登録
```java
package com.example.samplegun;

import jp.wolfx.gunlauncher.api.GunRegistry;
import org.bukkit.plugin.java.JavaPlugin;

public class SampleGunPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        // 銃を登録
        GunRegistry.registerGun(new M4A1Gun());
        getLogger().info("Sample M4A1 gun registered successfully!");
    }
}
```

---

## コマンド
初期状態では、召喚および管理用コマンドのみが提供されます。
- `/gun give <player> <gunId>` - 指定したプレイヤーにカスタム銃を配布します。
- `/gun list` - 登録されているすべての銃の一覧を表示します。
