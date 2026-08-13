package jp.wolfx.guncraft;

import jp.wolfx.guncraft.manager.AdvancedMachineManager;
import jp.wolfx.guncraft.manager.AssemblyAndRollingManager;
import jp.wolfx.guncraft.manager.PrintingTableManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
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

        getServer().getPluginManager().registerEvents(printingManager, this);
        getServer().getPluginManager().registerEvents(assemblyManager, this);
        getServer().getPluginManager().registerEvents(advancedManager, this);

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
