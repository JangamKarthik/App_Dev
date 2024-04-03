package com.example.airlinereservation;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    String[] airlines ={ "Air India", "Indigo", "Air Asia", "SpiceJet"};
    String[] places = { "Kolkata", "Mangalore", "Mumbai", "Chennai", "Delhi"};
    private DatabaseHelper databaseHelper;
    String date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);

        Spinner fromSpinner = findViewById(R.id.from);
        Spinner toSpinner = findViewById(R.id.to);
        Spinner airlineSpinner = findViewById(R.id.airline);
        ArrayAdapter<String> placeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, places);
        ArrayAdapter<String> airlineAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, airlines);
        fromSpinner.setAdapter(placeAdapter);
        toSpinner.setAdapter(placeAdapter);
        airlineSpinner.setAdapter(airlineAdapter);

        CalendarView calendarView = findViewById(R.id.calenderView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                // Format selected date
                date = String.format(Locale.getDefault(), "%04d-%02d-%02d", year, month + 1, dayOfMonth);
            }
        });

        Button bookButton = findViewById(R.id.book);
        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookFlight();
            }
        });

        Button pastBookingsButton = findViewById(R.id.bookings);
        pastBookingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,BookingsActivity.class);
                startActivity(intent);
            }
        });
    }
    private void bookFlight() {
        String date = this.date;
        String from = ((Spinner) findViewById(R.id.from)).getSelectedItem().toString();
        String to = ((Spinner) findViewById(R.id.to)).getSelectedItem().toString();
        String airline = ((Spinner) findViewById(R.id.airline)).getSelectedItem().toString();

        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_DATE, date);
        values.put(DatabaseHelper.COLUMN_FROM, from);
        values.put(DatabaseHelper.COLUMN_TO, to);
        values.put(DatabaseHelper.COLUMN_AIRLINE, airline);
        db.insert(DatabaseHelper.TABLE_BOOKINGS, null, values);
        db.close();
        Toast.makeText(this,"Booking successful!!",Toast.LENGTH_SHORT).show();
    }
}