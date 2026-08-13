package jp.wolfx.guncraft.manager;

import jp.wolfx.guncraft.item.ExpandedGlockPartsRegistry;
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

import java.util.ArrayList;
import java.util.List;

public class CoatingMachineManager implements Listener {
    private final Plugin plugin;

    public CoatingMachineManager(Plugin plugin) {
        this.plugin = plugin;
    }

    public void openCoatingMachine(Player player) {
        Inventory inv = Bukkit.createInventory(null, 27, ChatColor.DARK_GRAY + "nDLC表面硬化処理炉 (Coating Station)");
        
        ItemStack pane = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta meta = pane.getItemMeta();
        meta.setDisplayName(" ");
        pane.setItemMeta(meta);
        for (int i = 0; i < 27; i++) {
            if (i != 11 && i != 15) inv.setItem(i, pane);
        }

        // Slot 11: Uncoated Slide or Barrel
        ItemStack raw = ExpandedGlockPartsRegistry.getPart(1, false);
        ItemMeta rawMeta = raw.getItemMeta();
        rawMeta.setDisplayName(ChatColor.GRAY + "未処理スライド (Uncoated Slide)");
        raw.setItemMeta(rawMeta);
        inv.setItem(11, raw);

        // Slot 15: nDLC Coated Slide (Pos. 1)
        ItemStack coated = ExpandedGlockPartsRegistry.getPart(1, false);
        ItemMeta coatedMeta = coated.getItemMeta();
        coatedMeta.setDisplayName(ChatColor.DARK_AQUA + "nDLCコーティング済み Slide G17 Gen5/FS (Pos.1)");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GREEN + "表面硬度: ダイヤモンド級 (耐食性・耐傷性極限向上)");
        coatedMeta.setLore(lore);
        coated.setItemMeta(coatedMeta);
        inv.setItem(15, coated);

        player.openInventory(inv);
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (!event.getView().getTitle().contains("nDLC表面硬化処理炉")) return;
        event.setCancelled(true);

        Player player = (Player) event.getWhoClicked();
        Inventory inv = event.getInventory();
        int slot = event.getRawSlot();

        if (slot == 15) {
            ItemStack output = inv.getItem(15);
            if (output != null) {
                player.getInventory().addItem(output.clone());
                player.sendMessage(ChatColor.DARK_AQUA + "nDLC熱処理および表面硬化コーティングが完了しました！");
            }
        }
    }
}
