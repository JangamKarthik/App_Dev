package com.example.l7q1;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private ListView studentListView;
    private ArrayAdapter<String> adapter;
    private List<String> studentDetailsList;
    private EditText rollNumberEditText, nameEditText, marksEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);

        rollNumberEditText = findViewById(R.id.rollNumberEditText);
        nameEditText = findViewById(R.id.nameEditText);
        marksEditText = findViewById(R.id.marksEditText);

        Button addButton = findViewById(R.id.addButton);
        Button updateButton = findViewById(R.id.updateButton);
        Button deleteButton = findViewById(R.id.deleteButton);

        studentListView = findViewById(R.id.studentListView);
        studentDetailsList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, studentDetailsList);
        studentListView.setAdapter(adapter);

        loadStudentList();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rollNumber = rollNumberEditText.getText().toString().trim();
                String name = nameEditText.getText().toString().trim();
                String marks = marksEditText.getText().toString().trim();
                if (!rollNumber.isEmpty() && !name.isEmpty() && !marks.isEmpty()) {
                    boolean success = dbHelper.addStudent(rollNumber, name, marks);
                    if (success) {
                        Toast.makeText(MainActivity.this, "Student added successfully", Toast.LENGTH_SHORT).show();
                        loadStudentList();
                        clearFields();
                    } else {
                        Toast.makeText(MainActivity.this, "Failed to add student", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Inside MainActivity.java
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rollNumber = rollNumberEditText.getText().toString().trim();
                String name = nameEditText.getText().toString().trim();
                String marks = marksEditText.getText().toString().trim();
                if (!rollNumber.isEmpty() && !name.isEmpty() && !marks.isEmpty()) {
                    boolean success = dbHelper.updateStudent(rollNumber, name, marks);
                    if (success) {
                        Toast.makeText(MainActivity.this, "Student updated successfully", Toast.LENGTH_SHORT).show();
                        loadStudentList();
                        clearFields();
                    } else {
                        Toast.makeText(MainActivity.this, "Failed to update student", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rollNumber = rollNumberEditText.getText().toString().trim();
                if (!rollNumber.isEmpty()) {
                    boolean success = dbHelper.deleteStudent(rollNumber);
                    if (success) {
                        Toast.makeText(MainActivity.this, "Student deleted successfully", Toast.LENGTH_SHORT).show();
                        loadStudentList();
                        clearFields();
                    } else {
                        Toast.makeText(MainActivity.this, "Failed to delete student", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Please enter roll number to delete", Toast.LENGTH_SHORT).show();
                }
            }
        });

        studentListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String studentDetails = adapter.getItem(position);
                // You can parse the student details if needed
                Toast.makeText(MainActivity.this, "Clicked on: " + studentDetails, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadStudentList() {
        studentDetailsList.clear();
        Cursor cursor = dbHelper.getAllStudents();
        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(0);
                String rollNumber = cursor.getString(1);
                String name = cursor.getString(2);
                String marks = cursor.getString(3);
                String studentDetails = "ID: " + id + "\nRoll Number: " + rollNumber + "\nName: " + name + "\nMarks: " + marks;
                studentDetailsList.add(studentDetails);
            } while (cursor.moveToNext());
            adapter.notifyDataSetChanged();
        } else {
            Toast.makeText(this, "No students found", Toast.LENGTH_SHORT).show();
        }
        cursor.close();
    }

    private void clearFields() {
        rollNumberEditText.setText("");
        nameEditText.setText("");
        marksEditText.setText("");
    }
}
