package com.chaseoes.mobninja;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;

import com.chaseoes.mobninja.utilities.SerializableLocation;
import com.chaseoes.mobninja.utilities.Utilities;

public class MobNinjaGame {

    private String name;
    private List<String> playersInGame = new ArrayList<String>();
    private HashMap<String, NinjaPlayer> ninjaPlayers = new HashMap<String, NinjaPlayer>();
    private GameScoreboard scoreboard;
    private BukkitTask gameTask;

    public MobNinjaGame(String name) {
        this.name = name;
        scoreboard = new GameScoreboard(this);
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
    
    public GameScoreboard getScoreboard() {
        return scoreboard;
    }

    public void joinGame(Player player) {
        playersInGame.add(player.getName());
        ninjaPlayers.put(player.getName(), new NinjaPlayer(player));
        player.teleport(getSpawnLocation());
        player.setScoreboard(getScoreboard().getScoreboard());
        MobNinja.getInstance().getServer().broadcastMessage(Utilities.getPrefix() + player.getName() + " joined!");
        
        ItemStack bow = new ItemStack(Material.BOW, 1);
        bow.addEnchantment(Enchantment.ARROW_INFINITE, 1);
        bow.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 10);
        if (!player.getInventory().contains(Material.BOW)) {
            player.getInventory().clear();
            player.getInventory().addItem(bow);
        }
        
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
        gameTask = new GameTask(this).runTaskTimer(MobNinja.getInstance(), 0L, 100L);
    }
    
    public void winGame(Player player) {
        MobNinja.getInstance().getServer().broadcastMessage(Utilities.getPrefix() + player.getName() + " won the game!");
        stopGame();
    }

    public void stopGame() {
        gameTask.cancel();
        for (String p : getPlayersInGame()) {
            leaveGame(MobNinja.getInstance().getServer().getPlayerExact(p));
        }
        MobNinja.getInstance().getServer().broadcastMessage(Utilities.getPrefix() + "The game has ended.");
    }

    public List<String> getPlayersInGame() {
        return playersInGame;
    }

    public HashMap<String, NinjaPlayer> getNinjaPlayers() {
        return ninjaPlayers;
    }

}
