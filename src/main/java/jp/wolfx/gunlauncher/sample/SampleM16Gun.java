package jp.wolfx.gunlauncher.sample;

import jp.wolfx.gunlauncher.api.CustomGun;
import jp.wolfx.gunlauncher.GunLauncherPlugin;
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

public class SampleM16Gun implements CustomGun {
    private final NamespacedKey gunKey = new NamespacedKey(GunLauncherPlugin.getInstance(), "gun_id");
    private final NamespacedKey ammoKey = new NamespacedKey(GunLauncherPlugin.getInstance(), "gun_ammo");

    @Override public String getId() { return "sample:m16"; }
    @Override public String getName() { return "§6M16 Burst Rifle"; }
    @Override public int getMaxAmmo() { return 30; }

    @Override
    public ItemStack craftItemStack() {
        ItemStack item = new ItemStack(Material.IRON_HORSE_ARMOR);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', getName()));
            meta.setCustomModelData(1003); // M16 model
            meta.getPersistentDataContainer().set(gunKey, PersistentDataType.STRING, getId());
            meta.getPersistentDataContainer().set(ammoKey, PersistentDataType.INTEGER, getMaxAmmo());
            item.setItemMeta(meta);
        }
        return item;
    }

    @Override
    public void onShoot(Player player, ItemStack gunItem) {
        ItemMeta meta = gunItem.getItemMeta();
        if (meta == null) return;
        Integer ammo = meta.getPersistentDataContainer().get(ammoKey, PersistentDataType.INTEGER);
        if (ammo == null || ammo <= 3) {
            player.playSound(player.getLocation(), Sound.BLOCK_RESPAWN_ANCHOR_DEPLETE, 1.0f, 1.5f);
            player.sendMessage("§c弾不足です（3点バーストには最低3発必要です）");
            return;
        }

        // 3点バースト射撃
        meta.getPersistentDataContainer().set(ammoKey, PersistentDataType.INTEGER, ammo - 3);
        gunItem.setItemMeta(meta);

        for (int i = 0; i < 3; i++) {
            final int delay = i * 2;
            org.bukkit.Bukkit.getScheduler().runTaskLater(GunLauncherPlugin.getInstance(), () -> {
                if (player.isOnline()) {
                    player.playSound(player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_BLAST, 1.2f, 1.4f);
                    RayTraceResult result = player.getWorld().rayTraceEntities(player.getEyeLocation(), player.getEyeLocation().getDirection(), 70.0, 0.2, e -> e != player);
                    if (result != null && result.getHitEntity() instanceof LivingEntity) {
                        ((LivingEntity) result.getHitEntity()).damage(6.0, player);
                    }
                }
            }, delay);
        }
    }

    @Override
    public void onReload(Player player, ItemStack gunItem) {
        player.playSound(player.getLocation(), Sound.BLOCK_IRON_DOOR_CLOSE, 1.0f, 1.1f);
        player.sendMessage("§eM16 リロード中...");
        org.bukkit.Bukkit.getScheduler().runTaskLater(GunLauncherPlugin.getInstance(), () -> {
            if (player.isOnline() && player.getInventory().getItemInMainHand().equals(gunItem)) {
                ItemMeta meta = gunItem.getItemMeta();
                if (meta != null) {
                    meta.getPersistentDataContainer().set(ammoKey, PersistentDataType.INTEGER, getMaxAmmo());
                    gunItem.setItemMeta(meta);
                    player.playSound(player.getLocation(), Sound.BLOCK_IRON_DOOR_OPEN, 1.0f, 1.3f);
                    player.sendMessage("§aM16 リロード完了！");
                }
            }
        }, 35L);
    }
}
