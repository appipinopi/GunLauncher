package jp.wolfx.gunlauncher.sample.attachment;

import jp.wolfx.gunlauncher.api.Attachment;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SampleAttachments {

    public static class ScopeX4 implements Attachment {
        @Override public String getId() { return "sample:scope_x4"; }
        @Override public String getSlot() { return "SCOPE"; }
        @Override public String getName() { return "§bACOG 4x Scope"; }
        @Override public double getRangeBonus() { return 20.0; } // 射程延長
        @Override public ItemStack craftItemStack() {
            ItemStack item = new ItemStack(Material.SPYGLASS);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(getName());
            meta.setCustomModelData(3001);
            item.setItemMeta(meta);
            return item;
        }
        @Override public boolean matches(ItemStack item) {
            return item != null && item.getType() == Material.SPYGLASS && item.hasItemMeta() && item.getItemMeta().hasDisplayName() && item.getItemMeta().getDisplayName().equals(getName());
        }
    }

    public static class Silencer implements Attachment {
        @Override public String getId() { return "sample:silencer"; }
        @Override public String getSlot() { return "BARREL"; }
        @Override public String getName() { return "§8Tactical Silencer"; }
        @Override public double getDamageBonus() { return -1.0; } // 少し威力が落ちる代わりに消音
        @Override public ItemStack craftItemStack() {
            ItemStack item = new ItemStack(Material.IRON_NUGGET);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(getName());
            meta.setCustomModelData(3002);
            item.setItemMeta(meta);
            return item;
        }
        @Override public boolean matches(ItemStack item) {
            return item != null && item.getType() == Material.IRON_NUGGET && item.hasItemMeta() && item.getItemMeta().hasDisplayName() && item.getItemMeta().getDisplayName().equals(getName());
        }
    }

    public static class ExtendedMag implements Attachment {
        @Override public String getId() { return "sample:extended_mag"; }
        @Override public String getSlot() { return "MAGAZINE"; }
        @Override public String getName() { return "§dExtended Magazine (+15)"; }
        @Override public int getAmmoBonus() { return 15; }
        @Override public ItemStack craftItemStack() {
            ItemStack item = new ItemStack(Material.IRON_INGOT);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(getName());
            meta.setCustomModelData(3003);
            item.setItemMeta(meta);
            return item;
        }
        @Override public boolean matches(ItemStack item) {
            return item != null && item.getType() == Material.IRON_INGOT && item.hasItemMeta() && item.getItemMeta().hasDisplayName() && item.getItemMeta().getDisplayName().equals(getName());
        }
    }

    public static class HeavyStock implements Attachment {
        @Override public String getId() { return "sample:heavy_stock"; }
        @Override public String getSlot() { return "STOCK"; }
        @Override public String getName() { return "§eTactical Heavy Stock"; }
        @Override public double getDamageBonus() { return 1.5; }
        @Override public ItemStack craftItemStack() {
            ItemStack item = new ItemStack(Material.LEATHER);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(getName());
            meta.setCustomModelData(3004);
            item.setItemMeta(meta);
            return item;
        }
        @Override public boolean matches(ItemStack item) {
            return item != null && item.getType() == Material.LEATHER && item.hasItemMeta() && item.getItemMeta().hasDisplayName() && item.getItemMeta().getDisplayName().equals(getName());
        }
    }
}
