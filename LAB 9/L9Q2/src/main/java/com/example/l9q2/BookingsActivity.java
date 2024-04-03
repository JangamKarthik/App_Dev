package com.example.l9q2;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class BookingsActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookings);

        databaseHelper = new DatabaseHelper(this);

        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.query(DatabaseHelper.TABLE_BOOKINGS, null, null, null, null, null, null);

        StringBuilder pastBookings = new StringBuilder();
        while (cursor.moveToNext()) {
            pastBookings.append("Date: ").append(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_DATE)))
                    .append(", From: ").append(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_FROM)))
                    .append(", To: ").append(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_TO)))
                    .append(", Airline: ").append(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_AIRLINE)))
                    .append("\n");
        }
        cursor.close();
        db.close();

        TextView pastBookingsTextView = findViewById(R.id.pastBookingsTextView);
        pastBookingsTextView.setText(pastBookings.toString());
    }
}