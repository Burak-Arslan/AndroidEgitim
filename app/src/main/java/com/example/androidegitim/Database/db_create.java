package com.example.androidegitim.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class db_create extends SQLiteOpenHelper {


    public db_create(Context context, int version) {
        super(context, "egitim", null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {

            db.execSQL("CREATE TABLE if not exists User (" +
                    "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "UserName TEXT , " +
                    "UserPassword  TEXT , " +
                    "Cinsiyet  TEXT , " +
                    "Telefon  TEXT , " +
                    "EventDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL)");

        } catch (Exception ex) {
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void openDB(SQLiteDatabase db) {
        onOpen(db);
    }
}
