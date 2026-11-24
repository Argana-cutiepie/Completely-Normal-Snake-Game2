package com.mycompany.snakegame.model;

import java.util.Objects;

public abstract class GameObject implements Drawable {

    private Cell position;

    protected GameObject(Cell position) {
        this.position = Objects.requireNonNull(position);
    }

    public Cell getPosition() {
        return position;
    }

    protected void setPosition(Cell position) {
        this.position = Objects.requireNonNull(position);
    }

    public boolean isAt(Cell cell) {
        return position.equals(cell);
    }
}
