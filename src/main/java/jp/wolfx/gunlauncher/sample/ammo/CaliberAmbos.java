package jp.wolfx.gunlauncher.sample.ammo;

import jp.wolfx.gunlauncher.api.Ammunition;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CaliberAmbos {

    public static class Ammo9mm implements Ammunition {
        @Override public String getId() { return "sample:ammo_9mm"; }
        @Override public String getName() { return "§79mm Parabellum"; }
        @Override public ItemStack craftItemStack(int amount) {
            ItemStack item = new ItemStack(Material.PAPER, amount);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(getName());
            meta.setCustomModelData(2001);
            item.setItemMeta(meta);
            return item;
        }
        @Override public boolean matches(ItemStack item) {
            return item != null && item.getType() == Material.PAPER && item.hasItemMeta() && item.getItemMeta().hasDisplayName() && item.getItemMeta().getDisplayName().equals(getName());
        }
    }

    public static class Ammo556mm implements Ammunition {
        @Override public String getId() { return "sample:ammo_556mm"; }
        @Override public String getName() { return "§75.56x45mm NATO"; }
        @Override public ItemStack craftItemStack(int amount) {
            ItemStack item = new ItemStack(Material.PAPER, amount);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(getName());
            meta.setCustomModelData(2002);
            item.setItemMeta(meta);
            return item;
        }
        @Override public boolean matches(ItemStack item) {
            return item != null && item.getType() == Material.PAPER && item.hasItemMeta() && item.getItemMeta().hasDisplayName() && item.getItemMeta().getDisplayName().equals(getName());
        }
    }

    public static class Ammo762mm implements Ammunition {
        @Override public String getId() { return "sample:ammo_762mm"; }
        @Override public String getName() { return "§77.62x51mm NATO"; }
        @Override public ItemStack craftItemStack(int amount) {
            ItemStack item = new ItemStack(Material.PAPER, amount);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(getName());
            meta.setCustomModelData(2003);
            item.setItemMeta(meta);
            return item;
        }
        @Override public boolean matches(ItemStack item) {
            return item != null && item.getType() == Material.PAPER && item.hasItemMeta() && item.getItemMeta().hasDisplayName() && item.getItemMeta().getDisplayName().equals(getName());
        }
    }

    public static class Ammo50AE implements Ammunition {
        @Override public String getId() { return "sample:ammo_50ae"; }
        @Override public String getName() { return "§c.50 AE (Action Express)"; }
        @Override public ItemStack craftItemStack(int amount) {
            ItemStack item = new ItemStack(Material.PAPER, amount);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(getName());
            meta.setCustomModelData(2004);
            item.setItemMeta(meta);
            return item;
        }
        @Override public boolean matches(ItemStack item) {
            return item != null && item.getType() == Material.PAPER && item.hasItemMeta() && item.getItemMeta().hasDisplayName() && item.getItemMeta().getDisplayName().equals(getName());
        }
    }

    public static class Ammo50BMG implements Ammunition {
        @Override public String getId() { return "sample:ammo_50bmg"; }
        @Override public String getName() { return "§4.50 BMG (Anti-Materiel)"; }
        @Override public ItemStack craftItemStack(int amount) {
            ItemStack item = new ItemStack(Material.PAPER, amount);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(getName());
            meta.setCustomModelData(2005);
            item.setItemMeta(meta);
            return item;
        }
        @Override public boolean matches(ItemStack item) {
            return item != null && item.getType() == Material.PAPER && item.hasItemMeta() && item.getItemMeta().hasDisplayName() && item.getItemMeta().getDisplayName().equals(getName());
        }
    }
}
