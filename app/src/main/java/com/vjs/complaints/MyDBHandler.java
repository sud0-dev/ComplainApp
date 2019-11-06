package com.vjs.complaints;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;

import androidx.annotation.Nullable;

import com.vjs.complaints.students.account;

public class MyDBHandler extends SQLiteOpenHelper {
        //information of database
        private static final int DATABASE_VERSION = 1;
        private static final String DATABASE_NAME = "complains.db";
        public static final String TABLE_NAME = "Complains";
        public static final String NAME = "Name";
        public static final String YEAR = "Year";
        public static final String BRANCH = "Branch";
        public static final String HOSTEL = "Hostel";
        public static final String COMPLAIN = "Complain";
        //initialize the database

        public MyDBHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + NAME +
                    "TEXT," + YEAR + "TEXT," + BRANCH + "TEXT," + HOSTEL + "TEXT," + COMPLAIN + "TEXT )";
            db.execSQL(CREATE_TABLE);
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int i1) {
            db.execSQL("drop table if exists Complains");
            onCreate(db);
        }

        public String loadHandler() {
            String result = "";
            String query = "Select*FROM " + TABLE_NAME ;
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(query, null);
            while (cursor.moveToNext()) {
                int result_0 = cursor.getInt(0);
                String result_1 = cursor.getString(1);
                result = String.valueOf(result_0) + " " + result_1 + System.getProperty("line.separator");
            }
            cursor.close();
            db.close();
            return result;
        }

        public void addHandler(account acc) {
            ContentValues values = new ContentValues();
            values.put(NAME, acc.name);
            values.put(YEAR, acc.year);
            values.put(BRANCH, acc.branch);
            values.put(HOSTEL, acc.hostel);
            values.put(COMPLAIN, acc.complain);
            SQLiteDatabase db = this.getWritableDatabase();
            db.insert(TABLE_NAME, null, values);
            db.close();
        }

        //public account findHandler(String name) {}
        //public boolean deleteHandler(int ID) {}
        //public boolean updateHandler(int ID, String name) {}
}
