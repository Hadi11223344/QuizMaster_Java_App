package com.example.quizmaster;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;



public class MyDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "QuizMaster";
    private static final int DATABASE_VERSION = 1;

    public MyDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final String TABLE_NAME = "user";
    private static final String KEY_ID = "_id";
    private static final String KEY_NAME = "_name";
    private static final String KEY_EMAIL = "_email";
    private static final String KEY_PASSWORD = "_password";

    private static final String TAG = "MyDBHelper";

    @Override
    public void onCreate(SQLiteDatabase db) {
        String q = "CREATE TABLE " + TABLE_NAME + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_NAME + " TEXT, " + KEY_EMAIL + " TEXT," + KEY_PASSWORD + " TEXT" + ")";

        db.execSQL(q);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public void addUser(String name, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        values.put(KEY_EMAIL, email);
        values.put(KEY_PASSWORD, password);

        try {
            db.insertOrThrow(TABLE_NAME, null, values);

        } catch (SQLiteConstraintException e) {
            // Handle the case where insertion failed due to a constraint violation (e.g., duplicate email)
            Log.e(TAG, "Error adding user: " + e.getMessage());
        } finally {
            db.close(); // Always remember to close the database connection
        }
    }

    public boolean verifyUser(String email,String password){
        SQLiteDatabase db=this.getWritableDatabase();
        String whereclause =  KEY_EMAIL+" = ? and "+ KEY_PASSWORD +" = ?"; //<<<<<<<<<< ?'s will be replaced according to whereargs on a 1 by 1 basis
        String[] columns = {KEY_ID};
        String[] whereargs = {email,password};
        Cursor cursor = db.query(
                TABLE_NAME,
                columns,
                whereclause,
                whereargs,null,null,null
        );
        int count = cursor.getCount();
        cursor.close();
        return count > 0;
    }
}


