package jp.wolfx.guncraft.item;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpandedGlockPartsRegistry {
    private static final Map<Integer, String> PART_NAMES = new HashMap<>();

    static {
        // Glock Gen5 Official Parts List (Sportec / Glock Reference)
        PART_NAMES.put(1, "Slide G17 Gen5/FS (スライド)");
        PART_NAMES.put(2, "Barrel G17 Gen5 / M (バレル)");
        PART_NAMES.put(3, "Recoil spring assembled (リコイルスプリング)");
        PART_NAMES.put(4, "Firing pin assembled (ファイアリングピン)");
        PART_NAMES.put(5, "Spacer sleeve (スペーサーリーブ)");
        PART_NAMES.put(6, "Firing pin spring 24N (ファイアリングピンバネ)");
        PART_NAMES.put(7, "Spring cups (スプリングカップ)");
        PART_NAMES.put(8, "Firing pin channel liner (チャンネルライナー)");
        PART_NAMES.put(9, "Firing pin safety assembled (FPセーフティ)");
        PART_NAMES.put(10, "Extractor Loaded Chamber Indicator (エキストラクター)");
        PART_NAMES.put(11, "Extractor depressor plunger assembled (プランジャー)");
        PART_NAMES.put(12, "Spring-loaded bearing (スプリングロードベアリング)");
        PART_NAMES.put(13, "Slide cover plate (スライドカバープレート)");
        PART_NAMES.put(14, "Rear sight 6,1 GMS polymer (リアサイト)");
        PART_NAMES.put(15, "Front sight 4.1 set polymer (フロントサイト)");
        PART_NAMES.put(16, "Frame G17 Gen5 flared assembled (ポリマーフレーム)");
        PART_NAMES.put(17, "Beavertail set (ビーバーテイルセット)");
        PART_NAMES.put(18, "Magazine catch spring (マガジンキャッチバネ)");
        PART_NAMES.put(19, "Magazine catch reversible (マガジンキャッチ)");
        PART_NAMES.put(20, "Slide lock spring (スライドロックバネ)");
        PART_NAMES.put(21, "Slide lock (スライドロック)");
        PART_NAMES.put(22, "Locking block (ロッキングブロック)");
        PART_NAMES.put(23, "Trigger mechanism housing with ejector (ハウジング/エジェクター)");
        PART_NAMES.put(24, "Connector 5 (dot) (コネクター)");
        PART_NAMES.put(25, "Trigger with trigger bar AMBI (トリガー/トリガーバー)");
        PART_NAMES.put(26, "Slide stop lever AMBI (スライドストップレバー)");
        PART_NAMES.put(27, "Trigger pin AMBI (トリガーピン)");
        PART_NAMES.put(28, "Trigger housing pin MBS (ハウジングピン)");
        PART_NAMES.put(29, "Magazine tube (マガジンチューブ)");
        PART_NAMES.put(30, "Follower orange (マガジンフォロアー)");
        PART_NAMES.put(31, "Magazine spring (マガジンバネ)");
        PART_NAMES.put(32, "Magazine floor plate (フロアプレート)");

        // ネジ類 (0.5mm ~ 2.0mm)
        for (int i = 33; i <= 60; i++) {
            double mm = 0.5 + ((i - 33) * 0.05);
            PART_NAMES.put(i, "Gen5 Precision Screw (" + String.format("%.2f", mm) + "mm Micro Screw)");
        }

        // 61〜150はGen5内部精密メカニカル・スプリング・ピン類
        for (int i = 61; i <= 150; i++) {
            PART_NAMES.put(i, "Gen5 Internal Precision Component #" + i);
        }
    }

    public static String getPartName(int id) {
        return PART_NAMES.getOrDefault(id, "Glock Gen5 Part #" + id);
    }

    public static ItemStack getPart(int id, boolean isDamaged) {
        ItemStack item = new ItemStack(isDamaged ? Material.BARRIER : Material.IRON_NUGGET);
        ItemMeta meta = item.getItemMeta();
        String name = getPartName(id);

        if (isDamaged) {
            meta.setDisplayName(ChatColor.RED + "【故障/破損】 " + name);
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.DARK_RED + "Gen5規格のパーツに歪みや破損があります！");
            meta.setLore(lore);
            meta.setCustomModelData(8000 + id);
        } else {
            meta.setDisplayName(ChatColor.WHITE + name);
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GREEN + "状態: Glock Gen5公式規格 (Part ID #" + id + ")");
            meta.setLore(lore);
            meta.setCustomModelData(7000 + id);
        }
        item.setItemMeta(meta);
        return item;
    }
}
