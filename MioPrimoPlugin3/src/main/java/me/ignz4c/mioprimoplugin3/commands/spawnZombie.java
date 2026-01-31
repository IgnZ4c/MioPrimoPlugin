package me.ignz4c.mioprimoplugin3.commands;

import me.ignz4c.mioprimoplugin3.TutorialPlugin;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class spawnZombie implements CommandExecutor {
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    TutorialPlugin plugin = (TutorialPlugin)JavaPlugin.getPlugin(TutorialPlugin.class);
    if (!(sender instanceof Player)) {
      sender.sendMessage("Non sei un giocatore.");
      return true;
    } 
    Player player = (Player)sender;
    World world = player.getWorld();
    world.spawnEntity(player.getLocation(), EntityType.ZOMBIE);
    String percorsoConfig = "messaggi.spawn-zombie";
    String messaggii = plugin.getConfig().getString(percorsoConfig);
    return true;
  }
}
