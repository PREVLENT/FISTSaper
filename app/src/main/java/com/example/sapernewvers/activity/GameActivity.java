package com.example.sapernewvers.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.example.sapernewvers.gameLogic.GameEngine;
import com.example.sapernewvers.R;
import com.example.sapernewvers.gameLogic.Timer;
import com.example.sapernewvers.util.TimerStorage;

public class GameActivity extends Activity {
    private Button exitBut;
    private EditText timerText;
    private Timer timer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GameEngine.getInstance().createGrid(this);

        exitBut = (Button) findViewById(R.id.exitBut);

        exitBut.setOnClickListener(v -> {
            exitToMainMenu();
        });

        timerText = (EditText) findViewById(R.id.editScore);

        timer = new Timer(timerText);

        timer.startStopWatch();
    }

    public void openGameOverMenu() {
        Intent intent = new Intent(GameActivity.this, GameOverActivity.class);
        startActivity(intent);
        long elapsedTime = timer.stopStopwatch();
    }

    public void openGameWinMenu() {
        long elapsedTime = timer.stopStopwatch();
        Intent intent = new Intent(GameActivity.this, GameWinActivity.class);
        TimerStorage.saveTime(elapsedTime);
        startActivity(intent);
    }

    public void exitToMainMenu() {
        Intent intent = new Intent(GameActivity.this, MainMenuActivity.class);
        startActivity(intent);
    }
}