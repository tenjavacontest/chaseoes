package com.chaseoes.mobninja;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import com.chaseoes.mobninja.listeners.EntityDamageByEntityListener;
import com.chaseoes.mobninja.utilities.Utilities;

public class MobNinja extends JavaPlugin implements Listener {

    private static MobNinja instance;

    public static MobNinja getInstance() {
        return instance;
    }

    public void onEnable() {
        instance = this;
        getServer().getPluginManager().registerEvents(this, this);

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

        if (strings[0].equalsIgnoreCase("join")) {
            if (strings.length == 2) {
                MobNinjaGame game = new MobNinjaGame(strings[1]);
                if (game.exists()) {
                    
                } else {
                    cs.sendMessage(Utilities.getPrefix() + "That game does not exist!");
                }
            }
        }
        return true;
    }

}
