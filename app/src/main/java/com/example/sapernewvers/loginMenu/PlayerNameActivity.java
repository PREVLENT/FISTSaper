package com.example.sapernewvers.loginMenu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.ConditionVariable;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sapernewvers.R;
import com.example.sapernewvers.activity.MainMenuActivity;
import com.example.sapernewvers.web.UserService;

public class PlayerNameActivity extends Activity {
    private Button confirm;
    private EditText nameText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);


        Log.e("PlayerNameActivity", "onCreate");
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        if (sharedPreferences.contains("nickName")) {
            Intent intent = new Intent(PlayerNameActivity.this,
                    MainMenuActivity.class);
            startActivity(intent);
        } else {
            setContentView(R.layout.player_name);
        }
        confirm = findViewById(R.id.confirmBut);
        nameText = findViewById(R.id.editName);

        confirm.setOnClickListener(v -> {
            String text = nameText.getText().toString();

            Context context = this;

            if (!text.isEmpty() && text.length() > 3) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (UserService.isNameFree(text)){
                            sharedPreferences.edit().putString("nickName", text).apply();
                            Intent intent = new Intent(PlayerNameActivity.this,
                                    MainMenuActivity.class);
                            startActivity(intent);
                        }else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(context, "Имя на сервере уже занято!", Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    }
                }).start();
            } else {
                Toast.makeText(this, "Ник должен содержать больше трех символов!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
