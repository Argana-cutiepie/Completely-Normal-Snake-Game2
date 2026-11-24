package com.mycompany.snakegame.model;

import com.mycompany.snakegame.util.GameConfig;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 * MODULE 4 PROOF (Polymorphism): the engine holds a plain Food reference. At run
 * time Java dispatches draw()/getPoints() to this class when the object is golden
 * (dynamic method dispatch).
 */
public class GoldenFood extends Food {

    public GoldenFood(Cell position) {
        super(position);
    }

    @Override
    public int getPoints() {
        return GameConfig.get("goldenPoints");
    }

    @Override
    public Color getColor() {
        return new Color(245, 190, 20);
    }

    @Override
    public String getLabel() {
        return "Golden apple";
    }

    @Override
    public void draw(Graphics2D g, int cell) {
        super.draw(g, cell);   // MODULE 4 PROOF: super.method() reuses the parent's drawing
        int x = getPosition().col() * cell;
        int y = getPosition().row() * cell;
        g.setColor(Color.WHITE);                                  // little sparkle
        g.drawLine(x + 4, y + 4, x + 10, y + 10);
        g.drawLine(x + 10, y + 4, x + 4, y + 10);
    }
}
