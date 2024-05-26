package com.example.sapernewvers.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sapernewvers.R;
import com.example.sapernewvers.gameLogic.DifficultLevel;
import com.example.sapernewvers.gameLogic.Timer;
import com.example.sapernewvers.util.DifficultStorage;
import com.example.sapernewvers.util.TimerStorage;
import com.example.sapernewvers.web.UserService;

public class GameWinActivity extends Activity {
    private Button shareBut;
    private Button menuBut;
    private EditText editScore;
    private EditText serverScoreText;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        String nickName = sharedPreferences.getString("nickName", ""); //ToDo обработать пустое значение
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_victory);

        editScore = findViewById(R.id.editScore);
        DifficultLevel levelDifficult = DifficultStorage.get();
        Long score = (10000L * levelDifficult.getId()) / TimerStorage.getTimeInSecond();

        editScore.setText(String.valueOf(score));

        shareBut = findViewById(R.id.sendBut);

        shareBut.setOnClickListener(v -> {
            processClickShareButton(nickName, score);
        });

        menuBut = findViewById(R.id.menuBut);

        menuBut.setOnClickListener(v -> {
            openMainMenu();
        });

        serverScoreText = findViewById(R.id.serverScoreText);

        new Thread(() -> {
            Long scoreFromServer = UserService.getCurrentScoreByName(nickName);
            runOnUiThread(() -> serverScoreText.setText(String.valueOf(scoreFromServer)));
        }).start();
    }

    public void openMainMenu() {
        Intent intent = new Intent(GameWinActivity.this, MainMenuActivity.class);
        startActivity(intent);
    }

    public void processClickShareButton(String name, Long score) {
        new Thread(() -> {
            UserService.shareScore(name, score);
            runOnUiThread(() -> Toast.makeText(this, "Рекорд записан ;)", Toast.LENGTH_SHORT).show());
        }).start();
    }
}
