package jp.wolfx.guncraft.item;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class CraftMaterials {

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
