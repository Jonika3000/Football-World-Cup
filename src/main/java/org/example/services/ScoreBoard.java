package org.example.services;

import org.example.exception.ScoreBoardException;
import org.example.models.Game;
import org.example.models.Team;

import java.util.ArrayList;

public class ScoreBoard {
    private ArrayList<Game> games = new ArrayList<>();

    public Game startNewGame (Team homeTeam, Team awayTeam) throws ScoreBoardException {
        var game = new Game(homeTeam, awayTeam);
        if (games.contains(game))
            throw new ScoreBoardException("Game already exists");

        games.add(game);
        return game;
    }

    public Game updateGameScore (Game game, int scoreHomeTeam, int scoreAwayTeam) throws ScoreBoardException {
        var updatedGame = games.stream()
                .filter(gameFilter -> gameFilter.getAwayTeam().equals(game.getAwayTeam())
                        && gameFilter.getHomeTeam().equals(game.getHomeTeam()))
                .findFirst()
                .orElseThrow(() -> new ScoreBoardException("Game not found"));
        if(validScore(scoreHomeTeam) || validScore(scoreAwayTeam)) {
            throw new ScoreBoardException("Invalid score");
        }
        var updatedHomeScore = updatedGame.getHomeScore()+scoreHomeTeam;
        var updatedAwayScore = updatedGame.getAwayScore()+scoreAwayTeam;
        updatedGame.setHomeScore(updatedHomeScore);
        updatedGame.setAwayScore(updatedAwayScore);

        return updatedGame;
    }

    private boolean validScore (int score) {
        return score < 0;
    }

    public ArrayList<Game> getSummary () {
        return games;
    }
}