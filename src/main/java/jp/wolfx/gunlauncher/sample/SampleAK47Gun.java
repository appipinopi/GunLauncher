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

public class SampleAK47Gun implements CustomGun {
    private final NamespacedKey gunKey = new NamespacedKey(GunLauncherPlugin.getInstance(), "gun_id");
    private final NamespacedKey ammoKey = new NamespacedKey(GunLauncherPlugin.getInstance(), "gun_ammo");

    @Override public String getId() { return "sample:ak47"; }
    @Override public String getName() { return "§cAK-47 Assault Rifle"; }
    @Override public int getMaxAmmo() { return 30; }

    @Override
    public ItemStack craftItemStack() {
        ItemStack item = new ItemStack(Material.IRON_HORSE_ARMOR);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', getName()));
            meta.setCustomModelData(1002); // AK-47 model
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
            player.sendMessage("§c弾切れです！リロードしてください。");
            return;
        }

        meta.getPersistentDataContainer().set(ammoKey, PersistentDataType.INTEGER, ammo - 1);
        gunItem.setItemMeta(meta);

        player.playSound(player.getLocation(), Sound.ENTITY_WARDEN_ANGRY, 1.0f, 0.8f); // 重厚な銃声
        RayTraceResult result = player.getWorld().rayTraceEntities(player.getEyeLocation(), player.getEyeLocation().getDirection(), 50.0, 0.4, e -> e != player);
        if (result != null && result.getHitEntity() instanceof LivingEntity) {
            ((LivingEntity) result.getHitEntity()).damage(9.0, player); // 高いダメージ
        }
    }

    @Override
    public void onReload(Player player, ItemStack gunItem) {
        player.playSound(player.getLocation(), Sound.BLOCK_IRON_DOOR_CLOSE, 1.0f, 0.9f);
        player.sendMessage("§eAK-47 リロード中...");
        org.bukkit.Bukkit.getScheduler().runTaskLater(GunLauncherPlugin.getInstance(), () -> {
            if (player.isOnline() && player.getInventory().getItemInMainHand().equals(gunItem)) {
                ItemMeta meta = gunItem.getItemMeta();
                if (meta != null) {
                    meta.getPersistentDataContainer().set(ammoKey, PersistentDataType.INTEGER, getMaxAmmo());
                    gunItem.setItemMeta(meta);
                    player.playSound(player.getLocation(), Sound.BLOCK_IRON_DOOR_OPEN, 1.0f, 1.0f);
                    player.sendMessage("§aAK-47 リロード完了！");
                }
            }
        }, 45L);
    }
}
