package jp.wolfx.gunlauncher.api;

import org.bukkit.inventory.ItemStack;

public interface Ammunition {
    /**
     * Unique ID of the ammo type (e.g., "sample:ammo_9mm")
     */
    String getId();

    /**
     * Display name of the ammo
     */
    String getName();

    /**
     * Create the ItemStack representation of this ammo
     */
    ItemStack craftItemStack(int amount);

    /**
     * Check if a given ItemStack matches this ammo type
     */
    boolean matches(ItemStack item);
}
