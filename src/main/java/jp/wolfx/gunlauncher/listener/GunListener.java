package jp.wolfx.gunlauncher.listener;

import jp.wolfx.gunlauncher.api.Attachment;
import jp.wolfx.gunlauncher.api.CustomGun;
import jp.wolfx.gunlauncher.api.GunRegistry;
import jp.wolfx.gunlauncher.api.ItemRegistry;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class GunListener implements Listener {
    private final NamespacedKey gunKey;

    public GunListener(Plugin plugin) {
        this.gunKey = new NamespacedKey(plugin, "gun_id");
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if (item == null || !item.hasItemMeta()) return;

        ItemMeta meta = item.getItemMeta();
        String gunId = meta.getPersistentDataContainer().get(gunKey, PersistentDataType.STRING);
        if (gunId == null) return; // Not a gun

        CustomGun gun = GunRegistry.getGun(gunId);
        if (gun == null) return;

        event.setCancelled(true);
        Action action = event.getAction();

        // Check if player is holding an attachment to install it (Shift + Right Click on gun)
        ItemStack offhand = player.getInventory().getItemInOffHand();
        if (player.isSneaking() && offhand != null && !offhand.getType().isAir()) {
            for (Attachment part : ItemRegistry.getAttachments()) {
                if (part.matches(offhand)) {
                    installAttachment(player, item, part, offhand);
                    return;
                }
            }
        }

        if (player.isSneaking() && (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK)) {
            gun.onReload(player, item);
        } else if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
            gun.onShoot(player, item);
        }
    }

    private void installAttachment(Player player, ItemStack gunItem, Attachment part, ItemStack partItem) {
        ItemMeta meta = gunItem.getItemMeta();
        if (meta == null) return;

        NamespacedKey slotKey = new NamespacedKey(player.getServer().getPluginManager().getPlugin("GunLauncher"), "part_" + part.getSlot().toLowerCase());
        
        // Save attachment ID to gun PDC
        meta.getPersistentDataContainer().set(slotKey, PersistentDataType.STRING, part.getId());
        
        // Update Lore
        List<String> lore = meta.getLore();
        if (lore == null) lore = new ArrayList<>();
        
        // Remove existing part for this slot in lore if present
        lore.removeIf(line -> line.contains("[" + part.getSlot() + "]"));
        lore.add("§a[" + part.getSlot() + "] " + part.getName());
        meta.setLore(lore);
        gunItem.setItemMeta(meta);

        // Consume one part from offhand
        partItem.setAmount(partItem.getAmount() - 1);

        player.sendMessage("§aSuccessfully installed " + part.getName() + "!");
    }
}
