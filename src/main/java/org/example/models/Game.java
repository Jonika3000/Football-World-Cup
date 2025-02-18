package org.example.models;

public class Game {
    private Team homeTeam;
    private Team awayTeam;
    private int homeScore = 0;
    private int awayScore = 0;
    private final long timestamp;

    public Game(Team homeTeam, Team awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.timestamp = System.currentTimeMillis();
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(int awayScore) {
        this.awayScore = awayScore;
    }

    @Override
    public String toString() {
        return  homeTeam.getName() + " - " + awayTeam.getName() + ": " + homeScore + " " + awayScore;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public int getTotalScore() {
        return homeScore + awayScore;
    }
}
