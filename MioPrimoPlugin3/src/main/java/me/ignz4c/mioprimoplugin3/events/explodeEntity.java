package me.ignz4c.mioprimoplugin3.events;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class explodeEntity implements Listener {
  @EventHandler
  public void onEntityRightClick(PlayerInteractEntityEvent event) {
    System.out.println("Qualcuno ha cliccato tasto destro su" + String.valueOf(event.getRightClicked().getClass()));
    if (event.getRightClicked().getType() == EntityType.ZOMBIE) {
      Zombie zombie = (Zombie)event.getRightClicked();
      zombie.getWorld().createExplosion(zombie.getLocation(), 2.5F);
    } 
  }
}
