package com.example.sapernewvers.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.sapernewvers.gameLogic.DifficultLevel;
import com.example.sapernewvers.R;
import com.example.sapernewvers.util.DifficultStorage;

public class DifficultActivity extends Activity {
    private Button easy;
    private Button middle;
    private Button hard;
    private Button backButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.difficult);

        easy = findViewById(R.id.easyBut);

        easy.setOnClickListener(v -> {
            openGameActivity(DifficultLevel.EASY);
        });

        middle = findViewById(R.id.middleBut);

        middle.setOnClickListener(v -> {
            openGameActivity(DifficultLevel.MIDDLE);
        });

        hard = findViewById(R.id.hardBut);

        hard.setOnClickListener(v -> {
            openGameActivity(DifficultLevel.HARD);
        });

        backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(v -> {
            backMenuActivity();
        });
    }

    public void openGameActivity(DifficultLevel difficultLevel) {
        Intent intent = new Intent(DifficultActivity.this, GameActivity.class);
        DifficultStorage.save(difficultLevel);
        //intent.putExtra("difficultLevel", difficultLevel.getId());
        startActivity(intent);
    }
    public void backMenuActivity() {
        Intent intent = new Intent(DifficultActivity.this, MainMenuActivity.class);
        startActivity(intent);
    }
}
