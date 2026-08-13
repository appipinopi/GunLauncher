package jp.wolfx.gunlauncher.sample;

import jp.wolfx.gunlauncher.api.Ammunition;
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
import org.bukkit.util.Vector;

public class SampleM4A1Gun implements CustomGun {
    private final NamespacedKey gunKey = new NamespacedKey(GunLauncherPlugin.getInstance(), "gun_id");
    private final NamespacedKey ammoKey = new NamespacedKey(GunLauncherPlugin.getInstance(), "gun_ammo");

    @Override
    public String getId() {
        return "sample:m4a1";
    }

    @Override
    public String getName() {
        return "§bM4A1 Assault Rifle";
    }

    @Override
    public int getMaxAmmo() {
        return 30;
    }

    @Override
    public ItemStack craftItemStack() {
        // リソースパックでモデルを変更しやすいようにアイアンホースアーマーや各種アイテムを使用
        ItemStack item = new ItemStack(Material.IRON_HORSE_ARMOR);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', getName()));
            
            // ★ リソースパック連動の要: CustomModelData
            // リソースパック側で assets/minecraft/models/item/iron_horse_armor.json に
            // overrides で custom_model_data: 1001 の時のテクスチャを指定します。
            meta.setCustomModelData(1001);
            
            meta.getPersistentDataContainer().set(gunKey, PersistentDataType.STRING, getId());
            meta.getPersistentDataContainer().set(ammoKey, PersistentDataType.INTEGER, getMaxAmmo());
            
            item.setItemMeta(meta);
        }
        return item;
    }

    @Override
    public void onShoot(Player player, ItemStack gunItem) {
        // 残弾数の確認と消費
        ItemMeta meta = gunItem.getItemMeta();
        if (meta == null) return;
        
        Integer ammo = meta.getPersistentDataContainer().get(ammoKey, PersistentDataType.INTEGER);
        if (ammo == null || ammo <= 0) {
            player.playSound(player.getLocation(), Sound.BLOCK_RESPAWN_ANCHOR_DEPLETE, 1.0f, 1.5f);
            player.sendMessage("§c弾切れです！リロードしてください (スニーク + 右クリック)");
            return;
        }

        // 弾を1発消費
        meta.getPersistentDataContainer().set(ammoKey, PersistentDataType.INTEGER, ammo - 1);
        gunItem.setItemMeta(meta);

        // 発射音とパーティクル
        player.playSound(player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_BLAST, 1.5f, 1.0f);

        // レイキャストによる弾道判定
        var eyeLoc = player.getEyeLocation();
        Vector dir = eyeLoc.getDirection();
        double range = 60.0;
        double damage = 7.0;

        RayTraceResult result = player.getWorld().rayTraceEntities(eyeLoc, dir, range, 0.3, e -> e != player);
        if (result != null && result.getHitEntity() instanceof LivingEntity) {
            LivingEntity target = (LivingEntity) result.getHitEntity();
            target.damage(damage, player);
        }

        player.spigot().sendMessage(net.md_5.bungee.api.ChatMessageType.ACTION_BAR, 
            net.md_5.bungee.api.chat.TextComponent.fromLegacy("§eAmmo: " + (ammo - 1) + " / " + getMaxAmmo()));
    }

    @Override
    public void onReload(Player player, ItemStack gunItem) {
        ItemMeta meta = gunItem.getItemMeta();
        if (meta == null) return;

        player.playSound(player.getLocation(), Sound.BLOCK_IRON_DOOR_CLOSE, 1.0f, 1.0f);
        player.sendMessage("§eリロード中...");

        // 2秒後に弾を補充
        org.bukkit.Bukkit.getScheduler().runTaskLater(GunLauncherPlugin.getInstance(), () -> {
            if (player.isOnline() && player.getInventory().getItemInMainHand().equals(gunItem)) {
                ItemMeta currentMeta = gunItem.getItemMeta();
                if (currentMeta != null) {
                    currentMeta.getPersistentDataContainer().set(ammoKey, PersistentDataType.INTEGER, getMaxAmmo());
                    gunItem.setItemMeta(currentMeta);
                    player.playSound(player.getLocation(), Sound.BLOCK_IRON_DOOR_OPEN, 1.0f, 1.2f);
                    player.sendMessage("§aリロード完了！");
                }
            }
        }, 40L);
    }
}
