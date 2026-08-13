package jp.wolfx.guncraft.manager;

import jp.wolfx.guncraft.item.AdvancedCraftMaterials;
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

    public void openSpringCoiler(Player player) {
        Inventory inv = Bukkit.createInventory(null, 27, ChatColor.LIGHT_PURPLE + "スプリング巻き機 (Spring Coiler)");
        setupMachineGUI(inv, Material.IRON_BARS, "スプリング部品");
        player.openInventory(inv);
    }

    public void openCncMachine(Player player) {
        Inventory inv = Bukkit.createInventory(null, 27, ChatColor.AQUA + "CNC精密加工機 (CNC Machine)");
        setupMachineGUI(inv, Material.IRON_INGOT, "金属削り出し部品 (スライド/トリガー)");
        player.openInventory(inv);
    }

    public void openInjectionMolder(Player player) {
        Inventory inv = Bukkit.createInventory(null, 27, ChatColor.GOLD + "射出成形機 (Injection Molder)");
        setupMachineGUI(inv, Material.BLACK_CONCRETE, "ポリマーフレーム");
        player.openInventory(inv);
    }

    public void openHammerForge(Player player) {
        Inventory inv = Bukkit.createInventory(null, 27, ChatColor.RED + "ハンマー鍛造機 (Hammer Forge)");
        setupMachineGUI(inv, Material.NETHERITE_INGOT, "高精度バレル (銃身)");
        player.openInventory(inv);
    }

    private void setupMachineGUI(Inventory inv, Material inputMat, String categoryName) {
        ItemStack pane = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta meta = pane.getItemMeta();
        meta.setDisplayName(" ");
        pane.setItemMeta(meta);
        for (int i = 0; i < 27; i++) {
            if (i != 11 && i != 15) inv.setItem(i, pane);
        }

        // Slot 11: Raw Material
        inv.setItem(11, new ItemStack(inputMat, 1));

        // Slot 15: Output Part (e.g. Normal or Damaged check)
        inv.setItem(15, AdvancedCraftMaterials.getPartCategory(categoryName, 1, false));
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        String title = event.getView().getTitle();
        if (!title.contains("スプリング巻き機") && !title.contains("CNC精密加工機") && 
            !title.contains("射出成形機") && !title.contains("ハンマー鍛造機")) return;
        event.setCancelled(true);

        Player player = (Player) event.getWhoClicked();
        Inventory inv = event.getInventory();
        int slot = event.getRawSlot();

        if (slot == 15) {
            ItemStack output = inv.getItem(15);
            if (output != null) {
                player.getInventory().addItem(output.clone());
                player.sendMessage(ChatColor.GREEN + "専用の精密部品の製造に成功しました！");
            }
        }
    }
}
