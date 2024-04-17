package com.example.prac;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "employee_db";
    private static final String TABLE_EMPLOYEE = "employees";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PHN = "phone";
    private static final String KEY_COUNTRY = "country";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_EMPLOYEE_TABLE = "CREATE TABLE " + TABLE_EMPLOYEE + "("
                + KEY_ID + " TEXT PRIMARY KEY,"
                + KEY_NAME + " TEXT,"
                + KEY_PHN + " TEXT,"
                + KEY_COUNTRY + " TEXT" + ")";
        db.execSQL(CREATE_EMPLOYEE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMPLOYEE);
        onCreate(db);
    }

    public void addEmployee(Employee employee) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID, employee.getid());
        values.put(KEY_NAME, employee.getname());
        values.put(KEY_PHN, employee.getphn());
        values.put(KEY_COUNTRY, employee.getplace());
        db.insert(TABLE_EMPLOYEE, null, values);
        db.close();
    }

    public void updateEmployee(String id, String country) {
        SQLiteDatabase db = this.getWritableDatabase();
        String UPDATEQUERY = "UPDATE " + TABLE_EMPLOYEE + " SET " + KEY_COUNTRY + " = '" + country + "' WHERE " + KEY_ID + " = '" + id + "'";
        db.execSQL(UPDATEQUERY);
        db.close();
    }


    public List<Employee> getAllEmployees() {
        List<Employee> productList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_EMPLOYEE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Employee product = new Employee(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
                productList.add(product);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return productList;
    }

    public List<Employee> getSpecificEmployees(String country) {
        List<Employee> productList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_EMPLOYEE + " WHERE " + KEY_COUNTRY + " = ?";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{country});
        if (cursor.moveToFirst()) {
            do {
                Employee product = new Employee(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
                productList.add(product);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return productList;
    }


    public List<Employee> getAllSortedEmployees() {
        List<Employee> productList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_EMPLOYEE + " ORDER BY " + KEY_ID +" DESC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Employee product = new Employee(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
                productList.add(product);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return productList;
    }

    public List<Employee> getone(String id) {
        List<Employee> productList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_EMPLOYEE + " WHERE " + KEY_ID + " = "+ id;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Employee product = new Employee(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
                productList.add(product);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return productList;
    }
}