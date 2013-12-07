package com.chaseoes.mobninja.listeners;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.chaseoes.mobninja.GameUtilities;
import com.chaseoes.mobninja.NinjaPlayer;

public class EntityDamageByEntityListener implements Listener {

    public void entityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Arrow) {
            Arrow arrow = (Arrow) event.getDamager();
            if (arrow.getShooter() instanceof Player) {
                Player player = (Player) arrow.getShooter();
                if (GameUtilities.playerIsIngame(player)) {
                    NinjaPlayer np = new NinjaPlayer(player);
                    np.setKills(-1);
                }
            }
        }
    }

}
