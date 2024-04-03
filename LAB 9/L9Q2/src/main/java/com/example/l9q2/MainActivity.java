package com.example.l9q2;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    String[] airlines ={ "Air India", "Indigo", "Air Asia", "SpiceJet"};
    String[] places = { "Kolkata", "Mangalore", "Mumbai", "Chennai", "Delhi"};
    String[] passengerCounts = {"1", "2", "3", "4", "5"};
    String[] ticketClasses = {"Economy", "Business", "First Class"};

    private DatabaseHelper databaseHelper;
    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);

        Spinner fromSpinner = findViewById(R.id.fromSpinner);
        Spinner toSpinner = findViewById(R.id.toSpinner);
        Spinner airlineSpinner = findViewById(R.id.airlineSpinner);
        Spinner passengerSpinner = findViewById(R.id.passengerSpinner);
        Spinner classSpinner = findViewById(R.id.classSpinner);

        ArrayAdapter<String> placeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, places);
        ArrayAdapter<String> airlineAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, airlines);
        ArrayAdapter<String> passengerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, passengerCounts);
        ArrayAdapter<String> classAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, ticketClasses);

        fromSpinner.setAdapter(placeAdapter);
        toSpinner.setAdapter(placeAdapter);
        airlineSpinner.setAdapter(airlineAdapter);
        passengerSpinner.setAdapter(passengerAdapter);
        classSpinner.setAdapter(classAdapter);

        Button bookFlightButton = findViewById(R.id.bookFlightButton);
        bookFlightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookFlight();
            }
        });
    }

    private void bookFlight() {
        String date = this.date;
        String from = ((Spinner) findViewById(R.id.fromSpinner)).getSelectedItem().toString();
        String to = ((Spinner) findViewById(R.id.toSpinner)).getSelectedItem().toString();
        String airline = ((Spinner) findViewById(R.id.airlineSpinner)).getSelectedItem().toString();
        String passengers = ((Spinner) findViewById(R.id.passengerSpinner)).getSelectedItem().toString();
        String ticketClass = ((Spinner) findViewById(R.id.classSpinner)).getSelectedItem().toString();

        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_DATE, date);
        values.put(DatabaseHelper.COLUMN_FROM, from);
        values.put(DatabaseHelper.COLUMN_TO, to);
        values.put(DatabaseHelper.COLUMN_AIRLINE, airline);
        values.put(DatabaseHelper.COLUMN_PASSENGERS, passengers);
        values.put(DatabaseHelper.COLUMN_CLASS, ticketClass);

        db.insert(DatabaseHelper.TABLE_BOOKINGS, null, values);
        db.close();

        Toast.makeText(this,"Booking successful!!",Toast.LENGTH_SHORT).show();
    }
}
