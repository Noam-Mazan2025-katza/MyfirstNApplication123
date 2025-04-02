package com.noammazan.myfirstnapplication;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import androidx.annotation.Nullable;

public class Service_music extends Service {

    private MediaPlayer mediaPlayer;

    @Override
    public void onCreate() {
        super.onCreate();
        // יצירת MediaPlayer וטעינת המוזיקה (נניח המוזיקה נמצאת בתיקיית raw)
        mediaPlayer = MediaPlayer.create(this, R.raw.background_music); // החלף לנתיב הנכון למוזיקה שלך
        mediaPlayer.setLooping(true); // להגדיר את המוזיקה לחזור על עצמה
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // הפעלת המוזיקה כשמפעילים את השירות
        if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
        return START_STICKY; // השירות יתחיל מחדש אם יפסיק
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // עצירת המוזיקה ושחרור המשאבים
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null; // לא נדרש כאן, זהו שירות לא-ממומש
    }
}
