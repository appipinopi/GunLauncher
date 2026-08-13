package jp.wolfx.gunmodule.api;

import org.bukkit.inventory.ItemStack;

public interface Attachment {
    String getId();
    String getSlot();
    String getName();
    default double getDamageBonus() { return 0.0; }
    default double getRangeBonus() { return 0.0; }
    default int getAmmoBonus() { return 0; }
    ItemStack craftItemStack();
    boolean matches(ItemStack item);
}
