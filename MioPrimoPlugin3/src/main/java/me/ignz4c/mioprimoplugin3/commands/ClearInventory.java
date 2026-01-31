package me.ignz4c.mioprimoplugin3.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearInventory implements CommandExecutor {
  public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
    if (!(commandSender instanceof Player)) {
      commandSender.sendMessage(String.valueOf(ChatColor.RED) + "Comando che può essere eseguito solo da un player");
      return true;
    } 
    Player player = (Player)commandSender;
    player.getInventory().clear();
    player.sendMessage(String.valueOf(ChatColor.GREEN) + "Il tuo inventario è stato svuotato!");
    return true;
  }
}
