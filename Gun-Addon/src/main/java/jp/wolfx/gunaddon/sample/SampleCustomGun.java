package jp.wolfx.gunaddon.sample;

import jp.wolfx.gunmain.api.CustomGun;
import jp.wolfx.gunmain.GunMainPlugin;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.RayTraceResult;

public class SampleCustomGun implements CustomGun {
    private final NamespacedKey gunKey = new NamespacedKey(GunMainPlugin.getInstance(), "gun_id");
    private final NamespacedKey ammoKey = new NamespacedKey(GunMainPlugin.getInstance(), "gun_ammo");

    @Override public String getId() { return "addon:custom_rifle"; }
    @Override public String getName() { return "§dCustom Personal Rifle"; }
    @Override public int getMaxAmmo() { return 35; }

    @Override
    public ItemStack craftItemStack() {
        ItemStack item = new ItemStack(Material.IRON_HORSE_ARMOR);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', getName()));
        meta.setCustomModelData(1201);
        meta.getPersistentDataContainer().set(gunKey, PersistentDataType.STRING, getId());
        meta.getPersistentDataContainer().set(ammoKey, PersistentDataType.INTEGER, getMaxAmmo());
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public void onShoot(Player player, ItemStack gunItem) {
        ItemMeta meta = gunItem.getItemMeta();
        if (meta == null) return;
        Integer ammo = meta.getPersistentDataContainer().get(ammoKey, PersistentDataType.INTEGER);
        if (ammo == null || ammo <= 0) {
            player.playSound(player.getLocation(), Sound.BLOCK_RESPAWN_ANCHOR_DEPLETE, 1.0f, 1.5f);
            player.sendMessage("§cPersonal Rifle Out of Ammo!");
            return;
        }

        meta.getPersistentDataContainer().set(ammoKey, PersistentDataType.INTEGER, ammo - 1);
        gunItem.setItemMeta(meta);

        player.playSound(player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_BLAST, 1.5f, 1.2f);
        RayTraceResult result = player.getWorld().rayTraceEntities(player.getEyeLocation(), player.getEyeLocation().getDirection(), 70.0, 0.3, e -> e != player);
        if (result != null && result.getHitEntity() instanceof LivingEntity) {
            ((LivingEntity) result.getHitEntity()).damage(8.5, player);
        }
    }

    @Override
    public void onReload(Player player, ItemStack gunItem) {
        player.playSound(player.getLocation(), Sound.BLOCK_IRON_DOOR_CLOSE, 1.0f, 1.0f);
        player.sendMessage("§eReloading Personal Rifle...");
        org.bukkit.Bukkit.getScheduler().runTaskLater(GunMainPlugin.getInstance(), () -> {
            if (player.isOnline() && player.getInventory().getItemInMainHand().equals(gunItem)) {
                ItemMeta meta = gunItem.getItemMeta();
                if (meta != null) {
                    meta.getPersistentDataContainer().set(ammoKey, PersistentDataType.INTEGER, getMaxAmmo());
                    gunItem.setItemMeta(meta);
                    player.playSound(player.getLocation(), Sound.BLOCK_IRON_DOOR_OPEN, 1.0f, 1.4f);
                    player.sendMessage("§aReload Complete!");
                }
            }
        }, 35L);
    }
}
