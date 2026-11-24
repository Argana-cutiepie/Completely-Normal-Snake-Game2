package com.mycompany.snakegame.model;

/**
 * One row of the SCORE table.
 *
 * MODULE 5 PROOF (Records): immutable data carrier with a compact constructor
 * that validates its input.
 * MODULE 8 PROOF: this is the domain object the DAO maps ResultSet rows into.
 */
public record Score(int id, String player, int points, String difficulty, String playedAt) {

    public Score {
        if (points < 0) {
            throw new IllegalArgumentException("Points cannot be negative.");
        }
    }
}
