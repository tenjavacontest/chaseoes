package com.chaseoes.mobninja.utilities;

import org.bukkit.Location;

import com.chaseoes.mobninja.MobNinja;

public class SerializableLocation {
    
    public static String serializeLocation(Location location) {
        String world = location.getWorld().getName();
        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();
        float pitch = location.getPitch();
        float yaw = location.getYaw();
        System.out.println("Yaw: " + yaw);
        System.out.println("Float: " + pitch);
        return world + ":" + x + ":" + y + ":" + z + ":" + pitch + ":" + yaw;
    }
    
    public static Location unSerializeLocation(String string) {
        String[] coords = string.split(":");
        System.out.println("Yaw: " + coords[5]);
        System.out.println("Float: " + coords[4]);
        return new Location(MobNinja.getInstance().getServer().getWorld(coords[0]), Integer.parseInt(coords[1]), Integer.parseInt(coords[2]), Integer.parseInt(coords[3]), Float.parseFloat(coords[5]), Float.parseFloat(coords[4]));
    }

}
