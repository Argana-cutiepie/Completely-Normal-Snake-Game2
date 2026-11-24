package com.mycompany.snakegame.ui;

import com.mycompany.snakegame.engine.GameEngine;

/**
 * MODULE 4 PROOF (Interface with default methods): the panel reports events through this
 * contract; a listener only overrides the events it cares about.
 * MODULE 7 PROOF (Event-driven design): the game panel does not know the window exists.
 */
public interface GameListener {

    default void onNewGame() { }

    default void onScoreChanged(int score) { }

    default void onStateChanged(GameEngine.State state) { }

    default void onGameEnded(int score) { }
}
