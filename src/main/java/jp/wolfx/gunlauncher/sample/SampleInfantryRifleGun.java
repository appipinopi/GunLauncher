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

public class SampleInfantryRifleGun implements CustomGun {
    private final NamespacedKey gunKey = new NamespacedKey(GunLauncherPlugin.getInstance(), "gun_id");
    private final NamespacedKey ammoKey = new NamespacedKey(GunLauncherPlugin.getInstance(), "gun_ammo");

    @Override public String getId() { return "sample:infantry_rifle"; }
    @Override public String getName() { return "§a歩兵銃 (Infantry Rifle)"; }
    @Override public int getMaxAmmo() { return 5; } // ボルトアクションのため少なめ

    @Override
    public ItemStack craftItemStack() {
        ItemStack item = new ItemStack(Material.IRON_HORSE_ARMOR);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', getName()));
            meta.setCustomModelData(1005); // Infantry Rifle model
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
            player.sendMessage("§c弾がありません！装填してください。");
            return;
        }

        meta.getPersistentDataContainer().set(ammoKey, PersistentDataType.INTEGER, ammo - 1);
        gunItem.setItemMeta(meta);

        player.playSound(player.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 0.5f, 2.0f); // 爆音の単発銃
        RayTraceResult result = player.getWorld().rayTraceEntities(player.getEyeLocation(), player.getEyeLocation().getDirection(), 100.0, 0.2, e -> e != player);
        if (result != null && result.getHitEntity() instanceof LivingEntity) {
            ((LivingEntity) result.getHitEntity()).damage(16.0, player); // 圧倒的高ダメージ
        }
    }

    @Override
    public void onReload(Player player, ItemStack gunItem) {
        player.playSound(player.getLocation(), Sound.ITEM_CROSSBOW_LOADING_START, 1.0f, 1.0f);
        player.sendMessage("§eボルトアクション操作中...");
        org.bukkit.Bukkit.getScheduler().runTaskLater(GunLauncherPlugin.getInstance(), () -> {
            if (player.isOnline() && player.getInventory().getItemInMainHand().equals(gunItem)) {
                ItemMeta meta = gunItem.getItemMeta();
                if (meta != null) {
                    meta.getPersistentDataContainer().set(ammoKey, PersistentDataType.INTEGER, getMaxAmmo());
                    gunItem.setItemMeta(meta);
                    player.playSound(player.getLocation(), Sound.ITEM_CROSSBOW_SHOOT, 1.0f, 1.0f);
                    player.sendMessage("§a装填完了！");
                }
            }
        }, 60L); // 3秒の装填時間
    }
}
