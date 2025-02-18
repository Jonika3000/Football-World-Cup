package org.example.models;

public class Game {
    public Team homeTeam;
    public Team awayTeam;
    public int homeScore = 0;
    public int awayScore = 0;

    public Game(Team homeTeam, Team awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }
}
