package org.football_world_cap.service;

import org.football_world_cap.exception.ControlPanelException;
import org.football_world_cap.exception.ScoreBoardException;
import org.football_world_cap.model.Game;
import org.football_world_cap.model.Team;

import java.util.Scanner;

public class ControlPanelService {
    public static void play () {
        ScoreBoardService scoreBoard = new ScoreBoardService();
        Scanner scanner = new Scanner(System.in);
        Game currentGame = null;

        while (true) {
            int choice = OutputService.printMenu(scanner);
            switch (choice) {
                case 1 -> {
                    try {
                        if(currentGame != null) {
                            throw new ControlPanelException("Finish the current game to start a new one");
                        }
                        Team homeTeam = OutputService.menuCreateTeam(scanner, "home");
                        Team awayTeam = OutputService.menuCreateTeam(scanner, "away");
                        currentGame = scoreBoard.startNewGame(homeTeam, awayTeam);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 2 -> {
                    int homeScore = OutputService.menuAddScore(scanner, "home");
                    int awayScore = OutputService.menuAddScore(scanner, "away");
                    scanner.nextLine();
                    try {
                        currentGame = scoreBoard.updateGameScore(currentGame, homeScore, awayScore);
                    } catch (ScoreBoardException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 3 -> {
                    System.out.print("Game will be finished.");
                    currentGame = null;
                }
                case 4 -> {
                    System.out.println("\nGame Summary:");
                    scoreBoard.getSummary().forEach(System.out::println);
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
