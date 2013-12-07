package com.chaseoes.mobninja.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerQuitEvent;

import com.chaseoes.mobninja.GameUtilities;

public class PlayerQuitListener {
    
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (GameUtilities.playerIsIngame(player)) {
            GameUtilities.getCurrentGame(player).leaveGame(player);
        }
    }

}
