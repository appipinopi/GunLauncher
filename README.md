# GunLauncher (Core Framework)

GunLauncherは、Minecraft（Spigot / Paper 1.21）向けの**銃追加ランチャーフレームワーク**です。
GunLauncher自体には銃は含まれておらず、**弾薬・カスタムパーツ・銃本体を追加する外部プラグイン（アドオン）**を導入することで、高度な銃撃戦システムを構築できます。

---

## 拡張機能とAPI

### 1. 弾薬システム (`Ammunition`)
他のプラグインから `ItemRegistry.registerAmmo(...)` を呼び出すことで、カスタム弾薬を追加できます。
- プレイヤーは `/gun ammo <player> <ammoId> [amount]` で弾薬を獲得できます。

### 2. カスタムパーツシステム (`Attachment`)
スコープやサイレンサー、拡張マガジンなどのアタッチメントを作成・登録できます。
- パーツの種類 (`SCOPE`, `BARREL`, `MAGAZINE`, `STOCK`) ごとに、ダメージボーナスや弾数ボーナスを設定可能です。
- **装着方法**: オフハンドにパーツを持ち、スニークしながら銃を右クリックすることで銃に装着できます。

---

## 外部プラグイン開発者向けガイド

### サンプル: 弾薬とカスタムパーツの作成

```java
package com.example.samplegun;

import jp.wolfx.gunlauncher.api.Ammunition;
import jp.wolfx.gunlauncher.api.Attachment;
import jp.wolfx.gunlauncher.api.CustomGun;
import jp.wolfx.gunlauncher.api.ItemRegistry;
import jp.wolfx.gunlauncher.api.GunRegistry;
import jp.wolfx.gunlauncher.GunLauncherPlugin;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

// 1. 弾薬の実装
public class Ammo9mm implements Ammunition {
    @Override public String getId() { return "sample:ammo_9mm"; }
    @Override public String getName() { return "§79mm Ammo"; }
    @Override public ItemStack craftItemStack(int amount) {
        ItemStack item = new ItemStack(Material.PAPER, amount);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(getName());
        item.setItemMeta(meta);
        return item;
    }
    @Override public boolean matches(ItemStack item) {
        return item != null && item.getType() == Material.PAPER && item.hasItemMeta() && item.getItemMeta().getDisplayName().equals(getName());
    }
}

// 2. カスタムパーツ（拡張マガジン）の実装
public class ExtendedMag implements Attachment {
    @Override public String getId() { return "sample:extended_mag"; }
    @Override public String getSlot() { return "MAGAZINE"; }
    @Override public String getName() { return "§dExtended Mag (+15 Ammo)"; }
    @Override public int getAmmoBonus() { return 15; }
    @Override public ItemStack craftItemStack() {
        ItemStack item = new ItemStack(Material.IRON_INGOT);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(getName());
        item.setItemMeta(meta);
        return item;
    }
    @Override public boolean matches(ItemStack item) {
        return item != null && item.getType() == Material.IRON_INGOT && item.hasItemMeta() && item.getItemMeta().getDisplayName().equals(getName());
    }
}

// 3. プラグイン有効化時に登録
public class SampleGunPlugin extends org.bukkit.plugin.java.JavaPlugin {
    @Override
    public void onEnable() {
        ItemRegistry.registerAmmo(new Ammo9mm());
        ItemRegistry.registerAttachment(new ExtendedMag());
        getLogger().info("Ammo and attachments registered!");
    }
}
```

---

## コマンド一覧
- `/gun give <player> <gunId>` - 銃を配布
- `/gun ammo <player> <ammoId> [amount]` - 弾薬を配布
- `/gun part <player> <partId>` - カスタムパーツを配布
- `/gun list` - 登録されているすべての銃・弾薬・パーツの一覧を表示
