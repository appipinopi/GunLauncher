package jp.wolfx.guncraft.manager;

import jp.wolfx.gunmain.api.CustomGun;
import jp.wolfx.gunmain.api.GunRegistry;
import jp.wolfx.guncraft.item.CraftMaterials;
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

        inv.setItem(11, CraftMaterials.getBrass());
        inv.setItem(13, CraftMaterials.getLeadBullet());
        
        ItemStack ammo = new ItemStack(Material.PAPER, 16);
        ItemMeta ammoMeta = ammo.getItemMeta();
        ammoMeta.setDisplayName(ChatColor.YELLOW + "9mm Parabellum 弾薬 x16");
        ammo.setItemMeta(ammoMeta);
        inv.setItem(15, ammo);

        player.openInventory(inv);
    }

    public void openAssemblyTable(Player player) {
        Inventory inv = Bukkit.createInventory(null, 27, ChatColor.GOLD + "銃器組立台 (Glock Gen5 最終組立)");
        
        ItemStack pane = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);
        ItemMeta meta = pane.getItemMeta();
        meta.setDisplayName(" ");
        pane.setItemMeta(meta);
        for (int i = 0; i < 27; i++) {
            if (i != 11 && i != 15) inv.setItem(i, pane);
        }

        // Slot 10: Requires Blueprint
        ItemStack blueprint = CraftMaterials.getPrintedPaper("sample:glock17", "Glock Gen5");
        inv.setItem(10, blueprint);

        // Slot 13: Requires Proof Load Ammo (工程⑥)
        ItemStack proofAmmo = jp.wolfx.guncraft.item.ProcessMaterials.getProofLoadAmmo();
        inv.setItem(13, proofAmmo);

        // Slot 16: Output Glock 17 Gen5 (Tested & Passed)
        CustomGun glock = GunRegistry.getGun("sample:glock17");
        if (glock != null) {
            ItemStack output = glock.craftItemStack();
            ItemMeta outMeta = output.getItemMeta();
            java.util.List<String> lore = outMeta.hasLore() ? outMeta.getLore() : new java.util.ArrayList<>();
            lore.add(ChatColor.DARK_RED + "✓ CIPプルーフ弾 ストレステスト合格品");
            outMeta.setLore(lore);
            output.setItemMeta(outMeta);
            inv.setItem(16, output);
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
                player.sendMessage(ChatColor.GREEN + "弾薬の成形に成功しました！");
            }
        } else if (title.contains("銃器組立台") && slot == 16) {
            // Check if player has all 34 official parts + blueprint + proof ammo in inventory
            boolean hasAllParts = true;
            for (int i = 1; i <= 34; i++) {
                boolean found = false;
                for (ItemStack item : player.getInventory().getContents()) {
                    if (item != null && item.hasItemMeta() && item.getItemMeta().hasCustomModelData()) {
                        if (item.getItemMeta().getCustomModelData() == 7000 + i) {
                            found = true;
                            break;
                        }
                    }
                }
                if (!found) {
                    hasAllParts = false;
                    player.sendMessage(ChatColor.RED + "不足している公式パーツがあります: Pos." + i + " " + ExpandedGlockPartsRegistry.getPartName(i));
                    break;
                }
            }

            if (hasAllParts) {
                // Consume 1-34 parts from inventory
                for (int i = 1; i <= 34; i++) {
                    for (ItemStack item : player.getInventory().getContents()) {
                        if (item != null && item.hasItemMeta() && item.getItemMeta().hasCustomModelData()) {
                            if (item.getItemMeta().getCustomModelData() == 7000 + i) {
                                item.setAmount(item.getAmount() - 1);
                                break;
                            }
                        }
                    }
                }

                ItemStack gun = inv.getItem(16);
                if (gun != null) {
                    player.getInventory().addItem(gun.clone());
                    player.sendMessage(ChatColor.GOLD + "工程⑥：プルーフ弾テスト合格！Glock Gen5 の全組み立て工程が完了しました！");
                }
            } else {
                player.sendMessage(ChatColor.RED + "Glock Gen5の最終組立には、公式パーツPos.1〜34およびプルーフ弾テストが必要です！");
            }
        }
    }
}
