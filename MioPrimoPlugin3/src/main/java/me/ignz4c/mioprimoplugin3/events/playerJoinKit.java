package me.ignz4c.mioprimoplugin3.events;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

public class playerJoinKit implements Listener {
  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event) {
    Player player = event.getPlayer();
    PlayerInventory inventory = player.getInventory();
    ItemStack cobblestone = new ItemStack(Material.COBBLESTONE, 64);
    ItemStack spada = new ItemStack(Material.DIAMOND_SWORD);
    player.sendMessage(String.valueOf(ChatColor.GREEN) + "Ti è stato dato il kit base!");
    ItemMeta meta = spada.getItemMeta();
    meta.setDisplayName(String.valueOf(ChatColor.DARK_AQUA) + String.valueOf(ChatColor.DARK_AQUA) + "Spadona Acquatica");
    meta.addEnchant(Enchantment.DAMAGE_ALL, 100, true);
    spada.setItemMeta(meta);
    inventory.addItem(new ItemStack[] { cobblestone, spada });
  }
}
