package com.mycompany.snakegame.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Central place for constants and tunable settings.
 *
 * MODULE 1 PROOF (program structure): a class with static members, no main().
 * MODULE 2 PROOF: constants use 'final' with UPPER_SNAKE_CASE; the Scanner reads
 * text and String.split / Integer.parseInt convert it into numbers.
 * MODULE 5 PROOF: a TEXT BLOCK holds the default settings and the wrapper class
 * Integer is used for the map values (autoboxing / unboxing).
 * MODULE 6 PROOF: HashMap stores key -> value settings.
 */
public final class GameConfig {

    public static final int COLUMNS = 24;
    public static final int ROWS = 24;
    public static final int CELL = 25;
    public static final int SCREEN_WIDTH = COLUMNS * CELL;    // 600 (arithmetic operator)
    public static final int SCREEN_HEIGHT = ROWS * CELL;      // 600
    public static final int START_LENGTH = 4;
    public static final String PLAYER_NAME_PATTERN = "[A-Za-z0-9_ ]{1,12}";

    private static final String DEFAULTS = """
            normalPoints=1
            goldenPoints=3
            goldenChance=20
            """;

    private static final Map<String, Integer> VALUES = load();

    private GameConfig() {
        // utility class: no objects needed
    }

    private static Map<String, Integer> load() {
        Map<String, Integer> map = new HashMap<>();
        try (Scanner sc = new Scanner(DEFAULTS)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }
                String[] parts = line.split("=");              // array of String
                map.put(parts[0], Integer.parseInt(parts[1])); // autoboxing int -> Integer
            }
        }
        return map;
    }

    public static int get(String key) {
        return VALUES.getOrDefault(key, 0);                    // unboxing Integer -> int
    }
}
