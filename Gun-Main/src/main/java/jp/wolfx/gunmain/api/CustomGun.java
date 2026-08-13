package jp.wolfx.gunmain.api;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface CustomGun {
    String getId();
    String getName();
    int getMaxAmmo();
    ItemStack craftItemStack();
    void onShoot(Player player, ItemStack gunItem);
    void onReload(Player player, ItemStack gunItem);
}
