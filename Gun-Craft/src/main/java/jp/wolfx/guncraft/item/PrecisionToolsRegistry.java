package jp.wolfx.guncraft.item;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class PrecisionToolsRegistry {

    // ネジのサイズ規格（0.5mm 〜 2.5mm）
    public static ItemStack getScrew(double sizeMm) {
        ItemStack item = new ItemStack(Material.IRON_NUGGET);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.YELLOW + "精密ネジ (" + sizeMm + "mm)");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "規格: " + sizeMm + "mm マイクロビス");
        lore.add(ChatColor.DARK_GRAY + "※適合するドライバーが必要です");
        meta.setLore(lore);
        meta.setCustomModelData(9000 + (int)(sizeMm * 10));
        item.setItemMeta(meta);
        return item;
    }

    // 対応するドライバー（例: 0.5mm〜1.0mm対応、1.0mm〜2.0mm対応など）
    public static ItemStack getPrecisionScrewdriver(double minMm, double maxMm) {
        ItemStack item = new ItemStack(Material.SHEARS); // ドライバーの代用としてハサミテクスチャ等を使用
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.AQUA + "精密ドライバー (" + minMm + "mm ～ " + maxMm + "mm対応)");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GREEN + "対応ネジ規格: " + minMm + "mm ～ " + maxMm + "mm");
        lore.add(ChatColor.YELLOW + "ネジ山を保護しながら正確に締結します。");
        meta.setLore(lore);
        meta.setCustomModelData(9500 + (int)(minMm * 10));
        item.setItemMeta(meta);
        return item;
    }

    // 潰れたネジ（Stripped Screw）
    public static ItemStack getStrippedScrew(double sizeMm) {
        ItemStack item = new ItemStack(Material.BARRIER);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.RED + "【ネジ山潰れ】精密ネジ (" + sizeMm + "mm)");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_RED + "不適合な工具によりネジ山が潰れています！");
        lore.add(ChatColor.GRAY + "リューターでの除去またはドリル修正が必要です。");
        meta.setLore(lore);
        meta.setCustomModelData(9900 + (int)(sizeMm * 10));
        item.setItemMeta(meta);
        return item;
    }

    // 適合判定ロジック
    public static boolean canDriveScrew(ItemStack screwdriver, double screwSizeMm) {
        if (screwdriver == null || !screwdriver.hasItemMeta() || !screwdriver.getItemMeta().hasLore()) {
            return false;
        }
        // 簡単なLore解析またはCustomModelDataによる検証
        for (String line : screwdriver.getItemMeta().getLore()) {
            if (line.contains("対応ネジ規格:")) {
                try {
                    // 例: "対応ネジ規格: 0.5mm ～ 1.0mm" から数値を抽出
                    String clean = line.replaceAll("[^0-9~.]", "");
                    String[] parts = clean.split("~");
                    if (parts.length == 2) {
                        double min = Double.parseDouble(parts[0]);
                        double max = Double.parseDouble(parts[1]);
                        return screwSizeMm >= min && screwSizeMm <= max;
                    }
                } catch (Exception ignored) {}
            }
        }
        return false;
    }
}
