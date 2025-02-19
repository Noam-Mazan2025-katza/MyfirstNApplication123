package com.noammazan.myfirstnapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
public class NewActivity  extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // קובץ ה-XML של העמוד

        ImageView timerImage = findViewById(R.id.textView3); // מזהה התמונה

        final int[] images = {
                R.drawable.five,
                R.drawable.four,
                R.drawable.three,
                R.drawable.two,
                R.drawable.one,
        };

        new CountDownTimer(5000, 1000) {
            int i = 0;
            public void onTick(long millisUntilFinished) {
                timerImage.setImageResource(images[i]); // מחליף תמונה בכל שנייה
                i++;
            }

            public void onFinish() {
                Intent intent = new Intent(NewActivity.this,MainActivity.class);
                startActivity(intent); // מעבר לעמוד הבא אחרי הספירה
            }
        }.start();
    }

}
