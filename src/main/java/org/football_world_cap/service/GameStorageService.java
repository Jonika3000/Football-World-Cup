package org.football_world_cap.service;

import org.football_world_cap.model.Game;

import java.util.ArrayList;
import java.util.List;

public class GameStorage {
    private List<Game> finishedGames = new ArrayList<>();

    public void addFinishedGame(Game game) {
        finishedGames.add(game);
    }

    public List<Game> getSummary() {
        return finishedGames.stream()
                .sorted((g1, g2) -> {
                    int scoreComparison = Integer.compare(g2.getTotalScore(), g1.getTotalScore());
                    return scoreComparison != 0 ? scoreComparison : Long.compare(g2.getTimestamp(), g1.getTimestamp());
                })
                .toList();
    }
}
