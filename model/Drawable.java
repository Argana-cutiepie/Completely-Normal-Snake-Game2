package com.mycompany.snakegame.model;

import java.awt.Graphics2D;

/**
 * MODULE 4 PROOF (Interfaces): a contract that says "I know how to draw myself".
 * Unrelated classes can promise this behavior without sharing a superclass.
 */
public interface Drawable {
    void draw(Graphics2D g, int cellSize);
}
