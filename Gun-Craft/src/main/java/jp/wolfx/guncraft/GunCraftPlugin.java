package jp.wolfx.guncraft;

import jp.wolfx.gunmain.api.CustomGun;
import jp.wolfx.gunmain.api.GunRegistry;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class GunCraftPlugin extends JavaPlugin {
    private static GunCraftPlugin instance;
    private NamespacedKey blueprintKey;

    @Override
    public void onEnable() {
        instance = this;
        blueprintKey = new NamespacedKey(this, "blueprint_gun_id");
        getLogger().info("=== Gun-Craft (Manufacturing System) Enabled ===");

        getCommand("guncraft").setExecutor(new CommandExecutor() {
            @Override
            public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
                if (!(sender instanceof Player)) return true;
                Player player = (Player) sender;

                if (args.length < 2) {
                    player.sendMessage("§cUsage: /guncraft blueprint <gunId>");
                    return true;
                }

                String sub = args[0].toLowerCase();
                if (sub.equals("blueprint")) {
                    String gunId = args[1];
                    CustomGun gun = GunRegistry.getGun(gunId);
                    if (gun == null) {
                        player.sendMessage("§cGun ID '" + gunId + "' not found.");
                        return true;
                    }

                    ItemStack blueprint = new ItemStack(Material.PAPER);
                    ItemMeta meta = blueprint.getItemMeta();
                    meta.setDisplayName(ChatColor.GOLD + "Blueprint: " + gun.getName());
                    List<String> lore = new ArrayList<>();
                    lore.add("§7Right-click to assemble this gun!");
                    lore.add("§8Target ID: " + gun.getId());
                    meta.setLore(lore);
                    meta.getPersistentDataContainer().set(blueprintKey, PersistentDataType.STRING, gun.getId());
                    blueprint.setItemMeta(meta);

                    player.getInventory().addItem(blueprint);
                    player.sendMessage("§aCreated blueprint for " + gun.getName() + "!");
                }
                return true;
            }
        });
    }

    @Override
    public void onDisable() {
        getLogger().info("Gun-Craft Disabled.");
    }

    public static GunCraftPlugin getInstance() {
        return instance;
    }
}
