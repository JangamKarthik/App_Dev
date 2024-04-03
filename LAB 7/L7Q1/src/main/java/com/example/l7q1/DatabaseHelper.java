package com.example.l7q1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "student.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "student";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_ROLL_NUMBER = "roll_number";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_MARKS = "marks";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_ROLL_NUMBER + " VARCHAR, "
                + COLUMN_NAME + " VARCHAR, "
                + COLUMN_MARKS + " VARCHAR)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addStudent(String rollNumber, String name, String marks) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ROLL_NUMBER, rollNumber);
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_MARKS, marks);
        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }

    public Cursor getAllStudents() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    // Inside DatabaseHelper.java
    public boolean updateStudent(String rollNumber, String name, String marks) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_MARKS, marks);
        int affectedRows = db.update(TABLE_NAME, values, COLUMN_ROLL_NUMBER + "=?", new String[]{rollNumber});
        return affectedRows > 0;
    }


    public boolean deleteStudent(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int deletedRows = db.delete(TABLE_NAME, "id=?", new String[]{id});
        return deletedRows > 0;
    }
}
