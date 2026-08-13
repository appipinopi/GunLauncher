package jp.wolfx.guncraft.item;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GlockPartsRegistry {
    private static final Map<Integer, String> PART_NAMES = new HashMap<>();

    static {
        // グロックの実銃構成に基づく100個の個別部品名称定義
        PART_NAMES.put(1, "スライド (Slide)");
        PART_NAMES.put(2, "バレル (Barrel)");
        PART_NAMES.put(3, "リコイルプロングロッド (Recoil Spring Guide Rod)");
        PART_NAMES.put(4, "リコイルスプリング (Recoil Spring)");
        PART_NAMES.put(5, "ポリマーフレーム (Polymer Frame)");
        PART_NAMES.put(6, "トリガー (Trigger)");
        PART_NAMES.put(7, "トリガーバー (Trigger Bar)");
        PART_NAMES.put(8, "コネクター (Connector)");
        PART_NAMES.put(9, "トリガー春 (Trigger Spring)");
        PART_NAMES.put(10, "トリガーピン (Trigger Pin)");
        PART_NAMES.put(11, "ロッキングブロック (Locking Block)");
        PART_NAMES.put(12, "ロッキングブロックピン (Locking Block Pin)");
        PART_NAMES.put(13, "スライドストップレバー (Slide Stop Lever)");
        PART_NAMES.put(14, "スライドロック (Slide Lock)");
        PART_NAMES.put(15, "スライドロックスプリング (Slide Lock Spring)");
        PART_NAMES.put(16, "マガジンキャッチ (Magazine Catch)");
        PART_NAMES.put(17, "マガジンキャッチスプリング (Magazine Catch Spring)");
        PART_NAMES.put(18, "ファイアリングピン (Firing Pin)");
        PART_NAMES.put(19, "ファイアリングピンセーフティ (Firing Pin Safety)");
        PART_NAMES.put(20, "ファイアリングピンセーフティスプリング (Safety Spring)");
        PART_NAMES.put(21, "エキストラクター (Extractor)");
        PART_NAMES.put(22, "エキストラクターデプレッサープランジャー (Plunger)");
        PART_NAMES.put(23, "エキストラクタープランジャースプリング (Plunger Spring)");
        PART_NAMES.put(24, "エジェクター (Ejector)");
        PART_NAMES.put(25, "スライドカバープレート (Slide Cover Plate)");
        PART_NAMES.put(26, "フロントサイト (Front Sight)");
        PART_NAMES.put(27, "リアサイト (Rear Sight)");
        PART_NAMES.put(28, "マガジンチューブ (Magazine Tube)");
        PART_NAMES.put(29, "マガジンフォロアー (Magazine Follower)");
        PART_NAMES.put(30, "マガジンバネ (Magazine Spring)");
        PART_NAMES.put(31, "マガジンフロアプレート (Magazine Floor Plate)");

        // 32〜100は精密・内部構成部品として一括生成
        for (int i = 32; i <= 100; i++) {
            PART_NAMES.put(i, "内部精密メカニカル部品 #" + i + " (Internal Component)");
        }
    }

    public static String getPartName(int id) {
        return PART_NAMES.getOrDefault(id, "専用銃器部品 #" + id);
    }

    public static ItemStack getPart(int id, boolean isDamaged) {
        ItemStack item = new ItemStack(isDamaged ? Material.BARRIER : Material.IRON_NUGGET);
        ItemMeta meta = item.getItemMeta();
        String name = getPartName(id);

        if (isDamaged) {
            meta.setDisplayName(ChatColor.RED + "【故障】 " + name);
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.DARK_RED + "パーツが破損しています！修復が必要です。");
            meta.setLore(lore);
            meta.setCustomModelData(6000 + id);
        } else {
            meta.setDisplayName(ChatColor.WHITE + name);
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GREEN + "状態: 正常 (Precision Part #" + id + ")");
            meta.setLore(lore);
            meta.setCustomModelData(5000 + id); // 例: 5001〜5100が各パーツのCustomModelData
        }
        item.setItemMeta(meta);
        return item;
    }
}
