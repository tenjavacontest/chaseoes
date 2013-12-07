package com.chaseoes.mobninja;

import org.bukkit.entity.Player;

import com.chaseoes.mobninja.utilities.SerializableLocation;

public class MobNinjaGame {
    
    private String name;
    
    public MobNinjaGame(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public boolean exists() {
        return MobNinja.getInstance().getConfig().getString("games." + getName() + ".spawn") != null;
    }
    
    public void create(Player player) {
        MobNinja.getInstance().getConfig().set("games." + getName() + ".spawn", SerializableLocation.serializeLocation(player.getLocation()));
    }

}
