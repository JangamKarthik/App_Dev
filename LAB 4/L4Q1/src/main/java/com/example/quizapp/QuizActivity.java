package com.example.quizapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class QuizActivity extends AppCompatActivity {

    private RadioGroup radioGroup1, radioGroup2, radioGroup3, radioGroup4, radioGroup5;
    private Button submitButton;

    public int score = 0;
    public static final int tr_score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        radioGroup1 = findViewById(R.id.optionsRadioGroup);
        radioGroup2 = findViewById(R.id.optionsRadioGroup2);
        radioGroup3 = findViewById(R.id.optionsRadioGroup3);
        radioGroup4 = findViewById(R.id.optionsRadioGroup4);
        radioGroup5 = findViewById(R.id.optionsRadioGroup5);

        submitButton = findViewById(R.id.button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSubmitConfirmationDialog();
            }
        });
    }

    private void showSubmitConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want to submit the quiz?")
                .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int score = calculateScore();
                        showResultActivity(score);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private int calculateScore() {


        // Example: Check the selected radio button for each question
        if (isCorrectAnswer(radioGroup1, R.id.option2RadioButton)) {
            score++;
        }
        if (isCorrectAnswer(radioGroup2, R.id.option3RadioButton2)) {
            score++;
        }
        if (isCorrectAnswer(radioGroup3, R.id.option2RadioButton3)) {
            score++;
        }
        if (isCorrectAnswer(radioGroup4, R.id.option3RadioButton4)) {
            score++;
        }
        if (isCorrectAnswer(radioGroup5, R.id.option2RadioButton5)) {
            score++;
        }

        return score;
    }

    private boolean isCorrectAnswer(RadioGroup radioGroup, int correctOptionId) {
        int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
        return selectedRadioButtonId == correctOptionId;
    }

    private void showResultActivity(int score) {
        Intent intent = new Intent(this, ResultPage.class);
        intent.putExtra("SCORE", score);
        startActivity(intent);
    }
}