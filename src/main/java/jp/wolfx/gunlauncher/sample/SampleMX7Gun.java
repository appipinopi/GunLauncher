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

public class SampleMX7Gun implements CustomGun {
    private final NamespacedKey gunKey = new NamespacedKey(GunLauncherPlugin.getInstance(), "gun_id");
    private final NamespacedKey ammoKey = new NamespacedKey(GunLauncherPlugin.getInstance(), "gun_ammo");

    @Override public String getId() { return "sample:mx7"; }
    @Override public String getName() { return "§9MX7 Submachine Gun"; }
    @Override public int getMaxAmmo() { return 50; } // 大容量マガジン

    @Override
    public ItemStack craftItemStack() {
        ItemStack item = new ItemStack(Material.IRON_HORSE_ARMOR);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', getName()));
            meta.setCustomModelData(1004); // MX7 model
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
        if (ammo == null || ammo <= 0) {
            player.playSound(player.getLocation(), Sound.BLOCK_RESPAWN_ANCHOR_DEPLETE, 1.0f, 1.5f);
            player.sendMessage("§c弾切れです！");
            return;
        }

        meta.getPersistentDataContainer().set(ammoKey, PersistentDataType.INTEGER, ammo - 1);
        gunItem.setItemMeta(meta);

        player.playSound(player.getLocation(), Sound.ENTITY_ARROW_SHOOT, 1.8f, 1.8f); // 高音のSMG音
        RayTraceResult result = player.getWorld().rayTraceEntities(player.getEyeLocation(), player.getEyeLocation().getDirection(), 40.0, 0.4, e -> e != player);
        if (result != null && result.getHitEntity() instanceof LivingEntity) {
            ((LivingEntity) result.getHitEntity()).damage(4.5, player); // 低ダメージ・高速連射
        }
    }

    @Override
    public void onReload(Player player, ItemStack gunItem) {
        player.playSound(player.getLocation(), Sound.BLOCK_IRON_DOOR_CLOSE, 1.0f, 1.2f);
        player.sendMessage("§eMX7 リロード中...");
        org.bukkit.Bukkit.getScheduler().runTaskLater(GunLauncherPlugin.getInstance(), () -> {
            if (player.isOnline() && player.getInventory().getItemInMainHand().equals(gunItem)) {
                ItemMeta meta = gunItem.getItemMeta();
                if (meta != null) {
                    meta.getPersistentDataContainer().set(ammoKey, PersistentDataType.INTEGER, getMaxAmmo());
                    gunItem.setItemMeta(meta);
                    player.playSound(player.getLocation(), Sound.BLOCK_IRON_DOOR_OPEN, 1.0f, 1.4f);
                    player.sendMessage("§aMX7 リロード完了！");
                }
            }
        }, 30L);
    }
}
