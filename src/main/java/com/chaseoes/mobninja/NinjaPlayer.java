package com.chaseoes.mobninja;

import org.bukkit.entity.Player;

public class NinjaPlayer {
    
    private String name;
    
    int kills = 0;
    
    public NinjaPlayer(Player player) {
        name = player.getName();
    }
    
    public String getName() {
        return name;
    }
    
    public int getKills() {
        return kills;
    }
    
    public void setKills(int i) {
        if (i == -1) {
            kills++;
        } else {
            kills = i;
        }
    }

}
