package com.example.sapernewvers.util;

import com.example.sapernewvers.gameLogic.DifficultLevel;

public class DifficultStorage {
    private static DifficultLevel difficultLevel;
    private static boolean isInit = false;

    public static void save(DifficultLevel newDifficultLevel) {
        difficultLevel = newDifficultLevel;
        isInit = true;
    }

    public static DifficultLevel get() {
        if (!isInit) {
            throw new RuntimeException("DifficultStorage is not init");
        }
        return difficultLevel;
    }
}
