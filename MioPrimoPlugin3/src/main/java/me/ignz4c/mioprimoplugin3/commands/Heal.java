package me.ignz4c.mioprimoplugin3.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Heal implements CommandExecutor {
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (!sender.hasPermission("tutorial.heal")) {
      sender.sendMessage(String.valueOf(ChatColor.RED) + "Non hai il permesso per usare questo comando!");
      return true;
    } 
    if (!(sender instanceof Player) && 
      args.length == 0) {
      sender.sendMessage(String.valueOf(ChatColor.RED) + "Errore: Devi specificare un nome da curare!");
      return true;
    } 
    Player targetPlayer = null;
    if (args.length >= 1) {
      targetPlayer = Bukkit.getServer().getPlayer(args[0]);
      if (targetPlayer == null) {
        sender.sendMessage(String.valueOf(ChatColor.RED) + "Errore: Il giocatore " + String.valueOf(ChatColor.RED) + " non è online.");
        return true;
      } 
    } else {
      if (args.length > 1) {
        sender.sendMessage(String.valueOf(ChatColor.RED) + "Errore: Utilizzo corretto: /heal [nome]");
        return true;
      } 
      targetPlayer = (Player)sender;
    } 
    targetPlayer.setHealth(targetPlayer.getMaxHealth());
    targetPlayer.setFoodLevel(20);
    if (targetPlayer == sender) {
      sender.sendMessage(String.valueOf(ChatColor.GREEN) + "Sei stato curato!");
    } else {
      sender.sendMessage(String.valueOf(ChatColor.GREEN) + String.valueOf(ChatColor.GREEN) + " è stato curato.");
      targetPlayer.sendMessage(String.valueOf(ChatColor.GREEN) + "Sei stato curato da " + String.valueOf(ChatColor.GREEN) + "!");
    } 
    return true;
  }
}
