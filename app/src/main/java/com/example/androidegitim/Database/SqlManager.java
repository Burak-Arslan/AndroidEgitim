package com.example.androidegitim.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.annotation.Nullable;

public class SqlManager {

    private static SQLiteDatabase _db;
    Context context;
    private db_create db;

    public SqlManager(Context _context) {
        context = _context;
        CreateDBInstanece();
    }

    public void CreateDBInstanece() {
        if (_db == null) {
            db = new db_create(context, 1);
            _db = db.getReadableDatabase();

        }
    }

    public long Insert(String tableName, ContentValues values) {

        long result = _db.insert(tableName, "", values);

        return result;
    }

    public int Update(String tableName, ContentValues values, String whereParams, String[] whereValues) {
        int result = _db.update(tableName, values, whereParams, whereValues);
        return result;
    }

    public boolean ExecuteQuery(String query) {
        if (!_db.isOpen()) {
            db.openDB(_db);
        }

        boolean result = false;
        try {
            _db.execSQL(query);
            result = true;
        } catch (Exception ex) {

        }

        return result;
    }

    public Cursor ExecuteRawQuery(String query, @Nullable String[] params) {
        if (!_db.isOpen()) {
            db.openDB(_db);
        }

        Cursor cursor = null;
        try {
            cursor = _db.rawQuery(query, params);
        } catch (Exception ex) {

        }
        return cursor;
    }

    public Cursor GetTableValues(String tableName, String whereQuery, String[] parameterValues) {
        if (!_db.isOpen()) {
            db.openDB(_db);
        }

        Cursor cursor = null;
        try {
            cursor = _db.rawQuery("SELECT * FROM " + tableName + " " + whereQuery, parameterValues);


        } catch (SQLiteException Exp) {
            Exp.printStackTrace();
        }
        return cursor;
    }

    public Cursor GetTableValues(String tableName) {
        if (!_db.isOpen()) {
            db.openDB(_db);
        }

        Cursor cursor = null;
        try {
            cursor = _db.rawQuery("select * from " + tableName, null);
        } catch (SQLiteException Exp) {
            Exp.printStackTrace();
        }
        return cursor;
    }

    public String GetStringValue(Cursor c, String colName) {
        int colIndex = c.getColumnIndex(colName);
        String result = "";
        if (colIndex > -1) {
            result = c.getString(colIndex);
        } else {
            result = colName + " alan bulunamadı";
        }

        return result;
    }

    public Integer GetIntValue(Cursor c, String colName) {
        int colIndex = c.getColumnIndex(colName);
        Integer result = c.getInt(colIndex);
        return result;
    }
}
