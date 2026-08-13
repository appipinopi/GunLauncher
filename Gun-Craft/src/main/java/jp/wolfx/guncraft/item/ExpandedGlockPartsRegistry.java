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
        // 150以上の精密部品・ネジ・スプリングの詳細定義
        PART_NAMES.put(1, "スライド本体 (Slide Housing)");
        PART_NAMES.put(2, "銃身ブロック (Match-Grade Barrel)");
        PART_NAMES.put(3, "リコイルガイドロッド (Recoil Guide Rod)");
        PART_NAMES.put(4, "デュアルリコイルスプリング (Dual Recoil Spring)");
        PART_NAMES.put(5, "強化ポリマーフレーム (Reinforced Polymer Frame)");
        PART_NAMES.put(6, "ファイヤーコントロールトリガー (Trigger Shoe)");
        PART_NAMES.put(7, "トリガーバー連結アーム (Trigger Bar)");
        PART_NAMES.put(8, "ゴーストコネクター (Connector)");
        PART_NAMES.put(9, "トルク・トリガーバネ (Torsion Trigger Spring)");
        PART_NAMES.put(10, "メインスライドピン (Main Frame Pin)");
        PART_NAMES.put(11, "ロッキングブロック (Steel Locking Block)");
        PART_NAMES.put(12, "ロッキングブロックピン (Locking Block Pin)");
        PART_NAMES.put(13, "アンビ・スライドストップ (Slide Stop Lever)");
        PART_NAMES.put(14, "スライドロックピン (Slide Lock)");
        PART_NAMES.put(15, "スライドロックスプリング (Slide Lock Spring)");
        PART_NAMES.put(16, "リバーシブルマガジンキャッチ (Magazine Catch)");
        PART_NAMES.put(17, "キャッチリーフスプリング (Catch Leaf Spring)");
        PART_NAMES.put(18, "チタン・ファイアリングピン (Firing Pin)");
        PART_NAMES.put(19, "ドロップセーフティプランジャー (Firing Pin Safety)");
        PART_NAMES.put(20, "セーフティスプリング (Safety Spring)");
        PART_NAMES.put(21, "CNCエキストラクター (Extractor)");
        PART_NAMES.put(22, "エキストラクターデプレッサー (Plunger)");
        PART_NAMES.put(23, "プランジャースプリング (Plunger Spring)");
        PART_NAMES.put(24, "スチールエジェクター (Ejector)");
        PART_NAMES.put(25, "バックプレート (Slide Cover Plate)");
        PART_NAMES.put(26, "ナイトフロントサイト (Tritium Front Sight)");
        PART_NAMES.put(27, "アジャスタブルリアサイト (Adjustable Rear Sight)");
        
        // ネジ類 (0.5mm ~ 2.0mm)
        for (int i = 28; i <= 50; i++) {
            double mm = 0.5 + ((i - 28) * 0.05);
            PART_NAMES.put(i, "精密固定ネジ (" + String.format("%.2f", mm) + "mm Micro Screw)");
        }

        // 51〜150は内部メカニカル・スプリング・ピン類
        for (int i = 51; i <= 150; i++) {
            PART_NAMES.put(i, "内部メカニカルコンポーネント #" + i + " (Precision Component)");
        }
    }

    public static String getPartName(int id) {
        return PART_NAMES.getOrDefault(id, "特殊銃器パーツ #" + id);
    }

    public static ItemStack getPart(int id, boolean isDamaged) {
        ItemStack item = new ItemStack(isDamaged ? Material.BARRIER : Material.IRON_NUGGET);
        ItemMeta meta = item.getItemMeta();
        String name = getPartName(id);

        if (isDamaged) {
            meta.setDisplayName(ChatColor.RED + "【故障/破損】 " + name);
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.DARK_RED + "パーツの精度不良または破損があります！");
            meta.setLore(lore);
            meta.setCustomModelData(8000 + id);
        } else {
            meta.setDisplayName(ChatColor.WHITE + name);
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GREEN + "状態: 正常高精度 (Part ID #" + id + ")");
            meta.setLore(lore);
            meta.setCustomModelData(7000 + id);
        }
        item.setItemMeta(meta);
        return item;
    }
}
