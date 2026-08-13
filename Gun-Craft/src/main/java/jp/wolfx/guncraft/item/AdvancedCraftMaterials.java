package jp.wolfx.guncraft.item;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class AdvancedCraftMaterials {

    // 1. 専門機械
    public static ItemStack getSpringCoiler() {
        ItemStack item = new ItemStack(Material.PISTON);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.LIGHT_PURPLE + "スプリング巻き機 (Spring Coiler)");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "鋼線からスプリング部品を成形する機械");
        meta.setLore(lore);
        meta.setCustomModelData(7001);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack getCncMachine() {
        ItemStack item = new ItemStack(Material.STONECUTTER);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.AQUA + "CNC精密加工機 (CNC Machine)");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "金属塊からスライドやトリガーを削り出す");
        meta.setLore(lore);
        meta.setCustomModelData(7002);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack getInjectionMolder() {
        ItemStack item = new ItemStack(Material.FURNACE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "射出成形機 (Injection Molder)");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "ポリマーフレームを高圧成形する機械");
        meta.setLore(lore);
        meta.setCustomModelData(7003);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack getHammerForge() {
        ItemStack item = new ItemStack(Material.ANVIL);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.RED + "ハンマー鍛造機 (Hammer Forge)");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "高精度な銃身（バレル）を鍛造する機械");
        meta.setLore(lore);
        meta.setCustomModelData(7004);
        item.setItemMeta(meta);
        return item;
    }

    // 2. 特殊部品カテゴリ
    public static ItemStack getPartCategory(String categoryName, int partNumber, boolean isDamaged) {
        ItemStack item = new ItemStack(isDamaged ? Material.BARRIER : Material.IRON_NUGGET);
        ItemMeta meta = item.getItemMeta();
        if (isDamaged) {
            meta.setDisplayName(ChatColor.RED + "【故障】" + categoryName + " #" + partNumber);
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.DARK_RED + "パーツが破損しています！修理が必要です。");
            meta.setLore(lore);
            meta.setCustomModelData(8000 + partNumber);
        } else {
            meta.setDisplayName(ChatColor.WHITE + categoryName + " #" + partNumber);
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GREEN + "状態: 正常 (Normal Precision)");
            meta.setLore(lore);
            meta.setCustomModelData(7500 + partNumber);
        }
        item.setItemMeta(meta);
        return item;
    }
}
