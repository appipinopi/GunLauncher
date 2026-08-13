package jp.wolfx.gunlauncher.sample.guns;

import jp.wolfx.gunlauncher.api.Ammunition;
import jp.wolfx.gunlauncher.api.CustomGun;
import jp.wolfx.gunlauncher.GunLauncherPlugin;
import jp.wolfx.gunlauncher.sample.ammo.CaliberAmbos;
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

public class AdvancedGunsPart2 {

    public static class AWP implements CustomGun {
        private final NamespacedKey gunKey = new NamespacedKey(GunLauncherPlugin.getInstance(), "gun_id");
        private final NamespacedKey ammoKey = new NamespacedKey(GunLauncherPlugin.getInstance(), "gun_ammo");
        private final Ammunition requiredAmmo = new CaliberAmbos.Ammo762mm();

        @Override public String getId() { return "sample:awp"; }
        @Override public String getName() { return "§5Accuracy International AWP"; }
        @Override public Ammunition getRequiredAmmo() { return requiredAmmo; }
        @Override public int getMaxAmmo() { return 10; }

        @Override
        public ItemStack craftItemStack() {
            ItemStack item = new ItemStack(Material.IRON_HORSE_ARMOR);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', getName()));
            meta.setCustomModelData(1103);
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
                player.sendMessage("§cAWP 弾切れです！");
                return;
            }

            meta.getPersistentDataContainer().set(ammoKey, PersistentDataType.INTEGER, ammo - 1);
            gunItem.setItemMeta(meta);

            player.playSound(player.getLocation(), Sound.ENTITY_WARDEN_DEATH, 1.5f, 0.5f);
            RayTraceResult result = player.getWorld().rayTraceEntities(player.getEyeLocation(), player.getEyeLocation().getDirection(), 120.0, 0.15, e -> e != player);
            if (result != null && result.getHitEntity() instanceof LivingEntity) {
                ((LivingEntity) result.getHitEntity()).damage(25.0, player); // 即死級ダメージ
            }
        }

        @Override
        public void onReload(Player player, ItemStack gunItem) {
            player.playSound(player.getLocation(), Sound.ITEM_CROSSBOW_LOADING_START, 1.0f, 0.8f);
            player.sendMessage("§eAWP ボルトアクション装填中...");
            org.bukkit.Bukkit.getScheduler().runTaskLater(GunLauncherPlugin.getInstance(), () -> {
                if (player.isOnline() && player.getInventory().getItemInMainHand().equals(gunItem)) {
                    ItemMeta meta = gunItem.getItemMeta();
                    if (meta != null) {
                        meta.getPersistentDataContainer().set(ammoKey, PersistentDataType.INTEGER, getMaxAmmo());
                        gunItem.setItemMeta(meta);
                        player.playSound(player.getLocation(), Sound.ITEM_CROSSBOW_SHOOT, 1.0f, 1.0f);
                        player.sendMessage("§aAWP 準備完了！");
                    }
                }
            }, 50L);
        }
    }

    public static class BarrettM82 implements CustomGun {
        private final NamespacedKey gunKey = new NamespacedKey(GunLauncherPlugin.getInstance(), "gun_id");
        private final NamespacedKey ammoKey = new NamespacedKey(GunLauncherPlugin.getInstance(), "gun_ammo");
        private final Ammunition requiredAmmo = new CaliberAmbos.Ammo50BMG();

        @Override public String getId() { return "sample:barrett_m82"; }
        @Override public String getName() { return "§4Barrett M82 (.50 BMG)"; }
        @Override public Ammunition getRequiredAmmo() { return requiredAmmo; }
        @Override public int getMaxAmmo() { return 10; }

        @Override
        public ItemStack craftItemStack() {
            ItemStack item = new ItemStack(Material.IRON_HORSE_ARMOR);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', getName()));
            meta.setCustomModelData(1104);
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
                player.sendMessage("§cBarrett 弾切れです！ (.50 BMG必要)");
                return;
            }

            meta.getPersistentDataContainer().set(ammoKey, PersistentDataType.INTEGER, ammo - 1);
            gunItem.setItemMeta(meta);

            player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 2.0f, 0.5f);
            RayTraceResult result = player.getWorld().rayTraceEntities(player.getEyeLocation(), player.getEyeLocation().getDirection(), 150.0, 0.2, e -> e != player);
            if (result != null && result.getHitEntity() instanceof LivingEntity) {
                ((LivingEntity) result.getHitEntity()).damage(35.0, player); // 圧倒的対物ダメージ
            }
        }

        @Override
        public void onReload(Player player, ItemStack gunItem) {
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_PLACE, 1.0f, 1.0f);
            player.sendMessage("§eBarrett M82 マガジン交換中...");
            org.bukkit.Bukkit.getScheduler().runTaskLater(GunLauncherPlugin.getInstance(), () -> {
                if (player.isOnline() && player.getInventory().getItemInMainHand().equals(gunItem)) {
                    ItemMeta meta = gunItem.getItemMeta();
                    if (meta != null) {
                        meta.getPersistentDataContainer().set(ammoKey, PersistentDataType.INTEGER, getMaxAmmo());
                        gunItem.setItemMeta(meta);
                        player.playSound(player.getLocation(), Sound.BLOCK_IRON_DOOR_OPEN, 1.0f, 0.8f);
                        player.sendMessage("§aBarrett 装填完了！");
                    }
                }
            }, 60L);
        }
    }
}
