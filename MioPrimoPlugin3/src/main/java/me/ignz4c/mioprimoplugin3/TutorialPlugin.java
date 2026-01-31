package me.ignz4c.mioprimoplugin3;

import java.io.File;
import java.io.IOException;
import me.ignz4c.mioprimoplugin3.commands.ClearInventory;
import me.ignz4c.mioprimoplugin3.commands.ExampleCommand;
import me.ignz4c.mioprimoplugin3.commands.Heal;
import me.ignz4c.mioprimoplugin3.commands.Warps;
import me.ignz4c.mioprimoplugin3.commands.spawnZombie;
import me.ignz4c.mioprimoplugin3.events.explodeEntity;
import me.ignz4c.mioprimoplugin3.events.playerJoin;
import me.ignz4c.mioprimoplugin3.events.playerJoinKit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class TutorialPlugin extends JavaPlugin implements Listener {
  private FileConfiguration config;
  
  private File warpsFile;
  
  private FileConfiguration warpsConfig;
  
  public void onEnable() {
    getLogger().info("onEnable is called!");
    saveDefaultConfig();
    this.config = getConfig();
    this.config.options().copyDefaults(true);
    this.config.addDefault("youAreAwesome", Boolean.valueOf(true));
    saveConfig();
    loadWarpsFile();
    getServer().getPluginManager().registerEvents(this, (Plugin)this);
    getServer().getPluginManager().registerEvents((Listener)new playerJoinKit(), (Plugin)this);
    getServer().getPluginManager().registerEvents((Listener)new playerJoin(), (Plugin)this);
    getServer().getPluginManager().registerEvents((Listener)new explodeEntity(), (Plugin)this);
    getCommand("exampleCommand").setExecutor((CommandExecutor)new ExampleCommand());
    getCommand("heal").setExecutor((CommandExecutor)new Heal());
    getCommand("clearinventory").setExecutor((CommandExecutor)new ClearInventory());
    getCommand("spawnzombie").setExecutor((CommandExecutor)new spawnZombie());
    getCommand("warp").setExecutor((CommandExecutor)new Warps(this));
  }
  
  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event) {
    Player player = event.getPlayer();
    if (this.config.getBoolean("youAreAwesome")) {
      player.sendMessage("You are awesome!");
    } else {
      player.sendMessage("You are not awesome...");
    } 
  }
  
  public void loadWarpsFile() {
    this.warpsFile = new File(getDataFolder(), "warps.yml");
    if (!this.warpsFile.exists()) {
      this.warpsFile.getParentFile().mkdirs();
      try {
        this.warpsFile.createNewFile();
      } catch (IOException e) {
        e.printStackTrace();
      } 
    } 
    this.warpsConfig = (FileConfiguration)YamlConfiguration.loadConfiguration(this.warpsFile);
  }
  
  public void saveWarpsFile() {
    try {
      this.warpsConfig.save(this.warpsFile);
    } catch (IOException e) {
      getLogger().severe(String.valueOf(ChatColor.RED) + "Impossibile salvare il file warps.yml!");
      e.printStackTrace();
    } 
  }
  
  public FileConfiguration getWarpsConfig() {
    return this.warpsConfig;
  }
  
  public void onDisable() {
    getLogger().info("onDisable is called!");
    saveWarpsFile();
  }
}
