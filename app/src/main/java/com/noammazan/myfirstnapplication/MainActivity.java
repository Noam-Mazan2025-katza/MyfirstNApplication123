package com.noammazan.myfirstnapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    Button btn1, btn2, btnLinear, btn3;
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
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.black));
        initView();

        // אתחול של ה-TextView בתוך המתודה onCreate
        tvAttempts = findViewById(R.id.textView5);

        // רישום ה-Context Menu לתמונה
        im = findViewById(R.id.im);
        registerForContextMenu(im);
    }

    private void initView() {
        btn1 = findViewById(R.id.btnClickListenerId);
        btn1.setOnClickListener(view ->
                Toast.makeText(MainActivity.this, "היידד1", Toast.LENGTH_LONG).show()
        );

        btn2 = findViewById(R.id.btnClickListenerId2);
        btn2.setOnClickListener(view ->
                Toast.makeText(MainActivity.this, "היידד2", Toast.LENGTH_LONG).show()
        );

        tv1 = findViewById(R.id.textView2);
        layout = findViewById(R.id.constraintLayout);

        switchColor = findViewById(R.id.switchChangeColor);
        layout.setBackgroundColor(0xFFAB99CC);
        switchColor.setOnClickListener(v -> {
            if (switchColor.isChecked()) {
                layout.setBackgroundColor(Color.BLACK); // צבע חדש
            } else {
                layout.setBackgroundColor(Color.parseColor("#4F5A78")); // צבע המקורי
            }
        });

        sb = findViewById(R.id.seekBar);
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                float alpha = (float) i / 100;
                im.setAlpha(alpha);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        btnLinear = findViewById(R.id.button123);
        btnLinear.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, LinearActivity.class);
            startActivity(intent);
            finish();
        });

        btn3 = findViewById(R.id.button3);
        btn3.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, guessing_number.class);
            startActivityForResult(intent, START_GAME);  // התחלת המשחק
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == START_GAME && resultCode == RESULT_OK && data != null) {
            int attempts = data.getIntExtra("attemptCount", 0); // 0 הוא ערך ברירת המחדל אם אין נתון
            tvAttempts.setText("Number of attempts: " + attempts);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        // שינוי צבע ה-title של כל הפריטים בתפריט
        for (int i = 0; i < menu.size(); i++) {
            MenuItem item = menu.getItem(i);
            SpannableString s = new SpannableString(item.getTitle());
            s.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.black)), 0, s.length(), 0);
            item.setTitle(s);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.item1:
                // יצירת Intent לאקטיביטי של קטגוריה 1
                Intent intent1 = new Intent(this, guessing_number.class);
                startActivity(intent1);
                return true;
            case R.id.item2:
                // יצירת Intent לאקטיביטי של קטגוריה 2
                Intent intent2 = new Intent(this, Regulations.class);
                startActivity(intent2);
                return true;
            case R.id.item3:
                // יצירת Intent לאקטיביטי של קטגוריה 3
                Intent intent3 = new Intent(this, LinearActivity.class);
                startActivity(intent3);
                return true;
            case R.id.main:
                Intent intent4 = new Intent(this, MainActivity.class);
                startActivity(intent4);
                return true;
            case R.id.settings:
                // אם נבחרה קטגוריה 1, נווט לפעולה אחרת (למשל, Activity חדשה)
                Intent intent = new Intent(this, Setting.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    // יצירת תפריט Context Menu לתמונה
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_main, menu); // קישור לתפריט ה-XML
    }

    // טיפול בלחיצה על הפריט בתפריט ההקשר
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.main) {

            Intent intent = new Intent(this, NewActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onContextItemSelected(item);
    }
}
