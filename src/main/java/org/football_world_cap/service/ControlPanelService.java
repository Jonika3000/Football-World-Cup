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
            System.out.println("\nFootball Scoreboard Menu:");
            System.out.println("1. Start New Game");
            System.out.println("2. Update Score");
            System.out.println("3. Finish Game");
            System.out.println("4. Show Summary");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    try {
                        if(currentGame != null) {
                            throw new ControlPanelException("Finish the current game to start a new one");
                        }
                        System.out.print("Enter home team: ");
                        Team homeTeam = new Team(scanner.nextLine());
                        System.out.print("Enter away team: ");
                        Team awayTeam = new Team(scanner.nextLine());
                        currentGame = scoreBoard.startNewGame(homeTeam, awayTeam);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 2 -> {
                    System.out.print("Enter how much add to home team score: ");
                    int homeScore = scanner.nextInt();
                    System.out.print("Enter how much add to away team score: ");
                    int awayScore = scanner.nextInt();
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
