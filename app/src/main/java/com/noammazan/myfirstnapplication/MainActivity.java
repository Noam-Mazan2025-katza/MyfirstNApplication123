package com.noammazan.myfirstnapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.SeekBar;
import android.graphics.Color;

public class MainActivity extends AppCompatActivity {
    private static final int START_GAME = 2222;
    Button btn1,btn2,btnLinear,btn3;
Switch switchColor;
ConstraintLayout layout;
    SeekBar sb;
    ImageView im;
    TextView tvAttempts;


TextView tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        setContentView(R.layout.activity_main);

        // אתחול של ה-TextView בתוך המתודה onCreate
        tvAttempts = findViewById(R.id.textView5);

        // שאר הקוד שלך
        initView();
    }

    private void initView() {
        btn1 = findViewById(R.id.btnClickListenerId);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "היידד1" , Toast.LENGTH_LONG).show();
            }
        });

        btn2 = findViewById(R.id.btnClickListenerId2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "היידד2" , Toast.LENGTH_LONG).show();
            }
        });

        tv1 =findViewById(R.id.textView2);
        layout= findViewById(R.id.constraintLayout);

        switchColor = findViewById(R.id.switchChangeColor);
        layout.setBackgroundColor(0xFFAB99CC);
        switchColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if (switchColor.isChecked()) {
                    layout.setBackgroundColor(Color.BLACK); // צבע חדש
                } else {
                    layout.setBackgroundColor(Color.parseColor("#4F5A78")); // צבע המקורי
                }
            }
        });
        im=findViewById(R.id.im);
        sb = findViewById(R.id.seekBar);
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                float alpha =(float) i/100;
                im.setAlpha(alpha);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        }
        );

        btnLinear= findViewById(R.id.button123);
        btnLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, LinearActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btn3 = findViewById(R.id.button3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, guessing_number.class);
                startActivityForResult(intent, START_GAME);  // התחלת המשחק
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // בדוק אם מדובר בבקשה שהתחלתה מתוך START_GAME
        if (requestCode == START_GAME && resultCode == RESULT_OK && data != null) {
            // קבלת כמות הניסיונות שהוחזרה מ-GuessingNumberActivity
            int attempts = data.getIntExtra("attemptCount", 0); // 0 הוא ערך ברירת המחדל אם אין נתון

            // הצגת כמות הניסיונות ב-TextView
            tvAttempts.setText("Number of attempts: " + attempts);
        }
    }



    public void myfistClick(View view) {

        }


/*    @Override
    public void onClick(View view) {
        if(view==btn1){
            Toast.makeText(this, "היידד1" , Toast.LENGTH_LONG).show();
            Toast.makeText(this, "היידד2" , Toast.LENGTH_LONG).show();
        }

        else if(view==btn2)
            Toast.makeText(this, "היידד2" , Toast.LENGTH_LONG).show();
    } */
}
