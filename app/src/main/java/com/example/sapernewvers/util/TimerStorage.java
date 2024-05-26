package com.example.sapernewvers.util;

public class TimerStorage {
    private static long time;

    public static void saveTime(long newTime) {
        time = newTime;
    }

    public static long getTime() {
        return time;
    }
    public static long getTimeInSecond(){
        return (time / 1000) % 60;
    }
}
