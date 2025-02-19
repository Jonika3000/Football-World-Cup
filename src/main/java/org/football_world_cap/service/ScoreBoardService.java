package org.football_world_cap.service;

import org.football_world_cap.exception.ControlPanelException;
import org.football_world_cap.exception.ScoreBoardException;
import org.football_world_cap.validator.Validator;
import org.football_world_cap.model.Game;
import org.football_world_cap.model.Team;

import java.util.Scanner;

public class ScoreBoardService {
    private Game activeGame = null;
    private final Scanner scanner = new Scanner(System.in);

    public void startNewGame () throws ControlPanelException {
        Team homeTeam = InputService.menuCreateTeam("home");
        Team awayTeam = InputService.menuCreateTeam("away");
        if (null != activeGame) {
            throw new ControlPanelException("Finish the current game to start a new one");
        }
        activeGame = new Game(homeTeam, awayTeam);
    }

    public void updateGameScore () throws ScoreBoardException {
        if (null == activeGame) {
            throw new ScoreBoardException("No active game");
        }
        int scoreHomeTeam = InputService.menuAddScore("home");
        int scoreAwayTeam = InputService.menuAddScore("away");

        if(Validator.validScore(scoreHomeTeam) || Validator.validScore(scoreAwayTeam)) {
            throw new ScoreBoardException("Invalid score");
        }

        activeGame.setHomeScore(activeGame.getHomeScore() + scoreHomeTeam);
        activeGame.setAwayScore(activeGame.getAwayScore() + scoreAwayTeam);
    }

    public void finishGame(GameStorageService storage) throws ScoreBoardException {
        if (null == activeGame) {
            throw new ScoreBoardException("There are no active games.");
        }
        storage.addFinishedGame(activeGame);
        activeGame = null;
    }
}