package com.chaseoes.mobninja;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.chaseoes.mobninja.listeners.EntityDamageByEntityListener;
import com.chaseoes.mobninja.utilities.Utilities;

public class MobNinja extends JavaPlugin {

    private static MobNinja instance;
    private HashMap<String, MobNinjaGame> games = new HashMap<String, MobNinjaGame>();

    public static MobNinja getInstance() {
        return instance;
    }

    public void onEnable() {
        instance = this;
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new EntityDamageByEntityListener(), this);
    }

    public void onDisable() {
        instance = null;
    }

    public boolean onCommand(CommandSender cs, Command cmnd, String string, String[] strings) {
        if (strings.length == 0) {
            cs.sendMessage(Utilities.getPrefix() + "Version " + getDescription().getVersion() + " by " + getDescription().getAuthors().get(0) + ".");
            return true;
        }

        if (cs instanceof Player) {
            Player player = (Player) cs;
            if (strings[0].equalsIgnoreCase("join")) {
                if (strings.length == 2) {
                    MobNinjaGame game = new MobNinjaGame(strings[1]);
                    if (game.exists()) {
                        game.joinGame(player);
                    } else {
                        cs.sendMessage(Utilities.getPrefix() + "That game does not exist!");
                    }
                }
            }
        } else {
            cs.sendMessage("You must be a player to use that command!");
        }
        return true;
    }

    public List<MobNinjaGame> getGames() {
        List<MobNinjaGame> gamesList = new ArrayList<MobNinjaGame>();
        for (MobNinjaGame g : games.values()) {
            gamesList.add(g);
        }
        return gamesList;
    }

    public MobNinjaGame getGame(String name) {
        if (games.containsKey(name)) {
            return games.get(name);
        } else {
            return null;
        }
    }

}
