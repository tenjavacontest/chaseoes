package com.chaseoes.mobninja;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import com.chaseoes.mobninja.utilities.SerializableLocation;

public class GameTask extends BukkitRunnable {

    MobNinjaGame game;

    public GameTask(MobNinjaGame game) {
        this.game = game;
    }

    @Override
    public void run() {
        for (String p : game.getPlayersInGame()) {
            Player player = MobNinja.getInstance().getServer().getPlayerExact(p);

            ItemStack bow = new ItemStack(Material.BOW, 1);
            bow.addEnchantment(Enchantment.ARROW_INFINITE, 1);
            bow.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 10);
            if (!player.getInventory().contains(Material.BOW)) {
                player.getInventory().clear();
                player.getInventory().addItem(bow);
            }

            int i = 0;
            while (i != game.getSpawns().size()) {
                launch(SerializableLocation.unSerializeLocation(game.getSpawns().get(i)));
                i++;
            }
//
//            for (double t = 0; t < 2 * Math.PI; t += 1) {
//                Location l = player.getLocation().add(-7 * Math.cos(t), 0, -7 * Math.sin(t));
//                launch(l);
//            }
        }
    }

    public static void launch(Location l) {
        Entity entity = l.getWorld().spawnEntity(l, EntityType.VILLAGER);
        Vector entityVelocity = entity.getVelocity();
        double launchSpeed = 2.6;

        entityVelocity.setY(launchSpeed);
        entity.setVelocity(entityVelocity);
    }

}
