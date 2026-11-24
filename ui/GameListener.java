package com.mycompany.snakegame.ui;

import com.mycompany.snakegame.engine.GameEngine;

public interface GameListener {

    default void onNewGame() { }

    default void onScoreChanged(int score) { }

    default void onStateChanged(GameEngine.State state) { }

    default void onGameEnded(int score) { }
}
