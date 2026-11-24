package com.mycompany.snakegame.util;

import java.util.ArrayList;
import java.util.List;

/**
 * MODULE 5 PROOF (Generics): a generic METHOD with type parameter T. It works for
 * a List of Score, String, Integer ... and the compiler checks the types.
 */
public final class Lists {

    private Lists() {
    }

    public static <T> List<T> topN(List<T> items, int n) {
        return new ArrayList<>(items.subList(0, Math.min(n, items.size())));
    }
}
