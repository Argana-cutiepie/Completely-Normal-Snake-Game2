package com.mycompany.snakegame.model;

/**
 * Game speed levels.
 * MODULE 5 PROOF (Enums can carry behavior): each constant stores a label and a
 * timer delay, and the class has a static lookup method.
 */
public enum Difficulty {
    EASY("Easy", 140),
    NORMAL("Normal", 100),
    HARD("Hard", 70);

    private final String label;
    private final int delayMs;

    Difficulty(String label, int delayMs) {
        this.label = label;
        this.delayMs = delayMs;
    }

    public int getDelayMs() { return delayMs; }

    /** MODULE 2 PROOF: for loop + method with a parameter and a return value. */
    public static Difficulty fromLabel(String text) {
        for (Difficulty d : values()) {
            if (d.label.equalsIgnoreCase(text)) {
                return d;
            }
        }
        return NORMAL;
    }

    /** Used by JComboBox and by the database (the label is what gets stored). */
    @Override
    public String toString() {
        return label;
    }
}
