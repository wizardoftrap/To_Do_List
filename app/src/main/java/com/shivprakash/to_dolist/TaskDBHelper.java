package com.shivprakash.to_dolist;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TaskDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "tasklist.db";
    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TASK_TABLE =
            "CREATE TABLE " + TaskContract.TaskEntry.TABLE_NAME + " (" +
                    TaskContract.TaskEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    TaskContract.TaskEntry.COLUMN_TASK + " TEXT NOT NULL, " +
                    TaskContract.TaskEntry.COLUMN_CATEGORY + " TEXT, " +
                    TaskContract.TaskEntry.COLUMN_PRIORITY + " TEXT, " +
                    TaskContract.TaskEntry.COLUMN_NOTES + " TEXT, " +
                    TaskContract.TaskEntry.COLUMN_DUE_DATE + " TEXT, " +
                    TaskContract.TaskEntry.COLUMN_DUE_TIME + " TEXT, " +
                    TaskContract.TaskEntry.COLUMN_COMPLETED + " INTEGER DEFAULT 0);";

    public TaskDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TASK_TABLE);
    }
    public Cursor getAllTasks() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(
                TaskContract.TaskEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
