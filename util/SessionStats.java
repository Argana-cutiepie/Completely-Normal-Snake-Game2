package com.mycompany.snakegame.util;

public class SessionStats {

    private int gamesPlayed;
    private int bestScore;
    private int savedScores;

    public synchronized void recordGame(int points) {
        gamesPlayed++;
        if (points > bestScore) {
            bestScore = points;
        }
    }

    public synchronized void recordSaved() {
        savedScores++;
    }

    public synchronized int getBestScore() {
        return bestScore;
    }

    public synchronized String summary() {
        return String.format("Session: %d game(s), best %d, %d score(s) saved",
                gamesPlayed, bestScore, savedScores);
    }
}
