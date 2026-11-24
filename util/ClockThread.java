package com.mycompany.snakegame.util;

import java.util.function.IntConsumer;

/**
 * Background stopwatch for the play time.
 *
 * MODULE 7 PROOF (Threads): extends Thread, overrides run(), uses sleep(), and stops
 * COOPERATIVELY through interrupt() (never the deprecated Thread.stop()).
 * Lifecycle: NEW (constructor) -> RUNNABLE (start) -> TIMED_WAITING (sleep) -> TERMINATED.
 * MODULE 5 PROOF: IntConsumer is a functional interface - the caller passes a lambda.
 */
public class ClockThread extends Thread {

    private final IntConsumer onTick;
    private final Object lock = new Object();
    private int seconds;
    private volatile boolean counting;

    public ClockThread(IntConsumer onTick) {
        super("snake-clock");
        setDaemon(true);
        this.onTick = onTick;
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                Thread.sleep(1000);
                if (counting) {
                    int now;
                    synchronized (lock) {
                        now = ++seconds;
                    }
                    onTick.accept(now);
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();   // keep the interrupt flag, then leave run()
        } finally {
            System.out.println("[ClockThread] stopped");
        }
    }

    public void resetSeconds() {
        synchronized (lock) {
            seconds = 0;
        }
    }

    public void setCounting(boolean counting) {
        this.counting = counting;
    }
}
