package org.football_world_cap.service;

import org.football_world_cap.exception.ControlPanelException;
import org.football_world_cap.model.Team;
import org.football_world_cap.validator.Validator;

import java.util.Scanner;

public class InputService {
    private static Scanner scanner = new Scanner(System.in);

    public static String printMenu () {
        System.out.println("\nFootball Scoreboard Menu:");
        System.out.println("1. Start New Game");
        System.out.println("2. Update Score");
        System.out.println("3. Finish Game");
        System.out.println("4. Show Summary");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");

        return scanner.nextLine();
    }

    public static Team menuCreateTeam (String team) throws ControlPanelException {
        System.out.printf("Enter %s team: ", team);
        String teamName = scanner.nextLine();

        if(!Validator.isValidName(teamName))
            throw new ControlPanelException("Team name is invalid");

        return new Team(teamName);
    }

    public static int menuAddScore (String teamName) {
        System.out.printf("Enter how much add to %s team score: ", teamName);
        int score = scanner.nextInt();
        scanner.nextLine();

        return score;
    }
}
