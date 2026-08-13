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

        getServer().getPluginManager().registerEvents(printingManager, this);
        getServer().getPluginManager().registerEvents(assemblyManager, this);
        getServer().getPluginManager().registerEvents(advancedManager, this);
        getServer().getPluginManager().registerEvents(screwManager, this);

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
                        player.sendMessage("§cUsage: /guncraft part <1-150>");
                        return true;
                    }
                    try {
                        int partId = Integer.parseInt(args[1]);
                        if (partId < 1 || partId > 150) {
                            player.sendMessage("§cPart ID must be between 1 and 150.");
                            return true;
                        }
                        ItemStack part = jp.wolfx.guncraft.item.ExpandedGlockPartsRegistry.getPart(partId, false);
                        player.getInventory().addItem(part);
                        player.sendMessage("§aAdded " + part.getItemMeta().getDisplayName() + " (CMD: " + part.getItemMeta().getCustomModelData() + ")");
                    } catch (NumberFormatException e) {
                        player.sendMessage("§cInvalid part ID number.");
                    }
                } else if (sub.equals("screw")) {
                    if (args.length < 2) {
                        player.sendMessage("§cUsage: /guncraft screw <sizeMm (e.g. 1.2)>");
                        return true;
                    }
                    try {
                        double size = Double.parseDouble(args[1]);
                        ItemStack screw = jp.wolfx.guncraft.item.PrecisionToolsRegistry.getScrew(size);
                        player.getInventory().addItem(screw);
                        player.sendMessage("§aAdded " + screw.getItemMeta().getDisplayName());
                    } catch (NumberFormatException e) {
                        player.sendMessage("§cInvalid size number.");
                    }
                } else if (sub.equals("driver")) {
                    if (args.length < 3) {
                        player.sendMessage("§cUsage: /guncraft driver <minMm> <maxMm>");
                        return true;
                    }
                    try {
                        double min = Double.parseDouble(args[1]);
                        double max = Double.parseDouble(args[2]);
                        ItemStack driver = jp.wolfx.guncraft.item.PrecisionToolsRegistry.getPrecisionScrewdriver(min, max);
                        player.getInventory().addItem(driver);
                        player.sendMessage("§aAdded Precision Screwdriver (" + min + "mm - " + max + "mm)");
                    } catch (NumberFormatException e) {
                        player.sendMessage("§cInvalid number format.");
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
