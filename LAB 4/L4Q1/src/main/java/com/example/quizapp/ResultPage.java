package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_page);
        Intent intent = getIntent();
        int recv = intent.getIntExtra("SCORE",0);
        TextView res = findViewById(R.id.textView6);
        res.setText("Your SCORE IS: "+recv);
    }
}