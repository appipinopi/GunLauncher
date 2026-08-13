package jp.wolfx.guncraft.manager;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class MachineInteractListener implements Listener {
    private final PrintingTableManager printingManager;
    private final AssemblyAndRollingManager assemblyManager;
    private final AdvancedMachineManager advancedManager;

    public MachineInteractListener(Plugin plugin, PrintingTableManager p, AssemblyAndRollingManager a, AdvancedMachineManager adv) {
        this.printingManager = p;
        this.assemblyManager = a;
        this.advancedManager = adv;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        Block block = event.getClickedBlock();
        if (block == null) return;

        Player player = event.getPlayer();
        Material type = block.getType();

        // 各専用機械のブロックに対応するGUIを開く（コマンド不要の物理操作）
        if (type == Material.CARTOGRAPHY_TABLE) {
            event.setCancelled(true);
            printingManager.openPrintingTable(player);
            player.sendMessage(ChatColor.DARK_BLUE + "[工場] 銃器プリント台を起動しました。");
        } else if (type == Material.DISPENSER) {
            event.setCancelled(true);
            assemblyManager.openRollingMachine(player);
            player.sendMessage(ChatColor.DARK_GREEN + "[工場] 丸める機械を起動しました。");
        } else if (type == Material.SMITHING_TABLE) {
            event.setCancelled(true);
            assemblyManager.openAssemblyTable(player);
            player.sendMessage(ChatColor.GOLD + "[工場] 銃器組立台を起動しました。");
        } else if (type == Material.PISTON) {
            event.setCancelled(true);
            advancedManager.openSpringCoiler(player);
            player.sendMessage(ChatColor.LIGHT_PURPLE + "[工場] スプリング巻き機を起動しました。");
        } else if (type == Material.STONECUTTER) {
            event.setCancelled(true);
            advancedManager.openCncMachine(player);
            player.sendMessage(ChatColor.AQUA + "[工場] CNC精密加工機を起動しました。");
        } else if (type == Material.FURNACE) {
            event.setCancelled(true);
            advancedManager.openInjectionMolder(player);
            player.sendMessage(ChatColor.GOLD + "[工場] 射出成形機を起動しました。");
        } else if (type == Material.ANVIL) {
            event.setCancelled(true);
            advancedManager.openHammerForge(player);
            player.sendMessage(ChatColor.RED + "[工場] ハンマー鍛造機を起動しました。");
        }
    }
}
