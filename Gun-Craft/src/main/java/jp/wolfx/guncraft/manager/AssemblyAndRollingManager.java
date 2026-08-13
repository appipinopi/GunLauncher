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
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class AssemblyAndRollingManager implements Listener {
    private final Plugin plugin;

    public AssemblyAndRollingManager(Plugin plugin) {
        this.plugin = plugin;
    }

    public void openRollingMachine(Player player) {
        Inventory inv = Bukkit.createInventory(null, 27, ChatColor.DARK_GREEN + "丸める機械 (Rolling Machine)");
        
        ItemStack pane = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
        ItemMeta meta = pane.getItemMeta();
        meta.setDisplayName(" ");
        pane.setItemMeta(meta);
        for (int i = 0; i < 27; i++) {
            if (i != 11 && i != 13 && i != 15) inv.setItem(i, pane);
        }

        // Slot 11: Brass
        inv.setItem(11, CraftMaterials.getBrass());
        // Slot 13: Lead Bullet
        inv.setItem(13, CraftMaterials.getLeadBullet());
        // Slot 15: Output Ammo
        ItemStack ammo = new ItemStack(Material.PAPER, 16);
        ItemMeta ammoMeta = ammo.getItemMeta();
        ammoMeta.setDisplayName(ChatColor.YELLOW + "完成した弾薬 x16");
        ammo.setItemMeta(ammoMeta);
        inv.setItem(15, ammo);

        player.openInventory(inv);
    }

    public void openAssemblyTable(Player player) {
        Inventory inv = Bukkit.createInventory(null, 27, ChatColor.GOLD + "銃器組立台 (100部品アセンブラ)");
        
        ItemStack pane = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);
        ItemMeta meta = pane.getItemMeta();
        meta.setDisplayName(" ");
        pane.setItemMeta(meta);
        for (int i = 0; i < 27; i++) {
            if (i != 11 && i != 15) inv.setItem(i, pane);
        }

        // Slot 11: Requires 100 Printed Papers
        ItemStack req = CraftMaterials.getPrintedPaper("sample:m4a1", "M4A1 Assault Rifle");
        req.setAmount(100);
        inv.setItem(11, req);

        // Slot 15: Output Gun
        if (!GunRegistry.getGuns().isEmpty()) {
            CustomGun first = GunRegistry.getGuns().iterator().next();
            inv.setItem(15, first.craftItemStack());
        }

        player.openInventory(inv);
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        String title = event.getView().getTitle();
        if (!title.contains("丸める機械") && !title.contains("銃器組立台")) return;
        event.setCancelled(true);

        Player player = (Player) event.getWhoClicked();
        Inventory inv = event.getInventory();
        int slot = event.getRawSlot();

        if (title.contains("丸める機械") && slot == 15) {
            ItemStack output = inv.getItem(15);
            if (output != null) {
                player.getInventory().addItem(output.clone());
                player.sendMessage(ChatColor.GREEN + "弾薬の作成に成功しました！");
            }
        } else if (title.contains("銃器組立台") && slot == 15) {
            // Check if player has 100 printed papers in inventory
            boolean has100 = false;
            for (ItemStack item : player.getInventory().getContents()) {
                if (item != null && item.getType() == Material.PAPER && item.hasItemMeta() && item.getItemMeta().hasLore()) {
                    List<String> lore = item.getItemMeta().getLore();
                    if (lore != null && lore.stream().anyMatch(l -> l.contains("ターゲットID")) && item.getAmount() >= 100) {
                        has100 = true;
                        item.setAmount(item.getAmount() - 100);
                        break;
                    }
                }
            }

            if (has100) {
                ItemStack gun = inv.getItem(15);
                if (gun != null) {
                    player.getInventory().addItem(gun.clone());
                    player.sendMessage(ChatColor.GOLD + "100個の部品を組み上げ、銃の本体が完成しました！");
                }
            } else {
                player.sendMessage(ChatColor.RED + "銃の組み立てには、最低100枚のプリント済み設計図が必要です！");
            }
        }
    }
}
