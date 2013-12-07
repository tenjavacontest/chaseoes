package com.chaseoes.mobninja;

import org.bukkit.entity.Player;

public class GameUtilities {
    
    public static boolean playerIsIngame(Player player) {
        return getCurrentGame(player) != null;
    }
    
    public static MobNinjaGame getCurrentGame(Player player) {
        for (MobNinjaGame game : MobNinja.getInstance().getGames()) {
            if (game.getPlayersInGame().contains(player.getName())) {
                return game;
            }
        }
        return null;
    }

}
