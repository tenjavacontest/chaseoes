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
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class MobNinja extends JavaPlugin implements Listener {
    
    private static MobNinja instance;
    
    public static MobNinja getInstance() {
        return instance;
    }
	
    public void onEnable() {
        instance = this;
        getServer().getPluginManager().registerEvents(this, this);
    }

    public void onDisable() {
        instance = null;
    }

    public boolean onCommand(CommandSender cs, Command cmnd, String string, String[] strings) {
        
        return true;
    }
    
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.hasBlock() && event.getClickedBlock().getType() == Material.EMERALD_BLOCK) {
            launch(event.getPlayer());
        }
    }
    
    public void launch(Player p) {
        Entity entity = p.getLocation().getWorld().spawnEntity(p.getLocation().add(12, -20, 0), EntityType.VILLAGER);
        Vector entityVelocity = entity.getVelocity();
        double launchSpeed = 2.5;
        
        entityVelocity.setY(launchSpeed);
        entity.setVelocity(entityVelocity);
        
        ItemStack bow = new ItemStack(Material.BOW, 1);
        bow.addEnchantment(Enchantment.ARROW_INFINITE, 1);
        bow.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 10);
        if (!p.getInventory().contains(Material.BOW)) {
            p.getInventory().clear();
            p.getInventory().addItem(bow);
        }
    }

}
