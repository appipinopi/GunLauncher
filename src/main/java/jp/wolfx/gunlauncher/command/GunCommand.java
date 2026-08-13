package jp.wolfx.gunlauncher.command;

import jp.wolfx.gunlauncher.api.*;
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
            sender.sendMessage("§e/gun give <player> <gunId> §7- Give a custom gun");
            sender.sendMessage("§e/gun ammo <player> <ammoId> [amount] §7- Give ammo");
            sender.sendMessage("§e/gun part <player> <partId> §7- Give attachment part");
            sender.sendMessage("§e/gun list §7- List all registered items");
            return true;
        }

        String sub = args[0].toLowerCase();
        if (sub.equals("list")) {
            sender.sendMessage("§6--- Registered Guns (" + GunRegistry.getGuns().size() + ") ---");
            for (CustomGun gun : GunRegistry.getGuns()) {
                sender.sendMessage("§e- Gun: " + gun.getId() + " (" + gun.getName() + ")");
            }
            sender.sendMessage("§6--- Registered Ammo (" + ItemRegistry.getAmmos().size() + ") ---");
            for (Ammunition ammo : ItemRegistry.getAmmos()) {
                sender.sendMessage("§e- Ammo: " + ammo.getId() + " (" + ammo.getName() + ")");
            }
            sender.sendMessage("§6--- Registered Attachments (" + ItemRegistry.getAttachments().size() + ") ---");
            for (Attachment part : ItemRegistry.getAttachments()) {
                sender.sendMessage("§e- Part: " + part.getId() + " [" + part.getSlot() + "] (" + part.getName() + ")");
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
            CustomGun gun = GunRegistry.getGun(args[2]);
            if (gun == null) {
                sender.sendMessage("§cGun ID '" + args[2] + "' not found.");
                return true;
            }
            target.getInventory().addItem(gun.craftItemStack());
            sender.sendMessage("§aGave gun " + gun.getId() + " to " + target.getName());
            return true;
        } else if (sub.equals("ammo")) {
            if (args.length < 3) {
                sender.sendMessage("§cUsage: /gun ammo <player> <ammoId> [amount]");
                return true;
            }
            Player target = Bukkit.getPlayer(args[1]);
            if (target == null) {
                sender.sendMessage("§cPlayer not found.");
                return true;
            }
            Ammunition ammo = ItemRegistry.getAmmo(args[2]);
            if (ammo == null) {
                sender.sendMessage("§cAmmo ID '" + args[2] + "' not found.");
                return true;
            }
            int amount = 30;
            if (args.length >= 4) {
                try {
                    amount = Integer.parseInt(args[3]);
                } catch (NumberFormatException ignored) {}
            }
            target.getInventory().addItem(ammo.craftItemStack(amount));
            sender.sendMessage("§aGave " + amount + " of " + ammo.getId() + " to " + target.getName());
            return true;
        } else if (sub.equals("part")) {
            if (args.length < 3) {
                sender.sendMessage("§cUsage: /gun part <player> <partId>");
                return true;
            }
            Player target = Bukkit.getPlayer(args[1]);
            if (target == null) {
                sender.sendMessage("§cPlayer not found.");
                return true;
            }
            Attachment part = ItemRegistry.getAttachment(args[2]);
            if (part == null) {
                sender.sendMessage("§cAttachment ID '" + args[2] + "' not found.");
                return true;
            }
            target.getInventory().addItem(part.craftItemStack());
            sender.sendMessage("§aGave attachment " + part.getId() + " to " + target.getName());
            return true;
        }

        sender.sendMessage("§cUnknown subcommand. Use /gun for help.");
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            return List.of("give", "ammo", "part", "list").stream().filter(s -> s.startsWith(args[0].toLowerCase())).collect(Collectors.toList());
        } else if (args.length == 2 && (args[0].equalsIgnoreCase("give") || args[0].equalsIgnoreCase("ammo") || args[0].equalsIgnoreCase("part"))) {
            return null; // players
        } else if (args.length == 3) {
            if (args[0].equalsIgnoreCase("give")) {
                return GunRegistry.getGuns().stream().map(CustomGun::getId).collect(Collectors.toList());
            } else if (args[0].equalsIgnoreCase("ammo")) {
                return ItemRegistry.getAmmos().stream().map(Ammunition::getId).collect(Collectors.toList());
            } else if (args[0].equalsIgnoreCase("part")) {
                return ItemRegistry.getAttachments().stream().map(Attachment::getId).collect(Collectors.toList());
            }
        }
        return new ArrayList<>();
    }
}
