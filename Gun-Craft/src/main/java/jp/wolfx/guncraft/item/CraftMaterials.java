package jp.wolfx.guncraft.item;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class CraftMaterials {

    public static ItemStack getPrintingTable() {
        ItemStack item = new ItemStack(Material.CARTOGRAPHY_TABLE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_BLUE + "銃器プリント台 (Printing Table)");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "紙とイカ墨から設計図を作成する機械");
        meta.setLore(lore);
        meta.setCustomModelData(5001);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack getRollingMachine() {
        ItemStack item = new ItemStack(Material.DISPENSER);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_GREEN + "丸める機械 (Rolling Machine)");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "真鍮と鉛球から弾薬を成形する機械");
        meta.setLore(lore);
        meta.setCustomModelData(5002);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack getAssemblyTable() {
        ItemStack item = new ItemStack(Material.SMITHING_TABLE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "銃器組立台 (Assembly Table)");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "100個の部品と設計図から銃本体を組み立てる");
        meta.setLore(lore);
        meta.setCustomModelData(5003);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack getGunPart(int partNumber, boolean isDamaged) {
        ItemStack item = new ItemStack(isDamaged ? Material.BARRIER : Material.IRON_INGOT);
        ItemMeta meta = item.getItemMeta();
        if (isDamaged) {
            meta.setDisplayName(ChatColor.RED + "【故障】銃器部品 #" + partNumber + " (Damaged)");
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.DARK_RED + "この部品は破損しています！修理が必要です。");
            meta.setLore(lore);
            meta.setCustomModelData(6000 + partNumber);
        } else {
            meta.setDisplayName(ChatColor.WHITE + "精密銃器部品 #" + partNumber);
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GREEN + "状態: 正常 (Normal)");
            meta.setLore(lore);
            meta.setCustomModelData(5000 + partNumber);
        }
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack getBrass() {
        ItemStack item = new ItemStack(Material.GOLD_INGOT);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.YELLOW + "真鍮 (Brass Ingot)");
        meta.setCustomModelData(4001);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack getLeadBullet() {
        ItemStack item = new ItemStack(Material.IRON_NUGGET);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY + "鉛球 (Lead Bullet)");
        meta.setCustomModelData(4002);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack getPrintedPaper(String gunId, String gunName) {
        ItemStack item = new ItemStack(Material.PAPER);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.AQUA + "プリント済み設計図: " + gunName);
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "ターゲットID: " + gunId);
        lore.add(ChatColor.YELLOW + "必要パーツ数: 100枚");
        meta.setLore(lore);
        meta.setCustomModelData(4003);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack getGunPartComponent() {
        ItemStack item = new ItemStack(Material.IRON_INGOT);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.WHITE + "精密銃器パーツ (Gun Part)");
        meta.setCustomModelData(4004);
        item.setItemMeta(meta);
        return item;
    }
}
