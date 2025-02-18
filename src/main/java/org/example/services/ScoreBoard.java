package org.example.services;

import org.example.exceptions.ScoreBoardException;
import org.example.helpers.Validator;
import org.example.models.Game;
import org.example.models.Team;

import java.util.ArrayList;
import java.util.List;

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
        if(Validator.validScore(scoreHomeTeam) || Validator.validScore(scoreAwayTeam)) {
            throw new ScoreBoardException("Invalid score");
        }
        var updatedHomeScore = updatedGame.getHomeScore()+scoreHomeTeam;
        var updatedAwayScore = updatedGame.getAwayScore()+scoreAwayTeam;
        updatedGame.setHomeScore(updatedHomeScore);
        updatedGame.setAwayScore(updatedAwayScore);

        return updatedGame;
    }


    public List<Game> getSummary () {
        return games.stream()
                .sorted((g1, g2) -> {
                    int scoreComparison = Integer.compare(g2.getTotalScore(), g1.getTotalScore());
                    if (scoreComparison == 0) {
                        return Long.compare(g2.getTimestamp(), g1.getTimestamp());
                    }
                    return scoreComparison;
                })
                .toList();
    }
}