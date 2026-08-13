package jp.wolfx.gunlauncher.command;

import jp.wolfx.gunlauncher.api.CustomGun;
import jp.wolfx.gunlauncher.api.GunRegistry;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GunCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("gunlauncher.admin")) {
            sender.sendMessage("§cYou do not have permission to use this command.");
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage("§6--- GunLauncher Commands ---");
            sender.sendMessage("§e/gun give <player> <gunId> §7- Give a custom gun to a player");
            sender.sendMessage("§e/gun list §7- List all registered custom guns");
            return true;
        }

        String sub = args[0].toLowerCase();
        if (sub.equals("list")) {
            sender.sendMessage("§6--- Registered Guns (" + GunRegistry.getGuns().size() + ") ---");
            for (CustomGun gun : GunRegistry.getGuns()) {
                sender.sendMessage("§e- " + gun.getId() + " (" + gun.getName() + ")");
            }
            return true;
        } else if (sub.equals("give")) {
            if (args.length < 3) {
                sender.sendMessage("§cUsage: /gun give <player> <gunId>");
                return true;
            }
            Player target = Bukkit.getPlayer(args[1]);
            if (target == null) {
                sender.sendMessage("§cPlayer not found.");
                return true;
            }
            String gunId = args[2];
            CustomGun gun = GunRegistry.getGun(gunId);
            if (gun == null) {
                sender.sendMessage("§cCustom gun ID '" + gunId + "' not found. Make sure the gun plugin is installed and loaded.");
                return true;
            }

            ItemStack item = gun.craftItemStack();
            target.getInventory().addItem(item);
            sender.sendMessage("§aSuccessfully gave " + gun.getId() + " to " + target.getName());
            target.sendMessage("§aYou received: " + gun.getName());
            return true;
        }

        sender.sendMessage("§cUnknown subcommand. Use /gun for help.");
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            return List.of("give", "list").stream().filter(s -> s.startsWith(args[0].toLowerCase())).collect(Collectors.toList());
        } else if (args.length == 2 && args[0].equalsIgnoreCase("give")) {
            return null; // players
        } else if (args.length == 3 && args[0].equalsIgnoreCase("give")) {
            return GunRegistry.getGuns().stream().map(CustomGun::getId).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
