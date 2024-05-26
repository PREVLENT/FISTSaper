package com.example.sapernewvers.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.sapernewvers.R;
import com.example.sapernewvers.web.UserService;
import com.example.sapernewvers.web.dto.PlayerInfo;
import com.example.sapernewvers.web.dto.TopPlayerResponse;

public class TopActivity extends Activity {
    private Button backButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.top_players);
        printTopPlayer();

        backButton = findViewById(R.id.returnButton);

        backButton.setOnClickListener(v -> {
            returnMenu();
        });
    }

    public void returnMenu() {
        Intent intent = new Intent(TopActivity.this, MainMenuActivity.class);
        startActivity(intent);
    }

    private void printTopPlayer() {
        new Thread(() -> {
            TopPlayerResponse response = UserService.getTopPlayer();
            runOnUiThread(() -> {
                TableLayout tableLayout = findViewById(R.id.tableLayout);

                for (int i = 0; i < response.getPlayerInfoList().size(); i++) {
                    PlayerInfo playerInfo = response.getPlayerInfoList().get(i);
                    TableRow tableRow = new TableRow(this);

                    tableRow.setLayoutParams(new TableLayout.LayoutParams(
                            TableLayout.LayoutParams.MATCH_PARENT,
                            TableLayout.LayoutParams.WRAP_CONTENT));

                    TextView textView = new TextView(this);
                    textView.setText(playerInfo.getName() + " " + playerInfo.getScore());
                    tableRow.addView(textView);

                    tableLayout.addView(tableRow);
                }
            });
        }).start();
    }
}
