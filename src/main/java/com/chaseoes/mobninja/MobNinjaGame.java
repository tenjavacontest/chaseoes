package com.chaseoes.mobninja;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

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
        player.teleport(SerializableLocation.unSerializeLocation(MobNinja.getInstance().getConfig().getString("games." + getName() + ".spawn")));
        MobNinja.getInstance().getServer().broadcastMessage(Utilities.getPrefix() + player.getName() + " joined!");
        
        if (getPlayersInGame().size() == 2) {
            startGame();
        }
    }
    
    public void leaveGame(Player player) {
        playersInGame.remove(player.getName());
        player.teleport(player.getLocation().getWorld().getSpawnLocation());
        MobNinja.getInstance().getServer().broadcastMessage(Utilities.getPrefix() + player.getName() + " left!");
    }
    
    public void startGame() {
        BukkitTask task = new GameTask(this).runTaskTimer(MobNinja.getInstance(), 0L, 100L);
    }
    
    public void stopGame() {
        
    }
    
    public List<String> getPlayersInGame() {
        return playersInGame;
    }

}
