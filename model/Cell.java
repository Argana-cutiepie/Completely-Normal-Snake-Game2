package com.mycompany.snakegame.model;

public record Cell(int col, int row) {

    public Cell step(Direction direction) {
        return new Cell(col + direction.dx(), row + direction.dy());
    }
}
