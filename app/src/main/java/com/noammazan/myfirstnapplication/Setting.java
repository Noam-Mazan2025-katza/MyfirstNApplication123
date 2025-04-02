package com.noammazan.myfirstnapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

public class Setting extends AppCompatActivity {

    private Button playMusicButton;
    private boolean isMusicPlaying = false; // משתנה שיאחסן את מצב המוזיקה (אם מנוגנת או לא)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        playMusicButton = findViewById(R.id.music_switch); // קישור לכפתור מה-XML

        // הגדרת לחצן שיאפשר להפעיל ולהפסיק את המוזיקה
        playMusicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isMusicPlaying) {
                    // אם המוזיקה מנוגנת, נעצור את השירות
                    stopService(new Intent(Setting.this, Service_music.class));
                    playMusicButton.setText("הפעל מוזיקה"); // עדכון הטקסט של הכפתור
                    isMusicPlaying = false; // עדכון מצב המוזיקה
                } else {
                    // אם המוזיקה לא מנוגנת, נתחיל את השירות
                    startService(new Intent(Setting.this, Service_music.class));
                    playMusicButton.setText("עצור מוזיקה"); // עדכון הטקסט של הכפתור
                    isMusicPlaying = true; // עדכון מצב המוזיקה
                }
            }
        });
    }
}
