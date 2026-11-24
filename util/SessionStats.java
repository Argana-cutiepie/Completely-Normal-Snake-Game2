package com.mycompany.snakegame.util;

/**
 * Counters shared by the Swing thread (EDT) and the background score-saver thread.
 *
 * MODULE 7 PROOF (Synchronization): every method that reads or writes the shared
 * fields is 'synchronized', so two threads cannot interleave "read - add 1 - write"
 * and lose an update (a race condition).
 */
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
