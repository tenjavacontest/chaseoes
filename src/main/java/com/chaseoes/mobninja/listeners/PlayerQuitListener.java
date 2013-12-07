package com.chaseoes.mobninja.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.chaseoes.mobninja.GameUtilities;

public class PlayerQuitListener implements Listener {
    
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (GameUtilities.playerIsIngame(player)) {
            GameUtilities.getCurrentGame(player).leaveGame(player);
        }
    }

}
