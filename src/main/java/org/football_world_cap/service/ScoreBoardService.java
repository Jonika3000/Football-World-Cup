package org.football_world_cap.service;

import org.football_world_cap.exception.ControlPanelException;
import org.football_world_cap.exception.ScoreBoardException;
import org.football_world_cap.validator.Validator;
import org.football_world_cap.model.Game;
import org.football_world_cap.model.Team;

public class ScoreBoardService {
    private Game activeGame = null;

    public void startNewGame (Team homeTeam, Team awayTeam) throws ControlPanelException {
        if (null != activeGame) {
            throw new ControlPanelException("Finish the current game to start a new one");
        }
        activeGame = new Game(homeTeam, awayTeam);
    }

    public void updateGameScore (int scoreHomeTeam, int scoreAwayTeam) throws ScoreBoardException {
        if (null == activeGame) throw new ScoreBoardException("No active game");

        if(Validator.validScore(scoreHomeTeam) || Validator.validScore(scoreAwayTeam)) {
            throw new ScoreBoardException("Invalid score");
        }
        activeGame.setHomeScore(activeGame.getHomeScore() + scoreHomeTeam);
        activeGame.setAwayScore(activeGame.getAwayScore() + scoreAwayTeam);
    }

    public void finishGame(GameStorageService storage) throws ScoreBoardException {
        if (null == activeGame) throw new ScoreBoardException("Game not found");
        storage.addFinishedGame(activeGame);
        activeGame = null;
    }
}