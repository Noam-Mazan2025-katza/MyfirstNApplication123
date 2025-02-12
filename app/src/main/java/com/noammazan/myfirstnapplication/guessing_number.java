package com.noammazan.myfirstnapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class guessing_number extends AppCompatActivity {

    private EditText editText;
    private Button buttonGuess;
    private TextView textViewResult;
    private RadioButton radioButton1, radioButton2, radioButton3;
    private int randomNumber;
    private int lowerBound = 1;
    private int upperBound = 100;
    private int attemptCount = 0; // משתנה למעקב אחרי מספר הניסיונות


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guessing_number);

        // אתחול רכיבי UI
        editText = findViewById(R.id.editId);
        buttonGuess = findViewById(R.id.button);
        textViewResult = findViewById(R.id.textView4);
        radioButton1 = findViewById(R.id.radioButton1);
        radioButton2 = findViewById(R.id.radioButton2);
        radioButton3 = findViewById(R.id.radioButton3);

        // קביעת פעולה לכפתור GUESS
        buttonGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeGuess();
            }
        });

        // הגדרת טווח מספרים לפי בחירת RadioButton
        radioButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lowerBound = 1;
                upperBound = 100;
                generateRandomNumber();
                updateCategoryText();
            }
        });

        radioButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lowerBound = 100;
                upperBound = 200;
                generateRandomNumber();
                updateCategoryText();
            }
        });

        radioButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lowerBound = 200;
                upperBound = 300;
                generateRandomNumber();
                updateCategoryText();
            }
        });

        // ברירת מחדל: טווח 1-100
        generateRandomNumber();
    }

    // פונקציה ליצירת מספר אקראי בטווח הנבחר
    private void generateRandomNumber() {
        Random rand = new Random();
        randomNumber = rand.nextInt(upperBound - lowerBound + 1) + lowerBound;
    }

    // עדכון טקסט המורה על הטווח הנבחר
    private void updateCategoryText() {
        textViewResult.setText("Guess the number from " + lowerBound + " to " + upperBound + ":");
    }

    // פונקציה לניחוש המספר
    private void makeGuess() {
        String userInput = editText.getText().toString();

        if (userInput.isEmpty()) {
            textViewResult.setText("Please enter a valid number!");
            return;
        }

        int userGuess = Integer.parseInt(userInput);

        // הגברת המניין של הניסיונות
        attemptCount++;

        // בדיקה אם הניחוש נכון
        if (userGuess < randomNumber) {
            textViewResult.setText("Too low, try again!");
        } else if (userGuess > randomNumber) {
            textViewResult.setText("Too high, try again!");
        } else {
            // אם הניחוש נכון
            textViewResult.setText("Congratulations! You guessed the right number in " + attemptCount + " attempts!");

            // שלח את כמות הניסיונות חזרה ל-MainActivity
            Intent resultIntent = new Intent();
            resultIntent.putExtra("attemptCount", attemptCount);  // שליחת מספר הניסיונות
            setResult(RESULT_OK, resultIntent);  // מחזיר את הנתונים ל-Activity הראשי
            finish();  // סיום המשחק ויציאה חזרה ל-MainActivity
        }
    }


}
