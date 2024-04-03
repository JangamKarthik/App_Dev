package com.example.airlinereservation;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

public class DatabaseHelper extends SQLiteOpenHelper {
    static final String DATABASE_NAME = "airline_reservation.db";
    static final int DATABASE_VERSION = 1;
    static final String TABLE_BOOKINGS = "bookings";
    static final String COLUMN_ID = "id";
    static final String COLUMN_DATE = "date";
    static final String COLUMN_FROM = "from_location";
    static final String COLUMN_TO = "to_location";
    static final String COLUMN_AIRLINE = "airline";

    // SQL statement to create the bookings table
    private static final String CREATE_TABLE_BOOKINGS = "CREATE TABLE " + TABLE_BOOKINGS +
            "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_DATE + " TEXT," +
            COLUMN_FROM + " TEXT," +
            COLUMN_TO + " TEXT," +
            COLUMN_AIRLINE + " TEXT" +
            ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_BOOKINGS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKINGS);

        // Create tables again
        onCreate(db);
    }
}
