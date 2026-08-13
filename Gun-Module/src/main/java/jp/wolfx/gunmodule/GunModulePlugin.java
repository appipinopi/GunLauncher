package jp.wolfx.gunmodule;

import jp.wolfx.gunmodule.api.Attachment;
import jp.wolfx.gunmodule.api.ItemRegistry;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class GunModulePlugin extends JavaPlugin {
    private static GunModulePlugin instance;

    @Override
    public void onEnable() {
        instance = this;
        getLogger().info("=== Gun-Module (Attachments) Enabled ===");

        // Register default modules/attachments
        ItemRegistry.registerAttachment(new Attachment() {
            @Override public String getId() { return "module:scope_4x"; }
            @Override public String getSlot() { return "SCOPE"; }
            @Override public String getName() { return "§bACOG 4x Scope"; }
            @Override public double getRangeBonus() { return 25.0; }
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
        });

        getLogger().info("Registered attachments count: " + ItemRegistry.getAttachments().size());
    }

    @Override
    public void onDisable() {
        getLogger().info("Gun-Module Disabled.");
    }

    public static GunModulePlugin getInstance() {
        return instance;
    }
}
