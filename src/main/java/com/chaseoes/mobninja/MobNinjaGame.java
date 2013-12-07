package com.chaseoes.mobninja;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import com.chaseoes.mobninja.utilities.SerializableLocation;
import com.chaseoes.mobninja.utilities.Utilities;

public class MobNinjaGame {

    private String name;
    private List<String> playersInGame = new ArrayList<String>();
    private HashMap<String, NinjaPlayer> ninjaPlayers = new HashMap<String, NinjaPlayer>();

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
        MobNinja.getInstance().saveConfig();
    }

    public void addSpawn(Location location) {
        List<String> spawns = new ArrayList<String>();
        if (MobNinja.getInstance().getConfig().getStringList("games." + getName() + ".spawns") != null) {
            spawns = MobNinja.getInstance().getConfig().getStringList("games." + getName() + ".spawns");
        }
        spawns.add(SerializableLocation.serializeLocation(location));
        MobNinja.getInstance().getConfig().set("games." + getName() + ".spawns", spawns);
        MobNinja.getInstance().saveConfig();
    }

    public List<String> getSpawns() {
        return MobNinja.getInstance().getConfig().getStringList("games." + getName() + ".spawns");
    }

    public Location getSpawnLocation() {
        return SerializableLocation.unSerializeLocation(MobNinja.getInstance().getConfig().getString("games." + getName() + ".spawn"));
    }

    public void joinGame(Player player) {
        playersInGame.add(player.getName());
        ninjaPlayers.put(player.getName(), new NinjaPlayer(player));
        player.teleport(getSpawnLocation());
        MobNinja.getInstance().getServer().broadcastMessage(Utilities.getPrefix() + player.getName() + " joined!");

        System.out.println(playersInGame.size());
        if (getPlayersInGame().size() == 1) {
            startGame();
        }
    }

    public void leaveGame(Player player) {
        playersInGame.remove(player.getName());
        ninjaPlayers.remove(player.getName());
        player.teleport(player.getLocation().getWorld().getSpawnLocation());
        MobNinja.getInstance().getServer().broadcastMessage(Utilities.getPrefix() + player.getName() + " left!");
    }

    public void startGame() {
        MobNinja.getInstance().getServer().broadcastMessage(Utilities.getPrefix() + "The game has started!");
        BukkitTask task = new GameTask(this).runTaskTimer(MobNinja.getInstance(), 0L, 100L);
    }

    public void stopGame() {

    }

    public List<String> getPlayersInGame() {
        return playersInGame;
    }

    public HashMap<String, NinjaPlayer> getNinjaPlayers() {
        return ninjaPlayers;
    }

}
