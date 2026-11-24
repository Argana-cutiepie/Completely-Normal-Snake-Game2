package com.mycompany.snakegame.model;

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

    public static Difficulty fromLabel(String text) {
        for (Difficulty d : values()) {
            if (d.label.equalsIgnoreCase(text)) {
                return d;
            }
        }
        return NORMAL;
    }

    @Override
    public String toString() {
        return label;
    }
}
