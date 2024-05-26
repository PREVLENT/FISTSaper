package com.example.sapernewvers.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.sapernewvers.R;
import com.example.sapernewvers.gameLogic.Timer;
import com.example.sapernewvers.util.TimerStorage;

public class MainMenuActivity extends Activity {
    private Button startButton;
    private Button topMenuButton;
    private TextView nameText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        Log.e("MainMenuActivity", "onCreate");

        nameText = (TextView) findViewById(R.id.nameText);

        startButton = (Button) findViewById(R.id.startButton);

        startButton.setOnClickListener(v -> {
            openGameActivity();
        });

        topMenuButton = (Button) findViewById(R.id.topButton);

        topMenuButton.setOnClickListener(v -> {
            openTopActivity();
        });

        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String nickName = preferences.getString("nickName", "");
        nameText.setText(nickName);
    }

    public void openGameActivity() {
        Intent intent = new Intent(MainMenuActivity.this, DifficultActivity.class);
        startActivity(intent);
    }

    public void openTopActivity() {
        Intent intent = new Intent(MainMenuActivity.this, TopActivity.class);
        startActivity(intent);
    }
}
