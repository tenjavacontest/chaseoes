package com.chaseoes.mobninja;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import com.chaseoes.mobninja.utilities.SerializableLocation;
import com.chaseoes.mobninja.utilities.Utilities;

public class MobNinjaGame {
    
    private String name;
    private List<String> playersInGame = new ArrayList<String>();
    
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
    
    public void joinGame(Player player) {
        playersInGame.add(player.getName());
        MobNinja.getInstance().getServer().broadcastMessage(Utilities.getPrefix() + player.getName() + " joined!");
    }
    
    public void leaveGame(Player player) {
        playersInGame.remove(player.getName());
        MobNinja.getInstance().getServer().broadcastMessage(Utilities.getPrefix() + player.getName() + " left!");
    }
    
    public void startGame() {
        
    }
    
    public void stopGame() {
        
    }
    
    public List<String> getPlayersInGame() {
        return playersInGame;
    }

}
