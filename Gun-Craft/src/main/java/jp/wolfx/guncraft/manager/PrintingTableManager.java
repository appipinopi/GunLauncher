package jp.wolfx.guncraft.manager;

import jp.wolfx.gunmain.api.CustomGun;
import jp.wolfx.gunmain.api.GunRegistry;
import jp.wolfx.guncraft.item.CraftMaterials;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class PrintingTableManager implements Listener {
    private final Plugin plugin;

    public PrintingTableManager(Plugin plugin) {
        this.plugin = plugin;
    }

    public void openPrintingTable(Player player) {
        Inventory inv = Bukkit.createInventory(null, 27, ChatColor.DARK_BLUE + "銃器プリント台 (Printing Table)");
        
        // Fill background
        ItemStack pane = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta meta = pane.getItemMeta();
        meta.setDisplayName(" ");
        pane.setItemMeta(meta);
        for (int i = 0; i < 27; i++) {
            if (i != 11 && i != 13 && i != 15) {
                inv.setItem(i, pane);
            }
        }

        // Slot 11: Paper (1 needed)
        inv.setItem(11, new ItemStack(Material.PAPER));
        // Slot 13: Ink Sac (4 needed)
        ItemStack ink = new ItemStack(Material.INK_SAC, 4);
        ItemMeta inkMeta = ink.getItemMeta();
        inkMeta.setDisplayName(ChatColor.DARK_GRAY + "イカ墨 x4");
        ink.setItemMeta(inkMeta);
        inv.setItem(13, ink);

        // Slot 15: Output placeholder (First registered gun by default)
        if (!GunRegistry.getGuns().isEmpty()) {
            CustomGun first = GunRegistry.getGuns().iterator().next();
            inv.setItem(15, CraftMaterials.getPrintedPaper(first.getId(), first.getName()));
        }

        player.openInventory(inv);
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (!event.getView().getTitle().contains("銃器プリント台")) return;
        event.setCancelled(true);

        Player player = (Player) event.getWhoClicked();
        Inventory inv = event.getInventory();
        int slot = event.getRawSlot();

        // If clicking output slot (15)
        if (slot == 15) {
            ItemStack paper = inv.getItem(11);
            ItemStack ink = inv.getItem(13);

            if (paper != null && paper.getType() == Material.PAPER && paper.getAmount() >= 1 &&
                ink != null && ink.getType() == Material.INK_SAC && ink.getAmount() >= 4) {

                // Consume materials
                paper.setAmount(paper.getAmount() - 1);
                ink.setAmount(ink.getAmount() - 4);

                ItemStack output = inv.getItem(15);
                if (output != null) {
                    player.getInventory().addItem(output.clone());
                    player.sendMessage(ChatColor.GREEN + "設計図（プリント済みの紙）の作成に成功しました！");
                }
            } else {
                player.sendMessage(ChatColor.RED + "素材が不足しています (紙1枚 + イカ墨4個が必要です)");
            }
        }
    }
}
