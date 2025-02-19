package org.football_world_cap.service;

import org.football_world_cap.exception.ScoreBoardException;
import org.football_world_cap.model.Team;

import java.util.Scanner;

public class ControlPanelService {
    public static void play () {
        GameStorage gameStorage = new GameStorage();
        ScoreBoardService scoreBoard = new ScoreBoardService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            int choice = OutputService.printMenu(scanner);
            switch (choice) {
                case 1 -> {
                    try {
                        Team homeTeam = OutputService.menuCreateTeam(scanner, "home");
                        Team awayTeam = OutputService.menuCreateTeam(scanner, "away");
                        scoreBoard.startNewGame(homeTeam, awayTeam);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 2 -> {
                    int homeScore = OutputService.menuAddScore(scanner, "home");
                    int awayScore = OutputService.menuAddScore(scanner, "away");
                    scanner.nextLine();
                    try {
                        scoreBoard.updateGameScore(homeScore, awayScore);
                    } catch (ScoreBoardException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 3 -> {
                    System.out.print("Game will be finished.");
                    try {
                        scoreBoard.finishGame(gameStorage);
                    } catch (ScoreBoardException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 4 -> {
                    System.out.println("\nGame Summary:");
                    gameStorage.getSummary().forEach(System.out::println);
                }
                case 5 -> {
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
