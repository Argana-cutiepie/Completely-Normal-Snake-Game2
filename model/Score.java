package com.mycompany.snakegame.model;

public record Score(int id, String player, int points, String difficulty, String playedAt) {

    public Score {
        if (points < 0) {
            throw new IllegalArgumentException("Points cannot be negative.");
        }
    }
}
