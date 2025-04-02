package com.noammazan.myfirstnapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Setting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // אפשרות של Edge to Edge
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_setting);

        // שמירה על תוספת ה-padding בהסתמך על ה-System Bars (למשל, נוטף מעל התפריט)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // מניח שהקובץ שלך נקרא menu_main.xml או משהו דומה, ודאג לכלול את התפריט כאן
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // טיפול בהקליקים על פריטי התפריט
        switch (item.getItemId()) {
            case R.id.settings:
                // אם נבחרה "הגדרות" או פריט דומה, אפשר לבצע פעולות נוספות
                return true;
            case R.id.item1:
                // אם נבחרה קטגוריה 1, נווט לפעולה אחרת (למשל, Activity חדשה)
                Intent intent = new Intent(Setting.this, Setting.class);
                startActivity(intent);
                return true;
            case R.id.item2:
                // אם נבחרה "Regulations", זה יכול להוביל לפעולה אחרת
                return true;
            case R.id.item3:
                // אם נבחרה קטגוריה 3, זה גם יכול להוביל לפעולה אחרת
                return true;
            case R.id.main:
                // אם נבחר "Main" ניתן להחזיר לפעולה הראשית, למשל
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
