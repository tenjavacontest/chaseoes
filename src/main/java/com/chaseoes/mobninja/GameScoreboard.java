package com.chaseoes.mobninja;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class GameScoreboard {
    
    MobNinjaGame game;
    ScoreboardManager manager;
    Scoreboard board;
    Objective objective;
    
    public GameScoreboard(MobNinjaGame game) {
        this.game = game;
        manager = MobNinja.getInstance().getServer().getScoreboardManager();
        board = manager.getNewScoreboard();
        objective = board.registerNewObjective("mobninja", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(ChatColor.GREEN + "Mob Ninja");
    }
    
    public void update() {
        for (String p : game.getPlayersInGame()) {
            Player player = MobNinja.getInstance().getServer().getPlayerExact(p);
            NinjaPlayer np = game.getNinjaPlayers().get(player.getName());
            Score score = objective.getScore(player);
            score.setScore(np.getKills());
            
            if (score.getScore() == 25) {
                game.winGame(player);
            }
        }
    }
    
    public void clear() {
        for (String p : game.getPlayersInGame()) {
            Player player = MobNinja.getInstance().getServer().getPlayerExact(p);
            board.resetScores(player);
        }
    }
    
    public Scoreboard getScoreboard() {
        return board;
    }

}
