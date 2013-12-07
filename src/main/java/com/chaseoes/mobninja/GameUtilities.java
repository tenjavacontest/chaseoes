package com.chaseoes.mobninja;

import org.bukkit.entity.Player;

public class GameUtilities {
    
    public static boolean playerIsIngame(Player player) {
        return false;
    }
    
    public static MobNinjaGame getCurrentGame(Player player) {
        return new MobNinjaGame("a");
    }

}
