package com.chaseoes.mobninja.listeners;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.chaseoes.mobninja.GameUtilities;
import com.chaseoes.mobninja.NinjaPlayer;

public class EntityDamageByEntityListener implements Listener {

    @EventHandler
    public void entityDamageByEntity(EntityDamageByEntityEvent event) {
        System.out.println("EVENT!");
        System.out.println(event.getDamager());
        if (event.getDamager() instanceof Arrow) {
            System.out.println("ARROW!");
            Arrow arrow = (Arrow) event.getDamager();
            if (arrow.getShooter() instanceof Player) {
                System.out.println("PLAYER!");
                Player player = (Player) arrow.getShooter();
                if (GameUtilities.playerIsIngame(player)) {
                    NinjaPlayer np = new NinjaPlayer(player);
                    np.setKills(-1);
                    player.sendMessage(np.getKills() + " (kills)");
                }
            }
        }
    }

}
