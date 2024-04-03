package com.example.l9q1;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);

        Spinner colorSpinner = findViewById(R.id.colorSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.color_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        colorSpinner.setAdapter(adapter);

        Button submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitSurvey();
            }
        });
    }

    private void submitSurvey() {
        String selectedColor = ((Spinner) findViewById(R.id.colorSpinner)).getSelectedItem().toString();

        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NAME, "Anonymous");
        values.put(DatabaseHelper.COLUMN_COLOR, selectedColor);
        db.insert(DatabaseHelper.TABLE_SURVEY, null, values);
        db.close();

        Toast.makeText(this, "Survey submitted. Thank you!", Toast.LENGTH_SHORT).show();
        showSurveyResults();
    }

    private void showSurveyResults() {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + DatabaseHelper.COLUMN_COLOR + ", COUNT(*) FROM " +
                DatabaseHelper.TABLE_SURVEY + " GROUP BY " + DatabaseHelper.COLUMN_COLOR, null);

        Map<String, Integer> colorCounts = new HashMap<>();
        int totalResponses = 0;

        // Calculate counts for each color and total responses
        while (cursor.moveToNext()) {
            String color = cursor.getString(0);
            int count = cursor.getInt(1);
            colorCounts.put(color, count);
            totalResponses += count;
        }
        cursor.close();
        db.close();

        // Calculate percentages
        final Map<String, Float> colorPercentages = new HashMap<>();
        for (Map.Entry<String, Integer> entry : colorCounts.entrySet()) {
            String color = entry.getKey();
            int count = entry.getValue();
            float percentage = (count / (float) totalResponses) * 100;
            colorPercentages.put(color, percentage);
        }

        // Build and show AlertDialog with survey results
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Survey Results");
        StringBuilder message = new StringBuilder();
        for (Map.Entry<String, Float> entry : colorPercentages.entrySet()) {
            message.append(entry.getKey()).append(": ").append(String.format("%.2f", entry.getValue())).append("%\n");
        }
        builder.setMessage(message.toString());
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}