package org.football_world_cap.service;

import org.football_world_cap.exception.ScoreBoardException;

public class ControlPanelService {
    private GameStorageService gameStorage = null;
    private ScoreBoardService scoreBoard = null;

    public void play () {
        gameStorage = new GameStorageService();
        scoreBoard = new ScoreBoardService();

        while (true) {
            String choice = InputService.printMenu();

            switch (choice) {
                case "1" -> {
                    startGameAction();
                }
                case "2" -> {
                    updateScoreAction();
                }
                case "3" -> {
                    finishGameAction();
                }
                case "4" -> {
                    showSummaryAction();
                }
                case "5" -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void startGameAction () {
        try {
            scoreBoard.startNewGame();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateScoreAction () {
        try {
            scoreBoard.updateGameScore();
        } catch (ScoreBoardException e) {
            System.out.println(e.getMessage());
        }
    }

    private void finishGameAction () {
        try {
            scoreBoard.finishGame(gameStorage);
            System.out.print("Game will be finished.");
        } catch (ScoreBoardException e) {
            System.out.println(e.getMessage());
        }
    }

    private void showSummaryAction () {
        System.out.println("\nGame Summary:");
        gameStorage.getSummary().forEach(System.out::println);
    }
}
