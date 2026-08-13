package jp.wolfx.gunlauncher.api;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface CustomGun {
    String getId();
    String getName();
    
    // Ammo requirements
    default Ammunition getRequiredAmmo() { return null; }
    default int getMaxAmmo() { return 30; }

    ItemStack craftItemStack();

    void onShoot(Player player, ItemStack gunItem);
    void onReload(Player player, ItemStack gunItem);
}
