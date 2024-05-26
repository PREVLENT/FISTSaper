package com.example.sapernewvers.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.sapernewvers.R;

public class GameOverActivity extends Activity {

    private Button restBut;
    private Button menuBut;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_over);

        restBut = (Button) findViewById(R.id.sendBut);

        restBut.setOnClickListener(v -> {
            restGame();
        });
        menuBut = (Button) findViewById(R.id.menuBut);

        menuBut.setOnClickListener(v -> {
            openMainMenu();
        });
    }

    public void restGame() {
        Intent intent = new Intent(GameOverActivity.this, GameActivity.class);
        startActivity(intent);
    }
    public void openMainMenu() {
        Intent intent = new Intent(GameOverActivity.this, MainMenuActivity.class);
        startActivity(intent);
    }
}
