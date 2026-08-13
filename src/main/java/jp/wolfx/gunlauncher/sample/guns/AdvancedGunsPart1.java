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

public class AdvancedGunsPart1 {

    public static class ScarH implements CustomGun {
        private final NamespacedKey gunKey = new NamespacedKey(GunLauncherPlugin.getInstance(), "gun_id");
        private final NamespacedKey ammoKey = new NamespacedKey(GunLauncherPlugin.getInstance(), "gun_ammo");
        private final Ammunition requiredAmmo = new CaliberAmbos.Ammo762mm();

        @Override public String getId() { return "sample:scar_h"; }
        @Override public String getName() { return "§bFN SCAR-H (7.62mm)"; }
        @Override public Ammunition getRequiredAmmo() { return requiredAmmo; }
        @Override public int getMaxAmmo() { return 20; }

        @Override
        public ItemStack craftItemStack() {
            ItemStack item = new ItemStack(Material.IRON_HORSE_ARMOR);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', getName()));
            meta.setCustomModelData(1101);
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
                player.sendMessage("§cSCAR-H 弾切れです！ (7.62mm必要)");
                return;
            }

            meta.getPersistentDataContainer().set(ammoKey, PersistentDataType.INTEGER, ammo - 1);
            gunItem.setItemMeta(meta);

            player.playSound(player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_BLAST, 2.0f, 0.7f);
            RayTraceResult result = player.getWorld().rayTraceEntities(player.getEyeLocation(), player.getEyeLocation().getDirection(), 65.0, 0.3, e -> e != player);
            if (result != null && result.getHitEntity() instanceof LivingEntity) {
                ((LivingEntity) result.getHitEntity()).damage(11.0, player);
            }
        }

        @Override
        public void onReload(Player player, ItemStack gunItem) {
            player.playSound(player.getLocation(), Sound.BLOCK_IRON_DOOR_CLOSE, 1.0f, 1.0f);
            player.sendMessage("§eSCAR-H リロード中...");
            org.bukkit.Bukkit.getScheduler().runTaskLater(GunLauncherPlugin.getInstance(), () -> {
                if (player.isOnline() && player.getInventory().getItemInMainHand().equals(gunItem)) {
                    ItemMeta meta = gunItem.getItemMeta();
                    if (meta != null) {
                        meta.getPersistentDataContainer().set(ammoKey, PersistentDataType.INTEGER, getMaxAmmo());
                        gunItem.setItemMeta(meta);
                        player.playSound(player.getLocation(), Sound.BLOCK_IRON_DOOR_OPEN, 1.0f, 1.1f);
                        player.sendMessage("§aSCAR-H リロード完了！");
                    }
                }
            }, 40L);
        }
    }

    public static class DesertEagle implements CustomGun {
        private final NamespacedKey gunKey = new NamespacedKey(GunLauncherPlugin.getInstance(), "gun_id");
        private final NamespacedKey ammoKey = new NamespacedKey(GunLauncherPlugin.getInstance(), "gun_ammo");
        private final Ammunition requiredAmmo = new CaliberAmbos.Ammo50AE();

        @Override public String getId() { return "sample:desert_eagle"; }
        @Override public String getName() { return "§6Desert Eagle (.50 AE)"; }
        @Override public Ammunition getRequiredAmmo() { return requiredAmmo; }
        @Override public int getMaxAmmo() { return 7; }

        @Override
        public ItemStack craftItemStack() {
            ItemStack item = new ItemStack(Material.IRON_HORSE_ARMOR);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', getName()));
            meta.setCustomModelData(1102);
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
                player.sendMessage("§cDeagle 弾切れです！ (.50 AE必要)");
                return;
            }

            meta.getPersistentDataContainer().set(ammoKey, PersistentDataType.INTEGER, ammo - 1);
            gunItem.setItemMeta(meta);

            player.playSound(player.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 0.8f, 1.8f);
            RayTraceResult result = player.getWorld().rayTraceEntities(player.getEyeLocation(), player.getEyeLocation().getDirection(), 35.0, 0.3, e -> e != player);
            if (result != null && result.getHitEntity() instanceof LivingEntity) {
                ((LivingEntity) result.getHitEntity()).damage(14.0, player); // ハンドガンながら超高威力
            }
        }

        @Override
        public void onReload(Player player, ItemStack gunItem) {
            player.playSound(player.getLocation(), Sound.BLOCK_IRON_TRAPDOOR_CLOSE, 1.0f, 1.2f);
            player.sendMessage("§eDesert Eagle リロード中...");
            org.bukkit.Bukkit.getScheduler().runTaskLater(GunLauncherPlugin.getInstance(), () -> {
                if (player.isOnline() && player.getInventory().getItemInMainHand().equals(gunItem)) {
                    ItemMeta meta = gunItem.getItemMeta();
                    if (meta != null) {
                        meta.getPersistentDataContainer().set(ammoKey, PersistentDataType.INTEGER, getMaxAmmo());
                        gunItem.setItemMeta(meta);
                        player.playSound(player.getLocation(), Sound.BLOCK_IRON_TRAPDOOR_OPEN, 1.0f, 1.3f);
                        player.sendMessage("§aDesert Eagle リロード完了！");
                    }
                }
            }, 30L);
        }
    }
}
