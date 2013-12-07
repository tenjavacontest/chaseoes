package com.chaseoes.mobninja.listeners;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.chaseoes.mobninja.GameUtilities;
import com.chaseoes.mobninja.MobNinjaGame;
import com.chaseoes.mobninja.NinjaPlayer;

public class EntityDamageByEntityListener implements Listener {

    @EventHandler
    public void entityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Arrow) {
            Arrow arrow = (Arrow) event.getDamager();
            if (arrow.getShooter() instanceof Player) {
                Player player = (Player) arrow.getShooter();
                if (GameUtilities.playerIsIngame(player)) {
                    MobNinjaGame game = GameUtilities.getCurrentGame(player);
                    NinjaPlayer np = game.getNinjaPlayers().get(player.getName());
                    np.setKills(-1);
                    game.getScoreboard().update();
                }
            }
        }
    }

}
