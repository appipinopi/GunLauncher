package jp.wolfx.guncraft;

import jp.wolfx.guncraft.manager.AdvancedMachineManager;
import jp.wolfx.guncraft.manager.AssemblyAndRollingManager;
import jp.wolfx.guncraft.manager.PrintingTableManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class GunCraftPlugin extends JavaPlugin {
    private static GunCraftPlugin instance;
    private PrintingTableManager printingManager;
    private AssemblyAndRollingManager assemblyManager;

    @Override
    public void onEnable() {
        instance = this;
        printingManager = new PrintingTableManager(this);
        assemblyManager = new AssemblyAndRollingManager(this);
        AdvancedMachineManager advancedManager = new AdvancedMachineManager(this);
        jp.wolfx.guncraft.manager.ScrewDriveManager screwManager = new jp.wolfx.guncraft.manager.ScrewDriveManager(this);

        jp.wolfx.guncraft.manager.MachineInteractListener interactListener = new jp.wolfx.guncraft.manager.MachineInteractListener(this, printingManager, assemblyManager, advancedManager);

        getServer().getPluginManager().registerEvents(printingManager, this);
        getServer().getPluginManager().registerEvents(assemblyManager, this);
        getServer().getPluginManager().registerEvents(advancedManager, this);
        getServer().getPluginManager().registerEvents(screwManager, this);
        getServer().getPluginManager().registerEvents(interactListener, this);

        // Register machine crafting recipes
        jp.wolfx.guncraft.recipe.MachineRecipes.registerRecipes(this);

        getLogger().info("=== Gun-Craft (Advanced Manufacturing & Workstations) Enabled ===");

        getCommand("guncraft").setExecutor(new CommandExecutor() {
            @Override
            public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
                if (!(sender instanceof Player)) return true;
                Player player = (Player) sender;

                if (args.length == 0) {
                    player.sendMessage(ChatColor.GOLD + "--- Gun-Craft Commands ---");
                    player.sendMessage(ChatColor.YELLOW + "/guncraft print " + ChatColor.GRAY + "- Open Printing Table");
                    player.sendMessage(ChatColor.YELLOW + "/guncraft roll " + ChatColor.GRAY + "- Open Rolling Machine");
                    player.sendMessage(ChatColor.YELLOW + "/guncraft assemble " + ChatColor.GRAY + "- Open Gun Assembly Table (100 parts)");
                    return true;
                }

                String sub = args[0].toLowerCase();
                if (sub.equals("print")) {
                    printingManager.openPrintingTable(player);
                } else if (sub.equals("roll")) {
                    assemblyManager.openRollingMachine(player);
                } else if (sub.equals("assemble")) {
                    assemblyManager.openAssemblyTable(player);
                } else if (sub.equals("spring")) {
                    advancedManager.openSpringCoiler(player);
                } else if (sub.equals("cnc")) {
                    advancedManager.openCncMachine(player);
                } else if (sub.equals("mold")) {
                    advancedManager.openInjectionMolder(player);
                } else if (sub.equals("forge")) {
                    advancedManager.openHammerForge(player);
                } else if (sub.equals("part")) {
                    if (args.length < 2) {
                        player.sendMessage("§cUsage: /guncraft part <1-34>");
                        return true;
                    }
                    try {
                        int partId = Integer.parseInt(args[1]);
                        if (partId < 1 || partId > 34) {
                            player.sendMessage("§cPart ID must be between 1 and 34 (Glock Gen5 Official Parts).");
                            return true;
                        }
                        ItemStack part = jp.wolfx.guncraft.item.ExpandedGlockPartsRegistry.getPart(partId, false);
                        player.getInventory().addItem(part);
                        player.sendMessage("§aAdded " + part.getItemMeta().getDisplayName() + " (CMD: " + part.getItemMeta().getCustomModelData() + ")");
                    } catch (NumberFormatException e) {
                        player.sendMessage("§cInvalid part ID number.");
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "Unknown subcommand.");
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
