package com.chaseoes.mobninja;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import com.chaseoes.mobninja.utilities.SerializableLocation;

public class GameTask extends BukkitRunnable {

    MobNinjaGame game;
    int i = 0;

    public GameTask(MobNinjaGame game) {
        this.game = game;
    }

    @Override
    public void run() {
        while (i != game.getSpawns().size()) {
            System.out.println("SPAWNING");
            Location loc = SerializableLocation.unSerializeLocation(game.getSpawns().get(i));
            loc.add(0.5, 0, 0.5);
            launch(loc);
            i++;

            if (i == game.getSpawns().size()) {
                System.out.println("i = 0");
                i = 0;
            }
            return;
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
