package jp.wolfx.guncraft.recipe;

import jp.wolfx.guncraft.item.CraftMaterials;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.Plugin;

public class MachineRecipes {

    public static void registerRecipes(Plugin plugin) {
        // 1. プリント台のレシピ
        NamespacedKey printKey = new NamespacedKey(plugin, "printing_table");
        ShapedRecipe printRecipe = new ShapedRecipe(printKey, CraftMaterials.getPrintingTable());
        printRecipe.shape("III", "ICI", "III");
        printRecipe.setIngredient('I', Material.IRON_INGOT);
        printRecipe.setIngredient('C', Material.CARTOGRAPHY_TABLE);
        Bukkit.addRecipe(printRecipe);

        // 2. 丸める機械のレシピ
        NamespacedKey rollKey = new NamespacedKey(plugin, "rolling_machine");
        ShapedRecipe rollRecipe = new ShapedRecipe(rollKey, CraftMaterials.getRollingMachine());
        printRecipe.shape("IDI", "DBD", "IDI"); // Wait, use rollKey
        ShapedRecipe rollRecipeReal = new ShapedRecipe(rollKey, CraftMaterials.getRollingMachine());
        rollRecipeReal.shape("IDI", "DBD", "IDI");
        rollRecipeReal.setIngredient('I', Material.IRON_INGOT);
        rollRecipeReal.setIngredient('D', Material.DISPENSER);
        rollRecipeReal.setIngredient('B', Material.BLAST_FURNACE);
        Bukkit.addRecipe(rollRecipeReal);

        // 3. 銃器組立台のレシピ
        NamespacedKey asmKey = new NamespacedKey(plugin, "assembly_table");
        ShapedRecipe asmRecipe = new ShapedRecipe(asmKey, CraftMaterials.getAssemblyTable());
        asmRecipe.shape("NNN", "ATA", "NNN");
        asmRecipe.setIngredient('N', Material.NETHERITE_INGOT);
        asmRecipe.setIngredient('A', Material.ANVIL);
        asmRecipe.setIngredient('T', Material.SMITHING_TABLE);
        Bukkit.addRecipe(asmRecipe);

        plugin.getLogger().info("Registered custom workstation recipes for Gun-Craft!");
    }
}
