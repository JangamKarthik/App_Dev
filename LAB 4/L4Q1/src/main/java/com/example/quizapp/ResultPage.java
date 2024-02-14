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
        int receivedInteger = intent.getIntExtra("MY_INTEGER_KEY", 0);
        String str = Integer.valueOf(receivedInteger).toString();
        TextView res = findViewById(R.id.textView6);
        res.setText("Your score is: "+str);
    }
}