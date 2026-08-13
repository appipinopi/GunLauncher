package jp.wolfx.guncraft.manager;

import jp.wolfx.guncraft.item.PrecisionToolsRegistry;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class ScrewDriveManager implements Listener {
    private final Plugin plugin;

    public ScrewDriveManager(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        Player player = event.getPlayer();
        ItemStack mainHand = player.getInventory().getItemInMainHand();

        // プレイヤーがネジを持っていて、オフハンド（またはサブ）にドライバーを持っているか確認
        // あるいはその逆で、ドライバーでネジをクリックした場合の判定
        if (mainHand.getType() == Material.IRON_NUGGET && mainHand.hasItemMeta() && mainHand.getItemMeta().hasDisplayName()) {
            String name = mainHand.getItemMeta().getDisplayName();
            if (name.contains("精密ネジ")) {
                // ネジのサイズ（mm）を抽出する例（例: "(1.2mm)"）
                try {
                    String sizeStr = name.substring(name.indexOf("(") + 1, name.indexOf("mm"));
                    double screwSize = Double.parseDouble(sizeStr);

                    ItemStack offHand = player.getInventory().getItemInOffHand();
                    if (PrecisionToolsRegistry.canDriveScrew(offHand, screwSize)) {
                        player.sendMessage(ChatColor.GREEN + "適合する精密ドライバーで " + screwSize + "mm のネジを正常に締め付けました！");
                    } else {
                        player.sendMessage(ChatColor.RED + "警告: 不適合な工具またはドライバー未装備のため、ネジ山が潰れました（Stripped Screw）！");
                        // 潰れたネジに置き換え
                        player.getInventory().setItemInMainHand(PrecisionToolsRegistry.getStrippedScrew(screwSize));
                    }
                } catch (Exception ignored) {}
            }
        }
    }
}
