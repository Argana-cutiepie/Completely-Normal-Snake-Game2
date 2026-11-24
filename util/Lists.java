package com.mycompany.snakegame.util;

import java.util.ArrayList;
import java.util.List;

public final class Lists {

    private Lists() {
    }

    public static <T> List<T> topN(List<T> items, int n) {
        return new ArrayList<>(items.subList(0, Math.min(n, items.size())));
    }
}
