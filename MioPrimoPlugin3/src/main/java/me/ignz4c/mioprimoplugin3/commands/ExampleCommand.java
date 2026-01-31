package me.ignz4c.mioprimoplugin3.commands;

import me.ignz4c.mioprimoplugin3.TutorialPlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class ExampleCommand implements CommandExecutor {
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    TutorialPlugin plugin = (TutorialPlugin)JavaPlugin.getPlugin(TutorialPlugin.class);
    String percorsoConfig = "messaggi.principale";
    if (label.equalsIgnoreCase("alias1")) {
      percorsoConfig = "messaggi.alias1";
    } else if (label.equalsIgnoreCase("alias2")) {
      percorsoConfig = "messaggi.alias2";
    } 
    String messaggio = plugin.getConfig().getString(percorsoConfig);
    sender.sendMessage(ChatColor.translateAlternateColorCodes('§', messaggio));
    return true;
  }
}
