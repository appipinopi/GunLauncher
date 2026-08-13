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

public class AdvancedMachineManager implements Listener {
    private final Plugin plugin;

    public AdvancedMachineManager(Plugin plugin) {
        this.plugin = plugin;
    }

    // 1. スプリング巻き機 (Pos. 3, 6, 9, 12, 18, 20, 32)
    public void openSpringCoiler(Player player) {
        Inventory inv = Bukkit.createInventory(null, 27, ChatColor.LIGHT_PURPLE + "スプリング巻き機 (Spring Coiler)");
        setupPartSelectorGUI(inv, new int[]{3, 6, 9, 12, 18, 20, 32}, "スプリング製造");
        player.openInventory(inv);
    }

    // 2. CNC精密加工機 (スライド、トリガー、ピン等)
    public void openCncMachine(Player player) {
        Inventory inv = Bukkit.createInventory(null, 45, ChatColor.AQUA + "CNC精密加工機 (CNC Machine)");
        setupPartSelectorGUI(inv, new int[]{1, 4, 5, 8, 10, 11, 13, 14, 15, 19, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 33, 34}, "CNC金属削り出し");
        player.openInventory(inv);
    }

    // 3. 射出成形機 (フレーム、ビーバーテイル等 Pos. 16, 17)
    public void openInjectionMolder(Player player) {
        Inventory inv = Bukkit.createInventory(null, 27, ChatColor.GOLD + "射出成形機 (Injection Molder)");
        setupPartSelectorGUI(inv, new int[]{16, 17}, "ポリマーフレーム成形");
        player.openInventory(inv);
    }

    // 4. ハンマー鍛造機 (バレル Pos. 2)
    public void openHammerForge(Player player) {
        Inventory inv = Bukkit.createInventory(null, 27, ChatColor.RED + "ハンマー鍛造機 (Hammer Forge)");
        setupPartSelectorGUI(inv, new int[]{2}, "バレル鍛造");
        player.openInventory(inv);
    }

    private void setupPartSelectorGUI(Inventory inv, int[] partIds, String title) {
        ItemStack pane = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta meta = pane.getItemMeta();
        meta.setDisplayName(" ");
        pane.setItemMeta(meta);
        for (int i = 0; i < inv.getSize(); i++) {
            inv.setItem(i, pane);
        }

        int slot = 0;
        for (int id : partIds) {
            if (slot < inv.getSize() - 1) {
                inv.setItem(slot++, ExpandedGlockPartsRegistry.getPart(id, false));
            }
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        String title = event.getView().getTitle();
        if (!title.contains("スプリング巻き機") && !title.contains("CNC精密加工機") && 
            !title.contains("射出成形機") && !title.contains("ハンマー鍛造機")) return;
        event.setCancelled(true);

        ItemStack clicked = event.getCurrentItem();
        if (clicked != null && clicked.hasItemMeta() && clicked.getItemMeta().hasDisplayName()) {
            Player player = (Player) event.getWhoClicked();
            player.getInventory().addItem(clicked.clone());
            player.sendMessage(ChatColor.GREEN + "パーツを製造しました: " + clicked.getItemMeta().getDisplayName());
        }
    }
}
