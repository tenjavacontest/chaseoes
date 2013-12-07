package com.chaseoes.mobninja.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import com.chaseoes.mobninja.utilities.Utilities;

public class SignChangeListener implements Listener {
    
    @EventHandler
    public void onSignChange(SignChangeEvent event) {
        if (event.getPlayer().hasPermission("mobninja.createsign")) {
            if (event.getLine(0).equalsIgnoreCase("[MobNinja]")) {
                event.setLine(0, ChatColor.BOLD + "[MobNinja]");
                event.setLine(1, "Click to join:");
                event.setLine(2, event.getLine(1));
                event.setLine(3, "--");
                event.getPlayer().sendMessage(Utilities.getPrefix() + "Added join sign successfully!");
            }
        }
    }

}
