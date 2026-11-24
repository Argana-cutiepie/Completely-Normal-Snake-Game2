package com.mycompany.snakegame.model;

import com.mycompany.snakegame.util.GameConfig;
import java.awt.Color;

public class NormalFood extends Food {

    public NormalFood(Cell position) {
        super(position);
    }

    @Override
    public int getPoints() {
        return GameConfig.get("normalPoints");
    }

    @Override
    public Color getColor() {
        return new Color(205, 25, 35);
    }
}
