package com.mycompany.snakegame.util;

import com.mycompany.snakegame.exception.DatabaseException;
import com.mycompany.snakegame.exception.InvalidPlayerNameException;
import com.mycompany.snakegame.model.Difficulty;
import com.mycompany.snakegame.service.ScoreService;
import java.util.function.Consumer;

/**
 * Saves a score to the database on its own thread so the game window never freezes.
 *
 * MODULE 7 PROOF (Runnable): the TASK (this class) is separate from the THREAD that
 * runs it: new Thread(new ScoreSaver(...)).start().
 * MODULE 5 PROOF: multi-catch, finally, and a Consumer lambda callback.
 * MODULE 8 PROOF: calls the service/DAO layer, never SQL directly.
 */
public class ScoreSaver implements Runnable {

    private final ScoreService service;
    private final String player;
    private final int points;
    private final Difficulty difficulty;
    private final SessionStats stats;
    private final Consumer<String> onDone;

    public ScoreSaver(ScoreService service, String player, int points,
                      Difficulty difficulty, SessionStats stats, Consumer<String> onDone) {
        this.service = service;
        this.player = player;
        this.points = points;
        this.difficulty = difficulty;
        this.stats = stats;
        this.onDone = onDone;
    }

    @Override
    public void run() {
        try {
            service.save(player, points, difficulty);
            stats.recordSaved();
            onDone.accept("Saved " + points + " point(s) for " + player + ".");
        } catch (InvalidPlayerNameException | DatabaseException e) {
            onDone.accept("Could not save score: " + e.getMessage());
        } finally {
            System.out.printf("[ScoreSaver] finished for %s on thread %s%n",
                    player, Thread.currentThread().getName());
        }
    }
}
