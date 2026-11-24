package com.mycompany.snakegame.model;

/**
 * MODULE 5 PROOF (Enums): a closed set of named values that also carries data
 * (dx, dy) and behavior (opposite()).
 * MODULE 5 PROOF (Enhanced switch): opposite() is a switch EXPRESSION with arrows.
 */
public enum Direction {
    UP(0, -1), DOWN(0, 1), LEFT(-1, 0), RIGHT(1, 0);

    private final int dx;   // MODULE 3 PROOF: private fields = encapsulation
    private final int dy;

    Direction(int dx, int dy) {
        this.dx = dx;       // MODULE 3 PROOF: 'this' separates field from parameter
        this.dy = dy;
    }

    public int dx() { return dx; }

    public int dy() { return dy; }

    public Direction opposite() {
        return switch (this) {
            case UP -> DOWN;
            case DOWN -> UP;
            case LEFT -> RIGHT;
            case RIGHT -> LEFT;
        };
    }
}
