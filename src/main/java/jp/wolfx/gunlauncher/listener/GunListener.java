package jp.wolfx.gunlauncher.listener;

import jp.wolfx.gunlauncher.api.CustomGun;
import jp.wolfx.gunlauncher.api.GunRegistry;
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
        if (gunId == null) return;

        CustomGun gun = GunRegistry.getGun(gunId);
        if (gun == null) return;

        event.setCancelled(true);
        Action action = event.getAction();

        if (player.isSneaking() && (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK)) {
            gun.onReload(player, item);
        } else if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
            gun.onShoot(player, item);
        }
    }
}
