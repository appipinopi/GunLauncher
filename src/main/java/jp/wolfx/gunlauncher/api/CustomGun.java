package jp.wolfx.gunlauncher.api;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface CustomGun {
    /**
     * Unique ID of the gun (e.g., "sample:assault_rifle")
     */
    String getId();

    /**
     * Display name of the gun
     */
    String getName();

    /**
     * Create the ItemStack representation of this gun
     */
    ItemStack craftItemStack();

    /**
     * Called when a player shoots this gun
     */
    void onShoot(Player player, ItemStack gunItem);

    /**
     * Called when a player reloads this gun
     */
    void onReload(Player player, ItemStack gunItem);
}
