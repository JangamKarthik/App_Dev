package com.example.l8q3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "doctors_db";
    private static final String TABLE_DOCTORS = "doctors";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_IS_FREE = "is_free";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_DOCTORS_TABLE = "CREATE TABLE " + TABLE_DOCTORS + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " TEXT,"
                + KEY_IS_FREE + " INTEGER" + ")";
        db.execSQL(CREATE_DOCTORS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DOCTORS);
        onCreate(db);
    }

    public void addDoctor(Doctor doctor) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, doctor.getName());
        values.put(KEY_IS_FREE, doctor.isFree() ? 1 : 0);
        db.insert(TABLE_DOCTORS, null, values);
        db.close();
    }

    public List<Doctor> getAllDoctors() {
        List<Doctor> doctorList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_DOCTORS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Doctor doctor = new Doctor(cursor.getInt(0), cursor.getString(1), cursor.getInt(2) == 1);
                doctorList.add(doctor);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return doctorList;
    }

    public int updateDoctorStatus(int doctorId, boolean isFree) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_IS_FREE, isFree ? 1 : 0);
        return db.update(TABLE_DOCTORS, values, KEY_ID + " = ?", new String[]{String.valueOf(doctorId)});
    }
}
