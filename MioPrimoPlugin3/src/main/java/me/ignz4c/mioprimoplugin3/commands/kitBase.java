package me.ignz4c.mioprimoplugin3.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

public class kitBase implements CommandExecutor {
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    Player player = (Player)sender;
    PlayerInventory inventory = player.getInventory();
    ItemStack cobblestone = new ItemStack(Material.COBBLESTONE, 64);
    ItemStack spada = new ItemStack(Material.DIAMOND_SWORD);
    player.sendMessage(String.valueOf(ChatColor.GREEN) + "Ti è stato dato il kit base tramite il comando!");
    ItemMeta meta = spada.getItemMeta();
    meta.setDisplayName(String.valueOf(ChatColor.DARK_AQUA) + String.valueOf(ChatColor.DARK_AQUA) + "Spadona Aquatica");
    meta.addEnchant(Enchantment.DAMAGE_ALL, 100, true);
    spada.setItemMeta(meta);
    inventory.addItem(new ItemStack[] { cobblestone, spada });
    return true;
  }
}
