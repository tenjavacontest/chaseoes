package com.chaseoes.mobninja.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        try {
            if (event.hasBlock()) {
                if ((event.getClickedBlock().getType() == Material.SIGN || event.getClickedBlock().getType() == Material.WALL_SIGN) && !event.getPlayer().isSneaking()) {
                    Sign s = (Sign) event.getClickedBlock().getState();
                    if (ChatColor.stripColor(s.getLine(0)).equalsIgnoreCase("[MobNinja]")) {
                        String map = s.getLine(2);
                        event.getPlayer().performCommand("mobninja join " + map);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
