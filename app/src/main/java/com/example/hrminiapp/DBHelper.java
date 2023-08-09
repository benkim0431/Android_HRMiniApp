package com.example.hrminiapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.Nullable;


public class DBHelper extends SQLiteOpenHelper {
    static final String DBNAME = "Company.db";
    static final int VERSION = 1;  //initial version
    static final String TABLE_NAME="Employee";  // Table name
    // Columns of Employee Table
    static final String COL1="id";
    static final String COL2="name";
    static final String COL3="designation";
    static final String COL4="department";
    static final String COL5="emailid";
    static final String COL6="salary";
    // SQL Query for creating table
    static final String CREATE_TABLE="create table " + TABLE_NAME +
            " (" + COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL2 + " TEXT NOT NULL, " +
            COL3 + " TEXT, " +
            COL4 + " TEXT, " +
            COL5 + " TEXT, " +
            COL6 + " INTEGER); ";
    static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public  DBHelper(Context context)
    {
        //create database with version
        super(context,DBNAME, null, VERSION);
    }

    //create Table
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    // Drop and recreate table
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    // Insert Row into table using ContentsValues
    public boolean InsertEmployee(Employee objEmp)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL2, objEmp.getName());
        cv.put(COL3, objEmp.getDesig());
        cv.put(COL4, objEmp.getDept());
        cv.put(COL5, objEmp.getEmailid());
        cv.put(COL6, objEmp.getSalary());
        long result = db.insert(TABLE_NAME, null, cv);
        return((result==-1)? false: true);
    }

    // Delete Row from table
    public Integer DeleteEmployee(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "id="+id,null);
    }

    // Using cursor, select all rows from table, and return cursor
    public Cursor ListEmployee()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursorObj;
        cursorObj=db.rawQuery("select * from " + TABLE_NAME, null);
        if(cursorObj != null)
        {
            cursorObj.moveToFirst();
        }
        return cursorObj;
    }
}
