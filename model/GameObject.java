package com.mycompany.snakegame.model;

import java.util.Objects;

/**
 * Base class of everything that sits on the board.
 *
 * MODULE 4 PROOF (Abstraction): abstract class - it cannot be instantiated and
 * leaves draw() for subclasses to implement.
 * MODULE 3 PROOF (Encapsulation): the position is private; only getters and a
 * protected setter expose it.
 * MODULE 3 PROOF (is-a relationship): Snake is-a GameObject, Food is-a GameObject.
 */
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
