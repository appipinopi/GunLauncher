package jp.wolfx.guncraft.item;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ProcessMaterials {

    public static ItemStack getCarbonSteelBlock() {
        ItemStack item = new ItemStack(Material.IRON_BLOCK);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_GRAY + "高品質炭素鋼ブロック (Carbon Steel Bar)");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "工程①・②の原材料：スライド・バレル用");
        meta.setLore(lore);
        meta.setCustomModelData(8501);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack getPolygonalMandrel() {
        ItemStack item = new ItemStack(Material.END_ROD);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.LIGHT_PURPLE + "ポリゴナルライフリング芯棒 (Mandrel)");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "工程②用：冷間ハンマー鍛造用超硬合金芯棒");
        meta.setLore(lore);
        meta.setCustomModelData(8502);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack getPolymerResin() {
        ItemStack item = new ItemStack(Material.BLACK_CONCRETE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_PURPLE + "高強度ポリマー樹脂ベース");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "工程③用：極寒・酷暑対応特殊ナイロン樹脂");
        meta.setLore(lore);
        meta.setCustomModelData(8503);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack getSteelRailInsert() {
        ItemStack item = new ItemStack(Material.IRON_NUGGET);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.WHITE + "スチールフレームレール（スタンプ）");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "工程③用：インサート成形用金属レール");
        meta.setLore(lore);
        meta.setCustomModelData(8504);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack getMetalPowderMix() {
        ItemStack item = new ItemStack(Material.GUNPOWDER);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY + "MIM用金属粉末バインダー混合体");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "工程④用：金属粉末射出成形・焼結原材料");
        meta.setLore(lore);
        meta.setCustomModelData(8505);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack getProofLoadAmmo() {
        ItemStack item = new ItemStack(Material.FIREWORK_ROCKET);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.RED + "超過負荷プルーフ弾 (Proof Load Ammo)");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_RED + "工程⑥用：出荷前ストレステスト用高圧弾");
        meta.setLore(lore);
        meta.setCustomModelData(8506);
        item.setItemMeta(meta);
        return item;
    }
}
