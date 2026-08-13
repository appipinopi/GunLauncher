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
        // Glock Gen5 Official Spare Parts List (Pos 1 - 35)
        PART_NAMES.put(1, "Slide G17 Gen5/FS (Item No. 43773)");
        PART_NAMES.put(2, "Barrel G17 Gen5 / M (Item No. 41002)");
        PART_NAMES.put(3, "Recoil spring assembled (Item No. 33786)");
        PART_NAMES.put(4, "Firing pin assembled (Item No. 39327)");
        PART_NAMES.put(5, "Spacer sleeve (Item No. 56)");
        PART_NAMES.put(6, "Firing pin spring 24N (Item No. 63)");
        PART_NAMES.put(7, "Spring cups (Item No. 70)");
        PART_NAMES.put(8, "Firing pin safety assembled (Item No. 33782)");
        PART_NAMES.put(9, "Firing pin safety spring (Item No. 91)");
        PART_NAMES.put(10, "Extractor Loaded Chamber Indicator (Item No. 33774)");
        PART_NAMES.put(11, "Extractor depressor plunger assembled (Item No. 3449)");
        PART_NAMES.put(12, "Extractor depressor plunger spring (Item No. 33522)");
        PART_NAMES.put(13, "Spring-loaded bearing (Item No. 2714)");
        PART_NAMES.put(14, "Slide cover plate (Item No. 33784)");
        PART_NAMES.put(15, "Rear sight 6,1 GMS polymer (Item No. 39733)");
        PART_NAMES.put(16, "Front sight 4.1 set polymer (Item No. 7073)");
        PART_NAMES.put(17, "Frame G17 Gen5 flared assembled (Item No. 47985)");
        PART_NAMES.put(18, "Magazine catch spring (Item No. 39543)");
        PART_NAMES.put(19, "Magazine catch reversible (Item No. 7534)");
        PART_NAMES.put(20, "Slide lock spring (Item No. 39567)");
        PART_NAMES.put(21, "Slide lock (Item No. 33706)");
        PART_NAMES.put(22, "Locking block (Item No. 7894)");
        PART_NAMES.put(23, "Trigger mechanism housing with ejector (Item No. 47208)");
        PART_NAMES.put(24, "Connector 5 (dot) (Item No. 7965)");
        PART_NAMES.put(25, "Trigger with trigger bar AMBI (Item No. 39702)");
        PART_NAMES.put(26, "Slide stop lever AMBI (Item No. 47247)");
        PART_NAMES.put(27, "Trigger pin AMBI (Item No. 8298)");
        PART_NAMES.put(28, "Trigger housing pin SF (Item No. 1774)");
        PART_NAMES.put(29, "Trigger housing pin MBS (Item No. 7416)");
        PART_NAMES.put(30, "Magazine tube (Item No. 1587)");
        PART_NAMES.put(31, "Follower orange (Item No. 5233)");
        PART_NAMES.put(32, "Magazine spring (Item No. 33510)");
        PART_NAMES.put(33, "Magazine insert (Item No. 1693)");
        PART_NAMES.put(34, "Magazine floor plate 01 (Item No. 39283)");
    }

    public static String getPartName(int id) {
        return PART_NAMES.getOrDefault(id, "Glock Gen5 Part #" + id);
    }

    public static ItemStack getPart(int id, boolean isDamaged) {
        ItemStack item = new ItemStack(isDamaged ? Material.BARRIER : Material.IRON_NUGGET);
        ItemMeta meta = item.getItemMeta();
        String name = getPartName(id);

        if (isDamaged) {
            meta.setDisplayName(ChatColor.RED + "【故障/破損】 Pos." + id + " " + name);
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.DARK_RED + "公式パーツに破損または不適合があります！");
            meta.setLore(lore);
            meta.setCustomModelData(6000 + id);
        } else {
            meta.setDisplayName(ChatColor.WHITE + "Pos." + id + " " + name);
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GREEN + "状態: Glock Gen5 公式パーツ");
            meta.setLore(lore);
            meta.setCustomModelData(7000 + id);
        }
        item.setItemMeta(meta);
        return item;
    }
}
