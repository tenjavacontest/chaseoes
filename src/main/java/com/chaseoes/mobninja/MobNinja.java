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
import com.chaseoes.mobninja.listeners.PlayerInteractListener;
import com.chaseoes.mobninja.listeners.PlayerQuitListener;
import com.chaseoes.mobninja.listeners.SignChangeListener;
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
        pm.registerEvents(new PlayerQuitListener(), this);
        pm.registerEvents(new SignChangeListener(), this);
        pm.registerEvents(new PlayerInteractListener(), this);

        for (String name : getConfig().getConfigurationSection("games").getKeys(false)) {
            games.put(name, new MobNinjaGame(name));
        }
    }

    public void onDisable() {
        getServer().getScheduler().cancelTasks(this);
        reloadConfig();
        saveConfig();
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
                    if (games.containsKey(strings[1])) {
                        MobNinjaGame game = getGame(strings[1]);
                        game.joinGame(player);
                    } else {
                        cs.sendMessage(Utilities.getPrefix() + "That game does not exist!");
                    }
                } else {
                    cs.sendMessage(Utilities.getPrefix() + "Incorrect command usage!");
                }
            }

            if (strings[0].equalsIgnoreCase("leave")) {
                if (strings.length == 1) {
                    MobNinjaGame game = GameUtilities.getCurrentGame(player);
                    game.leaveGame(player);
                } else {
                    cs.sendMessage(Utilities.getPrefix() + "Incorrect command usage!");
                }
            }

            if (strings[0].equalsIgnoreCase("start")) {
                if (cs.hasPermission("mobninja.start")) {
                    if (strings.length == 2) {
                        if (games.containsKey(strings[1])) {
                            MobNinjaGame game = getGame(strings[1]);
                            game.startGame();
                            cs.sendMessage(Utilities.getPrefix() + "Game started.");
                        } else {
                            cs.sendMessage(Utilities.getPrefix() + "That game does not exist!");
                        }
                    } else {
                        cs.sendMessage(Utilities.getPrefix() + "Incorrect command usage!");
                    }
                } else {
                    cs.sendMessage(Utilities.getPrefix() + "You don't have permission.");
                }
            }

            if (strings[0].equalsIgnoreCase("stop")) {
                if (cs.hasPermission("mobninja.stop")) {
                    if (strings.length == 2) {
                        if (games.containsKey(strings[1])) {
                            MobNinjaGame game = getGame(strings[1]);
                            game.stopGame();
                            cs.sendMessage(Utilities.getPrefix() + "Game stopped.");
                        } else {
                            cs.sendMessage(Utilities.getPrefix() + "That game does not exist!");
                        }
                    } else {
                        cs.sendMessage(Utilities.getPrefix() + "Incorrect command usage!");
                    }
                } else {
                    cs.sendMessage(Utilities.getPrefix() + "You don't have permission.");
                }
            }

            if (strings[0].equalsIgnoreCase("create")) {
                if (cs.hasPermission("mobninja.create")) {
                    if (strings.length == 2) {
                        MobNinjaGame game = new MobNinjaGame(strings[1]);
                        if (!game.exists()) {
                            game.create(player);
                            games.put(strings[1], game);
                            cs.sendMessage(Utilities.getPrefix() + "Sucessfully created game!");
                        } else {
                            cs.sendMessage(Utilities.getPrefix() + "That game already exists!");
                        }
                    } else {
                        cs.sendMessage(Utilities.getPrefix() + "Incorrect command usage!");
                    }
                } else {
                    cs.sendMessage(Utilities.getPrefix() + "You don't have permission.");
                }
            }

            if (strings[0].equalsIgnoreCase("addspawn")) {
                if (cs.hasPermission("mobninja.addspawn")) {
                    if (strings.length == 2) {
                        if (games.containsKey(strings[1])) {
                            MobNinjaGame game = getGame(strings[1]);
                            game.addSpawn(player.getLocation());
                            cs.sendMessage(Utilities.getPrefix() + "Added spawn location!");
                        } else {
                            cs.sendMessage(Utilities.getPrefix() + "That game does not exist!");
                        }
                    } else {
                        cs.sendMessage(Utilities.getPrefix() + "Incorrect command usage!");
                    }
                } else {
                    cs.sendMessage(Utilities.getPrefix() + "You don't have permission.");
                }
            }
            
            if (strings[0].equalsIgnoreCase("setmax")) {
                if (cs.hasPermission("mobninja.setmax")) {
                    if (strings.length == 3) {
                        if (games.containsKey(strings[1])) {
                            MobNinjaGame game = getGame(strings[1]);
                            game.setMaxPlayers(Integer.parseInt(strings[2]));
                            cs.sendMessage(Utilities.getPrefix() + "Maximum players set to " + strings[2] + "!");
                        } else {
                            cs.sendMessage(Utilities.getPrefix() + "That game does not exist!");
                        }
                    } else {
                        cs.sendMessage(Utilities.getPrefix() + "Incorrect command usage!");
                    }
                } else {
                    cs.sendMessage(Utilities.getPrefix() + "You don't have permission.");
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
