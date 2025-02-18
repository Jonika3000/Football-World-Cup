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
}