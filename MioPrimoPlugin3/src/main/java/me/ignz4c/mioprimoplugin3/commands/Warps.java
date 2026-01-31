package me.ignz4c.mioprimoplugin3.commands;

import java.util.Set;
import me.ignz4c.mioprimoplugin3.TutorialPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Warps implements CommandExecutor {
  private final TutorialPlugin plugin;
  
  public Warps(TutorialPlugin plugin) {
    this.plugin = plugin;
  }
  
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (!(sender instanceof Player)) {
      sender.sendMessage(String.valueOf(ChatColor.RED) + "Solo i giocatori possono usare questo comando.");
      return true;
    } 
    Player player = (Player)sender;
    if (!player.hasPermission("tutorial.warp")) {
      player.sendMessage(String.valueOf(ChatColor.RED) + "Non hai il permesso per utilizzare il comando /warp.");
      return true;
    } 
    if (args.length >= 2 && args[0].equalsIgnoreCase("set")) {
      if (!player.hasPermission("tutorial.warp.set")) {
        player.sendMessage(String.valueOf(ChatColor.RED) + "Non hai il permesso di impostare un warp.");
        return true;
      } 
      String warpName = args[1].toLowerCase();
      Location loc = player.getLocation();
      FileConfiguration config = this.plugin.getWarpsConfig();
      config.set("warps." + warpName + ".world", loc.getWorld().getName());
      config.set("warps." + warpName + ".x", Double.valueOf(loc.getX()));
      config.set("warps." + warpName + ".y", Double.valueOf(loc.getY()));
      config.set("warps." + warpName + ".z", Double.valueOf(loc.getZ()));
      config.set("warps." + warpName + ".pitch", Float.valueOf(loc.getPitch()));
      config.set("warps." + warpName + ".yaw", Float.valueOf(loc.getYaw()));
      this.plugin.saveWarpsFile();
      player.sendMessage(String.valueOf(ChatColor.GREEN) + "Warp '" + String.valueOf(ChatColor.GREEN) + "' impostato con successo!");
      return true;
    } 
    if (args.length >= 2 && args[0].equalsIgnoreCase("delete")) {
      if (!player.hasPermission("tutorial.warp.delete")) {
        player.sendMessage(String.valueOf(ChatColor.RED) + "Non hai il permesso di cancellare un warp.");
        return true;
      } 
      String warpName = args[1].toLowerCase();
      FileConfiguration config = this.plugin.getWarpsConfig();
      String path = "warps." + warpName;
      if (config.contains(path)) {
        config.set(path, null);
        this.plugin.saveWarpsFile();
        player.sendMessage(String.valueOf(ChatColor.GREEN) + "Warp '" + String.valueOf(ChatColor.GREEN) + "' eliminato con successo!");
      } else {
        player.sendMessage(String.valueOf(ChatColor.RED) + "Errore: Il warp '" + String.valueOf(ChatColor.RED) + "' non esiste!");
      } 
      return true;
    } 
    if (args.length == 1 && args[0].equalsIgnoreCase("list")) {
      FileConfiguration config = this.plugin.getWarpsConfig();
      if (!config.contains("warps")) {
        player.sendMessage(String.valueOf(ChatColor.YELLOW) + "Non ci sono warp salvate al momento");
        return true;
      } 
      Set<String> warpNames = config.getConfigurationSection("warps").getKeys(false);
      if (warpNames.isEmpty()) {
        player.sendMessage(String.valueOf(ChatColor.YELLOW) + "Non ci sono warp salvate al momento");
        return true;
      } 
      String listString = String.join(", ", (Iterable)warpNames);
      String WarpSalvate = String.valueOf(ChatColor.AQUA) + "--- Warp Salvate (" + String.valueOf(ChatColor.AQUA) + ") ---" + warpNames.size() + "\n" + String.valueOf(ChatColor.GREEN);
      player.sendMessage(WarpSalvate);
      return true;
    } 
    if (args.length == 1) {
      String warpName = args[0].toLowerCase();
      FileConfiguration config = this.plugin.getWarpsConfig();
      if (config.contains("warps." + warpName)) {
        String worldName = config.getString("warps." + warpName + ".world");
        double x = config.getDouble("warps." + warpName + ".x");
        double y = config.getDouble("warps." + warpName + ".y");
        double z = config.getDouble("warps." + warpName + ".z");
        float pitch = (float)config.getDouble("warps." + warpName + ".pitch");
        float yaw = (float)config.getDouble("warps." + warpName + ".yaw");
        World world = Bukkit.getServer().getWorld(worldName);
        if (world == null) {
          player.sendMessage(String.valueOf(ChatColor.RED) + "Errore: Il mondo della warp non è caricato. Contatta un admin.");
          return true;
        } 
        Location targetLocation = new Location(world, x, y, z, yaw, pitch);
        player.teleport(targetLocation);
        player.sendMessage(String.valueOf(ChatColor.GREEN) + "Teletrasporto alla warp '" + String.valueOf(ChatColor.GREEN) + "' riuscito!");
      } else {
        player.sendMessage(String.valueOf(ChatColor.RED) + "Errore: La warp '" + String.valueOf(ChatColor.RED) + "' non esiste!");
      } 
      return true;
    } 
    String helpMessage = String.valueOf(ChatColor.YELLOW) + "Uso corretto del comando /warp:" + String.valueOf(ChatColor.YELLOW) + "\n/warp [nome]" + String.valueOf(ChatColor.YELLOW) + " (Teletrasporto)" + String.valueOf(ChatColor.GRAY) + "\n/warp set [nome]" + String.valueOf(ChatColor.YELLOW) + " (Imposta un nuovo warp)" + String.valueOf(ChatColor.GRAY) + "\n/warp delete [nome]" + String.valueOf(ChatColor.YELLOW) + " (Elimina un warp)" + String.valueOf(ChatColor.GRAY) + "\n/warp list" + String.valueOf(ChatColor.YELLOW) + " (Elenca tutti i warp)";
    player.sendMessage(helpMessage);
    return true;
  }
}
