package com.example.sapernewvers.gameLogic;

import android.os.Handler;
import android.os.SystemClock;
import android.widget.EditText;

public class Timer {
    private long startTime = 0;
    private boolean isRunning = false;
    private final Handler timerHandler = new Handler();
    private final long UPDATE_INTERVAL = 500;
    public EditText timer;

    public Timer(EditText timerText) {
        this.timer = timerText;
    }

    public void startStopWatch() {
        startTime = SystemClock.elapsedRealtime();
        isRunning = true;
        timerHandler.post(updateTimerRunnable);
    }

    public long stopStopwatch() {
        if (isRunning) {
            isRunning = false;
            timerHandler.removeCallbacks(updateTimerRunnable);
            return SystemClock.elapsedRealtime() - startTime;
        }
        return 0;
    }
    public static String formatTime(long millis) {
        int seconds = (int) (millis / 1000) % 60;
        int minutes = (int) ((millis / (1000 * 60)) % 60);
        return String.format("%02d:%02d", minutes, seconds);
    }
    private final Runnable updateTimerRunnable = new Runnable() {
        public void run() {
            if (isRunning) {
                long currentTime = getCurrentTime();
                timer.setText(formatTime(currentTime));
                timerHandler.postDelayed(this, UPDATE_INTERVAL);
            }
        }
    };

    private long getCurrentTime() {
        if (isRunning) {
            return SystemClock.elapsedRealtime() - startTime;
        }
        return 0;
    }
}
