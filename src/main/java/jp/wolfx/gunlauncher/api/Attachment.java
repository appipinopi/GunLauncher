package jp.wolfx.gunlauncher.api;

import org.bukkit.inventory.ItemStack;

public interface Attachment {
    /**
     * Unique ID of the attachment (e.g., "sample:scope_x4", "sample:silencer")
     */
    String getId();

    /**
     * Slot type of the attachment (e.g., "SCOPE", "BARREL", "MAGAZINE", "STOCK")
     */
    String getSlot();

    /**
     * Display name of the attachment
     */
    String getName();

    /**
     * Damage multiplier or bonus (e.g., 1.2 for +20% damage)
     */
    default double getDamageBonus() { return 0.0; }

    /**
     * Range multiplier or bonus
     */
    default double getRangeBonus() { return 0.0; }

    /**
     * Max ammo bonus (e.g., +15 for extended mag)
     */
    default int getAmmoBonus() { return 0; }

    /**
     * Create the ItemStack representation of this attachment
     */
    ItemStack craftItemStack();

    /**
     * Check if an item matches this attachment
     */
    boolean matches(ItemStack item);
}
