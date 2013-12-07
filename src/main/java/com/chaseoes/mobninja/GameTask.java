package com.chaseoes.mobninja;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class GameTask extends BukkitRunnable {

    MobNinjaGame game;

    public GameTask(MobNinjaGame game) {
        this.game = game;
    }

    @Override
    public void run() {
        for (String p : game.getPlayersInGame()) {
            Player player = MobNinja.getInstance().getServer().getPlayerExact(p);
            launch(player);
        }
    }

    public static void launch(Player p) {
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
